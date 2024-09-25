
# Homework 3: Java & OOP

Answer all questions with necessary code snippets.

## 1. Write up example code to demonstrate the three fundamental concepts of OOP.

1. **Encapsulation**
```
class Person{
   private String name;
   private int age;

   public Person(String name,int age){
      this.name=name;
      this.age=age;
   }

   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name=name;
   }

   public int getAge(){
      return age;
   }
}
```
2. **Polymorphism**
The class Person above has one more method
```
public int getAge(int add){
      return age+add;
   }
```
```
Person person = new Person("Coco",30);
person.getAge();
person.getAge(2000);
// these will call different methods
```
3. **Inheritance**
```
class Student extends Person{
   private int grade;

   public Student(String name, int age, int grade){
      super(name,age);
      this.grade=grade;
   }

   public getGrade(){
      return grade;
   }

   public setGrade(int grade){
      this.grade=grade;
   }
}
```

## 2. What is a wrapper class in Java and why do we need a wrapper class?
A wrapper class in Java is an object representation of a primitive data type. Java provides wrapper classes for all eight primitive types. 
e.g.  
int->Integer  
char->Character  
boolean->Boolean

Sometime we can only work with object(e.g. working with Collections). So we need wrapper class. Wrapper classes allow primitive types to behave as objects, providing more flexibility in Java programming.

## 3. What is the difference between HashMap and HashTable?
HashMap:  Not synchronized (not thread-safe). Multiple threads can access HashMap simultaneously, but external synchronization is required if used in multithreaded environments. Allow null.

HashTable: Synchronized (thread-safe). Only one thread can access a HashTable at a time, making it slower in a multithreaded environment. Not allow null.


## 4. What is the String pool in Java and why do we need a String pool? Explain String immutability.
The String Pool in Java is a special area in the heap memory that stores String literals. Whenever a String literal is created, the JVM checks the String pool to see if an identical String already exists. If it does, the reference to that existing String is returned, rather than creating a new object. If not, the new String is added to the pool. The String pool helps optimize memory usage because Strings are immutable and can be shared across different parts of the program.  

A String in Java is immutable, meaning once a String object is created, it cannot be changed. Any operation that appears to modify a String, such as concatenation or replacement, creates a new String object rather than modifying the original.


## 5. Explain garbage collection.
Garbage collection in Java is the process of automatically reclaiming memory that is no longer in use by a program. It is one of the key features of the Java runtime environment that helps manage memory efficiently and prevents memory leaks by cleaning up objects that are no longer reachable.

In Java, memory allocation and deallocation for objects are handled by the JVM (Java Virtual Machine), which includes a garbage collector that identifies and removes objects that are no longer needed by the application.

## 6. What are access modifiers and their scopes in Java?
`public`:  Anywhere
`private`:  Same Class
`protected`:  Same Class, Same Package, Subclass(Different Package)
Default: Same Class, Same Oackage.

## 7. Explain the `final` keyword (Field, Method, Class).
Simillar to the `constant` keyword in other language.  
The final keyword in Java is used to apply restrictions on classes, methods, and variables.  

When a variable is declared as final, its value can be assigned only once.  

When a method is declared as final, it cannot be overridden by subclasses.  

When a class is declared as final, it cannot be extended.




## 8. Explain the `static` keyword (Field, Method, Class). When do we usually use it?
When a member of a class is declared as static, it belongs to the class itself rather than to instances of the class. This means that static members can be accessed without creating an object of the class. 

A static field is shared among all instances of a class. It belongs to the class rather than to any individual object, meaning that all instances of the class share the same static variable.

A static method belongs to the class rather than to any object of the class. It can be called without creating an instance of the class. However, a static method cannot access non-static fields or methods directly because it is not tied to any particular object.

A static block is used to initialize static variables or execute code when the class is first loaded.

A static nested class is a nested class that is declared static`. It can be accessed without creating an instance of the enclosing class. However, a static nested class cannot access non-static members of the outer class

## 9. What is the difference between overriding and overloading?
Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. The method in the subclass must have the same name, return type, and parameters as the method in the superclass.

Overloading occurs when multiple methods in the same class (or sometimes in the subclass) share the same name but have different parameter lists (i.e., different number of parameters or different types of parameters).



## 10. Explain how Java defines a method signature, and how it helps with overloading and overriding.
In Java, a method signature is a combination of the method name and the parameter list (types and order of parameters) used to uniquely identify a method within a class. The return type, access modifier, and exceptions thrown are not considered part of the method signature.

Method Overloading allows multiple methods in the same class to have the same name but different parameter lists. The method signature is what allows Java to distinguish between these methods. When you call a method, the compiler looks at the method signature (name + parameter types) to determine which version of the method to invoke.


Method Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. For a method to be overridden, it must have the same method signature as the method in the parent class (same method name and parameter list). If the signature differs, it is considered an overloaded method, not an overridden one.

## 11. What is the difference between `super` and `this`?
The this keyword refers to the current object of the class in which it is used.

The super keyword refers to the parent class (superclass) object and is primarily used for accessing members (fields, methods, or constructors) of the superclass from within a subclass.

## 12. Explain how `equals` and `hashCode` work.
The equals() method is used to compare the contents of two objects to determine if they are "logically equal." The default implementation of equals() in the Object class simply compares the memory addresses of the two objects, meaning two different objects will not be considered equal unless they are the same instance.  
You typically override the equals() method to compare the contents of objects, not just their references. 

The hashCode() method returns an integer (the hash code) that represents the internal memory address of an object by default. It is used in conjunction with equals() in hash-based collections like HashMap, HashSet, etc., to efficiently locate and store objects.
When you override the equals() method, you must also override the hashCode() method to maintain consistency. 

## 13. What is the Java load sequence?
The class loading sequence (or Java load sequence) in Java describes the process by which a Java class is loaded into memory, initialized, and prepared for execution by the Java Virtual Machine (JVM). This process occurs in several stages, starting from loading the bytecode to the final instantiation of objects.

## 14. What is Polymorphism? And how does Java implement it?
Polymorphism in Java allows objects to take on multiple forms, enabling methods to behave differently based on the object being referenced or the parameters passed. Java implements polymorphism via method overloading (compile-time) and method overriding (runtime), along with support for polymorphism using interfaces and abstract classes. This flexibility allows for more reusable, maintainable, and scalable code.




## 15. What is Encapsulation? How does Java implement it? And why do we need encapsulation?
Encapsulation: It is the process of bundling data (fields) and methods that operate on the data into a single unit (class), while restricting direct access to some components of the object (data hiding) and allowing controlled access through methods.  
How Java Implements Encapsulation: Java implements encapsulation using access modifiers. Fields are typically declared as private, and public getter and setter methods are provided to access and modify the fields in a controlled manner.  
Why We Need Encapsulation:  
Data Security: It prevents unauthorized access to sensitive data.  
Data Integrity: Ensures data validity through validation logic in setters.  
Modularity and Flexibility: Internal changes do not affect external usage.  
Maintainability: Improves readability, maintainability, and reduces complexity.  
Reusability: Provides a clean interface for reuse in different contexts.


## 16. Compare interface and abstract class.
Interface: An interface in Java is a blueprint of a class that contains only abstract methods (before Java 8) or abstract methods along with default and static methods (in Java 8 and later). It is used to define a contract that implementing classes must follow.

Abstract Class: An abstract class can contain both abstract methods (methods without a body) and concrete methods (methods with an implementation). It can be partially implemented and is intended to be extended by subclasses.

Interface: Use an interface when you need to define a contract that multiple unrelated classes can implement. Interfaces are useful for achieving multiple inheritance and defining behaviors that a class should follow.

Abstract Class: Use an abstract class when you want to share code among related classes and when there is a clear relationship (is-a) between the classes. Abstract classes allow partial implementation and can include constructors and instance variables.


## 17. Write a factory pattern in code.
```
// Common interface for all animals
interface Animal {
    void speak();
}
```
```
// Dog class implementing Animal interface
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Dog says: Woof Woof");
    }
}

// Cat class implementing Animal interface
class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Cat says: Meow Meow");
    }
}

// Lion class implementing Animal interface
class Lion implements Animal {
    @Override
    public void speak() {
        System.out.println("Lion says: Roar");
    }
}
```
```
// Factory class to create objects of different animals
class AnimalFactory {
    // Method to create an animal based on input
    public static Animal createAnimal(String animalType) {
        if (animalType == null) {
            return null;
        }
        if (animalType.equalsIgnoreCase("DOG")) {
            return new Dog();
        } else if (animalType.equalsIgnoreCase("CAT")) {
            return new Cat();
        } else if (animalType.equalsIgnoreCase("LION")) {
            return new Lion();
        }
        return null;
    }
}
```
```
public class Main {
    public static void main(String[] args) {
        // Using the factory to get an object of Dog
        Animal dog = AnimalFactory.createAnimal("DOG");
        dog.speak();  // Output: Dog says: Woof Woof

        // Using the factory to get an object of Cat
        Animal cat = AnimalFactory.createAnimal("CAT");
        cat.speak();  // Output: Cat says: Meow Meow

        // Using the factory to get an object of Lion
        Animal lion = AnimalFactory.createAnimal("LION");
        lion.speak();  // Output: Lion says: Roar
    }
}
```

## 18. Write an adapter pattern in code.
```
// Target Interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}
```
```
// Concrete class implementing MediaPlayer interface (can play MP3)
class AudioPlayer implements MediaPlayer {

    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Play MP3 files directly
        if (audioType.equalsIgnoreCase("MP3")) {
            System.out.println("Playing MP3 file. Name: " + fileName);
        }
        // Use the MediaAdapter to play other formats
        else if (audioType.equalsIgnoreCase("MP4") || audioType.equalsIgnoreCase("VLC")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType + " format not supported.");
        }
    }
}
```
```
// Advanced Media Player Interface for different formats
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}
```
```
// Concrete class for playing VLC files
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing VLC file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

// Concrete class for playing MP4 files
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file. Name: " + fileName);
    }
}
```
```
// Adapter class that adapts AdvancedMediaPlayer to MediaPlayer
class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    // Constructor to choose the right player
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("VLC")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("MP4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("VLC")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("MP4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
```
```
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        // Playing MP3 file directly
        audioPlayer.play("MP3", "song1.mp3");

        // Playing MP4 file using adapter
        audioPlayer.play("MP4", "video1.mp4");

        // Playing VLC file using adapter
        audioPlayer.play("VLC", "movie1.vlc");

        // Trying an unsupported format
        audioPlayer.play("AVI", "clip.avi");
    }
}
```


## 19. Write a singleton pattern in code, making sure your code is thread-safe.
```
public class Singleton {
    // Volatile keyword ensures visibility of changes to variables across threads
    private static volatile Singleton instance = null;

    // Private constructor to prevent instantiation from other classes
    private Singleton() {
        // Initialize resources here
    }

    // Method to return the singleton instance with double-checked locking
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

## 20. Design a parking lot (put the code in the codingQuestions/coding1 folder).
   - If you have no ability to design it, please find the solution on the internet, understand it, and re-type it. (Do NOT just copy and paste)

Please refer to [Coding/HW3/ParkingLotDesign](../Coding/HW3/ParkingLotDesign/)

## 21. What are Queue interface implementations and what are the differences and when to use which?

 Queue interface is part of the java.util package and represents a collection designed for holding elements prior to processing. It is based on the FIFO (First In First Out) principle, but some implementations allow different ordering, such as priority-based or LIFO (Last In First Out).

There are several implementations of the Queue interface in Java, each designed for specific use cases. The main implementations are:

LinkedList  
PriorityQueue  
ArrayDeque  
ConcurrentLinkedQueue  
BlockingQueue (and its subtypes)  

Use LinkedList: When you need a simple, general-purpose queue and thread-safety is not a concern.  
Use PriorityQueue: When you need elements to be processed in a priority order, regardless of insertion order.  
Use ArrayDeque: When you need a fast, resizable queue or double-ended queue (deque) in a single-threaded environment.  
Use ConcurrentLinkedQueue: When you need a non-blocking, thread-safe queue for high-concurrency applications.  
Use LinkedBlockingQueue or ArrayBlockingQueue: In producer-consumer scenarios where threads need to block while waiting for space (bounded queues) or items (empty queues).  
Use PriorityBlockingQueue: When you need a priority queue with blocking behavior in a multi-threaded environment.