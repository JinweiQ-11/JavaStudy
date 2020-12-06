package base;


class Person111 {
    private  int s =10;
    public void say (){
        System.out.println();
    }
    private void say111(){};
}

class Person1234{
    //Person111 p = new Person111();

    public void say11 (){
        System.out.println();
    }
}
public class test01 {
    public static void main(String args[]){
        for(int i = 0,j = i+1;i < 5;i++,j  = i*2){
            System.out.println("i = "+i+"j = "+j);
        }
        int [] a;
        int c = 111;
        a = new int[c];
        System.out.println(a.length);
        printV(new Object[]{47,45,45});
        printV(new Object[]{new Person111(),45,45});
        Person111 p = new Person111();
        p.say();

        final int aaa = 1;

        System.out.println();
    }
    static void printV(Object[] args){

        for(Object obj:args){
            System.out.println(obj);
        }
    }
}
