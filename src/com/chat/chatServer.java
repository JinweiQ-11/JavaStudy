package com.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class chatServer {

    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while(true) {
            Socket client = server.accept();//阻塞式
            System.out.println("建立连接");
//            new Thread(() -> {
////                DataInputStream dis = null;
////                DataOutputStream dos = null;
////                try {
////                    dis = new DataInputStream(client.getInputStream());
////                    dos = new DataOutputStream(client.getOutputStream());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
//                boolean isRunning = true;
//                while (isRunning) {
//                    String msg = null;
//                    try {
//                        msg = dis.readUTF();
//                        dos.writeUTF(msg);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    // System.out.println(data);
//                    //返回
//
//                    try {
//                        if (null == dos) {
//                            dos.flush();
//                        }
//                        if (null == dis) {
//                            dis.close();
//                        }
//                        if (null == dos) {
//                            dos.close();
//                        }
//                        if (null == client) {
//                            client.close();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        isRunning = false;
//                    }
//                }
//            }).start();
            //接受
            new Thread(new Channel(client)).start();
        }
    }
    static class Channel implements Runnable{
        private  DataInputStream dis = null;
        private DataOutputStream dos = null;
        private Socket client;
        private boolean isRunning = false;
        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        //接受消息
        private  String receive(){
            String msg  = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
            return msg;
        }
        //发送消息
        private void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }

        }
        private void release(){
            this.isRunning = false;
            Utils.close(dis,dos,client);
        }
        @Override
        public void run() {
            while (isRunning){
                String msg = receive();
                if(!msg.equals("")){
                    send(msg);
                }
            }
        }
    }

}
