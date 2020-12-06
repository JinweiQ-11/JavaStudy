package JUC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class JUCdelayDeque {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Student> queue = new DelayQueue<Student>();		// 定义延迟队列
        queue.put(new Student("小李", 2, TimeUnit.SECONDS));			// 保存延迟队列信息
        queue.put(new Student("小王", 5, TimeUnit.SECONDS));
        while (!queue.isEmpty()) { 									// 是否还有人在
            Student stu = queue.take(); 								// 获取弹出数据
            System.out.println(stu);
            TimeUnit.SECONDS.sleep(1);								// 延迟操作
        }
    }
}
class Student implements Delayed { 									// 定义延迟计算
    private String name; 												// 姓名
    private long expire;												// 离开时间
    private long delay; 												// 停留时间
    /**
     * 进行延迟项的设置
     * @param name  聚会者的姓名
     * @param delay 停留的时间（延迟时间）
     * @param unit  你使用的时间单位
     */
    public Student(String name, long delay, TimeUnit unit) {
        this.name = name;
        // 如果要计算离开的时间肯定需要与当前的系统时间进行比较，系统时间返回的都是毫秒
        this.delay = TimeUnit.MILLISECONDS.convert(delay, unit);		// 转换时间为毫秒
        this.expire = System.currentTimeMillis() + this.delay;			// 失效时间计算
    }
    public String toString() {
        return this.name + "同学已经达到了预计的停留时间“" +
                TimeUnit.SECONDS.convert(this.delay, TimeUnit.MILLISECONDS) + "”秒，已经离开了。";
    }
    @Override
    public int compareTo(Delayed obj) {								// 队列弹出计算
        return (int) (this.delay - this.getDelay(TimeUnit.MILLISECONDS));
    }
    @Override
    public long getDelay(TimeUnit unit) { 								// 延迟时间计算
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}
