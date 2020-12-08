//: annotations/ExtractInterface.java
// APT-based annotation processing.
package annotations;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
  public String value();
} ///:~
