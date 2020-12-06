package JUC;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class JUCList {
     public static void main(String[] args) throws Exception {
         List <String> all = new CopyOnWriteArrayList<>();
         Set<String> allSet = new CopyOnWriteArraySet<>();//Set

         //List<String> all = new ArrayList<String>(); 				// List集合
         for (int num = 0; num < 10; num++) {						// 循环定义线程
             new Thread(() -> {
                 for (int x = 0; x < 10; x++) {
                     allSet.add(Thread.currentThread().getName());
                     all.add(Thread.currentThread().getName());// 每个线程保存10个数据
                 }
                 //System.out.println(all);
                 System.out.println(allSet);
             }, "集合操作线程-" + num).start();
         }

         Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();	// Map集合
         for (int num = 0; num < 10; num++) {									// 创建线程
             new Thread(() -> {
                 for (int x = 0; x < 10; x++) {
                     map.put("www.mldn.cn"+x, x);								// 保存信息
                     //System.out.println(map);
                 }
                 System.out.println(map);
             }, "集合操作线程-" + num).start();									// 启动线程
         }

     }

}


