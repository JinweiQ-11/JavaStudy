package com.reflect;


interface ImessageService{
    public void send();
}
interface  IChannelService{
    public boolean connect();
}
abstract class AbstractBase{}

class Mail extends AbstractBase implements ImessageService,IChannelService{


    @Override
    public void send() {
        if(this.connect()){
            System.out.println("fasong");
        }
    }

    @Override
    public boolean connect() {
        return true;
    }
}
public class test03  {
    public static void main(String args[]){
        Class<Mail>mail = Mail.class;
        Mail pp = new Mail();
        Package p =  mail.getPackage();
        System.out.println(pp.getClass());
        System.out.println(p.getName());
    }

}
