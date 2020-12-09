package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOEchoServer {
    public static void main(String[] args) throws Exception {
        new Thread(new AIOServerThread()).start(); 						// 启动服务器
    }
}


class EchoHandler implements CompletionHandler<Integer, ByteBuffer> { 		// 实现回调处理
    private AsynchronousSocketChannel clientChannel; 						// 客户端对象
    private boolean exit = false; 										// 结束标记
    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }
    @Override
    public void completed(Integer result, ByteBuffer buffer) {				// 回调任务
        buffer.flip(); 													// 重置缓冲区
        String readMessage = new String(buffer.array(), 0,
                buffer.remaining()).trim(); 							// 接收读取数据
        System.err.println("【服务器端读取到数据】" + readMessage); 			// 信息提示
        String resultMessage = "【ECHO】" + readMessage; 					// 回应信息
        if ("exit".equalsIgnoreCase(readMessage)) {						// 退出标记
            resultMessage = "【EXIT】拜拜，下次再见！"; 						// 回应内容
            this.exit = true; 											// 退出
        }
        this.echoWrite(resultMessage); 									// 消息回应
    }
    @Override
    public void failed(Throwable exp, ByteBuffer buffer) {					// 异步处理失败
        this.closeClient();												// 关闭连接
    }
    private void closeClient() {
        System.out.println("客户端连接有错误，中断与此客户端的处理！");
        try {
            this.clientChannel.close(); 									// 通道关闭
        } catch (IOException e) {}
    }
    private void echoWrite(String result) { 								// 数据回应
        ByteBuffer buffer = ByteBuffer.allocate(100); 						// 回应缓冲区
        buffer.put(result.getBytes()); 									// 回应处理
        buffer.flip(); 													// 重置缓冲区
        this.clientChannel.write(buffer, buffer,
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer buffer) {
                        if (buffer.hasRemaining()) { 						// 有数据
                            EchoHandler.this.clientChannel.write(
                                    buffer, buffer, this); 			// 输出
                        } else {
                            if (EchoHandler.this.exit == false) { 			// 继续下一次操作
                                ByteBuffer readBuffer = ByteBuffer.allocate(100);
                                EchoHandler.this.clientChannel.read(readBuffer, readBuffer,
                                        new EchoHandler(EchoHandler.this.clientChannel));
                            }
                        }
                    }
                    @Override
                    public void failed(Throwable exp, ByteBuffer buffer) {
                        EchoHandler.this.closeClient();					// 关闭通道
                    }
                });
    }
}
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServerThread> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AIOServerThread aioThread) {
        aioThread.getServerChannel().accept(aioThread, this); 				// 接收连接
        ByteBuffer buffer = ByteBuffer.allocate(100); 						// 接收缓冲区
        channel.read(buffer, buffer, new EchoHandler(channel)); 			// 交由其它异步处理
    }
    @Override
    public void failed(Throwable exp, AIOServerThread aioThread) {
        System.out.println("服务器的连接处理失败... ...");
        aioThread.getLatch().countDown(); 									// 解除阻塞状态
    }
}


class AIOServerThread implements Runnable { 								// AIO处理线程
    private static final int PORT = 9999; 									// 监听端口
    private CountDownLatch latch = null; 									// 服务端关闭阻塞
    private AsynchronousServerSocketChannel serverChannel = null; 			// 服务端通道
    public AIOServerThread() throws Exception {
        this.latch = new CountDownLatch(1); 								// 服务端阻塞线程数
        this.serverChannel = AsynchronousServerSocketChannel.open(); 		// 异步通道
        this.serverChannel.bind(new InetSocketAddress(PORT)); 				// 绑定端口
        System.out.println("服务器启动成功，在" + PORT + "端口上进行监听，等待客户端连接 ... ...");
    }
    public AsynchronousServerSocketChannel getServerChannel() {
        return serverChannel;
    }
    public CountDownLatch getLatch() {
        return latch;
    }
    @Override
    public void run() { 													// 启动线程
        this.serverChannel.accept(this, new AcceptHandler()); 				// 等待连接
        try {
            this.latch.await(); 											// 保持连接
            System.err.println("服务器的连接失败，服务器停止运行 ... ...");
        } catch (InterruptedException e) {}
    }
}


