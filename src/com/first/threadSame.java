package com.first;
class MyThreads implements Runnable{
	private int ticket = 3;
	@Override
	public void run() {
		while(true) {
			
			if(this.ticket>0) {
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"maipiao,ticket = "+this.ticket--);
			}else {
				System.out.println("meile");
				break;
			}
		}
	}
	
}


/*
 * ͬ�������
 */

class MyThreadSameCode implements Runnable{
	private int ticket = 3;
	@Override
	public void run() {
		while(true) {
			synchronized(this) {
			if(this.ticket>0) {
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"��Ʊ,ticket = "+this.ticket--);
			}else {
				System.out.println("������");
				break;
			}
			}
		}
	}
	
}
/*
 * ͬ������
 */


class MyThreadSameM implements Runnable{
	private int ticket = 3;
	@Override
	public void run() {
		while(this.sale()) {
			;
		}
	}
	
	public synchronized boolean sale() {
		if(this.ticket>0) {
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"��Ʊ,ticket = "+this.ticket--);
			return true;
		}else {
			System.out.println("������");
			return false;
		}
	} 
	
}
public class threadSame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThreadSameM mt = new MyThreadSameM();
		new Thread(mt,"A").start();
		new Thread(mt,"B").start();
		new Thread(mt,"C").start();
	}

}
