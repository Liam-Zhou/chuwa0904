#### 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
**1. Use Optional for Return Values**
Avoid returning null from methods whenever possible. Instead, use Optional<T> to explicitly indicate that a value might be absent.
```
import java.util.Optional;

public class Example {
    public Optional<String> getName() {
        String name = "John";
        return Optional.ofNullable(name); // Optional.ofNullable to allow null
    }

    public static void main(String[] args) {
        Example example = new Example();
        Optional<String> nameOpt = example.getName();

        nameOpt.ifPresent(name -> System.out.println("Name: " + name));
    }
}
```
**2. Check for null Before Dereferencing**
Always check if an object is null before accessing its properties or methods.
```
public class NullCheckExample {
    public static void main(String[] args) {
        String str = null;
        
        if (str != null) {
            System.out.println(str.length());
        } else {
            System.out.println("String is null");
        }
    }
}
```
**3. Use Objects.requireNonNull**
Java provides a utility method Objects.requireNonNull() to ensure that an object reference is not null. This helps to fail fast by throwing an exception if the object is null.
```
import java.util.Objects;

public class RequireNonNullExample {
    public static void main(String[] args) {
        String str = null;
        str = Objects.requireNonNull(str, "String must not be null");
        
        System.out.println(str.length()); // This line will not be reached
    }
}
```
**4. Use Default Values Instead of null**
In cases where null is an expected result, provide default values to avoid returning or handling null.
```
public class DefaultValueExample {
    public static void main(String[] args) {
        String str = null;
        
        String result = (str != null) ? str : "default";
        System.out.println(result); // Outputs: default
    }
}
```
**5. Use @NonNull Annotations**
Use annotations like @NonNull (from frameworks like Lombok or within your IDE tools) to enforce compile-time checks that prevent null values from being passed or assigned.
```
import lombok.NonNull;

public class NonNullExample {
    public void printMessage(@NonNull String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        NonNullExample example = new NonNullExample();
        example.printMessage(null); // Will cause a NullPointerException at runtime
    }
}
```
#### 6. Discuss Java 8 new features with code snippet
**1. Lambda Expressions**
Lambda expressions allow you to write concise, anonymous functions, making your code more readable and functional. They are particularly useful in collections and stream operations.
```
import java.util.Arrays;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.forEach(name -> System.out.println(name)); // Lambda Expression

        // Equivalent to:
        names.forEach(System.out::println); // Method Reference
    }
}
```
**2. Functional Interfaces**
Java 8 introduced @FunctionalInterface, which indicates that an interface is intended to be a functional interface (an interface with only one abstract method). Common examples are Runnable, Callable, and the newly introduced interfaces in java.util.function.
```
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Greeting greeting = (name) -> System.out.println("Hello, " + name);
        greeting.sayHello("Alice");
    }
}
```
**3. Streams API**
The Streams API provides a functional approach to processing sequences of data. It allows for operations like filtering, mapping, and reducing, providing a fluent and expressive way to work with collections.
```
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        List<String> filteredNames = names.stream()
                                          .filter(name -> name.startsWith("A"))
                                          .collect(Collectors.toList());

        System.out.println(filteredNames); // Outputs: [Alice]
    }
}
```
#### 7. What are the advantages of the Optional class?
The Optional class is a powerful tool that helps developers write safer and cleaner code by enforcing explicit handling of absent values, reducing the risk of NullPointerException, and promoting more functional and concise programming practices.
#### 8. Explain Functional Interface and Lambda with code samples
A Functional Interface is an interface that contains exactly one abstract method, making it eligible to be implemented via lambda expressions. Functional interfaces may have other default or static methods but must contain only one abstract method.
```
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();
    
    // You can have default methods
    default void sayHello() {
        System.out.println("Hello from default method!");
    }
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunctionalInterface fi = () -> System.out.println("Executing method");
        fi.execute(); // Outputs: Executing method
        fi.sayHello(); // Outputs: Hello from default method!
    }
}
```

A lambda expression is a short block of code that takes in parameters and returns a value. Lambdas enable you to write more concise code by providing a way to express instances of functional interfaces directly without needing anonymous classes. They are a key feature introduced in Java 8 to support functional programming.
```
import java.util.function.Consumer;

public class LambdaExample {
    public static void main(String[] args) {
        // Lambda for a Consumer Functional Interface
        Consumer<String> printUpperCase = (str) -> System.out.println(str.toUpperCase());
        
        printUpperCase.accept("hello lambda!"); // Outputs: HELLO LAMBDA!
    }
}
```
#### 9. Explain Method Reference with code samples?
**Reference to a static method**
```
public class StaticMethodReferenceExample {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Using method reference to a static method
        names.forEach(StaticMethodReferenceExample::print);
    }
}
```
**Reference to an instance method of a particular object**
```
public class InstanceMethodReferenceExample {
    public void printName(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        InstanceMethodReferenceExample obj = new InstanceMethodReferenceExample();
        
        // Using method reference to an instance method of a particular object
        names.forEach(obj::printName);
    }
}
```
**Reference to an instance method of an arbitrary object of a particular type**
```
public class ArbitraryObjectMethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using method reference to an instance method of an arbitrary object
        names.forEach(String::toUpperCase); // Calls toUpperCase() on each String element
        
        // Print all elements
        names.forEach(System.out::println); // Outputs: Alice, Bob, Charlie
    }
}
```
**Reference to a constructor**
```
public class ConstructorReferenceExample {
    public ConstructorReferenceExample() {
        System.out.println("Constructor invoked!");
    }

    public static void main(String[] args) {
        // Using method reference to a constructor
        Supplier<ConstructorReferenceExample> supplier = ConstructorReferenceExample::new;

        // Creating a new object using the method reference
        supplier.get();  // Outputs: Constructor invoked!
    }
}
```

#### 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
Lambdas can use variables from the enclosing scope, but those variables must be final or effectively final. This restriction ensures that the lambda always works with the same value, preventing unexpected side effects. If you try to modify such a variable, the Java compiler will throw an error, enforcing immutability for captured variables.

#### 11. Can a functional interface extend/inherit another interface?
Yes, a functional interface can extend or inherit another interface, as long as it still adheres to the rule of having only one abstract method. This means that if the parent interface already defines a single abstract method, the extending functional interface can inherit that method and still be considered functional.

#### 12. What are Intermediate and Terminal operations?
**1. Intermediate Operations**
Intermediate operations are those that return a new stream (or transform the original stream) and are lazy, meaning they do not get executed until a terminal operation is invoked. They allow method chaining and are used to transform or filter data without consuming the stream.
**2. Terminal Operations**
Terminal operations are those that trigger the processing of the stream and consume it. After a terminal operation is called, the stream is considered consumed and can’t be reused.

#### 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
**filter()**
```
public class FilterExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Ann");

        names.stream()
            .filter(name -> name.startsWith("A"))  // Filters names that start with 'A'
            .forEach(System.out::println);         // Output: Alice, Ann
    }
}
```
#### 14. How are Collections different from Stream?
**Collections** are ideal for managing data in memory, where you need to frequently access or modify data.
**Streams** are suitable for processing data in a declarative, functional style, especially when performing complex or bulk operations on data.

#### 15. 
```
class CustomStream<T> {
    private final List<T> source;

    // Constructor
    public CustomStream(List<T> source) {
        this.source = source;
    }

    // Custom filter method
    public CustomStream<T> filter(Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T item : source) {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        }
        return new CustomStream<>(filteredList);
    }

    // Custom map method
    public <R> CustomStream<R> map(Function<T, R> function) {
        List<R> mappedList = new ArrayList<>();
        for (T item : source) {
            mappedList.add(function.apply(item));
        }
        return new CustomStream<>(mappedList);
    }

    // Terminal method to collect the results
    public List<T> collect() {
        return source;
    }
}

// Example usage
public class CustomStreamExample {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // Using custom stream to filter and map
        List<String> result = new CustomStream<>(names)
            .filter(name -> name.startsWith("A"))       // Filter names starting with 'A'
            .map(String::toUpperCase)                   // Convert names to uppercase
            .collect();                                // Collect the results

        System.out.println(result);  // Output: [ALICE]
    }
}
```