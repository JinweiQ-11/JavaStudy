package com.first;

import java.util.concurrent.atomic.AtomicInteger;

class MyfirstT extends Thread{
	private String title;
	public MyfirstT(String title) {
		this.title = title;
	}
	@Override
	public void run() {
		for(int x = 0;x < 10; x++) {
			System.out.println(this.title + "����, x = "+x);
		}
	}
}
public class Mythread {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyfirstT("xianchenga").start();
		new MyfirstT("xianchengb").start();
		AtomicInteger i = new AtomicInteger();
		new Thread(()->{
			i.getAndIncrement();
		}).start();
	}

}
