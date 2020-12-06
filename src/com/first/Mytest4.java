package com.first;


class Mythread1 implements Runnable{
	private int ticket = 5;
	private String title;
	//public Mythread1(String title) {
		//this.title = title;
	//}
	@Override
	public void run() {
		for(int x = 0;x < 100; x++) {
			if(this.ticket>0) {
				System.out.println("��Ʊ,ticket = " + this.ticket--);
			}
			
		}
	}
}



class MyThread2 implements Runnable{
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
public class Mytest4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread2 mt = new MyThread2();
		 new Thread(mt,"1").start();
		 new Thread(mt,"2").start();
		 mt.run();
		 //new Thread(mt).start();

	}

}
