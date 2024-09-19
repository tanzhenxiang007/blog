//package com.blog.sftp;
//
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//import java.util.Vector;
//import sun.net.ftp.FtpClient;
//import java.sql.Timestamp;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//import com.jcraft.jsch.ChannelSftp.LsEntry;
//
///**
// * @说明：系统连接SFTP的客户端类
// * @author WHT
// *
// */
//public class SftpClient {
//
//
//	public static String LOCK_FILE="ok";
//	public static String LOCK_MTS_FILE="Token.lock";
//	private String servip;
//	private String servport;
//	private String user;
//	private String password;
//	private String maindir;
//	private int timeout = 600000;
//	private Timestamp loginTime = new Timestamp(System.currentTimeMillis());
//	private FtpClient ftpclient = FtpClient.create();
//	private ChannelSftp channelsftp = null;
//
//	public SftpClient(String servip, String servport, String user,
//			String password, String maindir) {
//		this.servip = servip;
//		this.servport = servport;
//		this.user = user;
//		this.password = password;
//		this.maindir = maindir;
//	}
//
//	public int getTimeout() {
//		return timeout;
//	}
//
//	public void setTimeout(int timeout) {
//		this.timeout = timeout;
//	}
//
//	public Timestamp getLoginTime() {
//		return loginTime;
//	}
//
//	public void setLoginTime(Timestamp loginTime) {
//		this.loginTime = loginTime;
//	}
//
//	public String getServip() {
//		return servip;
//	}
//
//	public void setServip(String servip) {
//		this.servip = servip;
//	}
//
//	public String getServport() {
//		return servport;
//	}
//
//	public void setServport(String servport) {
//		this.servport = servport;
//	}
//
//	public String getUser() {
//		return user;
//	}
//
//	public void setUser(String user) {
//		this.user = user;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getMaindir() {
//		return maindir;
//	}
//
//	public void setMaindir(String maindir) {
//		this.maindir = maindir;
//	}
//
//	public FtpClient getFtpclient() {
//		return ftpclient;
//	}
//
//	public void setFtpclient(FtpClient ftpclient) {
//		this.ftpclient = ftpclient;
//	}
//
//	public ChannelSftp getChannelsftp() {
//		return channelsftp;
//	}
//
//	public void setChannelsftp(ChannelSftp channelsftp) {
//		this.channelsftp = channelsftp;
//	}
//
//
//
//	// 获取文件扩展名
//	public static String getExtension(String filename) {
//		if ((filename != null) && (filename.length() > 0)) {
//			int i = filename.lastIndexOf('.');
//			if ((i > 0) && (i < (filename.length() - 1))) {
//				return filename.substring(i + 1);
//			}
//		}
//		return "";
//	}
//
//	public File getTockenFile(String path) {
//		try {
//			File lockFile = new File(path + File.separator + LOCK_FILE);
//			if (lockFile.exists() && lockFile.isFile())
//				return lockFile;
//			lockFile.createNewFile();
//			OutputStream out = new FileOutputStream(lockFile);
//			PrintWriter write = new PrintWriter(out);// 写入一点数据，防止空文件无法被检测到！
//			write.println("This is Ebills declare send lock File!");
//			write.println("Don't delete it!");
//			write.println("Auth:PengZishun tel:15084847422");
//			write.flush();
//			write.close();
//			out.close();
//			return lockFile;
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	public void putFile(String remotePath, String localPath, String localFile,
//			boolean isDelFile) {
//		try {
//			this.changeDir(remotePath, "putFile");
//			System.out.println("上传:" + localFile);
//			// 本地的SEND目录
//			File file = new File(localPath + File.separator + localFile);
//			if(file.isFile()){
//				FileInputStream localFIS = new FileInputStream(file);
//				this.getChannelsftp().put(localFIS, file.getName());
//				localFIS.close();
//				System.out.println("上传文件[" + localPath +  localFile + "]成功!");
//				if (isDelFile) {
//					file.delete();
//				}
//			}else{
//				System.out.println( localFile + "不是文件，不能上传 " );
//			}
//		} catch (Exception e) {
//			System.out.println("上传文件[" + localPath + "]失败!" + e.getMessage());
//		}
//	}
//
//	public void putylFile(String remotePath, String localPath, String localFile,String dateStr,
//			boolean isDelFile) {
//		try {
//			this.changeDir(remotePath, "putFile");
//
//			try{
//				this.getChannelsftp().cd(dateStr);
//			}catch(Exception e){
//				System.out.println( dateStr + "日期目录不存在,重新创建日期目录 " );
//			   this.getChannelsftp().mkdir(dateStr);
//			   this.getChannelsftp().cd(dateStr);
//			}
//
//			System.out.println("上传:" + localFile);
//			// 本地的SEND目录
//			File file = new File(localPath + File.separator + localFile);
//			if(file.isFile()){
//				FileInputStream localFIS = new FileInputStream(file);
//				this.getChannelsftp().put(localFIS, file.getName());
//				localFIS.close();
//				System.out.println("上传文件[" + localPath +  localFile + "]成功!");
//				if (isDelFile) {
//					file.delete();
//				}
//			}else{
//				System.out.println( localFile + "不是文件，不能上传 " );
//			}
//		} catch (Exception e) {
//			System.out.println("上传文件[" + localPath + "]失败!" + e.getMessage());
//		}
//	}
//
//
//	public boolean getFile(String remotePath, String remoteFile, String localPath,
//			boolean isDelFile) throws Exception {
//		try {
//			this.changeDir(remotePath, "getFile");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			Date currentTime = new Date(System.currentTimeMillis());
//			String dateStr = sdf.format(currentTime);
//			File dir = new File(localPath);
//			//下载的文件不需要加路径20180502
////			File dir = new File(localPath + File.separator + dateStr);
////			if (!dir.exists()) {
////				dir.mkdir();
////			}
//			File file = new File(dir + File.separator + remoteFile);
//			FileOutputStream localFOS = new FileOutputStream(file);
//			//if(getFilesLock("./",remoteFile)){
//			if(getFilesLock(remotePath,remoteFile)){
//				this.getChannelsftp().get(remoteFile, localFOS);
//				localFOS.close();
//				System.out.println("下载文件[" + remoteFile + "]成功!");
//				return true;
//			}else{
//				System.out.println("下载的文件不存在");
//			}
//			if (isDelFile) {
//				this.deleteFile(remotePath, remoteFile);
//			}
//
//		} catch (Exception e) {
//			System.out.println("下载文件[" + remoteFile + "]失败!" + e.getMessage());
//		}
//		return false;
//	}
//
//
//	public void deleteFile(String remotePath, String remoteFile) {
//		try {
//			this.changeDir(remotePath, "deleteFile");
//			this.getChannelsftp().rm(remoteFile);
//			System.out.println("删除文件[" + remoteFile + "]成功!");
//		} catch (Exception e) {
//			System.out.println("删除文件[" + remoteFile + "]失败!" + e.getMessage());
//		}
//	}
//
//	public boolean connect() {
//		try {
//			JSch jsch = new JSch();
//			System.out.println("[SFTP]getServip:" + this.getServip());
//			System.out.println("[SFTP]getServport:" + this.getServport());
//			System.out.println("[SFTP]getUser:" + this.getUser());
//			System.out.println("[SFTP]getPassword:" + this.getPassword());
//			Session sshSession = jsch.getSession(this.getUser(), this
//					.getServip(), Integer.parseInt(this.getServport()));
//			System.out.println("Session created.");
//			sshSession.setPassword(this.getPassword());
//			Properties sshConfig = new Properties();
//			sshConfig.put("StrictHostKeyChecking", "no");
//			sshSession.setConfig(sshConfig);
//			sshSession.connect();
//			System.out.println("Session connected.");
//			System.out.println("Opening Channel.");
//			Channel channel = sshSession.openChannel("sftp");
//			channel.connect();
//			this.setChannelsftp((ChannelSftp) channel);
//			System.out.println("Connected to " + this.getServip() + ".");
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	public boolean disconnect() {
//		try {
//			this.getChannelsftp().getSession().disconnect();
//			this.getChannelsftp().disconnect();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//	public boolean isConnected() {
//		return this.getChannelsftp().isConnected();
//	}
//
//	public void changeDir(String remotPath, String descript) {
//		try {
//			System.out.println("[" + descript + "]当前工作目录:"
//					+ this.getChannelsftp().pwd());
//			try {
//				System.out.println("切换目录:" + remotPath);
//				this.getChannelsftp().cd(remotPath);
//			} catch (Exception e) {
//				try {
////					this.getChannelsftp().cd(
////							DataUtil.cffield.getSendPath());
//					this.getChannelsftp().cd(remotPath);
//				} catch (Exception e2) {
//					System.out.println("切换目录:" + remotPath + "失败!"
//							+ e.getMessage());
//				}
//			}
//			System.out.println("[" + descript + "]当前工作目录:"
//					+ this.getChannelsftp().pwd());
//		} catch (Exception e) {
//			//
//		}
//	}
//
//	public boolean isExist(String path, String name) {
//		try {
//			this.changeDir(path, "isExies");
//			Vector v = this.getChannelsftp().ls(name);
//			if (v != null) {
//				Iterator it = v.iterator();
//				while (it.hasNext()) {
//					LsEntry lsEntry = (LsEntry) it.next();
//					System.out.println("lsEntry.getFilename():::"
//							+ lsEntry.getFilename());
//					if (lsEntry.getFilename().equals(name)) {
//						return true;
//					}
//				}
//			}
//			return false;
//		} catch (Exception e) {
//			return false;
//		}
//
//	}
//
//
//	public boolean getFilesLock(String logpath, String logname) throws SftpException {
//		System.out.println(":::::::::::::::"+logpath+"| 判断条件：" + logname );
//	    boolean flag = false;
//	    System.out.println(this.getChannelsftp().pwd());
//		Vector v = this.getChannelsftp().ls(logpath);
//		if (v != null) {
//			Iterator it = v.iterator();
//			while (it.hasNext()) {
//				LsEntry lsEntry = (LsEntry) it.next();
//				System.out.println("测试取得文件的判断条件文件是否可以："+lsEntry.getFilename()+"| 判断条件：" + logname );
//		        if (lsEntry.getFilename().equals(logname)){
//		        	flag =  true;
//		        }
//			}
//		}
//
//	return flag;
//	}
//
//	/*
//	public boolean getFilesLock(String path, String name) {
//		try {
//			this.changeDir(path, "GetFilesLock");
//			Vector v = this.getChannelsftp().ls(name);
//			if (v != null) {
//				Iterator it = v.iterator();
//				while (it.hasNext()) {
//					LsEntry lsEntry = (LsEntry) it.next();
//					System.out.println("lsEntry.getFilename():::"
//							+ lsEntry.getFilename());
//					if (getExtension(lsEntry.getFilename()).equals(name)) {
//						return true;
//					}
//				}
//			}
//			return false;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//*/
//
//
//	public List getFileNames(String remotePath) throws Exception {
//		List fileNames = new ArrayList();
//		Vector v = this.getChannelsftp().ls(remotePath);
//		if (v != null) {
//			Iterator it = v.iterator();
//			while (it.hasNext()) {
//				LsEntry lsEntry = (LsEntry) it.next();
//				if(!lsEntry.getFilename().equals(".") && !lsEntry.getFilename().equals("..") && (lsEntry.getFilename().indexOf(".") != -1)){
//					fileNames.add(lsEntry.getFilename());
//				}
//			}
//		}
//		return fileNames;
//	}
//	public  boolean getFxsOk(String remotePath) throws Exception {
//		List fileNames = new ArrayList();
//		Vector v = this.getChannelsftp().ls(remotePath);
//		boolean fxs=false;
//		if (v != null) {
//			Iterator it = v.iterator();
//			while (it.hasNext()) {
//				LsEntry lsEntry = (LsEntry) it.next();
//				if(!lsEntry.getFilename().equals(".") && !lsEntry.getFilename().equals("..") && (lsEntry.getFilename().indexOf(".") != -1)){
//					fxs=true;
//				}
//			}
//		}
//		return fxs;
//	}
//
//
//
//
//
//	 /** 判断接口目录下是否有令牌环文件* */
//		public static boolean GetFilesLock(ChannelSftp ftp, String fileDest)
//				throws Exception {
//			ftp.cd(fileDest);
//			// FTPFile[] getfiles = ftp.listFiles();
//			boolean flag = false;
//			Vector v = ftp.ls(fileDest);
//			if (v != null) {
//				Iterator it = v.iterator();
//				while (it.hasNext()) {
//					LsEntry lsEntry = (LsEntry) it.next();
//					if (lsEntry.getFilename().equals(LOCK_MTS_FILE)) {
//						flag = true;
//					}
//				}
//			}
//
//			System.out.println("TokenFlag:" + flag);
//			return flag;
//		}
//
//		/** 上传完后删除令牌环文件* */
//		public static boolean DeleteFilesLock(ChannelSftp ftp, String fileDest)
//				throws Exception {
//			boolean flag = false;
//			ftp.cd(fileDest);
//			Vector v = ftp.ls(fileDest);
//			System.out.println("FTP当前工作目录：" + ftp.pwd());
//			if (v != null) {
//				Iterator it = v.iterator();
//				while (it.hasNext()) {
//					LsEntry lsEntry = (LsEntry) it.next();
//					if (lsEntry.getFilename().equals(LOCK_MTS_FILE)) {
//						ftp.rm(LOCK_MTS_FILE);
//					}
//				}
//			}
//			return flag;
//		}
//
//		public static boolean copyFile(File source, File dest) throws Exception {
//			try {
//				if (!source.exists())
//					throw new Exception("源文件不存在");
//				if (!source.isFile())
//					throw new Exception("源文件是目录");
//
//				if (dest.exists())
//					//throw new ApplicationException("目标文件已存在");
//				    dest.delete();
//				if (!dest.createNewFile())
//					throw new Exception("不能建立文件:" + dest);
//			} catch (Exception e) {
//				throw new Exception(e.getMessage(), e);
//			}
//			try {
//				FileInputStream sourceStream = new FileInputStream(source);
//				FileOutputStream destStream = new FileOutputStream(dest);
//				byte[] buf = new byte[1024];
//				int len = 0;
//				len = sourceStream.read(buf);
//				while (len > 0) {
//					destStream.write(buf, 0, len);
//					len = sourceStream.read(buf);
//				}
//				sourceStream.close();
//				destStream.flush();
//				destStream.close();
//			} catch (Exception e) {
//				throw new Exception(e);
//			}
//			return true;
//		}
//
//		public static File getMTSTockenFile(String path) throws Exception {
//			try {
//				File lockFile = new File(path + File.separator + LOCK_MTS_FILE);
//				if (lockFile.exists() && lockFile.isFile())
//					return lockFile;
//				lockFile.createNewFile();
//				OutputStream out = new FileOutputStream(lockFile);
//				PrintWriter write = new PrintWriter(out);// 写入一点数据，防止空文件无法被检测到！
//				write.println("This is Ebills declare send lock File!");
//				write.println("Don't delete it!");
//				write.println("Auth:PengZishun tel:15084847422");
//				write.flush();
//				write.close();
//				out.close();
//				return lockFile;
//			} catch (Exception e) {
//				return null;
//			}
//		}
//}