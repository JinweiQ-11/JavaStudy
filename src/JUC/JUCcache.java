package JUC;

import java.util.Map;
import java.util.concurrent.*;

public class JUCcache {
    public static void main(String[] args) throws Exception {
        Cache<Long,News> cache = new Cache<Long,News>() ;				// 定义缓存类对象
        cache.put(1L, new News(1L,"www.mldn.cn"));						// 向缓存保存数据
        cache.put(2L, new News(2L,"www.jixianit.com"));				// 向缓存保存数据
        cache.put(3L, new News(3L,"www.mldnjava.cn"));					// 向缓存保存数据
        System.out.println(cache.get(1L));							// 通过缓存获取数据
        System.out.println(cache.get(2L));							// 通过缓存获取数据
        TimeUnit.SECONDS.sleep(5);									// 延迟获取
        System.out.println(cache.get(1L));							// 通过缓存获取数据
    }
}
class Cache<K, V> {	// 定义一个缓存数据处理类
    private static final TimeUnit TIME = TimeUnit.SECONDS ;				// 时间工具类
    private static final long DELAY_SECONDS = 2 ; 						// 缓存时间
    private Map<K,V> cacheObjects = new ConcurrentHashMap<K,V>() ; 		// 设置缓存集合
    private BlockingQueue<DelayedItem<Pair>> queue = new DelayQueue<DelayedItem<Pair>>() ;
    public Cache() {													// 启动守护线程
        Thread thread = new Thread(()->{
            while(true) {
                try {
                    DelayedItem<Pair> item = Cache.this.queue.take() ; 	// 数据消费
                    if (item != null) {								// 有数据
                        Pair pair = item.getItem() ; 					// 获取内容
                        Cache.this.cacheObjects.remove(
                                pair.key, pair.value) ; 				// 删除数据
                    }
                } catch (InterruptedException e) {}
            }
        })  ;
        thread.setDaemon(true); 										// 设置后台线程
        thread.start();												// 线程启动
    }
    public void put(K key,V value) throws Exception {					// 保存数据
        V oldValue = this.cacheObjects.put(key, value) ; 				// 数据保存
        if (oldValue != null) {										// 重复保存
            this.queue.remove(oldValue) ;								// 删除已有数据
        }
        this.queue.put(new DelayedItem<Pair>(new Pair(key, value),
                DELAY_SECONDS, TIME));								// 重新保存
    }
    public V get(K key) {												// 获取缓存数据
        return this.cacheObjects.get(key) ;							// Map查询
    }
    private class Pair {												// 封装保存数据
        private K key ;												// 数据Key
        private V value ;											// 数据Value
        public Pair(K key,V value) {
            this.key = key ;
            this.value = value ;
        }
    }
    private class DelayedItem<T> implements Delayed { 					// 延迟数据保存项
        private T item ; 												// 数据项
        private long delay ; 											// 保存时间
        private long expire ; 										// 失效时间
        public DelayedItem(T item,long delay,TimeUnit unit) {
            this.item = item ;
            this.delay = TimeUnit.MILLISECONDS.convert(delay,unit) ;
            this.expire = System.currentTimeMillis() + this.delay ;
        }
        @Override
        public int compareTo(Delayed obj) {
            return (int) (this.delay - this.getDelay(TimeUnit.MILLISECONDS));
        }
        @Override
        public long getDelay(TimeUnit unit) { 							// 延时计算
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS) ;
        }
        public T getItem() {											// 获取数据
            return this.item ;
        }
    }
}
class News {															// 新闻数据
    private long nid ;
    private String title ;
    public News(long nid,String title) {
        this.nid = nid ;
        this.title = title ;
    }
    public String toString() {
        return "【新闻数据】新闻编号：" + this.nid + "、新闻标题：" + this.title ;
    }
}
