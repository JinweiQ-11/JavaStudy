package com.first;

abstract class message{
	private String type;
	public abstract String getConnectInfo();
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
}
class DatabaseMessage extends message{
	@Override
	public String getConnectInfo() {
		return "sb";
	}
}
public class my {
	public static void main(String[] args) {
		message msg = new DatabaseMessage();
		System.out.println(msg.getConnectInfo());
	}

}
