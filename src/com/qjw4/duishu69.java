package com.qjw4;
class Persson{
	private String name;
	private int age;
	public Persson(String name,int age) {
		this.name=name;
		this.age=age;
	}
	public String getinfo() {
		return "����"+this.name+" ����"+this.age;
	}
}
public class duishu69 {
	public static void main(String args[]) {
		Persson per[]=new Persson[3];
		per[0]=new Persson("ddf",11);
		per[1]=new Persson("ddf",12);
		per[2]=new Persson("ddf",13);
		for (int i=0;i<per.length;i++) {
			System.out.println(per[i].getinfo());
		}
	} 
}
