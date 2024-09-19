package com.blog.java操作Linux;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.*;
import java.util.Locale;

public class GetAllSubfolders {

    // 登录， host 远程主机IP, username 用户名 password 密码 端口默认为22
    private static Connection login(String host, String username, String password) throws IOException {
        Connection conn = new Connection(host);
        conn.connect();
        if (!conn.authenticateWithPassword(username, password)) {
            throw new RuntimeException("用户名或密码错误!");
        }
        return conn;
    }

    // 执行远程linux命令行
    public static String remoteExec(String cmd, String host, String username, String password) throws IOException {
        //登录，获取连接
        Connection conn = login(host, username, password);
        Session session = null;
        BufferedReader br = null;
        InputStream is = null;
        StringBuffer res = new StringBuffer();
        try {
            // 开启会话
            session = conn.openSession();
            // 执行命令
            session.execCommand(cmd, "UTF-8");
            // 处理输出内容
            is = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
        } finally {
            //关闭资源
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
                if (session != null) {
                    session.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

    /**
     * 执行本机linux命令行，与ganymed-ssh2依赖无关，JDK自带功能
     */
    public static String exec(String command) {
        String osName = System.getProperty("os.name");
        try {
            /*系统命令不支持的操作系统Windows XP, 2000 2003 7 8 9 10 11*/
            if (osName.toLowerCase(Locale.ROOT).indexOf("win") != -1) {
                throw new RuntimeException("不支持的操作系统:" + osName);
            }
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(command);

            LineNumberReader br = new LineNumberReader(
                    new InputStreamReader(
                            process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void test1() throws IOException {
        String s = remoteExec("mkdir /home/bili", "192.xxx.xxx.xxx", "root", "bikabika");
        System.out.println(s);
    }
}

