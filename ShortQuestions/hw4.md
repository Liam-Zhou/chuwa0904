
# Homework 4: Java 8

## 1. **Learn Java Generics** by reading and practicing the following code:  
   [Java Generics Tutorial](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic)

## 2. **Read the following code repository** and type it by yourself:  
   [Java 8 Features](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features)

## 3. **Practice the following Stream API exercises** at least 3 times:  
   [Stream API Exercises](https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java)

## 4. **Practice Optional methods** at least 2 times:  
   [Shopping Cart Utility](https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java)

## 5. **Discuss best practices** on null pointer exception prevention and provide code snippets for each practice you mention.  
   1. Use Optional Class
```
import java.util.Optional;

public class NullPointerPrevention {
    public static void main(String[] args) {
        Optional<String> optionalValue = Optional.ofNullable(getValue());
        
        // Avoid direct null checks
        optionalValue.ifPresentOrElse(
            value -> System.out.println("Value is: " + value),
            () -> System.out.println("Value is absent")
        );
    }
    
    private static String getValue() {
        return null; // Simulate a null return
    }
}
```
   2.  Perform Explicit Null Checks
```
public class NullCheckExample {
    public static void main(String[] args) {
        String str = getNullableString();
        
        if (str != null) {
            System.out.println(str.length());
        } else {
            System.out.println("String is null");
        }
    }
    
    private static String getNullableString() {
        return null;
    }
}
```
   3. Use Objects.requireNonNull()
```
import java.util.Objects;

public class RequireNonNullExample {
    public static void main(String[] args) {
        String input = getInput();
        
        // Throws a NullPointerException if input is null
        String validInput = Objects.requireNonNull(input, "Input must not be null");
        System.out.println(validInput);
    }
    
    private static String getInput() {
        return null; // Simulating a null input
    }
}
```

   4. Use Default Values Instead of null
```
public class DefaultValueExample {
    public static void main(String[] args) {
        String str = getDefaultString(null);
        System.out.println(str); // Outputs: Default String
    }
    
    private static String getDefaultString(String input) {
        return input != null ? input : "Default String";
    }
}
```

   5. Avoid Returning null in Collections
```
import java.util.Collections;
import java.util.List;

public class AvoidNullInCollections {
    public static void main(String[] args) {
        List<String> items = getItems();
        
        // Safe to iterate, no null check needed
        for (String item : items) {
            System.out.println(item);
        }
    }
    
    private static List<String> getItems() {
        return Collections.emptyList(); // Return empty list instead of null
    }
}
```



## 6. **Discuss Java 8 new features** with code snippets.
   1.  Lambda Expressions
```
import java.util.Arrays;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");
        
        // Traditional for loop
        for (String name : names) {
            System.out.println(name);
        }
        
        // Using Lambda expressions
        names.forEach(name -> System.out.println(name));
    }
}
```

   2. Functional Interfaces
```
import java.util.function.Predicate;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Predicate<String> isLongerThanFive = str -> str.length() > 5;
        
        System.out.println(isLongerThanFive.test("Java"));   // false
        System.out.println(isLongerThanFive.test("Lambda")); // true
    }
}
```
   3. Stream API
```
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter even numbers and collect into a new list
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        
        System.out.println(evenNumbers); // Output: [2, 4, 6, 8, 10]
    }
}
```

   4. Optional Class
```
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optionalValue = Optional.ofNullable(getValue());
        
        // Handle the optional value
        optionalValue.ifPresentOrElse(
            value -> System.out.println("Value is: " + value),
            () -> System.out.println("No value present")
        );
    }
    
    private static String getValue() {
        return null; // Simulating a null value
    }
}
```

   5. Method References
```
import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Using method reference instead of lambda
        names.forEach(System.out::println);
    }
}
```

   6. Default and Static Methods in Interfaces
```
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting");
    }
    
    static void honk() {
        System.out.println("Vehicle is honking");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting");
    }
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        Car car = new Car();
        car.start(); // Car-specific implementation
        
        Vehicle.honk(); // Static method in interface
    }
}
```



## 7. Advantages of the Optional class

The `Optional` class in Java 8 offers several benefits:
1. **Prevents NullPointerException**: By returning an `Optional` instead of `null`, it forces handling of missing values explicitly.
2. **Cleaner Code**: Reduces null checks and uses methods like `isPresent()`, `orElse()`, and `ifPresent()`.
3. **Functional Programming Support**: Works well with `map()`, `filter()`, and `flatMap()` for transforming values.
4. **Safe Method Chaining**: Allows null-safe chaining of method calls.
5. **Expressive APIs**: Makes it clear when a value might be absent, leading to better API design.

```java
Optional<String> optionalValue = Optional.ofNullable(getNullableValue());
System.out.println(optionalValue.orElse("Default Value"));
```

## 8. Explain Functional Interface and Lambda with code samples

A **Functional Interface** is an interface with exactly one abstract method. Lambdas provide a concise way to implement these interfaces without anonymous classes.

```java
@FunctionalInterface
interface GreetService {
    void sayMessage(String message);
}

public class LambdaExample {
    public static void main(String[] args) {
        GreetService greetService = message -> System.out.println("Hello " + message);
        greetService.sayMessage("World");
    }
}
```

## 9. Explain Method Reference with code samples

Method references allow referencing methods directly using the `::` symbol, simplifying lambdas. They can reference static methods, instance methods, or constructors.

```java
import java.util.Arrays;

public class MethodReferenceExample {
    public static void main(String[] args) {
        String[] names = {"John", "Paul", "George", "Ringo"};
        Arrays.sort(names, String::compareToIgnoreCase);
        Arrays.stream(names).forEach(System.out::println);
    }
}
```

## 10. Lambda can use unchanged variable outside of lambda

Lambdas can access variables from their enclosing scope, but the variable must be effectively final (unchanged after declaration).

```java
public class LambdaScopeExample {
    public static void main(String[] args) {
        int number = 5;  // effectively final
        Runnable r = () -> System.out.println(number);
        r.run();
    }
}
```

## 11. Can a functional interface extend/inherit another interface?

Yes, a functional interface can extend another interface, provided the parent interface does not introduce additional abstract methods.

```java
@FunctionalInterface
interface A {
    void methodA();
}

@FunctionalInterface
interface B extends A {
    // Inherits methodA from A
}
```

## 12. What are Intermediate and Terminal operations?

- **Intermediate operations**: Operations on a stream that return another stream (e.g., `filter()`, `map()`). They are lazy, meaning they are not executed until a terminal operation is invoked.
- **Terminal operations**: Operations that produce a result or a side effect (e.g., `forEach()`, `collect()`, `reduce()`).

```java
Stream.of("a", "b", "c")
      .filter(s -> s.startsWith("a"))  // Intermediate
      .forEach(System.out::println);   // Terminal
```

## 13. Demonstrate the most commonly used Intermediate operations in Stream API with code snippets

- **filter()**: Filters elements based on a condition.
- **map()**: Transforms elements.
- **sorted()**: Sorts elements.

```java
Stream.of("apple", "banana", "cherry")
      .filter(s -> s.startsWith("a"))
      .map(String::toUpperCase)
      .sorted()
      .forEach(System.out::println); // Output: APPLE
```

## 14. How are Collections different from Streams?

- **Collections**: Store data in memory and are eagerly constructed. Operations on collections modify or access their elements directly.
- **Streams**: Provide a sequence of data and are lazily constructed. Operations on streams don’t modify the underlying data structure but produce new streams or results.

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream().map(String::toUpperCase);
stream.forEach(System.out::println);  // Streams operate on data lazily
```

## 15. Implement Stream API's filter and map methods by yourself

Here’s an implementation of `filter()` and `map()` using loops:

```java
import java.util.ArrayList;
import java.util.List;

public class CustomStreamOperations {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> names = List.of("John", "Paul", "George", "Ringo");

        List<String> filteredNames = filter(names, name -> name.startsWith("J"));
        List<String> mappedNames = map(filteredNames, String::toUpperCase);

        mappedNames.forEach(System.out::println); // Output: JOHN
    }
}
```

