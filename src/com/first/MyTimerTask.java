package com.first;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
 

public class MyTimerTask extends TimerTask {
	
	private static int i = 1;
    @Override
    public void run() {
    	
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time��"+format.format(calendar.getTime()));//��ȡ��ǰϵͳʱ��
        System.out.println("ִ�д���"+i);
        i = i + 1;
    }
    public static void main(String[] args) throws ParseException {
        MyTimerTask task = new MyTimerTask();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println(format.format(now));
        Date startDate = format.parse("2020-09-18 22:55:00");
        //calendar.add(Calendar.SECOND,3);//��ȡ���뵱ǰʱ��3����ʱ��
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,startDate,10000);
        int[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};
        Arrays.sort(a);
    }
}
