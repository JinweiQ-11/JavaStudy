package NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe {
    public static void main(String[] args) throws Exception {
        Pipe pipe = Pipe.open(); 											// 打开管道流
        new Thread(() -> {
            Pipe.SourceChannel sourceChannel = pipe.source(); 				// 打开管道输入流
            ByteBuffer buffer = ByteBuffer.allocate(50); 					// 开辟缓存空间
            try {
                int count = sourceChannel.read(buffer); 					// 返回读取个数
                buffer.flip();											// 重置缓冲区
                System.out.println("｛接收端｝" + new String(buffer.array(), 0, count));
            } catch (IOException e) {}
        }, "接收线程").start();											// 启动线程
        new Thread(() -> {
            String msg = "【" + Thread.currentThread().getName() + "】www.mldn.cn"; // 信息
            Pipe.SinkChannel sinkChannel = pipe.sink(); 					// 获取管道输出流
            ByteBuffer buffer = ByteBuffer.allocate(50); 					// 开辟缓冲区
            buffer.put(msg.getBytes()); 									// 设置要发送的数据
            buffer.flip(); 												// 重置缓冲区
            while (buffer.hasRemaining()) {								// 缓冲区有数据
                try {
                    sinkChannel.write(buffer);							// 输出
                } catch (IOException e) {}
            }
        }, "发送线程").start();											// 启动线程
    }

}
