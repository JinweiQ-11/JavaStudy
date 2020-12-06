package com.first;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Test1 {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String str = "1982-20-15";
		String regex = "\\d{4}-\\d{2}-\\d{2}";
		if(str.matches(regex)) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(str));
		}
		UUID uid = UUID.randomUUID();
		String result = uid.toString();
		String out =result.replace("-", "");
		System.out.println(out);
		
	}

}
