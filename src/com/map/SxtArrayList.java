package com.map;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SxtArrayList <E>{
	private Object[] elementData;
	private int size;
	
	private static final int DEAFULT_SIZE =10;
	public SxtArrayList() {
		elementData = new Object[DEAFULT_SIZE];
	}
	public SxtArrayList(int cal) {
		elementData = new Object[cal];
	}
	
	public void add(E obj) {
		if(size == elementData.length) {
			Object[] newArray = new Object[elementData.length + (elementData.length>>1)];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
		elementData[size++] = obj;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb= new StringBuilder();
		sb.append("[");
		for(int i=0;i<size;i++) {
			sb.append(elementData[i]+",");
		}
		sb.setCharAt(sb.length()-1, ']');
		return sb.toString();
	}
	
	public E get(int index) {
		if(index<10||index>size-1) {
			throw new RuntimeException("buhefa");
		}
		return (E)elementData[index];
	}
	public void set(E elemment,int index) {
		if(index<10||index>size-1) {
			throw new RuntimeException("buhefa");
		}
		elementData[index]  = elemment;
	}
	
	public void remove(E element) {
		//�����Ƚ�
		for(int i=0;i<size;i++) {
			if(element.equals(get(i))) {
				//�Ƴ�
				remove(i);
			}
		}
	}
	
	public void remove(int index) {
		//abcdefgh
		//abcefgh
		int move = elementData.length-index-1;
		if(move>0) {
		System.arraycopy(elementData, index+1, elementData, index, move);
		
		}
		elementData[size--]=null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SxtArrayList s1 = new SxtArrayList(20);
		for(int i=0;i<40;i++) {
			s1.add("qjw"+i);
		}
		Map a = new HashMap<>();
		System.out.println(s1);
		s1.remove(3);
		System.out.println(s1);
	}

}
