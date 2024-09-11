# HW3 

Haifeng Yang



## 1. OOP Fundatmental

**Encapsulation** 

~~~java
Public class Food {
	private string name;
	private int price;
	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
~~~

 

**Polymorphism**: Polymorphism provides Code resusability, Extensibility and Dynamic method binding benefits, making it helpful in building flexible, scalable and maintainable system

- **Compile-time Polymorphism(Method overloading)**

~~~java
Public class Calculator{
	public int add(int a, int b) {
		return a + b;
	}
	public int add(int a, int b, int c) {
		return a + b + c;
	}
	public long add(int a, int b) {
		return a + (long)b;
	}
}
~~~

- **Run-time Polymorphism(Method overriding)**

~~~java
Public class Human {
	public void eat() {
		System.out.println("Eat");
	}
}

Public class Homeless extends Human{
    @Override
    public void eat() {
        System.out.println("God bless you");
    }
}

Public class Worker extends Human{
    @Override
    public void eat() {
        System.out.println("You are so gorgeous");
    }
}
~~~



**Inheritance**: Subclass inherits properties and behaviors from parent class(Super Class). Subclass can use the variables and methods defined in parent class without redefining them. Subclass can also adds its unique method or properties or modified the inherited ones.

~~~java
class Window {
	int width;
	int height;
    public window() {
        this.width  = 600;
        this.height = 600;
    }
	public window(int width, int height) {
		this.width = width;
        this.height = height;
	}
    public void getInfo() {
        System.out.println("Width: "  + width);
        System.out.println("Height: " + height);
    }
}

class canvas extends window {
    public canvas(int width, int height) {
        super(width, height);
    }
    
    public void getInfo() {
        super.getInfo();
        System.out.println("This is the canvas");
    }
}
~~~



## 2. Wrapper Class in Java

**What is wrapper class:** Wrapper encapsulate the primitive data type into an object. Doing this enables the primitive type to work with

**Why we need it**: 

- **Work with Collections**: It enables the primitive type to work with java's collections of data structure like HashMap, ArrayList etc., because the collections only work with ojects.

- **Autobox and Unbox**: 

  ~~~java
  Integer num = 2;
  int n = num;
  ~~~

- **Wrapper allows null, primitive not**
- Wrapper class provide **Utility methods**
- **Security**, Wrapper classes are **Immutable** 

## 3. HashMap, HashTable

### Similarity

- Implements the Map interface. 

- Use the hashtable to store key and value

- Both have a load factor of 0.75.

### Difference

- **Implementation**
  - **HashMap**: Has more modern implementations like ConcurrentHashMap, LinkedHashMap 
  - **HashTable**: Has an array of buckets. Hashtable uses their key-valus hashcode to store the key-value pairs into bucket. 

 ## 4. What is String pool in Java and why we need String pool? Explain String immunity

### String Pool

- It is a storage area in Java Heap memory that stores the string literals.

- String comparasion using == operator only compares the references of two strings, faster then using .equals() which is used to compare the hashcode value.

### Why We need String Pool

- It **optimize the memory** by resue the same created string and avoid keep creating new instances. Since string is immutable, it is safe to use the string pool.
- As a result of memory saving, It **reduce** the pressure of Java **garbage collection**.

### String Immunity

- Immutable means that once string is created, it will can not be modified. 

- In following example, both strings points to the same string in pool.

  ~~~java
  String str  = "hello";
  String temp = "hello";
  str == temp   // returns true because they both point to the same address. 
  temp = "Cat"; // will create a new string in String pool. and temp will point to the "Cat" string. 
  ~~~

## 5. Garbage Collection

Java Virtual Machine handles the assign and release memory automatically. Generally, it will identifies the memory that is no longer in used (memory that is not referenced by any objects). This prevents meory leaks and freed the unused memory to save space.

## 6. What are Access Modifers and their scopes in java

| Default   | Visible in the pacakge                                       |
| :-------- | ------------------------------------------------------------ |
| Private   | Visible to the class itself                                  |
| Protected | Visible to the class within the pacakge or all subclasses (evevn in different package) |
| Public    | Visible anywhere, any package                                |

## 7. Explain Final Keyword (Filed, Method, Class)

### Variable

Final defines the **constants**, make the varable immutable, prevent modification.

### Method

Final prevents the method from being override

### Class

Prevent Inheritence, Exmaple, Public Final class String. Attempt to inherit from it will cause compilation error.

~~~java
public final class ImmutableClass {
    private final String name;  // Final field
    private final int age;      // Final field

    // Constructor
    public ImmutableClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // No setter methods, only getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
~~~

Final keyword itself did **not enough** to Make class **immutable**. To make it immutable like string, we will make sure 

- **no modification method** like setter, we can have getter. 
- Use **final** and **private** keyword to the **variable** inside of the class, like string use the final for the inner string array. 
- The class is **final**, preventing subclassing and any potential modification to its behavior.

## 8. Explain Static Keyword (Filed, Method, Class). When to use?

### Variable

Static variable is initialized **only once** when the class is first loaded into memory by Java ClassLoader, the value is shared by all instances of the class.

It belongs to the class and does not initialized with new object. 

WhenIt can be used **when** we want a variable that can be shared across this class objects. For example, having a counter of how many class objects it created.

### Method

Same for method, the static method **did not belong to any class**. It will be initialized without call to create an object. 

Static Method can only access the static variables and other static method directly. Because of its unique initialization and lifecycle; also because it did not belong to any instance.

Staic method is used **when** the method does not depend on any instance variable, and when it needs to **operate** **at class level**. Usually it is used for the Utility function (Math.min(), Array.sort())

### Class

Static class is used as a **nested class** inside of another class. It can not access its outer class non-static varaible and method, it can **only access the static** ones.

Methods in static can be non-static and they behave the same like normal instance method.

Static class can be used to **organize** the classes that are **logically** belong together while keep their interaction controlled, this is **when** the inner static class do not access the outer class directly

## 9. Difference Overriding vs Overloading

Both are concept from polymorphism.

- **Compile-time Polymorphism(Method overloading)**: Multiple method in a same class can have the same name but different input parameter. The method gets called is determined by the number and type of the input parameter passed at compile time.

- **Run-time Polymorphism(Method overriding)**: When a subclass inherit from a parent class, it can provides speciifc implementation of a method that is already defined in the parent class. This behavior happens at run-time.

## 10. How Java defines a method signature? How it helps overloading and overriding

Method Signature is its **name and the parameter lists**(order, return type, number of parameters)

Notice the **return type** is not a method signature, the exmaple code will have compiler error.

~~~java
public class Calculator {
    public int add(int a, int b) { ... }
    public double add(int a, int b) { ... }  // Compilation error: method signature is the same
}
~~~

In **overloading**, the signature identifies which method to call. Thus it helps implement the polymorphism where we can do the same behavior with different input.

## 11. Difference between super and this

**Super** holds a reference to the class it inherits.

**This** holds a reference to the class itself.

## 12. How equals and hashCode work

**Equals** is used to compare two objects for equality. By **default it checks if two objects reference** are same. However in most cases we would **need to define** the equals to check for **logical equality.** The Equals could check the hashcode after checking their logical equality, however, equals() does not rely on the hashcode for its decision.



**HashCode** return the integer representing current instance of the class.

- If two objects are equal, they must have the same hashcode
- The object hashcode may only change if a property changes

- Unequal object may have same hashCode

If we want the HashMap function as expected, we would need to define the hashcode generation so that object with same variable return the same hashcode. The HashMap uses combination of equals and hashcode to determine if two objects are the same.

## 13. Java load sequence

1. **Loading the class**. The JVM loads class int memory, when
   - A static member is accessed
   - A staic method is called
   - An instance of class is created 
   - The class is referenced in any way
2. **Linking**: 
   - Verification ensures the bytecode valid and able to be executed safely
   - Prepareation allocates memory for static field and initializes them with default tags, 0 for numbers, null for objects, false for bool
   - Resolution: Resolves symbolic reference into direct references(i.e.. resolve names of classes, methods, and fields into memory references)
3. **Initialization**, class is initialized after loading and linking.

## 14. What is Polymorphism. How it implements?

**Polymorphism**: Polymorphism provides Code resusability, Extensibility and Dynamic method binding benefits, making it helpful in building flexible, scalable and maintainable system

- **Compile-time Polymorphism(Method overloading)**: Multiple method in a same class can have the same name but different input parameter. The method gets called is determined by the number and type of the input parameter passed at compile time.

- **Run-time Polymorphism(Method overriding)**: When a subclass inherit from a parent class, it can provides speciifc implementation of a method that is already defined in the parent class. This behavior happens at run-time.

## 15. What is Encapsulation. How it implements? Why We need it?

**Encapsulation** in OOP refers to binding data and the methods to operate how those data accessed and modified (**getter and setter**) together in a single class unit.

**Why?** 

- Data access protection using private 
- Encapsulation getter and setter controls the acces right
- Increase Flexibility Maintainability and Readability. 
- Reduce the coupling, Improves the modularity

## 16. Compare interface and abstract class

When to use Interface: 

- Multiple inheritance, only interface supports multiple inheritance
- Common contract
- No shared states

When to use Abstract: 

- Provide base class with default behaviors that can be inherits
- Default implementation
- Inheritance of shared state (Variable, non-abstract method)

| Feature                              | **Interface**                                                | **Abstract Class**                                           |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Purpose**                          | Used to define a contract (set of methods) that a class must implement. | Used to provide a common base class for subclasses to share common methods and fields. |
| **Keyword**                          | Defined using the `interface` keyword.                       | Defined using the `abstract` keyword.                        |
| **Multiple Inheritance**             | A class can implement **multiple interfaces**.               | A class can **extend only one abstract class** (single inheritance). |
| **Methods**                          | - Can have **abstract methods** (before Java 8).  - Can have **default** and **static methods** (from Java 8).  - Can have **private methods** (from Java 9) for internal use. | - Can have **abstract** methods and **concrete** (non-abstract) methods. |
| **Fields (Variables)**               | - **Only constants** (public, static, and final by default).  - Cannot have instance variables. | - Can have **instance variables** (with any access modifier: private, protected, public).  - Can have static or non-static fields. |
| **Constructors**                     | Interfaces **cannot have constructors**.                     | Abstract classes **can have constructors** (used by subclasses). |
| **Access Modifiers**                 | Methods are implicitly `public` and cannot have other access modifiers. | Methods can have any access modifier (`public`, `protected`, or `private`). |
| **Implementation Requirement**       | A class that implements an interface **must** implement all its methods unless the class is abstract. | A class that extends an abstract class **may** implement some or all abstract methods. If it doesn't implement all, it must also be declared abstract. |
| **Default Behavior**                 | Interfaces do not provide any default behavior (except for default methods introduced in Java 8). | Abstract classes can provide **default behavior** (with non-abstract methods). |
| **Use Case**                         | Used when you want to define a **contract** that multiple classes can implement in different ways. | Used when classes share **common behavior** or **state** and need a base class for that. |
| **Type of Inheritance**              | Represents a **"can do"** relationship (e.g., a class can **do** something by implementing an interface). | Represents a **"is-a"** relationship (e.g., a class **is a** specialized version of the abstract class). |
| **Support for Multiple Inheritance** | Supports **multiple inheritance** of interfaces. A class can implement multiple interfaces. | Does **not support multiple inheritance**. A class can extend only one abstract class. |
| **Performance**                      | Interfaces are slightly slower than abstract classes because of dynamic method resolution (unless optimized by the JVM). | Abstract classes are generally faster because method resolution is determined statically during compile-time. |

## 17. Factory Pattern

~~~java
public interface Product {
    void create();
}

public class ProductA implements Product {
    @override
    public void create() {
        System.out.println("ProductA created.");
    }
}

public class ProductB implements Product {
    @override
    public void create() {
        System.out.println("ProductB created.");
    }
}

public class ProductFactory{
    private Map<String, Supplier<Product>> registry = new HashMapM<>();
    
    public void registerProduct(String productId, Supplier<Product> supplier) {
        registry.put(productId, supplier);
    }
    
    public Product createProduct(String productId) {
        Supplier<Product> productSupplier = registry.get(productId);
        if(productSupplier != null)
            return productSupplier.get();
       	throw new IllegalArgumentException("No product found for id: " + productId);
    }
}
public class Main {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        // Register products with the factory
        factory.registerProduct("ProductA", ProductA::new);  // Register ProductA
        factory.registerProduct("ProductB", ProductB::new);  // Register ProductB
        // Create products using the factory
        Product product1 = factory.createProduct("ProductA");
        product1.create();  // Output: ProductA created.
        Product product2 = factory.createProduct("ProductB");
        product2.create();  // Output: ProductB created.
    }
}

~~~



## 18. Adapter Pattern

~~~java
// The pattern is a wrapper of existing class into compatible interface
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
// Adaptee with an incompatible interface
public class VlcPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }
}
// Adapter
public class MediaAdapter implements MediaPlayer {
    private VlcPlayer vlcPlayer;

    public MediaAdapter() {
        vlcPlayer = new VlcPlayer();  // Composition: using VlcPlayer inside
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);  // Adapting VlcPlayer's method to MediaPlayer interface
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported.");
        }
    }
}
~~~



## 19. Thread-Safe Singleton Pattern

~~~java
// Lazy Load implementation
// Only when the static SingletonHolder is loaded and initialized, it happens when the getInstance() is called for the first time, the loading and initialization is thread saft, thus this is thread safe
public class Singleton {
	// prevent access
    private Singleton() {}
    static{
    	///do something
    }
	// Static inner class to hold the Singleton instance
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
	// The only way to access the Singleton instance
    // Public static method to get the Singleton instance
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
~~~



## 20. Design a parking lot

Here's the running result. See Coding folder.

~~~
Vehicle parked at spot: S3
Ticket Generated: TICKER_SERIAL0
Vehicle parked at spot: M1
Ticket Generated: TICKER_SERIAL1
Vehicle parked at spot: L1
Ticket Generated: TICKER_SERIAL2
Processing ticket...
Parking Time: 2 Seconds
Parking Fee: 10.0
Vehicle removed from spot: S3
Parking Time: 2 Seconds
Parking Fee: 20.0
Vehicle removed from spot: M1
~~~



## 21. What are Queue interface implementations? what are differences? When to use?

LinkedList, PrioirityQueue, ArrayQueue, ConcurrentLinkedQueue, Blocking Queue (LinkedBlockingQueue, ArrayBlocking Queue).

Depends on our requirements like performance, thread safety, function like if is priority queue, and use cases.

**LiknedList**: Simple queue with linkedlist behavior

**PrioirtyQueue**: When we need a specific order

**ArrayDeque**: When we need to use Deque

**ConcurrentLinkedList**: Queue with concurrent ability, it is non-blocking queue

**BlockingQueue**: Thread-saft. Specifically in Producer-Consumer problems
