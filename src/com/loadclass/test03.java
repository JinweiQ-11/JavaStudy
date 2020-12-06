package com.loadclass;
class Member {}
public class test03 {
    public static void main(String[] args) throws Exception {
        Member member = new Member() ;
        System.out.println(member.getClass().getClassLoader());
        System.out.println(member.getClass().getClassLoader().getParent());
        System.out.println(member.getClass().getClassLoader().getParent().getParent());
    }
}

