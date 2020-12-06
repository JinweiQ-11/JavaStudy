package com.qjw4;
import java.util.*;
class AA{
	public static void res(int temp[][]) {
		for(int i=0;i<temp.length;i++) {
			for(int j=0; j<temp[i].length; j++) {
				if(j<i) {
					int t=temp[i][j];
					temp[i][j]=temp[j][i];
					temp[j][i]=t;
					
				}	
			}
		}
	}
	public static void pri(int temp[][]) {
		for(int i=0;i<temp.length;i++) {
			for(int j=0; j<temp[i].length; j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
	}
	
}
}
public class d2zhuanzhi {
	public static void main(String args[]) {
		int data[][]=new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		AA.res(data);
		AA.pri(data);
		//Arrays.sort(data);
	}
}
