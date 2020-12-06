package com.first;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Templates;

public class IOtest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("D:"+File.separator+"Javaexp"+File.separator+"hello"+File.separator+"test.txt");
		InputStream in =null;
		try {
			int temp;
			byte[] car = new byte[2];
			int len = -1;
			in = new FileInputStream(file);
			//while((temp=in.read())!=-1) {
			while((len=in.read(car))!=-1) {
				//System.out.println((char)temp);//һ��һ����ȡ
				//�ֶζ�ȡ
				String str = new String(car,0,len);
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(null!=in) {
				in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
