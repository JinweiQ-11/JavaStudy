package com.qjw.tcp;

import java.io.*;
import java.net.Socket;

/**
 *
 * 创建客服端
 * socket创建客户端指定端口 建立连接
 * 输入输出流操作
 * 释放资源
 *
 */
public class FileClient {
    public static void main(String args[]) throws IOException {
        System.out.println("客户端");
        Socket client = new Socket("localhost",8888);

        //读取文件
        InputStream is = new BufferedInputStream(new FileInputStream("src/timg.jpg"));
        //DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        OutputStream os = new BufferedOutputStream((client.getOutputStream()));
        byte[] flush = new byte[1024];
        int len = -1;
        while((len=is.read(flush))!=-1){
            os.write(flush,0,len);
        }
        os.flush();
        is.close();
        client.close();

    }
}
