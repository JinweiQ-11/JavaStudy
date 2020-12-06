package JUC;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test02 {
    public static void main(String[] args) {
        long current = System.currentTimeMillis(); 	// 获取当前的时间
        // 利用当前的时间戳（毫秒） + 18天的毫秒数
        long after = current + TimeUnit.MILLISECONDS.convert(18, TimeUnit.DAYS);
        // 将long数据转为Date并且利用SimpleDateFormat进行格式化显示
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date(after)));
    }

}
