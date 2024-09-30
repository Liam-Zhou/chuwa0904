# HW 3

Yuhang Li

# 1. Write up Example code to demonstrate the three foundmental concepts of OOP. 

### 1. **Encapsulation**
Encapsulation is the concept of bundling the data (variables) and methods that operate on the data within a class and restricting access to some of the object's components using access modifiers (like `private`, `public`, etc.).

```java
class Person {
    // Private variables to hide the internal state (Encapsulation)
    private String name;
    private int age;

    // Constructor to initialize the object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public getter and setter methods to access private variables
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
        if (age > 0) {  // Additional validation logic
            this.age = age;
        }
    }

    // Method to display person details
    public void displayPerson() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        // Encapsulation: We access the private variables through public methods
        Person person = new Person("John", 30);
        person.displayPerson();
        
        person.setAge(35);  // Modify age using the setter
        person.displayPerson();
    }
}
```

### 2. **Polymorphism**
Polymorphism allows one method to perform different tasks based on the object that is invoking it. There are two types of polymorphism: **Compile-time (method overloading)** and **Runtime (method overriding)**.

```java
// Compile-time polymorphism (Method Overloading)
class MathOperations {
    // Same method name, different parameter types (Overloading)
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

// Runtime polymorphism (Method Overriding)
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        // Compile-time polymorphism
        MathOperations math = new MathOperations();
        System.out.println("Sum (int): " + math.add(5, 3));
        System.out.println("Sum (double): " + math.add(5.5, 3.5));

        // Runtime polymorphism (Overriding)
        Animal animal = new Animal();
        Animal dog = new Dog();  // Dog is treated as an Animal type
        animal.sound();  // Calls the method in Animal
        dog.sound();     // Calls the overridden method in Dog (Runtime Polymorphism)
    }
}
```

### 3. **Inheritance**
Inheritance allows one class to inherit properties and methods from another class, promoting code reuse.

```java
// Parent class (Base class)
class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println(brand + " starts");
    }
}

// Child class (Derived class) inherits Vehicle class
class Car extends Vehicle {
    private int speed;

    public Car(String brand, int speed) {
        super(brand);  // Call the constructor of the Vehicle class
        this.speed = speed;
    }

    public void accelerate() {
        System.out.println(brand + " is accelerating at " + speed + " mph");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an instance of Car, which inherits from Vehicle
        Car myCar = new Car("Tesla", 100);
        myCar.start();        // Inherited method
        myCar.accelerate();   // Specific method of Car
    }
}
```

### Summary of OOP Concepts:

1. **Encapsulation**: Wrapping data and methods in a class and controlling access via getters and setters.
2. **Polymorphism**: Ability to use a method in different ways (method overloading and overriding).
3. **Inheritance**: Allowing a class to inherit properties and methods from a parent class, promoting code reuse.



# 2. What is **wrapper class **in Java and Why we need wrapper class?

In Java, **wrapper classes** are classes that provide a way to use primitive data types (like `int`, `char`, `boolean`, etc.) as objects. Each of the eight primitive types has a corresponding wrapper class in the `java.lang` package.

### Primitive Types and Their Wrapper Classes:
| **Primitive Type** | **Wrapper Class** |
| ------------------ | ----------------- |
| `int`              | `Integer`         |
| `char`             | `Character`       |
| `boolean`          | `Boolean`         |
| `byte`             | `Byte`            |
| `short`            | `Short`           |
| `long`             | `Long`            |
| `float`            | `Float`           |
| `double`           | `Double`          |

### Why Do We Need Wrapper Classes?

1. **Working with Collections (like ArrayList)**
   Java’s generic collections, such as `ArrayList`, cannot store primitive types directly. They only accept objects. Wrapper classes allow us to store primitive types in collections like `ArrayList`:
   ```java
   ArrayList<Integer> numbers = new ArrayList<>();
   numbers.add(10);  // Autoboxing converts primitive int to Integer object
   ```

2. **Autoboxing and Unboxing**
   Java automatically converts between primitive types and their corresponding wrapper classes, known as **autoboxing** (from primitive to wrapper) and **unboxing** (from wrapper to primitive):
   ```java
   Integer x = 5;      // Autoboxing (int to Integer)
   int y = x;          // Unboxing (Integer to int)
   ```

3. **Object Methods for Primitives**
   Wrapper classes provide useful methods that allow us to work with primitive types in ways that aren’t available for the raw primitives. For example, parsing strings into numbers:
   ```java
   int number = Integer.parseInt("123");  // Convert String to int
   ```

4. **Immutability**
   Wrapper classes are **immutable**, meaning their value cannot be changed once they are created. This is useful when you need to ensure the data should not be modified.

5. **Type Safety**
   When working with generics or APIs that require object types (such as the reflection API), wrapper classes ensure type safety by converting primitive types into objects.

6. **Nullable Values**
   Wrapper classes can hold `null` values, while primitive types cannot. This can be helpful in cases where you need to represent the absence of a value:
   ```java
   Integer x = null;  // valid
   int y = null;      // compilation error (primitives cannot be null)
   ```

### Example:

```java
public class WrapperExample {
    public static void main(String[] args) {
        // Autoboxing: automatically converting int to Integer
        Integer intObject = 10;
        
        // Unboxing: converting Integer back to int
        int primitiveInt = intObject;

        // Using a method from the wrapper class
        String numberString = "123";
        int parsedInt = Integer.parseInt(numberString);
        
        System.out.println("Parsed Integer: " + parsedInt);
    }
}
```

### Summary
**Wrapper classes** allow Java’s primitive types to be used in contexts that require objects, such as working with collections, using utility methods, handling `null` values, and ensuring compatibility with Java's object-oriented features like generics and reflection.

# 3. What is the difference between **HashMap** and **HashTable**?

The key differences between **HashMap** and **Hashtable** in Java relate to synchronization, null values, performance, and legacy status. Here's a detailed comparison:

### 1. **Synchronization**
- **HashMap**: Not synchronized, meaning it is not thread-safe. Multiple threads can access and modify a `HashMap` concurrently, which can lead to inconsistent results. If you need thread safety, you must explicitly synchronize it (e.g., using `Collections.synchronizedMap()`).
  
- **Hashtable**: Synchronized, meaning it is thread-safe. Every method in `Hashtable` is synchronized, so only one thread can access it at a time. However, this can lead to performance issues in multithreaded environments due to the overhead of synchronization.

### 2. **Null Keys and Values**
- **HashMap**: Allows one `null` key and multiple `null` values. It’s more flexible when working with `null` keys and values.
  ```java
  HashMap<String, String> map = new HashMap<>();
  map.put(null, "value1");  // Allowed
  map.put("key1", null);    // Allowed
  ```
  
- **Hashtable**: Does **not allow `null` keys or values**. If you try to insert a `null` key or value, it will throw a `NullPointerException`.
  ```java
  Hashtable<String, String> table = new Hashtable<>();
  table.put(null, "value1");  // Throws NullPointerException
  table.put("key1", null);    // Throws NullPointerException
  ```

### 3. **Performance**
- **HashMap**: Generally faster because it is not synchronized. In a single-threaded environment, `HashMap` performs better than `Hashtable` because there is no synchronization overhead.
  
- **Hashtable**: Slower due to synchronization on every method. This synchronization overhead makes it less performant compared to `HashMap` in non-concurrent scenarios.

### 4. **Legacy**
- **HashMap**: Part of the Java **Collections Framework** (introduced in Java 1.2). It is the more modern and preferred implementation for key-value mappings in most cases.
  
- **Hashtable**: A **legacy** class (introduced in Java 1.0), predating the Java Collections Framework. It has largely been replaced by `HashMap` for most use cases, but it is retained for backward compatibility.

### 5. **Iterator Type**
- **HashMap**: Uses the **fail-fast iterator**. This means that if the map is modified while iterating (except through the iterator's own `remove()` method), the iterator will throw a `ConcurrentModificationException`.
  
- **Hashtable**: Uses an **enumerator** which does not have fail-fast behavior. It won’t throw `ConcurrentModificationException` while iterating, but this can lead to inconsistent or unpredictable behavior.

### 6. **Inheritance**
- **HashMap**: Extends the `AbstractMap` class and implements the `Map` interface. It is part of the Java Collections Framework.
  
- **Hashtable**: Extends the `Dictionary` class, which is a legacy class in Java. It also implements the `Map` interface, but it's not part of the core Java Collections Framework.

### 7. **Example of Usage**

#### HashMap Example:
```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put(null, "value3");  // Allows null key
        map.put("key3", null);    // Allows null value
        
        System.out.println(map);
    }
}
```

#### Hashtable Example:
```java
import java.util.Hashtable;

public class HashtableExample {
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("key1", "value1");
        table.put("key2", "value2");
        // table.put(null, "value3");  // Throws NullPointerException
        // table.put("key3", null);    // Throws NullPointerException
        
        System.out.println(table);
    }
}
```

### Summary of Differences:

| **Feature**          | **HashMap**                                  | **Hashtable**                          |
| -------------------- | -------------------------------------------- | -------------------------------------- |
| **Thread-Safety**    | Not synchronized (Not thread-safe)           | Synchronized (Thread-safe)             |
| **Null Keys/Values** | Allows one null key and multiple null values | Does not allow null keys or values     |
| **Performance**      | Faster (in non-concurrent scenarios)         | Slower due to synchronization overhead |
| **Legacy**           | Part of Java Collections Framework (modern)  | Legacy class (older)                   |
| **Iterator Type**    | Fail-fast iterator                           | Not fail-fast (Enumeration)            |
| **Inheritance**      | Extends `AbstractMap`                        | Extends `Dictionary`                   |

In general, **`HashMap` is preferred** over `Hashtable` in modern Java programming unless you specifically need the thread-safe behavior provided by `Hashtable`. If thread safety is required, alternatives like `ConcurrentHashMap` are usually a better choice.

# 4. What is **String pool** in Java and why we need Stringpool? Explain String immunity.

### **What is the String Pool in Java?**

The **String pool** (or **interned string pool**) in Java is a special memory area in the **heap** that stores **string literals**. The Java String pool helps optimize memory usage by storing only one copy of each distinct string literal, which can be referenced by multiple variables. This is part of Java's internal optimizations for `String` objects.

When you create a string literal, like `"Hello"`:
```java
String s1 = "Hello";
String s2 = "Hello";
```
Both `s1` and `s2` point to the same object in the String pool, rather than creating two separate objects.

### **Why Do We Need the String Pool?**
The main reason for the String pool is **memory optimization**:
1. **Memory Efficiency**: Instead of creating a new object every time a string is used, the JVM reuses the same literal string from the pool. This is particularly useful because strings are immutable and used frequently in Java applications.
2. **Performance**: By reducing the number of duplicate `String` objects, the String pool enhances performance by saving memory and making string comparisons faster.

### **How the String Pool Works**:
- **String Literals**: When you declare a string using a literal (e.g., `String s = "Hello";`), Java checks the string pool. If the literal already exists in the pool, it returns a reference to the existing string. If not, it adds the literal to the pool and returns a reference to it.
  
- **`new` Keyword**: When you create a string using the `new` keyword (e.g., `String s = new String("Hello");`), it forces the creation of a new `String` object in the heap, **outside** the string pool, even if an equivalent string exists in the pool.
  
  ```java
  String s1 = "Hello";            // Refers to string pool
  String s2 = new String("Hello"); // Refers to a new object in the heap
  ```

- **Interning**: You can manually add strings to the pool using the `intern()` method:
  ```java
  String s1 = new String("Hello").intern();  // Adds "Hello" to the pool
  ```

### **String Immutability**

**String immutability** means that once a `String` object is created, its value cannot be changed. Every time you modify a string, Java creates a new `String` object instead of modifying the original one.

#### Why Is String Immutable?
1. **Security**: Strings are used in many sensitive areas such as file paths, network connections, etc. If strings were mutable, changing their content would pose security risks.
  
2. **Thread-Safety**: Since strings are immutable, multiple threads can safely share and reference the same `String` object without the risk of one thread modifying the string and affecting the others.
  
3. **String Pooling**: Immutability makes string pooling possible. If strings were mutable, altering one string would affect all other references to the same string, making pooling inefficient and error-prone.

4. **Caching Hashcodes**: Strings cache their hashcodes after they are computed. Since their contents cannot change, they can store the hashcode for reuse, improving the performance of string operations like hash-based collections (e.g., `HashMap`).

### **Example of String Pool and Immutability**

```java
public class StringPoolExample {
    public static void main(String[] args) {
        String s1 = "Hello";        // Refers to "Hello" in the string pool
        String s2 = "Hello";        // Points to the same string in the pool
        String s3 = new String("Hello"); // Creates a new String object in the heap

        System.out.println(s1 == s2);  // true, same reference in the pool
        System.out.println(s1 == s3);  // false, different objects (heap vs pool)
        
        // Demonstrating immutability
        s1 = s1 + " World";           // s1 now points to a new string "Hello World"
        System.out.println(s1);       // Prints "Hello World"
        System.out.println(s2);       // Prints "Hello", unchanged due to immutability
    }
}
```

### **Summary**
- **String Pool** is a memory area in the heap where Java stores string literals to save memory by reusing identical strings.
- **String Immutability** means strings cannot be changed once created. This enables secure, thread-safe usage, efficient string pooling, and caching of hashcodes.
- When you create a string literal, it checks the pool first. Using the `new` keyword always creates a new string outside the pool.

By combining the benefits of immutability and the string pool, Java ensures both performance and security in applications that make extensive use of strings.

# 5. Explain **garbage collection**?

**Garbage collection** (GC) in Java is an automatic memory management process that **reclaims memory by identifying and removing objects that are no longer in use or referenced by the application.** Java’s **Garbage Collector** helps ensure that memory leaks and manual memory management errors are avoided, as developers do not need to explicitly deallocate memory, unlike in languages like C or C++.

### How Does Garbage Collection Work?
Java’s memory is divided into different areas, the most notable being the **heap**. Objects are allocated memory on the heap, and the **Garbage Collector** tracks which objects are still in use by checking object references. If an object is no longer reachable (i.e., no active references point to it), the garbage collector marks it as eligible for removal.

### Key Concepts in Garbage Collection:

1. **Heap Memory**: The heap is where objects in Java are dynamically allocated. It’s divided into two areas:
   - **Young Generation**: Where new objects are allocated. This area is further divided into:
     - **Eden Space**: Most new objects are allocated here.
     - **Survivor Spaces**: Objects that survive multiple garbage collection cycles are moved here.
   - **Old Generation (Tenured Space)**: Objects that have survived many garbage collection cycles are moved here. These objects are long-lived.

2. **Mark and Sweep Algorithm**: This is the most common garbage collection algorithm. It consists of two phases:
   - **Mark Phase**: The Garbage Collector identifies all the live objects by traversing object references from the root (such as local variables and static fields). All reachable objects are "marked."
   - **Sweep Phase**: The Garbage Collector removes unmarked (unreachable) objects and frees up their memory.

3. **Stop-the-World Events**: During garbage collection, the JVM pauses the execution of the application. This pause is known as a "Stop-the-World" event. While this ensures correct memory management, excessive or frequent pauses can affect application performance.

### Types of Garbage Collectors in Java

1. **Serial Garbage Collector**:
   - Works with a single thread for both marking and sweeping.
   - Suitable for small applications with limited memory usage.
   - Causes frequent "Stop-the-World" events but is simple and efficient for single-threaded environments.

2. **Parallel Garbage Collector (Throughput Collector)**:
   - Uses multiple threads to perform the garbage collection process in parallel.
   - Good for applications running on multi-core processors.
   - Aims to maximize application throughput by reducing GC overhead.

3. **Concurrent Mark-Sweep (CMS) Collector**:
   - Aimed at minimizing "Stop-the-World" pauses.
   - Marks live objects concurrently with the application running but may experience fragmentation in memory due to its incremental approach.
   - Suitable for applications that require low pause times, such as real-time systems.

4. **G1 Garbage Collector (Garbage First)**:
   - Aims to achieve high throughput and low pause times.
   - Divides the heap into multiple regions and collects garbage in a more incremental fashion.
   - Attempts to meet pause-time goals by focusing on areas of the heap that are most likely to contain garbage.

### Why Do We Need Garbage Collection?

1. **Automatic Memory Management**: Java abstracts the complexities of manual memory management, reducing the risk of memory leaks and dangling pointers (unlike in C/C++).
2. **Improved Performance**: By removing unreferenced objects from memory, Java applications can make more efficient use of available memory.
3. **Prevents Memory Leaks**: Unused objects are automatically cleaned up, preventing memory bloat, which could eventually crash the application.
4. **Simplifies Development**: Developers don't need to focus on explicitly freeing memory, leading to cleaner, less error-prone code.

### Example of Garbage Collection in Action:

```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        GarbageCollectionExample obj = new GarbageCollectionExample();
        
        // Making the object eligible for GC
        obj = null;  // Dereferencing the object
        
        // Requesting JVM for garbage collection
        System.gc();  // Suggests GC, but it's not guaranteed to run immediately
    }

    @Override
    protected void finalize() throws Throwable {
        // This method is called before the object is garbage collected
        System.out.println("Garbage collection is happening!");
    }
}
```

- **`System.gc()`** is a method to request the JVM to perform garbage collection, but it does not guarantee immediate execution.
- **`finalize()`** is a method that gets called before an object is garbage collected (though it’s largely deprecated in favor of other mechanisms like `try-with-resources`).

### When Does Garbage Collection Occur?
- When **heap memory** is low, the Garbage Collector kicks in to free memory.
- You can request it manually using `System.gc()`, but the JVM decides when to run the Garbage Collector.
  
### Key Points:
- **Garbage Collection is Automatic**: Java manages memory automatically, so developers don’t need to worry about manual allocation and deallocation.
- **Cannot Force GC**: Although you can suggest garbage collection via `System.gc()`, the JVM controls when and how GC occurs.
- **Object Eligibility**: Objects become eligible for garbage collection when they are no longer referenced by any active part of the code.

### Summary
Garbage Collection is a process in Java that automatically reclaims memory from objects that are no longer in use. It helps in managing memory efficiently without developer intervention. Different garbage collectors (like Serial, Parallel, CMS, and G1) are designed to balance memory management and application performance, depending on the specific use case.

# 6. What are **access modifiers** and their scopes in Java?

In Java, **access modifiers** are keywords that determine the visibility and accessibility of classes, methods, variables, and constructors. They control which parts of a program can access or modify a particular entity.

### Types of Access Modifiers

1. **`public`**  
2. **`private`**  
3. **`protected`**  
4. **(default)** *(no modifier)*

### **1. `public` Access Modifier**
- **Scope**: The `public` modifier allows the class, method, or variable to be accessible from **anywhere** in the program, including from other classes and packages.
  
  - **Class**: If a class is `public`, it can be accessed from any other class, in any package.
  - **Method/Field**: If a method or field is `public`, it can be accessed from any other class, even if those classes are in different packages.

  ```java
  public class PublicClass {
      public String publicField = "Accessible Everywhere";
      
      public void publicMethod() {
          System.out.println("Public method can be accessed from anywhere.");
      }
  }
  ```

### **2. `private` Access Modifier**
- **Scope**: The `private` modifier restricts access to the class members (fields, methods, constructors) **within the same class**. It cannot be accessed outside of the class in which it is defined.
  
  - **Class**: A top-level class cannot be `private`.
  - **Method/Field**: If a method or field is `private`, it can only be accessed within the same class. Not accessible in subclasses or other classes, even if they are in the same package.

  ```java
  public class PrivateExample {
      private String privateField = "Accessible only within the class";
      
      private void privateMethod() {
          System.out.println("Private method is only accessible within the class.");
      }
      
      public void accessPrivateMethod() {
          privateMethod();  // Allowed because it's within the same class
      }
  }
  ```

### **3. `protected` Access Modifier**
- **Scope**: The `protected` modifier allows the class members to be accessible **within the same package** and by **subclasses**, even if they are in different packages.
  
  - **Class**: A top-level class cannot be `protected`.
  - **Method/Field**: If a method or field is `protected`, it can be accessed within the same package, and also by any subclass (even if it is outside the package).

  ```java
  public class ProtectedExample {
      protected String protectedField = "Accessible in package and subclasses";
      
      protected void protectedMethod() {
          System.out.println("Protected method can be accessed in the package and by subclasses.");
      }
  }
  
  class SubClass extends ProtectedExample {
      public void accessProtectedMethod() {
          protectedMethod();  // Allowed because this is a subclass
      }
  }
  ```

### **4. (default) Access Modifier** *(No Modifier)*
- **Scope**: When no access modifier is specified, it is called **package-private** or **default access**. This means the class members are accessible **only within the same package** but not from other packages, even if they are subclasses.
  
  - **Class**: A class with default access can only be accessed by other classes in the same package.
  - **Method/Field**: A method or field with default access can be accessed by any class in the same package but not by classes from outside the package.

  ```java
  class DefaultAccessExample {
      String defaultField = "Accessible within the package";
      
      void defaultMethod() {
          System.out.println("Default method is only accessible within the package.");
      }
  }
  ```

### **Summary Table of Access Modifiers**

| Modifier    | Same Class | Same Package | Subclass (Different Package) | Outside Package |
| ----------- | ---------- | ------------ | ---------------------------- | --------------- |
| `public`    | Yes        | Yes          | Yes                          | Yes             |
| `protected` | Yes        | Yes          | Yes                          | No              |
| (default)   | Yes        | Yes          | No                           | No              |
| `private`   | Yes        | No           | No                           | No              |

### **Key Use Cases:**
- **`public`**: Use when you want the method/field to be accessible everywhere.
- **`private`**: Use for fields and methods that should not be accessed directly from outside the class.
- **`protected`**: Use when you want to give access to subclasses, even those in other packages, but restrict access to outside classes.
- **default (no modifier)**: Use for fields and methods that should be accessible within the same package but hidden from outside packages.

These access modifiers help **encapsulate** the details of your class and control which components can interact with each other.

# 7. Explain **final** keyword? (Filed,Method,Class)

The `**final**` keyword in Java is a special modifier used to apply restrictions on variables (fields), methods, and classes. It is used to indicate that a particular entity cannot be modified after being set or inherited. Let's break down its usage in different contexts:

### 1. **Final Field (Constant)**
When a field (variable) is marked as `final`, it means that once the field is assigned a value, it **cannot be changed**. In other words, it makes the field a constant.

- **Initialization**: A `final` field must be initialized at the time of declaration or in the constructor. Once initialized, its value cannot be modified.
- **Use Case**: Commonly used to declare constants in Java, often with the `static` keyword for memory efficiency.

**Example**:
```java
public class FinalFieldExample {
    public final int MAX_VALUE = 100;  // Constant value
    public final int minValue;         // Must be initialized in the constructor

    public FinalFieldExample(int minValue) {
        this.minValue = minValue;      // Initializing final field in the constructor
    }

    public void changeValue() {
        // MAX_VALUE = 200;  // Not allowed, compilation error
    }
}
```

- **Immutable Constants**: Fields declared as `final` and `static` are often used to define constants that can be shared across instances without modification.

```java
public class Constants {
    public static final String APP_NAME = "MyApplication";
}
```

### 2. **Final Method**
When a method is declared as `final`, it means that the method **cannot be overridden** by subclasses. This ensures that the behavior of the method cannot be changed in child classes.

- **Use Case**: Final methods are typically used when a method's implementation is critical to the class’s operation and should not be altered by subclasses.

**Example**:
```java
class ParentClass {
    public final void displayMessage() {
        System.out.println("This is a final method.");
    }
}

class ChildClass extends ParentClass {
    // public void displayMessage() {
    //     // Not allowed, can't override final method
    // }
}
```

### 3. **Final Class**
When a class is marked as `final`, it means that the class **cannot be extended** (i.e., it cannot have subclasses). This is useful when you want to prevent inheritance to preserve the class's design or functionality.

- **Use Case**: A `final` class is often used for utility or helper classes that should not be subclassed, or when you want to ensure that no one can alter the behavior of the class through inheritance.

**Example**:
```java
final class FinalClass {
    public void show() {
        System.out.println("This is a final class.");
    }
}

// class SubClass extends FinalClass { 
//     // Not allowed, cannot extend a final class
// }
```

### **Summary of `final` Keyword Usage:**

| Entity     | Description                                                  | Effect                                                       |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Field**  | Prevents reassignment of the variable after it has been initialized. | Makes the variable constant.                                 |
| **Method** | Prevents a method from being overridden in subclasses.       | Ensures the method’s implementation remains the same in all derived classes. |
| **Class**  | Prevents a class from being subclassed.                      | Ensures no class can inherit and modify its behavior.        |

### **Key Points:**
- **Final Fields**: Once assigned, cannot be changed; used to declare constants.
- **Final Methods**: Cannot be overridden; ensures that the behavior of a method cannot be altered in subclasses.
- **Final Classes**: Cannot be subclassed; ensures that the class’s behavior cannot be inherited or extended.

The `final` keyword is crucial for maintaining control over how variables, methods, and classes can be used and modified, ensuring certain parts of a program remain immutable or unchangeable when necessary.

# 8. Explan **static** keyword?(Filed,Method,Class). When do we usually use it?

The `**static**` keyword in Java is used to declare class-level variables, methods, and even nested classes. When something is declared `static`, it belongs to the **class itself**, rather than instances of the class. Let's look at the usage of `static` in different contexts:

### 1. **Static Field (Variable)**
A **static field** is a class-level variable that is shared among all instances of the class. It is created when the class is loaded, and there is **only one copy** of the static field, regardless of how many objects are created from the class.

- **Scope**: It is shared across all instances of the class, meaning all objects refer to the same variable.
- **Initialization**: It can be initialized when declared or in a static block.

**Use Case**: Commonly used for constants or data that should be shared among all instances, like a counter or configuration settings.

**Example**:
```java
public class StaticFieldExample {
    public static int count = 0; // Static variable (shared among all instances)

    public StaticFieldExample() {
        count++;  // Incrementing the shared count
    }

    public void displayCount() {
        System.out.println("Number of objects created: " + count);
    }
}

class Test {
    public static void main(String[] args) {
        StaticFieldExample obj1 = new StaticFieldExample();
        StaticFieldExample obj2 = new StaticFieldExample();

        obj1.displayCount();  // Output: 2 (shared count)
    }
}
```

In the above example, `count` is a static field, shared by all objects of the `StaticFieldExample` class.

### 2. **Static Method**
A **static method** belongs to the class rather than instances of the class. This means that static methods can be called **without creating an instance** of the class.

- **Accessing Static Fields**: Static methods can directly access static fields and other static methods, but **cannot directly access non-static fields or methods**.
- **When to Use**: Static methods are typically used for utility functions or methods that do not depend on instance variables (non-static fields).

**Example**:
```java
public class StaticMethodExample {
    public static void printMessage() {
        System.out.println("This is a static method.");
    }

    public static int add(int a, int b) {
        return a + b; // Utility function, doesn't need an instance
    }
}

class Test {
    public static void main(String[] args) {
        StaticMethodExample.printMessage();  // Calling static method without creating an object

        int result = StaticMethodExample.add(5, 3);
        System.out.println("Result: " + result);
    }
}
```

In this example, `printMessage()` and `add()` are static methods, meaning they can be called directly using the class name without creating an instance.

### 3. **Static Class (Nested Static Class)**
A **static nested class** is a nested class that is declared static. Unlike an inner class, a static nested class **does not have a reference** to an instance of its enclosing class, meaning it can be created without an object of the outer class.

- **Use Case**: Static nested classes are used when the nested class does not need access to the instance of the outer class. They are often used to group related classes together to provide better organization and readability.

**Example**:
```java
public class OuterClass {
    static class NestedStaticClass {
        public void display() {
            System.out.println("This is a static nested class.");
        }
    }
}

class Test {
    public static void main(String[] args) {
        OuterClass.NestedStaticClass nested = new OuterClass.NestedStaticClass();
        nested.display();
    }
}
```

Here, `NestedStaticClass` is a static nested class and can be instantiated without creating an instance of `OuterClass`.

### Key Points About the `static` Keyword:
1. **Static Fields (Variables)**:
   - Shared across all instances of the class.
   - Useful for constants or shared data (e.g., counters, configuration values).
   
2. **Static Methods**:
   - Can be called without creating an object of the class.
   - Cannot access instance variables or methods (non-static) directly.
   - Useful for utility methods that don’t require instance data (e.g., mathematical operations, helper functions).

3. **Static Class (Nested Class)**:
   - A static nested class does not need a reference to an instance of the outer class.
   - Useful for logically grouping classes together, especially if the nested class is only relevant to the outer class.

### Example Use Cases:
- **Static Fields**: Often used to define constants or to maintain a counter across all instances of a class (e.g., `public static final int MAX_SIZE = 100`).
- **Static Methods**: Commonly used in utility classes like `Math`, where methods like `Math.abs()` are static and don’t require a `Math` object to be called.
- **Static Nested Classes**: Useful for organizing code and grouping related classes (e.g., an outer class `Car` with a static nested class `Engine`).

### Summary of `static` Keyword Usage:

| Entity            | Description                                                  | Usage                                                        |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Static Field**  | A class-level variable shared by all instances of the class. | Used for constants or data shared across instances (e.g., counters, configuration). |
| **Static Method** | A method that can be called without creating an object of the class. | Used for utility methods that don’t require instance-specific data (e.g., `Math.abs()`). |
| **Static Class**  | A nested class that does not have a reference to the enclosing class instance. | Used for grouping classes together or when a nested class doesn’t require the outer class. |

The `static` keyword is crucial in Java for creating class-level variables and methods that do not rely on instance-specific data and for organizing code more effectively using static nested classes.

# 9. What is the differences between **overriding** and **overloading**?

**Overriding** and **overloading** are two fundamental concepts in Java that relate to method functionality, but they serve different purposes and have distinct characteristics. Here’s a breakdown of their differences:

### 1. **Definition**
- **Overriding**:
  - Overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. The method in the subclass has the same name, return type, and parameters as the method in the parent class.
  
- **Overloading**:
  - Overloading occurs when multiple methods in the same class (or subclass) have the same name but different parameters (different type, number, or both). The method signature must differ for overloading to occur.

### 2. **Purpose**
- **Overriding**:
  - The purpose of overriding is to provide a specific implementation of a method in a subclass that is already defined in the superclass. This allows a subclass to define its behavior while still following the parent class's interface.
  
- **Overloading**:
  - The purpose of overloading is to create multiple methods with the same name that can perform similar but slightly different tasks based on their parameters. This provides flexibility and improves code readability.

### 3. **Method Signature**
- **Overriding**:
  - The method signature (name and parameter list) in the subclass must be exactly the same as in the superclass.
  
- **Overloading**:
  - The method signature must be different (different parameter types, numbers, or order of parameters) to distinguish the overloaded methods.

### 4. **Return Type**
- **Overriding**:
  - The return type of the overriding method can be the same as or a subtype of the return type declared in the original overridden method (covariant return type). However, it cannot be a different type.
  
- **Overloading**:
  - The return type can be different for overloaded methods, as long as the parameter lists are different.

### 5. **Compile-Time vs. Runtime**
- **Overriding**:
  - Overriding is determined at runtime (dynamic binding), meaning the method to be executed is resolved during the execution of the program based on the object type.
  
- **Overloading**:
  - Overloading is resolved at compile-time (static binding), meaning the method to be called is determined during compilation based on the method signature.

### 6. **Example**
Here’s an example illustrating both concepts:

**Overriding Example**:
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {  // Overriding the sound method
        System.out.println("Dog barks");
    }
}

public class TestOverriding {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.sound();  // Output: Dog barks (runtime polymorphism)
    }
}
```

**Overloading Example**:
```java
class MathOperations {
    int add(int a, int b) {  // Method to add two integers
        return a + b;
    }

    double add(double a, double b) {  // Overloaded method to add two doubles
        return a + b;
    }

    int add(int a, int b, int c) {  // Overloaded method to add three integers
        return a + b + c;
    }
}

public class TestOverloading {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println(math.add(5, 3));          // Output: 8
        System.out.println(math.add(5.5, 3.2));      // Output: 8.7
        System.out.println(math.add(1, 2, 3));       // Output: 6
    }
}
```

### **Summary of Differences**

| Aspect           | Overriding                                | Overloading                                                  |
| ---------------- | ----------------------------------------- | ------------------------------------------------------------ |
| Definition       | Redefining a method in a subclass         | Creating multiple methods with the same name                 |
| Purpose          | To provide a specific implementation      | To perform similar operations with different parameters      |
| Method Signature | Must be the same as the superclass method | Must differ (different parameters)                           |
| Return Type      | Can be the same or a subtype              | Can be different                                             |
| Binding Type     | Runtime (dynamic binding)                 | Compile-time (static binding)                                |
| Example Context  | Subclass modifies superclass method       | Same class has methods with the same name but different parameters |

In summary, overriding is about changing the implementation of an existing method in a subclass, while overloading is about defining multiple methods with the same name but different signatures within the same class.

# 10. Explain how Java defines a **method signature**, and how it helps on overloading and overriding. 

### Method Signature in Java

In Java, a **method signature** is a combination of the method's name and its parameter list. The signature defines the unique identity of a method within a class and consists of:

1. **Method Name**: The name of the method.
2. **Parameter List**: The types and order of parameters the method accepts (but not their names). 

The method signature is used by the Java compiler to differentiate between overloaded methods (methods with the same name but different parameter lists) and to verify method overriding (ensuring the overriding method in a subclass has the same signature as the method in the superclass).

### Significance in Overloading and Overriding

- **Overloading**: 
  - Methods can be overloaded by creating multiple methods with the same name but different signatures (different parameter types or counts). The compiler distinguishes between them based on their signatures.
  - Example:
    ```java
    public void print(int a) {}        // Signature: print(int)
    public void print(String b) {}     // Signature: print(String)
    public void print(int a, String b) {} // Signature: print(int, String)
    ```

- **Overriding**: 
  - In overriding, the method in the subclass must have the same name and the same parameter list (signature) as the method in the superclass. This ensures that the correct method is called when the overridden method is invoked.
  - Example:
    ```java
    class Parent {
        void display() {}
    }
    
    class Child extends Parent {
        @Override
        void display() {}  // Same signature as Parent's display()
    }
    ```

# 11. What is the differences between **super** and **this**?

### Differences Between `super` and `this`

`super` and `this` are both keywords used in Java to refer to specific objects and their members, but they serve different purposes:

#### 1. **`this` Keyword**

- **Definition**: The `this` keyword refers to the current instance of the class. It is used to access instance variables and methods of the current object.
- **Usage**:
  - To distinguish between instance variables and parameters with the same name.
  - To invoke instance methods or constructors from the current object.
- **Example**:
  ```java
  class Example {
      private int value;
  
      public Example(int value) {
          this.value = value; // 'this.value' refers to the instance variable
      }
  
      public void display() {
          System.out.println(this.value); // Accessing the instance variable
      }
  }
  ```

#### 2. **`super` Keyword**

- **Definition**: The `super` keyword refers to the superclass of the current object. It is used to access members (variables and methods) of the parent class.
- **Usage**:
  - To call a superclass method that has been overridden.
  - To access superclass constructors.
- **Example**:
  ```java
  class Parent {
      void display() {
          System.out.println("Parent class");
      }
  }
  
  class Child extends Parent {
      void display() {
          super.display(); // Calls the method of the Parent class
          System.out.println("Child class");
      }
  }
  ```

### Summary of Differences Between `super` and `this`

| Aspect           | `this`                                        | `super`                                                      |
| ---------------- | --------------------------------------------- | ------------------------------------------------------------ |
| Reference        | Refers to the current instance of the class   | Refers to the superclass of the current instance             |
| Purpose          | Access instance variables/methods             | Access superclass variables/methods                          |
| Usage            | Used within instance methods and constructors | Used within instance methods to refer to superclass methods/constructors |
| Constructor Call | Cannot call superclass constructor            | Can call superclass constructor using `super()`              |

In summary, `this` is used for referring to the current object's members, while `super` is used for accessing members from the superclass, facilitating inheritance and method overriding.

# 12. Explain how equals and hashCode work.

In Java, the `equals()` and `hashCode()` methods are fundamental for comparing objects and storing them in collections like `HashMap`, `HashSet`, and `Hashtable`. Understanding how they work and how they should be implemented is crucial for maintaining object equality and ensuring the correct behavior of collections. Here’s an overview of each method:

### `equals()` Method

#### Definition:
The `equals()` method is used to compare two objects for **equality**. The default implementation in the `Object` class checks for reference equality (i.e., whether both references point to the same object in memory). However, this may not be sufficient for classes where logical equality (i.e., equality based on the state of the objects) is required.

#### Common Implementation:
When overriding the `equals()` method, you should follow these guidelines:
1. **Symmetry**: If `a.equals(b)` returns `true`, then `b.equals(a)` must also return `true`.
2. **Transitivity**: If `a.equals(b)` returns `true` and `b.equals(c)` returns `true`, then `a.equals(c)` must return `true`.
3. **Consistency**: If `a.equals(b)` is invoked multiple times, it should consistently return the same result as long as the objects are not modified.
4. **Non-nullity**: For any non-null reference `a`, `a.equals(null)` should return `false`.

#### Example Implementation:
```java
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Reference equality
        if (obj == null || getClass() != obj.getClass()) return false; // Null check and class check

        Person other = (Person) obj; // Type casting
        return age == other.age && name.equals(other.name); // Logical equality based on fields
    }

    // It's good practice to override hashCode() whenever equals() is overridden
}
```

### `hashCode()` Method

#### Definition:
The `hashCode()` method returns an integer hash code value for the object. This hash code is used in hash-based collections (like `HashMap`, `HashSet`, and `Hashtable`) to determine the bucket where the object is stored.

#### Importance:
1. **Contract**: If two objects are equal according to the `equals()` method, they must have the same hash code. However, the reverse is not required; two different objects can have the same hash code (this is called a hash collision).
2. **Performance**: Implementing a good `hashCode()` method improves the performance of hash-based collections by minimizing collisions.

#### Example Implementation:
```java
@Override
public int hashCode() {
    int result = name.hashCode(); // Generate hash code based on name
    result = 31 * result + age; // Combine hash codes (using a prime number)
    return result;
}
```

### Relationship Between `equals()` and `hashCode()`

When you override `equals()`, you should also override `hashCode()`. The general contract is:

- If two objects are considered equal by `equals()`, they must return the same hash code.
- If two objects are not equal according to `equals()`, they may return the same or different hash codes.

### Summary

- **`equals()` Method**: Used for comparing the logical equality of two objects. It should be overridden to define what makes two instances equal based on their field values.
- **`hashCode()` Method**: Returns an integer that represents the object's hash code. It should be overridden to ensure that equal objects produce the same hash code.

Together, these methods are critical for the proper functioning of collections that rely on hashing and equality checks, enabling effective storage, retrieval, and management of objects in Java.

# 13. What is the Java **load sequence**?

The Java load sequence refers to the process that the Java Virtual Machine (JVM) follows to load classes and resources when a Java program is executed. Understanding this sequence is essential for effective debugging, performance tuning, and class management. Here’s an overview of the Java load sequence:

### 1. **Class Loader Hierarchy**

The Java load sequence involves multiple class loaders organized in a hierarchical structure. The primary class loaders are:

- **Bootstrap Class Loader**: Loads core Java libraries (located in the `$JAVA_HOME/jre/lib` directory) and the `java.lang` package. It is part of the JVM and is implemented in native code.
- **Extension Class Loader**: Loads classes from the Java extension directory (typically located in `$JAVA_HOME/jre/lib/ext`). It is responsible for loading additional libraries and extensions.
- **Application Class Loader**: Loads classes from the application's classpath, which can include JAR files, directories, and other resource locations specified in the `CLASSPATH` environment variable.

### 2. **Loading Process**

The loading process consists of several key steps:

1. **Class Loading**:
   - When a class is referenced in the code (e.g., creating an instance of a class or accessing a static method), the JVM checks if the class has already been loaded.
   - If the class is not loaded, the JVM invokes the appropriate class loader (starting from the Application Class Loader and moving up to the Bootstrap Class Loader) to load the class.

2. **Linking**:
   - After loading, the class goes through a linking process, which consists of three stages:
     - **Verification**: Ensures that the class file adheres to the Java language and runtime constraints. This step checks the bytecode for any illegal code that could violate the security and integrity of the JVM.
     - **Preparation**: Allocates memory for class variables and sets them to their default values (e.g., `0` for integers, `null` for objects).
     - **Resolution**: Replaces symbolic references in the class with direct references. This step resolves any references to classes, fields, and methods that the class depends on.

3. **Initialization**:
   - Once the class is linked, the JVM initializes it. This step involves executing the class's static initializers (static blocks) and initializing static variables to their specified values.
   - The class initialization is performed only once per class in the JVM lifetime.

### 3. **Execution**

After the class has been loaded, linked, and initialized, the JVM can execute the code by invoking the `main()` method (or other methods) as needed. The execution process may involve:

- Creating instances of loaded classes.
- Invoking methods on those instances.
- Managing memory for the created objects through garbage collection.

### Summary of the Load Sequence

1. **Class Loading**: The JVM loads the class using the appropriate class loader.
2. **Linking**:
   - **Verification**: Checks bytecode integrity.
   - **Preparation**: Allocates memory for class variables and initializes them.
   - **Resolution**: Resolves symbolic references.
3. **Initialization**: Executes static initializers and initializes static variables.
4. **Execution**: Runs the code, invoking methods and creating instances as required.

### Important Notes

- **Lazy Loading**: Classes are loaded only when they are referenced for the first time, which optimizes memory usage.
- **Class Loader Isolation**: Different class loaders can load the same class independently, leading to separate instances and conflicts, particularly in web applications or modular environments.

This loading sequence is crucial for ensuring that the Java application runs correctly, with proper class management and memory allocation.

# 14. What is **Polymorphism** ? And how Java implements it ?

### What is Polymorphism?

**Polymorphism** is one of the four fundamental concepts of Object-Oriented Programming (OOP), along with encapsulation, inheritance, and abstraction. The term "polymorphism" means "many shapes" or "many forms," and it allows objects of different classes to be treated as objects of a common superclass. 

Polymorphism can be classified into two types:

1. **Compile-time Polymorphism (Static Binding)**: This is achieved through method overloading and operator overloading (the latter is not applicable in Java). In method overloading, multiple methods can have the same name with different parameter lists within the same class.

2. **Runtime Polymorphism (Dynamic Binding)**: This occurs when a method call is resolved at runtime, typically through method overriding in subclasses. It allows the program to choose the appropriate method to execute based on the object that invokes it.

### How Java Implements Polymorphism

Java implements polymorphism primarily through **method overloading** and **method overriding**.

#### 1. Compile-time Polymorphism (Method Overloading)

- **Definition**: Method overloading occurs when multiple methods in the same class (or in a subclass) have the same name but different parameters (different type, number, or both).
  

**Example**:
```java
class MathOperations {
    // Method to add two integers
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded method to add three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method to add two doubles
    double add(double a, double b) {
        return a + b;
    }
}

public class TestOverloading {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println(math.add(5, 3));          // Calls add(int, int)
        System.out.println(math.add(5, 3, 2));       // Calls add(int, int, int)
        System.out.println(math.add(5.5, 3.2));      // Calls add(double, double)
    }
}
```

In this example, the `add` method is overloaded with different parameters, demonstrating compile-time polymorphism.

#### 2. Runtime Polymorphism (Method Overriding)

- **Definition**: Method overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. The method in the subclass must have the same name, return type, and parameters as the method in the superclass.

**Example**:
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {  // Overriding the sound method
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void sound() {  // Overriding the sound method
        System.out.println("Cat meows");
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        Animal myDog = new Dog();  // Upcasting
        Animal myCat = new Cat();  // Upcasting

        myDog.sound();  // Output: Dog barks
        myCat.sound();  // Output: Cat meows
    }
}
```

In this example, the `sound` method is overridden in the `Dog` and `Cat` classes. Even though the reference type is `Animal`, the method invoked at runtime corresponds to the actual object type (either `Dog` or `Cat`), demonstrating runtime polymorphism.

### Key Points About Polymorphism

- **Flexibility**: Polymorphism allows for flexible code, enabling methods to process objects of different classes that share a common interface or superclass.
- **Code Reusability**: It enhances code reusability by allowing the same method to behave differently based on the object's type, reducing code duplication.
- **Dynamic Method Dispatch**: In runtime polymorphism, the method call is resolved at runtime, enabling the correct method to be executed based on the object type.

### Summary

Polymorphism in Java allows methods to be executed in a context-sensitive manner, supporting method overloading (compile-time) and method overriding (runtime). This provides flexibility, enhances code maintainability, and allows for the design of extensible systems.

# 15. What is **Encapsulation** ? How Java implements it? And why we need encapsulation? 

### What is Encapsulation?

**Encapsulation** is one of the four fundamental principles of Object-Oriented Programming (OOP), along with inheritance, polymorphism, and abstraction. It is the practice of bundling the data (attributes) and methods (functions) that operate on the data into a single unit, typically called a class. Encapsulation restricts direct access to some of an object's components and can prevent the accidental modification of data.

### How Java Implements Encapsulation

Java implements encapsulation through:

1. **Access Modifiers**: These control the visibility of class members (variables and methods) to other classes. The main access modifiers in Java are:
   - **private**: The member is accessible only within its own class.
   - **default** (no modifier): The member is accessible only within classes in the same package.
   - **protected**: The member is accessible within its own package and by subclasses.
   - **public**: The member is accessible from any other class.

2. **Getter and Setter Methods**: To provide controlled access to private variables, Java uses public methods (getters and setters) to read and modify the values of these variables.

**Example**:
```java
class BankAccount {
    // Private variables to restrict direct access
    private String accountNumber;
    private double balance;

    // Constructor to initialize the account
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Getter for accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance (if you want to allow modifications)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

public class TestEncapsulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.0);
        
        // Accessing account number and balance using getters
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());

        // Modifying balance using methods
        account.deposit(500);
        System.out.println("Updated Balance: " + account.getBalance());

        account.withdraw(200);
        System.out.println("Balance after withdrawal: " + account.getBalance());
    }
}
```

### Why Do We Need Encapsulation?

1. **Data Hiding**: Encapsulation provides data hiding, ensuring that sensitive information is protected from unauthorized access and modification. For example, in the `BankAccount` class, the balance is hidden, and users can only modify it through controlled methods (deposit and withdraw).

2. **Improved Maintenance**: By restricting access to the internal state of an object, encapsulation makes it easier to change the internal implementation without affecting external code that relies on the class. For instance, you could change the way balance calculations are done without changing how other classes interact with `BankAccount`.

3. **Increased Flexibility and Reusability**: Encapsulated classes can be easily reused in different contexts. Since they expose only the necessary interfaces (methods), you can swap implementations without altering the code that depends on them.

4. **Enhanced Control**: Encapsulation provides greater control over the data. You can validate data before it is set, enforce invariants, and provide additional behavior when accessing or modifying data.

### Summary

Encapsulation is a fundamental principle of OOP that combines data and behavior within a single unit (class) and restricts direct access to an object's internal state. Java implements encapsulation using access modifiers and getter/setter methods, providing data hiding, improved maintainability, and enhanced control over the data. These benefits lead to more secure, flexible, and reusable code.

# 16. Compare **interface** and **abstract class**.

### Comparison of Interface and Abstract Class in Java

**Interface** and **abstract class** are both used to achieve abstraction in Java, but they have different use cases and features. Here’s a detailed comparison:

| Feature                   | Interface                                                    | Abstract Class                                               |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Definition**            | A reference type that can contain only constants, method signatures, default methods, static methods, and nested types. | A class that cannot be instantiated and can contain abstract methods (without a body) and concrete methods (with a body). |
| **Purpose**               | Defines a contract for classes to implement, specifying what methods must be provided. | Provides a base class with shared behavior and properties, allowing subclasses to inherit common code. |
| **Method Implementation** | Methods are abstract by default (Java 8 and later allows default and static methods). | Can have both abstract and concrete (implemented) methods.   |
| **Multiple Inheritance**  | A class can implement multiple interfaces, allowing for a form of multiple inheritance. | A class can extend only one abstract class (single inheritance). |
| **Fields**                | All fields are implicitly `public`, `static`, and `final`. Cannot have instance variables. | Can have instance variables, and fields can have various access modifiers. |
| **Constructor**           | Cannot have constructors since interfaces cannot be instantiated. | Can have constructors to initialize fields when an instance of a subclass is created. |
| **Use Case**              | Best used when you need to define a contract for classes that may not be related. Ideal for defining capabilities (e.g., `Comparable`, `Runnable`). | Best used when classes share common behavior or state, allowing for a common implementation while enforcing certain methods to be implemented. |
| **Default Methods**       | Supports default methods (since Java 8), which allow providing a default implementation. | Does not support default methods but can provide default implementations of methods. |
| **Static Methods**        | Can include static methods (since Java 8) that belong to the interface rather than instances. | Can include static methods that belong to the abstract class. |

### Examples

**Interface Example**:
```java
interface Animal {
    void makeSound(); // Abstract method

    default void eat() { // Default method
        System.out.println("This animal eats food.");
    }
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Cat meows");
    }
}
```

**Abstract Class Example**:
```java
abstract class Vehicle {
    abstract void start(); // Abstract method

    void stop() { // Concrete method
        System.out.println("Vehicle stops");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starts");
    }
}

class Bike extends Vehicle {
    void start() {
        System.out.println("Bike starts");
    }
}
```

### Summary

- **Interfaces** are used to define a contract that multiple classes can implement, supporting multiple inheritance.
- **Abstract classes** provide a base for related classes to share common functionality while enforcing specific methods to be implemented by subclasses.
- Choose interfaces when you want to define a set of behaviors, and use abstract classes when you need to share code among closely related classes.

# 17. Write a factory pattern in code.

The Factory Pattern is a creational design pattern that provides a way to create objects without specifying the exact class of object that will be created. It defines an interface for creating an object but allows subclasses to alter the type of objects that will be created. Here’s a simple example demonstrating the Factory Pattern in Java.

### Example Code for Factory Pattern

**Step 1: Create an Interface**

```java
// Shape.java
public interface Shape {
    void draw();
}
```

**Step 2: Create Concrete Classes Implementing the Shape Interface**

```java
// Circle.java
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

// Rectangle.java
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

// Square.java
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}
```

**Step 3: Create a Factory Class to Generate Object Instances**

```java
// ShapeFactory.java
public class ShapeFactory {
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
```

**Step 4: Use the Factory to Create Objects**

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get an object of Circle and call its draw method
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

        // Get an object of Rectangle and call its draw method
        Shape rectangle = shapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        // Get an object of Square and call its draw method
        Shape square = shapeFactory.getShape("SQUARE");
        square.draw();
    }
}
```

### Output

When you run the `Main` class, the output will be:

```
Drawing a Circle
Drawing a Rectangle
Drawing a Square
```

### Explanation

1. **Interface (`Shape`)**: Defines a method `draw()` that all concrete classes must implement.
2. **Concrete Classes (`Circle`, `Rectangle`, `Square`)**: Implement the `Shape` interface and provide specific implementations of the `draw()` method.
3. **Factory Class (`ShapeFactory`)**: Contains a method `getShape()` that takes a string input and returns an instance of the corresponding shape. This abstracts the instantiation logic from the client code.
4. **Client Code (`Main`)**: Uses the `ShapeFactory` to create shape objects without knowing the details of their instantiation.

### Advantages of the Factory Pattern

- **Decoupling**: The client code does not need to know about the specific classes being instantiated, reducing dependencies.
- **Flexibility**: You can easily introduce new shapes without modifying the existing client code, adhering to the Open/Closed Principle.
- **Encapsulation**: The object creation logic is encapsulated within the factory, making it easier to manage and maintain.

This pattern is widely used in various scenarios, such as creating GUI components, database connections, and other scenarios where object creation logic can be abstracted away.

# 18. Write a adapter pattern in code.

The Adapter Pattern is a structural design pattern that **allows incompatible interfaces to work together**. It acts as a bridge between two incompatible interfaces by converting the interface of a class into another interface that a client expects. Here's a simple example to demonstrate the Adapter Pattern in Java.

### Example Code for Adapter Pattern

**Step 1: Create the Target Interface**

```java
// Target.java
public interface Target {
    void request();
}
```

**Step 2: Create the Adaptee Class**

```java
// Adaptee.java
public class Adaptee {
    public void specificRequest() {
        System.out.println("Called specificRequest from Adaptee.");
    }
}
```

**Step 3: Create the Adapter Class**

```java
// Adapter.java
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // Call the adaptee's specificRequest method
        adaptee.specificRequest();
    }
}
```

**Step 4: Use the Adapter in Client Code**

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        // Create an instance of Adaptee
        Adaptee adaptee = new Adaptee();
        
        // Create an adapter for the adaptee
        Target adapter = new Adapter(adaptee);
        
        // Use the adapter to call the request method
        adapter.request();
    }
}
```

### Output

When you run the `Main` class, the output will be:

```
Called specificRequest from Adaptee.
```

### Explanation

1. **Target Interface (`Target`)**: This defines the domain-specific interface that the client will use. In this case, it has a method `request()`.

2. **Adaptee Class (`Adaptee`)**: This is a class with a method (`specificRequest()`) that the client wants to use, but it does not match the interface that the client expects.

3. **Adapter Class (`Adapter`)**: This implements the `Target` interface and contains a reference to an instance of `Adaptee`. It translates the call to `request()` into a call to `specificRequest()` on the `Adaptee`.

4. **Client Code (`Main`)**: The client interacts with the `Target` interface without needing to know about the `Adaptee`. It uses the adapter to make calls.

### Advantages of the Adapter Pattern

- **Compatibility**: It allows classes with incompatible interfaces to work together, facilitating integration of legacy or third-party classes.
- **Decoupling**: The client code is decoupled from the implementation details of the adaptee, making it easier to change the adaptee's implementation without affecting the client code.
- **Reusability**: Existing classes can be reused without modifying their source code, adhering to the Open/Closed Principle.

The Adapter Pattern is particularly useful in scenarios where you need to integrate new functionality into existing systems, especially when dealing with third-party libraries or legacy code that you cannot change.

# 19. Write singleton pattern in code, make sure your code is thread-safe.

The Singleton Pattern ensures that a class has only **one instance and provides a global point of access to that instance.** In a multithreaded environment, it's important to ensure that the singleton instance is created in a thread-safe manner. Here's an implementation of the Singleton Pattern in Java using the **Double-Checked Locking** technique for thread safety.

### Singleton Pattern Example (Thread-Safe)

```java
// Singleton.java
public class Singleton {
    // Volatile variable to ensure visibility of the instance across threads
    private static volatile Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialization code
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        // First check (no locking)
        if (instance == null) {
            synchronized (Singleton.class) {
                // Second check (with locking)
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate functionality
    public void displayMessage() {
        System.out.println("Singleton instance: " + this);
    }
}
```

### Usage Example

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        // Get the singleton instance and call a method
        Singleton singleton = Singleton.getInstance();
        singleton.displayMessage();

        // Verify that the same instance is returned
        Singleton anotherSingleton = Singleton.getInstance();
        anotherSingleton.displayMessage();

        // Check if both references point to the same instance
        System.out.println("Are both instances the same? " + (singleton == anotherSingleton));
    }
}
```

### Output

When you run the `Main` class, the output will be similar to:

```
Singleton instance: Singleton@1a2b3c4
Singleton instance: Singleton@1a2b3c4
Are both instances the same? true
```

### Explanation

1. **Private Constructor**: The constructor of the `Singleton` class is private to prevent external instantiation.

2. **Volatile Instance**: The `instance` variable is declared as `volatile` to ensure that changes to the variable are visible to all threads. This prevents issues related to instruction reordering by the Java Memory Model.

3. **Double-Checked Locking**: The `getInstance()` method first checks if `instance` is `null` without synchronization (first check). If it is `null`, it synchronizes on the `Singleton.class`, then checks again (second check) to ensure that another thread hasn't initialized the instance while waiting for the lock. If it is still `null`, it creates the instance.

4. **Thread Safety**: This implementation ensures that only one instance of the `Singleton` class is created, even in a multithreaded environment.

### Advantages of This Implementation

- **Lazy Initialization**: The instance is created only when it is needed, which can improve performance if the instance is not always required.
- **Thread Safety**: The double-checked locking mechanism ensures that the instance is created in a thread-safe manner without incurring the overhead of synchronization on every call to `getInstance()`.

This implementation of the Singleton Pattern is widely used in Java applications where a single instance of a class is needed, such as in configuration classes, logging, and connection pooling.

# 20. Design a parking lot (put the code to **codingQuestions**/coding1 folder, ) If you have no ability to design it, please find the solution in internet, then understand it,and re-type it.(**Do NOT just copy and paste**)

For detailed codes, **please** refer to the codes in CodingQuestions

Designing a parking lot involves creating a system that can manage vehicles, parking spots, and the overall operations of the parking lot. Here’s a basic design in object-oriented terms, focusing on classes and their relationships. This design is scalable and could be adapted for different types of parking lots (multi-level, different types of vehicles, etc.).

### Requirements:
1. The parking lot should be able to park different types of vehicles (cars, motorcycles, trucks).
2. The parking lot should keep track of which parking spots are available and which are occupied.
3. The system should be able to assign vehicles to parking spots and free them when vehicles leave.
4. There could be multiple levels in the parking lot.
5. Different types of vehicles need different sizes of parking spaces.

---

### Classes and Relationships:

#### 1. **ParkingLot**
   - Represents the entire parking lot.
   - Contains multiple levels, each having multiple parking spots.
   - Methods to park and remove vehicles.

#### 2. **ParkingLevel**
   - Represents a single level in the parking lot.
   - Contains a collection of parking spots.
   - Can check for available spots and assign vehicles to spots.

#### 3. **ParkingSpot**
   - Represents a single parking spot.
   - Can be assigned to a vehicle or marked as available.
   - Knows the type of vehicle it can hold (e.g., car, motorcycle, truck).

#### 4. **Vehicle**
   - Represents a generic vehicle.
   - Subclasses for `Car`, `Motorcycle`, and `Truck`.
   - Each subclass can specify the size of the vehicle and the type of parking spot it requires.

#### 5. **Ticket**
   - Represents the parking ticket issued when a vehicle parks in the lot.
   - Can store information like entry time, exit time, and parking spot number.

---

### UML Representation (Simplified):
```
+-----------------+        +------------------+
|   ParkingLot    |        |   ParkingLevel    |
+-----------------+        +------------------+
| - levels[]      |<------>| - parkingSpots[]  |
+-----------------+        +------------------+
| + parkVehicle() |        | + parkVehicle()   |
| + removeVehicle()|       | + findAvailableSpot() |
+-----------------+        +------------------+

+------------------+       +-------------------+
|   ParkingSpot    |       |     Vehicle       |
+------------------+       +-------------------+
| - isAvailable    |       | - size            |
| - vehicleType    |       | + getSize()       |
+------------------+       +-------------------+
| + assignVehicle()|       |                   |
| + removeVehicle()|       +-------------------+
+------------------+          ^        ^       ^
                               |        |       |
                         +----------+ +----------+ +----------+
                         |   Car    | | Motorcycle| |  Truck   |
                         +----------+ +----------+ +----------+
```

---

### Java Code Example

#### 1. **Vehicle Class Hierarchy**

```java
abstract class Vehicle {
    protected String licensePlate;
    protected int size;

    public Vehicle(String licensePlate, int size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public abstract String getType();
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, 1); // Cars take 1 unit space
    }

    @Override
    public String getType() {
        return "Car";
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, 1); // Motorcycles take 1 unit space
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, 3); // Trucks take 3 unit spaces
    }

    @Override
    public String getType() {
        return "Truck";
    }
}
```

#### 2. **ParkingSpot Class**

```java
class ParkingSpot {
    private Vehicle vehicle;
    private int spotSize;

    public ParkingSpot(int size) {
        this.spotSize = size;
    }

    public boolean fit(Vehicle vehicle) {
        return this.vehicle == null && vehicle.getSize() <= this.spotSize;
    }

    public void assignVehicle(Vehicle vehicle) {
        if (fit(vehicle)) {
            this.vehicle = vehicle;
        }
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }
}
```

#### 3. **ParkingLevel Class**

```java
class ParkingLevel {
    private ParkingSpot[] spots;

    public ParkingLevel(int numberOfSpots) {
        spots = new ParkingSpot[numberOfSpots];
        for (int i = 0; i < numberOfSpots; i++) {
            if (i < numberOfSpots / 3) {
                spots[i] = new ParkingSpot(3); // Large spots for trucks
            } else if (i < numberOfSpots / 2) {
                spots[i] = new ParkingSpot(1); // Small spots for motorcycles
            } else {
                spots[i] = new ParkingSpot(1); // Medium spots for cars
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(vehicle)) {
                spot.assignVehicle(vehicle);
                return true;
            }
        }
        return false; // No available spot
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(vehicle)) {
                spot.removeVehicle();
                break;
            }
        }
    }
}
```

#### 4. **ParkingLot Class**

```java
class ParkingLot {
    private ParkingLevel[] levels;

    public ParkingLot(int numLevels, int spotsPerLevel) {
        levels = new ParkingLevel[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new ParkingLevel(spotsPerLevel);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                System.out.println(vehicle.getType() + " parked.");
                return true;
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            level.removeVehicle(vehicle);
            System.out.println(vehicle.getType() + " removed from parking.");
            return;
        }
    }
}
```

#### 5. **Main Function to Test the System**

```java
public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 10);

        Vehicle car = new Car("CAR-123");
        Vehicle motorcycle = new Motorcycle("BIKE-456");
        Vehicle truck = new Truck("TRUCK-789");

        parkingLot.parkVehicle(car);        // Output: Car parked.
        parkingLot.parkVehicle(motorcycle); // Output: Motorcycle parked.
        parkingLot.parkVehicle(truck);      // Output: Truck parked.

        parkingLot.removeVehicle(car);      // Output: Car removed from parking.
    }
}
```

---

### Key Concepts Covered

- **Encapsulation**: Each class (ParkingSpot, ParkingLevel, ParkingLot, Vehicle) encapsulates its data and behavior. They interact via well-defined interfaces.
- **Inheritance**: The `Vehicle` class is extended by `Car`, `Motorcycle`, and `Truck`.
- **Polymorphism**: The `parkVehicle()` method handles all types of vehicles through the `Vehicle` superclass.
- **Composition**: A `ParkingLot` contains multiple `ParkingLevel`s, and each `ParkingLevel` contains multiple `ParkingSpot`s.

### Possible Extensions

- Add support for parking fees and time-based tracking (e.g., issuing and managing `Ticket`s).
- Add a reservation system for parking spots.
- Add more vehicle types and associated logic for parking.

This design is flexible and can be extended or modified based on additional requirements (multi-level parking, electric vehicle spots, etc.).

# 21. What are Queue interface implementations and what are the differences and when to use what?

In Java, the `Queue` interface is part of the Java Collections Framework and represents a collection designed for holding elements prior to processing. It provides methods to add, remove, and examine elements in a specific order. There are several implementations of the `Queue` interface, each with its own characteristics and use cases. Here are the main implementations and their differences:

### 1. **LinkedList**
- **Description**: Implements both `List` and `Queue` interfaces, allowing it to function as a list or a queue.
- **Characteristics**:
  - Doubly linked list structure.
  - Allows null elements.
  - Supports insertion, removal, and access operations in constant time.
- **Use Case**: When you need a queue with frequent insertions and deletions, and you also want the flexibility to treat it as a list.

### 2. **ArrayDeque**
- **Description**: A resizable array implementation of the `Deque` interface, which also implements `Queue`.
- **Characteristics**:
  - Does not allow null elements.
  - Faster than `LinkedList` for queue operations (offer, poll, and peek).
  - Can be used as a stack (LIFO) or queue (FIFO).
- **Use Case**: When you need a high-performance queue with dynamic sizing, and do not need to use null elements.

### 3. **PriorityQueue**
- **Description**: Implements a priority queue, where elements are ordered based on their natural ordering or a custom comparator.
- **Characteristics**:
  - Does not allow null elements.
  - Elements with higher priority are processed before elements with lower priority.
  - Offers logarithmic time complexity for insertion and removal.
- **Use Case**: When you need to process elements based on priority rather than the order of arrival (e.g., scheduling tasks).

### 4. **ConcurrentLinkedQueue**
- **Description**: A thread-safe, non-blocking queue based on linked nodes.
- **Characteristics**:
  - Supports concurrent access from multiple threads.
  - Unbounded; grows as needed.
  - Allows null elements.
- **Use Case**: When you need a thread-safe queue for high-throughput applications, such as producer-consumer scenarios.

### 5. **BlockingQueue**
- **Description**: An interface that provides additional blocking operations to support concurrent processing.
- **Characteristics**: 
  - Includes various implementations like `ArrayBlockingQueue`, `LinkedBlockingQueue`, and `PriorityBlockingQueue`.
  - Provides methods such as `put()` and `take()`, which block the caller until space becomes available or an element becomes available.
- **Use Case**: When you need a queue that supports producer-consumer patterns, where threads may need to wait for space to become available or for elements to be added.

#### **Implementations of BlockingQueue**:
- **ArrayBlockingQueue**: A bounded blocking queue backed by an array. Suitable when you want to limit the number of elements.
- **LinkedBlockingQueue**: An optionally bounded blocking queue backed by linked nodes. It provides better throughput and can grow dynamically.
- **PriorityBlockingQueue**: A blocking queue that orders elements based on priority, similar to `PriorityQueue` but with blocking capabilities.

### Summary of Use Cases:
- **Use `LinkedList`** when you want a flexible queue that can also act like a list.
- **Use `ArrayDeque`** for high-performance queue operations with dynamic sizing.
- **Use `PriorityQueue`** when the order of processing is based on priority rather than arrival time.
- **Use `ConcurrentLinkedQueue`** for thread-safe queue operations in concurrent applications.
- **Use `BlockingQueue` implementations** for producer-consumer scenarios where threads may need to wait for resources.

### Choosing the Right Implementation:
When deciding which `Queue` implementation to use, consider the following factors:
- **Thread Safety**: If multiple threads will access the queue, consider using a concurrent implementation.
- **Performance**: Choose based on the expected number of elements, the frequency of insertions/removals, and whether you need ordering.
- **Behavior**: Determine if you need FIFO, LIFO, or priority-based processing.

Understanding these implementations and their characteristics will help you choose the most appropriate one for your application’s requirements.
