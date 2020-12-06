package com.testurl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class urlTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com:80/index.html?");
        System.out.println("协议"+url.getProtocol());
        System.out.println("域名ip"+url.getHost());
        System.out.println("端口号"+url.getPort());
        System.out.println("请求资源"+url.getFile());


        URL url1 = new URL("https://www.jd.com");
        URL url2 = new URL("https://www.dianping.com");

        //下载资源
        InputStream is = url1.openStream();
        BufferedReader  br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        String msg = null;
        while(null!=(msg=br.readLine())){
            System.out.println(msg);
        }
        br.close();


        HttpURLConnection conn =  (HttpURLConnection)url2.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
        BufferedReader  br1 = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        String msg1 = null;
        while(null!=(msg1=br1.readLine())){
            System.out.println(msg1);
        }
        br1.close();
    }
}
