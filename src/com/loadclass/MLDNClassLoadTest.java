package com.loadclass;

import java.lang.reflect.Method;

public class MLDNClassLoadTest {
    public static void main(String[] args) throws Exception {
        MLDNClassLoader classLoader = new MLDNClassLoader() ;			// 实例化自定义类加载器
        Class<?> cls = classLoader.loadData("com.reflect.Person1") ; 	// 进行类的加载
        // 由于Message类并不在CLASSPATH之中，所以此时无法直接将对象转为Message类型，只能通过反射调用
        Object obj = cls.getDeclaredConstructor().newInstance() ;		// 实例化对象
        Method method = cls.getDeclaredMethod("send") ;				// 获取方法
        method.invoke(obj) ;											// 方法调用
    }

}
