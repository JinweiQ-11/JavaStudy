package com.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Gtest02 {
    public static void main(String args[]) throws Exception  {
        String path = "com.reflect.Person";
        Class<Person> clazz = (Class<Person>)Class.forName(path);
        Person p  = clazz.newInstance();
        System.out.println(p);
        Constructor<Person> c =clazz.getDeclaredConstructor(String.class,String.class);
        Person p2 = c.newInstance("11","22");
        System.out.println(p2.getId());

        //反射api调用普通方法
        Person p3 = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("setName",String.class);
        method.invoke(p3,"12134");
        System.out.println(p3.getName());

        //操作属性
        Person p4 = clazz.newInstance();
        Field f = clazz.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p4,"q5");
        System.out.println(p4.getName());
    }
}
