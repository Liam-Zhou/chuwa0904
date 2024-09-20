1. Write an example code to demonstrate the three fundamental concepts of OOP: **Encapsulation**, **Polymorphism**, and **Inheritance**

   **Encapsulation**:

   Encapsulation is the bundling of data and methods that operate on that data into a single unit or class. 

   ```java
   class Person {
       // Private variables encapsulated
       private String name;
       private int age;
   
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
           this.age = age;
       }
   }
   
   ```

   **Inheritance**:

   Inheritance allows a class (subclass) to inherit the properties and methods of another class (superclass).

   ```java
   class Animal {
       public void eat() {
           System.out.println("This animal is eating");
       }
   }
   
   class Dog extends Animal {
       public void bark() {
           System.out.println("The dog is barking");
       }
   }
   
   ```

   **Polymorphism**:

   In programming, **polymorphism** refers to the same object exhibiting different **forms and behaviors**.

   - **Static** Polymorphism - **Overload** (same class) - **compile time**

     Example of Method Overloading:

     ```java
     class MathOperations {
         // Method to add two numbers
         public int add(int a, int b) {
             return a + b;
         }
         
         // Overloaded method to add three numbers
         public int add(int a, int b, int c) {
             return a + b + c;
         }
         
         // Overloaded method to add two double numbers
         public double add(double a, double b) {
             return a + b;
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             MathOperations math = new MathOperations();
             
             System.out.println(math.add(5, 10));        // Calls the method with 2 int arguments
             System.out.println(math.add(5, 10, 15));    // Calls the method with 3 int arguments
             System.out.println(math.add(5.5, 2.5));     // Calls the method with 2 double arguments
         }
     }
     ```

     Output:

     ```
     15
     30
     8.0
     ```

     In the above example:

     - The `add` method is overloaded based on the number and type of arguments.
     - The appropriate method is chosen at compile-time depending on the arguments passed.

   - **Dynamic** Polymorphism - **Override** (child class) - **run time**

     Example of Method Overriding:

     ```java
     class Animal {
         // Method in the superclass
         public void sound() {
             System.out.println("This animal makes a sound");
         }
     }
     
     class Dog extends Animal {
         // Overriding the sound method in Dog class
         @Override
         public void sound() {
             System.out.println("Dog barks");
         }
     }
     
     class Cat extends Animal {
         // Overriding the sound method in Cat class
         @Override
         public void sound() {
             System.out.println("Cat meows");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             Animal myAnimal = new Animal();   // Animal reference
             Animal myDog = new Dog();         // Animal reference, but Dog object
             Animal myCat = new Cat();         // Animal reference, but Cat object
             
             myAnimal.sound();  // Calls Animal's sound method
             myDog.sound();     // Calls Dog's overridden sound method
             myCat.sound();     // Calls Cat's overridden sound method
         }
     }
     ```

     Output:

     ```java
     This animal makes a sound
     Dog barks
     Cat meows
     ```

     In the above example:

     - The `sound` method is overridden in both the `Dog` and `Cat` classes.
     - At runtime, the actual object's method (Dog or Cat) is invoked, demonstrating **runtime polymorphism**.

   

2. What is a **wrapper class** in Java and why do we need it?

   What is a Wrapper Class in Java?

   A **wrapper class** in Java provides a way to use primitive data types (like `int`, `char`, `boolean`, etc.) as objects. These classes encapsulate a primitive value in an object, allowing primitives to be used in contexts that require objects, such as in Java's collection frameworks. Wrapper classes are part of the `java.lang` package.

   Main Wrapper Classes in Java:

   Each primitive data type in Java has a corresponding wrapper class:
   - **Boolean** for `boolean`
   - **Character** for `char`
   - **Byte** for `byte`
   - **Short** for `short`
   - **Integer** for `int`
   - **Long** for `long`
   - **Float** for `float`
   - **Double** for `double`

   Why Do We Need Wrapper Classes?

   Wrapper classes are crucial for:
   - **Collections Usage**: Java collections such as `ArrayList` and `HashMap` only store objects, not primitives. Wrapper classes allow primitives to be used as objects.
   - **Utility Methods**: They provide utility methods for converting, comparing, and processing values, which are not available with primitives.
   - **Null Support**: Unlike primitives, their wrappers can be null, which is useful for indicating the absence of a value.

   Example of Usage:

   Using `Integer` and `ArrayList`

   ```java
   import java.util.ArrayList;
   
   public class WrapperClassExample {
       public static void main(String[] args) {
           ArrayList<Integer> numbers = new ArrayList<>();
           
           // Adding elements (Autoboxing)
           numbers.add(10);  // Here, Java automatically converts 'int' to 'Integer'
           numbers.add(20);
           
           // Retrieving elements (Unboxing)
           int firstNumber = numbers.get(0);  // Converts 'Integer' back to 'int'
           
           System.out.println("First number: " + firstNumber);
       }
   }
   ```

   Output:

   ```
   First number: 10
   ```

   In this example:
   - **Autoboxing**: Automatically converting a primitive `int` to an `Integer` object when adding to `ArrayList`.
   - **Unboxing**: Automatically converting an `Integer` back to a primitive `int` when retrieving from the `ArrayList`.

3. What is the difference between **HashMap** and **HashTable**?

   - **HashMap** is non-synchronized. It is not thread-safe and can’t be shared between many threads without proper synchronization code whereas **Hashtable** is synchronized. It is thread-safe and can be shared with many threads.
   - **HashMap** allows one null key and multiple null values whereas **Hashtable** doesn’t allow any null key or value.
   - **HashMap** is generally preferred over **HashTable** if thread synchronization is not needed.

   why HashTable doesn’t allow null and HashMap do? 

   The **answer** is simple. In order to successfully store and retrieve objects from a HashTable, the objects used as keys must implement the hashCode method and the equals method. Since null is not an object, it can’t implement these methods. HashMap is an advanced version and improvement on the Hashtable. HashMap was created later.

4. What is **Stringpool **in Java and why we need Stringpool ? Explain String immunity.

   The **String pool** is a memory area in Java where literal strings are stored to save memory. If a string literal already exists in the pool, Java returns the reference instead of creating a new object.

   ```java
   public class StringPoolExample {
       public static void main(String[] args) {
           String str1 = "Java";
           String str2 = "Java";  // Reuses the "Java" from the String pool
   
           if (str1 == str2) {
               System.out.println("Both str1 and str2 point to the same object in the String pool.");
           }
       }
   }
   ```

   Output:
   ```bash
   Both str1 and str2 point to the same object in the String pool.
   ```

   **String immutability** means that once a string is created, it cannot be changed. When you modify a string, a new object is created in memory.

   ```java
   public class StringImmutabilityExample {
       public static void main(String[] args) {
           String original = "Hello";
           String modified = original.concat(" World!");  // Creates a new string
   
           System.out.println("Original String: " + original);  // Output: Hello
           System.out.println("Modified String: " + modified);  // Output: Hello World!
       }
   }
   
   ```

   Output:
   ```bash
   Original String: Hello
   Modified String: Hello World!
   ```

5. Explain **Garbage collection** 

   **Garbage collection** in Java is the process of automatically identifying and reclaiming memory that is no longer in use by the program. The **Garbage Collector (GC)** runs in the background, freeing up memory by removing objects that are no longer referenced.

6. What are access modifiers and their scopes in Java?

   - **public**: Accessible from anywhere.

   - **private**: Accessible only within the class.

   - **protected**: Accessible within the package and subclasses.

   - **default** (no modifier): Accessible within the same package.

   - Summary of Access Modifiers:

     | Access Modifier | Within Same Class | Within Same Package | Subclass in Different Package | Everywhere |
     | --------------- | ----------------- | ------------------- | ----------------------------- | ---------- |
     | **public**      | Yes               | Yes                 | Yes                           | Yes        |
     | **protected**   | Yes               | Yes                 | Yes                           | No         |
     | **default**     | Yes               | Yes                 | No                            | No         |
     | **private**     | Yes               | No                  | No                            | No         |

7. Explain the `final` keyword (Field, Method, Class).

   - **Final field**: A final field cannot be changed after initialization.
   - **Final method**: A final method cannot be overridden by subclasses.
     - a **final method** can be **overloaded**, but it cannot be **overridden**.
   - **Final class**: A final class cannot be extended (no subclasses).

8. Explain the `static` keyword (Field, Method, Class). When do we usually use it?

   - **Static field**: Shared among all instances of the class.

   - **Static method**: Can be called without an instance of the class.

   - **Static class**: Inner classes can be declared static to belong to the outer class.

   - Example using `static` keyword for Field, Method, and Class

     ```java
     // Static class nested inside a regular class
     class OuterClass {
         
         // Static field (shared by all instances)
         public static int staticCounter = 0;
         
         // Static method (can be called without an instance)
         public static void incrementCounter() {
             staticCounter++;
             System.out.println("Counter incremented to: " + staticCounter);
         }
         
         // Static nested class (can be instantiated without an instance of the outer class)
         public static class StaticNestedClass {
             public void displayMessage() {
                 System.out.println("This is a message from the static nested class.");
             }
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             // Accessing the static field without creating an instance
             System.out.println("Initial staticCounter: " + OuterClass.staticCounter);
             
             // Calling the static method without creating an instance
             OuterClass.incrementCounter();
             OuterClass.incrementCounter();
             
             // Instantiating and using the static nested class
             OuterClass.StaticNestedClass nestedClass = new OuterClass.StaticNestedClass();
             nestedClass.displayMessage();
         }
     }
     ```

     Explanation:

     1. **Static Field** (`staticCounter`):
        - `staticCounter` is a static field, which means it is shared across all instances of `OuterClass`. Changes to this field affect all instances of the class.
        - It is accessed directly through the class name (`OuterClass.staticCounter`), without needing to create an instance of `OuterClass`.
        - When the `incrementCounter` method is called, it modifies the static field `staticCounter`.

        **How it works**: Static fields are stored in a special part of memory, and only one copy of the static field exists for the entire class, regardless of how many objects are created from the class.

     2. **Static Method** (`incrementCounter`):
        - `incrementCounter` is a static method that can be called without an instance of `OuterClass`.
        - It can access and modify the static field `staticCounter`.
        - Static methods cannot access non-static fields or methods of the class unless an instance of the class is provided because static members belong to the class itself, not to individual objects.

        **How it works**: Static methods are utility methods that perform operations that are not tied to a specific object instance. They can only directly access static fields and other static methods.

     3. **Static Nested Class** (`StaticNestedClass`):
        - `StaticNestedClass` is a static nested class, meaning it can be instantiated without having an instance of `OuterClass`.
        - It behaves similarly to a top-level class but is logically scoped within `OuterClass`.

        **How it works**: A static nested class doesn't require an instance of the outer class to be instantiated. This makes it different from an inner (non-static) class, which always requires an instance of the outer class.

     Output of the Above Program:

     ```
     Initial staticCounter: 0
     Counter incremented to: 1
     Counter incremented to: 2
     This is a message from the static nested class.
     ```

     Summary:

     - **Static Field**: Shared by all instances of a class and can be accessed without an instance of the class.
     - **Static Method**: Can be called without an instance and can only access static fields and methods.
     - **Static Nested Class**: Can be instantiated without an instance of the outer class and behaves like a normal class but with a logical grouping inside the outer class.

9. What are the differences between overriding and overloading?

   - **Overriding**: Redefining a method in a subclass.

   - **Overloading**: Having multiple methods with the same name but different parameter lists.

10. Explain how Java defines a method signature, and how it helps with overloading and overriding.

    A **method signature** in Java is defined by the method's name and its parameter types (number, order, type). It helps in method overloading by distinguishing methods with different signatures.

    In **overriding**, the method signature must be the same in both superclass and subclass, allowing polymorphism

11. What are the differences between `super` and `this`?

    **`this`** refers to the current object.

    **`super`** refers to the parent class and is used to access the parent class's methods or constructors.

12. Explain how `equals()` and `hashCode()` work.

    Default Behavior of `equals()` and `hashCode()` in Java

    - **`equals()`** (default behavior in `Object` class):
      - By default, `equals()` in the `Object` class compares the **memory addresses** (references) of two objects.
      - Two objects are considered equal **only if they refer to the exact same object** in memory (i.e., `==` comparison).
      
      **Example of default `equals()` behavior**:
      ```java
      public class Main {
          public static void main(String[] args) {
              Object obj1 = new Object();
              Object obj2 = new Object();
              
              System.out.println(obj1.equals(obj2));  // false (different memory references)
              System.out.println(obj1.equals(obj1));  // true (same memory reference)
          }
      }
      ```

    - **`hashCode()`** (default behavior in `Object` class):
      - The default `hashCode()` method in the `Object` class returns a hash code value that is typically derived from the **memory address** of the object.
      - Each object will have a unique hash code unless overridden.

      **Example of default `hashCode()` behavior**:
      ```java
      public class Main {
          public static void main(String[] args) {
              Object obj1 = new Object();
              Object obj2 = new Object();
              
              System.out.println(obj1.hashCode());  // Unique hash code for obj1
              System.out.println(obj2.hashCode());  // Unique hash code for obj2
          }
      }
      ```

    Summary:

    - **`equals()`** (default): Compares memory references. Objects are equal only if they are the same instance.
    - **`hashCode()`** (default): Returns a hash value based on the object's memory address, ensuring a unique hash for each object instance.

13. What is the Java **load sequence**?

    The **Java class load sequence** involves loading, linking, and initializing:

    1. **Loading**: The class is loaded by the class loader.
    2. **Linking**: Verifies, prepares, and resolves the class.
    3. **Initialization**: Static fields and blocks are initialized.

14. What is **Polymorphism** ? And how Java implements it ?

    **Polymorphism** is the ability of a variable, function, or object to take on multiple forms. Java implements it through:

    1. **Method overriding**: Achieved through inheritance.
    2. **Method overloading**: Multiple methods with the same name but different signatures.

15. What is **Encapsulation** ? How Java implements it? And why we need encapsulation? 

    **Encapsulation** is the bundling of data (variables) and methods that operate on the data into a single unit (class). It restricts access to the internal details of the class, promoting security and modularity.

16. Compare **interface** and **abstract class**.

    ### What is an Abstract Class?

    An **abstract class** in Java is a class that cannot be instantiated on its own and is meant to be extended by other classes. It can contain both **abstract methods** (methods without a body, which must be implemented by subclasses) and **concrete methods** (methods with a body).

    - **Abstract methods**: Methods that are declared without an implementation (body) and must be implemented by any non-abstract subclass.
    - **Concrete methods**: Regular methods with a body that can be inherited by subclasses.

    #### Example of an Abstract Class:
    ```java
    abstract class Animal {
        // Abstract method (no implementation)
        public abstract void sound();
    
        // Concrete method (has an implementation)
        public void sleep() {
            System.out.println("This animal is sleeping.");
        }
    }
    
    class Dog extends Animal {
        // Implementing the abstract method
        public void sound() {
            System.out.println("Dog barks.");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Dog dog = new Dog();
            dog.sound();  // Output: Dog barks.
            dog.sleep();  // Output: This animal is sleeping.
        }
    }
    ```
    In this example, `Animal` is an abstract class with an abstract method `sound()` and a concrete method `sleep()`. The `Dog` class provides the implementation for the `sound()` method.

    **Can abstract classes have constructors?** Yes, abstract classes can have constructors, and they are used to initialize shared fields. These constructors are called when a subclass is instantiated.

    ---

    ### What is an Interface?

    An **interface** in Java is a blueprint for a class. It can only contain **abstract methods** (prior to Java 8) or **default methods** and **static methods** (introduced in Java 8). Interfaces define a set of methods that implementing classes must provide. A class can implement multiple interfaces, which helps achieve multiple inheritance in Java.

    - **Abstract methods**: Methods without implementation that must be implemented by the class that implements the interface.
    - **Default methods**: Methods with a default implementation in the interface (introduced in Java 8).
    - **Static methods**: Methods that can be called on the interface itself (introduced in Java 8).

    #### Example of an Interface:
    ```java
    interface Animal {
        // Abstract method (no implementation)
        public void sound();
    
        // Default method (introduced in Java 8)
        default void sleep() {
            System.out.println("This animal is sleeping.");
        }
    }
    
    class Cat implements Animal {
        // Implementing the abstract method
        public void sound() {
            System.out.println("Cat meows.");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Cat cat = new Cat();
            cat.sound();  // Output: Cat meows.
            cat.sleep();  // Output: This animal is sleeping.
        }
    }
    ```
    In this example, `Animal` is an interface with an abstract method `sound()` and a default method `sleep()`. The `Cat` class provides the implementation for `sound()`.

    ---

    ### Comparison Between Abstract Class and Interface

    | **Feature**              | **Abstract Class**                                           | **Interface**                                                |
    | ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
    | **Multiple Inheritance** | A class can extend only one abstract class.                  | A class can implement multiple interfaces.                   |
    | **Methods**              | Can contain both abstract and concrete methods.              | Can only contain abstract methods (prior to Java 8), default, and static methods (from Java 8 onward). |
    | **Fields**               | Can have instance variables (with any access modifier).      | All fields are `public`, `static`, and `final` (constants).  |
    | **Access Modifiers**     | Abstract methods can have any access modifier.               | All abstract methods are implicitly `public`.                |
    | **Use Case**             | Used when classes share common behaviors (both abstract and concrete). | Used to define a contract for what classes must do, without dictating how. |
    | **Constructors**         | Can have constructors.                                       | Cannot have constructors.                                    |
    | **Inheritance**          | Can extend only one class (abstract or not).                 | Can implement multiple interfaces, allowing multiple inheritance. |
    | **When to Use**          | Use when you want to provide some shared code or default behavior. | Use when you want to define a contract that multiple classes should follow without any default implementation. |

    **Use case perspective**

    **Use Interface** when:
     define common behavior across **unrelated classes**.
     need **multiple inheritance** (a class can implement multiple interfaces).
     defining a contract or common behavior, and you don’t need to share state or implementation.

    **Use Abstract Class** when:
     required to share **common behavior and state** across **related classes**.
     needs **default/example implementation** that can be reused by subclasses. needs to share **instance variables** (state) or common initialization logic.
     When your class hierarchy is closely related, and you can benefit from **code reuse**.

17. Write a factory pattern in code.

    The **Factory Pattern** is a creational design pattern that provides a way to create objects without exposing the creation logic to the client. Instead, the creation logic is encapsulated in a factory class.

    ```java
    // Step 1: Create an interface
    interface Animal {
        void speak();
    }
    
    // Step 2: Create concrete classes implementing the same interface
    class Dog implements Animal {
        public void speak() {
            System.out.println("Dog barks");
        }
    }
    
    class Cat implements Animal {
        public void speak() {
            System.out.println("Cat meows");
        }
    }
    
    // Step 3: Create a Factory class to generate objects
    class AnimalFactory {
        public Animal getAnimal(String animalType) {
            if (animalType == null) {
                return null;
            }
            if (animalType.equalsIgnoreCase("DOG")) {
                return new Dog();
            } else if (animalType.equalsIgnoreCase("CAT")) {
                return new Cat();
            }
            return null;
        }
    }
    
    // Step 4: Use the Factory to create objects
    public class Main {
        public static void main(String[] args) {
            AnimalFactory factory = new AnimalFactory();
    
            // Create a Dog
            Animal animal1 = factory.getAnimal("DOG");
            animal1.speak();  // Output: Dog barks
    
            // Create a Cat
            Animal animal2 = factory.getAnimal("CAT");
            animal2.speak();  // Output: Cat meows
        }
    }
    
    ```

18. Write a adapter pattern in code.

    The **Adapter Pattern** is a structural design pattern that allows objects with incompatible interfaces to work together by wrapping one of the objects with an adapter. It acts as a bridge between two interfaces.

    ```java
    // Step 1: Create an interface for the desired functionality
    interface MediaPlayer {
        void play(String audioType, String fileName);
    }
    
    // Step 2: Create an advanced media player with additional functionality
    class AdvancedMediaPlayer {
        public void playMp4(String fileName) {
            System.out.println("Playing MP4 file: " + fileName);
        }
    
        public void playVlc(String fileName) {
            System.out.println("Playing VLC file: " + fileName);
        }
    }
    
    // Step 3: Create an Adapter class to adapt the AdvancedMediaPlayer to the MediaPlayer interface
    class MediaAdapter implements MediaPlayer {
        AdvancedMediaPlayer advancedMediaPlayer;
    
        public MediaAdapter(String audioType) {
            advancedMediaPlayer = new AdvancedMediaPlayer();
        }
    
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("mp4")) {
                advancedMediaPlayer.playMp4(fileName);
            } else if (audioType.equalsIgnoreCase("vlc")) {
                advancedMediaPlayer.playVlc(fileName);
            }
        }
    }
    
    // Step 4: Create a concrete class implementing the MediaPlayer interface
    class AudioPlayer implements MediaPlayer {
        MediaAdapter mediaAdapter;
    
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing MP3 file: " + fileName);
            } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media type: " + audioType);
            }
        }
    }
    
    // Step 5: Use the Adapter in the client code
    public class Main {
        public static void main(String[] args) {
            AudioPlayer player = new AudioPlayer();
    
            player.play("mp3", "song.mp3");  // Output: Playing MP3 file: song.mp3
            player.play("mp4", "video.mp4"); // Output: Playing MP4 file: video.mp4
            player.play("vlc", "movie.vlc"); // Output: Playing VLC file: movie.vlc
            player.play("avi", "movie.avi"); // Output: Invalid media type: avi
        }
    }
    
    ```

19. Write singleton pattern in code, make sure your code is thread-safe.

    The **Singleton Pattern** ensures that a class has only one instance and provides a global point of access to it. The thread-safe version ensures that the Singleton is instantiated safely even in multithreaded environments.

    ```java
    public class Singleton {
        // Step 1: Create a private static variable to hold the single instance
        private static volatile Singleton instance;
    
        // Step 2: Make the constructor private so that this class cannot be instantiated from outside
        private Singleton() {
            // Prevent instantiation
        }
    
        // Step 3: Provide a public static method to get the single instance, with thread safety
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
    
        // Example method to demonstrate Singleton functionality
        public void showMessage() {
            System.out.println("Singleton instance invoked.");
        }
    
        public static void main(String[] args) {
            Singleton singleton = Singleton.getInstance();
            singleton.showMessage();  // Output: Singleton instance invoked.
        }
    }
    
    ```

    How it works:

    1. **Private constructor**: Prevents instantiation of the class from outside.
    2. **Static method (`getInstance`)**: Provides the global access point to the single instance of the class.
    3. **Double-checked locking**: Ensures that the Singleton is created only once, even in multithreaded environments. The `volatile` keyword ensures that multiple threads handle the instance variable correctly.
    4. **Thread-safe**: Using `synchronized` ensures that only one thread can create the instance at a time.

20. Design a parking lot (put the code to **codingQuestions**/coding1 folder)

    If you have no ability to design it, please find the solution in internet, then understand it, and re-type it. (**Do NOT just copy and paste**)

    Parking Lot Design in Java (OOP)

    Designing a parking lot in Java using Object-Oriented Programming (OOP) involves defining the entities, their attributes, and the relationships between them. Below is a basic design that includes essential features like parking spots, vehicles, and parking lot operations.

    Entities to Include:

    1. **Parking Lot**: Represents the parking lot itself, containing different parking spots.
    2. **Parking Spot**: Represents a single spot where vehicles can park. Parking spots can be of different sizes (e.g., for cars, bikes, or trucks).
    3. **Vehicle**: Represents the vehicles that need to be parked. Different types of vehicles may need different spot sizes.
    4. **Parking Ticket**: Represents the ticket given to vehicles when they park, tracking entry time.

    Classes Overview:

    - **ParkingLot**: Manages parking spots and operations like parking a vehicle and removing a vehicle.
    - **ParkingSpot**: Represents a single parking spot and keeps track of whether it's occupied.
    - **Vehicle**: Base class for different types of vehicles.
    - **Car/Bike/Truck**: Subclasses of `Vehicle` with different size requirements.
    - **ParkingTicket**: Represents a ticket issued when a vehicle is parked.

    Code Implementation:

    ```java
    import java.util.*;
    
    // Vehicle class and its subclasses (Car, Bike, Truck)
    abstract class Vehicle {
        private String licensePlate;
        private VehicleSize size;
    
        public Vehicle(String licensePlate, VehicleSize size) {
            this.licensePlate = licensePlate;
            this.size = size;
        }
    
        public String getLicensePlate() {
            return licensePlate;
        }
    
        public VehicleSize getSize() {
            return size;
        }
    }
    
    class Car extends Vehicle {
        public Car(String licensePlate) {
            super(licensePlate, VehicleSize.COMPACT);
        }
    }
    
    class Bike extends Vehicle {
        public Bike(String licensePlate) {
            super(licensePlate, VehicleSize.SMALL);
        }
    }
    
    class Truck extends Vehicle {
        public Truck(String licensePlate) {
            super(licensePlate, VehicleSize.LARGE);
        }
    }
    
    // Enum for Vehicle Sizes
    enum VehicleSize {
        SMALL, COMPACT, LARGE
    }
    
    // Parking Spot class
    class ParkingSpot {
        private VehicleSize spotSize;
        private Vehicle currentVehicle;
    
        public ParkingSpot(VehicleSize spotSize) {
            this.spotSize = spotSize;
            this.currentVehicle = null;
        }
    
        public boolean fit(Vehicle vehicle) {
            return currentVehicle == null && vehicle.getSize().ordinal() <= spotSize.ordinal();
        }
    
        public void park(Vehicle vehicle) {
            currentVehicle = vehicle;
        }
    
        public void leave() {
            currentVehicle = null;
        }
    
        public boolean isAvailable() {
            return currentVehicle == null;
        }
    
        public Vehicle getCurrentVehicle() {
            return currentVehicle;
        }
    }
    
    // Parking Ticket class
    class ParkingTicket {
        private String ticketNumber;
        private Date entryTime;
    
        public ParkingTicket(String ticketNumber, Date entryTime) {
            this.ticketNumber = ticketNumber;
            this.entryTime = entryTime;
        }
    
        public String getTicketNumber() {
            return ticketNumber;
        }
    
        public Date getEntryTime() {
            return entryTime;
        }
    }
    
    // Parking Lot class
    class ParkingLot {
        private List<ParkingSpot> spots;
    
        public ParkingLot(int smallSpots, int compactSpots, int largeSpots) {
            spots = new ArrayList<>();
            for (int i = 0; i < smallSpots; i++) {
                spots.add(new ParkingSpot(VehicleSize.SMALL));
            }
            for (int i = 0; i < compactSpots; i++) {
                spots.add(new ParkingSpot(VehicleSize.COMPACT));
            }
            for (int i = 0; i < largeSpots; i++) {
                spots.add(new ParkingSpot(VehicleSize.LARGE));
            }
        }
    
        public ParkingSpot findAvailableSpot(Vehicle vehicle) {
            for (ParkingSpot spot : spots) {
                if (spot.fit(vehicle)) {
                    return spot;
                }
            }
            return null;  // No available spot found
        }
    
        public ParkingTicket parkVehicle(Vehicle vehicle) {
            ParkingSpot spot = findAvailableSpot(vehicle);
            if (spot != null) {
                spot.park(vehicle);
                ParkingTicket ticket = new ParkingTicket(UUID.randomUUID().toString(), new Date());
                System.out.println("Vehicle parked: " + vehicle.getLicensePlate() + " in spot for " + vehicle.getSize());
                return ticket;
            } else {
                System.out.println("No available spot for vehicle: " + vehicle.getLicensePlate());
                return null;
            }
        }
    
        public void removeVehicle(Vehicle vehicle) {
            for (ParkingSpot spot : spots) {
                if (spot.getCurrentVehicle() == vehicle) {
                    spot.leave();
                    System.out.println("Vehicle removed: " + vehicle.getLicensePlate());
                    break;
                }
            }
        }
    
        public void displayAvailableSpots() {
            long availableSpots = spots.stream().filter(ParkingSpot::isAvailable).count();
            System.out.println("Available spots: " + availableSpots);
        }
    }
    
    // Main class to test the system
    public class Main {
        public static void main(String[] args) {
            // Create a parking lot with 3 small, 3 compact, and 2 large spots
            ParkingLot parkingLot = new ParkingLot(3, 3, 2);
    
            // Create vehicles
            Vehicle car = new Car("CAR-1234");
            Vehicle bike = new Bike("BIKE-5678");
            Vehicle truck = new Truck("TRUCK-9999");
    
            // Park vehicles
            parkingLot.parkVehicle(car);
            parkingLot.parkVehicle(bike);
            parkingLot.parkVehicle(truck);
    
            // Display available spots
            parkingLot.displayAvailableSpots();
    
            // Remove a vehicle
            parkingLot.removeVehicle(bike);
    
            // Display available spots again
            parkingLot.displayAvailableSpots();
        }
    }
    ```

    How It Works:

    1. **Vehicles**: We have an abstract `Vehicle` class with subclasses like `Car`, `Bike`, and `Truck`. Each vehicle has a size (`VehicleSize`) and a license plate.
       
    2. **ParkingSpot**: A parking spot can hold a vehicle. It checks if the vehicle fits (based on the size), and it has methods to park and remove vehicles.

    3. **ParkingTicket**: This class is used to keep track of parked vehicles and the time they entered the parking lot.

    4. **ParkingLot**: The `ParkingLot` class manages the parking spots, allowing vehicles to park and be removed. It keeps track of available spots and assigns vehicles to the correct spot.

    Features:

    - The parking lot can accommodate vehicles of different sizes (small, compact, large).
    - Vehicles are assigned to appropriate spots based on their size.
    - We can park vehicles, remove vehicles, and check the available spots.
    - A parking ticket is generated with a unique ticket number and timestamp when a vehicle is parked.

    Possible Extensions:

    - Adding payment processing for parking tickets based on the time parked.
    - Expanding the system to handle more complex operations like reservations or VIP parking.
    - Implementing a user interface for real-world usage.

21. What are Queue interface implementations and what are the differences and when to use what?

    Queue Interface Implementations in Java

    The **`Queue`** interface in Java is part of the **`java.util`** package and represents a **FIFO (First-In-First-Out)** data structure. It provides methods for inserting, removing, and inspecting elements. Several classes in Java implement the `Queue` interface, each with its own specific behavior.

    Common Implementations of `Queue` Interface:

    1. **`LinkedList`**
    2. **`PriorityQueue`**
    3. **`ArrayDeque`**
    4. **`ConcurrentLinkedQueue`**
    5. **`BlockingQueue` (sub-interfaces and implementations like `ArrayBlockingQueue`, `LinkedBlockingQueue`)**

    1. **LinkedList**

    - **Description**: A `LinkedList` is a doubly linked list that implements the `Queue` interface. It can act as both a queue and a deque (double-ended queue). It follows the **FIFO** principle when used as a queue.
    - **Key Features**:
      - Allows `null` elements.
      - Offers both `Queue` and `Deque` functionalities (can also be used as a stack).
      - Provides constant-time insertion and removal from both ends of the list.
      
    - **When to Use**: Use `LinkedList` when you need a basic FIFO queue with the ability to add or remove elements from both ends of the queue (acting as a deque). It works well for general-purpose queue implementations.

    ```java
    Queue<String> queue = new LinkedList<>();
    queue.add("A");
    queue.add("B");
    System.out.println(queue.poll());  // Removes and returns "A"
    ```

    2. **PriorityQueue**

    - **Description**: A `PriorityQueue` is a **heap-based** queue that orders its elements based on their natural ordering (or based on a comparator, if provided). It does **not follow FIFO**; instead, elements are removed in priority order.
    - **Key Features**:
      - Does not allow `null` elements.
      - Elements are ordered either according to their natural order or based on a comparator.
      - Offers logarithmic time complexity for enqueuing and dequeuing operations.

    - **When to Use**: Use `PriorityQueue` when you need elements to be processed in a priority-based order rather than in the order they were inserted. For example, in task scheduling, where tasks with higher priority should be processed first.

    ```java
    Queue<Integer> priorityQueue = new PriorityQueue<>();
    priorityQueue.add(10);
    priorityQueue.add(20);
    priorityQueue.add(5);
    System.out.println(priorityQueue.poll());  // Removes and returns "5" (smallest element)
    ```

    3. **ArrayDeque**

    - **Description**: An `ArrayDeque` is a **resizable-array implementation** of a deque that can be used as both a queue and a stack. It provides more efficient operations than `LinkedList` and does not allow `null` elements.
    - **Key Features**:
      - Does not allow `null` elements.
      - Provides constant-time performance for adding or removing elements from both ends.
      - More efficient than `LinkedList` for both stack and queue operations due to its array-based structure.

    - **When to Use**: Use `ArrayDeque` when you need a double-ended queue with better performance than `LinkedList`. It is also a good replacement for `Stack` when using stack-like operations (LIFO).

    ```java
    Queue<String> arrayDeque = new ArrayDeque<>();
    arrayDeque.add("A");
    arrayDeque.add("B");
    System.out.println(arrayDeque.poll());  // Removes and returns "A"
    ```

    4. **ConcurrentLinkedQueue**

    - **Description**: A `ConcurrentLinkedQueue` is a thread-safe, non-blocking queue based on a **lock-free algorithm**. It is part of the `java.util.concurrent` package and provides high throughput for concurrent access.
    - **Key Features**:
      - Non-blocking and thread-safe.
      - Suitable for highly concurrent environments.
      - Provides weak consistency, meaning that elements may not be immediately visible to all threads.

    - **When to Use**: Use `ConcurrentLinkedQueue` when you need a non-blocking, thread-safe queue for high-throughput scenarios in multi-threaded environments, such as task queues for concurrent processing.

    ```java
    Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
    concurrentQueue.add("A");
    concurrentQueue.add("B");
    System.out.println(concurrentQueue.poll());  // Removes and returns "A"
    ```

    5. **BlockingQueue** (and its Implementations)

    **`BlockingQueue`** is an interface that represents a thread-safe queue with blocking operations, meaning that if the queue is full, `put()` operations will wait until space is available, and if the queue is empty, `take()` operations will wait until elements are available.

    - **Key Implementations**:
      - **`ArrayBlockingQueue`**: A fixed-size, array-backed blocking queue.
      - **`LinkedBlockingQueue`**: A linked-list-backed blocking queue with optional capacity.
      - **`PriorityBlockingQueue`**: A blocking version of `PriorityQueue` with ordering based on priority.

    - **When to Use**: Use `BlockingQueue` when you need a thread-safe queue that supports **blocking behavior**, typically in **producer-consumer** scenarios where threads may need to wait for data.

    ```java
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(2);
    blockingQueue.put("A");
    blockingQueue.put("B");
    // blockingQueue.put("C");  // Would block if queue is full
    System.out.println(blockingQueue.take());  // Removes and returns "A"
    ```

    Key Differences Between Queue Implementations:

    | **Implementation**        | **Thread Safety**  | **Ordering**                    | **Blocking** | **Null Allowed** | **When to Use**                                    |
    | ------------------------- | ------------------ | ------------------------------- | ------------ | ---------------- | -------------------------------------------------- |
    | **LinkedList**            | No                 | FIFO                            | No           | Yes              | General-purpose queue, where nulls are allowed.    |
    | **PriorityQueue**         | No                 | Natural ordering / custom       | No           | No               | Priority-based processing, non-FIFO.               |
    | **ArrayDeque**            | No                 | FIFO (or LIFO if used as stack) | No           | No               | High-performance, double-ended queue or stack.     |
    | **ConcurrentLinkedQueue** | Yes (non-blocking) | FIFO                            | No           | No               | Concurrent, non-blocking, high-throughput use.     |
    | **ArrayBlockingQueue**    | Yes                | FIFO                            | Yes          | No               | Bounded, blocking queue for producer-consumer use. |
    | **LinkedBlockingQueue**   | Yes                | FIFO                            | Yes          | No               | Unbounded/optionally bounded blocking queue.       |

    When to Use What:

    - **LinkedList**: Use when you need a simple, non-thread-safe FIFO queue with the flexibility of adding/removing elements from both ends (deque).
    - **PriorityQueue**: Use when the elements need to be processed based on priority, not necessarily in the order they were inserted.
    - **ArrayDeque**: Use when you need a high-performance, resizable double-ended queue or stack. It's more efficient than `LinkedList` for both stack and queue operations.
    - **ConcurrentLinkedQueue**: Use in concurrent, multi-threaded environments where non-blocking behavior is required.
    - **BlockingQueue** (e.g., `ArrayBlockingQueue`, `LinkedBlockingQueue`): Use in multi-threaded environments where producers and consumers need to block until the queue has available space or data, respectively.

    Summary:

    Different `Queue` implementations are suited for different use cases based on requirements like thread safety, blocking behavior, element ordering, and performance characteristics. Choosing the correct implementation depends on whether you need concurrency support, ordering, and blocking behavior.

    

    

    











## References:

### 1. What Does "Null Support" Mean?

In Java, "Null support" refers to the capability of handling `null` values. A `null` value signifies that a variable does not point to any object in memory. In the context of primitive data types and their corresponding wrapper classes, **null support** means that while primitive data types cannot be `null`, their wrapper classes can hold `null` values. This feature is particularly significant because it allows developers to represent the absence or undefined state of a value explicitly.

### Why It Requires Wrapper Classes

Primitive data types in Java, such as `int`, `double`, `char`, `boolean`, etc., are not objects; they are basic data types handled directly by the Java runtime for efficiency. Because they are not objects:
- **Primitives cannot be `null`**: Primitives always hold a value, even if it's just a default value like `0` or `false`. There's no way to set a primitive to `null` because `null` is essentially a reference that points to no object.

On the other hand, wrapper classes (`Integer`, `Double`, `Character`, `Boolean`, etc.) are full-fledged objects that can hold a reference. Therefore:
- **Wrapper classes can be `null`**: As objects, instances of wrapper classes can hold a `null` reference, meaning they can explicitly denote the lack of a set value.

### Example and Importance

Consider a scenario where you need to store the results of a test score that might not have been taken by every student. Using primitives directly won't allow representing a "not taken" state naturally unless you designate an unrealistic score to symbolize it (like `-1`). However, using `-1` could be problematic if it's a possible valid score or can be confusing for someone else reading the code.

#### Example using `Integer` for potential `null` values:
```java
public class TestScores {
    public static void main(String[] args) {
        Integer score = null;  // Score not available

        if (score == null) {
            System.out.println("Test score not available.");
        } else {
            System.out.println("Test score: " + score);
        }
    }
}
```

**Output:**
```
Test score not available.
```

In this example:
- **Using `Integer`**: Allows the `score` to be explicitly set to `null`, clearly indicating that no test score is available.
- **Flexibility and Clarity**: This adds a layer of flexibility and clarity in data management, allowing developers to distinguish between having no data (`null`) and data with default values (like `0` for `int`).

### Conclusion

Null support through wrapper classes is essential for scenarios where data might not exist or hasn't been defined yet. By providing a way to represent such states explicitly, Java enables clearer and more error-resistant coding practices, especially in the context of complex applications where such distinctions are crucial.



### 2. Overloading and Method Signatures in Java

Regarding the code in the image and your questions:

1. **Overloading and Same Return Type Issue**:
   - In Java, overloading a method involves using the **same method name** with **different argument lists** (different number, type, or order of parameters).
   - The **return type** cannot be used to distinguish overloaded methods. Changing only the return type of a method, while keeping the same method name and parameter list, **does not** constitute valid overloading in Java. This means the code you referenced will cause a compile-time error because Java identifies methods by their signatures, which include the method name and parameter types but **not** the return type.

#### Example of Invalid Overloading:
```java
public class Test {
    public int add(int a, int b) {
        return a + b;
    }

    public String add(int a, int b) {
        return "a + b";
    }
}
```
This code will not compile because both methods have the same parameter list (`int, int`) and method name (`add`), differing only by return type, which is not allowed.

### Static Polymorphism (Compile-Time Polymorphism):

**Static polymorphism** in Java is achieved through method overloading. The method that is to be called is determined at compile time based on the method signature. This is also known as **compile-time polymorphism**.

### Dynamic Polymorphism (Run-Time Polymorphism):

**Dynamic polymorphism** is achieved through method overriding, where a method in a subclass has the same signature as a method in its superclass. The method that executes will depend on the object's runtime type, not its compile-time type. This allows for behaviors specific to the subclass to be invoked using a reference of the superclass.

### How Java Defines Method Signature:

In Java, a method signature is defined by the method's name and its input parameters (types and order). The return type is **not** part of the method signature. Therefore, the method signature is used both to identify which method to call in cases of overloading and to match method definitions and calls in cases of overriding.

### Clarification on Method Signatures and Polymorphism Types

#### Static Polymorphism (Compile-Time Polymorphism)
Static polymorphism in Java is the ability to process objects differently depending on their data type or class. It is resolved during compile time. Method overloading is an example of static polymorphism, where multiple methods can have the same name but different parameters. Decisions about which method is to be called are made at compile time.

**Example of Static Polymorphism (Method Overloading):**
```java
public class MathOperations {
    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }
}
```
Here, the `multiply` method is overloaded with different parameter types, and the compiler decides which method to call based on the argument types provided at compile time.

#### Dynamic Polymorphism (Run-Time Polymorphism)
Dynamic polymorphism is a process in which a call to an overridden method is resolved at runtime rather than compile-time. In Java, this is achieved through method overriding, where a subclass has a different implementation for a method declared in the superclass.

**Example of Dynamic Polymorphism (Method Overriding):**
```java
class Animal {
    void sound() {
        System.out.println("Generic sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}

class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        myAnimal.sound();  // Output will be "Bark"
    }
}
```
In this example, the `sound` method in `Dog` overrides the `sound` method in `Animal`. Which method is called is determined at runtime based on the object type, not the reference type.

#### How Java Defines Method Signature
In Java, a method's signature includes its name and the list and type of its parameters. The return type and the access modifiers do not form part of the method signature. This signature is crucial for identifying the method uniquely within the class where it is defined.

**Importance in Overloading and Overriding:**
- **Overloading**: The compiler uses the method signature to determine which overloaded method should be called, based on the method arguments.
- **Overriding**: When overriding a method, the subclass method must match the signature of the method in its superclass to ensure that the correct method is overridden at runtime.

This precise definition of a method signature in Java underpins both overloading and overriding, providing a robust mechanism for implementing polymorphism.



### 3.  Can a static method access non-static variables?

No, **static methods cannot access non-static (instance) variables** or methods directly. This is because static methods belong to the class itself, not to any particular instance of the class. Non-static (instance) variables require an instance of the class to exist, whereas static methods can be called without creating an instance.

#### Example:
```java
public class Example {
    private int instanceVariable = 10;  // Non-static variable

    public static void staticMethod() {
        // instanceVariable cannot be accessed directly because it's non-static
        // System.out.println(instanceVariable);  // Error

        // However, we can access it through an instance of the class:
        Example obj = new Example();
        System.out.println(obj.instanceVariable);  // This works, via an instance
    }

    public static void main(String[] args) {
        staticMethod();
    }
}
```

### Common Static Methods

Some commonly used static methods are found in utility classes in Java:
- **`Math` class**: Provides mathematical operations like `Math.sqrt()`, `Math.abs()`, `Math.pow()`.
- **`Integer` class**: `Integer.parseInt()` converts strings to integers.
- **`String` class**: `String.valueOf()` converts values to strings.
- **`System` class**: `System.out.println()` is used for printing to the console.
  

These methods do not require an instance of the class and can be called directly using the class name.

#### Example:
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Math.sqrt(16));  // Calls static method Math.sqrt()
        System.out.println(Integer.parseInt("100"));  // Calls static method Integer.parseInt()
        System.out.println(String.valueOf(123));  // Calls static method String.valueOf()
    }
}
```

### When to use static methods?

Static methods are typically used when:
1. **The method performs operations that do not require data from an instance of the class** (i.e., they operate only on the method's parameters and do not rely on instance variables).
2. **Utility or helper methods**: These methods typically perform simple operations like mathematical computations, conversions, or string manipulations (e.g., methods in the `Math` and `String` classes).
3. **When you want to share a method across all instances**: Static methods can be shared by all objects of a class, making them ideal for operations that should be common to all objects.

#### Example Scenario:
Utility classes like `Math`, `System`, and `Integer` often have static methods because their methods don’t require instance data and can be shared across the entire application.

---

### How to call static methods?

Static methods are called using the **class name**, followed by the method name. Since they belong to the class, not to an instance, there's no need to create an object to call a static method.

#### Syntax:
```java
ClassName.methodName();
```

#### Example:
```java
public class Example {
    public static void staticMethod() {
        System.out.println("This is a static method.");
    }

    public static void main(String[] args) {
        // Calling the static method directly using the class name
        Example.staticMethod();
    }
}
```

In this example, `staticMethod()` is called using the class name `Example` because it is a static method.

### Summary:

1. **Can static method access non-static variables?** No, static methods can only access non-static variables through an instance of the class.
2. **Common static methods** are found in utility classes like `Math`, `Integer`, `String`, and `System`.
3. **When to use static methods?** When the method doesn't require instance-specific data and should operate at the class level, such as utility methods.
4. **How to call static methods?** Use the class name followed by the method name (e.g., `ClassName.methodName()`).
