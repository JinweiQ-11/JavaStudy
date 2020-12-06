package com.qjw.internet;

/**
 * 多线程双向
 */
public class TalkStudent {
    public static void main(String args[]){
        new Thread(new TalkSend(5555,"localhost",9999)).start();
        new Thread(new TalkReceive(8888,"老师说")).start();
    }
}
