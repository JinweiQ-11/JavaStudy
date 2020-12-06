//: interfaces/interfaceprocessor/Apply.java
package javathink.interfaces.interfaceprocessor;
import static javathink.net.mindview.util.Print.*;

public class Apply {
  public static void process(Processor p, Object s) {
    print("Using Processor " + p.name());
    print(p.process(s));
  }
} ///:~
