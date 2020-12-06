package com.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements  Runnable{
    private  BufferedReader br;
    private  DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;
    public  Send(Socket client,String name){
        this.name = name;
        this.client = client;
        br  = new BufferedReader(new InputStreamReader(System.in));
        try {
            dos = new DataOutputStream(this.client.getOutputStream());//发送
            //发名字
            send(name);
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }
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
        Utils.close(dos,client);
    }
    @Override
    public void run() {
        while (isRunning){
            String msg = getMsg();
            if(!msg.equals("")){
                send(msg);
            }

        }
    }
    private  String getMsg(){
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
