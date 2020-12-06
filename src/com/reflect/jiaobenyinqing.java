package com.reflect;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class jiaobenyinqing {
    public static void main(String args[]) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine  = sem.getEngineByName("JavaScript");
        engine.put("msg","gaoqi");
        String str = "var user = {name:'gaoqi',age:18};";
        str += "print(user.name)";
        engine.eval(str);
        engine.eval("msg = 'hhh';");
        System.out.println(engine.get("msg"));
        System.out.println("###################");

        //定义函数
        engine.eval("function add(a,b){var sum = a + b;return sum}");
        Invocable jsIn = (Invocable) engine;
        Object ss =  jsIn.invokeFunction("add",new Object[]{13,20});
        System.out.println(ss);

        String  string = "罗长1111";
        byte[] sb = string.getBytes();
        Object ob = sb.toString();
        System.out.println(ob);


    }
}
