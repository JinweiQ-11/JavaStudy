package com.qjw.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 * 1  DatagramSocket 指定端口 创建接收端
 * 2 准备容器 封装成DatagramPacket包裹
 * 3 阻塞式接受包裹 receive(DatagramPacket p)
 * 分析数据
 * 释放资源
 */
public class Test01 {
    public static void main(String args[]) throws IOException {
        DatagramSocket server  = new DatagramSocket(9999);
        byte[] con = new byte[1024*60];
        DatagramPacket packet = new DatagramPacket(con,0,con.length);
        //阻塞式接受包裹
        server.receive(packet);
        byte[] datas = packet.getData();
        System.out.println(new String(datas,0,datas.length));
        server.close();
    }
}
