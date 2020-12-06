package com.reflect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Mail03{
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean connect(){
        return true;
    }
    public  void send(){
        System.out.println("haah");
    }
}

public class TestMetgod {
    public static void main(String args[]) throws Exception{
        Class<?>cls = Mail03.class;
        Method[]  methos = cls.getMethods();
        for(Method met :methos){
            //System.out.println(met);
            int mod = met.getModifiers();
            //System.out.println(Modifier.toString(mod));
            System.out.println(met.getReturnType());
        }

        Mail03 obj = (Mail03) cls.getDeclaredConstructor().newInstance();
        Method m = cls.getDeclaredMethod("setName", String.class);
        m.invoke(obj,"hahahah");
        Method get = cls.getDeclaredMethod("getName");
        System.out.println(get.invoke(obj));
        //getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
        //getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
        Field filesd = cls.getDeclaredField("name");
        System.out.println(filesd.getType());
        System.out.println(String.class);
    }
}
