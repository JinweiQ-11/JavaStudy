package com.qjw.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * 创建客服端
 * socket创建客户端指定端口 建立连接
 * 输入输出流操作
 * 释放资源
 *
 */
public class TcpTest01Client {
    public static void main(String args[]) throws IOException {
        System.out.println("客户端");
        Socket client = new Socket("localhost",8888);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String data = "hello";
        dos.writeUTF(data);
        dos.flush();
        dos.close();
        client.close();
    }
}
