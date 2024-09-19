package com.blog.玩玩.udp学习.广播消息;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务端
 * 发送广播消息需要使用广播地址: 255.255.255.255
 * 发送端的数据包的目的地址是广播地址+指定端口号(255.255.255.255,9999)
 * 本机所在网段的其他主机只要匹配到端口即可接受消息(9999)
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception {
        System.out.println("=============客户端启动===========");

        // 1.创建接受对象
        DatagramSocket socket = new DatagramSocket(9999);

        // 2.创建一个数据包接收数据
        byte [] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {

            // 3.等待接受数据
            socket.receive(packet);

            // 4.取出数据
            int len = packet.getLength();
            String rs = new String(buffer,0,len);
            System.out.println("收到来自: "+ packet.getAddress()+ ",对方端口号为: "+ packet.getPort()+"的消息: " + rs);
        }

    }
}


