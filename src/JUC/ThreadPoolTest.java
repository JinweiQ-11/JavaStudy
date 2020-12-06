package JUC;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
        // 创建定时调度池，并且设置允许的内核线程数量为1
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int x = 0; x < 1; x++) {
            int index = x;
            // 设置调度任务，操作单位为“秒”，3秒后开始执行，每2秒执行一次
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "、x = " + index);
                }
            }, 3, 10, TimeUnit.SECONDS);
        }
        //new ScheduledThreadPoolExecutor(5).scheduleAtFixedRate()
    }

}
