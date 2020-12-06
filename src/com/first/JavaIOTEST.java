package com.first;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class SendThread implements Runnable{
	private PipedOutputStream output;//�ܵ������
	public SendThread() {
		this.output = new PipedOutputStream();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.output.write("www.baidau.com".getBytes());
			this.output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public PipedOutputStream getOutput() {
		return output;
	}
}
class ReceiveThread implements Runnable{
	private PipedInputStream input;
	public ReceiveThread() {
		this.input = new PipedInputStream();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte data [] = new byte[3];
		int len = 0;
		ByteArrayOutputStream pos = new ByteArrayOutputStream();
		try {
			while((len = this.input.read(data))!=-1) {
			//len = this.input.read(data);
				System.out.println(len);
				pos.write(data,0,len);

				//pos.write(len);
			}
			System.out.println(pos);
			System.out.println(new String(pos.toByteArray()));
			pos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			this.input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PipedInputStream getInput() {
		return input;
		
	}
	
}
public class JavaIOTEST {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SendThread send = new SendThread();
		ReceiveThread receive = new ReceiveThread();
		send.getOutput().connect(receive.getInput());
		new Thread(send,"send").start();
		new Thread(receive,"receive").start();

	}

}
