package com.loadclass;

public class testLod {
    public static void main(String args[]) throws ClassNotFoundException {
        FileSystemClassLoader loader = new FileSystemClassLoader("C:\\Users\\QJW\\Desktop\\JavaStudy\\load");
        //可以实现网络加载类  和 加密解密  结合流操作
        Class<?>c = loader.loadClass("com.reflect.Person1");
        System.out.println(c.hashCode());
        System.out.println(c.getClassLoader());
    }
}
