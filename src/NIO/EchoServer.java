package NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SocketClientChannelThread implements Runnable {					// 客户端处理线程
    private SocketChannel clientChannel; 								// 客户端通道
    private boolean flag = true; 										// 循环标记
    public SocketClientChannelThread(SocketChannel clientChannel) throws Exception {
        this.clientChannel = clientChannel;							// 保存客户端通道
        System.out.println("【客户端连接成功】，该客户端的地址为：" +
                clientChannel.getRemoteAddress());
    }
    @Override
    public void run() {												// 线程任务
        ByteBuffer buffer = ByteBuffer.allocate(50); 					// 创建缓冲区
        try {
            while (this.flag) { 										// 不断与客户端交互
                // 由于可能重复使用一个Buffer，所以使用之前需要将其做出清空处理
                buffer.clear();
                int readCount = this.clientChannel.read(buffer); 		// 接收客户端发送数据
                String readMessage = new String(buffer.array(),
                        0, readCount).trim(); 						// 数据变为字符串
                System.out.println("【服务器接收到消息】" + readMessage);// 提示信息
                String writeMessage = "【ECHO】" + readMessage + "\n";	// 回应信息
                if ("exit".equals(readMessage)) { 						// 结束指令
                    writeMessage = "【EXIT】拜拜，下次再见！";			// 结束消息
                    this.flag = false; 								// 修改标记
                }
                buffer.clear(); 										// 清空缓冲区
                buffer.put(writeMessage.getBytes()); 					// 缓冲区保存数据
                buffer.flip(); 										// 重置缓冲区
                this.clientChannel.write(buffer);						// 回应信息
            }
            this.clientChannel.close(); 								// 关闭通道
        } catch (Exception e) {}
    }
}

public class EchoServer {
    public static final int PORT = 9999; 								// 绑定端口
    public static void main(String[] args) throws Exception {
        // 考虑到性能的优化所以最多只允许5个用户进行访问
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 打开一个服务端的Socket的连接通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); 					// 设置非阻塞模式
        serverSocketChannel.bind(new InetSocketAddress(PORT)); 			// 服务绑定端口
        // 打开一个选择器，随后所有的Channel都要注册到此选择器之中
        Selector selector = Selector.open();
        // 将当前的ServerSocketChannel统一注册到Selector之中，接受统一的管理
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动程序，该程序在" + PORT + "端口上监听，等待客户端连接... ...");
        // 所有的连接处理都需要被Selector所管理，也就是说只要有新的用户连接，那么就通过Selector处理
        int keySelect = 0; 											// 接收连接状态
        while ((keySelect = selector.select()) > 0) { 					// 持续等待连接
            Set<SelectionKey> selectedKeys = selector.selectedKeys(); 	// 获取全部连接通道
            Iterator<SelectionKey> selectionIter = selectedKeys.iterator();
            while (selectionIter.hasNext()) {
                SelectionKey selectionKey = selectionIter.next(); 		// 获取每一个通道
                if (selectionKey.isAcceptable()) { 					// 模式为接收连接模式
                    SocketChannel clientChannel = serverSocketChannel.accept(); // 等待接收
                    if (clientChannel != null) { 						// 已经有了连接
                        executorService.submit(new SocketClientChannelThread(clientChannel));
                    }
                }
                selectionIter.remove(); 								// 移除掉此通道
            }
        }
        executorService.shutdown();									// 关闭线程池
        serverSocketChannel.close();									// 关闭服务端通道
    }

}
