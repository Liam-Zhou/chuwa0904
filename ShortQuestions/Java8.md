# Java 8
## 1. Lean Java generics
[Java generics](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic)
## 2. Read the following code repo and type it one by one
[Repo](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features)
## 3. Practice following stream API exercises at least 3 times
[Stream API exercise](https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java)
## 4. Practice Optional methods at least 2times
[Optional methods](https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java)
## 5. Best Practices for Preventing NullPointerException in Java
### 1. Use Optional for return types
```java
public Optional<String> findNameById(int id) {
    return Optional.ofNullable(database.getNameById(id));
}

String name = findNameById(1).orElse("Default Name");
```

### 2. Use Objects.requireNonNull() for Parameter Validation
```java
public void processData(String data) {
    this.data = Objects.requireNonNull(data, "Data must not be null");
}

```
### 3. Use @NonNull Annotations
```java
public void setName(@NonNull String name) {
    this.name = name;
}

```

### 4. Avoid Returning null; Return Empty Collections Instead
```java
public List<String> getItems() {
    return items != null ? items : Collections.emptyList();
}

```

## 6. Discuss Java 8 new features with code snippet.
### 1. Lambda Expressions
```java
// Normal way
List<String> names = Arrays.asList("John", "Jane", "Doe");
for (String name : names) {
        System.out.println(name);
}

// Lambda expression
        names.forEach(name -> System.out.println(name));

```

### 2. Functional Interfaces
```java
@FunctionalInterface
public interface MyFunction {
    void apply(String s);
}
// Use
MyFunction print = s -> System.out.println(s);
print.apply("Hello, World!");

```

### 3. Streams API
```java
List<String> names = Arrays.asList("A", "B", "C", "AA");

// Filter and collect
List<String> filtered = names.stream()
                             .filter(name -> name.startsWith("A"))
                             .collect(Collectors.toList());

```

### 4. Optional
```java
public Optional<String> findNameById(int id) {
    return Optional.ofNullable(database.getNameById(id));
}

// Use
String name = findNameById(1).orElse("Default Name");

```

### 5. Default Methods in Interfaces
```java
public interface MyInterface {
    default void defaultMethod() {
        System.out.println("Default method");
    }
}

// Implementation
public class MyClass implements MyInterface {}

```

### 6. Method References
```java
List<String> names = Arrays.asList("A", "B", "C", "AA");

// Method reference
names.forEach(System.out::println);

```

## 7. What are the advantages of the Optional class?
### 1. Prevents NullPointerException
Helps avoid null references and associated runtime exceptions.

### 2. Improves Code Readability
Makes it clear that a value may be absent, improving code intention.

### 3. Encourages Functional Programming
Provides methods like map, flatMap, and ifPresent to work with values in a functional style.

### 4. Supports Safe Default Values
Optional provides methods such as orElse, orElseGet, and orElseThrow to handle cases where the value might be absent in a safe manner, reducing the risk of runtime exceptions.
### 5. Facilitates Better API Design
Enforces explicit handling of optional values, making API contracts clearer.

## 8. Explain Functional Interface and Lambda with code samples
### 1. Functional Interface
A functional interface is an interface with a single abstract method. It can have multiple default or static methods but only one abstract method.
```java
@FunctionalInterface
public interface MyFunction {
    void apply(String s);
}
```
### 2. Lambda Expressions
Lambda expressions provide a concise way to implement functional interfaces. They express instances of single-method interfaces (functional interfaces) in a clear and concise manner.
```java
// Implementing MyFunction with a lambda expression
MyFunction print = s -> System.out.println(s);

// Using the lambda
print.apply("Hello, Lambda!");
```

## 9. Explain Method Reference with code samples?
Method references provide a shorthand syntax for calling methods directly, often used with functional interfaces.
There are different types of Method Reference.
### 1. Static Method Reference 
`ClassName::staticMethod`
References a static method of a class.
```java
// Static method
public class Utils {
    public static void print(String s) {
        System.out.println(s);
    }
}

// Method reference
List<String> names = Arrays.asList("John", "Jane", "Doe");
names.forEach(Utils::print);

```
### 2. Instance Method of a Specific Object
`instance::instanceMethod`
References an instance method of a particular object.
```java
public class Printer {
    public void print(String s) {
        System.out.println(s);
    }
}

// Instance method reference
Printer printer = new Printer();
List<String> names = Arrays.asList("John", "Jane", "Doe");
names.forEach(printer::print);
```
### 3. Instance Method of an Arbitrary Object 
`ClassName::instanceMethod`
References an instance method of an object that is provided by the stream.
```java
List<String> names = Arrays.asList("A", "B", "C", "AA");
names.sort(String::compareToIgnoreCase);

```
### 4. Constructor Reference 
`ClassName::new`
References a constructor to create new instances.
```java
Supplier<List<String>> listSupplier = ArrayList::new;
List<String> names = listSupplier.get();
```

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
Lambda expressions can access variables from their enclosing scope, but these variables must be effectively final. This means the variable’s value cannot be changed after it is initialized.
```java
public class LambdaExample {
    public static void main(String[] args) {
        final int multiplier = 2;  // final variable

        // Lambda expression using the external variable
        Function<Integer, Integer> multiply = x -> x * multiplier;

        // Using the lambda expression
        System.out.println(multiply.apply(5));  // Output: 10
    }
}

```

## 11. Can a functional interface extend/inherit another interface?
Yes, a functional interface can extend another interface, but the extended interface must not introduce more abstract methods. The resulting interface must still have exactly one abstract method.
```java
@FunctionalInterface
interface Base {
    void baseMethod();
}

@FunctionalInterface
interface Extended extends Base {
    @Override
    void baseMethod();  // Must be the only abstract method
}
```

## 12. What are Intermediate and Terminal operations?
### 1. Intermediate Operations
Operations that transform a stream into another stream and are lazy, they are not executed until a terminal operation is invoked.
Examples: filter, map, sorted, distinct
```java
Stream<String> stream = Stream.of("aa", "bb", "cc");
Stream<String> filtered = stream.filter(s -> s.startsWith("b"));  
```
### 2. Terminal Operations
Operations that produce a result or a side-effect and trigger the processing of the stream pipeline.
Examples: collect, forEach, reduce, count
```java
Stream<String> stream = Stream.of("aa", "bb", "cc");
long count = stream.count();  // Terminal operation

```

## 13. Demonstrate the most commonly used Intermediate operations in Stream API, with code snippet.
### 1. filter
Filters elements based on a predicate.
```java
Stream<String> stream = Stream.of("aa", "bb", "cc");
Stream<String> filtered = stream.filter(s -> s.startsWith("b"));
// Result: "bb"
```

### 2. map
Transforms each element using a function.
```java
Stream<String> stream = Stream.of("a", "bb", "ccc");
Stream<Integer> lengths = stream.map(String::length);
// Result: 1, 2, 3
```

### 3. sorted
Sorts the elements of the stream.
```java
Stream<String> stream = Stream.of("a", "c", "b");
Stream<String> sorted = stream.sorted();
// Result: "a", "b", "c"
```

### 4. distinct
Removes duplicate elements.
```java
Stream<String> stream = Stream.of("a", "b", "a", "c");
Stream<String> distinct = stream.distinct();
// Result: "a", "b", "c"
```

### 5. limit
Limits the number of elements.
```java
Stream<String> stream = Stream.of("a", "b", "c", "d");
Stream<String> limited = stream.limit(3);
// Result: "a", "b", "c"
```

### 6. skip
Skips the first n elements.
```java
Stream<String> stream = Stream.of("a", "b", "c", "d");
Stream<String> skipped = stream.skip(2);
// Result: "c", "d"
```

## 14. How are Collections different from Stream?
### 1. **Purpose**
- **Collections**: Store and manage data in-memory.
- **Streams**: Process data from collections or other sources in a functional style.

### 2. **Mutability**
- **Collections**: Can be modified, such as adding or removing elements.
- **Streams**: Immutable, they do not modify the underlying data source.

### 3. **Execution**
- **Collections**: Eagerly evaluated, operations are performed immediately.
- **Streams**: Lazily evaluated, operations are only performed when a terminal operation is invoked.

### 4. **State**
- **Collections**: Maintain the state of the data.
- **Streams**: Do not maintain state. They represent a sequence of elements computed on-demand.

### 5. **Usage**
- **Collections**: Best for storing and managing data.
- **Streams**: Best for processing sequences of data with operations like filtering, mapping, and reducing.


## 15. Implement Stream API's filter and map methods by your self.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CustomStream<T> {
    private final List<T> data;

    public CustomStream(List<T> data) {
        this.data = data;
    }

    // Custom filter method
    public CustomStream<T> filter(Predicate<T> predicate) {
        List<T> filteredData = new ArrayList<>();
        for (T item : data) {
            if (predicate.test(item)) {
                filteredData.add(item);
            }
        }
        return new CustomStream<>(filteredData);
    }

    // Custom map method
    public <R> CustomStream<R> map(Function<T, R> mapper) {
        List<R> mappedData = new ArrayList<>();
        for (T item : data) {
            mappedData.add(mapper.apply(item));
        }
        return new CustomStream<>(mappedData);
    }

    // Terminal method to get results
    public List<T> toList() {
        return data;
    }

    public static void main(String[] args) {
        List<String> names = List.of("aa", "bb", "cc");

        // Using custom stream
        CustomStream<String> customStream = new CustomStream<>(names);

        List<String> result = customStream
            .filter(name -> name.startsWith("b"))      
            .map(String::toUpperCase)                 
            .toList();                                 

        System.out.println(result);  // Output: [BB]
    }
}
```
