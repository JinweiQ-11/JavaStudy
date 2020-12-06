package JUC;

import java.util.concurrent.atomic.AtomicReference;

class Member{
    private String name;
    private int age;
    public Member(String name,int age) {
        this.name = name ;
        this.age = age ;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class test05 {
    public static void main(String args[]){
        Member memA = new Member("mldn",12) ;					// 实例化Member实例
        Member memB = new Member("小李老师",18) ;
        AtomicReference<Member> ref = new AtomicReference<Member>(memA);
        ref.compareAndSet(memA, memB) ;						// 修改当前保存数据
        System.out.println(ref);								// 输出当前数据内容

    }

}
