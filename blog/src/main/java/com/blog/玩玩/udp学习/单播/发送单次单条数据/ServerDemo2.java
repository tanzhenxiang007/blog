package com.blog.玩玩.udp学习.单播.发送单次单条数据;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 接收端
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception {
        System.out.println("=============客户端启动===========");

        // 1.创建接受对象
        DatagramSocket socket = new DatagramSocket(8888);

        // 2.创建一个数据包接收数据
        byte [] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // 3.等待接受数据
        socket.receive(packet);


        // 4.取出数据
        int len = packet.getLength();
        String rs = new String(buffer,0,len);
        System.out.println("收到的数据:" + rs);
        // 获取发送端的ip和端口
        String ip = packet.getSocketAddress().toString();
        System.out.println("发送端的IP地址: " + ip);

        int port = packet.getPort();
        System.out.println("发送端端口为: "+port);

        // 5.发送数据
        buffer = "我已经接收到你的消息了".getBytes();
        DatagramPacket packet2 = new DatagramPacket(buffer,buffer.length, InetAddress.getLocalHost(),6666);

        socket.send(packet2);
        // 关闭管道
        socket.close();
    }
}

