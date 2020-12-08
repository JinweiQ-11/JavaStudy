package NIO;

import java.io.*;

public class InputUtils {

    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new

            InputStreamReader(System.in));
    private  InputUtils(){}
    public  static  String  getString(String prompt) throws IOException {
        boolean flag = true;
        String str = null;

        while (flag){
            System.out.println(prompt);
            str = KEYBOARD_INPUT.readLine();//读取一行
            if(str ==null ||"".equals(str)){
                System.out.println("error");
            }else{
                flag = false;
            }

        }
        return  str;
    }

}
