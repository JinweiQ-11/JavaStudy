package com.qjw4;

public class d2array {
	public static void main(String args[]) {
		//int data[]=new int[]{1,2,3,4,5};
		/*for(int i=0;i<data.length;i++) {
			for(int j=0;j<data[i].length;j++) {
				System.out.println(data[i][j]);
			}
			System.out.println();
		}*/
		int data[]=inita();
		change(data);
		printA(data);
	}
	public static int[] inita() {
		int arr[]=new int[] {1,2,3,4,5};
		return arr;
		
	}
	public static void change(int arr[]) {
		for(int i=0;i<arr.length;i++) {
			arr[i]*=2;
		}
	}
	public static void printA(int temp[]) {
		
		for(int i=0;i<temp.length;i++) {
			
			System.out.println(temp[i]);
		}
	}

}
