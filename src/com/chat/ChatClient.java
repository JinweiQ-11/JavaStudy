package com.chat;
import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String args[]) throws IOException {
        System.out.println("客户端");
        Socket client = new Socket("localhost",8888);
        //客户端发送消息
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        DataOutputStream dos = new DataOutputStream(client.getOutputStream());//发送
//        DataInputStream dis = new DataInputStream(client.getInputStream());//接受
//        boolean isRunning = true;
//        while(isRunning) {
//            String msg = br.readLine();
//            dos.writeUTF(msg);
//            dos.flush();
//            msg = dis.readUTF();
//            System.out.println(msg);
//        }
//        dos.close();
////        dis.close();
//        client.close();
        //多线程

        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名:");
        String name = br.readLine();
        new Thread(new Send(client,name)).start();
        new Thread(new Receive(client)).start();
    }
}
