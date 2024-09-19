1. Learn Java generics by reading and practicing following code:
2. Read the follwoing code repo and type it one by one by yourself.
3. Practice following stream API exercises at least 3 times
4. Practice Optional methods at least 2 times
5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
    1. Use Optional
        ```java
        import java.util.Optional;

        public class User {
            private String name;

            public Optional<String> getName() {
                return Optional.ofNullable(name);
            }

            public static void main(String[] args) {
                User user = new User();
                
                // Use ifPresent() to avoid NullPointerException
                user.getName().ifPresent(n -> System.out.println("Name: " + n));

                // Use orElse() to provide a default value if name is null
                String name = user.getName().orElse("Unknown");
                System.out.println("Name: " + name);
            }
        }
        ```
    2. Use Ternary Operator
        ```java
        public class Customer {
            private String address;

            public String getAddress() {
                return address;
            }

            public static void main(String[] args) {
                Customer customer = new Customer();
                
                // Use ternary operator to avoid NullPointerException
                String customerAddress = customer.getAddress() != null ? customer.getAddress() : "Address not available";
                System.out.println(customerAddress);
            }
        }
        ```
    3.  Use try-catch
        ```java
        public class Book {
            private String title;

            public String getTitle() {
                return title;
            }

            public static void main(String[] args) {
                Book book = null;

                try {
                    // This will throw NullPointerException
                    System.out.println(book.getTitle());
                } catch (NullPointerException e) {
                    System.out.println("Book is null");
                }
            }
        }
        ```
6. Discuss Java 8 new features with code snippet.
    1. Lambda Expressions
        Lambda expressions provide a clear and concise way to represent one method interface using an expression. They enable functional programming in Java by allowing you to pass behavior (functions) as arguments.
        ```java
        import java.util.Arrays;
        import java.util.List;

        public class LambdaExample {
            public static void main(String[] args) {
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

                // Using Lambda Expression to iterate
                names.forEach(name -> System.out.println(name));
            }
        }
        ```
    2. Functional Interfaces and @FunctionalInterface Annotation
        A functional interface is an interface with exactly one abstract method. Java 8 introduced several built-in functional interfaces in the java.util.function package like Predicate, Function, Consumer, and Supplier.
        ```java
        import java.util.function.Predicate;

        public class FunctionalInterfaceExample {
            public static void main(String[] args) {
                // Using Predicate functional interface with a lambda expression
                Predicate<String> isLongerThan5 = s -> s.length() > 5;

                System.out.println(isLongerThan5.test("Hello"));  // Output: false
                System.out.println(isLongerThan5.test("Hello, World!"));  // Output: true
            }
        }
        ```
    3. Streams API
        The Streams API allows you to process sequences of elements (like collections) in a functional style. It provides methods for filtering, mapping, reducing, and collecting data.

        ```java
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

        public class StreamExample {
            public static void main(String[] args) {
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

                // Using Stream to filter names starting with 'C' and collect the results
                List<String> filteredNames = names.stream()
                                                .filter(name -> name.startsWith("C"))
                                                .collect(Collectors.toList());

                System.out.println(filteredNames);  // Output: [Charlie]
            }
        }
        ```
    4. Default and Static Methods in Interfaces
        Java 8 allows you to add non-abstract methods to interfaces using the default and static keywords. This provides a way to add methods to interfaces without breaking the existing implementations.
        ```java
        interface Vehicle {
            // Default method
            default void start() {
                System.out.println("Vehicle is starting");
            }

            // Static method
            static void stop() {
                System.out.println("Vehicle is stopping");
            }
        }

        public class Car implements Vehicle {
            public static void main(String[] args) {
                Car car = new Car();
                car.start();  // Calls the default method
                Vehicle.stop();  // Calls the static method
            }
        }
        ```
    5. Optional Class
        The Optional class is used to handle null values more gracefully. It helps to avoid NullPointerException by providing a container object that may or may not contain a non-null value.
        ```java
        import java.util.Optional;

        public class OptionalExample {
            public static void main(String[] args) {
                Optional<String> name = Optional.ofNullable(null);

                // Using Optional to provide a default value if the value is null
                String result = name.orElse("Default Name");
                System.out.println(result);  // Output: Default Name

                // Using Optional with a lambda expression
                name.ifPresent(n -> System.out.println("Name is: " + n));  // Will not execute
            }
        }
        ```
7. What are the advantages of the Optional class?
    The Optional class in Java 8 was introduced as a part of the java.util package to handle situations where a value may or may not be present. It provides a more expressive and less error-prone way to deal with null values, which are a common source of NullPointerException. Here are the key advantages of using the Optional class:

    1. Avoids NullPointerException
        One of the primary advantages of Optional is to reduce the risk of NullPointerException (NPE). By using Optional, you explicitly indicate that a value might be absent, which forces the programmer to handle the absence of a value properly.

        ```java
        Optional<String> name = Optional.ofNullable(getName());
        name.ifPresent(n -> System.out.println("Name: " + n)); // Safe to call without risk of NPE
        ```
    2. Makes Code More Readable and Expressive
        Using Optional makes it clear that a variable or return type might not contain a value. This provides better readability and expressiveness, making the code self-documenting.

        ```java
        public Optional<String> findUsernameById(int id) {
            // Explicitly indicates that the result may or may not contain a value
            return Optional.ofNullable(database.findUsername(id));
        }
        ```
        In this example, the return type Optional<String> clearly indicates that the result might not be present, making the code more expressive.
    3. Provides Methods for Safe Handling of Values
        Optional provides a rich set of methods to safely handle the presence or absence of values. Some of the key methods include:
        ```java
        isPresent(): Checks if a value is present.
        ifPresent(): Executes a given action if a value is present.
        orElse(): Returns a default value if a value is absent.
        orElseGet(): Returns a value from a Supplier if the value is absent.
        orElseThrow(): Throws an exception if the value is absent.
        map(): Transforms the value if present.
        flatMap(): Similar to map(), but returns an Optional.

        Optional<String> optionalName = Optional.ofNullable(getName());

        // Execute a block of code only if the value is present
        optionalName.ifPresent(name -> System.out.println("Hello, " + name));

        // Provide a default value if the value is absent
        String name = optionalName.orElse("Guest");
        System.out.println("Welcome, " + name);
        ```
8. Explain Functional Interface and Lambda with code samples.
```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}
@Lambda
(parameters) -> { statements; }
```
9. Explain Method Reference with code samples?
    Method references are a shorthand syntax in Java that allows you to refer to methods or constructors directly without invoking them. Method references are introduced in Java 8, and they provide a concise way to write lambda expressions that call existing methods. They are often used in conjunction with functional interfaces to make the code more readable and less verbose.
    Types of Method References
    There are four types of method references in Java:
    1. Reference to a static method: ClassName::staticMethodName
    2. Reference to an instance method of a particular object: instance::instanceMethodName
    3. Reference to an instance method of an arbitrary object of a particular type: ClassName::instanceMethodName
    4. Reference to a constructor: ClassName::new

    1. Reference to a Static Method
        ```java
        import java.util.function.BiFunction;

        public class StaticMethodReferenceExample {
            // Static method
            public static int add(int a, int b) {
                return a + b;
            }

            public static void main(String[] args) {
                // Using a lambda expression
                BiFunction<Integer, Integer, Integer> additionLambda = (a, b) -> StaticMethodReferenceExample.add(a, b);
                System.out.println(additionLambda.apply(10, 20));  // Output: 30

                // Using method reference
                BiFunction<Integer, Integer, Integer> additionMethodRef = StaticMethodReferenceExample::add;
                System.out.println(additionMethodRef.apply(10, 20));  // Output: 30
            }
        }
        ```
    2. Reference to an Instance Method of a Particular Object
        ```java
        import java.util.function.Consumer;

        public class InstanceMethodReferenceExample {
            // Instance method
            public void printMessage(String message) {
                System.out.println("Message: " + message);
            }

            public static void main(String[] args) {
                InstanceMethodReferenceExample example = new InstanceMethodReferenceExample();

                // Using a lambda expression
                Consumer<String> lambdaConsumer = message -> example.printMessage(message);
                lambdaConsumer.accept("Hello, World!");  // Output: Message: Hello, World!

                // Using method reference
                Consumer<String> methodRefConsumer = example::printMessage;
                methodRefConsumer.accept("Hello, World!");  // Output: Message: Hello, World!
            }
        }
        ```

10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
    In Java, lambda expressions can capture variables from their enclosing scope. However, these captured variables must be effectively final, meaning that their value must not be modified after they are initialized. In other words, you can use variables from the outer scope inside a lambda expression, but you cannot change their values within the lambda or elsewhere in the outer scope after their initialization.

    This constraint ensures that the behavior of the lambda expression is predictable and consistent with the concept of functional programming, where functions typically do not have side effects.

    Key Points:
        Effectively Final: A variable is effectively final if it is not modified after being assigned.
        Immutable Capture: Lambda expressions can capture and use variables from the enclosing scope only if those variables are not modified after their declaration.
    ```java
    public class LambdaScopeExample {
    public static void main(String[] args) {
        // A local variable in the enclosing scope
        int number = 10;  // This variable is "effectively final"

        // Using the variable inside a lambda expression
        Runnable runnable = () -> {
            System.out.println("The number is: " + number);  // Using 'number' inside the lambda
        };

        // Execute the runnable
        runnable.run();  // Output: The number is: 10

        // Attempting to modify the 'number' variable after lambda declaration would cause a compile-time error
        // number = 20; // Uncommenting this line will result in a compile-time error
        }
    }
    ```
    Explanation:
    The local variable number is used inside the lambda expression: () -> System.out.println("The number is: " + number);.
    Effectively Final: The variable number is not modified after it is assigned. This makes it an effectively final variable, which allows it to be used within the lambda expression.
    If you try to modify number (e.g., number = 20;), Java will throw a compile-time error because the lambda expression captures number as an effectively final variable. This ensures that the value of number inside the lambda does not change unexpectedly.

11. Can a functional interface extend/inherit another interface?
    Yes, a functional interface in Java can extend or inherit another interface, including another functional interface.
12. What are Intermediate and Terminal operations?
    In Java, the Streams API provides a way to process sequences of elements in a functional style. Within this API, operations on streams are classified into two categories: intermediate operations and terminal operations. Understanding these operations is crucial for effectively using streams to manipulate data.

    1. Intermediate Operations
        Intermediate operations are lazy operations on a stream that transform a stream into another stream. They are "lazy" because they do not process the stream elements immediately but instead return a new stream that knows how to perform the specified operation. The actual processing happens when a terminal operation is invoked on the stream.

        Characteristics:
            They are lazy: They do not process the stream elements immediately but create a new stream that processes elements only when a terminal operation is invoked.
            They are chained: Intermediate operations can be chained together to form a pipeline of operations.
            They return a new stream: Each intermediate operation returns a new stream, which can be further processed.

    2. Terminal Operations
        Terminal operations are operations that trigger the processing of the stream pipeline and produce a result or a side-effect. Once a terminal operation is executed, the stream is considered consumed and can no longer be used. Terminal operations may return a result of any type, such as a single value (reduce, count), a List (collect), or void (forEach).

        Characteristics:

        Trigger processing: Terminal operations are responsible for triggering the processing of intermediate operations in the stream.
        Consume the stream: After a terminal operation is invoked, the stream is consumed and cannot be used again.
        Produce a result or side-effect: They produce a result (e.g., collect, reduce) or a side-effect (e.g., forEach).
13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
    1. filter
        ```java
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

        public class FilterExample {
            public static void main(String[] args) {
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

                // Filter names that start with 'A'
                List<String> filteredNames = names.stream()
                                                .filter(name -> name.startsWith("A"))
                                                .collect(Collectors.toList());

                System.out.println(filteredNames);  // Output: [Alice]
            }
        }
        ```
    2. map(Function<T, R>)
        ```java
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;

        public class MapExample {
            public static void main(String[] args) {
                List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

                // Convert all names to uppercase
                List<String> upperCaseNames = names.stream()
                                                .map(String::toUpperCase)
                                                .collect(Collectors.toList());

                System.out.println(upperCaseNames);  // Output: [ALICE, BOB, CHARLIE]
            }
        }
        ```
14. How are Collections different from Stream?
Nature	
    Collections are in-memory data structures that hold elements.	Streams are pipelines of data that are processed on demand.

    Storage	Collections store elements in memory, allowing direct access to elements.	Streams do not store elements. They provide a way to process elements in a sequence.
Iteration	
    Collections support both external iteration (using loops) and internal iteration (via iterators).	
    
    Streams support internal iteration using functional programming constructs.
Mutability	
    Collections can be modified (add, remove, update elements).	
    
    Streams are immutable. They do not modify the source they operate on; instead, they produce a new stream.

Operations	
    Collections primarily support mutative operations (add, remove). They provide various methods for querying, adding, removing, and updating elements.	
    
    Streams support a variety of functional-style operations, including intermediate (e.g., filter, map, sorted) and terminal (e.g., collect, forEach, reduce) operations.
15. Implement Stream API's filter and map methods by your self.

    ```java
    import java.util.ArrayList;
    import java.util.List;
    import java.util.function.Function;
    import java.util.function.Predicate;

    public class MyStream<T> {
        private List<T> elements;

        // Constructor to initialize the stream with elements
        private MyStream(List<T> elements) {
            this.elements = elements;
        }

        // Factory method to create a MyStream from a list
        public static <T> MyStream<T> of(List<T> list) {
            return new MyStream<>(list);
        }

        // Implementation of the filter method
        public MyStream<T> filter(Predicate<T> predicate) {
            List<T> filteredElements = new ArrayList<>();
            for (T element : elements) {
                if (predicate.test(element)) {
                    filteredElements.add(element);
                }
            }
            return new MyStream<>(filteredElements);
        }

        // Implementation of the map method
        public <R> MyStream<R> map(Function<T, R> mapper) {
            List<R> mappedElements = new ArrayList<>();
            for (T element : elements) {
                mappedElements.add(mapper.apply(element));
            }
            return new MyStream<>(mappedElements);
        }

        // Terminal operation to collect the elements into a list
        public List<T> collect() {
            return elements;
        }

        public static void main(String[] args) {
            List<String> names = List.of("Alice", "Bob", "Charlie", "David");

            // Using the custom MyStream to filter and map elements
            List<String> result = MyStream.of(names)
                                        .filter(name -> name.startsWith("A"))  // Filter names starting with 'A'
                                        .map(String::toUpperCase)              // Convert to uppercase
                                        .collect();                            // Collect the results into a list

            System.out.println(result);  // Output: [ALICE]
        }
    }

    ```