# Java & OOP
## 1. Write up Example code to demonstrate the three fundamental concepts of OOP.
### 1. Encapsulation 

Integrating data (variables) and code (methods) into a single unit. In encapsulation, a class's variables are hidden from other classes and can only be accessed by the methods of the class in which they are found.
```java
class Animal {
    private String name; 

    public Animal(String name) {  
        this.name = name;
    }

    public String getName() {  
        return name;
    }

    public void makeSound() {
        System.out.println("General animal sound");
    }
}
```

### 2. Polymorphism
Polymorphism is demonstrated by overriding the makeSound() method in the Dog class to provide a specific implementation, while the base class (Animal) provides a generic one.
```java
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {  
        System.out.println("Woof! Woof!");
    }
}
```

### 3. Inheritance
   Inheritance allows a class (Dog) to inherit fields and methods from another class (Animal).
```java
class Dog extends Animal {  
    
    public Dog(String name) {
        super(name);  
    }
}
```

## 2. What is a Wrapper Class in Java and Why Do We Need It?
A wrapper class in Java provides a way to use primitive data types (like `int`, `char`, `double`, etc.) as objects. 
Java provides wrapper classes for all primitive types, such as `Integer` for `int`, `Character` for `char`, `Double` for `double`, etc.

### Why We Need Wrapper Classes:
1. Collections (e.g., `ArrayList`, `HashMap`) can only store objects, not primitives.
2. Wrapper classes provide useful methods (e.g., `Integer.parseInt()`).
3. Wrapper classes allow easy conversion between different types (e.g., `int` to `String`).
4. Automatic conversion between primitives and objects.


## 3. What is the Difference Between HashMap and Hashtable?
HashMap and HashTable are both implementations of the Map interface, but:
- **Synchronization**: `HashMap` is not synchronized and allows null keys and values. `Hashtable` is synchronized and does not allow null keys or values.
- **Performance**: `HashMap` is generally faster than `Hashtable` due to the lack of synchronization overhead.
- **Implementation**: `HashMap` are implemented using an array of linked lists, while `Hashtable` is implemented using an array of buckets.
- **Iteration**: `HashMap` uses a fail-fast iterator, which means that if the HashMap is changed while iterating over it, an exception will be thrown. `Hashtable` uses enumerations that are fail-safe, so they won't throw an exception if the hash table is modified during iteration.
Use `HashMap` for most cases.



## 4. What is the String Pool in Java and Why Do We Need It? Explain String immunity.
The String pool is a special memory area in the Java heap that stores unique string literals. When a string is created, Java first checks the pool to see if an identical string already exists. If so, it returns the reference to the existing string instead of creating a new one.

### Why We Need the String Pool
- **Memory Efficiency**: Reduces memory usage by reusing immutable string objects.
- **Performance**: Improves performance by avoiding the creation of duplicate string objects.

### String Immunity
**String Immunity** refers to the immutability of string objects in Java. Once a String object is created, it cannot be modified. Any operation that seems to modify a String actually creates a new String object. This immutability allows the String Pool to safely reuse String objects.


## 5. What is Garbage Collection?
Garbage Collection is a process in Java that automatically manages memory by reclaiming space used by objects that are no longer needed or referenced.
Garbage collection primarily deals with the heap memory where objects are allocated.

## 6. What are Access Modifiers and Their Scopes in Java?
Access Modifiers control the visibility and accessibility of classes, methods, and variables. The main access modifiers are:
1. **`public`**:
    - Scope: Accessible from any other class or package.

2. **`protected`**:
    - Scope: Accessible within the same package and by subclasses (even if they are in different packages).

3. **`default`** (no modifier):
    - Scope: Accessible only within the same package.

4. **`private`**:
    - Scope: Accessible only within the same class.

## 7. Explain the final Keyword (Field, Method, Class)
The `final` keyword in Java is used to indicate that a variable, method, or class cannot be modified or extended.

- **Final Field**:
  - A final field's value cannot be changed. Use final fields for constants or values that should remain unchanged after initialization.
- **Final Method**:
  - A final method cannot be overridden by subclasses. Use final methods to prevent subclasses from altering the behavior of inherited methods.
- **Final Class**:
  - A final class cannot be subclassed or extended. Use final classes to prevent inheritance, ensuring that the class's behavior remains unchanged.


## 8. Explain the static keyword (Field, Method, Class). When do we usually use it?
The `static` keyword is used to indicate that a field, method, or class belongs to the class itself rather than to instances of the class.

- **Static Field**:
  - A static field is shared among all instances of a class. It is used for constants or values that are common to all instances.
- **Static Method**:
    - A static method belongs to the class and can be called without creating an instance. It is used for utility functions that do not depend on instance data.
- **Static Class**:
    - A static class can only be nested within another class. It does not have access to the instance variables or methods of the enclosing class.


## 9. What are the differences between Overriding and Overloading?
- **Overriding**:
    - Redefining a method in a subclass with the same name, return type, and parameters as in the parent class.
    It allows a subclass to provide a specific implementation of a method that is already defined in its superclass.
- **Overloading**:
    - Defining multiple methods in the same class with the same name but different parameters (type, number, or both).
    It allows a class to have more than one method with the same name but different functionality based on parameters.
Overriding changes the behavior of inherited methods, while overloading allows multiple methods with the same name but different parameters in the same class.


## 10. Explain how Java defines a method signature, and how it helps with overloading and overriding
- **Method Signature**:
    - The method signature consists of the method's name and its parameter list (types and order). It does not include the return type or access modifiers.
    - For the method `public void func(int a, double b)`, the signature is `func(int, double)`.
- **Overriding**:
    - Allows a subclass to provide a specific implementation of a method already defined in its superclass. The method signature must match the superclass method exactly.
- **Overloading**:
    - Allows multiple methods in the same class to have the same name but different parameter lists. The method signature helps distinguish between these methods.
The method signature (name + parameter list) is crucial for distinguishing overloaded methods and ensuring correct method overriding in Java.


## 11. What are the differences between super and this?
- **`this`**: refers to the current instance of the class.
- **`super`**: refers to the superclass of the current instance.
Use `this` for current instance members and `super` for superclass members.


## 12. Explain how `equals` and `hashCode` work
- **`equals`**:
    - **Purpose**: Checks if two objects are equal based on their content.
    - **Override**: Customize equality logic in your class.

- **`hashCode`**:
    - **Purpose**: Provides a hash code for the object, used in hash-based collections.
    - **Override**: Ensure consistency with `equals` by providing a matching hash code.
**Summary**: `equals` compares object content, while `hashCode` provides a unique identifier for hash-based collections.

## 13. What is the Java load sequence?
Java loading sequence involves loading, linking, initializing, and instantiating classes before execution.

## 14. What is Polymorphism? And how does Java implement it?
Polymorphism allows objects to be treated as instances of their parent 
class, with Java implementing it through method overloading (compile-time) and method overriding (runtime).
- **Compile-time Polymorphism (Method Overloading)**: Achieved by defining multiple methods with the same name but different parameters in the same class.
- **Runtime Polymorphism (Method Overriding)**: Achieved by redefining a method in a subclass that is already defined in its superclass, allowing the subclass to provide a specific implementation.

## 15. What is Encapsulation? How does Java implement It? And why do we need it?
Encapsulation is the concept of wrapping data (variables) and methods (functions) into a single unit, called a class. It hides the internal state of the object and only exposes a controlled interface.
- **How Java Implements It**: Use `private` to hide data from outside classes and `public` to provide controlled access through getter and setter methods.
- **Why We Need Encapsulation**:
  - **Data Hiding**: Protects the internal state of an object from unintended or harmful modifications.
  - **Control Access**: Allows control over how data is accessed or modified, ensuring data integrity.
  - **Improved Maintenance**: Simplifies code maintenance and enhances code readability by centralizing data access in one place.

## 16. Compare Interface and Abstract Class
Use interfaces for defining shared behaviors and abstract classes for a common base with both shared and enforced methods.
- **Interface**: 
    - **Methods**: Abstract methods, default methods, and static methods.
    - **Inheritance**: Multiple interfaces can be implemented.
    - **Fields**: Only constants (public static final).

- **Abstract Class**: Provides a base with common methods and some abstract methods.
    - **Methods**: Both abstract and concrete methods.
    - **Inheritance**: A class can inherit only one abstract class.
    - **Fields**: Can have instance variables with any access modifier.

## 17. Write a factory pattern in Java
The Factory Pattern helps in creating objects without specifying the exact class of the object that will be created, thus promoting loose coupling and flexibility.
```java
// interface Animal
interface Animal {
    void speak();
}

// concrete classes dog and cat implementing the Animal interface.
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}

// create a Factory class to generate objects of concrete classes based on given information.
class AnimalFactory {
    public Animal createAnimal(String type) {
        if ("Dog".equalsIgnoreCase(type)) {
            return new Dog();
        } else if ("Cat".equalsIgnoreCase(type)) {
            return new Cat();
        }
        return null;
    }
}

// use the factory to create objects and call their methods.
public class FactoryPatternExample {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();

        Animal dog = factory.createAnimal("Dog");
        dog.speak();  // Output: Woof!

        Animal cat = factory.createAnimal("Cat");
        cat.speak();  // Output: Meow!
    }
}
```
This simple factory pattern example shows how to create different Animal objects (Dog, Cat) without directly instantiating their classes, using a factory method.


## 18. Write an adapter pattern in Java
Adapter Pattern is a structural design pattern that allows two incompatible interfaces to work together. It acts as a bridge between two objects, converting the interface of a class into another interface that a client expects. This pattern is especially useful when we want to reuse a class that does not have an interface that matches our needs.
```java
// define the Target interface that the client expects.
interface ToyDuck {
    void squeak();
}

// the existing class that needs to be adapted.
class Bird {
    public void chirp() {
        System.out.println("Chirp Chirp");
    }
}

// the adapter class that implements the ToyDuck interface and contains a reference to a Bird object. 
// The adapter translates the squeak method call to the chirp method of the Bird.
class BirdAdapter implements ToyDuck {
    private Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void squeak() {
        // Adapt the Bird's chirp method to ToyDuck's squeak method
        bird.chirp();
    }
}

// use the Adapter in the client code.
public class AdapterPatternExample {
    public static void main(String[] args) {
        Bird bird = new Bird();
        ToyDuck toyDuck = new BirdAdapter(bird); // Using adapter
        toyDuck.squeak();  // Output: Chirp Chirp
    }
}

```

## 19. Write singleton pattern in code, make sure your code is thread-safe.
The Singleton Pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to it. To make a Singleton class thread-safe in Java, we can the most common method which is the Double-Checked Locking method.
```java
public class Singleton {

    // declare a private volatile static instance of the Singleton class
    private static volatile Singleton instance;

    // make the constructor private to prevent instantiation from other classes.
    private Singleton() {
    }

    // public method to provide global access point using double-checked locking
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {  // Locking for thread safety
                if (instance == null) {  // Second check (with locking)
                    instance = new Singleton();  // Create new instance
                }
            }
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Singleton instance: Thread-safe using Double-Checked Locking!");
    }
}

// test the Singleton class
public class SingletonPatternExample {
    public static void main(String[] args) {
        // Get the only instance of Singleton class
        Singleton singleton = Singleton.getInstance();

        // Call a method on the instance
        singleton.showMessage();  // Output: Singleton instance: Thread-safe using Double-Checked Locking!
    }
}

```

## 20. Design parking lot
See codingQuestions/coding1 folder


### 21. What are Queue interface implementations and what are the differences and when to use what?
Queue interface is part of the Java Collections Framework and represents a collection designed for holding elements about to be processed in FIFO(First In First Out) order.
1. **`LinkedList`**
    - **Description**: Implements both `List` and `Queue`, supports `FIFO` order.
    - **When to Use**: General-purpose queue with frequent insertions and deletions.

2. **`PriorityQueue`**
    - **Description**: Orders elements by priority, not `FIFO`.
    - **When to Use**: Need elements sorted by priority.

3. **`ArrayDeque`**
    - **Description**: Resizable array, supports fast operations at both ends.
    - **When to Use**: Need fast access and insertion from both ends.

4. **`ConcurrentLinkedQueue`**
    - **Description**: Thread-safe, non-blocking queue.
    - **When to Use**: Concurrent applications requiring thread safety.

5. **`BlockingQueue` Implementations** (e.g., `LinkedBlockingQueue`)
    - **Description**: Supports blocking operations (waiting for queue to become non-empty/full).
    - **When to Use**: Producer-consumer scenarios needing blocking operations.


