package com.first;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;


public class TestTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Map<Emp,String>test = new TreeMap<>();	
         test.put(new Emp(100, "asa", 50000), "value100");
         test.put(new Emp(150, "as", 5000), "value150");
         test.put(new Emp(120, "as", 500), "value120");
         
         
         for(Emp key:test.keySet()) {
        	 System.out.println(key+"---"+test.get(key));
         }
         
        
	}

}

class Emp implements Comparable<Emp>{
	int id;
	String name;
	double salary;
	public Emp(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	@Override
	public int compareTo(Emp o) {
		
		if(this.salary>o.salary) {
			return 1;
		}else if(this.salary<o.salary) {
			return -1;
		}else {
			if(this.id>o.id) {
				return 1;
			}
			else if(this.id<o.id) {
				return -1;
			}
			else {
				return 0;
			}
		}
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id="+id;
	}
	
	
}
