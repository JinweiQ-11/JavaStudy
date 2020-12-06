package com.first;

import java.util.ArrayList;
import java.util.List;

public class array {
	private  int[] arrayset(int [] arrayA){
		
       for(int i=0;i<arrayA.length;i++) {
    	   arrayA[i] = i*i*arrayA[i];
       }
       List<String> str = new ArrayList<>();
       return arrayA;
    }
	public static void main(String[] args) {
		int[] arryA = new int []{1,2,3,4};
		int [] b;
		array a = new array();
		b = a.arrayset(arryA);
		for(int m:b) {
			System.out.println(m);
		}
		String strA = "true";
		boolean flagA = Boolean.parseBoolean(strA);
		System.out.println(flagA);
	}
}
