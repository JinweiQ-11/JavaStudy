package base;

public class ss {
    public static void main(String args[]){
        myss.say();
    }

}

class myss{
    static class myfirst {
        myfirst(){
            System.out.println("hihi");
        }
    }
    static void say(){
        System.out.println("method");
    }
}
