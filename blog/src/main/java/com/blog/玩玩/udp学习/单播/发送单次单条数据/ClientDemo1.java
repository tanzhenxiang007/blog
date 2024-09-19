package com.blog.玩玩.udp学习.单播.发送单次单条数据;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientDemo1 {
    /**
     * 发送端
     */
    public static void main(String[] args) throws Exception {
        System.out.println("=============发送端启动===========");
        // 1.创建发送端对象
        DatagramSocket socket = new DatagramSocket(6666);

        // 2.创建一个数据包对象封装数据
        /**
         * 参数1:封装要发送的数据
         * 参数2:发送数据的大小
         * 参数3:服务端的IP地址
         * 参数4:服务端的端口
         *
         * InetAddress.getLocalHost() 获取本机IP地址
         */
        byte[] buffer = "我是一坨数据!".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length, InetAddress.getLocalHost(),8888);

        // 3.发送数据
        socket.send(packet);


        // 4.接收数据
        byte [] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        socket.receive(datagramPacket);
        int len = datagramPacket.getLength();
        String rs = new String(buf,0,len);
        System.out.println("收到的数据:" + rs);
        // 获取发送端的ip和端口
        String ip = packet.getSocketAddress().toString();
        System.out.println("发送端的IP地址: " + ip);

        int port = packet.getPort();
        System.out.println("发送端端口为: "+port);

        // 4.关闭管道
        socket.close();
    }
}

