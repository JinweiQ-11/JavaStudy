package com.qjw4;
/**
 * 
 * @author qjw
 *
 */

class Car{
	private String name;
	private double price;
	private Person10 person;
	public Car(String name,double price) {
		this.name=name;
		this.price=price;
	}
	public void setperson(Person10 person) {
		this.person=person;
	}
	public Person10 getperson() {
		return this.person;
	}
	public String getinfo() {
		return "Ʒ��"+this.name+"������ֵ"+this.price;
	}
}
class Person10{
	private String name;
	private int age;
	private Car car;
	public Person10(String name,int age) {
		this.name=name;
		this.age=age;
	}
	public void setcar(Car car) {
		this.car=car;
	}
	public Car getcar() {
		return this.car;
	}
	public String getinfo() {
		return "����"+this.name+"sui"+this.age;
	}
}
public class yinyong610 {
	public static void main(String args[]) {
		Person10 person=new Person10("hewnji",29);
		Car car=new Car("����",11111);
		person.setcar(car);
		car.setperson(person);
		System.out.println(person.getcar().getinfo());
	}
}
