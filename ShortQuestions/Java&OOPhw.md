# Java&OOP homework answers

#### 1. Write up Example code to demonstrate the three foundmental concepts of OOP.

- Encapsulation:
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
- Polymorphism:
```java
class Shape {
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle();
        Shape shape2 = new Square();

        shape1.draw();  // Output: Drawing a circle
        shape2.draw();  // Output: Drawing a square
    }
}
```

- Inheritance:
```java
class Animal {
    void eat() {
        System.out.println("This animal eats food");
    }
}

class Lion extends Animal {
    void roar() {
        System.out.println("Lion roars");
    }
}

public class Test {
    public static void main(String[] args) {
        Lion lion = new Lion();
        lion.eat();  // Inherited method
        lion.roar();
    }
}
```

#### 2. What is wrapper class in Java and Why we need wrapper class?

- A wrapper class is an object representation of primitive data types (e.g., Integer, Double, Boolean). We need wrapper classes because certain data structures, like ArrayList, cannot store primitive types directly, and wrapper classes help by providing an object reference for these types.

#### 3. What is the difference between HashMap and HashTable?

- HashMap is not synchronized, allows one null key and multiple null values.
- HashTable is synchronized and doesn’t allow null keys or values.

#### 4. What is String pool in Java and why we need String pool? Explain String immunity.

- String Pool is a special memory area for strings to store string literals. This helps save memory by reusing immutable strings.
- String Immutability: Once a String object is created, it cannot be changed.

#### 5. Explain garbage collection?

- Java’s garbage collector automatically garbages memory for objects that are no longer reachable, freeing up resources without manual intervention.

#### 6. What are access modifiers and their scopes in Java?

- Private: Accessible only within the class.
- Default: Accessible within the same package.
- Protected: Accessible within the same package and subclasses.
- Public: Accessible from anywhere.

#### 7. Explain final keyword? (FieLd, Method, Class)

- Final Field: Cannot be modified after initialization.
- Final Method: Cannot be overridden.
- Final Class: Cannot be extended.

#### 8. Explan static keyword? (FieLd, Method, Class). When do we usually use it?

- Static Field: Belongs to the class, not the instance.
- Static Method: Can be called without creating an instance of the class.
- Static Class: An inner class that can be instantiated without an outer class instance.

#### 9. What is the differences between overriding and overloading?

- Overriding: Redefining a method in a subclass.
- Overloading: Defining multiple methods with the same name but different parameters.

#### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

- A method signature consists of the method name and parameter types, helping to differentiate between overloaded methods.

#### 11. What is the differences between super and this?

- This: Refers to the current object.
- Super: Refers to the superclass.

#### 12. Explain how equals and hashCode work.

- Equals: Compares two objects for equality in value.
- HashCode: Generates an integer from both items and compares.

#### 13. What is the Java load sequence?

- Java class loading involves loading, linking, and initializing classes before they are used.

#### 14. What is Polymorphism ? And how Java implements it ?

- Java achieves polymorphism through method overriding and interfaces, allowing objects to behave differently depending on their types.

#### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation? 

- Encapsulation restricts direct access to an object’s fields and methods. It is implemented by making fields private and providing public methods for access.

#### 16. Compare interface and abstract class.

- Interface: Can have abstract methods and static methods but no instance variables.
- Abstract Class: Can have both abstract and non-abstract methods and instance variables.

#### 17. Write a factory pattern in code.
```java
interface Transport {
    void deliver();
}

class Truck implements Transport {
    public void deliver() {
        System.out.println("Deliver by truck");
    }
}

class Ship implements Transport {
    public void deliver() {
        System.out.println("Deliver by ship");
    }
}

class TransportFactory {
    public Transport getTransport(String type) {
        if (type.equalsIgnoreCase("Truck")) {
            return new Truck();
        } else if (type.equalsIgnoreCase("Ship")) {
            return new Ship();
        }
        return null;
    }
}

```

#### 18. Write a adapter pattern in code.
```java
interface HDMI {
    void display();
}

class VGA {
    void show() {
        System.out.println("Displaying via VGA");
    }
}

class HDMIAdapter implements HDMI {
    private VGA vga;

    public HDMIAdapter(VGA vga) {
        this.vga = vga;
    }

    public void display() {
        vga.show();
    }
}
```

#### 19. Write singleton pattern in code, make sure your code is thread-safe.
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

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


#### 20. design a parking lot (put the code to codingQuestions/coding1 folder, )

- Check in the code folder

#### 21. What are Queue interface implementations and what are the differences and when to use what?
- The common Queue interface implementations and difference:
    1. PriorityQueue: When elements must be processed based on priority.
    2. LinkedList: When you need a simple FIFO queue or a combination of list and queue functionality.
    3. ArrayDeque: When you need fast insertion/removal from both ends.

