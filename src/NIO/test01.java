package NIO;

import java.nio.ByteBuffer;

public class test01 {
    public static void main(String[] args) {
        String str = "www.mldm.cn";
        ByteBuffer buffer = ByteBuffer.allocate(20);
        System.out.println(buffer.capacity()+","+buffer.limit()+","+buffer.position());
        buffer.put(str.getBytes());
        byte[] a = str.getBytes();
        System.out.println(buffer.capacity()+","+buffer.limit()+","+buffer.position());
        buffer.flip();
        System.out.println(buffer.capacity()+","+buffer.limit()+","+buffer.position());
        while (buffer.hasRemaining()) { 					// 缓冲区中是否有数据
            System.out.print(buffer.get() + "、"); 		// 返回字节数据
        }
        System.out.println();								// 换行
        buffer.clear(); 									// 清空缓冲区
        System.out.println("【4】清空缓冲区：capacity = " + buffer.capacity()
                + "、limit = " + buffer.limit() + "、position = "	+ buffer.position());


    }
}
