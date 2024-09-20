# Homework 4 Answers

### 1. Learn Java generics by reading and practicing following code:

### 2. Read the follwoing code repo and type it one by one by yourself.

### 3. Practice following stream API exercises at least 3 times 

### 4. Practice Optional methods at least 2 times

### 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.

1. Use Optional Class: Using Optional helps avoid direct null references.

    ```java
    Optional<String> optionalStr = Optional.ofNullable(someString);
    optionalStr.ifPresent(System.out::println);
    ```

2. Null Check Before Access: Always check for null before using.

    ```java
    if (someString != null) {
        //do something
    }
    ```

3. Default Values: Assign default values to avoid null usage.

    ```java
    String value = (someString != null) ? someString : "default";
    ```

4. Use Try-Catch for Fallback Handling

    ```java
    String value = getValue();
        
    try {
        System.out.println(value.length());
    } catch (NullPointerException e) {
        System.out.println("Caught NullPointerException, value is null");
    }
    ```


### 6. Discuss Java8 new features with code snippet.

1. Lambda Expressions:

    ```java
    public class LambdaExample {
        public static void main(String[] args) {
            List<String> letter = Arrays.asList("A", "C", "B");
            
            names.forEach(letter -> System.out.println(letter));
            names.sort((a, b) -> a.compareTo(b));
        }
    }
    ```

2. Functional Interfaces:

    ```java
    @FunctionalInterface
    interface MyFunctionalInterface {
        void execute();
    }

    public class FunctionalInterfaceExample {
        public static void main(String[] args) {
            MyFunctionalInterface functionalInterface = () -> System.out.println("Executing!");
            functionalInterface.execute();
        }
    }
    ```

3. Stream API:

    ```java
    public class StreamExample {
        public static void main(String[] args) {
            List<String> animal = Arrays.asList("cat", "dog", "cow");

            names.stream()
                .filter(letter -> letter.startsWith("c"))
                .forEach(System.out::println);
        }
    }
    ```

4. Optional Class

    ```java
    public class OptionalExample {
        public static void main(String[] args) {
            Optional<String> optionalValue = Optional.ofNullable(null);
            
            optionalValue.ifPresent(value -> System.out.println(value));
            
            String result = optionalValue.orElse("Default Value");
            System.out.println(result);
        }
    }
    ```

5. Method References:

    ```java
    public class MethodReferenceExample {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("John", "Jane", "Tom");
            
            names.forEach(System.out::println);
        }
    }
    ```

6. Default and Static Methods in Interfaces:

    ```java
    interface MyInterface {
        default void defaultMethod() {
            System.out.println("Default Method");
        }

        static void staticMethod() {
            System.out.println("Static Method");
        }
    }

    public class DefaultMethodExample implements MyInterface {
        public static void main(String[] args) {
            DefaultMethodExample example = new DefaultMethodExample();
            example.defaultMethod();
            
            MyInterface.staticMethod();
        }
    }
    ```

### 7. What are the advantages of the Optional class?
- Here are some key advantages of using Optional:
    1. Avoids NullPointerException
    2. Improves Code Readability
    3. Encourages Functional Programming
    4. Reduces the Risk of Bugs

### 8. Explain Functional Interface and Lambda with code samples.

Functional Interface: An interface with a single abstract method, used as a target for lambda expressions.

    ```java
    @FunctionalInterface
    interface MyFunctionalInterface {
        void execute();
    }

    public class FunctionalInterfaceExample {
        public static void main(String[] args) {
            MyFunctionalInterface functionalInterface = () -> System.out.println("Executing!");
            functionalInterface.execute();
        }
    }
    ```

Lambda Expression: A way to implement a functional interface, making the code shorter and more readable.

    ```java
    public class LambdaExample {
        public static void main(String[] args) {
            List<String> letter = Arrays.asList("A", "C", "B");
            
            names.forEach(letter -> System.out.println(letter)); //Print each letter
            names.sort((a, b) -> a.compareTo(b)); // Compare
        }
    }
    ```

### 9. Explain Method Reference with code samples?

Method References in Java provide a shorthand notation for calling a method by referring to it with the :: symbol. They are a more readable way to use lambda expressions that simply call a method.

Reference to a Static Method:

    ```java
    public class MethodReferenceExample {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("John", "Jane", "Tom");
            
            names.forEach(System.out::println); //Print the names
        }
    }
    ```

### 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

lambda expressions can access any variable from the outer scope that effectively final. The variable must not be changed after its initial assignment.

    ```java

    public class LambdaVariableExample {
        public static void main(String[] args) {
            int number = 10;
            
            Consumer<Integer> multiplier = (multiplierValue) -> {
                System.out.println("Result: " + (number * multiplierValue));
            };
            
            multiplier.accept(5);  // Output: Result: 50

            // Below is showing a error is uncommented
            // number = 20;  // Error: Variable used in lambda should be effectively final
        }
    }
    ```

### 11. Can a functional interface extend/inherit another interface?

Yes, a functional interface can extend another interface or functional interface that retains only one abstract method. It can inherit default and static methods without affecting its functional interface status.

### 12. What are Intermediate and Terminal operations?

- Intermediate Operations: Transform a stream into another stream (filter(), map(), sorted()).
- Terminal Operations: Consume the stream and produce a result (forEach(), collect(), reduce(), count()).

### 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

filter():

    ```java
    public class FilterExample {
        public static void main(String[] args) {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            // Filter even numbers
            List<Integer> evenNumbers = numbers.stream()
                                            .filter(n -> n % 2 == 0)  // Intermediate operation: filter
                                            .toList();  // Terminal operation: collect results into a list

            System.out.println(evenNumbers);  // Output: [2, 4, 6, 8, 10]
        }
    }
    ```

map():

    ```java
    public class MapExample {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("john", "jane", "jack");

            // Convert all names to uppercase
            List<String> upperCaseNames = names.stream()
                                            .map(String::toUpperCase)  // Intermediate operation: map
                                            .toList();  // Terminal operation: collect results into a list

            System.out.println(upperCaseNames);  // Output: [JOHN, JANE, JACK]
        }
    }
    ```

sorted():

    ```java
    public class SortedExample {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("Jane", "John", "Tom", "Jack");

            // Sort names alphabetically
            List<String> sortedNames = names.stream()
                                            .sorted()  // Intermediate operation: sorted (natural order)
                                            .toList();  // Terminal operation: collect results into a list

            System.out.println(sortedNames);  // Output: [Jack, Jane, John, Tom]
        }
    }
    ```

### 14. How are Collections different from Stream?

A collection is a data structure that stores elements and a stream is a sequence of elements that supports various operations to process data.
A collection is External Iteration and a stream is Internal Iteration.
A collection can be processed any number of times and a stream can only traversed once.
A collection is easy for data access and a stream has no way to access data.

### 15. Implement Stream API's filter and map methods by yourself.

    ```java

    public class myStream<T> {
        private List<T> source;

        // Constructor that takes a list
        public myStream(List<T> source) {
            this.source = source;
        }

        // Filter method
        public myStream<T> filter(Predicate<T> predicate) {
            List<T> filteredList = new ArrayList<>();
            for (T element : source) {
                if (predicate.test(element)) {
                    filteredList.add(element);
                }
            }
            return new myStream<>(filteredList);
        }

        // Map method
        public <R> myStream<R> map(Function<T, R> mapper) {
            List<R> mappedList = new ArrayList<>();
            for (T element : source) {
                mappedList.add(mapper.apply(element));
            }
            return new myStream<>(mappedList);
        }

        // Collect method to return the final list
        public List<T> collect() {
            return source;
        }

        public static void main(String[] args) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            List<Integer> result = new myStream<>(numbers)
                .filter(n -> n % 2 == 0)   // Filter even numbers
                .map(n -> n * 2)           // Map
                .collect();                // Collect the result

            // Print the result
            System.out.println(result);  // Output: [4, 8, 12, 16, 20]
        }
    }
    ```