package com.qjw4;

public class tte {
	public static void main(String[] args) {
		student per=new student();
		per.name="qjw";
		//per.rest();
		student per2=new student("qjw",56,"��Դ�붯��");
		per2.study();
		System.out.println(per2 instanceof student);
	}
}
class Person{
	String name;
	int height;
	public void rest() {
		System.out.println(name+"��Ϣ");
	}
	
}

class student extends Person{
	String major;
	public void study() {
		System.out.println("my name is "+ name+height+ major);
	}
	public student(String name,int height,String major) {
		this.name=name;
		this.height=height;
		this.major=major;
		
	}
	public student() {
		
	}
}
