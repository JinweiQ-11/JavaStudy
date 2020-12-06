package com.first;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JavaIOdemo {
//	public static void main(String[] args) {
//		File file = new File("D:"+File.separator+"Javaexp");
//		listDir(file);
//		
//	}
//	public static void listDir(File file) {
//		if(file.isDirectory()) {
//			File results[] =file.listFiles(); 
//			if(results !=null) {
//				for(int x = 0; x < results.length; x++) {
//					listDir(results[x]);
//				}
//			}
//		}
//		System.out.println(file);
//	}
	public static void main(String[] args) throws IOException {
		File file = new File("D:"+File.separator+"Javaexp"+File.separator+"hello"+File.separator+"test.txt");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output = new FileOutputStream(file,true);
		String str = "hwenjing";
		output.write(str.getBytes());
		output.close();
	}
	
}
