package com.first;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OutPut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Map<String,Integer>map = new HashMap<>();
		map.put("one", 1);
		map.put("two",2);
		map.put("three",3);
		Set<Map.Entry<String,Integer>> set =map.entrySet();
		Iterator<Map.Entry<String,Integer>> iter = set.iterator();
		
		Set<String>keySet = map.keySet();
//		while(iter.hasNext()) {
//			Map.Entry<String,Integer> me = iter.next();
//			System.out.println(me.getKey()+"="+me.getValue());
//		}
		for(String id:keySet) {
			System.out.println(map.get(id));
		}
		
	}

}
