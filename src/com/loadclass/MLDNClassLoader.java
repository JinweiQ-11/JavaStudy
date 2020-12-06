package com.loadclass;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MLDNClassLoader extends ClassLoader {
    private static final String MESSAGE_CLASS_PATH = "C:\\Users\\QJW\\Desktop\\JavaStudy\\load\\com\\reflect\\Person1.class";
            //File.separator + "Message.class";			// 定义要加载的类文件完整路径
    /**
     * 进行指定类的加载操作
     * @param className 类的完整名称“包.类”
     * @return 返回一个指定类的Class对象
     * @throws Exception 如果类文件不存在则无法加载
     */
    public Class<?> loadData(String className) throws Exception {
        byte[] data = this.loadClassData(); 			// 读取二进制数据文件
        if (data != null) { 							// 读取到了
            return super.defineClass(className, data, 0, data.length);
        }
        return null;
    }
    private byte[] loadClassData() throws Exception { 	// 通过文件进行类的加载
        InputStream input = null;
        ByteArrayOutputStream bos = null; 				// 将数据加载到内存之中
        byte data[] = null;
        try {
            bos = new ByteArrayOutputStream(); 		// 实例化内存流
            input = new FileInputStream(new File(MESSAGE_CLASS_PATH)); // 文件流加载
            //input.transferTo(bos); 					// 读取数据
            data = bos.toByteArray(); 				// 字节数据取出
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();						// 关闭输入流
            }
            if (bos != null) {
                bos.close();							// 关闭内存流
            }
        }
        return data;
    }
}

