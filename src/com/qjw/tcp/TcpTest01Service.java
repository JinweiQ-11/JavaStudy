package com.qjw.tcp;

import javax.xml.validation.SchemaFactoryConfigurationError;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * 创建服务器
 * 指定端口 使用ServerSocket创建服务器
 * 阻塞式等待连接accept
 * 操作 输入输出流操作
 * 释放资源
 *
 */
public class TcpTest01Service {
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();//阻塞式
        System.out.println("建立连接");
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String data = dis.readUTF();
        System.out.println(data);
        dis.close();
        client.close();
        server.close();
    }
}
