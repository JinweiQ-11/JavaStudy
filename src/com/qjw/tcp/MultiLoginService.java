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
public class MultiLoginService {
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        boolean isRuning = true;
        while(isRuning) {
            Socket client = server.accept();//阻塞式
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
        server.close();
    }
    static class Channel implements Runnable{
        private  Socket client;
        DataInputStream dis;
        DataOutputStream dos;
        public Channel( Socket client){
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }
        @Override
        public void run() {
            String name = "";
            String passwd = "";
            String[] dataArray = receive().split("&");
            for (String info : dataArray) {
                String[] userInfo = info.split("=");
                if (userInfo[0].equals("name")) {
                    System.out.println("用户名为:" + userInfo[1]);
                    name = userInfo[1];
                } else if (userInfo[0].equals("passwd")) {
                    System.out.println("密码为:" + userInfo[1]);
                    passwd = userInfo[1];
                }
                //System.out.println(userInfo[0]+"---->"+userInfo[1]);
            }
            if (name.equals("qjw") && passwd.equals("123")) {//登录成功
                send("登录成功");
            } else {//失败
                send("用户名或密码错误");
            }

            release();
        }
        private String receive(){//接受数据
            String datas  = "";
            try {
                datas = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return datas;
        }
        private void send(String msg){//接受数据
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //释放资源
        private void release(){
            try {
                if(null!=dos) {
                    dos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null!=dis) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null!=client) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
