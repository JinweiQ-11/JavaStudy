package NIO;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServiceMuti {
    private static class ClientThread implements Runnable {				// 客户端线程类
        private Socket client = null; 								// 客户端Socket
        private Scanner scan = null;									// 输入流
        private PrintStream out = null;								// 输出流
        private boolean flag = true; 									// 循环标记
        public ClientThread(Socket client) throws Exception {			// 接收客户端Socket
            this.client = client;										// 保存Socket
            this.scan = new Scanner(client.getInputStream()); 			// 输入流
            this.scan.useDelimiter("\n"); 							// 设置分隔符
            this.out = new PrintStream(client.getOutputStream()); 		// 输出流
        }
        @Override
        public void run() {											// 线程执行
            while (this.flag) {										// Echo循环处理
                if (scan.hasNext()) { 								// 有数据发送
                    String val = scan.next().trim(); 					// 接收数据
                    if ("byebye".equalsIgnoreCase(val)) {				// 结束响应
                        out.println("ByeByeBye....");					// 回应信息
                        this.flag = false; 							// 结束循环
                    } else {
                        out.println("【ECHO】" + val);					// Echo信息
                    }
                }
            }
            try {
                scan.close();										// 关闭输入流
                out.close();											// 关闭输出流
                client.close();										// 关闭客户端
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999); 					// 设置服务监听端口
        System.out.println("等待客户端连接.............");				// 打印提示信息
        boolean flag = true ; 										// 循环标记
        while (flag) {
            Socket client = server.accept() ;
            System.out.println("hello");// 有客户端连接
            new Thread(new ClientThread(client)).start();
        }
        server.close();												// 关闭服务端
    }

}
