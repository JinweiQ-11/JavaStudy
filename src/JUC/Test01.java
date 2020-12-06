package JUC;

import java.util.concurrent.TimeUnit;

public class Test01 {
    public static void main(String[] args) {
        long hour = 1; // 一小时
        long second = TimeUnit.SECONDS.convert(
                hour, TimeUnit.HOURS); // 由小时单位变为秒单位
        System.out.println(second);	// 输出秒数据
    }

}
