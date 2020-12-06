package JUC;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class JUCblockingDeQUE {

    public static void main(String[] args) throws Exception {
        BlockingDeque<String> queue = new LinkedBlockingDeque<String>(5); 		// 双端队列
        for (int x = 0; x < 10; x++) {
            if (x % 2 == 0) {
                new Thread(() -> {
                    for (int y = 0; y < 100; y++) {
                        try {
                            TimeUnit.SECONDS.sleep(2);						// 操作延迟
                            String msg = "【" + Thread.currentThread().getName()
                                    + "】生产数据 = " + y;
                            queue.putFirst(msg);								// 首部保存
                            System.out.println("｛生产数据｝  " + msg);
                        } catch (Exception e) {}
                    }
                }, "【FIRST】MLDN生产者-" + x).start();							// 启动线程
            } else {
                new Thread(() -> {
                    for (int y = 0; y < 100; y++) {
                        try {
                            TimeUnit.SECONDS.sleep(2);						// 操作延迟
                            String msg = "【" + Thread.currentThread().getName()
                                    + "】生产数据 = " + y;
                            queue.putLast(msg);								// 尾部保存
                            System.out.println("｛生产数据｝  " + msg);
                        } catch (Exception e) {}
                    }
                }, "〖LAST〗MLDN生产者-" + x).start();							// 启动线程
            }
        }
        for (int x = 0; x < 2; x++) {
            new Thread(() -> {
                int count = 0;
                while (true) { 												// 不断消费
                    try {
                        TimeUnit.SECONDS.sleep(2);							// 延迟操作
                        if (count % 2 == 0) {
                            System.err.println("（FIRST取出）"
                                    + queue.takeFirst());							// 首部取出
                        } else {
                            System.err.println("（LAST取出）"
                                    + queue.takeLast());							// 尾部取出
                        }
                        count++;
                    } catch (InterruptedException e) {}
                }
            }, "MLDN消费者-" + x).start();
        }
    }

}
