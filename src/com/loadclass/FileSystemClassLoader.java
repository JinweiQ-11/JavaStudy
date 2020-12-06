package com.loadclass;

import java.io.*;

public class FileSystemClassLoader extends  ClassLoader {
    private String rootDir;
    public FileSystemClassLoader(String rootDir){
        this.rootDir = rootDir;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if(c!=null){
            return c;
        }
        else{
            ClassLoader parent = this.getParent();
            try {
                c= parent.loadClass(name);
            } catch (ClassNotFoundException e) {
                //
            }
            if(c!=null){
                return c;
            }else{
                byte[] classData = new byte[1024];
                try {
                    classData = getClassData(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(classData==null){
                    throw  new ClassNotFoundException();
                }else{
                    c= defineClass(name,classData,0,classData.length);
                }
            }
        }
        return c;
    }
    private byte[] getClassData(String classname) throws IOException {//com.reflect.Person
        String path = rootDir + "/"+classname.replace('.','/')+".class";
        //IOUtils将流中的数据转为字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = new FileInputStream(path);
        byte[] buffer = new byte[1024];
        int temp = 0;
        while((temp = is.read(buffer))!=-1){
            baos.write(buffer,0,temp);

        }
        return baos.toByteArray();
    }

}
