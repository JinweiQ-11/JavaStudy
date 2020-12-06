package com.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

class Emp{
    private String ename;
    private String job;
    private Double salary;
    private Integer age;
    private Date hiredate;

    public String getEname() {
        return ename;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", hiredate=" + hiredate +
                '}';
    }
}
class StringUtils{
    public static String initcap(String str){
        if(str==null||"".equals(str)){
            return str;
        }if (str.length()==1){
            return str.toUpperCase();
        }
        else{
            return str.substring(0,1).toUpperCase() + str.substring(1);
        }
    }

}
class BeanUtils{
    private BeanUtils(){

    }
    public static void setValue(Object obj,String value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String results[] = value.split("\\|");
        for(int i = 0; i < results.length; i++){
            String attval[] = results[i].split(":");
            Field field = obj.getClass().getDeclaredField(attval[0]);
            Method setMethod = obj.getClass().getDeclaredMethod("set"+StringUtils.initcap(attval[0]),field.getType());
            Object convert = BeanUtils.convertAttributeValue(field.getType().getName(),attval[1]);
            //setMethod.invoke(obj,attval[1]);
            setMethod.invoke(obj,convert);
        }

    }

    //实现属性类型转换
    private static Object convertAttributeValue(String type, String value){
        if("long".equals(type)||"java.lang.Long".equals(type)){
            return Long.parseLong(value);
        }else if("int".equals(type)||"java.lang.Integer".equals(type)){
            return Integer.parseInt(value);
        }else if("double".equals(type)||"java.lang.Double".equals(type)){
            return Double.parseDouble(value);
        }else if("java.util.Date".equals(type)){
            SimpleDateFormat sdf  = null;
            if(value.matches("\\d{4}-\\d{2}-\\d{2}")){
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }else if(value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }else{
                return new Date();
            }
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                return new Date();
            }
        }
        else{
            return value;
        }
    }

}

class ClassInstanceFactory{
    private ClassInstanceFactory(){}
    public  static <T> T create(Class<T> clazz, String value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Object obj  = clazz.getDeclaredConstructor().newInstance();//调用无参数 实例化类
        BeanUtils.setValue(obj,value);//obj为实例化类
        return  (T)obj;
    }
}
public class SetValue {
    public static void main(String args[]) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
            //String value = "ename:qjw|job:it";
            String value = "ename:qjw|job:it|salary:10000|age:27|hiredate:2003-02-21";
            Emp p =  ClassInstanceFactory.create(Emp.class,value);
            //System.out.println(p.getEname());
            System.out.println(p);
    }
}
