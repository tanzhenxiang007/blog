package com.blog.玩玩.udp学习.组播;

import java.net.*;

/**
 * 服务端
 * 使用组播地址: 224.0.0.0~239.255.255.255
 * 发送端的数据包的目的目的地址是组播ip(224.0.1.1,9999)
 * 接收端必须绑定该组播ip(224.0.1.1),端口还要对应发送端的目的端口(9999)
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception {
        // 输出启动信息
        System.out.println("=============客户端启动===========");

        // 1. 创建接收对象
        // 创建一个MulticastSocket对象，用于接收组播消息，并绑定到9999端口
        MulticastSocket socket = new MulticastSocket(9999);

        // 将当前接收端加入到一个组播组中，绑定对应的组播消息组IP
        // 这里使用新的方法并指定网络接口
        // 创建一个组播地址和端口号的InetSocketAddress对象
        InetSocketAddress groupAddress = new InetSocketAddress(InetAddress.getByName("224.0.1.1"), 9999);
        // 获取本地主机的网络接口
        NetworkInterface localNetworkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        // 将socket加入组播组，并指定网络接口
        socket.joinGroup(groupAddress, localNetworkInterface);

        //        socket.joinGroup(InetAddress.getByName("224.0.1.1")); 过时的方法

        // 2. 创建一个数据包用于接收数据
        // 创建一个字节数组作为接收缓冲区
        byte[] buffer = new byte[1024];
        // 创建一个DatagramPacket对象，用于接收数据
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // 进入一个无限循环，持续接收组播消息
        while (true) {
            // 3. 等待接收数据
            // 调用socket的receive方法，等待接收组播数据，数据将被填充到之前创建的packet对象中
            socket.receive(packet);

            // 4. 取出数据
            // 获取实际接收到的数据长度
            int len = packet.getLength();
            // 根据实际长度创建字符串对象，避免乱码
            String rs = new String(buffer, 0, len);
            // 输出接收到的组播消息信息，包括发送方的IP地址、端口号和消息内容
            System.out.println("收到来自: " + packet.getAddress() + ",对方端口号为: " + packet.getPort() + "消息: " + rs);
        }
    }
}


