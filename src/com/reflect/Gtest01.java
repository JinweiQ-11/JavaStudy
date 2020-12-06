package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 加载类
 */
public class Gtest01 {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException {
        String path = "com.reflect.Person";
        Class clazz = Class.forName(path);
        System.out.println(clazz.hashCode());
        Class clazz2 = Class.forName(path);
        System.out.println(clazz2.hashCode());

        Class re = String.class;
        System.out.println(re);
        Class re1 = path.getClass();
        System.out.println(re1.hashCode());
        System.out.println(clazz.getName());
        //Field[] files = clazz.getFields();
        Field[] files = clazz.getDeclaredFields();
        System.out.println(files);
        for(Field temp:files){
            System.out.println(temp);
        }

//        Method[] methods = clazz.getDeclaredMethods();
//        for(Method temp:methods){
//            System.out.println(temp);
//        }

        Method m1 = clazz.getDeclaredMethod("getId",String.class);
        System.out.println(m1);

        Constructor[] constructors  = clazz.getDeclaredConstructors();//所有构造器
    }
}
