package com.first;


interface Imessage{
	public abstract String message();
	public default boolean connect() {
		System.out.println("������Ϣͨ��");
		return true;
	}
	public static Imessage getInstance() {
		return new MessageImpl();
	}
}
class MessageImpl implements Imessage{
	public  String message() {
		if (this.connect())
		{
			return "ood";
		}
		return "bad";
	}
}
public class interfancetest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Imessage a = Imessage.getInstance();
		System.out.println(a.message());
	}

}
