package JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 无障碍锁
 */

class AccountStamped { 												// 银行账户
    private String name; 										// 账户名称
    private double asset; 									// 账户资产
    private StampedLock stampledLock = new StampedLock() ; 		// 读写锁
    public AccountStamped(String name, double asset) {				// 设置账户信息
        this.name = name;
        this.asset = asset;
    }
    public void saveMoney(double money) { 						// 资产追加
        long stamp = this.stampledLock.readLock() ; 			// 获取读锁，检查状态
        boolean flag = true ;
        try {
            long writeStamp = this.stampledLock.tryConvertToWriteLock(stamp) ;	// 转为写锁
            while(flag) {
                if (writeStamp != 0) { 						// 当前为写锁
                    stamp = writeStamp ; 						// 修改为写锁的标记
                    this.asset += money ; 					// 进行资产修改
                    TimeUnit.SECONDS.sleep(1); 				// 模拟延迟
                    System.out.println("【" + Thread.currentThread().getName() +
                            "】修改银行资产数据，修改金额“" + money + "”，当前总资产：" + this.asset);
                    flag = false ; 							// 结束循环
                } else {	// 没有获取到写锁
                    this.stampledLock.unlockRead(stamp);		// 释放读锁
                    writeStamp = this.stampledLock.writeLock() ; // 获取写锁
                    stamp = writeStamp ;
                }
            }
        } catch (Exception e) {
        } finally {
            this.stampledLock.unlock(stamp); 					// 解锁
        }
    }
    public String toString() { 								// 数据读取
        long stamp = this.stampledLock.tryOptimisticRead() ; 	// 获取乐观锁
        try {
            double current = this.asset ; 					// 获取当前的资产
            TimeUnit.SECONDS.sleep(1); 						// 模拟延迟
            // validate()方法虽然可以检测但是依然有可能出现异常，所以本处依据StampleLock
            // 类的源代码多追加了一个验证机制
            if (!this.stampledLock.validate(stamp) ||
                    (stamp & (long)(Math.pow(2, 7)-1))==0) {	// 验证记录点有效性
                long readStamp = this.stampledLock.readLock() ; // 获取互斥锁
                current = this.asset ; 						// 修改当前内容
                stamp = readStamp ; 							// 修改原始记录点
            }
            return "【账户信息｛" + Thread.currentThread().getName() +
                    "｝】账户名称：" + this.name + "、银行资产：" + current ;
        } catch(Exception e) {
            return null ;
        } finally {
            try {
                this.stampledLock.unlockRead(stamp); 			// 释放指定的写锁
            } catch (Exception e) {}
        }
    }
}
public class StampedLockTest {
    public static void main(String[] args) {
        Account account = new Account("小李", 0.0); 			// 存放数据
        double[] moneyData = new double[] { 120.00, 300.00, 500, 700, 5000.0};
        for (int x = 0; x < 5; x++) { 						// 5个写入线程
            new Thread(() -> {
                for (int y = 0; y < moneyData.length; y++) {
                    account.saveMoney(moneyData[y]); 			// 存放金额
                }
            }).start();
        }
        for (int x = 0; x < 30; x++) { 						// 30个读取线程
            new Thread(() -> {
                while (true) {
                    System.err.println(account.toString());	// 获取数据
                }
            }).start();
        }
}
}

