package com.first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TestStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> all = new ArrayList<String>();
		Collections.addAll(all, "Java","JavaScript","Jsp");
		Stream<String>stream = all.stream();
		
	}

}
