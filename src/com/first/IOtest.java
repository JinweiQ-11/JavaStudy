package com.first;

import java.io.File;
import java.io.IOException;

public class IOtest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file  = new File("D:"+File.separator+"Javaexp"+File.separator);
		if (file.isDirectory()) {
			File result[] = file.listFiles();
			for(File term:result) {
				System.out.println(term);
			}
		}
//		if (!file.getParentFile().exists()) {
//			file.getParentFile().mkdirs();
//		}
//		if(file.exists()) {
//			file.delete();
//		}else {
//			System.out.println(file.createNewFile());
//		}



		 
	}

}
