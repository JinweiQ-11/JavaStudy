package JUC;

import java.util.concurrent.TimeUnit;

public class Test03 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int x = 0; x < 10; x++) {
                try { // 可以直接利用具体时间单位设置具体的休眠时长
                    TimeUnit.SECONDS.sleep(5);// 休眠1分钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【" + Thread.currentThread().getName() + "】x = " + x);
            }
        }).start();
    }
}

