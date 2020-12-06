package com.loadclass;

import java.io.ObjectOutput;
import java.io.OutputStream;

public class test01 {
    static {
        System.out.println("chushihuaTEST01");
    }
    public static void main(String args[]) throws ClassNotFoundException {

//        System.out.println("主函数");
//        A p = new A();
//        System.out.println(p.width);   //加载static
//        A p2 = new A();//只加载一次

        //主引用  调用static 块  加载 常量不主动引用
        //new A();//new A
       // System.out.println(A.width);//主引用 静态区
        //被动不会 常量
        System.out.println(A.aaa);
        //反射也会初始化 主动
        Class.forName("com.loadclass.A");
        //定义数组不会初始化
        A[] as = new A[10];
        //
        System.out.println(B.width);//不会初始化B;子类访问父类静态区 子类不会初始化
    }
}

class B extends  A{
    static {
        System.out.println("初始化B");
    }
}
class A extends  A_father{
    /**
    * 静态区赋值 块合并一起 形成类构造器。 类初始化阶段执行类构造器 <clinit>()方法的过程   线程安全
    *
    * */
    public static int width = 10;
    public static final int aaa = 10;
    static {
        System.out.println("初始化类A");
        width  = 2000;
    }
    public A(){
        System.out.println("创建类A");
    }
}

class A_father{
    static {
        System.out.println("父类");
    }
}