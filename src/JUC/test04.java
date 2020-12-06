package JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class test04 {
    public static void main(String args[]) throws InterruptedException {
        AtomicLong num = new AtomicLong(100);					// 实例化原子操作类
        for(int x = 0 ; x < 10 ; x ++) {
            new Thread(()->{
                num.addAndGet(200); 							// 增加数据并取得
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);							// 休眠2秒，等待执行结果
        System.out.println(num.get());
    }
					// 自增之后的内容


}
