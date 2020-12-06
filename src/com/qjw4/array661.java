package com.qjw4;
class ffsort{
	public  void sort(int data[]) {
		for(int x=0;x<data.length;x++) {
			for(int y=0;y<data.length-x-1;y++) {
				if(data[y]>data[y+1]) {
					int temp=data[y];
					data[y]=data[y+1];
					data[y+1]=temp;
				}
			}
		}
		
	
	for(int i=0;i<data.length;i++) {
		System.out.println(data[i]);
		}
	}
}
public class array661 {
	public static void main(String args[]) {
		int data[]=new int[] {8,9,0,2,3,5,10,7,6,1};
		ffsort paixu=new ffsort();
		paixu.sort(data);
	}
}
