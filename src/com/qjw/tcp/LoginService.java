package com.qjw.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class LoginService {
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();//阻塞式
        System.out.println("建立连接");
        String name = "";
        String passwd = "";
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String data = dis.readUTF();
        String[] dataArray = data.split("&");
        for(String info:dataArray){
            String[] userInfo = info.split("=");
            if(userInfo[0].equals("name")){
                System.out.println("用户名为:"+userInfo[1]);
                name = userInfo[1];
            }else if(userInfo[0].equals("passwd")){
                System.out.println("密码为:"+userInfo[1]);
                passwd = userInfo[1];
            }
            //System.out.println(userInfo[0]+"---->"+userInfo[1]);
        }
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        if(name.equals("qjw")&&passwd.equals("123")){//登录成功
            dos.writeUTF("登录成功");
        }else{//失败
            dos.writeUTF("用户名或密码错误");
        }
        dos.flush();
        dis.close();
        client.close();
    }
}
