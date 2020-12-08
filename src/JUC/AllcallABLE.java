package JUC;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程放入集合
 */
public class AllcallABLE {
    public static void main(String[] args) throws Exception {
        Set<Callable<String>> allThreads = new HashSet<Callable<String>>(); // 保存多个线程对象
        for (int x = 0; x < 5; x++) {										// 创建线程
            final int temp = x;
            allThreads.add(() -> {										// 集合追加线程
                return Thread.currentThread().getId() + " - "
                        + Thread.currentThread().getName() + "、数量：" + temp;
            });
        }
        ExecutorService service = Executors.newFixedThreadPool(3); 			// 创建定长线程池
        List<Future<String>> results = service.invokeAll(allThreads); 		// 执行线程对象
        for (Future<String> future : results) {							// 获取执行结果
            System.out.println(future.get());
        }
    }
}
