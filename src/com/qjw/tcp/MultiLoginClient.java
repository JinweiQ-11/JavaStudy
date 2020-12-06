package com.qjw.tcp;
import java.io.*;
import java.net.Socket;
/**
 * 创建客服端
 * socket创建客户端指定端口 建立连接
 * 输入输出流操作
 * 释放资源
 *
 */
public class MultiLoginClient {
    public static void main(String args[]) throws IOException {
        System.out.println("准备连接客户端");
        Socket client = new Socket("localhost",8888);
        new Send(client).send();
        //DataOutputStream dos = new DataOutputStream(client.getOutputStream());
//        dos.writeUTF("name="+username+"&"+"passwd="+passwd);
//        dos.flush();
//        DataInputStream dis = new DataInputStream(client.getInputStream());
//        String result = dis.readUTF();
//        System.out.println(result);
//        dos.close();
        new Receive(client).receive();
        client.close();
    }

    static class Send{
        private String msg;
        private DataOutputStream dos;
        private Socket client;
        private BufferedReader br;
        public Send(Socket client){
            this.client = client;
            br = new BufferedReader(new InputStreamReader(System.in));
            this.msg = init();
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public  void send(){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        private String init(){
            try {
                System.out.println("请输入用户名：");
                String username= br.readLine();
                System.out.println("请输入密码：");
                String passwd = br.readLine();
                return "name="+username+"&"+"passwd="+passwd;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
    static  class Receive{
        private Socket client;
        private  DataInputStream dis;
        public  Receive(Socket client){
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void receive(){
            String result = "";
            try {
                result = dis.readUTF();
                System.out.println(result);
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
