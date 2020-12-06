package com.first;

import java.io.IOException;
import java.io.InputStream;

public class SystemIn {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        InputStream input = System.in;
        System.out.println("��������Ϣ��");
        byte[] data = new byte[1024];
        int len = input.read(data);
        System.out.println(new String(data,0,len));
	}

}
