package NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioEchoClient {
    public static final String HOST = "localhost"; 					// 连接主机
    public static final int PORT = 9999; 								// 绑定端口
    public static void main(String[] args) throws Exception {
        SocketChannel clientChannel = SocketChannel.open(); 			// 获取客户端通道
        clientChannel.connect(new InetSocketAddress(HOST, PORT)); 		// 连接服务端
        ByteBuffer buffer = ByteBuffer.allocate(50); 					// 开辟缓存
        boolean flag = true;
        while (flag) { 												// 持续输入信息
            buffer.clear(); 											// 清空缓冲区
            String msg = InputUtils.getString("请输入要发送的信息：");	// 提示信息
            buffer.put(msg.getBytes());								// 数据保存在缓冲区
            buffer.flip(); 											// 重设缓冲区
            clientChannel.write(buffer);								// 发送消息
            buffer.clear();											// 清空缓冲区
            int readCount = clientChannel.read(buffer);				// 读取服务端回应
            buffer.flip(); 											// 重置缓冲区
            System.err.println(new String(buffer.array(), 0, readCount));
            if ("exit".equals(msg)) {									// 结束指令
                flag = false; 										// 结束循环
            }
        }
        clientChannel.close();										// 关闭通道
    }

}
