package com.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Mail01{
    private String msg;
    public Mail01(){

    }
    public  Mail01(String msg) {
        this.msg = msg;
        System.out.println("调用单参数构造器");
    }

    @Override
    public String toString() {
       System.out.println("重写toString方法");
       return msg;
    }
}
public class Test04 {
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> mail = Mail01.class;
        Constructor<?>[] constructor = mail.getDeclaredConstructors();
        for(Constructor<?>con:constructor){
            System.out.println(con);
        }
        Constructor<?> conp = mail.getDeclaredConstructor(String.class);
        Mail01 pp = (Mail01)conp.newInstance("www");
        System.out.println(pp);

    }


}
