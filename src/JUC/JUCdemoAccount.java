package JUC;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Account{
  private String name;
  private double ssert;
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  public Account(String name,double ssert){
        this.name = name;
        this.ssert = ssert;
  }
  public void saveMoney(double money){
      this.readWriteLock.writeLock().lock();
      try{
          this.ssert += money;
          TimeUnit.SECONDS.sleep(2);
          System.out.println("输出"+Thread.currentThread().getName()+"修改了余额"+money+"账户剩余"
          +this.ssert);
      }catch (Exception e){

      }finally {
          this.readWriteLock.writeLock().unlock();
      }
  }
  public String toString(){
      this.readWriteLock.readLock().lock();
      try{
          TimeUnit.SECONDS.sleep(1);
          return "账户信息:"+Thread.currentThread().getName()+this.name+this.ssert;
      }catch (Exception e){
          return  null;
      }finally {
          this.readWriteLock.readLock().unlock();
      }

  }

}
public class JUCdemoAccount {
    public static void main(String args[]){
        Account account = new Account("wuha",0.0);
        double[] moneyData = new double[]{100,200,300,400,500};
        for(int x = 0;x < 5;x++){
            new Thread(()->{
                for(int y = 0 ;y < moneyData.length;y++){
                    account.saveMoney(moneyData[y]);
                }
            }).start();
        }
        for(int x = 0;x < 5; x++){
            new Thread(()->{
                while(true){
                    System.out.println(account.toString());
                }
            }).start();

        }
    }


}
