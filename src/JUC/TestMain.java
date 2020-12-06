package JUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory(false));
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestRunnable(String.valueOf(i)));
        }
        executorService.shutdown();
    }
}

     class TestRunnable implements Runnable{

        private String msg;

        public TestRunnable(String msg){
            this.msg = msg;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"runnable:"+msg);
        }
    }
class MyThreadFactory implements ThreadFactory{

    private AtomicInteger atomicInteger = new AtomicInteger();
    private boolean isDaemon;
    public MyThreadFactory(boolean isDaemon){
        this.isDaemon = isDaemon;
    }
    @Override
    public Thread newThread(Runnable r) {
        atomicInteger.getAndIncrement();
        //Thread thread =  new MyWorkThread(atomicInteger,r);
        Thread thread =  new Thread(r);
        thread.setName("定制池中的线程->" + Math.random());
        return thread;
    }
}
class MyWorkThread extends Thread{
    private AtomicInteger atomicInteger;
    public MyWorkThread(AtomicInteger atomicInteger,Runnable runnable){
        super(runnable);//所以会运行test线程对象
        this.atomicInteger = atomicInteger;
    }
    @Override
    public void run() {
        System.out.println(this.getName()+" test"+atomicInteger.getAndDecrement());
        super.run();
    }
}
