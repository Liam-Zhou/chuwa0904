# Java&OOP
## 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
### 1.1 Encapsulation
```
class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

```

### 1.2 Polymorphism
```
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
```

### 1.3 Inheritance
```
class Vehicle {
    public void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
}
```
## 2. What is wrapper class in Java and Why we need wrapper class?
Wrapper classs are used to convert primitive data types into Object. They can make the primitive date be used with collection, and they provide more tools to manipulate the data.

## 3. What is the difference between HashMap and HashTable?
- **HashMap** is not synchronized, meaning it is not thread-safe. It allows `null` keys and values.
- **HashTable** is synchronized, meaning it is thread-safe. It does not allow `null` keys or values.

## 4. What is String pool in Java and why we need String pool? Explain String immunity.

The **String pool** is a special memory area inside the Java heap where string literals are stored. Java optimizes memory usage by storing each literal only once in the pool. If multiple strings have the same content, they will point to the same object in the pool, which means the String pool can save the memory
If you modify a string, a new `String` object is created, and the old one remains unchanged.

## 5. Explain garbage collection?
**Garbage collection** free up memory for new objects. This process helps in preventing memory leaks and ensuring efficient use of memory.

## 6. What are access modifiers and their scopes in Java?
| Modifier    | Class | Package | Subclass | Global |
|-------------|-------|---------|----------|--------|
| `public`    | Yes   | Yes     | Yes      | Yes    |
| `protected` | Yes   | Yes     | Yes      | No     |
| `default`   | Yes   | Yes     | No       | No     |
| `private`   | Yes   | No      | No       | No     |


## 7.Explain final key word? (Filed, Method, Class)
The `final` keyword in Java is used to apply restrictions on classes, methods, and variables. Once a class, method, or variable is declared as `final`, its behavior cannot be modified in certain ways.


## 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?
The `static` keyword in Java is used to create class-level variables and methods. It can be applied to variables, methods, blocks, and inner classes. When we want to share the same variable with all objects, we can use it.


## 9. What is the differences between overriding and overloading?
- **Overloading**: Same method name, different parameter lists.
- **Overriding**: Same method name and parameters, but a different implementation in the subclass


## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
- **method signature** includes method name and parameter types.
- **Overloading** relies on different method signature
- **Overloading** relies on same method signature in the subclass to provide different implementation 

## 11. What is the differences between super and this?
- **super**: refers to the parent class
- **this**: refers to the current instance of the class

## 12. Explain how equals and hashCode work.
- **equals**:compares objects for logical equality
- - **hashCode**: returns an integer used in hashing-based collections 

## 13. What is the Java load sequence?
- **Java load sequence**:defines the order in which classes, variables, and blocks are loaded and initialized during the execution of a Java program.


## 14. What is Polymorphism ? And how Java implements it ?
- **Java load sequence**: allows a single method or interface to operate in different ways depending on the context.
- using method overloading and method overriding

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
- **Encapsulation**: binds data and methods into a single unit and restricts direct access.
- using private fields and public getter and setter methods
- ensures data integrity, security, and maintainability.

## 16. Compare interface and abstract class.
| Feature               | Interface                                       | Abstract Class                                    |
|-----------------------|-------------------------------------------------|---------------------------------------------------|
| **Methods**            | Can only have abstract methods (before Java 8). Can have default and static methods from Java 8 onwards. | Can have both abstract and concrete methods.      |
| **Variables**          | Only public, static, and final variables. | Can have any type of variables. |
| **Inheritance**        | Can implement multiple interfaces | Can only extend one abstract class. |
| **Constructors**       | Cannot have constructors.                       | Can have constructors.                            |


## 17. Write a factory pattern in code.

```
interface Vehicle {
    void drive();
}
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike");
    }
}

class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Truck");
    }
}
class VehicleFactory {
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        if (vehicleType.equalsIgnoreCase("CAR")) {
            return new Car();
        } else if (vehicleType.equalsIgnoreCase("BIKE")) {
            return new Bike();
        } else if (vehicleType.equalsIgnoreCase("TRUCK")) {
            return new Truck();
        }
        return null;
    }
}

public class FactoryPatternVehicle{
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle1 = vehicleFactory.getVehicle("CAR");
        vehicle1.drive();
        Vehicle vehicle2 = vehicleFactory.getVehicle("BIKE");
        vehicle2.drive();
        Vehicle vehicle3 = vehicleFactory.getVehicle("TRUCK");
        vehicle3.drive();
    }
}
```


## 18. Write a adapter pattern in code.
```
interface AmericanSocket {
    void plugIn();
}
class AmericanPlug implements AmericanSocket {
    @Override
    public void plugIn() {
        System.out.println("American plug is plugged into American socket.");
    }
}
class EuropeanPlug {
    public void insertIntoEuropeanSocket() {
        System.out.println("European plug is inserted into European socket.");
    }
}
class PowerAdapter implements AmericanSocket {
    private EuropeanPlug europeanPlug;
    public PowerAdapter(EuropeanPlug europeanPlug) {
        this.europeanPlug = europeanPlug;
    }
    @Override
    public void plugIn() {
        europeanPlug.insertIntoEuropeanSocket();
    }
}
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AmericanSocket americanPlug = new AmericanPlug();
        americanPlug.plugIn();
        EuropeanPlug europeanPlug = new EuropeanPlug();
        AmericanSocket adapter = new PowerAdapter(europeanPlug);
        adapter.plugIn(); 
    }
}

```

## 19. Write singleton pattern in code, make sure your code is thread-safe.
```
public class CustomSingleton {
    private int value;
    private CustomSingleton() {
        this.value = 42;
        System.out.println("CustomSingleton instance created with value: " + value);
    }
    static {
        System.out.println("CustomSingleton class is being loaded");
    }
    private static class SingletonHolder {
        private static final CustomSingleton INSTANCE = new CustomSingleton();
    }
    public static CustomSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int newValue) {
        this.value = newValue;
        System.out.println("Value updated to: " + newValue);
    }
}
```

## 20. design a ppaarrkkiinngg lloott (put the code to codingQuestions/coding1 folder, )


## 21. What are Queue interface implementations and what are the differences and when to use what?

- **Java load sequence**: LinkedList, PriorityQueue, ArrayDeque
### Difference
| Feature            | **LinkedList**                    | **PriorityQueue**                     | **ArrayDeque**                     |
|--------------------|-----------------------------------|---------------------------------------|------------------------------------|
| **Ordering**        | FIFO              | Natural ordering or custom comparator | FIFO, or operations on both ends |
| **Null elements**   | Allows null                       | Does not allow null                   | Does not allow null                |
| **Performance**     | O(1) insertion/removal            | O(log n) insertion/removal            | O(1) insertion/removal             |

