package com.qjw.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 创建服务器
 * 指定端口 使用ServerSocket创建服务器
 * 阻塞式等待连接accept
 * 操作 输入输出流操作
 * 释放资源
 *
 */
public class FileService {
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();//阻塞式
        System.out.println("建立连接");
        //DataInputStream dis = new DataInputStream(client.getInputStream());
        InputStream is = new BufferedInputStream(client.getInputStream());
        //DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("src/tcp.jpg"));
        byte[] flush = new byte[1024];
        int len = -1;
        while((len=is.read(flush))!=-1){
            os.write(flush,0,len);
        }
        os.flush();
        is.close();
        client.close();
        server.close();
    }
}
