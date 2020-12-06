package com.reflect;

public class Person1 {
    private String name;
    private String id;


    public Person1(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public Person1(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getId(String id) {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	public void send(){
		System.out.println("Hello");
	}
}
