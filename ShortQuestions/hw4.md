## HW4 -- Java8

### 1.  Learn Java generics by reading and practicing following code

<https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic>

### 2. Read the following code repo and type it one by one by yourself.

<https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features>

[See Coding/Java8_features](../Coding/Java8_features)

### 3. Practice following stream API exercises at least 3 times

<https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java>


### 4. Practice Optional methods at least 2 times

<https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java>

### 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.

**1. Explicit Null Checks**

```java
String str = getString(); // Some method that returns a string

if (str != null) {
    System.out.println(str.length());
} else {
    System.out.println("String is null");
}
```

**2. Use `Optional` in Java**

```java
import java.util.Optional;

Optional<String> optionalStr = Optional.ofNullable(getString()); // Returns an Optional

// Using Optional to handle null safely
optionalStr.ifPresentOrElse(
    value -> System.out.println(value.length()),
    () -> System.out.println("String is null")
);
```

**3. Initialize Objects Properly**

```java
// Instead of declaring without initialization
List<String> list = null;

// Initialize it properly
List<String> list = new ArrayList<>();
```

**4. Throw and Catch**

```java
public void handleName(String name) {
    try {
        // Attempt to process the name, which may throw an exception
        processName(name);
    } catch (NullPointerException e) {
        // Handle the exception gracefully
        System.out.println("Caught an exception: " + e.getMessage());
        // Additional error handling logic, like logging
    }
}
```

### 6. Discuss Java 8 new features with code snippet.

**1. Lambda Expressions**

```java
import java.util.Arrays;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using lambda expression to iterate through a list
        names.forEach(name -> System.out.println(name));
    }
}
```

**2. Functional Interfaces and `@FunctionalInterface` Annotation**

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void printMessage(String message);
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Using lambda expression to implement a functional interface
        MyFunctionalInterface myFunc = message -> System.out.println(message);
        myFunc.printMessage("Hello, Functional Interface!");
    }
}
```

**3. Stream API**

```java
import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Using Stream API to filter and print names starting with 'D'
        names.stream()
            .filter(name -> name.startsWith("D"))
            .forEach(System.out::println); // Output: David
    }
}
```

**5. `Optional` Class**

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable(getName());

        // Using Optional to handle null safely
        optional.ifPresentOrElse(
            name -> System.out.println("Name: " + name),
            () -> System.out.println("Name is not present")
        );
    }

    public static String getName() {
        return null; // Or return some value
    }
}
```

### 7. What are the advantages of the` Optional` class?

**1. Avoids `NullPointerException`**

Instead of returning null from methods, we can return an `Optional` object, which the caller must handle appropriately.

```java
Optional<String> name = Optional.ofNullable(getName()); // getName() might return null

// Handle the absence of value safely
name.ifPresentOrElse(
    n -> System.out.println("Name is: " + n),
    () -> System.out.println("Name is not present")
);
```

**2. Makes Code More Readable and Expressive**

**3. Provides Better Control Over Default Values**

With `Optional`, we can define a default value or a fallback value if the value is not present, using methods `like orElse()` or `orElseGet()`.

```java
Optional<String> name = Optional.ofNullable(getName());

// Provide a default value if name is not present
String defaultName = name.orElse("Default Name");
System.out.println(defaultName);

```

### 8. Explain Functional Interface and Lambda with code samples

**1. Functional Interface**

- Has **one single Abstract method**
- `@FunctionalInterface` is for `sanity check onl`y, this annotation is NOT required for functional interface
  definition. 
- Can have a lot of default methods (Starting from Java 8)
- **Lambda is the implementation of the abstract method**

```java
@FunctionalInterface
public interface Bar { 
   // only one single Abstract method
   String method(String string);
   
   default String defaultBar() {
      String s = "default Bar method";
      System.out.println(s);
    return s;
      }
      
    default String defaultCommon() {
        String s = "defaultCommon from Bar";
        System.out.println(s);
    return s;
 }
}
```

**2. Lambda Expressions**

Lambda expressions provide a way to implement the single abstract method of a functional interface concisely. They allow you to create an instance of a functional interface using an expression instead of an anonymous class. The syntax of a lambda expression is:

```java
(parameters) -> expression
or
(parameters) -> { statements; }

```

- **Replace Anonymous Inner Classes**:Lambda expressions provide a concise way to replace anonymous inner classes, making the code shorter and more readable.
- **Work with Functional Interfaces**: Lambda expressions work with functional interfaces, which have exactly one abstract method (SAM - Single Abstract Method). This allows developers to implement these interfaces more concisely.
- **Syntax Sugar for Implementing SAM Interfaces**: Lambda expressions are essentially syntactic sugar for implementing interfaces that have a single abstract method (SAM). In Java 8, interfaces can also include static and default methods.
- **Any Interface with a Single Abstract Method is a Functional Interface**: Any interface that meets the SAM criteria (i.e., has a single abstract method) can be used with lambda expressions, even if it is not explicitly marked with the `@FunctionalInterface` annotation.
- **Use @FunctionalInterface Annotation for Compiler Checks**: While not mandatory, using the `@FunctionalInterface` annotation when declaring a functional interface enforces that the interface has exactly one abstract method. The compiler will generate an error if this is not the case.
- **Common Functional Interfaces with `@FunctionalInterface` Annotation**: Previously known SAM interfaces such as `Runnable`, `Comparator`, and `FileFilter` are marked with the `@FunctionalInterface` annotation.
- **New Functional Interfaces in Java 8**: Java 8 introduced many new functional interfaces in the java.util.function package, such as `Consumer`, `Supplier`, `Predicate`, and `Function`, categorized into types like consumer, supplier, predicate (for checks), and function (for operations).
- **Custom Functional Interfaces**: Developers can also create custom functional interfaces to fit specific development needs, as long as these interfaces have exactly one abstract method.

**Example of Lambda Expressions with a Functional Interface**

```java
public class LambdaExample {
    public static void main(String[] args) {
        // Implementing the Greeting functional interface using a lambda expression
        Greeting greeting = (String name) -> System.out.println("Hello, " + name);

        // Using the lambda expression
        greeting.sayHello("Alice"); // Output: Hello, Alice
    }
}
```

### 9. Explain Method Reference with code samples?

Method References are a feature introduced in Java 8 that provide a shorthand, concise syntax for invoking methods. They are often used with lambda expressions to make the code more readable and clean. Method references allow you to refer to methods of existing classes or objects without invoking them explicitly.

Method references help eliminate boilerplate code and provide a more readable alternative to lambda expressions, particularly when a lambda expression only calls a method.

#### Summary of Method References

1. **Reference to a Static Method**: `ClassName::staticMethodName`

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
numbers.forEach(StaticMethodReferenceExample::printNumber);

public static void printNumber(int number) {
    System.out.println(number);
}
```

2. **Reference to an Instance Method of a Particular Object**: `objectName::instanceMethodName`

```java
InstanceMethodReferenceExample example = new InstanceMethodReferenceExample();
List<String> names = Arrays.asList("Alice", "Bob");
names.forEach(example::printName);

public void printName(String name) {
    System.out.println(name);
}
```

3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**: `ClassName::instanceMethodName`

```java
List<String> names = Arrays.asList("Alice", "Bob");
names.sort(String::compareToIgnoreCase);
```

4. **Reference to a Constructor**: `ClassName::new`

```java
Supplier<Person> personSupplier = Person::new;
Person person = personSupplier.get();
```

5. **Reference to an Array Constructor**: `ArrayType::new`

```java
Function<Integer, String[]> arrayConstructor = String[]::new;
String[] array = arrayConstructor.apply(5);
```

#### Benefits of Using Method References
Conciseness, readability and reusability

### 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

Lambdas in Java can access and use variables from the enclosing scope, but these variables must be effectively final—assigned only once and never modified afterward. This restriction ensures safe and predictable behavior in concurrent and asynchronous programming scenarios.

- Final variable
- Non-final variable however never changed
- Object variable (if we use new to reassign a new memory, then this variable is changed, can not be used in
lambda)

```java
import java.util.Arrays;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        // Variable defined outside of the lambda expression
        String prefix = "Hello, ";

        // List of names
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Lambda expression using the effectively final variable 'prefix'
        names.forEach(name -> System.out.println(prefix + name));

        // If uncomment the next line, it will cause a compilation error
        // because 'prefix' is no longer effectively final
        // prefix = "Hi, ";  // Error: Local variable prefix defined in an enclosing scope must be final or effectively final
    }
}
```

### 11. Can a functional interface extend/inherit another interface?

Yes, a functional interface in Java can extend or inherit from another interface. BUt some rules must be followed to ensure that the resulting interface remains a functional interface.

1. Single Abstract Method (SAM) Rule
2. Multiple Default Methods
3. No Conflicting Abstract Methods

### 12. What are Intermediate and Terminal operations?

- **Intermediate Operations**: Define a pipeline of transformations and are lazily executed. They return a new stream, allowing for method chaining.
- **Terminal Operations**: Trigger the processing of the stream and produce a result, consuming the stream in the process. After a terminal operation is executed, the stream can no longer be reused.

**Differences Between Intermediate and Terminal Operations**

![Differences Between Intermediate and Terminal Operations](../Src/img/Differences%20Between%20Intermediate%20and%20Terminal%20Operations.jpg)

### 13. Demonstrate the most commonly used Intermediate operations in Stream API, with code snippet.

1. `filter(Predicate<? super T> predicate)`

Filters the elements of a stream based on a given predicate (a condition).

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Use 'filter' to keep only names starting with 'A'
        List<String> filteredNames = names.stream()
                                          .filter(name -> name.startsWith("A"))
                                          .collect(Collectors.toList());

        System.out.println(filteredNames); // Output: [Alice]
    }
}
```

2. `map(Function<? super T, ? extends R> mapper)`

3. Transforms each element of the stream using a given function. The result is a new stream consisting of the results of applying the function to each element.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Use 'map' to convert all names to uppercase
        List<String> uppercasedNames = names.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());

        System.out.println(uppercasedNames); // Output: [ALICE, BOB, CHARLIE]
    }
}
```

3. `flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`

Transforms each element into a stream and then flattens all the streams into a single stream. Useful when dealing with nested data structures

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Alice", "Bob"),
                Arrays.asList("Charlie", "David")
        );

        // Use 'flatMap' to flatten the nested list
        List<String> flatList = nestedList.stream()
                                          .flatMap(List::stream)
                                          .collect(Collectors.toList());

        System.out.println(flatList); // Output: [Alice, Bob, Charlie, David]
    }
}
```

4. `distinct()`

Removes duplicate elements from the stream based on the equals() method.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Alice", "David");

        // Use 'distinct' to remove duplicates
        List<String> distinctNames = names.stream()
                                          .distinct()
                                          .collect(Collectors.toList());

        System.out.println(distinctNames); // Output: [Alice, Bob, David]
    }
}
```

5. `sorted()` and `sorted(Comparator<? super T> comparator)`

Sorts the elements of the stream in natural order or based on a provided comparator.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("David", "Alice", "Charlie", "Bob");

        // Use 'sorted' to sort names in natural order
        List<String> sortedNames = names.stream()
                                        .sorted()
                                        .collect(Collectors.toList());

        System.out.println(sortedNames); // Output: [Alice, Bob, Charlie, David]
    }
}
```

6. `peek(Consumer<? super T> action)`

Performs an action on each element of the stream and returns a stream consisting of the elements. It is typically used for debugging purposes.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeekExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Use 'peek' to perform an action (e.g., print) on each element
        List<String> modifiedNames = names.stream()
                                          .peek(name -> System.out.println("Processing: " + name))
                                          .map(String::toUpperCase)
                                          .collect(Collectors.toList());

        System.out.println(modifiedNames); // Output: [ALICE, BOB, CHARLIE]
    }
}
```

7. `limit(long maxSize)`

Returns a stream consisting of the elements, truncated to be no longer than the specified maximum size.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LimitExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Use 'limit' to keep only the first 2 elements
        List<String> limitedNames = names.stream()
                                         .limit(2)
                                         .collect(Collectors.toList());

        System.out.println(limitedNames); // Output: [Alice, Bob]
    }
}
```

8. `skip(long n)`

Returns a stream consisting of the remaining elements of the stream after discarding the first n elements.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkipExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Use 'skip' to discard the first 2 elements
        List<String> skippedNames = names.stream()
                                         .skip(2)
                                         .collect(Collectors.toList());

        System.out.println(skippedNames); // Output: [Charlie, David]
    }
}
```

9. ` mapToInt(ToIntFunction<? super T> mapper)`

`ToIntFunction<? super T> mapper`: A functional interface representing a function that takes an object of type T and returns an int.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MapToIntExample {
    public static void main(String[] args) {
        List<String> numbersAsString = Arrays.asList("1", "2", "3", "4", "5");

        // Use 'mapToInt' to convert each string to an integer and compute the sum
        int sum = numbersAsString.stream()
                                 .mapToInt(Integer::parseInt) // Convert each string to an int
                                 .sum(); // Terminal operation to calculate the sum

        System.out.println("Sum of numbers: " + sum); // Output: Sum of numbers: 15
    }
}
```

### 14. How are Collections different from Stream?

![Collections and Streams](../Src/img/Collections%20&%20Streams.jpg)

### 15. Implement Stream API's filter and map methods by yourself

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

// Custom Stream-like class
class MyStream<T> {
    private final List<T> source;

    // Constructor that accepts a list
    private MyStream(List<T> source) {
        this.source = source;
    }

    // Factory method to create a MyStream instance
    public static <T> MyStream<T> of(List<T> source) {
        return new MyStream<>(source);
    }

    // Implementing the filter method
    public MyStream<T> filter(Predicate<? super T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T element : source) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return new MyStream<>(filteredList);
    }

    // Implementing the map method
    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedList = new ArrayList<>();
        for (T element : source) {
            mappedList.add(mapper.apply(element));
        }
        return new MyStream<>(mappedList);
    }

    // Terminal operation to collect the result into a list
    public List<T> collect() {
        return source;
    }
}
```


