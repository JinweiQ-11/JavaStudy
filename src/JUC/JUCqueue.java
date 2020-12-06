package JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class JUCqueue {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5); 	// 5个队列容量
        for (int x = 0; x < 10; x++) {									// 10个生产者
            new Thread(() -> {
                for (int y = 0; y < 100; y++) {
                    try {
                        TimeUnit.SECONDS.sleep(2);						// 操作延迟
                        String msg = "【" + Thread.currentThread().getName()
                                + "】生产数据 = " + y;
                        queue.put(msg);									// 队列保存数据
                        System.out.println("｛生产数据｝  " + msg);			// 提示信息
                    } catch (Exception e) {}
                }
            }, "MLDN生产者-" + x).start();									// 启动线程
        }
        for (int x = 0; x < 2; x++) {										// 2个消费者
            new Thread(() -> {
                while (true) { 											// 不断消费
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        // 延迟操作
                        //System.out.print(Thread.currentThread().getName());
                        System.err.println(queue.take());					// 消费数据
                    } catch (InterruptedException e) {}
                }
            }, "MLDN消费者-" + x).start();									// 启动线程
        }
    }

}
