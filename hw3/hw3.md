# JAVA and OOP

### 1.  Write up Example code to demonstrate the three fundamental concepts of OOP.
   1. Encapsulation;
   2. Polymorphism;
   3. Inheritance;  

**Encapsulation** is the concept of bundling data and methods that operate on that data within a single
unit, often a class, and restricting access to some components to protect the integrity of the object.

**Polymorphism** allows objects of different classes to be treated as objects of a common superclass, typically enabling
a single function or method to operate in different ways based on the object's class.

**Inheritance**  enables a new class to inherit attributes and methods from an existing class, promoting code reuse and
establishing a hierarchical relationship between classes.

```java
// Encapsulation: Using private fields and public methods to control access
class Animal {
    private String name; // private field, encapsulated within the class

    // Constructor
    public Animal(String name) {
        this.name = name;
    }

    // Public method to access private field
    public String getName() {
        return name;
    }

    // Polymorphism: This method can be overridden by subclasses
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

// Inheritance: Dog class inherits from Animal
class Dog extends Animal {

    // Constructor
    public Dog(String name) {
        super(name);
    }

    // Overriding the makeSound method to demonstrate polymorphism
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}


public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal("Generic Animal");
        Animal myDog = new Dog("Buddy"); // Polymorphism: myDog is an Animal reference pointing to a Dog object

        System.out.println(myAnimal.getName()); // Encapsulation: accessing the private name field via a method
        myAnimal.makeSound(); // Calls Animal's makeSound method

        System.out.println(myDog.getName()); // Encapsulation: accessing the private name field via a method
        myDog.makeSound(); // Polymorphism: Calls Dog's overridden makeSound method
    }
}
```  

### 2. What is wrapper class in Java and Why we need wrapper class?  

A wrapper class in Java is a class that encapsulates (wraps) a primitive data type (like int, char, boolean, etc.) 
in an object.  

We need Wrapper classes because  
1. Object Manipulation: Java collections (like ArrayList, HashMap) can only store objects, not primitive types. Wrapper 
classes allow primitives to be used in these collections by converting them into objects.  
2. Utility Methods: Wrapper classes provide utility methods for converting between types (e.g., Integer.parseInt(), 
Double.valueOf()) and performing operations (e.g., Integer.compare(), Character.isDigit()).
3. Nullability: Wrapper classes can be null, allowing you to represent the absence of a value, unlike primitives which
always have a default value.
4. Immutability: Wrapper classes are immutable; once created, their values cannot be changed, making them safer to use 
in multi-threaded environments.  

```java
import java.util.ArrayList;

public class WrapperExample {
    public static void main(String[] args) {
        // Autoboxing: Primitive int is automatically converted to Integer object
        Integer num = 10; 

        // Unboxing: Integer object is automatically converted back to primitive int
        int n = num; 

        // Using a wrapper class with a collection
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(20); // Autoboxing: 20 is automatically converted to an Integer object

        // Utility method of wrapper class
        int parsedNum = Integer.parseInt("123"); // Converts string to int using Integer class
        System.out.println("Parsed Number: " + parsedNum);
    }
}
```

### 3. What is the difference between HashMap and HashTable?  

**HashMap** is not synchronized, meaning it is not thread-safe. It allows one null key and multiple null values. HashMap
uses Iterator to iterate over keys and values. HashMap extends AbstractMap class.  
**HashTable** is synchronized and thread-safe, as all its methods are synchronized. This means only one thread can access
the Hashtable at a time, which makes it slower compared to HashMap in multi-threaded scenarios. Hashtable does not allow
nul keys or values. Attempting to add a null key or value will throw a NullPointerException. Hashtable uses Enumerator 
and Iterator for iteration. Hashtable extends Dictionary class.  

Note that HashMap is preferred in new codebases. Hashtable is considered a legacy class. `ConcurrentHashMap` can be
used instead.  

```java
        // HashMap Example
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 1);
        hashMap.put("Banana", 2);
        hashMap.put(null, 3); // Allowed in HashMap
        hashMap.put("Cherry", null); // Allowed in HashMap
        System.out.println("HashMap: " + hashMap);

        // Hashtable Example
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("Apple", 1);
        hashtable.put("Banana", 2);
        // hashtable.put(null, 3); // Throws NullPointerException
        // hashtable.put("Cherry", null); // Throws NullPointerException
        System.out.println("Hashtable: " + hashtable);

```

### 4. What is String pool in Java and why we need String pool? Explain String immunity.  

Strings in Java are specified as immutable because strings with the same content share storage in a single pool to minimize
creating a copy of the same value. Once a String is generated, its content cannot be changed and hence changing content will
lead to the creation of a new String. 

```java
        // Create a string object
        String original = "Hello";

        // Attempt to change the string by concatenating another string
        String modified = original.concat(", World!");

        // Print the original and modified strings
        // java string is immutable as we can see original is unchanged
        System.out.println("Original String: " + original); // Output: Hello
        System.out.println("Modified String: " + modified); // Output: Hello, World!

```

### 5. Explain garbage collection?  

**Garbage collection**  is an automatic memory management process that reclaims memory occupied by objects that are no 
longer in use, making it available for future allocations. Java developers do not directly control garbage collection, 
but they can influence it and observe its effects through coding practices and GC-related methods.
1. Marking. The first step in the process is called marking. This is where the garbage collector identifies which pieces
of memory are in use and which are not.  
2. Normal Deletion. Normal deletion removes unreferenced objects leaving referenced objects and pointers to free space.  
   3. Deletion with Compacting. To further improve performance, in addition to deleting unreferenced objects, you can also compact the remaining referenced objects. By moving referenced object 
   together, this makes new memory allocation much easier and faster.  

```java
public class GarbageCollectionDemo {

    // Constructor
    public GarbageCollectionDemo() {
        System.out.println("Object created");
    }

    // Override finalize method to see when an object is being garbage collected
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collector called and object is being removed");
        super.finalize(); // Call the finalize method of the superclass
    }

    public static void main(String[] args) {
        // Creating objects inside a block
        for (int i = 0; i < 5; i++) {
            GarbageCollectionDemo obj = new GarbageCollectionDemo();
        }

        // Suggest garbage collection to run
        System.gc(); // Request JVM to perform garbage collection
        
        // Adding a delay to observe the finalize output
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method");
    }
}
```

### 6. What are access modifiers and their scopes in Java?

**public**: Accessible from everywhere — within the class, other classes in the same package, and from classes in other packages.
**protected**: Accessible within the same package and by subclasses (child classes) in other packages.
**default**: Accessible only within the same package. No access modifier keyword is used (i.e., not public, protected, or private).
**private**: Accessible only within the class where it is declared. Not accessible from other classes, even within the same package.

```java
public class PublicExample {
    public int number = 10;  // Accessible from anywhere

    public void show() {     // Accessible from anywhere
        System.out.println("Public Method");
    }
}
class ProtectedExample {
    protected int protectedNumber = 5; // Accessible within the package and subclasses

    protected void showProtected() {   // Accessible within the package and subclasses
        System.out.println("Protected Method");
    }
}
class PrivateExample {
    private int secretNumber = 42; // Accessible only within this class

    private void displaySecret() {  // Accessible only within this class
        System.out.println("Private Method");
    }
}
class DefaultExample {
    int defaultNumber = 20; // Accessible only within the package

    void showDefault() {    // Accessible only within the package
        System.out.println("Default Method");
    }
}
```
### 7. Explain final key word? (Filed, Method, Class)  

**Variable**: define constants, make it immutable, prevent modification
```java
public static final String APP_NAME="testApp";
```  

**Method**: prevent override 
```java
public final int add(int a, int b){ return a + b; }
```  

**Class**: prevent inheritance, make class immutable
```java
public final class String
```

### 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?

**Static Vairable**: A static variable, also known as a class variable, is shared among all instances of the class. It belongs to the class rather than to any specific instance, so all
objects of the class share the same value of the static variable.

```java
class Counter {
    static int count = 0; // static variable

    Counter() {
        count++; // increment the static variable
    }

    void displayCount() {
        System.out.println("Count: " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        c1.displayCount(); // Output: Count: 3
        c2.displayCount(); // Output: Count: 3
        c3.displayCount(); // Output: Count: 3
    }
}
```

**Static Method**: Static methods belong to the class, not to any specific instance. They can be called without creating an instance of the class. Static methods can access other static variables and methods 
directly but cannot access instance (non-static) variables and methods.

```java
class MathUtility {
    static int square(int number) { // static method
        return number * number;
    }
}

public class Main {
    public static void main(String[] args) {
        // Calling the static method without creating an instance
        int result = MathUtility.square(5);
        System.out.println("Square of 5 is: " + result); // Output: Square of 5 is: 25
    }
}
```

**Static Class**: In Java, a class cannot be declared static on its own, but an inner class (nested class) can be static.
A static nested class does not have a reference to its outer class, meaning it cannot access the non-static members of
the outer class directly.  

```java
class OuterClass {
    int instanceVar = 10;

    static class StaticNestedClass { // static nested class
        void display() {
            System.out.println("This is a static nested class.");
            // Cannot access instanceVar directly because it's not static
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an instance of the static nested class
        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        nested.display(); // Output: This is a static nested class.
    }
}
```
### 9. What is the differences between overriding and overloading?

**Overloading** occurs when two or more methods in the same class (or subclass) have the same name but different parameters
(different type, number, or both). It is a compile-time concept and is used to improve code readability and reusability.  

```java
class Calculator {
    // Method to add two integers
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded method to add three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method with different parameter types
    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Sum of 2 integers: " + calc.add(10, 20));       // Output: 30
        System.out.println("Sum of 3 integers: " + calc.add(10, 20, 30));   // Output: 60
        System.out.println("Sum of 2 doubles: " + calc.add(10.5, 20.5));    // Output: 31.0
    }
}
```  

**Overriding** occurs when a subclass provides a specific implementation of a method that is already defined in its
superclass. It allows a subclass to provide its own version of a method to achieve runtime polymorphism.

```java
class Animal {
    // Method to be overridden
    void sound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    // Overriding the sound method in the subclass
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    // Overriding the sound method in the subclass
    @Override
    void sound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myDog.sound(); // Output: Bark
        myCat.sound(); // Output: Meow
    }
}
```
### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

A java method signature includes access levels, static (optional), final (optional), return type, method name, parameters
Overloading is same method name with different parameters or return types, or access levels; overriding has same
signature as superclass, it cannot have a more restrictive access level.  

```java
class Animal {
    // Method to be overridden
    protected void sound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    // Overriding the sound method in the subclass
    @Override
    public void sound() {   // ok
        System.out.println("Bark");
    }
    public void sound(int time) {
        for (int i = 0; i < time; i++) {
            System.out.println("Bark");
        }
    }
}
```
### 11. What is the differences between super and this?

**this** is used within an instance method or constructor of a class to refer to the current object (the object that 
is calling the method or constructor).  

**super** is used in a subclass to refer to the immediate parent class. It allows access to the parent class’s methods,
constructors, and instance variables.  

### 12. Explain how equals and hashCode work.  

**`equals()`** is used to compare two objects for equality  on their content rather than their memory addresses.  
**`hashCode()`** method returns an integer value, called a hash code, that represents the object. The hashCode() method is primarily used in hashing-based collections such as HashMap,
HashSet, and Hashtable to efficiently store and retrieve objects.

```java
class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    // Overriding hashCode to be consistent with equals
    @Override
    public int hashCode() {
        int result = name.hashCode(); // Get hash code of the name
        result = 31 * result + age;   // Combine it with age's hash using a multiplier (31 is a common choice)
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);

        System.out.println(p1.equals(p2));                // Output: true
        System.out.println(p1.hashCode() == p2.hashCode()); // Output: true
    }
}
```

### 13. What is the Java load sequence?


The [Java load sequence](https://blogs.oracle.com/javamagazine/post/how-the-jvm-locates-loads-and-runs-libraries) refers to the process by which Java classes and objects are loaded, initialized, and executed.  
1. finding the right class
2. verification, makes sure that class is not corrupted and is structurally correct
3. preparation, initializes static field to default value
4. resolution, checks that the symbolic references in the runtime constant pool actually point to valid classes of the required types.
5. initialization,  the static fields are initialized to whatever values are specified in the code.

### 14. What is Polymorphism ? And how Java implements it ?

**Polymorphism** allows objects of different classes to be treated as objects of a common superclass, typically enabling
a single function or method to operate in different ways based on the object's class.  

Java implements polymorphism through inheritance – either by building a hierarchy of classes with one common superclass
or by implementing a common interface for several classes.

### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?  

**Encapsulation** is the concept of bundling data and methods that operate on that data within a single
unit, often a class, and restricting access to some components to protect the integrity of the object.  We need encapsulation 
to manage objects with its attributes, methods, access levels, and also for abstraction. 

### 16. Compare interface and abstract class.  

Java abstract class cannot be instantiated; contains both abstract (without implementation) and concrete methods (with implementation) 
while interface specifies a set of methods a class must implement; methods are abstract by default.  

Abstract class can have both implemented and abstract methods, while interface methods are abstract by default. 

Class can only inherit from one abstract class, while a class can implement multiple interfaces.  

Access modifiers can be any in abstract class while interface is implicitly public in methods and properties.  

Variables can be final, non-final, static, non-static in abstract class, while interface variables are implicitly public, 
static, and final. 

### 17. Write a factory pattern in code.
the Factory Pattern creates objects without exposing the creation logic, providing a common interface for instantiating various types.
```java
// Shape.java
public interface Shape {
    void draw();
}
// Circle.java
public class Circle implements Shape {
   @Override
   public void draw() {
      System.out.println("Drawing a Circle");
   }
}

// Square.java
public class Square implements Shape {
   @Override
   public void draw() {
      System.out.println("Drawing a Square");
   }
}

// Rectangle.java
public class Rectangle implements Shape {
   @Override
   public void draw() {
      System.out.println("Drawing a Rectangle");
   }
}
// ShapeFactory.java
public class ShapeFactory {

   // Factory method to create objects based on the shape type
   public Shape getShape(String shapeType) {
      if (shapeType == null) {
         return null;
      }
      if (shapeType.equalsIgnoreCase("CIRCLE")) {
         return new Circle();
      } else if (shapeType.equalsIgnoreCase("SQUARE")) {
         return new Square();
      } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
         return new Rectangle();
      }
      return null;
   }
}
// FactoryPatternDemo.java
public class FactoryPatternDemo {
   public static void main(String[] args) {
      // Create a factory instance
      ShapeFactory shapeFactory = new ShapeFactory();

      // Get an object of Circle and call its draw method
      Shape shape1 = shapeFactory.getShape("CIRCLE");
      shape1.draw();  // Output: Drawing a Circle

      // Get an object of Square and call its draw method
      Shape shape2 = shapeFactory.getShape("SQUARE");
      shape2.draw();  // Output: Drawing a Square

      // Get an object of Rectangle and call its draw method
      Shape shape3 = shapeFactory.getShape("RECTANGLE");
      shape3.draw();  // Output: Drawing a Rectangle
   }
}
```

### 18. Write a adapter pattern in code.
The Adapter Pattern allows incompatible interfaces to work together by converting one interface into another expected
by the client
```java
// MediaPlayer.java
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
// AdvancedMediaPlayer.java
public interface AdvancedMediaPlayer {
   void playVlc(String fileName);
   void playMp4(String fileName);
}
// VlcPlayer.java
public class VlcPlayer implements AdvancedMediaPlayer {
   @Override
   public void playVlc(String fileName) {
      System.out.println("Playing VLC file. Name: " + fileName);
   }

   @Override
   public void playMp4(String fileName) {
      // Do nothing
   }
}

// Mp4Player.java
public class Mp4Player implements AdvancedMediaPlayer {
   @Override
   public void playVlc(String fileName) {
      // Do nothing
   }

   @Override
   public void playMp4(String fileName) {
      System.out.println("Playing MP4 file. Name: " + fileName);
   }
}
// MediaAdapter.java
public class MediaAdapter implements MediaPlayer {
   AdvancedMediaPlayer advancedMediaPlayer;

   public MediaAdapter(String audioType) {
      if (audioType.equalsIgnoreCase("vlc")) {
         advancedMediaPlayer = new VlcPlayer();
      } else if (audioType.equalsIgnoreCase("mp4")) {
         advancedMediaPlayer = new Mp4Player();
      }
   }

   @Override
   public void play(String audioType, String fileName) {
      if (audioType.equalsIgnoreCase("vlc")) {
         advancedMediaPlayer.playVlc(fileName);
      } else if (audioType.equalsIgnoreCase("mp4")) {
         advancedMediaPlayer.playMp4(fileName);
      }
   }
}
// AudioPlayer.java
public class AudioPlayer implements MediaPlayer {
   MediaAdapter mediaAdapter;

   @Override
   public void play(String audioType, String fileName) {
      // Built-in support to play mp3 music files
      if (audioType.equalsIgnoreCase("mp3")) {
         System.out.println("Playing MP3 file. Name: " + fileName);
      }
      // Use MediaAdapter to play other formats
      else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
         mediaAdapter = new MediaAdapter(audioType);
         mediaAdapter.play(audioType, fileName);
      } else {
         System.out.println("Invalid media. " + audioType + " format not supported");
      }
   }
}
// AdapterPatternDemo.java
public class AdapterPatternDemo {
   public static void main(String[] args) {
      AudioPlayer audioPlayer = new AudioPlayer();

      audioPlayer.play("mp3", "song.mp3");      // Output: Playing MP3 file. Name: song.mp3
      audioPlayer.play("mp4", "video.mp4");     // Output: Playing MP4 file. Name: video.mp4
      audioPlayer.play("vlc", "movie.vlc");     // Output: Playing VLC file. Name: movie.vlc
      audioPlayer.play("avi", "movie.avi");     // Output: Invalid media. avi format not supported
   }
}
```

### 19. Write singleton pattern in code, make sure your code is thread-safe.

```java
// Singleton.java
public class Singleton {
    // Volatile keyword ensures visibility of changes to variables across threads
    private static volatile Singleton instance;

    // Private constructor prevents instantiation from other classes
    private Singleton() {
        // Optional: Code to initialize the Singleton instance
    }

    // Public method to provide access to the instance with double-checked locking
    public static Singleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate the Singleton functionality
    public void showMessage() {
        System.out.println("Hello from the Singleton instance!");
    }
}
```
### 20. design a parking lot parking lot (put the code to codingQuestions/coding1 folder, )
    1. If you have no ability to design it, please find the solution in internet, then understand it, and re-type
       it.(Do NOT just copy and paste)  
Please see code section. 

### 21. What are Queue interface implementations and what are the differences and when to use what?

Queue, Deque, use deque when we need double-ended queue. 