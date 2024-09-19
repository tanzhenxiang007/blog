package com.blog.玩玩.udp学习.组播;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientDemo1 {
    /**
     * 发送端
     * 使用组播地址: 224.0.0.0~239.255.255.255
     * 发送端的数据包的目的目的地址是组播ip(224.0.1.1,9999)
     * 接收端必须绑定该组播ip(224.0.1.1),端口还要对应发送端的目的端口(9999)
     */
    public static void main(String[] args) throws Exception {
        System.out.println("=============发送端启动===========");
        // 1.创建发送端对象
        DatagramSocket socket = new DatagramSocket(6666);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入您要发送的消息: ");
            String msg = sc.nextLine();

            if("exit".equals(msg)){
                System.out.println("退出成功!");
                socket.close();
                break;
            }

            // 2.创建一个数据包对象封装数据
            byte[] buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length, InetAddress.getByName("224.0.1.1"),9999);

            // 3.发送数据
            socket.send(packet);
        }
    }
}

