package com.first;

import java.util.List;
import java.util.ArrayList;

class point<T>{
	private T x;
	private T y;
	public void setX(T x,T y) {
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return this.x;
	}
	public T getY() {
		return this.y;
	}
}
public class mytest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer>a = new ArrayList<Integer>();
		point <Integer> b = new point<Integer>();
		b.setX(10, 10);
		int x = b.getX();
	}

}
