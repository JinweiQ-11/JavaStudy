package com.qjw.internet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


/**
 * 发送端
 * 1  DatagramSocket 指定端口 创建发送端
 * 2 准备数据 将基本数据转为 字节数组
 * 3封装成DatagramPacket包裹 指定目的地
 * 4 发送包裹 send(DatagramPacket p)
 *
 * 释放资源
 */
public class UdpTypeC {
    public static void main(String args[]) throws IOException {
        System.out.println("发送启动中");
        DatagramSocket client  = new DatagramSocket(8888);
//        String data = "我很开心";
//        byte[] datas = data.getBytes();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream bos = new DataOutputStream(new BufferedOutputStream(baos));
        bos.writeUTF("我爱自己");
        bos.writeInt(18);
        bos.flush();
        byte[] datas = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
                new InetSocketAddress("localhost",9999));

        client.send(packet);
        client.close();
    }
}
