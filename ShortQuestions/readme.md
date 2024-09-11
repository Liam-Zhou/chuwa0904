# HW3 Java & OOP homework

## 1. Write up Example code to demonstrate the three foundmental concepts of OOP

### 1.1 Encapsulation

```
class Bird {
    private int weight;
    private String name;

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### 1.2 Polymorphism

```
class Animal {
    public void sound() {
        System.out.println("This animal makes a sound.");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("The dog barks.");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("The cat meows.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();   //Where the Polymorphism comes
        Animal myCat = new Cat();

        myAnimal.sound();
        myDog.sound();
        myCat.sound();
    }
}
```

### 1.3 Inheritance

```
class Animal {
    public void eat() {
        System.out.println("This animal is eating.");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println("The dog is barking.");
    }
}
```

## 2. What is wrapper class in Java and Why we need wrapper class?

`Wrapper class` is object which can convert the primitive data types into Object. This gives more API and collections to manipulate and store primitive data, adding flexibility in coding.

## 3. What is the difference between HashMap and HashTable?

`HashMap` is not synchronized, allows one null key and multiple null values.  
`HashTable` is synchronized and doesn’t allow null keys or values.

## 4. What is String pool in Java and why we need String pool? Explain String immunity.

The `String pool` is a special memory area inside the Java heap where string literals are stored.  
`String pool` helps to optimize memory usage by storing only one instance of a string literal and sharing it among all references that point to that literal.  
String pool design the string in immunity, which means the string instance in the string pool cannot be modified, if the string is changed, it will create a new object in the string pool and the old unchanged one remains.

## 5. Explain garbage collection?

`Garbage collection` is when JVM find the object that are no longer in use, it will dispose the object and frees up memory. The process happens automatically.

## 6. What are access modifiers and their scopes in Java?

In Java, access modifiers are keywords used to set the visibility and accessibility of classes, methods, variables, and constructors.  
![alt text](./Picture/image1.png)

## 7. Explain final key word? (Filed, Method, Class)

Final Variable: variable that cannot be changed (same to const).  
Final Method: Method that cannot be overrided.  
Final Class: Class that cannot be inheritaed or modified.

## 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?

The `static` keyword in Java is used to indicate that a member (field, method, or nested class) belongs to the class itself rather than to instances of the class.  
`Static` keyword is usually used in the initialization or resources shared between different instance of the same class.

## 9. What is the differences between overriding and overloading?

`Overloading` has same method name in different parameter lists, whereas `overriding` has same method name and same parameters.  
`Overloading` happens in the same class/scope and `overriding` happends in the inheritance relationship.  
`Overloading` is done in the compile phase whereas `overriding` happends in the runtime.

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding?

Java `method signature` includes the method name, parameter types and parameter amount.  
Java allows method overloading as long as the method signatures differ in terms of the number, type, or order of parameters.
The overriding method must have the same method signature as the method in the superclass.

## 11. What is the differences between super and this?

`super` refers to the parent class whereas `this` refers to the current instance of the class.

## 12. Explain how equals and hashCode work?

The `equals()` method is used to check if two objects are logically equal. By default, the `equals()` method in the object class compares the reference of two objects.  
The `hashCode()` method returns an integer value, called the hash code, that represents the object. It can used in the hash-based colletions to quickly find the bucket where an object is stored.

## 13. What is the Java load sequence?

The `Java load sequence` refers to the order in which the Java Virtual Machine (JVM) loads, initializes, and executes various components of a class (including fields, methods, and blocks) when a Java program is run. This process involves class loading, initialization of static and instance variables, and constructor execution.

## 14. What is Polymorphism ? And how Java implements it ?

The term `polymorphism` means "many forms," and in programming, it refers to the ability of a single interface (such as a method, function, or object) to represent different types or behaviors. In simple terms, `polymorphism` allows one entity (such as a method or object) to take on multiple forms depending on the context in which it is used.  
In Java, this is typically achieved through method overloading (`static polymorphism`) and method overriding (`dynamic polymorphism`).

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?

It refers to the bundling of data (fields/attributes) and the methods (functions/operations) that operate on that data into a single unit, called a class.  
Java implements encapsulation using `Access Modifiers` and `Getter and Setter Methods`.  
`Encapsulation` increases data protection and security, improved maintainability and flexibility of the class, and added design pattern in practice.

## 16. Compare interface and abstract class?

`Interface` cannot have constructors, whereas `abstract class` can have constructors.  
`Interface` can implement multiple interfaces, whereas `abstract class` can only extend one abstract class.  
`Interface` can only have abstract methods, whereas `abstract class` can have both abstract and concrete methods.

## 17. Write a factory pattern in code.

```
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
```

## 18. Write a adapter pattern in code.

```
interface MediaPlayer {
    void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("avi", "unsupported.avi");
    }
}
```

## 19. Write singleton pattern in code, make sure your code is thread-safe.

```
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the singleton instance");
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## 20. Design a parking lot

See the java file in `../Coding/Main.java`

## 21. What are Queue interface implementations and what are the differences and when to use what?

1. Priority Queue: When you need to process elements based on priority rather than insertion order
2. Linked List: When you need a FIFO queue (standard queue behavior). And when you want fast insertions and deletions at both ends of the queue.
3. Arrary Deque: When you need a FIFO queue with better performance than `LinkedList` And when you need constant-time performance for insertions and deletions at both ends.
