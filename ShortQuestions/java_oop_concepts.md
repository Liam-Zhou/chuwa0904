# Java & OOP Concepts and Coding Examples

## 1. Fundamental OOP Concepts with Example Code:

### 1.1 Encapsulation:

Encapsulation is the concept of wrapping data (variables) and methods within a class and restricting direct access from outside.

```java
class Employee {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age.");
        }
    }
}
```

### 1.2 Polymorphism:

Polymorphism allows one action to be performed in different ways.

```java
// Compile-time Polymorphism (Method Overloading)
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

// Runtime Polymorphism (Method Overriding)
class Animal {
    public void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

### 1.3 Inheritance:

Inheritance allows one class to inherit the properties and behaviors of another class.

```java
class Animal {
    String name;

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

class Dog extends Animal {
    String breed;

    public void bark() {
        System.out.println(name + " is barking.");
    }
}
```

---

## 2. What is a Wrapper Class in Java, and Why Do We Need It?

A **wrapper class** in Java provides a way to use primitive data types (like `int`, `char`, etc.) as objects. Wrapper classes are needed when objects are required, such as in collections like `ArrayList`, which only accept objects.

Example:

```java
Integer intObj = Integer.valueOf(5); // Wrapping primitive int to Integer object
int primitive = intObj.intValue();   // Unwrapping Integer object to primitive int
```

---

## 3. Difference between HashMap and Hashtable:

- **HashMap**:

  - Not synchronized, not thread-safe.
  - Allows one `null` key and multiple `null` values.
- **Hashtable**:

  - Synchronized, thread-safe.
  - Does not allow `null` keys or values.

---

## 4. What is the String Pool in Java and Why Do We Need It?

A **String pool** is a memory area where Java stores string literals. When a string is created, Java first checks if the string already exists in the pool. If it does, it returns the reference to the existing string to save memory.

**String Immutability**: Strings are immutable in Java, meaning their value cannot be changed after they are created. This is crucial for security and thread safety.

---

## 5. Explain Garbage Collection:

**Garbage Collection** in Java is the process of automatically freeing memory by destroying objects that are no longer reachable or used in the program. It helps in efficient memory management and prevents memory leaks.

---

## 6. Access Modifiers and Their Scopes in Java:

- **public**: Accessible from anywhere.
- **protected**: Accessible within the same package and subclasses.
- **default (no modifier)**: Accessible only within the same package.
- **private**: Accessible only within the same class.

---

## 7. Explain the `final` Keyword:

- **Field**: A `final` field cannot be modified once initialized.
- **Method**: A `final` method cannot be overridden by subclasses.
- **Class**: A `final` class cannot be extended (inherited).

---

## 8. Explain the `static` Keyword:

- **Field**: A `static` field is shared among all instances of the class.
- **Method**: A `static` method belongs to the class rather than any instance.
- **Class**: `static` blocks are executed when the class is loaded.

**Usage**: Typically used when a resource or method should be shared across all objects.

---

## 9. Differences between Overriding and Overloading:

- **Overloading**: Methods with the same name but different parameter lists within the same class.
- **Overriding**: A subclass provides a specific implementation of a method defined in its superclass.

---

## 10. How Java Defines a Method Signature:

A **method signature** includes the method name and parameter types (order and number). Return type and method modifiers do not form part of the method signature. The method signature helps in differentiating overloaded methods.

---

## 11. Difference between `super` and `this`:

- **this**: Refers to the current class instance.
- **super**: Refers to the superclass and can be used to access methods and constructors of the parent class.

---

## 12. How `equals()` and `hashCode()` Work:

- **`equals()`**: Compares the contents of two objects for equality.
- **`hashCode()`**: Returns an integer representation of the object’s memory address. Objects that are equal must have the same `hashCode()`. Used in collections like `HashMap`.

---

## 13. Java Load Sequence:

1. **Static blocks** are executed first.
2. **Instance blocks** are executed before constructors.
3. **Constructors** are executed last to create objects.

---

## 14. What is Polymorphism and How Does Java Implement It?

Polymorphism is the ability of an object to take many forms. Java implements polymorphism via method overloading (compile-time) and method overriding (runtime).

---

## 15. What is Encapsulation and Why Do We Need It?

Encapsulation restricts access to data by making fields private and providing public getters and setters. It ensures data integrity and security by controlling how the data is accessed and modified.

---

## 16. Compare Interface and Abstract Class:

- **Interface**: All methods are abstract (before Java 8), and fields are implicitly `public static final`. Multiple interfaces can be implemented.
- **Abstract Class**: Can have both abstract and concrete methods. It cannot be instantiated but can be extended.

---

## 17. Factory Pattern Example:

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType.equals("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equals("RECTANGLE")) {
            return new Rectangle();
        }
        return null;
    }
}
```

---

## 18. Adapter Pattern Example:

```java
interface MediaPlayer {
    void play(String audioType, String fileName);
}

class AudioPlayer implements MediaPlayer {
    public void play(String audioType, String fileName) {
        if(audioType.equals("mp3")){
            System.out.println("Playing mp3 file: " + fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equals("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equals("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }

    public void play(String audioType, String fileName) {
        if (audioType.equals("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equals("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}
```

---

## 19. Singleton Pattern (Thread-Safe):

```java
public class Singleton {
    // Private constructor to prevent instantiation from outside the class
    private Singleton() {
    }
    static{
      ///do something
}
    // Static inner class to hold the Singleton instance
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
}
    // Public static method to get the Singleton instance
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
} }
```

---

## 20. Parking Lot Design:


---

## 21. Queue Interface Implementations:

Common implementations:

- **PriorityQueue**: Orders elements according to their natural ordering or a comparator.
- **LinkedList**: Implements a doubly linked list.
- **ArrayDeque**: Provides resizable array implementation of a deque.

Use **PriorityQueue** when ordering is required, and **LinkedList** or **ArrayDeque** for FIFO-based operations.
