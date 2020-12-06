package com.reflect;

public class Person {
    private String name;
    private String id;


    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public Person(){

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
}
