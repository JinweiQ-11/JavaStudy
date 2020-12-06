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
public class LoginClient {
    public static void main(String args[]) throws IOException {
        System.out.println("客户端");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String username= br.readLine();
        System.out.println("请输入密码：");
        String passwd = br.readLine();

        Socket client = new Socket("localhost",8888);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("name="+username+"&"+"passwd="+passwd);
        dos.flush();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        String result = dis.readUTF();
        System.out.println(result);
        dos.close();
        client.close();
    }
}
