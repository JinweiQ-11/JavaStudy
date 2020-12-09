package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOEchoClient {
    public static void main(String[] args) throws Exception {
        AIOClientThread client = new AIOClientThread();
        new Thread(client).start(); 								// 启动客户端线程
        while (client.sendMessge(InputUtils.getString("请输入要发送的消息："))) {
            ;
        }
    }

}


class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;									// 线程同步
    private AsynchronousSocketChannel clientChannel = null; 		// 客户端连接
    public ClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }
    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip(); 											// 重置缓冲区
        String receiveMessage = new String(buffer.array(),
                0, buffer.remaining()); 							// 读取返回内容
        System.err.println(receiveMessage); 						// 输出回应数据
    }
    @Override
    public void failed(Throwable exp, ByteBuffer buffer) {
        System.out.println("对不起，发送出现了问题，该客户端被关闭 ...");
        try {
            this.clientChannel.close();
        } catch (IOException e) {}
        this.latch.countDown(); 									// 接触阻塞状态
    }
}
class ClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private CountDownLatch latch;
    private AsynchronousSocketChannel clientChannel = null; 		// 客户端连接
    public ClientWriteHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }
    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) { 								// 有数据发送
            this.clientChannel.write(buffer, buffer, this);		// 数据发送
        } else { 												// 需要读取
            ByteBuffer readBuffer = ByteBuffer.allocate(100);		// 读取数据
            this.clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(
                    this.clientChannel, this.latch));				// 读取回调
        }
    }
    @Override
    public void failed(Throwable exp, ByteBuffer buffer) {
        System.out.println("对不起，发送出现了问题，该客户端被关闭 ...");
        try {
            this.clientChannel.close();
        } catch (IOException e) {}
        this.latch.countDown(); 									// 解除阻塞状态
    }
}
class AIOClientThread implements Runnable {						// 客户端线程类
    public static final String HOST = "localhost"; 				// 连接主机
    public static final int PORT = 9999; 							// 绑定端口
    private CountDownLatch latch;									// 线程锁定
    private AsynchronousSocketChannel clientChannel = null; 		// 客户端连接
    public AIOClientThread() throws Exception {
        this.clientChannel = AsynchronousSocketChannel.open(); 		// 客户端Channel
        this.clientChannel.connect(new InetSocketAddress(HOST, PORT)); // 进行客户端连接
        this.latch = new CountDownLatch(1); 						// 阻塞处理
    }
    @Override
    public void run() {
        try {
            this.latch.await(); 									// 等待处理
            this.clientChannel.close(); 							// 关闭客户端
        } catch (Exception e) {}
    }
    public boolean sendMessge(String msg) { 						// 实现消息发送
        ByteBuffer buffer = ByteBuffer.allocate(100); 				// 开辟缓冲区
        buffer.put(msg.getBytes()); 								// 保存发送内容
        buffer.flip(); 											// 重设缓冲区
        this.clientChannel.write(buffer, buffer, new ClientWriteHandler(
                this.clientChannel, this.latch));					// 缓冲区输出
        if ("exit".equalsIgnoreCase(msg)) {						// 结束指令
            return false;
        }
        return true;
    }
}
