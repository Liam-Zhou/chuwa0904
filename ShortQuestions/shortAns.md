### encapsulation
- encapsulation restricts direct access to some of an object's components, protecting the integrity of the data by allowing controlled access through methods (getters and setters). This promotes data hiding and modularity.

```
public class Husband { 
    
   /*
    * Encapsulation:
    *
    */ 
   private int age ; 

   /*
    * setter()、getter() to access data
    */ 

   public int getAge() { 
       return age; 
   } 

    public void setAge(int age) { 
       this.age = age; 
   }
 } 


```

```
public class Main {
   public static void main(String[] args) {
       Husband husband = new Husband();
        husband.setAge(18);
        System.out.println("Husband's Age: " + husband.getAge());
   }
}

```

### polymorphism
- **Method overloading** supports compile-time polymorphism, where methods have the same name but different parameters.
- ***Method overriding*** enables runtime polymorphism, where a subclass redefines a method from its superclass. This provides flexibility and enhances code reusability.

```
        //run time polymorphsim:override
       husband.display();


       // compile time polymorphsim:overload
       Calculator cal = new Calculator();
       System.out.println(cal.add(1,2));//int
       System.out.println(cal.add(1.5,2.5));//double
```

Compile-time polymorphism(overload): diff signature(method type), diff parameter

```
public class Calculator {
   public int add(int a ,int b){
       return a+b;
   }
   public double add(double a ,double b){
       return a+b;
   }
}
```
runtime polymorphism(override): subclass provides its own method implementation).

```
public class Person {
   public void display(){
       System.out.println("Display person..");
   }
   //Static methods: These belong to the class, not instances, so they can be called without creating an object of the class.
   static void display(Person p){
       p.display();
   }


}
```
in Husband.java file

```
@Override
   public void display(){
       System.out.println("Display husband..");
   }
```

### inheritance

 where one class (called the subclass or child class) inherits the properties and behaviors (fields and methods) of another class (called the superclass or parent class). 

```
public class Person {
   public void display(){
       System.out.println("Display person..");
   }
   //Static methods: These belong to the class, not instances, so they can be called without creating an object of the class.
   static void display(Person p){
       p.display();
   }
}
```
```
public class Husband extends Person { 
    
   /*
    * Encapsulation:
    *
    */ 
   private int age ; 


    
   /*
    * setter()、getter() to access data
    */ 
   


   public int getAge() { 
       return age; 
   }
   public void setAge(int age) { 
       this.age = age; 
   }


   @Override
   public void display(){
       System.out.println("Display husband..");
   }
  
  
 } 

   ```
### what is wrapper class and why we need it
A wrapper class in Java is an object representation of primitive data types. Java provides a wrapper class for each primitive type, like Integer for int, Double for double, and so on. Wrapper classes are used when we need objects instead of primitive types, such as when working with collections like ArrayList that only accept objects.

```
import java.util.ArrayList;


public class WrapperExample {
    public static void main(String[] args) {
       // Autoboxing: Converting int to Integer
       int primitiveInt = 5;
       Integer wrapperInt = primitiveInt;  // Autoboxing


       // Unboxing: Converting Integer back to int
       int newPrimitiveInt = wrapperInt;  // Unboxing


       // Using wrapper class in a collection
       ArrayList<Integer> list = new ArrayList<>();
       list.add(wrapperInt);  // Adding Integer object to ArrayList


       System.out.println("Primitive int: " + primitiveInt);
       System.out.println("Wrapper Integer: " + wrapperInt);
       System.out.println("ArrayList value: " + list.get(0));
   }
}
```

### diff between hashmap and hashset
hashmap: Stores key-value pairs.Keys must be unique, but values can be duplicated.
Allows one null key and multiple null values.
```
import java.util.HashMap;


public class HashMapExample {
   public static void main(String[] args) {
       // Create a HashMap
       HashMap<Integer, String> map = new HashMap<>();
       map.put(1, "Alice");
       map.put(2, "Bob");
       map.put(3, "Charlie");


       // Retrieve value by key
       System.out.println("Key 2: " + map.get(2));  // Output: Bob


       // Display all key-value pairs
       for (Integer key : map.keySet()) {
           System.out.println("Key: " + key + ", Value: " + map.get(key));
       }
   }
}
```

hashset:Stores only unique elements (no key-value pairs)Does not allow duplicate elements.
Allows a single null element.
```
public class HashSetExample {
   public static void main(String[] args) {
       // Create a HashSet
       HashSet<String> set = new HashSet<>();
       set.add("Alice");
       set.add("Bob");
       set.add("Charlie");


       // Trying to add a duplicate element
       set.add("Alice");  // Won't add as it's a duplicate


       // Display all elements
       for (String name : set) {
           System.out.println("Name: " + name);
       }
   }
}
```
### what is string pool and why we need it, explain string immuable 

string pool is a special memory region in Java's heap where String literals are stored. When a string literal is created, Java first checks the string pool to see if that string already exists. If it does, the reference to the existing string is returned. If not, a new string object is created and added to the pool. 
This helps save memory because it avoids creating multiple string objects with the same value.

```
public class StringPoolExample {
   public static void main(String[] args) {
       // Using string literals, these will be stored in the String pool
       String str1 = "Hello";
       String str2 = "Hello";


       // Since both strings are the same literal, they point to the same object in the pool
       System.out.println(str1 == str2); // Output: true (same reference)


       // Creating a new string object using the 'new' keyword
       String str3 = new String("Hello");


       // Even though str3 has the same content, it doesn't point to the same object in the pool
       System.out.println(str1 == str3); // Output: false (different objects)


       // Interning str3, which forces it to refer to the string in the pool
       String str4 = str3.intern();
       System.out.println(str1 == str4); // Output: true (same reference now)
   }
}
```


In Java, strings are immutable, which means once a String object is created, its value cannot be changed. 
```
public class StringImmutabilityExample {
   public static void main(String[] args) {
       String original = "Hello";
      
       // Attempting to modify the string
       String modified = original.concat(", World!");


       // Original string remains unchanged
       System.out.println("Original: " + original);  // Output: Hello
       System.out.println("Modified: " + modified);  // Output: Hello, World!
   }
}
```
In this code, the original string remains unchanged after concat() is called because the String class is designed to be immutable. Therefore, a new string "Hello, World!" is created and assigned to modified, while original still references the unchanged string "Hello".

### explain garbage collection

Garbage Collection (GC) in Java is the process of automatically freeing up memory by finding and removing objects that are no longer needed by the program.
```
public class GarbageCollectionExample {
   public static void main(String[] args) {
       // Creating a new object of type Person
       Person2 person1 = new Person2("Alice");


       // person1 is no longer pointing to the Person object, so the object is eligible for GC
       person1 = null;


       // Creating another object
       Person2 person2 = new Person2("Bob");


       // Now person2 is pointing to a new Person object, so the previous object is eligible for GC
       person2 = new Person2("Charlie");


       // Suggesting GC (not guaranteed to happen immediately)
       System.gc();
   }
}
class Person2 {
   String name;


   Person2(String name) {
       this.name = name;
   }


   @Override
   protected void finalize() throws Throwable {
       System.out.println("Garbage collected for " + name);
   }
}
```

### what are access modifier and their scope in java
**Public**: Accessible everywhere.
Private: Accessible only within the same class.
(Private methods and fields cannot be accessed by subclasses or other classes in the same package.)

```
class Example {
    private int number;  // Accessible only within this class

    private void showNumber() {
        System.out.println(number);
    }
}
```
**Protected** Accessible within the same package and in subclasses.
```
class Parent {
    protected int number;  // Accessible within package and subclasses

    protected void showNumber() {
        System.out.println(number);
    }
}

class Child extends Parent {
    public void display() {
        showNumber();  // Accessible in subclass
    }
}
```


**Default**  Accessible within the same package but not outside

```
class Example {
    int number;  // No modifier means package-private

    void showNumber() {
        System.out.println(number);
    }
}

```

### explain final keyword(filed,method,class)

-  **final variable** is a constant, meaning its value cannot be changed after it is initialized. It must be assigned a value when it is declared or within the constructor (for instance variables).
```
class Example { 
    final int MAX_VALUE = 100; // Constant value 
        void showValue() { 
        // MAX_VALUE = 200; 
        // Error: cannot modify a final variable 
        System.out.println(MAX_VALUE); 
        } 
    }

```
-  **final method**
A final method cannot be overridden by subclasses. This is useful when you want to prevent subclasses from modifying the behavior of a method

```
class Parent {
    final void showMessage() {
        System.out.println("This is a final method.");
    }
}

class Child extends Parent {
    // void showMessage() {   // Error: cannot override the final method
    //     System.out.println("Cannot override final method.");
    // }
}
```

- **final class**
A final class cannot be subclassed or extended. This ensures that the class's implementation cannot be altered through inheritance.

```
final class FinalClass {
    void display() {
        System.out.println("This class cannot be extended.");
    }
}

// class SubClass extends FinalClass {  // Error: cannot inherit from final class
// }
```

### explain static keyword((filed,method,class))

- Static Field (Static Variable)
```
class StaticExample {
   static int count = 0;  // Static field, shared by all instances


   StaticExample() {
       count++;  // Incremented each time a new instance is created
   }


   void showCount() {
       System.out.println("Count: " + count);
   }
}
public class Test{
   public static void main(String[] args) {
       StaticExample obj1 = new StaticExample();
       StaticExample obj2 = new StaticExample();
       obj1.showCount();  // Output: Count: 2
       obj2.showCount();  // Output: Count: 2
   }
}
```

- Static Method
A static method belongs to the class rather than any specific instance, so it can be called without creating an instance of the class.

```
class MathUtility {
   static int square(int number) {
       return number * number;
   }
}


public class MathUtilityTest {
   public static void main(String[] args) {
       int result = MathUtility.square(5);  // No object creation needed
       System.out.println("Square of 5: " + result);  // Output: 25
   }
}
```
- Static Class
A static nested class is a class that is declared inside another class with the static keyword. It can be instantiated without needing an instance of the outer class
```
class OuterClass {
   static class StaticNestedClass {
       void display() {
           System.out.println("This is a static nested class.");
       }
   }
}


public class StaticNestedClassTest {
   public static void main(String[] args) {
       // No need to create an instance of OuterClass
       OuterClass.StaticNestedClass nestedObj = new OuterClass.StaticNestedClass();
       nestedObj.display();  // Output: This is a static nested class.
   }
}
```
### what is diff between overriding and overloading 

**Method overloading** supports compile-time polymorphism, where methods have the same name but different parameters. 
**Method overriding** enables runtime polymorphism, where a subclass redefines a method from its superclass. This provides flexibility and enhances code reusability.

### explain how java defines method signature and how it helps overriding and overloading


in Java, a method signature is defined by the method's name and parameter list (types, number, and order of parameters). It does not include the return type.

overloading: same name, different return type and diff paramter , override: same name and return type and parameter, diff implementation

### diff between super and this
**this**: Refers to the current class instance, used for accessing instance members and constructor chaining.
**super**: Refers to the parent class, used to access parent class members and invoke the parent class constructor
```
class Parent {
   int number = 50;


   void display() {
       System.out.println("Parent number: " + number);
   }
}


class Child extends Parent {
   int number = 100;


   void show() {
       System.out.println("Child number: " + this.number);  // Refers to Child's instance variable
       System.out.println("Parent number: " + super.number);  // Refers to Parent's instance variable
   }


   @Override
   void display() {
       super.display();  // Calls the display method of the Parent class
       System.out.println("Child display method");
   }
}
```

### explain eaquals and hashcode work

- equals() Method:Used to compare two objects for equality. By default, it checks if the two references point to the same object 


- hashCode() Method
hashCode() is used to generate a unique integer for an object, typically used in hash-based collections (like HashMap and HashSet).
    - If two objects are equal (per equals()), they must have the same hash code.
    - However, if two objects have the same hashCode(), they may or may not be equal according to equals() (hash collisions can occur).


### what is java load sequence

- The class is loaded by the ClassLoader when it is first referenced.
- (class level)Static variables and static blocks are initialized when the class is loaded.
- (object level)Instance variables are initialized when an object is created.

### what is polymorphism and how does java implement it

- Compile-time polymorphism through method overloading, where multiple methods have the same name but diff returntype, different parameter lists, diff parameter type.
- Runtime polymorphism through method overriding, where a subclass provides its own implementation of a method defined in its superclass.

### what is encapsulation and how java implement it and why we need it 

- what: 
    - encapsulation restricts direct access to some of an object's components, protecting the integrity of the data by allowing controlled access through methods (getters and setters). This promotes data hiding and modularity.
- how:
    - This is done by keeping the data private and providing public methods (getters and setters) to access and modify the data in a controlled manner.
### compare interface and abstract class

- interface: Used to define a contract for classes without any implementation, use implements keyword.Can implement multiple interface.

- abstract class:Used to define common behavior for related classes with some shared implementation, use extends keyword. Can only extend one class

### write a factory pattern in code

The Factory Pattern is a design pattern that provides a way to create objects without specifying the exact class to create. Instead, it lets the subclasses or specific classes choose which object to create. This helps keep the main code separate from the details of object creation, making it more flexible and easier to manage.
```
code in Question17 folder
```
### write a adapter pattern in code

The Adapter Pattern is a design pattern that helps two different types of interfaces work together. It works like a bridge by wrapping one object and making it compatible with another class, so they can communicate even if their interfaces don't match.

- MediaPlayer (Target Interface): The interface expected by the client.
- AdvancedMediaPlayer (Adaptee Interface): The existing interface with different functionality.
- MediaAdapter (Adapter Class): Acts as a bridge between MediaPlayer and AdvancedMediaPlayer.
- AudioPlayer (Client Class): Uses MediaAdapter to play formats that it doesn't natively support.

```

code in Question18 folder
```



### write singleton pattern in code make sure your code is thread safe

Thread safety means that a program functions correctly when multiple threads access shared resources.synchronized ensures thread safety by allowing only one thread at a time to execute critical sections of code, preventing race conditions and ensuring consistent and predictable results.

```
package Question19;


public class Singleton {
   private static Singleton instance;


   private Singleton(){
   }


   public static Singleton getInstance(){
       if(instance == null){//first check before locking
           synchronized(Singleton.class){
               if(instance == null){ //second check after locking
                   instance = new Singleton();
               }
           }
       }


       return instance;
   }


   public void DisplayMsg(){
       System.out.println("Msg from singletone class");
   }
}


class SingletonMain{
   public static void main(String[] args){
       Singleton s = Singleton.getInstance();
       s.DisplayMsg();


   }
}
```
### design a parkinglot, put in codingQuestion/coding1 folder

- Has a single level with a fixed number of parking spots.
Can park cars and bikes (with different space requirements).
Tracks parking spot availability.
```
 cd /Users/fionagu/Desktop/JavaOOP ; /usr/bin/env /Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/java --enable-preview -XX:+ShowCodeDetailsInExce
ptionMessages -cp /Users/fionagu/Library/Application\ Support/Code/User/workspaceStorage/c8834ab5f23c8c239553ec55ec4afced/redhat.java/jdt_ws/JavaOOP_c26882c3/bin Q
uestion20.ParkingMain 
spot parked with:PLATE123
spot parked with:PLATE456
Availble spot:3
Vehicle left PLATE456
Availble spot:4
```

### what are queue interface implementation and what are the diff and when to use what


| Imple    | When to use |
| -------- | --------    |
| LinkedList    | General-purpose queue with no specific performance needs.     |
| PriorityQueue    | When elements need to be dequeued by priority|
| ArrayDeque    | need fast, efficient insertions/removals at both ends.     |
| ConcurrentLinkedQueue	    | High-concurrency scenarios with non-blocking behavior.|
| ArrayBlockingQueue    | Thread-safe, bounded queue where producers block when full     |
| LinkedBlockingQueue   | Thread-safe, unbounded or bounded queue with optional capacity limits.|















