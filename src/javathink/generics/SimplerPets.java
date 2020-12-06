package javathink.generics;//: generics/SimplerPets.java
import javathink.typeinfo.pets.*;
import java.util.*;
import javathink.net.mindview.util.*;

public class SimplerPets {
  public static void main(String[] args) {
    Map<Person, List<? extends Pet>> petPeople = New.map();
    // Rest of the code is the same...
  }
} ///:~
