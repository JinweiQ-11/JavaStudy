package base;


interface  doWork{
    void doSomrthing();
}

class Read implements doWork{

    @Override
    public void doSomrthing() {
        System.out.println("i am reading book");
    }
}
class write implements doWork{

    @Override
    public void doSomrthing() {
        System.out.println("i am write book");
    }
}
class Mywork{
    private  doWork doo = null;
    public Mywork(doWork doo){
         this.doo = doo;
    }
    void domyworking(){
        doo.doSomrthing();
    }
}
public class TestBeanDi {
    public static void main(String[] args) {
        Mywork my = new Mywork(new Read());
        my.domyworking();
        my = new Mywork(new write());
        my.domyworking();
    }
}
