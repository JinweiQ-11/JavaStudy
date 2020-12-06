package com.first;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import com.first.message2;
public class InputTest {
	message2 me = new message2();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("D:"+File.separator+"Javaexp"+File.separator+"hello"+File.separator+"test.txt");
		if(file.exists()) {
			InputStream input  = new FileInputStream(file);
			byte data [] = new byte [1024];
			int len  = input.read(data);
			System.out.println(len);
			String ss = new String(data,0,len);
			System.out.println(ss);
			String str = "www.chaina.www";
			InputStream inputm  = new ByteArrayInputStream(str.getBytes());
			OutputStream output = new ByteArrayOutputStream();
			int datam  = 0;
			
			while((datam = inputm.read())!=-1){
				System.out.println(datam);
				output.write(Character.toUpperCase(datam));
			}
			
			
		}
	}

}
