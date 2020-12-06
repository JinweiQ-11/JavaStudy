package base;


class Shared{
    private int refcount = 0;
    private static long counter = 0;
    private final long id  = counter++;
    public Shared(){
        System.out.println("createing"+this);
    }
    public void addRef(){
        refcount++;
    }

}
public class ReferenceCount {
}
