package com.first;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class TestBufferInputStream {
	public static void main(String[] args) {
		File src = new File("D:"+File.separator+"Javaexp"+File.separator+"hello"+File.separator+"test.txt");
		InputStream is  = null;
		//BufferedInputStream bis = null;
		try {
			//is = new FileInputStream(src);
			is = new BufferedInputStream(new FileInputStream(src));
			byte[] flush = new byte[1024*10];
			int len=-1;	
			while((len=is.read(flush))!=-1) {
				String string = new String(flush,0,len);
				System.out.println(string);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null!=is) {
				is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
