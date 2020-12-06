package com.first;
interface Imassage{
	public static  final String INFO  = "www.qjw.cn";
	public abstract String getInfo();
}
interface Ichannel{
	public abstract boolean connect();
}
class message2 implements Imassage,Ichannel{
    /**
     *override
     */
	public String getInfo() {
		if(this.connect()) {
			return "qjw"+Imassage.INFO;
		}
		return null;
		
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return true;
	}
}
public class interfacetest {
    //array a = new array();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Imassage message = new message2();
		System.out.println(message.getInfo());
	}

}
