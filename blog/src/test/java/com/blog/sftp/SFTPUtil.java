//package com.blog.sftp;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//import java.util.Vector;
//
//import org.apache.log4j.Logger;
//import org.easitline.common.core.log.LogEngine;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.ChannelSftp.LsEntry;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//
//public class SFTPUtil {
//	private static Logger logger =  LogEngine.getLogger("cc-ocmx","cc-ocmx-SysCallTaskService");
//
//	public static ChannelSftp channelSftp;
//
//	private  Session sshSession;
//
//	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//	public static SFTPUtil getFTPUtilsSFTP(String host,String username,String password,int port) {
//		return new SFTPUtil(host,username,password,port);
//	}
//
//	private SFTPUtil(String host,String username,String password,int port) {
//		 try {
//			 JSch jsch = new JSch();
//		     jsch.getSession(username, host, port);
//		     sshSession = jsch.getSession(username, host);
//		     sshSession.setPassword(password);
//		     Properties sshConfig = new Properties();
//		     sshConfig.put("StrictHostKeyChecking", "no");
//		     sshSession.setConfig(sshConfig);
//		     sshSession.connect();
//		     Channel channel = sshSession.openChannel("sftp");
//		     channel.connect();
//		     channelSftp = (ChannelSftp) channel;
//		 }catch (Exception e) {
//			 logger.error("[ func:FTPUtilSFTP ] sftp连接服务器失败："+host+" "+username+" "+port,e);
//		 }
//	}
//
//	public boolean upload(String direcotry,String uploadFile) {
//		try {
//			isConnect();
//			channelSftp.cd(direcotry);
//			File file  = new File(uploadFile);
//			channelSftp.put(new FileInputStream(file+File.separator+file.getName()), file.getName());
//		} catch (SftpException e) {
//			 logger.error("[ func:upload ] 远程目录不存在："+direcotry,e);
//			 return false;
//		} catch (FileNotFoundException e) {
//			 logger.error("[ func:upload ] 文件上传失败："+uploadFile,e);
//		} catch (JSchException e) {
//			logger.error("[ func:upload ] 文件上传失败："+uploadFile,e);
//		}
//		return true;
//	}
//
//	private void isConnect() throws JSchException {
//		if(channelSftp != null && channelSftp.isClosed())
//			channelSftp.connect();
//	}
//
//	public boolean download(String direcotry,String downloadFile,String saveFilePath) {
//		FileOutputStream fileOutputStream = null;
//		try {
//			isConnect();
//			channelSftp.cd(direcotry);
//			File saveFile = new File(saveFilePath+File.separator+downloadFile);
//			if(!saveFile.exists()) {
//				saveFile.createNewFile();
//			}
//			fileOutputStream = new FileOutputStream(saveFile);
//			channelSftp.get(downloadFile,fileOutputStream);
//			return true;
//		} catch (SftpException e) {
//			logger.error("[ func:download ] 远程目录不存在："+direcotry,e);
//		} catch (FileNotFoundException e) {
//			 logger.error("[ func:upload ] 文件下载失败："+saveFilePath,e);
//		} catch (IOException e) {
//			 logger.error("[ func:upload ] 文件创建失败："+saveFilePath,e);
//		} catch (JSchException e) {
//			 logger.error("[ func:upload ] 文件创建失败："+saveFilePath,e);
//		}finally {
//				try {
//					if(fileOutputStream != null)
//						fileOutputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//		return false;
//	}
//
//	public boolean delete(String direcotry,String delteFile) {
//		try {
//			isConnect();
//			channelSftp.cd(direcotry);
//			channelSftp.rm(delteFile);
//			return true;
//		} catch (SftpException e) {
//			logger.error("[ func:download ] 文件删除失败："+direcotry,e);
//		} catch (JSchException e) {
//			logger.error("[ func:download ] 文件删除失败："+direcotry,e);
//		}
//		return false;
//	}
//
//	 /**
//     * 列出目录下的文件
//     *
//     * @param directory 要列出的目录
//     * @param sftp
//     * @return
//     * @throws SftpException
//     */
//	@SuppressWarnings("unchecked")
//	public Vector<ChannelSftp.LsEntry> listFiles(String direcotry) {
//		Vector<ChannelSftp.LsEntry> fileNames = new Vector<>();
//		try {
//			isConnect();
//			fileNames = channelSftp.ls(direcotry);
//		} catch (SftpException e) {
//			e.printStackTrace();
//		} catch (JSchException e) {
//			e.printStackTrace();
//		}
//        return fileNames;
//    }
//
//	public boolean downloadFile(String direcotry,String downloadPath) {
//		int i = 0;
//		int sumFileCount = 0;
//		FileOutputStream fileOutputStream = null;
//		try {
//			isConnect();
//			channelSftp.cd(direcotry);
//			Vector<ChannelSftp.LsEntry> fileNames = listFiles(direcotry);
//			sumFileCount = fileNames.size();
//			if(sumFileCount <= 0) {
//				logger.info("[ func:downloadFile ]暂无文件需要同步，时间："+sf.format(new Date()));
//				return false;
//			}
//			for(LsEntry fileNameLE : fileNames) {
//				String fileName = fileNameLE.getFilename();
//				if(!fileNameLE.getAttrs().isDir()) {//判断是否为文件
//					File saveFile = new File(downloadPath+File.separator+fileName);
//					if(saveFile.exists()) {
//						continue;
//					}
//					saveFile.createNewFile();
//					fileOutputStream = new FileOutputStream(saveFile);
//					channelSftp.get(fileName,fileOutputStream);
////					channelSftp.rm(fileName);
//					i++;
//				}
//			}
//			logger.info("[ func:downloadFile ] 文件夹总文件数："+sumFileCount +" 同步成功文件数："+i);
//			if(i <= 0 ) return false;
//			return true;
//		} catch (SftpException e) {
////			e.printStackTrace();
//			logger.error("[ func:downloadFile ] 远程目录不存在："+direcotry,e);
//		} catch (FileNotFoundException e) {
//			logger.error("[ func:downloadFile ] 文件同步失败："+direcotry,e);
//		} catch (IOException e) {
//			logger.error("[ func:downloadFile ] 文件创建失败："+direcotry,e);
//		} catch (JSchException e) {
//			logger.error("[ func:downloadFile ] 重新连接失败：",e);
//		}finally {
//				try {
//					if(fileOutputStream != null)
//						fileOutputStream.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//
//		return false;
//	}
//
//	public void closeConnect() {
//		if(channelSftp != null)
//			channelSftp.disconnect();
//		if(sshSession != null)
//			sshSession.disconnect();
//	}
//}
