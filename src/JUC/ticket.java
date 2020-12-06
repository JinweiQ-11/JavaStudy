package JUC;

import java.util.concurrent.locks.ReentrantLock;

class TTicket { 													// 卖票类，该类不是线程子类
    private int count = 10; 										// 售票总数
    private ReentrantLock reentrantLock = new ReentrantLock(); 		// 互斥锁（独占锁）
    /**
     * 售票操作方法，该方法不再直接使用synchronized进行同步处理，每次只允许一个线程操作
     */
    public void sal() {
        try { 													// 单线程操作
            this.reentrantLock.lock(); 							// 锁定
            if (this.count > 0) { 								// 票数有空余
                //TimeUnit.MILLISECONDS.sleep(50);						// 模拟网络延迟
                System.out.println("【" + Thread.currentThread().getName() +
                        "】卖票，票数剩余：" + this.count--);
            } else {
                System.err.println("【" + Thread.currentThread().getName() +
                        "】没票了，别卖了。");
            }
        } catch (Exception e) {
        } finally {
            this.reentrantLock.unlock(); 							// 解除锁定
        }
    }
}

public class ticket {
    public static void main(String args[]){
        TTicket ticket = new TTicket();								// 实例化类对象
        for(int x = 0; x <20; x++) { 							// 创建N个卖票线程对象
            new Thread(() -> {
                ticket.sal(); 									// 卖票
            }, "售票员-" + x).start();								// 线程启动
        }

    }

}

