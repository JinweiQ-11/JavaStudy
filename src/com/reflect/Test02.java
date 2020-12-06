package com.reflect;

import java.lang.reflect.InvocationTargetException;

class Member{
    public Member(){
        System.out.println("无参数构造方法实例化对象！！！");
    }

    @Override
    public String toString() {
        return "努力学习java";
    }
}
public class Test02 {
    public static void main(String args[]) throws Exception {
        Class<?>clazz = Class.forName("com.reflect.Member");//获取Class类实例化对象
        Object obj = clazz.getDeclaredConstructor().newInstance();
        System.out.println(obj);
    }
}
