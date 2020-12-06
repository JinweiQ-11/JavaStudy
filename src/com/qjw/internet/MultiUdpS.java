package com.qjw.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端 多次
 * 1  DatagramSocket 指定端口 创建接收端
 * 2 准备容器 封装成DatagramPacket包裹
 * 3 阻塞式接受包裹 receive(DatagramPacket p)
 * 分析数据
 * 释放资源
 */
public class MultiUdpS {
    public static void main(String args[]) throws IOException {
        DatagramSocket server  = new DatagramSocket(9999);
        while(true) {
            byte[] con = new byte[1024 * 60];
            DatagramPacket packet = new DatagramPacket(con, 0, con.length);
            //阻塞式接受包裹
            server.receive(packet);
            byte[] datas = packet.getData();
            int len = packet.getLength();
            String out = new String(datas, 0,len);

            System.out.println(out);
            if(out.equals("bye")){
                break;
            }

        }
        server.close();
    }
}