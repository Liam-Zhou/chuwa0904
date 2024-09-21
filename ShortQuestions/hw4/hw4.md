1.  Learn Java generics by reading and practicing following code:
    <https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic>

2.  Read the follwoing code repo and type it one by one by yourself.
    <https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features>

3.  Practice following stream API exercises at least 3 times
    <https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java>

4.  Practice Optional methods at least 2 times
    <https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java>

5.  Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.

    - Use Optional to Avoid Null Checks

      ```
      import java.util.Optional;

      public class OptionalExample {
          public static void main(String[] args) {
              // Using Optional to avoid NullPointerException
              String value = "Hello World";
              Optional<String> optionalValue = Optional.ofNullable(value);

              // If present, print the value
              optionalValue.ifPresent(System.out::println);

              // Handle default value if null
              String defaultValue = optionalValue.orElse("Default Value");
              System.out.println(defaultValue);
          }
      }
      ```

    - Use Objects.requireNonNull() for Early Null Checks

      ```
      import java.util.Objects;

      public class NonNullCheck {
          public static void process(String input) {
              // Throws NullPointerException if input is null
              Objects.requireNonNull(input, "Input cannot be null");
              System.out.println(input.toUpperCase());
          }

          public static void main(String[] args) {
              process("Hello");
              // process(null); // This would throw a NullPointerException
          }
      }
      ```

    - Avoid Returning null from Methods

      ```
      import java.util.Collections;
      import java.util.List;

            public class AvoidNullReturn {
                public static List<String> getList() {
                    // Instead of returning null, return an empty list
                    return Collections.emptyList();
                }

                public static void main(String[] args) {
                    List<String> list = getList();
                    System.out.println("List size: " + list.size()); // Safe to call size
                }
            }

      ```

    - Use Collection and Stream API

      ```
      import java.util.Arrays;
      import java.util.List;

            public class StreamExample {
                public static void main(String[] args) {
                    List<String> names = Arrays.asList("John", null, "Alice", "Bob");

                    // Stream API ignores null values automatically during filter and map
                    names.stream()
                        .filter(name -> name != null)
                        .map(String::toUpperCase)
                        .forEach(System.out::println);
                }
            }

      ```

6.  Discuss Java 8 new features with code snippet.

    - Lambda Expressions

      ```
      import java.util.Arrays;
      import java.util.List;

      public class LambdaExample {
          public static void main(String[] args) {
              List<String> names = Arrays.asList("John", "Alice", "Bob");

              // Using Lambda expression for iteration
              names.forEach(name -> System.out.println(name));

              // Equivalent method reference
              names.forEach(System.out::println);
          }
      }
      ```

    - Stream API

      ```
      import java.util.Arrays;
      import java.util.List;
      import java.util.stream.Collectors;

      public class StreamExample {
          public static void main(String[] args) {
              List<String> names = Arrays.asList("John", "Alice", "Bob", "Jane");

              List<String> filteredNames = names.stream()
                                              .filter(name -> name.startsWith("J"))
                                              .collect(Collectors.toList());

              System.out.println(filteredNames); // Output: [John, Jane]
          }
      }
      ```

    - Optional Class

      ```
      import java.util.Optional;

      public class OptionalExample {
          public static void main(String[] args) {
              Optional<String> optionalName = Optional.ofNullable(getName());

              // If value is present, print it, otherwise print "Unknown"
              System.out.println(optionalName.orElse("Unknown"));

              // Using ifPresent to check if value is present
              optionalName.ifPresent(name -> System.out.println("Name is: " + name));
          }

          public static String getName() {
              return null; // or return "Alice";
          }
      }
      ```

    - New Date and Time API (java.time)

      ```
      import java.time.LocalDate;
      import java.time.LocalDateTime;
      import java.time.format.DateTimeFormatter;

      public class DateTimeExample {
          public static void main(String[] args) {
              // Get the current date
              LocalDate today = LocalDate.now();
              System.out.println("Today's Date: " + today);

              // Create a specific date
              LocalDate specificDate = LocalDate.of(2020, 1, 1);
              System.out.println("Specific Date: " + specificDate);

              // Get current time with date
              LocalDateTime currentDateTime = LocalDateTime.now();
              System.out.println("Current DateTime: " + currentDateTime);

              // Format date using DateTimeFormatter
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
              System.out.println("Formatted DateTime: " + currentDateTime.format(formatter));
          }
      }
      ```

    - Default Methods in Interfaces

      ```
      interface Vehicle {
          // Abstract method
          void start();

          // Default method
          default void stop() {
              System.out.println("Vehicle is stopping.");
          }
      }

      class Car implements Vehicle {
          @Override
          public void start() {
              System.out.println("Car is starting.");
          }
      }

      public class DefaultMethodExample {
          public static void main(String[] args) {
              Vehicle myCar = new Car();
              myCar.start(); // Output: Car is starting.
              myCar.stop();  // Output: Vehicle is stopping.
          }
      }
      ```

    - Functional Interfaces and java.util.function Package

      ```
      import java.util.function.Predicate;

      public class FunctionalInterfaceExample {
          public static void main(String[] args) {
              Predicate<Integer> isEven = number -> number % 2 == 0;

              // Using Predicate to check if a number is even
              System.out.println(isEven.test(4)); // Output: true
              System.out.println(isEven.test(5)); // Output: false
          }
      }
      ```

7.  What are the advantages of the Optional class?

- Avoids NullPointerExceptions (NPE)
  ```
  Optional<String> optionalValue = Optional.ofNullable(getValue());
  optionalValue.ifPresent(value -> System.out.println(value.toUpperCase()));
  ```
- Explicit Null Handling
  ```
  Optional<String> optionalName = Optional.ofNullable(getName());
  // Explicitly handle absent value
  String name = optionalName.orElse("Default Name");
  ```
- Provides Default Values with orElse()
  ```
  Optional<String> optionalValue = Optional.ofNullable(null);
  String value = optionalValue.orElse("Default Value");
  System.out.println(value); // Output: Default Value
  ```
- Avoids Return of null

  ```
  public Optional<String> getName() {
      return Optional.ofNullable(null); // Safe: returns Optional.empty() if null
  }

  Optional<String> name = getName();
  System.out.println(name.orElse("No Name"));
  ```

- Support for Stream Operations

  ```
  List<Optional<String>> list = Arrays.asList(
      Optional.of("Apple"), Optional.empty(), Optional.of("Banana")
  );

  List<String> result = list.stream()
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toList());

  System.out.println(result); // Output: [Apple, Banana]
  ```

8. Explain Functional Interface and Lambda with code samples.

   A functional interface is an interface that contains exactly one abstract method. Functional interfaces can have any number of default or static methods, but they must have only one abstract method.

   ```
   @FunctionalInterface
   interface MyFunctionalInterface {
       void execute(); // Only one abstract method
   }
   ```

   Lambda expressions are a concise way to express instances of functional interfaces using an anonymous function. In essence, lambda expressions provide a clear and simple way to create anonymous methods that can be passed as parameters or stored as variables.

   ```
   @FunctionalInterface
   interface Greeting {
       void sayMessage(String message);
   }

   public class LambdaExample {
       public static void main(String[] args) {
           Greeting greeting = (message) -> System.out.println("Hello, " + message);

           greeting.sayMessage("World"); // Output: Hello, World
       }
   }
   ```

9. Explain Method Reference with code samples?

   - Reference to a Static Method

   ```
   import java.util.function.Function;

   public class StaticMethodReferenceExample {
       // Static method
       public static int square(int number) {
           return number * number;
       }

       public static void main(String[] args) {
           // Lambda expression
           Function<Integer, Integer> lambdaSquare = number -> StaticMethodReferenceExample.square(number);

           // Using method reference for static method
           Function<Integer, Integer> methodRefSquare = StaticMethodReferenceExample::square;

           // Calling the function
           System.out.println(lambdaSquare.apply(5));  // Output: 25
           System.out.println(methodRefSquare.apply(6)); // Output: 36
       }
   }
   ```

   - Reference to an Instance Method of a Particular Object

   ```
   import java.util.function.Consumer;

   public class InstanceMethodReferenceExample {
       public void printMessage(String message) {
           System.out.println(message);
       }

       public static void main(String[] args) {
           InstanceMethodReferenceExample instance = new InstanceMethodReferenceExample();

           // Lambda expression
           Consumer<String> lambdaPrinter = message -> instance.printMessage(message);

           // Using method reference for instance method
           Consumer<String> methodRefPrinter = instance::printMessage;

           // Calling the method
           lambdaPrinter.accept("Hello from Lambda!"); // Output: Hello from Lambda!
           methodRefPrinter.accept("Hello from Method Reference!"); // Output: Hello from Method Reference!
       }
   }
   ```

   - Reference to an Instance Method of an Arbitrary Object of a Specific Type

   ```
   import java.util.Arrays;
   import java.util.List;

   public class ArbitraryObjectMethodReferenceExample {
       public static void main(String[] args) {
           List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

           // Lambda expression
           names.forEach(name -> System.out.println(name));

           // Method reference for instance method of an arbitrary object
           names.forEach(System.out::println);
       }
   }
   ```

   - Reference to a Constructor

   ```
   import java.util.function.Supplier;

   class User {
       private String name;

       public User() {
           this.name = "Default User";
       }

       @Override
       public String toString() {
           return name;
       }
   }

   public class ConstructorReferenceExample {
       public static void main(String[] args) {
           // Lambda expression
           Supplier<User> lambdaUserCreator = () -> new User();

           // Using constructor reference
           Supplier<User> methodRefUserCreator = User::new;

           // Creating an instance
           System.out.println(lambdaUserCreator.get());  // Output: Default User
           System.out.println(methodRefUserCreator.get()); // Output: Default User
       }
   }
   ```

10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet

    lambda expressions can use variables from the enclosing scope, provided those variables are effectively final. This means that the variable must either be declared as final, or it must be unchanged after its initial assignment

    ```
    import java.util.function.Consumer;

    public class LambdaScopeExample {
        public static void main(String[] args) {
            String greeting = "Hello";  // Effectively final

            // Lambda expression using the variable from the outer scope
            Consumer<String> lambda = (name) -> System.out.println(greeting + ", " + name);

            lambda.accept("Alice");  // Output: Hello, Alice

            // Uncommenting the line below would cause a compile-time error because 'greeting' is being changed
            // greeting = "Hi"; // Error: Variable used in lambda expression should be final or effectively final
        }
    }
    ```

11. Can a functional interface extend/inherit another interface?

    Yes:  
    if a functional interface extends another interface, it must ensure that there is only one abstract method between itself and any interfaces it inherits. If it ends up with more than one abstract method, it will no longer qualify as a functional interface.

12. What are Intermediate and Terminal operations?

- **Intermediate operations**:  
  Intermediate operations are operations that transform a stream into another stream. They are lazy, meaning they are not executed immediately

- **Terminal operations**:  
  Terminal operations are operations that actually consume the stream and produce a result, such as a collection, a primitive value, or a side effect (e.g., printing to the console). Once a terminal operation is executed, the stream is considered consumed and cannot be used again.

13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet

    - filter(Predicate)

      ```
      import java.util.Arrays;
      import java.util.List;
      import java.util.stream.Collectors;

      public class FilterExample {
          public static void main(String[] args) {
              List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

              // Filter even numbers
              List<Integer> evenNumbers = numbers.stream()
                                              .filter(n -> n % 2 == 0)
                                              .collect(Collectors.toList());

              System.out.println("Even Numbers: " + evenNumbers); // Output: [2, 4, 6, 8, 10]
          }
      }
      ```

    - map(Function)

      ```
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

              System.out.println("Uppercase Names: " + upperCaseNames); // Output: [ALICE, BOB, CHARLIE]
          }
      }
      ```

    - flatMap(Function)

      ```
      import java.util.Arrays;
      import java.util.List;
      import java.util.stream.Collectors;

      public class FlatMapExample {
          public static void main(String[] args) {
              List<List<String>> nestedList = Arrays.asList(
                  Arrays.asList("Alice", "Bob"),
                  Arrays.asList("Charlie", "David"),
                  Arrays.asList("Eve", "Frank")
              );

              // Flatten nested list into a single stream of strings
              List<String> flatList = nestedList.stream()
                                              .flatMap(List::stream)
                                              .collect(Collectors.toList());

              System.out.println("Flattened List: " + flatList); // Output: [Alice, Bob, Charlie, David, Eve, Frank]
          }
      }
      ```

    - distinct()

      ```
      import java.util.Arrays;
      import java.util.List;
      import java.util.stream.Collectors;

      public class DistinctExample {
          public static void main(String[] args) {
              List<String> names = Arrays.asList("Alice", "Bob", "Alice", "Charlie", "Bob");

              // Remove duplicates
              List<String> distinctNames = names.stream()
                                              .distinct()
                                              .collect(Collectors.toList());

              System.out.println("Distinct Names: " + distinctNames); // Output: [Alice, Bob, Charlie]
          }
      }
      ```

    - sorted()

      ```
      import java.util.Arrays;
      import java.util.List;
      import java.util.stream.Collectors;

      public class SortedExample {
          public static void main(String[] args) {
              List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 9, 2);

              // Sort the numbers in natural order
              List<Integer> sortedNumbers = numbers.stream()
                                                  .sorted()
                                                  .collect(Collectors.toList());

              System.out.println("Sorted Numbers: " + sortedNumbers); // Output: [1, 2, 3, 5, 8, 9]
          }
      }
      ```

14. How are Collections different from Stream?

    | Feature                       | **Collections**                                                                                        | **Streams**                                                                                                               |
    | ----------------------------- | ------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------- |
    | **Nature**                    | A **data structure** that stores elements.                                                             | A **data pipeline** that processes elements from a source.                                                                |
    | **Element Storage**           | Stores all elements in memory at once.                                                                 | Does not store elements; instead, computes them on demand (lazy evaluation).                                              |
    | **Modification**              | Elements in a collection can be added, removed, or modified.                                           | Stream elements cannot be modified. Streams are read-only.                                                                |
    | **Iterating**                 | Supports both internal (`forEach`) and external (`for-each` or iterator) iteration.                    | Only supports **internal iteration** (handled by the Stream API).                                                         |
    | **Usage**                     | Used for storing, managing, and manipulating elements in a data structure (like `List`, `Set`, `Map`). | Used for **functional processing** (e.g., filtering, mapping, reducing) of a stream of data.                              |
    | **Reusability**               | Collections can be **reused** after operations. You can iterate over them multiple times.              | Streams are **single-use**: once a terminal operation is invoked, the stream is consumed and cannot be reused.            |
    | **Eager vs. Lazy Evaluation** | Collections operate in an **eager** manner (operations are performed immediately).                     | Streams operate in a **lazy** manner (operations are only executed when a terminal operation is called).                  |
    | **Parallelism**               | Collections do not natively support parallelism (though you can manually implement it).                | Streams support **parallel processing** via the `parallelStream()` method, making it easy to perform parallel operations. |
    | **Type of Operations**        | Used primarily for **storing** and managing data (CRUD operations).                                    | Primarily used for **processing** data (filtering, mapping, reducing, etc.) in a declarative way.                         |
    | **In-memory Size**            | The entire collection must fit in memory.                                                              | Streams can process **infinite** or large datasets because they do not store data, but compute it on demand.              |
    | **Modification of Source**    | A collection can be modified (mutated).                                                                | Streams cannot modify the source of the data; they are intended for **read-only** operations.                             |
    | **Statefulness**              | Collections maintain a specific state in memory.                                                       | Streams are **stateless** (they do not maintain state, except for certain operations like `sorted()`).                    |

15. Implement Stream API's filter and map methods by your self.

    ```
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    import java.util.function.Function;
    import java.util.function.Predicate;

    class CustomStream<T> {
        private List<T> source;

        public CustomStream(List<T> source) {
            this.source = source;
        }

        // Implementing the filter method
        public CustomStream<T> filter(Predicate<? super T> predicate) {
            List<T> result = new ArrayList<>();
            for (T element : source) {
                if (predicate.test(element)) {
                    result.add(element);
                }
            }
            return new CustomStream<>(result);
        }

        // Implementing the map method
        public <R> CustomStream<R> map(Function<? super T, ? extends R> mapper) {
            List<R> result = new ArrayList<>();
            for (T element : source) {
                result.add(mapper.apply(element));
            }
            return new CustomStream<>(result);
        }

        // Terminal operation to collect the results into a List
        public List<T> collect() {
            return source;
        }
    }

    public class CustomStreamExample {
        public static void main(String[] args) {
            List<String> names = List.of("Alice", "Bob", "Charlie", "David");

            // Creating a custom stream
            CustomStream<String> customStream = new CustomStream<>(names);

            // Applying filter and map operations
            List<String> result = customStream
                    .filter(name -> name.length() > 3)     // Filter names with length greater than 3
                    .map(String::toUpperCase)              // Convert to uppercase
                    .collect();                            // Collect the result

            // Output the result
            System.out.println(result);  // Output: [ALICE, CHARLIE, DAVID]
        }
    }
    ```
