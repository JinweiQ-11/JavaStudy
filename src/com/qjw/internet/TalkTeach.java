package com.qjw.internet;

/**
 * 多线程双向
 */
public class TalkTeach {
    public static void main(String args[]){
        new Thread(new TalkReceive(9999,"学生说")).start();
        new Thread(new TalkSend(7777,"localhost",8888)).start();
    }

}
