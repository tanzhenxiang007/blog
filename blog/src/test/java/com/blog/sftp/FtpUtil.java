package com.blog.sftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 丁鹏飞
 * Date: 2017/12/11 14:04
 * Title: ftp工具类
 * Describe:
 */
public class FtpUtil {
    private static Logger logger= LoggerFactory.getLogger(FtpUtil.class);

    private int       port;
    private String    user;
    private String    password;
    private String    host;
    private FTPClient ftp;
    private boolean isConnected;


    public FtpUtil() {
        ftp          = new FTPClient();
        isConnected = false;
    }
    
    public FtpUtil(String host,int port,String user,String password) {
        ftp          = new FTPClient();
        isConnected = false;
        this.host=host;
        this.port=port;
        this.user=user;
        this.password=password;
        isConnected = false;
        ftp.setDefaultTimeout(30 * 1000);
        ftp.setConnectTimeout(10 * 1000);
        ftp.setDataTimeout(30 * 1000);
        ftp.setControlEncoding("utf-8");
    }

    public FtpUtil(int defaultTimeoutSecond, int connectTimeoutSecond, int dataTimeoutSecond) {
        ftp          = new FTPClient();
        isConnected = false;
        ftp.setDefaultTimeout(defaultTimeoutSecond * 1000);
        ftp.setConnectTimeout(connectTimeoutSecond * 1000);
        ftp.setDataTimeout(dataTimeoutSecond * 1000);
        ftp.setControlEncoding("UTF-8");
    }

    /**
     *
     * @param isTextMode 文本/二进制
     * @throws IOException 连接异常
     */
    public void connect(boolean isTextMode) throws IOException {
        // Connect to server.
        try {
            ftp.connect(host, port);
        } catch (UnknownHostException ex) {
            throw new IOException("Can't find FTP server '" + host + "'");
        }

        // Check rsponse after connection attempt.
        int reply = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();

            throw new IOException("Can't connect to server '" + host + "'");
        }

        // Login.
        if (!ftp.login(user, password)) {
            isConnected = false;
            disconnect();

            throw new IOException("Can't login to server '" + host + "'");
        } else {
            isConnected = true;
        }

        ftp.enterLocalActiveMode();
//        ftp.enterLocalPassiveMode();

        // Set data transfer mode.
        if (isTextMode) {
            ftp.setFileType(FTP.ASCII_FILE_TYPE);
        } else {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
        }
    }

    /**
     *创建文件夹
     * @param path  文件夹路径 例子:  aa/bb/cc
     * @throws IOException 连接异常
     */
    private void createDirs(String path) throws IOException {
        ftp.changeWorkingDirectory("/");

        String[] array = path.split("\\\\");

        for (String s : array) {
            ftp.makeDirectory(s);
            ftp.changeWorkingDirectory(s);
        }

        ftp.changeWorkingDirectory("/");
    }

    /**
     * 与服务器断开连接
     */
    public void disconnect()  {
    	isConnected = false;
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException ex) {
                logger.error(ex.getMessage(),ex);
            }
        }
    }

    /**
     * 下载
     * @param ftpFileName 服务器文件
     * @param localFile   本地文件
     * @throws IOException IO异常
     */
    public void download(String ftpFileName, File localFile) throws IOException {

        // Download.
        OutputStream out = null;

        try {
        	String fileName = ftpFileName;
            // Get file info.
        	if(ftpFileName.lastIndexOf("/")>=0) {
        		fileName = ftpFileName.substring(ftpFileName.lastIndexOf("/")+1);
        		String fileDirPath=ftpFileName.substring(0, ftpFileName.lastIndexOf("/")+1);
        		ftp.changeWorkingDirectory(fileDirPath);
        		ftp.enterLocalPassiveMode();
        	}else if(ftpFileName.lastIndexOf("\\")>=0) {
        		fileName = ftpFileName.substring(ftpFileName.lastIndexOf("\\")+1);
        		String fileDirPath=ftpFileName.substring(0, ftpFileName.lastIndexOf("\\")+1);
        		ftp.changeWorkingDirectory(fileDirPath);
        		ftp.enterLocalPassiveMode();
        	}
        	
            FTPFile[] fileInfoArray = ftp.listFiles();

            if (fileInfoArray == null||fileInfoArray.length==0) {
                throw new FileNotFoundException("File " + fileName + " was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long    size     = fileInfo.getSize();
            
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File " + fileName + " is too large.");
            }
            
            if(size <= 0) {
            	logger.warn("File " + fileName + " is empty.");
            }

            // Download file.
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            InputStream ins = ftp.retrieveFileStream(fileName);
            
            if(ins==null) {
            	throw new IOException("Error loading file " + fileName
                      + " from FTP server. Check FTP permissions and path.");
            }
            byte[] b = new byte[1024];
            int i = 0;
            while ((i=ins.read(b,0,1024))>0) {
				out.write(b,0,i);
			}
            ins.close();
            ftp.completePendingCommand();
//            if (!ftp.retrieveFile(ftpFileName, out)) {
//                throw new IOException("Error loading file " + ftpFileName
//                                      + " from FTP server. Check FTP permissions and path.");
//            }
            out.flush();
        } catch (FTPConnectionClosedException e) {
        	logger.error("<FtpUtil.download()> "+e.getMessage(), e);
//			this.connect(true);//重新连接
//			this.download(ftpFileName, localFile);
		}catch(SocketException e1) {
			logger.error("<FtpUtil.download()> "+e1.getMessage(), e1);
//			this.connect(true);//重新连接
//			this.download(ftpFileName, localFile);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(),ex);
                }
            }
        }
    }

    /**
     * 服务器上指定文件夹内文件名列表
     * @param filePath 文件路径
     * @return 文件名
     * @throws IOException IO异常
     */
    public List<String> list(String filePath) throws IOException {
        List<String> fileList = new ArrayList<>();
        ftp.changeWorkingDirectory(filePath);
        ftp.enterLocalPassiveMode();
        FTPFile[]    ftpFiles = ftp.listFiles();
        int          size     = (ftpFiles == null)
                                ? 0
                                : ftpFiles.length;

        for (int i = 0; i < size; i++) {
            FTPFile ftpFile = ftpFiles[i];

            if (ftpFile.isFile()) {
                fileList.add(ftpFile.getName());
            }
        }

        return fileList;
    }
    
    /**
     * 服务器上指定文件夹内文件名列表
     * @param filePath 路径
     * @return 文件列表
     * @throws IOException 连接服务器异常
     */
    public List<FtpFileInfo> listFiles(String filePath) throws IOException {
        List<FtpFileInfo> fileList = new ArrayList<>();
        ftp.changeWorkingDirectory(filePath);
        ftp.enterLocalPassiveMode();
        FTPFile[]         ftpFiles = ftp.listFiles();
        int               size     = (ftpFiles == null)
                                     ? 0
                                     : ftpFiles.length;

        for (int i = 0; i < size; i++) {
            FTPFile     ftpFile = ftpFiles[i];
            FtpFileInfo fi      = new FtpFileInfo();

            fi.setName(ftpFile.getName());
            fi.setSize(ftpFile.getSize());
            fi.setTimestamp(ftpFile.getTimestamp());
            fi.setType(ftpFile.isDirectory());
            fileList.add(fi);
        }

        return fileList;
    }

    /**
     * author 丁鹏飞
     * version V1.0.0
     * description 获取ftp服务器中的文件和目录
     * param remotePath 文件夹路径
     */
    @SuppressWarnings("unused")
	private void listRemoteAllFiles(String remotePath) {
        try {
            FTPFile[] files = ftp.listFiles(remotePath);

            for (FTPFile file:files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("目录" + file.getName());
                    listRemoteAllFiles(remotePath + file.getName() + "/");
                }
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        } finally {
            try {
                ftp.disconnect();
            } catch (IOException e) {
            	logger.error(e.getMessage(),e);
            }
        }
    }


    /**
     * 删除文件
     * @param ftpFileName 文件路径
     * @throws IOException IO异常
     */
    public void remove(String ftpFileName) throws IOException {
        if (!ftp.deleteFile(ftpFileName)) {
            throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
        }
    }


    /**
     *上传文件
     *
     * @param ftpFileName 服务器文件如果目录不存在，会自动创建目录
     * @param localFile   本地文件
     * @throws IOException IO异常
     */
    public void upload(String ftpFileName, File localFile) throws IOException {

        // File check.
        if (!localFile.exists()) {
            throw new IOException("Can't upload '" + localFile.getAbsolutePath() + "'. This file doesn't exist.");
        }

        // Upload.
        InputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(localFile));
            createDirs(new File(ftpFileName).getParent());

            if (!ftp.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName
                                      + "' to FTP server. Check FTP permissions and path.");
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        } finally {
            try {
                if(in!=null) {
                    in.close();
                }
            } catch (IOException ex) {
            	logger.error(ex.getMessage(),ex);
            }
        }
    }

    /**
     * 测试有没有连接到服务器
     * @return true/false
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * 获取文件
     * param ftpFileName 服务器文件
     * param out         输出流
     * throws IOException 异常
     */
    public void getFile(String ftpFileName, OutputStream out) throws IOException {
        try {

            // Get file info.
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);

            if (fileInfoArray == null) {
                throw new FileNotFoundException("File '" + ftpFileName + "' was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long    size     = fileInfo.getSize();

            if (size > Integer.MAX_VALUE) {
                throw new IOException("File '" + ftpFileName + "' is too large.");
            }

            // Download file.
            if (!ftp.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file '" + ftpFileName
                                      + "' from FTP server. Check FTP permissions and path.");
            }

            out.flush();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(),ex);
                }
            }
        }
    }

    /**
     * Get parent directory name on ftp server
     * @return parent directory
     */
    public String getParentDirectory() throws Exception {
        if (!isConnected) {
            return "";
        }

        String w = getWorkingDirectory();

        if(setParentDirectory()){
            throw new Exception("设置工作目录失败");
        }

        String p = getWorkingDirectory();

        if(setWorkingDirectory(w)){
            throw new Exception("设置工作目录失败");
        }

        return p;
    }

    /**
     * Change working directory on ftp server to parent directory
     * @return true, if working directory changed
     */
    private boolean setParentDirectory() {
        if (!isConnected) {
            return false;
        }

        try {
            return ftp.changeToParentDirectory();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        return false;
    }

    /**
     * Get current directory on ftp server
     *
     * @return current directory
     */
    public String getWorkingDirectory() {
        if (!isConnected) {
            return "";
        }

        try {
            return ftp.printWorkingDirectory();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        return "";
    }

    /**
     * 设置工作目录
     *
     * @param dir 新的工作目录
     * @return true, if working directory changed
     */
    public boolean setWorkingDirectory(String dir) {
        if (!isConnected) {
            return false;
        }

        try {
            return ftp.changeWorkingDirectory(dir);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        return false;
    }
    
    /**
     * 读取文件转行成List存储
     * @param ftpFileName
     * @return
     */
    public List<List<String>> getTextList(String ftpFileName){
    	int listSize = 40000;
    	ArrayList<List<String>> list = new ArrayList<>();
    	InputStream is = null;
    	InputStreamReader isr = null;
    	BufferedReader br = null;
        try {
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);

            if (fileInfoArray == null) {
    			disconnect();
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }
            
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();

            if (size > Integer.MAX_VALUE) {
    			disconnect();
                throw new IOException("File " + ftpFileName + " is too large.");
            }
            is = ftp.retrieveFileStream(ftpFileName);
            if(is==null){
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            int i = 0;
            ArrayList<String> alist = new ArrayList<>();
            while ((line = br.readLine()) != null) {
            	alist.add(line);
            	i++;
            	if(i == listSize){
                	list.add(alist);
            		alist = new ArrayList<>();
            		i = 0;
            	}
			}
            if(i != listSize){
            	list.add(alist);
        	}
        }catch(Exception e){
        	logger.error(e.getMessage(),e);
        }finally {
        	try {
    			if(is!=null){
    				is.close();
    				is = null;
    			}
    			if(isr!=null){
    				isr.close();
    				isr = null;
    			}
    			if(br!=null){
    				br.close();
    				br = null;
    			}
    			disconnect();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
        return list;
    }
    
    /**
     * 检查FTP文件是否存在
     * param ftpFileName 服务器文件
     * throws IOException 异常
     */
    public boolean checkFile(String ftpFileName){
        try {
        	
            // Get file info.
            FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
            if (fileInfoArray.length<=0) {
            	return false;
            }else{
                return true;
            }
           
        }catch (IOException e) {
			logger.error(e.getMessage(),e);
			return false;
		}
        
    }
}



//~ Formatted by Jindent --- http://www.jindent.com
