package com.blog.sftp.wangshang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;

@Slf4j
public class FtpClient {

    /**
     * FTP服务地址
     */
    private static String FTP_IP;
    /**
     * FTP端口号
     */
    private static Integer FTP_PORT;
    /**
     * FTP用户名
     */
    private static String FTP_USER;
    /**
     * FTP密码
     */
    private static String FTP_PASSWORD;
    /**
     * FTP根路径
     */
    private static String FTP_PATH;
    /**
     * 映射盘符
     */
    private static String FTP_DRIVELETTER;
    private static FTPClient ftpClient;

    static {
        try {
            // 根据该类的class文件获取到yaml文件
            Yaml yaml = new Yaml();
            URL resource = FtpClient.class.getClassLoader().getResource("application-dev.yml");
            assert resource != null;
            // 把yaml文件加载到对象中
            Object obj = yaml.load(new FileInputStream(resource.getFile()));
            // 解析对象中的属性并赋值
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
            JSONObject ftp = jsonObject.getObject("fanhai", JSONObject.class).getObject("ftp", JSONObject.class);
            FTP_IP = String.valueOf(ftp.get("ip"));
            FTP_PORT = Integer.valueOf(String.valueOf(ftp.get("port")));
            FTP_USER = String.valueOf(ftp.get("username"));
            FTP_PASSWORD = String.valueOf(ftp.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FTPClient getFtpConnection() {
        ftpClient = new FTPClient();
        try {
            //连接
            ftpClient.connect(FtpClient.FTP_IP, FtpClient.FTP_PORT);
            //设置编码
            ftpClient.setControlEncoding("UTF-8");
            //设置传输文件类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //登录
            ftpClient.login(FtpClient.FTP_USER, FtpClient.FTP_PASSWORD);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info("==============未连接到FTP，用户名或密码错误=================");
                //拒绝连接
                ftpClient.disconnect();
            } else {
                log.info("==============连接到FTP成功=================");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            log.info("==============FTP的IP地址错误==============");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("==============FTP的端口错误==============");
        }
        return ftpClient;
    }

    public static void closeConnect() {
        log.warn("关闭ftp服务器");
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
