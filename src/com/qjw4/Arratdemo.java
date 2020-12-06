package com.qjw4;

class uu{
	private int sum;
	private double avg;
	private int max;
	private int min;
	public uu(int data[]) {
		this.max=data[0];
		this.min=data[0];
		for(int i=0;i<data.length;i++) {
			if (data[i]>max) {
				max=data[i];
			}
			if(data[i]<min) {
				min=data[i];
			}
			sum+=data[i];
		}
		avg=sum/data.length;
	}
	public int getsum() {
		return sum;
	}
	public double getavg() {
		return avg;
	}
	public int getmax() {
		return max;
	}
	public int getmin() {
		return min;
	}
}
public class Arratdemo {
	public static void main(String args[]) {
		int data[] = new int[] {3,4,5,1,2,8,9,2,10,0};
		uu xx=new uu(data);
		System.out.println(xx.getmin());
	}
}

