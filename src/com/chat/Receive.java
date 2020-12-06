package com.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream  dis;

    private Socket client;
    private  boolean isRunning;
    public Receive(Socket client){
        this.client = client;
        try {
            dis = new DataInputStream(client.getInputStream());//接受
            isRunning =true;
        } catch (IOException e) {
            release();
            e.printStackTrace();
        }
    }
    private void release(){
        this.isRunning = false;
        Utils.close(dis,client);
    }

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
    @Override
    public void run() {
        while(isRunning){
            String msg = receive();

                System.out.println(msg);

        }
    }
}
