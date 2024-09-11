# Java&OOP
### 1. Write up Example code to demonstrate the three fundamental concepts of OOP.
- Encapsulation;
```java
class Employee {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
- Polymorphism
```java
class Developer extends Employee {
    @Override
    public String getName() {
        return "Developer: " + super.getName();
    }
}
public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Developer();
        emp1.setName("Jerry");
    }
}
```
- Inheritance
```java
class Manager extends Employee {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {  // Basic validation
            this.age = age;
        }
    }
}
```
### 2. What is wrapper class in Java and Why we need wrapper class?
In Java, a wrapper class is an object representation of a primitive data type.

The Java Collection Framework (like Map) only works with objects, not primitive types. Wrapper classes help store primitive data types as objects in collections.
### 3. What is the difference between HashMap and HashTable?
HashMap is not synchronized: It is not thread-safe. Multiple threads can access and modify a HashMap at the same time. HashTable is synchronized: It is thread-safe because only one thread can access a Hashtable at a time.
### 4. What is String pool in Java and why we need String pool? Explain String immunity.
- String pool is a special memory area within the Java heap that stores string literals. String Pool can reduce memory usage and improve performance by reusing immutable string objects.
- String immunity means strings cannot be modified after they are created.
### 5. Explain garbage collection?
Garbage collection is a memory management process to automatically reclaim memory from objects that are no longer in use.
### 6. What are access modifiers and their scopes in Java?
- Private: Accessible only within the class.
- Default: Accessible within the same package.
- Protected: Accessible within the same package and subclasses (even in different packages).
- Public: Accessible from everywhere.
### 7. Explain final keyword? (FieLd, Method, Class)
- Final Field: The variable becomes a constant; its value cannot be changed once assigned.
- Final Method: The method cannot be overridden by any subclass.
- Final Class: The class cannot be subclassed or extended.
### 8. Explain static keyword? (FieLd, Method, Class). When do we usually use it?
- Static Field: a variable that is shared among all instances of a class. It belongs to the class itself. Use static fields when you need shared data across all instances of a class
- Static Method: a method that belongs to the class. Use static methods for utility functions that don’t depend on instance variables
- Static Class: a class declared inside another class with the static keyword. Group related classes that do not need to access the outer class's instance data.
### 9. What is the differences between overriding and overloading?
- Overriding is about redefining a method from the superclass in the subclass to provide a specific implementation. It is related to runtime polymorphism and requires inheritance.
- Overloading is about defining multiple methods with the same name but different parameter lists within the same class. It is related to compile-time polymorphism.
### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
Signatures include different parameter types, number, or order.
- on Overloading: Same method name but different signature. Allows multiple methods with the same name in the same class.
- on Overriding: Same method signature as in the superclass. The subclass provides a specific implementation of the method.
### 11. What is the differences between super and this?
`this` refers to the current object and is used to access members (fields and methods) of the current class while `super` refers to the superclass and is used to access members of the parent class 
### 12. Explain how equals and hashCode work.
- `equals()` method in Java is used to compare two objects for equality. The default implementation in the Object class checks for reference equality.
- `hashCode()` method returns an integer representation of the object, which is used in hash-based collections such as HashMap
### 13. What is the Java load sequence?
Class Loading, Static Initialization, Object Creation (When an instance is created), Inheritance (If a class has a parent class)
### 14. What is Polymorphism ? And how Java implements it ?
Polymorphism allows methods or objects to behave differently based on the context or object type.
In java,
- Compile-time polymorphism (method overloading) occurs when methods have the same name but different signatures
- Runtime polymorphism (method overriding) occurs when a subclass overrides a method in the superclass
- interfaces. A class can implement multiple interfaces, and an interface reference can refer to any object of any class that implements that interface.
### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
Encapsulation is the process of bundling data and methods that manipulate that data into a single unit (class) and controlling access to that data using access modifiers and methods.

Java implements encapsulation by using private fields to hide data and providing public getter and setter methods to allow controlled access.

For protecting the internal state of an object, ensuring data integrity.
### 16. Compare interface and abstract class.
- abstract classes:  share methods and behavior between related classes, and enforce the class hierarchy (single inheritance).
- interfaces: define a common contract or behavior that can be applied to multiple, potentially unrelated classes. Use interfaces to achieve multiple inheritance
### 17. Write a factory pattern in code.
```java
interface Animal {
    void sound();
}

class Dog implements Animal {
    public void sound() { System.out.println("Dog barks"); }
}

class Cat implements Animal {
    public void sound() { System.out.println("Cat meows"); }
}

class AnimalFactory {
    public Animal createAnimal(String type) {
        return type.equalsIgnoreCase("dog") ? new Dog() : new Cat();
    }
}

```
### 18. Write an adaptor pattern in code.
```java
interface MediaPlayer { void play(String file); }

class AdvancedMediaPlayer {
    void playMp4(String file) { System.out.println("Playing MP4: " + file); }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer player = new AdvancedMediaPlayer();
    public void play(String file) { player.playMp4(file); }
}

```
### 19. Write singleton pattern in code, make sure your code is thread-safe.
```java
class Singleton {
    private static volatile Singleton instance;
    private Singleton() { }
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
```
### 20. Design a parking lot (put the code to codingQuestions/coding1 folder)
### 21. What are Queue interface implementations and what are the differences and when to use what? 
- LinkedList: Doubly-linked list. Use when you need a flexible queue that supports Deque operations or list-like behavior, such as accessing elements from both ends.
- PriorityQueue: Binary heap. Use when the order of processing is important, and you need to handle elements based on priority rather than insertion order.
- ArrayDeque: Resizable array-based deque (circular) Use when you need a fast, resizable queue that supports both FIFO and LIFO operations.