package com.qjw4;

public class testoverw {
	public static void main(String[] args) {
		horse h= new horse();
		h.run();
	}
	
	
}
class vehicle{
	public void run() {
		System.out.println("��");
	}
	public void stop() {
		System.out.println("ֹͣ");
	}
}
class horse extends vehicle{
	public void run() {
		System.out.println("tmd");
	}
}


