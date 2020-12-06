package com.loadclass;

/**
 * 类加载器
 *
 * 1引导类加载器 原生代码实现 c++   不继承java.lang.classLoad 加载字节码 生成Class对象 加载核心库
 * 2扩展类加载器 classLoad子类
 * 3应用程序类加载器
 * 4自定义类加载器
 */
public class test02   {
    public static void main(String args[]){
        //双亲委托机制
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(System.getProperty("java.class.path"));
        //com.reflect
    }
}
