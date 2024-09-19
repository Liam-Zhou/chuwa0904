## 1. Write up Example code to demonstrate the three foundmental concepts of OOP

```
// Encapsulation: Class with private fields and public getter/setter methods
public class Vehicle {
    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void drive() {
        System.out.println(brand);
    }
}

// Inheritance: extends from parent class
class Car extends Vehicle {
    private final int numberOfDoors;

    public Car(String brand, int numberOfDoors) {
        super(brand);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

// Polymorphism: Override
    @Override
    public void drive() {
        System.out.println(getBrand() + " car with " + numberOfDoors + " doors is driving at ");
    }
}
```

## 2. What is wrapper class in Java and Why we need wrapper class?

- wrapper classes are used to convert primitive data types into objects.
- Why using wrapper class:
  - Working with **Collections**, because Collections in Java only work with objects and cannot store primitive types.
  - Primitive types cannot be assigned `null` but wrapper classes can.
  - Wrapper classes are immutable and it will be safer to use.

## 3. What is the difference between HashMap and HashTable?

- HashMap is not synchronized (not thread-safe), whereas Hashtable is synchronized (thread-safe but slower)
- HashMap allows null keys and values, whereas Hashtable does not.
- HashMap is generally faster than Hashtable due to lack of synchronization
- HashMap is a modern implementation, while Hashtable is a legacy class from Java 1.0

## 4. What is String pool in Java and why we need String pool? Explain String immunity

**String Pool:** In Java, the String Pool (also called the String Constant Pool) is a special area in the heap memory where string literals are stored

**why String Pool:** helps avoid creating duplicate String objects for **Memory Efficiency** and improve performance

**String immunity:** Once a String object is created, it cannot be changed. Any modification to a string results in the creation of a new String object, rather than altering the original object

## 5. Explain garbage collection?

Garbage Collection (GC) in Java is an automatic memory management process that helps reclaim memory occupied by objects that are no longer in use (i.e., objects that are unreachable).

In Java, memory is allocated on the heap, and objects are created dynamically. The garbage collector is responsible for automatically identifying and freeing the memory occupied by objects that are no longer referenced by any part of the program, thus avoiding memory leaks and improving performance.

## 6. What are access modifiers and their scopes in Java?

Default (no explicit modifier keyword): declarations are visible only within the package (package private)

Private: declarations are visible within the class only

Protected: declarations are visible within the package or all subclasses

Public: declarations are visible everywhere

## 7. Explain final key word? (Filed, Method, Class)

**final** keyword defines constants:

- prevent constant variable modification
- prevent method overriding
- prevent class inheritance

## 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?

- **Static Fields:** A static variable is shared among all instances of a class.
- **Static Methods:** A static method belongs to the class rather than instances of the class. It can be called without creating an object of the class.
- **Static Class (Nested Static Class):** they can be instantiated without an instance of the outer class.

Usually used for Common Static Methods: Math.sqrt(), Integer, String, Math, System etc.

## 9. What is the differences between overriding and overloading?

- Static Polymorphism - Overload (same class) - compile time:
- Dynamic Polymorphism - Override (child class) - run time
  - **Private** and **final** methods can NOT be overridden

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

**Method Signature**: identify and differentiate methods  
 contains:  
 **Method Name**, **Parameter List**(number, order, and types of the parameters)  
 Not contains:  
 Return Type, Access Modifiers, Exceptions, Parameter Names

Method Overloading occurs when multiple methods in the same class (or subclass) have the **same name** but **different signatures** (i.e., they differ in the number, type, or order of parameters). The method signature is the determining factor that allows Java to differentiate between overloaded methods.

Method Overriding happens when a subclass provides a specific implementation of a method that is already defined in its superclass. The method in the subclass must have the **same signature** as the one in the superclass for it to override the method properly.

## 11. What is the differences between super and this?

**this**:

- reference and differentiate with Instance Variables
- Calling Another Constructor (Constructor Chaining)

  ```
  class Person {
      private String name;
      private int age;

      // Constructor 1
      public Person() {
          this("Unknown", 0);  // Calls Constructor 2
      }

      // Constructor 2
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
  ```

**super**:

- Calling the Superclass Constructor:

  ```
  class Animal {
        String name;

        public Animal(String name) {
           this.name = name;
        }
     }

     class Dog extends Animal {
        int age;

        public Dog(String name, int age) {
           super(name);  // Calls the constructor of Animal class
           this.age = age;
        }
     }
  ```

- Accessing Superclass Methods/Fields:

  ```
  class Animal {
     void sound() {
        System.out.println("Animal sound");
     }
  }

  class Dog extends Animal {
     void sound() {
        super.sound();  // Calls the sound method in Animal
        System.out.println("Dog barks");
     }
  }
  ```

## 12. Explain how equals and hashCode work.

- The **equals()** method is used to compare two objects for equality. The default implementation in the Object class simply compares the memory addresses (references) of the objects, meaning it checks if two object references point to the same object in memory.
- The hashCode() method returns an integer hash code for the object. It is used in conjunction with hash-based collections such as HashMap, HashSet, and Hashtable, which use hash codes to organize objects efficiently.

## 13. What is the Java load sequence?

1. **Static Variables**: Are initialized when the class is loaded (before any object of the class is created) and shared across all instances of the class.
2. **Static Block**: Executes once when the class is loaded (immediately after static variables are initialized, before any object of the class is created)
3. **Constructor**: The constructor is called each time an object is created, (after the static block has been executed)

## 14. What is Polymorphism ? And how Java implements it ?

Polymorphism refers to the same object exhibiting different forms and behaviors.

Java implements it by Overload & Override:

- Static Polymorphism - Overload (same class) - compile time
- Dynamic Polymorphism - Override (child class) - run time

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?

Encapsulation refers to binding the data and the methods to manipulate that data together in a single unit (class).

Implement with **_Getter_**/**_Setter_** (methods) and Access Modifiers

Why Do We Need Encapsulation?
- Data Hiding
- Controlled Access (by access modifier)

## 16. Compare interface and abstract class.

- abstract class:

  - Can have **constructor**, **static** variables and methods
  - abstract class can have Non-abstract/Normal methods
  - An abstract class **cannot** be instantiated
  - Child classes **MUST** implement **ALL** the abstract methods declared in the parent abstract class

- Interface

  - Interface **cannot** be declared **private** or **protected**.
  - Interface can be declared **public** or **no access modifier**(private to package).
  - Inside Interface all methods are **abstract methods** and have **no implementation**, expect from
    - `default` method (Java 8)  
       Can either use this default implementation or override it
    - `Static` methods (Java 8)  
       Cannot be overridden
    - `Private` methods (Java 9)  
       Can be used to encapsulate code shared by multiple default methods or static methods within the interface
  - An interface can have:
    - `abstract` method(s)
    - `static` method(s)
    - `default` method(s) - Java 8 new features
    - `private` method(s)
    - `private static` method(s)
    - `public static final` variable(s)

    
  - a class **MUST** implement **ALL** of the abstract method(s) declared in it.
  - **cannot** be **instantiated**
  - **cannot** have **constructor**(s) in it

## 17. Write a factory pattern in code.
```
public interface Vehicle {
    void start();
}

public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike has started.");
    }
}

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car has started.");
    }
}

public class VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        if (vehicleType.equalsIgnoreCase("Car")) {
            return new Car();  // Returns Car object
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            return new Bike();  // Returns Bike object
        }
        return null;
    }
}

public class FactoryPatternDemo {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle vehicle1 = vehicleFactory.getVehicle("Car");
        vehicle1.start();  // Output: Car has started.

        Vehicle vehicle2 = vehicleFactory.getVehicle("Bike");
        vehicle2.start();  // Output: Bike has started.
    }
}
```

## 18. Write a adapter pattern in code.
```
public interface Rectangle {
    void draw(int x, int y, int width, int height);
}

public class LegacyRectangle {
    public void drawRectangle(int x1, int y1, int x2, int y2) {
        System.out.println("Drawing Legacy Rectangle from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");
    }
}

public class RectangleAdapter implements Rectangle {
    private LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void draw(int x, int y, int width, int height) {signature
        int x2 = x + width;
        int y2 = y + height;
        legacyRectangle.drawRectangle(x, y, x2, y2);  // Call the adaptee's method
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        // Create the LegacyRectangle (adaptee)
        LegacyRectangle legacyRectangle = new LegacyRectangle();

        // Create the adapter and use it
        Rectangle rectangle = new RectangleAdapter(legacyRectangle);

        rectangle.draw(10, 20, 30, 40); 
    }
}
```

## 19. Write singleton pattern in code, make sure your code is thread-safe
```
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
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

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

## 20. design a parking lot

see project in `codingQuestions/coding1`

## 21. What are Queue interface implementations and what are the differences and when to use what?

| Implementation              | Thread-Safe | Allows Null | Use Case                                                                                           |
|-----------------------------|-------------|-------------|----------------------------------------------------------------------------------------------------|
| **`LinkedList`**             | No          | Yes         | Simple FIFO behavior or deque, when you don't need thread safety.                                   |
| **`PriorityQueue`**          | No          | No          | Use when elements need to be ordered by priority, not FIFO.                                         |
| **`ArrayDeque`**             | No          | No          | High-performance deque or stack when thread safety isn't required.                                  |
| **`ConcurrentLinkedQueue`**  | Yes         | No          | Non-blocking queue for highly concurrent environments.                                              |
| **`ArrayBlockingQueue`**     | Yes         | No          | Bounded blocking queue, typically for producer-consumer patterns in a multi-threaded environment.   |
| **`LinkedBlockingQueue`**    | Yes         | No          | Bounded or unbounded blocking queue for thread-safe operations.                                     |
| **`PriorityBlockingQueue`**  | Yes         | No          | Thread-safe priority-based queue when elements need to be ordered by priority.                      |
