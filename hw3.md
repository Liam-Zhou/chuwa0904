#### 1.

#### 2. What is wrapper class in Java and Why we need wrapper class?
A wrapper class is an object representation of a primitive data type. Java provides wrapper classes for each of the eight primitive types: int, char, float, double, boolean, byte, short, and long. The corresponding wrapper classes are Integer, Character, Float, Double, Boolean, Byte, Short, and Long.

Wrapper classes bridge the gap between primitive types and objects, allowing primitives to be used in places where objects are required, such as in Java’s collections framework. They also provide utility methods for type conversions and other operations, making them a valuable tool in Java programming

#### 3. What is the difference between HashMap and HashTable?
Hashtable is synchronized, making it thread-safe but slower, and it does not allow null keys or values
HashMap is not synchronized, making it faster in non-concurrent scenarios, and it permits one null key and multiple null values

#### 4. What is String pool in Java and why we need String pool? Explain String immunity
The String Pool in Java is a special memory area inside the Java Heap. When you create a String literal in Java, the JVM checks if the string already exists in the String Pool. If it does, the reference to the existing string is returned. If it doesn’t, the JVM adds the new string to the pool and returns a reference to it

**Memory Efficiency**: Strings are immutable in Java. If every String literal resulted in a new object, memory consumption would be much higher. The String Pool helps reduce the memory by reusing identical string literals
**Performance Improvement**: Reusing strings from the pool improves performance, particularly in cases where the same string is used multiple times. 

String immutability in Java means that once a String object is created, its value cannot be changed. If you try to modify a string, a new String object is created instead of altering the existing one

#### 5. Explain garbage collection?
Garbage collection in Java is the process by which the Java Virtual Machine (JVM) automatically identifies and removes objects that are no longer in use or referenced by the program. This frees up memory and helps prevent memory leaks, allowing the program to continue running efficiently without running out of memory

#### 6. What are access modifiers and their scopes in Java?
**Default** (no explicit modifier keyword) :declarations are visible only within the package (package private)
**Private**: declarations are visible within the class only
**Protected**: declarations are visible within the package or all subclasses
**Public**: declarations are visible everywhere

#### 7. Explain final key word? (Filed, Method, Class)
The final keyword can be used with classes, methods, and variables. A final class can't be subclassed, a final method can't be overridden, and a final variable's value can't be changed once assigned.

#### 8. Explain static keyword? (Filed, Method, Class). When do we usually use it?
**Static Field**: Shared across all instances of the class. Used for common data like counters or constants.
**Static Method**: Belongs to the class, can be called without creating an instance, and is used for utility or helper methods.
**Static Class**: A nested class that can be accessed without an instance of the outer class, often used for grouping related classes logically.
Using static helps in memory optimization and logically grouping data or behavior that doesn’t rely on the instance state of the class.

#### 9. What is the differences between overriding and overloading?
Method overloading is when multiple methods in the same class have the same name but different parameters. Method overriding is when a subclass provides a specific implementation for a method already defined in its superclass

#### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
A method **signature** in Java consists of the method's name and the parameter list.
**Overloading** relies on different method signatures (i.e., different parameter lists) within the same class, allowing methods with the same name to perform different tasks based on the arguments passed.
**Overriding** requires the same method signature in a subclass as in its superclass, enabling the subclass to provide a specific implementation of a method defined in the superclass. The method signature ensures that the subclass method is used when invoked on a subclass object

#### 11. What is the differences between super and this?
**this**: Refers to the current instance of the class. It is used for accessing instance variables and methods, constructor chaining within the same class, and resolving naming conflicts.
**super**: Refers to the parent class. It is used for accessing parent class methods and variables, invoking the parent class constructor, and resolving method overriding conflicts.

#### 12. Explain how equals and hashCode work.
**equals()** compares objects for logical equality. Override it when you want to compare objects based on their content, not just their memory address.
**hashCode()** provides a hash code used in hash-based collections. Override it along with equals() to ensure that equal objects have the same hash code.

#### 13. What is the Java load sequence?
**Class Loading**: The class is located and loaded into memory by the ClassLoader.
**Class Verification**: The bytecode is verified for correctness and security.
**Class Preparation**: Memory is allocated for class variables, and they are initialized to default values.
**Class Resolution**: Symbolic references in the class's constant pool are resolved.
**Class Initialization**: Static initializers and static fields are initialized.
**Object Instantiation**: Objects of the class are created, and instance initializers and constructors are executed.
**Execution of Main Method**: The main method is executed if it is defined in the class, starting the program.

#### 14. What is Polymorphism ? And how Java implements it?
**Polymorphism** in Java allows objects of different classes to be treated as objects of a common superclass. It is implemented through method **overloading** (compile-time polymorphism) and method **overriding** (runtime polymorphism). Method **overloading** provides multiple methods with the same name but different parameters, while method **overriding** allows subclasses to provide specific implementations of methods defined in their superclass.

#### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
**Encapsulation** in Java involves bundling data and methods into a class and restricting access to the class's internal data through private fields and public methods **(getters and setters)**. This approach protects the internal state of an object, provides controlled access, and improves flexibility, maintainability, and testing. By adhering to encapsulation principles, Java promotes a more **modular** and **robust** design, where implementation details are hidden, and only necessary interactions are exposed.

#### 16. Compare interface and abstract class.
**Purpose**:
**Interface**: Defines a contract for classes to implement. Ideal for defining capabilities.
**Abstract Class**: Provides a common base with shared functionality and state. Ideal for creating a common base with some shared implementation.

**Method Definitions**:
**Interface**: Can have abstract methods, default methods, and static methods.
**bstract Class**: Can have abstract methods, concrete methods, and can provide implementation.

**Fields**:
**Interface**: Can only have constants (public, static, final).
**Abstract Class**: Can have instance fields and various access modifiers.

**Multiple Inheritance**:
**Interface**: Supports multiple inheritance (a class can implement multiple interfaces).
**Abstract Class**: Supports single inheritance (a class can extend only one abstract class).

**Constructor**:
**Interface**: Cannot have constructors.
**Abstract Class**: Can have constructors and can be used by subclasses.

**Use Case**:
**Interface**: When you need to define a capability or contract that can be implemented by multiple classes.
**Abstract Class**: When you need a base class with shared behavior and state that other classes can extend.

#### 17. Write a factory pattern in code.
```
// Common interface
interface Shape {
    void draw();
}

// Concrete class
class Circle implements Shape {
    public void draw() { System.out.println("Circle::draw()"); }
}

// Factory class
class ShapeFactory {
    public Shape getShape(String shapeType) {
        if ("CIRCLE".equalsIgnoreCase(shapeType)) return new Circle();
        return null;
    }
}

// Client code
public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("CIRCLE");
        shape1.draw();  // Output: Circle::draw()
    }
}
```
#### 18. Write a adapter pattern in code.
```
// Common interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Advanced Media Players
class VlcPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }
}

class Mp4Player {
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}

// Adapter to make VlcPlayer and Mp4Player compatible with MediaPlayer
class MediaAdapter implements MediaPlayer {
    VlcPlayer vlcPlayer = new VlcPlayer();
    Mp4Player mp4Player = new Mp4Player();

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMp4(fileName);
        }
    }
}

// AudioPlayer uses the adapter
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter = new MediaAdapter();

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else {
            mediaAdapter.play(audioType, fileName);
        }
    }
}

// Demo
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "movie.vlc");
    }
}
```

#### 19. Write singleton pattern in code, make sure your code is thread-safe.
```
class Singleton {
    // Create a private static instance of the class
    private static Singleton singleObjectInstance;

    // Private constructor prevents instantiation from other classes
    private Singleton() {}

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (singleObjectInstance == null) {
            singleObjectInstance = new Singleton();
        }
        return singleObjectInstance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Demo class
public class SingletonPatternDemo {
    public static void main(String[] args) {
        // Get the only object available
        Singleton singleObject = Singleton.getInstance();

        // Show message
        singleObject.showMessage();
    }
}
```
#### 20. Parking lot design

#### 21. What are Queue interface implementations and what are the differences and when to use that?
**LinkedList**: General-purpose, supports operations at both ends. Use for a versatile queue.
**PriorityQueue**: Orders elements based on priority rather than insertion order. Use when priority-based processing is needed.
**ArrayDeque**: Efficient array-based queue with operations at both ends. Use for fast access and modifications.
**ConcurrentLinkedQueue**: Thread-safe, non-blocking queue for concurrent scenarios. Use in multi-threaded environments.
**LinkedBlockingQueue**: Blocking queue with thread-safe operations for producer-consumer problems. Use for blocking operations and synchronization.