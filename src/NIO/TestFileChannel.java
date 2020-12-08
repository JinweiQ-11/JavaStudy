package NIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestFileChannel {
    public static void main(String args[]) throws IOException {
        String path =  System.getProperty("user.dir");
        File file = new File(path+File.separator+"src"+File.separator+"channel.txt");
        FileInputStream input = new FileInputStream(file);
        FileChannel channel = input.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int count = 0;
        while((count = channel.read(buffer))!=-1){
            buffer.flip();
            while(buffer.hasRemaining()){
                bos.write(buffer.get());
            }
            buffer.clear();
        }
        System.out.println(new String(bos.toByteArray()));
    }
}

