package com.first;

public class JoinThread {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Thread mainThread = Thread.currentThread(); 
		Thread thread = new Thread(()-> {
			for (int x = 0;x < 100;x++) {
				if(x%3==0) {
					
						//mainThread.join();
						Thread.yield();
						System.out.println(Thread.currentThread().getName()+"rang");
					    
				}
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"ִ��x="+x);
			}
		},"���߳�");
		thread.start();
		for(int x =0;x<100;x++) {
			Thread.sleep(100);
			System.out.println("�� x="+x);
		}
	}

}
