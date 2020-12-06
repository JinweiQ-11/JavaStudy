package com.qjw.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 */
public class TalkReceive implements  Runnable {
    private DatagramSocket server;
    String from;
    public TalkReceive(int port,String from) {
        this.from = from;
        try {
            server = new DatagramSocket(port);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(true) {
            byte[] con = new byte[1024 * 60];
            DatagramPacket packet = new DatagramPacket(con, 0, con.length);
            //阻塞式接受包裹
            try {
                server.receive(packet);
                byte[] datas = packet.getData();
                int len = packet.getLength();
                String data = new String(datas, 0, len);
                System.out.println(from+":"+data);
                if (data.equals("bye")) {
                    break;
                }
                }catch (IOException e) {
                    e.printStackTrace();
            }
        }
        server.close();
    }
}
