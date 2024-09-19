1. Write up Example code to demonstrate the three foundmental concepts of OOP.
    - Encapsulation;
    - Polymorphism;
    - Inheritance;
2. What is wrapper class in Java and Why we need wrapper class?
A wrapper class in Java is an object representation of a primitive data type. Java provides wrapper classes to treat primitive data types (like int, char, etc.) as objects.
Working with Collections: Java’s collections framework (like ArrayList, HashMap, etc.) cannot store primitive data types directly; they only work with objects. Wrapper classes enable the use of primitive types as objects in these collections.

3. What is the difference between HashMap and HashTable?

HashMap
- Not thread-safe: HashMap is not synchronized and can be used in a single-threaded environment or synchronized externally if required.
- Faster: Since HashMap is not synchronized, it generally performs better in a single-threaded environment compared to Hashtable.
- Allows null keys and values: A HashMap can have one null key and multiple null values.

HashTable
- Thread-safe: Hashtable is synchronized, meaning it can be safely used in a multi-threaded environment.
- Slower: Synchronization adds overhead, which makes Hashtable slower in comparison to HashMap in a single-threaded context.
- Does not allow null keys or values: Hashtable throws NullPointerException if any key or value is null.

4. What is String pool in Java and why we need String pool? Explain String immunity.

The String Pool (also known as the String Intern Pool) in Java is a special area in the Heap Memory where String literals are stored. The main idea behind the String Pool is to optimize memory usage by storing only one copy of each distinct String literal.

When you create a String using a literal, Java checks the String Pool to see if an identical String already exists. If it does, it returns the reference to the existing String in the pool, rather than creating a new one. If it doesn't, it adds the new String to the pool.

Why Do We Need the String Pool?
- Memory Optimization: The String Pool avoids creating duplicate String objects in memory, reducing memory consumption. Since Strings are immutable and frequently used in Java programs (e.g., in loops or method calls), the String Pool optimizes memory by storing only one instance of each literal.

- Performance Improvement: Since identical String literals reference the same object in memory, it improves performance when comparing Strings using the == operator, as it checks for reference equality (which is faster) rather than content equality.

- Thread Safety: Since Strings are immutable (cannot be changed after they are created), sharing String objects from the pool between multiple threads is safe. There is no risk of one thread modifying the String object for another thread.

String immutability means that once a String object is created, its value cannot be changed. If you try to modify a String, a new String object is created instead.

5. Explain garbage collection?
Garbage Collection (GC) in Java is the process of automatically reclaiming memory by identifying and disposing of objects that are no longer in use or have no references pointing to them.Garbage collection is handled by the Java Virtual Machine (JVM).

6. What are access modifiers and their scopes in Java?
In Java, access modifiers are keywords used to define the visibility and accessibility of classes, methods, constructors, and variables. They control where certain elements can be accessed from, providing a way to encapsulate and protect the data. Java has four main access modifiers:
    - public
    - private
    - protected
    - default (also called package-private)
public
Scope: The public modifier makes a class, method, or variable accessible from anywhere — within the same class, package, subclass, or even from outside packages.
private
Scope: The private modifier restricts access to the same class only. Other classes, even those in the same package, cannot access private members.
protected
Scope: The protected modifier allows access within the same package and subclasses (even if the subclass is in a different package).
default (package-private)
Scope: If no access modifier is specified, it is known as default or package-private access. It is accessible only within the same package but not outside the package.
7. Explain final key word? (Filed, Method, Class) The final keyword in Java is used to impose restrictions on classes, methods, and variables (fields). It can be applied to fields (variables), methods, and classes with different effects.
    1. Final Fields (Variables):
    When a variable is declared final, it means that once a value has been assigned to it, it cannot be changed (i.e., it becomes a constant).
    Final fields must be initialized at the time of declaration or within the constructor.
    2. Final Methods:
    A method declared as final cannot be overridden by subclasses.
    This is useful when you want to prevent subclasses from altering the behavior of a method.
    3. Final Classes:
    A class declared as final cannot be inherited by any other class. No subclass can be created from it.
    This is useful when you want to prevent inheritance and ensure that the behavior of the class cannot be modified.
8. Explan static keyword? (Filed, Method, Class). When do we usually use it?The static keyword in Java is used for memory management and allows elements (fields, methods, and nested classes) to belong to the class itself rather than to instances of the class. This means static elements are shared across all instances of the class. 
    1. Static Fields:
    A static field is shared by all instances of the class. There is only one copy of the static field for the entire class.
    It can be accessed using the class name, and it does not require an object instance.
    2. Static Methods:
    Static methods belong to the class rather than any instance. They can be called without creating an instance of the class.
    Static methods can only access static fields and cannot access instance variables directly.
    3. Static Classes (Nested Classes):
    In Java, you cannot declare a top-level class as static, but you can declare nested classes as static.
    A static nested class is associated with the outer class and can be accessed without creating an instance of the outer class.

9. What is the differences between overriding and overloading?
    - Method Overriding:
    Definition: Overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass.
    Purpose: To change or extend the behavior of a method in the subclass.
    Requirements:
    The method must have the same name, parameters, and return type as the method in the superclass.
    The method in the subclass cannot have a more restrictive access modifier.
    Runtime Polymorphism: Overriding supports runtime polymorphism, allowing objects of the superclass type to call methods of the subclass.

    - Method Overloading:
    Definition: Overloading occurs when two or more methods in the same class have the same name but different parameter lists (number, types, or both).
    Purpose: To add flexibility to a method with different argument variations or parameter types.
    Requirements:
    The method must have the same name but different parameter lists (either by changing the number or types of parameters).
    The return type can be different, but it doesn’t affect the overloading decision.
    Compile-Time Polymorphism: Overloading is resolved at compile time, meaning which method to call is determined at compile time.
10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
    In Java, a method signature is a combination of the method's name and its parameter list (the number, order, and types of parameters). It does not include the return type, throws clause, or access modifiers.

    Components of a Method Signature:
    Method Name: The name of the method.
    Parameter List: The number and types of the method’s parameters (including their order).

    Method Signature and Overloading
        Method overloading occurs when multiple methods in the same class share the same name but have different signatures (i.e., their parameter lists are different). Java distinguishes overloaded methods based on their signatures.

        How Signatures Enable Overloading:
        Java can differentiate between overloaded methods based on the method signature, allowing methods with the same name to perform different tasks based on the parameters passed.
        Return type does not affect method overloading; only the name and parameter list are considered for method signatures.

    Method Signature and Overriding
        Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. For overriding to happen, the method in the subclass must have the same signature as the method in the superclass.

        How Signatures Enable Overriding:
        In method overriding, the subclass method must have the exact same method signature (i.e., same method name and parameter list) as the method in the superclass.
        Java uses method signatures to ensure that the subclass correctly overrides the method, and during runtime, the appropriate method (superclass or subclass version) is called based on the actual object type (i.e., runtime polymorphism).
11. What is the differences between super and this?
Both super and this are keywords in Java used for referencing objects, but they serve different purposes.

this Keyword:
Refers to the Current Object:

this is used to refer to the current instance of the class (the object in which it is used).
Used for Disambiguation:

It is commonly used when field names are shadowed by parameters in a constructor or method. For example, this.field differentiates between the instance variable and the parameter with the same name.
Constructor Chaining:

this() can be used to call another constructor within the same class.

super Keyword:
Refers to the Parent Class:

super is used to refer to the parent class or superclass of the current object.
Access Superclass Methods and Fields:

It can be used to call methods or access fields of the superclass that have been hidden or overridden in the subclass.
Calling Superclass Constructors:

super() can be used to call the superclass constructor. If not explicitly called, Java automatically inserts super() (without parameters) in the constructor.

12. Explain how equals and hashCode work.

In Java, equals() and hashCode() are two fundamental methods that are essential when working with collections like HashSet, HashMap, and HashTable.

equals() Method:
The equals() method is used to compare two objects for equality. It checks whether two objects have the same values, not whether they reference the same object in memory.
By default, the equals() method from Object checks for reference equality (i.e., ==), but you can override it to check for logical equality.

hashCode() Method:
The hashCode() method returns an integer hash code for the object. The hash code is used to quickly locate objects in hash-based collections (e.g., HashMap, HashSet).
The general contract of hashCode() is:
If two objects are equal according to the equals() method, they must have the same hash code.
If two objects are not equal, they can have the same or different hash codes \(though having different hash codes improves performance in hash-based collections\)

13. What is the Java load sequence?

The Java class loading sequence refers to the order in which Java loads and initializes classes and their members (fields, methods, static blocks) before they can be used. The process involves three primary steps: Loading, Linking, and Initialization.

    1. Loading:
The class loader loads the .class file (bytecode) into memory. The class file is located using the classpath.
Once loaded, the class becomes a part of the JVM’s method area.
    2. Linking:
Verify: The bytecode is verified to ensure it conforms to the JVM specification and is valid (e.g., no illegal data or code structures).
Prepare: Static variables (fields) are assigned default values (e.g., 0 for int, null for objects).
Resolve: All symbolic references (e.g., class names, method names) are resolved into actual memory addresses.
    3. Initialization:
Static Initializers: Static fields and static blocks are executed in the order they are defined.
The class is fully initialized and ready for use after the static initialization.
Detailed Load Sequence for a Class:
Static Members Initialization:

Static fields are initialized with their defined values (if any).
Static blocks are executed in the order they appear.
Instance Initialization:

Instance fields are initialized with their default values (if any).
Constructor is called to create the object.

14. What is Polymorphism ? And how Java implements it ?
Polymorphism is one of the four fundamental principles of Object-Oriented Programming (OOP), and it refers to the ability of a variable, object, or function to take on multiple forms. Polymorphism allows one interface or method to be used for different types or objects. In Java, polymorphism can be achieved through two main types:

Types of Polymorphism in Java:
Compile-Time Polymorphism (Method Overloading):

Method overloading allows different methods to have the same name but with different parameter lists (different types, numbers, or order of parameters).
It is resolved at compile time, hence known as compile-time polymorphism.
Runtime Polymorphism (Method Overriding):

Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass.
It is resolved at runtime because the method to be executed is determined based on the object's actual type during execution.
Java implements runtime polymorphism through inheritance and interfaces.

15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
Encapsulation is the OOP concept of bundling data (variables) and methods that operate on that data within a single unit or class. It is also the practice of restricting direct access to some of an object's fields, which is accomplished through access modifiers (like private, protected, public). The main goal of encapsulation is to protect data integrity and maintain a clear separation between how an object works internally and how it is used externally.

How Java Implements Encapsulation:
Private Fields: In Java, fields (variables) are typically declared as private, which means they cannot be accessed directly from outside the class.
Public Getter and Setter Methods: To access or modify private fields, getter and setter methods are provided, allowing controlled access.
Access Modifiers: Java provides private, protected, and public access modifiers to define the level of access for each class member, allowing encapsulation.

Why Do We Need Encapsulation?
Data Protection: Encapsulation protects the internal state of an object from being directly modified by external code, reducing the risk of unintended changes or corruption.
Improved Maintainability: By hiding implementation details and exposing only necessary methods, changes to the internal workings of a class can be made without affecting other parts of the program.
Controlled Access: Getter and setter methods allow developers to control how variables are accessed and modified, enabling data validation, logging, or security measures.
Modularity: Encapsulation makes classes more modular, allowing them to be easily understood, reused, and tested in isolation.
16. Compare interface and abstract class.

Both interfaces and abstract classes in Java are used to achieve abstraction. 

17. Write a factory pattern in code.
```java
// Step 1: Create a common interface
interface Animal {
    void sound();
}

// Step 2: Create concrete classes implementing the same interface
class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat implements Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}

// Step 3: Create a Factory class to generate object of concrete classes
class AnimalFactory {
    // Use getAnimal() method to get object of type Animal
    public Animal getAnimal(String animalType) {
        if (animalType == null) {
            return null;
        }
        if (animalType.equalsIgnoreCase("Dog")) {
            return new Dog();
        } else if (animalType.equalsIgnoreCase("Cat")) {
            return new Cat();
        }
        return null;
    }
}

// Step 4: Use the Factory to get the object
public class Main {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();

        // Get an object of Dog and call its sound method
        Animal dog = animalFactory.getAnimal("Dog");
        dog.sound();

        // Get an object of Cat and call its sound method
        Animal cat = animalFactory.getAnimal("Cat");
        cat.sound();
    }
}
```
18. Write a adapter pattern in code.
```java
// Step 1: Create an interface representing the target
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Step 2: Create an existing class that needs to be adapted (incompatible interface)
class AdvancedMediaPlayer {
    public void playMp3(String fileName) {
        System.out.println("Playing mp3 file: " + fileName);
    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Step 3: Create an adapter class that implements the target interface and adapts the incompatible class
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        advancedMediaPlayer = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            advancedMediaPlayer.playMp3(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

// Step 4: Create a concrete class implementing MediaPlayer
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type");
        }
    }
}

// Step 5: Test the Adapter Pattern
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "song1.mp3");
        audioPlayer.play("mp4", "video1.mp4");
    }
}
```
19. Write singleton pattern in code, make sure your code is thread-safe.
The Singleton Pattern ensures that a class has only one instance and provides a global point of access to that instance. For thread safety, we need to ensure that the instance is created in a way that avoids issues in a multi-threaded environment.

```java
// Step 1: Create a Singleton class
public class Singleton {
    // Step 2: Declare a private static instance of the class
    private static volatile Singleton instance;

    // Step 3: Private constructor to prevent instantiation
    private Singleton() {
        // Prevent instantiation
    }

    // Step 4: Provide a public method to get the instance, using synchronized for thread safety
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();  // Lazy initialization
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Step 5: Test the Singleton Pattern
public class Main {
    public static void main(String[] args) {
        // Get the only object available
        Singleton singleton = Singleton.getInstance();
        
        // Show a message
        singleton.showMessage();
    }
}
```

How This Singleton is Thread-Safe:
Double-Checked Locking: The getInstance() method checks whether the instance is null before and after acquiring the lock to ensure that the instance is created only once.
Volatile Keyword: The volatile keyword ensures that changes to the instance variable are visible to all threads, preventing issues with instruction reordering.
Lazy Initialization: The instance is created only when it is first requested, saving memory until the instance is needed.

20. design a parking lot 


21. What are Queue interface implementations and what are the differences and when to use what? 

The Queue interface in Java is a part of the Java Collections Framework and represents a collection designed for holding elements prior to processing. It follows the First-In-First-Out (FIFO) order, though different implementations can alter this behavior.

Java provides several implementations of the Queue interface, each serving different purposes and having different characteristics. Let’s explore the main implementations and the differences between them, and when to use each.

1. LinkedList (Implements both List and Queue)
Characteristics:

Implements both the Queue and Deque interfaces, allowing it to function as a double-ended queue.
A doubly linked list where elements can be added or removed from both ends.
Operations like insertion and removal are efficient (O(1) for both).
FIFO Order: Follows FIFO when used as a queue.

Null Values: Allows null elements.

When to Use:

Use LinkedList when you need a simple, general-purpose queue or when you need both List and Queue behavior in one structure.
It is suitable when frequent insertion/removal is required, and ordering is important.

2. PriorityQueue
Characteristics:

A priority queue based on a heap (binary heap by default) where elements are ordered based on their natural ordering or a custom comparator.
It does not follow strict FIFO order but prioritizes elements based on their priority (smallest or highest based on comparator).
Operations such as insertion (offer) and removal (poll) have O(log n) time complexity.
Ordering: The head of the queue is always the least element in terms of priority, not necessarily the one that was added first.

Null Values: Does not allow null elements.

When to Use:

Use PriorityQueue when elements need to be processed based on priority rather than insertion order. Ideal for problems like task scheduling, event simulation, and pathfinding algorithms (e.g., Dijkstra’s algorithm).