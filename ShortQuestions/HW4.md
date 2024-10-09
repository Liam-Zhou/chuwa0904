# HW4

Yuhang Li

## 5. Discuss best practices on nullptrexception prevention, provide code snippet for each practice that you mentioned.

Preventing `NullPointerException` (NPE) in Java is essential to writing robust, error-free code. Here are several best practices to avoid NPEs, along with code snippets demonstrating each technique:

### 1. **Use `Optional` (Java 8 and later)**
   - **Why**: `Optional` is a container object that may or may not hold a value, reducing the risk of null values.
   - **How**: Use `Optional.ofNullable()` to wrap a value that might be null, and then use `isPresent()` or `orElse()` to handle it.

   ```java
   Optional<String> optionalValue = Optional.ofNullable(getValue()); // getValue() might return null

   // Safe access with default value
   String result = optionalValue.orElse("Default Value");
   ```

### 2. **Use `Objects.requireNonNull()`**
   - **Why**: This method checks if an object is null and throws a clear `NullPointerException` with a custom message if it is. This helps identify the root cause of the NPE.
   - **How**: Use it to validate method arguments or important variables.

   ```java
   public void process(String input) {
       String value = Objects.requireNonNull(input, "Input cannot be null");
       // Safe to proceed with 'value' as it is non-null
   }
   ```

### 3. **Avoid Returning `null` in Methods**
   - **Why**: Instead of returning `null` from methods, return empty collections, empty strings, or `Optional` to avoid null checks.
   - **How**: Return an empty object or use `Optional`.

   ```java
   // Bad practice - returns null
   public List<String> getNames() {
       return null;
   }

   // Good practice - returns an empty list
   public List<String> getNames() {
       return Collections.emptyList();  // Ensures the caller doesn't have to deal with null
   }
   ```

### 4. **Use Ternary Operator for Safe Assignment**
   - **Why**: The ternary operator allows you to assign a value based on whether a condition (like null) is true.
   - **How**: Use it to provide fallback values.

   ```java
   String name = (inputName != null) ? inputName : "Unknown";
   ```

### 5. **Check for `null` Before Using an Object**
   - **Why**: Always check if an object is `null` before performing operations on it to avoid potential NPEs.
   - **How**: Use standard `if` checks.

   ```java
   if (person != null && person.getAddress() != null) {
       System.out.println(person.getAddress().getStreet());
   }
   ```

### 6. **Use `String.valueOf()` Instead of `toString()`**
   - **Why**: Calling `toString()` on a null object will throw a `NullPointerException`. Using `String.valueOf()` safely converts null to `"null"` instead of throwing an exception.
   - **How**: Use `String.valueOf()`.

   ```java
   String safeString = String.valueOf(someObject);  // Returns "null" if someObject is null
   ```

### 7. **Default Values in Constructors or Initialization**
   - **Why**: Assign default values to fields during initialization or in the constructor to ensure they are not left null.
   - **How**: Provide initial values.

   ```java
   public class User {
       private String name = "";  // Default to empty string, avoids null
       
       public User(String name) {
           this.name = (name != null) ? name : "";
       }
   }
   ```

### 8. **Use Early Returns to Avoid Deep Nesting**
   - **Why**: Avoid deep nested conditions by using early returns, which makes the code cleaner and reduces the chance of NPEs.
   - **How**: Return early if an object is null.

   ```java
   public void printName(String name) {
       if (name == null) {
           System.out.println("Name is null");
           return;  // Early return to avoid further null checks
       }
       System.out.println(name);
   }
   ```

### 9. **Handle Collections and Arrays Carefully**
   - **Why**: Iterating over or accessing null collections or arrays can throw NPE. Always check if the collection is null or empty before operations.
   - **How**: Use null and empty checks.

   ```java
   List<String> items = getItems();
   if (items != null && !items.isEmpty()) {
       for (String item : items) {
           System.out.println(item);
       }
   }
   ```

### 10. **Use Dependency Injection**
   - **Why**: Dependency injection frameworks ensure that required objects are injected automatically, reducing the chance of null values.
   - **How**: Use frameworks like Spring or Guice.

   ```java
   @Autowired
   private Service service;  // Spring will inject this service, avoiding null
   ```

### Summary:
- **Use `Optional`** for better null handling.
- **Use `Objects.requireNonNull()`** for null checks with clear messages.
- **Avoid returning `null`**; return empty collections or objects instead.
- **Check for null** before performing operations.
- **Use default values** for fields and method parameters to avoid null.

These best practices will help you minimize the occurrence of `NullPointerException` and improve the robustness of your Java code.

## 6. Discuss Java8 new features with code snippet.

Java 8 introduced a range of new features and enhancements that revolutionized how Java code is written. Below are some of the key features along with code snippets demonstrating their use:

### 1. **Lambda Expressions**
   - **Purpose**: Enables functional programming by allowing you to write anonymous functions in a concise way.
   - **How**: Replaces boilerplate code for functional interfaces.

   ```java
   // Traditional approach
   Runnable r1 = new Runnable() {
       @Override
       public void run() {
           System.out.println("Hello from traditional Runnable!");
       }
   };
   
   // Lambda expression
   Runnable r2 = () -> System.out.println("Hello from Lambda Runnable!");

   r1.run();
   r2.run();
   ```

### 2. **Functional Interfaces**
   - **Purpose**: An interface with exactly one abstract method (SAM - Single Abstract Method). `@FunctionalInterface` is used for clarity.
   - **How**: Java 8 introduced several built-in functional interfaces like `Predicate`, `Function`, and `Consumer`.

   ```java
   @FunctionalInterface
   interface MyFunctionalInterface {
       void doSomething();
   }

   MyFunctionalInterface example = () -> System.out.println("Doing something!");
   example.doSomething();
   ```

### 3. **Streams API**
   - **Purpose**: Provides a powerful way to process collections of data in a functional style.
   - **How**: You can filter, map, and reduce collections without modifying the original data.

   ```java
   List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe");

   // Stream filtering and mapping
   names.stream()
        .filter(name -> name.startsWith("J"))
        .map(String::toUpperCase)
        .forEach(System.out::println);  // Output: JOHN, JANE, JACK
   ```

### 4. **Optional**
   - **Purpose**: A container that may or may not contain a non-null value. It helps prevent `NullPointerException` by explicitly handling null cases.
   - **How**: Use `Optional.ofNullable()` to wrap a value that might be null.

   ```java
   Optional<String> name = Optional.ofNullable(getName());  // getName() could return null
   name.ifPresent(System.out::println);                    // Prints the name if present
   String defaultName = name.orElse("Unknown");
   ```

### 5. **Default Methods in Interfaces**
   - **Purpose**: Allows adding new methods to existing interfaces without breaking the implementing classes. The method can have a default implementation.
   - **How**: Use `default` keyword in interfaces.

   ```java
   interface Vehicle {
       void drive();
       
       // Default method
       default void start() {
           System.out.println("Vehicle is starting.");
       }
   }

   class Car implements Vehicle {
       @Override
       public void drive() {
           System.out.println("Car is driving.");
       }
   }

   Vehicle car = new Car();
   car.start();  // Output: Vehicle is starting.
   car.drive();  // Output: Car is driving.
   ```

### 6. **Method References**
   - **Purpose**: A more concise way to refer to a method or constructor without using a lambda expression.
   - **How**: Use `ClassName::methodName` syntax.

   ```java
   // Using lambda expression
   List<String> names = Arrays.asList("John", "Jane", "Jack");
   names.forEach(name -> System.out.println(name));

   // Using method reference
   names.forEach(System.out::println);
   ```

### 7. **New Date and Time API (`java.time` package)**
   - **Purpose**: Java 8 introduced a brand new date and time API that is thread-safe, immutable, and more user-friendly than `java.util.Date` and `java.util.Calendar`.
   - **How**: Use classes like `LocalDate`, `LocalTime`, and `LocalDateTime` for date-time manipulation.

   ```java
   LocalDate today = LocalDate.now();                // Current date
   LocalDate birthday = LocalDate.of(1995, Month.JUNE, 15);  // Specific date
   Period age = Period.between(birthday, today);     // Calculate period between two dates
   System.out.println("Age: " + age.getYears());     // Output: Age: <calculated age>

   LocalTime now = LocalTime.now();                  // Current time
   System.out.println("Current Time: " + now);
   ```

### 8. **Collectors in Streams**
   - **Purpose**: Allows you to collect the results of stream operations into various forms like lists, sets, maps, and even custom data structures.
   - **How**: Use `Collectors.toList()`, `Collectors.toSet()`, etc.

   ```java
   List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe");

   List<String> filteredNames = names.stream()
                                     .filter(name -> name.startsWith("J"))
                                     .collect(Collectors.toList());  // Collect into List
   System.out.println(filteredNames);  // Output: [John, Jane, Jack]
   ```

### 9. **Parallel Streams**
   - **Purpose**: Java 8 provides parallel processing of streams to take advantage of multi-core processors.
   - **How**: Simply call `parallelStream()` to make a stream parallel.

   ```java
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
   
   int sum = numbers.parallelStream()
                    .reduce(0, Integer::sum);  // Parallel sum of numbers
   System.out.println(sum);  // Output: 55
   ```

### 10. **Base64 Encoding and Decoding**
   - **Purpose**: Java 8 introduced a built-in Base64 encoder and decoder for handling binary data.
   - **How**: Use `Base64.getEncoder()` and `Base64.getDecoder()`.

   ```java
   String originalInput = "Hello Java 8!";
   String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
   System.out.println(encodedString);  // Encoded output
   
   String decodedString = new String(Base64.getDecoder().decode(encodedString));
   System.out.println(decodedString);  // Decoded output: Hello Java 8!
   ```

### 11. **Streams with File I/O (NIO Enhancements)**
   - **Purpose**: Java 8 provides streams for reading files using the NIO package.
   - **How**: Use `Files.lines()` method to read a file as a stream of lines.

   ```java
   Path path = Paths.get("file.txt");

   // Read all lines from a file and print them
   try (Stream<String> lines = Files.lines(path)) {
       lines.forEach(System.out::println);
   } catch (IOException e) {
       e.printStackTrace();
   }
   ```

### Conclusion
Java 8 introduced several new features that made Java more expressive, efficient, and in line with modern programming paradigms. Key additions like Lambda expressions, the Streams API, `Optional`, and the new Date-Time API greatly improved both code readability and performance.

## 7. What are the advantages of the Optional class?

The **`Optional`** class in Java 8 is a container object used to represent values that might be either present or absent (i.e., null). It helps reduce the risk of `NullPointerException` and provides better mechanisms for handling null values. Here are some of the key advantages of using the `Optional` class:

### 1. **Avoids `NullPointerException`**
   - **Advantage**: One of the main causes of `NullPointerException` is attempting to access methods or properties of null objects. `Optional` provides a way to handle null values gracefully, preventing NPE.
   - **Example**:
     ```java
     Optional<String> optionalValue = Optional.ofNullable(getValue());
     optionalValue.ifPresent(System.out::println); // Executes only if value is present
     ```

### 2. **Explicit Null Handling**
   - **Advantage**: With `Optional`, the possibility of null is made explicit. Developers know that the value might not be present, making the code more predictable and clear.
   - **Example**:
     ```java
     Optional<String> optionalValue = Optional.ofNullable(maybeNullValue);
     String result = optionalValue.orElse("Default Value");  // Provide a default if absent
     ```

### 3. **Reduces Boilerplate Null Checks**
   - **Advantage**: In traditional code, you need to write multiple null checks to handle the possibility of null values. `Optional` reduces the need for these checks and makes the code more concise and readable.
   - **Example** (Before Java 8):
     ```java
     String name = getName();
     if (name != null) {
         System.out.println(name);
     }
     ```
     **With Optional**:
     ```java
     Optional<String> name = Optional.ofNullable(getName());
     name.ifPresent(System.out::println);  // Avoids manual null check
     ```

### 4. **Encourages Functional Programming**
   - **Advantage**: `Optional` works well with functional programming paradigms, allowing you to transform, filter, or map the value if present without the need for explicit checks.
   - **Example**:
     ```java
     Optional<String> name = Optional.ofNullable(getName());
     name.map(String::toUpperCase)
         .ifPresent(System.out::println);  // Apply transformation if value is present
     ```

### 5. **Provides Default Values**
   - **Advantage**: With `Optional`, you can easily provide default values if the value is absent, reducing the risk of returning null values unexpectedly.
   - **Example**:
     ```java
     Optional<String> name = Optional.ofNullable(getName());
     String result = name.orElse("Unknown");  // Returns "Unknown" if value is null
     ```

### 6. **Improves Code Readability**
   - **Advantage**: `Optional` makes code more expressive and easier to understand, as it clearly indicates that the value might be absent.
   - **Example**:
     ```java
     // Without Optional
     String name = getName();
     String finalName = (name != null) ? name : "Default";
     
     // With Optional
     Optional<String> nameOptional = Optional.ofNullable(getName());
     String finalName = nameOptional.orElse("Default");
     ```

### 7. **Prevents Unexpected `null` Return Values**
   - **Advantage**: Instead of returning `null` from methods, returning `Optional` makes the contract of the method clear—signaling to the caller that the return value might be absent, which encourages safe handling.
   - **Example**:
     ```java
     // Method returning Optional instead of null
     public Optional<String> getName() {
         return Optional.ofNullable(name);  // Clear that this method may return an empty Optional
     }
     ```

### 8. **Avoids Complex Nested Null Checks**
   - **Advantage**: If multiple levels of null checks are required, `Optional` simplifies the logic, making the code cleaner and easier to follow.
   - **Example**:
     ```java
     // Without Optional: Multiple null checks
     if (person != null && person.getAddress() != null && person.getAddress().getCity() != null) {
         System.out.println(person.getAddress().getCity());
     }
     
     // With Optional: Chain Optional methods
     Optional.ofNullable(person)
             .map(Person::getAddress)
             .map(Address::getCity)
             .ifPresent(System.out::println);
     ```

### 9. **Better Integration with Streams API**
   - **Advantage**: `Optional` works well with the Java Streams API, allowing for easy integration when dealing with potential nulls in stream operations.
   - **Example**:
     ```java
     Optional<Integer> result = list.stream()
                                    .filter(x -> x > 10)
                                    .findFirst();  // Result wrapped in Optional
     ```

### 10. **Improves Maintainability**
   - **Advantage**: By using `Optional`, it’s easier for other developers (or your future self) to maintain the code since null handling is explicit and consistent across the codebase.
   - **Example**:
     ```java
     Optional<String> name = getName();  // Explicitly says "this might be empty"
     ```

### Key Methods of `Optional`
- `isPresent()`: Checks if a value is present.
- `ifPresent(Consumer)`: Executes a block of code if the value is present.
- `orElse(T)`: Returns the value if present, otherwise returns the default value.
- `orElseGet(Supplier)`: Returns the value if present, otherwise computes the value using the given supplier.
- `orElseThrow(Supplier)`: Returns the value if present, otherwise throws an exception.
- `map(Function)`: Transforms the value if present.
- `filter(Predicate)`: Filters the value if it meets a condition.

### Conclusion:
`Optional` provides a robust mechanism for handling nullable values, improving code clarity, preventing `NullPointerException`, and reducing the need for boilerplate null checks. It encourages better design practices and enhances code readability, maintainability, and safety.

## 8. Explain Functional Interface and Lambda with code samples.

### Functional Interface in Java

A **Functional Interface** is an interface that has only one abstract method, but it can have multiple default or static methods. These interfaces can be used as the target for **lambda expressions** and **method references** in Java 8 and later. The **`@FunctionalInterface`** annotation can be used to mark an interface as functional, though it's optional.

### Example of Functional Interface:
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int x, int y); // Single abstract method
}
```

Here, `Calculator` is a functional interface because it has exactly one abstract method `calculate`.

### Lambda Expressions in Java

A **Lambda Expression** is a concise way to represent an instance of a functional interface. Lambda expressions enable you to treat functionality as a method argument or return it as an object. It provides a clear and readable syntax to instantiate and use a functional interface without having to create anonymous classes.

### Syntax of Lambda Expression:
```java
(parameters) -> expression
```

or

```java
(parameters) -> { statements; }
```

### Lambda Expression with Functional Interface Example:

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int x, int y); // Single abstract method
}

public class LambdaExample {
    public static void main(String[] args) {
        // Using Lambda to implement the calculate method
        Calculator addition = (x, y) -> x + y;
        Calculator multiplication = (x, y) -> x * y;

        System.out.println("Addition: " + addition.calculate(5, 3));  // Output: 8
        System.out.println("Multiplication: " + multiplication.calculate(5, 3));  // Output: 15
    }
}
```

### Key Points about Lambda Expressions:
- **Simplifies code**: They make the code shorter and more readable.
- **Eliminates boilerplate**: No need for anonymous classes to implement functional interfaces.
- **Enhances readability**: The logic is directly passed as a method argument.
  
### Features of Functional Interfaces:
1. **Single Abstract Method**: Only one abstract method is allowed, which makes it suitable for lambdas.
2. **`@FunctionalInterface` Annotation**: Although not mandatory, it helps enforce that the interface will always be functional. If you add another abstract method, the compiler will flag it as an error.
3. **Default and Static Methods**: Functional interfaces can have default and static methods in addition to the abstract method.

### Built-in Functional Interfaces in Java 8

Java 8 provides several built-in functional interfaces in the `java.util.function` package:

1. **Predicate<T>**: Represents a boolean-valued function.
    ```java
    Predicate<String> isEmpty = s -> s.isEmpty();
    System.out.println(isEmpty.test(""));  // Output: true
    ```

2. **Function<T, R>**: Represents a function that takes one argument and produces a result.
    ```java
    Function<String, Integer> stringLength = s -> s.length();
    System.out.println(stringLength.apply("Hello"));  // Output: 5
    ```

3. **Consumer<T>**: Represents an operation that takes an argument and returns no result.
    ```java
    Consumer<String> printer = s -> System.out.println(s);
    printer.accept("Hello Lambda!");  // Output: Hello Lambda!
    ```

4. **Supplier<T>**: Represents a supplier of results.
    ```java
    Supplier<Double> randomValue = () -> Math.random();
    System.out.println(randomValue.get());  // Output: (some random value)
    ```

5. **BinaryOperator<T>**: Represents an operation on two operands of the same type, producing a result of the same type.
    ```java
    BinaryOperator<Integer> add = (a, b) -> a + b;
    System.out.println(add.apply(10, 5));  // Output: 15
    ```

### Example Using Built-in Functional Interfaces:

```java
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalExample {
    public static void main(String[] args) {
        // Using Predicate interface to check if a number is positive
        Predicate<Integer> isPositive = number -> number > 0;
        System.out.println(isPositive.test(10));  // Output: true
        System.out.println(isPositive.test(-5));  // Output: false

        // Using Function interface to calculate the square of a number
        Function<Integer, Integer> square = number -> number * number;
        System.out.println(square.apply(5));  // Output: 25
    }
}
```

### Advantages of Functional Interfaces and Lambdas:
1. **Concise code**: Reduces boilerplate code by removing the need for anonymous inner classes.
2. **Clear intent**: Functional interfaces and lambdas make the intent of the code more explicit and readable.
3. **Improves testability**: Functional programming with lambdas and functional interfaces leads to more testable code as functions can be passed around as parameters and mocked easily.
4. **Encourages immutability**: Lambdas generally promote immutability and functional purity, which leads to fewer bugs.

### Conclusion:
Functional interfaces combined with lambda expressions make Java 8 more powerful, concise, and expressive. It simplifies handling single-method interfaces, allowing for cleaner code with fewer lines and enhanced readability, while also promoting functional programming paradigms.

## 9. Explain Method Reference with code samples?

### Method References in Java

**Method Reference** is a shorthand, compact syntax in Java 8 for writing lambda expressions that execute a method directly. It allows you to reference methods or constructors without actually invoking them, making the code more concise and readable.

Method references are used when you want to refer to a method without executing it immediately. Instead, they defer execution to the functional interface that invokes them.

### Types of Method References

There are four types of method references:
1. **Reference to a Static Method**
2. **Reference to an Instance Method of a Particular Object**
3. **Reference to an Instance Method of an Arbitrary Object of a Specific Type**
4. **Reference to a Constructor**

### 1. **Reference to a Static Method**

You can refer to a static method using `ClassName::methodName`.

#### Example:
```java
import java.util.function.Function;

public class StaticMethodReference {
    // Static method
    public static Integer square(Integer x) {
        return x * x;
    }

    public static void main(String[] args) {
        // Lambda expression equivalent
        Function<Integer, Integer> lambdaSquare = x -> StaticMethodReference.square(x);
        
        // Using method reference
        Function<Integer, Integer> methodRefSquare = StaticMethodReference::square;
        
        System.out.println(lambdaSquare.apply(5));  // Output: 25
        System.out.println(methodRefSquare.apply(5));  // Output: 25
    }
}
```

### 2. **Reference to an Instance Method of a Particular Object**

You can refer to an instance method of a specific object using `instance::methodName`.

#### Example:
```java
import java.util.function.Supplier;

public class InstanceMethodReference {
    public void printMessage() {
        System.out.println("Hello, Method Reference!");
    }

    public static void main(String[] args) {
        InstanceMethodReference instance = new InstanceMethodReference();
        
        // Lambda expression equivalent
        Supplier<Void> lambdaMessage = () -> { instance.printMessage(); return null; };
        
        // Using method reference
        Supplier<Void> methodRefMessage = instance::printMessage;
        
        // Call the method
        lambdaMessage.get();  // Output: Hello, Method Reference!
        methodRefMessage.get();  // Output: Hello, Method Reference!
    }
}
```

### 3. **Reference to an Instance Method of an Arbitrary Object of a Specific Type**

You can refer to an instance method of an arbitrary object of a particular type using `ClassName::methodName`. This is commonly used with stream operations.

#### Example:
```java
import java.util.Arrays;
import java.util.List;

public class ArbitraryObjectMethodReference {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Lambda expression equivalent
        names.forEach(name -> System.out.println(name));

        // Using method reference to instance method 'println' of 'System.out'
        names.forEach(System.out::println);  // Output: Alice Bob Charlie
    }
}
```

### 4. **Reference to a Constructor**

You can refer to a constructor using `ClassName::new`. This is useful when you want to create instances via a method reference.

#### Example:
```java
import java.util.function.Supplier;

class MyClass {
    private String message;

    public MyClass() {
        this.message = "Default message";
    }

    public String getMessage() {
        return message;
    }
}

public class ConstructorReference {
    public static void main(String[] args) {
        // Lambda expression equivalent
        Supplier<MyClass> lambdaSupplier = () -> new MyClass();
        
        // Using constructor reference
        Supplier<MyClass> methodRefSupplier = MyClass::new;
        
        // Create instances
        MyClass myClass1 = lambdaSupplier.get();
        MyClass myClass2 = methodRefSupplier.get();

        System.out.println(myClass1.getMessage());  // Output: Default message
        System.out.println(myClass2.getMessage());  // Output: Default message
    }
}
```

### Summary of Method Reference Syntax:
- **Static method**: `ClassName::staticMethodName`
- **Instance method of a particular object**: `instance::instanceMethodName`
- **Instance method of an arbitrary object of a specific type**: `ClassName::instanceMethodName`
- **Constructor reference**: `ClassName::new`

### Benefits of Using Method References:
1. **More concise code**: It simplifies the lambda expressions and reduces verbosity.
2. **Improves readability**: The intent of the method reference is clear and concise.
3. **Reusability**: You can reuse the existing methods, leading to less code duplication.

### Conclusion:
Method references are a clean and compact alternative to lambdas for invoking methods. They can improve code readability and reduce complexity when using functional interfaces. By referring to existing methods or constructors, they offer a way to write more expressive and maintainable code.

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

In Java, **lambda expressions** can use variables from the enclosing scope, but these variables must be **effectively final**. This means that the variables used inside the lambda expression should not be modified after they are declared. If a variable is not explicitly declared as `final`, but its value does not change, it is considered **effectively final**.

### Why only "unchanged" variables?
The reason lambda expressions in Java can only use "unchanged" (effectively final) variables is because lambdas may execute asynchronously or later than the code where the variable was originally declared. Allowing modifications could lead to unpredictable behavior since the state of the variable might have changed between its declaration and its use in the lambda.

### Example:
Here’s an example that demonstrates how lambdas can use variables from the enclosing scope that are unchanged (effectively final):

```java
import java.util.function.Consumer;

public class LambdaExample {
    public static void main(String[] args) {
        // Effectively final variable
        String greeting = "Hello, ";

        // Using the variable in a lambda expression
        Consumer<String> greeter = name -> System.out.println(greeting + name);

        // Invoking the lambda
        greeter.accept("Alice");  // Output: Hello, Alice

        // Uncommenting the following line would cause a compile-time error
        // greeting = "Hi, "; // Error: Variable used in lambda should be effectively final
    }
}
```

### Explanation:
- In this example, the variable `greeting` is used inside the lambda expression.
- Since the value of `greeting` is not modified after its declaration, it is considered **effectively final**, allowing the lambda to reference it.
- If we tried to modify the variable `greeting` after it was used in the lambda (by uncommenting `greeting = "Hi, ";`), the compiler would throw an error, because it would no longer be effectively final.

### Key Points:
- **Effectively final** means a variable whose value is never changed after it is initialized.
- **Lambda expressions** can capture and use these variables safely because their state won't change after the lambda starts using them.
  
### Example of Invalid Case:
If you try to modify a variable after it's captured by a lambda, it will cause a compilation error:

```java
import java.util.function.Consumer;

public class InvalidLambdaExample {
    public static void main(String[] args) {
        int counter = 0;  // Effectively final variable

        Consumer<String> printer = name -> {
            System.out.println(name + ": " + counter);
        };

        printer.accept("Test");  // Works fine

        // If you uncomment this line, you will get a compile-time error
        // counter++;  // Error: Variable 'counter' is used in lambda expression but is not effectively final
    }
}
```

Here, the attempt to modify the `counter` after using it in the lambda will cause a compilation error because it is no longer effectively final.

### Conclusion:
Lambda expressions can use variables from their enclosing scope as long as those variables are not modified after they are initialized (i.e., they are effectively final). This rule ensures that the lambda's behavior is predictable and avoids issues with variable state changing unexpectedly.

## 11. Can a functional interface extend/inherit another interface?

Yes, a **functional interface** in Java can extend (or inherit) another interface, as long as it does not introduce any additional abstract methods beyond the one abstract method required to maintain its functional interface status. If the extended interface is itself functional (i.e., it has exactly one abstract method), the subclassing interface remains a functional interface.

### Key Points:
- A functional interface can extend any number of other interfaces, provided that it still contains exactly one abstract method.
- The extending interface can inherit default or static methods from the parent interface, and it may add its own.
- The `@FunctionalInterface` annotation is optional but helps ensure that the interface remains functional (i.e., contains exactly one abstract method).

### Example:

Consider the following example where a functional interface extends another interface:

```java
@FunctionalInterface
interface Printer {
    void print(String message);  // Single abstract method
}

@FunctionalInterface
interface AdvancedPrinter extends Printer {
    // This interface still has only one abstract method (inherited from Printer)
    // We can also add default methods here
    default void printTwice(String message) {
        print(message);
        print(message);
    }
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Lambda expression implementing the functional interface
        AdvancedPrinter printer = msg -> System.out.println(msg);
        
        // Using the abstract method
        printer.print("Hello, World!");  // Output: Hello, World!
        
        // Using the default method
        printer.printTwice("Hello Again!");  // Output: Hello Again! Hello Again!
    }
}
```

### Explanation:
- **`Printer`** is a functional interface with one abstract method `print()`.
- **`AdvancedPrinter`** extends `Printer` and inherits the abstract method `print()`. Although `AdvancedPrinter` adds a default method `printTwice()`, it is still considered a functional interface because it has only one abstract method (`print()` inherited from `Printer`).
- A lambda expression is used to implement the `print()` method.

### Rules:
1. If a functional interface extends another interface that has a single abstract method, it remains a functional interface.
2. If the child interface introduces an additional abstract method, it will no longer be a functional interface (as it violates the "single abstract method" rule).

### Example with Error:
If you add another abstract method, the interface will no longer be considered functional, and the compiler will throw an error if you have the `@FunctionalInterface` annotation.

```java
@FunctionalInterface
interface InvalidPrinter extends Printer {
    // Adding another abstract method
    void anotherPrint(String message);  // Error: InvalidPrinter is not a functional interface
}
```

This would result in a compilation error because **`InvalidPrinter`** now has two abstract methods (`print()` and `anotherPrint()`), violating the functional interface constraint.

### Conclusion:
A functional interface can extend another interface as long as it still contains only one abstract method. Default and static methods do not count toward this limit, allowing the interface to remain functional while inheriting or adding non-abstract methods.

## 12. What are Intermediate and Terminal operations?

In Java 8's **Stream API**, operations on streams are classified into two categories: **Intermediate operations** and **Terminal operations**. Understanding the distinction between them is crucial when working with streams.

### 1. **Intermediate Operations**
Intermediate operations return a new stream and are **lazy**, meaning they are not executed until a terminal operation is invoked. These operations allow for method chaining, which facilitates creating a pipeline of processing steps. The stream is not processed until a terminal operation is performed, meaning intermediate operations are **deferred** until needed.

#### Characteristics of Intermediate Operations:
- **Lazy**: They do not process the elements until a terminal operation is executed.
- **Return a stream**: Each intermediate operation returns a new stream, allowing for further operations.
- **Stateless or Stateful**: Some operations, like `map()`, are stateless (they do not need to retain any state between elements), while others, like `distinct()`, are stateful (they retain state to eliminate duplicates).

#### Common Intermediate Operations:
- `map()`: Transforms each element of the stream.
- `filter()`: Filters elements based on a condition.
- `distinct()`: Removes duplicates.
- `sorted()`: Sorts the stream elements.
- `limit()`: Limits the number of elements.
- `skip()`: Skips the first few elements.

#### Example of Intermediate Operations:
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntermediateExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Intermediate operations: filter and map
        List<String> upperCaseNames = names.stream()
                                           .filter(name -> name.startsWith("A"))
                                           .map(String::toUpperCase)  // Converting to uppercase
                                           .collect(Collectors.toList());  // Terminal operation
        
        System.out.println(upperCaseNames);  // Output: [ALICE]
    }
}
```
In this example, `filter()` and `map()` are intermediate operations that return streams for further processing.

### 2. **Terminal Operations**
Terminal operations are **eager**, meaning they trigger the processing of the pipeline and produce a result or a side effect (like printing the elements). Once a terminal operation is invoked, the stream is consumed, and no further operations can be performed on it.

#### Characteristics of Terminal Operations:
- **Eager**: They process the entire pipeline of operations and return a result.
- **Consumes the stream**: After a terminal operation, the stream is considered consumed and cannot be reused.
- **Produces a result**: Terminal operations may produce a result, such as a collection, a primitive value, or a side effect (like `forEach()`).

#### Common Terminal Operations:
- `collect()`: Converts the stream into a collection (like a `List`, `Set`, etc.).
- `forEach()`: Iterates over each element, applying an action.
- `reduce()`: Reduces the elements to a single value (e.g., sum, product).
- `count()`: Returns the number of elements in the stream.
- `findFirst()`: Returns the first element in the stream.
- `anyMatch()`, `allMatch()`, `noneMatch()`: Check whether the stream elements match a condition.

#### Example of a Terminal Operation:
```java
import java.util.Arrays;
import java.util.List;

public class TerminalExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Terminal operation: sum using reduce
        int sum = numbers.stream()
                         .reduce(0, Integer::sum);  // Terminal operation
        
        System.out.println("Sum: " + sum);  // Output: Sum: 15
    }
}
```
In this example, `reduce()` is the terminal operation that triggers the processing of the stream and calculates the sum of the elements.

### Key Differences Between Intermediate and Terminal Operations:

| Feature                   | Intermediate Operations                 | Terminal Operations                  |
| ------------------------- | --------------------------------------- | ------------------------------------ |
| **Execution**             | Lazy (deferred until terminal op)       | Eager (triggers stream processing)   |
| **Return Type**           | Returns another stream                  | Returns a result or a side effect    |
| **Number of Occurrences** | Can be called multiple times in a chain | Can only occur once per stream       |
| **Examples**              | `map()`, `filter()`, `sorted()`         | `collect()`, `forEach()`, `reduce()` |

### Example of Both Intermediate and Terminal Operations:
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperationsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice");

        // Intermediate operations: filter, map, distinct, sorted
        List<String> result = names.stream()
                                   .filter(name -> name.length() > 3)
                                   .map(String::toUpperCase)
                                   .distinct()
                                   .sorted()
                                   .collect(Collectors.toList());  // Terminal operation
        
        System.out.println(result);  // Output: [ALICE, CHARLIE, DAVID]
    }
}
```
Here, the intermediate operations (`filter()`, `map()`, `distinct()`, `sorted()`) build a processing pipeline, while the terminal operation (`collect()`) triggers the processing and returns the final result.

### Conclusion:
- **Intermediate operations** build a stream processing pipeline and are lazily evaluated.
- **Terminal operations** trigger the evaluation of the stream and return a result or side effect, consuming the stream in the process.

## 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

Here’s a demonstration of the most commonly used **Intermediate operations** in the Java 8 **Stream API**. These operations return a new stream and allow for method chaining, building up a pipeline of operations.

### 1. **`filter()`**
Filters elements based on a given condition (predicate).

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Filter even numbers
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());

        System.out.println(evenNumbers);  // Output: [2, 4, 6]
    }
}
```

### 2. **`map()`**
Transforms each element of the stream into another object by applying a function.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Convert each name to uppercase
        List<String> upperCaseNames = names.stream()
                                           .map(String::toUpperCase)
                                           .collect(Collectors.toList());

        System.out.println(upperCaseNames);  // Output: [ALICE, BOB, CHARLIE]
    }
}
```

### 3. **`distinct()`**
Removes duplicates from the stream.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        // Remove duplicates
        List<Integer> distinctNumbers = numbers.stream()
                                               .distinct()
                                               .collect(Collectors.toList());

        System.out.println(distinctNumbers);  // Output: [1, 2, 3, 4, 5]
    }
}
```

### 4. **`sorted()`**
Sorts the elements of the stream in natural order or based on a custom comparator.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

        // Sort in natural order (alphabetical)
        List<String> sortedNames = names.stream()
                                        .sorted()
                                        .collect(Collectors.toList());

        System.out.println(sortedNames);  // Output: [Alice, Bob, Charlie]
    }
}
```

### 5. **`limit()`**
Limits the number of elements in the stream to a specified maximum.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LimitExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Limit the stream to the first 3 elements
        List<Integer> limitedNumbers = numbers.stream()
                                              .limit(3)
                                              .collect(Collectors.toList());

        System.out.println(limitedNumbers);  // Output: [1, 2, 3]
    }
}
```

### 6. **`skip()`**
Skips the first `n` elements of the stream.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkipExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Skip the first 3 elements
        List<Integer> skippedNumbers = numbers.stream()
                                              .skip(3)
                                              .collect(Collectors.toList());

        System.out.println(skippedNumbers);  // Output: [4, 5, 6]
    }
}
```

### 7. **`flatMap()`**
Flattens a nested structure (e.g., a list of lists) into a single-level stream.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<String>> namesList = Arrays.asList(
            Arrays.asList("Alice", "Bob"),
            Arrays.asList("Charlie", "David"),
            Arrays.asList("Eve", "Frank")
        );

        // Flatten the list of lists into a single list
        List<String> flatNamesList = namesList.stream()
                                              .flatMap(List::stream)
                                              .collect(Collectors.toList());

        System.out.println(flatNamesList);  // Output: [Alice, Bob, Charlie, David, Eve, Frank]
    }
}
```

### 8. **`peek()`**
Allows you to perform an operation on each element as it is processed. Commonly used for debugging.

```java
import java.util.Arrays;
import java.util.List;

public class PeekExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Peek to log elements
        numbers.stream()
               .peek(n -> System.out.println("Processing: " + n))  // Side-effect for debugging
               .filter(n -> n % 2 == 0)
               .forEach(System.out::println);  // Output: Processing: 1, 2, 3, 4, 5 (plus actual filtered results: 2, 4)
    }
}
```

### 9. **`mapToInt()`, `mapToDouble()`, `mapToLong()`**
These operations map the stream to primitive data types (int, long, double).

```java
import java.util.Arrays;
import java.util.List;

public class MapToIntExample {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "3", "4");

        // Map to an int stream and sum the numbers
        int sum = numbers.stream()
                         .mapToInt(Integer::parseInt)
                         .sum();

        System.out.println("Sum: " + sum);  // Output: Sum: 10
    }
}
```

### Conclusion:
These examples demonstrate the most commonly used **intermediate operations** in Java 8's Stream API, each of which builds up a processing pipeline for transforming, filtering, and manipulating collections. These operations are **lazy**, meaning they only get executed when a **terminal operation** is applied to the stream.

## 14. How are Collections different from Stream?

**Collections** and **Streams** in Java are both tools for working with data, but they serve different purposes and have distinct characteristics. Below is a comparison between the two:

### 1. **Storage vs. Processing**
   - **Collections**: Collections (like `List`, `Set`, `Map`) are **data structures** that store data in memory. They hold the actual elements and provide various operations to manipulate and retrieve elements.
   - **Streams**: Streams are designed for **processing** data, not storing it. They represent a sequence of elements and provide a functional approach to processing, like filtering, mapping, reducing, etc., but they do not store data themselves.

   ```java
   // Collection example
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);  // A list that holds the data
   ```

   ```java
   // Stream example
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
   Stream<Integer> stream = numbers.stream();  // Stream is for processing the data, not storing it
   ```

### 2. **Eager vs. Lazy Evaluation**
   - **Collections**: Operations on collections are **eager**, meaning they are performed as soon as the operation is called.
   - **Streams**: Streams are **lazy** in nature. Operations are not executed until a terminal operation (like `collect()`, `forEach()`, etc.) is invoked, making intermediate operations more efficient because they are processed only when needed.

   ```java
   // Collection operation is eager
   List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());  // Filter is applied immediately
   ```

   ```java
   // Stream operation is lazy
   Stream<Integer> stream = numbers.stream().filter(n -> n % 2 == 0);  // Filter is not applied until terminal operation is invoked
   List<Integer> evenNumbers = stream.collect(Collectors.toList());
   ```

### 3. **Finite vs. Infinite**
   - **Collections**: Collections are **finite**. You have a fixed number of elements in a collection, and the size does not change unless you add or remove elements.
   - **Streams**: Streams can be **finite** or **infinite**. For example, you can create an infinite stream using `Stream.iterate()` or `Stream.generate()`, which keeps producing elements until a terminal condition is met.

   ```java
   // Infinite stream example
   Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);  // Produces infinite numbers
   List<Integer> finiteList = infiniteStream.limit(5).collect(Collectors.toList());  // Limit the stream to the first 5 elements
   ```

### 4. **External vs. Internal Iteration**
   - **Collections**: Collections use **external iteration**. You need to explicitly iterate over the elements using loops (like `for-each` or `iterator`).
   - **Streams**: Streams use **internal iteration**. The iteration happens behind the scenes, abstracted by stream operations, making the code more concise and functional.

   ```java
   // Collection - External iteration using a for-each loop
   for (Integer number : numbers) {
       System.out.println(number);
   }

   // Stream - Internal iteration using forEach (Stream handles the iteration internally)
   numbers.stream().forEach(System.out::println);
   ```

### 5. **Mutability vs. Immutability**
   - **Collections**: Collections are **mutable**. You can add, remove, or update elements in a collection.
   - **Streams**: Streams are **immutable**. Once a stream is created, you cannot modify the data it holds. Instead, you apply transformations that result in new streams or other data structures.

   ```java
   // Collection - Mutable
   List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
   numbers.add(4);  // You can add elements

   // Stream - Immutable
   List<Integer> numbers = Arrays.asList(1, 2, 3);
   Stream<Integer> stream = numbers.stream().filter(n -> n > 1);  // You cannot modify the stream directly
   ```

### 6. **Reusability**
   - **Collections**: Collections can be reused multiple times. You can iterate over a collection any number of times.
   - **Streams**: Streams are **not reusable**. Once a stream is consumed (through a terminal operation), it cannot be reused. You need to create a new stream from the source if you want to process it again.

   ```java
   // Collection - Reusable
   List<Integer> numbers = Arrays.asList(1, 2, 3);
   for (Integer number : numbers) {
       System.out.println(number);  // Can iterate multiple times
   }

   // Stream - Not reusable
   Stream<Integer> stream = numbers.stream();
   stream.forEach(System.out::println);  // After this terminal operation, the stream cannot be used again
   // Attempting to use stream again would result in IllegalStateException
   ```

### 7. **Parallelism**
   - **Collections**: To process collections in parallel, you would need to manually manage threading or use external libraries.
   - **Streams**: Streams have **built-in support for parallel processing**. You can easily convert a stream to a parallel stream by calling `parallelStream()` or `parallel()` and the stream will divide the workload across available processor cores.

   ```java
   // Stream parallel processing
   numbers.parallelStream().forEach(System.out::println);  // Processes the stream elements in parallel
   ```

### Summary:

| Feature                 | Collections               | Streams                                 |
| ----------------------- | ------------------------- | --------------------------------------- |
| **Purpose**             | Stores data               | Processes data                          |
| **Evaluation**          | Eager                     | Lazy                                    |
| **Finite/Infinite**     | Finite                    | Can be finite or infinite               |
| **Iteration**           | External (explicit loops) | Internal (handled by stream operations) |
| **Mutability**          | Mutable (modifiable)      | Immutable (cannot modify stream)        |
| **Reusability**         | Reusable                  | Not reusable (one-time use)             |
| **Parallel Processing** | Manual (if needed)        | Built-in support for parallelism        |

Streams are great for processing data in a declarative, functional style, while collections are primarily used to hold and manipulate data in memory.

## 15. Implement Stream API's filter and map methods by your self.

To implement the `filter` and `map` methods of the Java 8 Stream API from scratch, we can create our own custom stream class that encapsulates a list of elements and provides these methods. Below is a simple implementation that demonstrates how to do this.

### Custom Stream Implementation

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class CustomStream<T> {
    private List<T> elements;

    // Constructor to initialize the stream with elements
    public CustomStream(List<T> elements) {
        this.elements = elements;
    }

    // Custom filter method
    public CustomStream<T> filter(Predicate<? super T> predicate) {
        List<T> filteredElements = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        return new CustomStream<>(filteredElements);
    }

    // Custom map method
    public <R> CustomStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedElements = new ArrayList<>();
        for (T element : elements) {
            mappedElements.add(mapper.apply(element));
        }
        return new CustomStream<>(mappedElements);
    }

    // Method to collect the elements into a List
    public List<T> collect() {
        return new ArrayList<>(elements);
    }
}
```

### Example Usage

Here’s how you can use the `CustomStream` class to filter and map elements:

```java
import java.util.Arrays;
import java.util.List;

public class CustomStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Create a CustomStream from the list of numbers
        CustomStream<Integer> customStream = new CustomStream<>(numbers);

        // Use the filter and map methods
        List<Integer> evenNumbers = customStream
                .filter(n -> n % 2 == 0) // Filter even numbers
                .map(n -> n * n)        // Map to their squares
                .collect();             // Collect the result

        System.out.println(evenNumbers); // Output: [4, 16, 36]
    }
}
```

### Explanation

1. **`CustomStream` Class**:
   - This class takes a list of elements and provides methods for `filter` and `map`.
   - The `filter` method iterates through the elements, applying a given predicate and returning a new `CustomStream` containing only the elements that match the predicate.
   - The `map` method transforms each element in the stream according to the provided function and returns a new `CustomStream` with the transformed elements.

2. **`collect` Method**:
   - The `collect` method converts the `CustomStream` back into a `List`.

3. **Usage**:
   - In the `CustomStreamExample` class, we demonstrate how to use our custom stream by filtering even numbers and then mapping them to their squares.
   - Finally, we collect the results and print them.

### Summary

This implementation provides a basic framework for filtering and mapping using a custom stream-like class. You can expand upon this by adding more methods to handle other stream operations, such as `reduce`, `distinct`, and so on, as needed.
