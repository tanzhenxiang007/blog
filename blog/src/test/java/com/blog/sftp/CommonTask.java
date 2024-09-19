//package com.blog.sftp;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.sql.SQLException;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Vector;
//import java.util.Map.Entry;
//
//import org.apache.log4j.Logger;
//import org.easitline.common.core.context.ServiceContext;
//import org.easitline.common.core.log.LogEngine;
//import org.easitline.common.core.service.IService;
//import org.easitline.common.db.EasyQuery;
//import org.easitline.common.db.EasyRecord;
//import org.easitline.common.db.EasySQL;
//import org.easitline.common.db.impl.JSONMapperImpl;
//import org.easitline.common.utils.calendar.EasyDate;
//import org.easitline.common.utils.string.StringUtils;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jcraft.jsch.ChannelSftp.LsEntry;
//import com.yunqu.cc.ocmx.base.Constants;
//import com.yunqu.cc.ocmx.base.EntContext;
//import com.yunqu.cc.ocmx.base.QueryFactory;
//import com.yunqu.cc.ocmx.base.TaskConstants;
//import com.yunqu.cc.ocmx.task.callUtil.JobCallUtil;
//import com.yunqu.cc.ocmx.utils.FtpFileInfo;
//import com.yunqu.cc.ocmx.utils.FtpUtil;
//import com.yunqu.cc.ocmx.utils.SFTPUtil;
//
//public class CommonTask implements Job{
//	private static Logger logger =  LogEngine.getLogger("cc-ocmx","cc-ocmx-job");
//
//	@Override
//	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		try {
//			Calendar calendar=Calendar.getInstance();
//
//			int month = calendar.get(Calendar.MONTH)+1;
//			int week = calendar.get(Calendar.DAY_OF_WEEK);//周日 1，周一 2 周六是7
//			int day = calendar.get(Calendar.DAY_OF_MONTH);
//			int hour = calendar.get(Calendar.HOUR_OF_DAY);
//			int minute = calendar.get(Calendar.MINUTE);
//
//			logger.info(
//					"job>>month:" + month
//					+ ",week:" + week
//					+ ",day:" + day
//					+ ",hour:" + hour
//					+ ",minute:" + minute);
//
//			logger.info("当前时间--->>>"+EasyDate.getCurrentDateString());
//
//
//			//起一个子线程检查数据并推送
//			JobCallUtil jobCallUtil = new JobCallUtil();
//			jobCallUtil.run();
//
//			//获取外呼任务
//			EasyQuery query = QueryFactory.getQuery(Constants.ENT_ID);
//			EntContext entContext = new EntContext(Constants.ENT_ID);
//			EasySQL sql = new EasySQL("SELECT t1.TASK_ID, "
//					+ "t1.FILE_NAME,t1.TASK_CAT, "
//					+ "t2.IP, "
//					+ "t2.PORT, "
//					+ "t2.USER_NAME, "
//					+ "t2.PWD, "
//					+ "t2.FILE_PATH "
//					+ " FROM " + entContext.getTableName("WRCB_CALL_TASK t1")
//					+ " LEFT JOIN " + entContext.getTableName("WRCB_CALL_SFTP t2"));
//			sql.append(" ON t1.FTP_ID = t2.ID ");
//			sql.append(" WHERE 1=1 ");
//			sql.append(" AND t1.ENABLE_STATUS='1' ");	//启用中
//			sql.append(" AND (t1.TASK_CAT='3' OR t1.TASK_CAT='4') ");		//ftp|sftp
//			sql.append(" AND t1.IS_DOWNLOAD='0' ");		//未下载
//
//			logger.info("SQL---->>>"+sql.toFullSql());
//			List<JSONObject> list = query.queryForList(sql.getSQL(), sql.getParams(), new JSONMapperImpl());
//
//			if (list==null || list.isEmpty()) {
//				logger.info("需要获取的外呼数据为空---->>>");
//				return;
//			}
//
//			//本地文件夹是否存在
//			File fileParent = new File(Constants.WRCB_LOCAL_PATH);
//			if(!fileParent.exists()){
//			    fileParent.mkdirs();
//			}
//			File filePath = new File(Constants.WRCB_LOCAL_PATH
//					+ File.separator
//					+ EasyDate.getCurrentDateString("yyyyMMdd"));
//
//			if(!filePath.exists()){
//				filePath.mkdirs();
//			}
//
//			//zeng：改成可配置多选ftp
//			HashMap<String, String> map = new HashMap<String, String>();
//			for (JSONObject json : list) {
//				String name = json.getString("FILE_NAME");
//				//找到文件
//				if (StringUtils.isNotBlank(name)) {
//					if (!StringUtils.isAllBlank(new String[] {json.getString("IP")
//							,json.getString("PORT")
//							,json.getString("USER_NAME")
//							,json.getString("PWD")})) {
//						//"4":SFTP链接
//						if ("4".equals(json.getString("TASK_CAT"))) {
//							logger.info("SFTP------>>>>>");
//							SFTPUtil sftp =  SFTPUtil.getFTPUtilsSFTP(json.getString("IP")
//									, json.getString("USER_NAME")
//									, json.getString("PWD")
//									, 22);
//							logger.info("FILE_PATH------>>>>>"+json.getString("FILE_PATH"));
//							Vector<LsEntry> listFiles = sftp.listFiles(json.getString("FILE_PATH"));
//							for (LsEntry lsEntry : listFiles) {
//								logger.info(lsEntry.getFilename().toString());
//								//找到文件
//								if (lsEntry.getFilename().equals(name)) {
//									logger.info("找到文件--->>>" + name);
//									boolean download = sftp.download(json.getString("FILE_PATH"),name,filePath+"");
//									if (download) {
//										logger.info("文件下载成功！--->>>" + filePath+"");
//										map.put(json.getString("TASK_ID"), filePath + File.separator + name);
//									}else {
//										logger.info("文件下载失败！--->>>" + filePath+"");
//									}
//								}
//							}
//							try {
//								SFTPUtil.channelSftp.exit();
//								if (SFTPUtil.channelSftp != null) {
//									if (SFTPUtil.channelSftp.isConnected()) {
//										SFTPUtil.channelSftp.disconnect();
//									} else if (SFTPUtil.channelSftp.isClosed()) {
//									}
//								}
//								if (SFTPUtil.channelSftp.getSession() != null) {
//									if (SFTPUtil.channelSftp.getSession().isConnected()) {
//										SFTPUtil.channelSftp.getSession().disconnect();
//									}
//								}
//
//							} catch (Exception e) {
//								logger.error("关闭SFTP剩余线程失败------>>>>>",e);
//							}
//
//						}else {
//							FtpUtil ftp = null;
//							logger.info("IP:" + json.getString("IP"));
//							logger.info("PORT:" + json.getString("PORT"));
//							logger.info("USER_NAME:" + json.getString("USER_NAME"));
//							logger.info("PWD:" + json.getString("PWD"));
//							try {
//								//获取服务器外呼文件列表
//								ftp = new FtpUtil(json.getString("IP")
//										,Integer.parseInt(json.getString("PORT"))
//										,json.getString("USER_NAME")
//										,json.getString("PWD"));
//								ftp.connect(true);
//								ftp.setWorkingDirectory(json.getString("FILE_PATH"));
//								if (ftp.checkFile(name)) {
//									logger.info("找到文件--->>>" + name);
//									ftp.download(name, new File(filePath + File.separator + name));
//									map.put(json.getString("TASK_ID"), filePath + File.separator + name);
//								}
//							} finally {
//								if (ftp!=null && ftp.isConnected()) {
//									ftp.disconnect();
//								}
//							}
//						}
//					}else {
//						logger.info("请检查任务ID为：" + json.getString("TASK_ID") + "的FTP配置是否正确...");
//						logger.info("IP:" + json.getString("IP"));
//						logger.info("PORT:" + json.getString("PORT"));
//						logger.info("USER_NAME:" + json.getString("USER_NAME"));
//					}
//
//				}
//			}
//
//			//修改任务状态
//			EasyRecord record = new EasyRecord(entContext.getTableName("WRCB_CALL_TASK"), "TASK_ID");
//			map.forEach((key, value) -> {
//				record.set("TASK_ID", key);
//				record.set("DOWNLOAD_PATH", value);
//				record.set("TASK_STATE", TaskConstants.AUTO_TASK_STATE_01);
//				record.set("IS_DOWNLOAD", 1);
//				try {
//					query.update(record);
//				} catch (SQLException e) {
//					logger.info("TASK_ID为：" + key + "的任务变更状态失败...");
//				}
//			});
//
//		}catch (Exception e) {
//			logger.error("==========调用获取智能外呼数据失败==========",e);
//		}
//	}
//
//}
