package com.blog.玩玩.udp学习.广播消息;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientDemo1 {
    /**
     * 发送端
     * * 发送广播消息需要使用广播地址: 255.255.255.255
     * * 发送端的数据包的目的地址是广播地址+指定端口号(255.255.255.255,9999)
     * * 本机所在网段的其他主机只要匹配到端口即可接受消息(9999)
     */
    public static void main(String[] args) throws Exception {
        // 输出启动信息
        System.out.println("=============发送端启动===========");

// 1. 创建发送端对象
// 创建一个DatagramSocket对象，用于发送UDP数据包，并监听6666端口
        DatagramSocket socket = new DatagramSocket(6666);

// 创建一个Scanner对象，用于从控制台读取用户输入
        Scanner sc = new Scanner(System.in);

// 进入一个无限循环，持续接收用户输入并发送数据
        while (true) {
            // 提示用户输入要发送的消息
            System.out.println("请输入您要发送消息: ");

            // 读取用户输入的消息
            String msg = sc.nextLine();

            // 如果用户输入的是"exit"，则退出循环并关闭socket
            if ("exit".equals(msg)) {
                System.out.println("退出成功!");
                socket.close();
                break;
            }

            // 2. 创建一个数据包对象封装数据
            // 将消息转换为字节数组
            byte[] buffer = msg.getBytes();

            // 创建一个DatagramPacket对象，封装要发送的数据和目标IP地址及端口号
            // 目标IP地址是广播地址255.255.255.255，端口号是9999
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), 9999);

            // 3. 发送数据
            // 通过socket对象发送封装好的数据包
            socket.send(packet);

    }
}
}
