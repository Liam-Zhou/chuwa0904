# hw4 Java 8 new feature

## 1. Learn Java generics by reading and practicing following code

Generic: Provide a parameter that allow a specific kind of data type.

Generic class: 

```Java
// Format
public class GenericClass<T> {
    // T is the generic, can be all kinds of data type
    T obj;

    public GenericClass(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }
}
```

Generic method:

```Java
// Format
public static < E > void printArray( E[] inputArray );
                |
                |
    // Useing <> to declare a generic
    // Multi-arguments are allowd, eg, <E, T, K, V, N>
```

## 2. Read the follwoing code repo and type it one by one by yourself

## 3. Practice following streamAPI exercises at least 3 times

## 4. Practice Optional methods at least 2 times

## 5. Discuss best practices on nullptrexception prevention, provide code snippet for each practice that you mentioned.

1. `Optional<T> class` (is designed to represent values that may or may not be present, reducing the need for null checks.)

```Java
Optional<String> optionalString = Optional.ofNullable(getString());
optionalString.ifPresent(s -> System.out.println(s));
```

2. Use try-catch Sparingly

```Java
try {
    myObject.someMethod();
} catch (NullPointerException e) {
    // Handle the exception
}
```

3. Use Objects.requireNonNull

```Java
public void process(String input) {
    Objects.requireNonNull(input, "Input string must not be null");
    // Proceed with logic
}
```

## 6. Discuss Java 8 new features with code snippet.

1. Lambda expression

```Java
(parameters) ->{ statements; }
```

2. Functional interface

Functional interface only has one abstract method.

```Java
@FunctionalInterface
interface Say 
{
    void saySomething(String s);
}

String s = "world";
Say say1 = s -> System.out.println("Hello " + s);
```

3. Default method

Allow implemented method in interface, the access modifier of this implemented method is `default`.

4. StreamAPI

StreamAPI makes data processing convenient.

- forEach()

```Java
Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
```

- map()

```Java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
// 获取对应的平方数
List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
// 9, 4, 49, 25
```

- filter()

```Java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
// 获取空字符串的数量
long count = strings.stream().filter(string -> string.isEmpty()).count();
```

5. Optional class

Designed to represent values that may or may not be present, reducing the need for null checks.

## 7. What are the advantages of the Optional class?

1. Avoids NullPointerExceptions
2. Explicit Handling of Null Values
3. Encourages Functional Programming
4. Clear Intent of "No Value"
5. Better Integration with Stream API
6. Null-Safe Method Chaining
7. Ensures Better API Design

## 8. Explain Functional Interface and Lambda with code samples.

Check question 6

## 9. Explain Method Reference with code samples?

Using `::` to refer to methods. (`ClassName::instanceMethod`)

1. Static Method Reference (`ClassName::staticMethod`)
2. Instance Method Reference on a Particular Object (`instance::instanceMethod`)
3. Instance Method Reference of an Arbitrary Object of a Class (`ClassName::instanceMethod`)
4. Constructor Reference (`ClassName::new`)

```Java
class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
```

```Java
// Constructor Reference
final Car car = Car.create( Car::new );
// Static method reference
cars.forEach( Car::collide );
// method reference
cars.forEach( Car::repair );
// An instance refer to a method
final Car police = Car.create( Car::new );
cars.forEach( police::follow );
```

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

In Java, lambda expression can access variables defined outside their body, but those variables must be effectively final (**final or effectively final**). 

```Java
public static void main(String[] args) {
    // Variable outside the lambda
    int multiplier = 2;
    // Or final int multiplier = 2;
    // List of numbers
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    // Using the unchanged variable inside a lambda
    numbers.forEach(number -> {
        int result = number * multiplier;  // Allowed, because multiplier is effectively final
        System.out.println(result);
    });
    // ERROR!
    // multiplier = 3;
    // java: local variables referenced from a lambda expression must be final or effectively final
}
```

## 11. Can a functional interface extend/inherit another interface?

Yes. Functional interface is just a special interface with only one abstract method, it can extend all interface, and if there is still only one abstract method from it to its parent interfaces, it is still a functional interface, vice versa.

## 12. What are Intermediate and Terminal operations?

1. Intermediate operations transform a stream into another stream. They are not executed until a terminal operation is invoked. `map(), filter(), distinct(), sorted()`
2. Terminal operations are the end operations that produce a result or a side-effect (such as printing, collecting, or counting). These operations trigger the processing of the stream pipeline and actually execute the intermediate operations. `forEach(), reduce(), count(), collect()`

## 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

Use `sorted(), filter(), map(), distinct()` together.
```Java
List<Integer> numbers = Arrays.asList(0, 3, 2, 2, 3, 7, 3, 5);
List<Integer> squaresList = numbers.stream().sorted().filter(integer -> integer != 0).map( i -> i*i).distinct().collect(Collectors.toList());
System.out.println(squaresList);
// 4, 9, 25, 49
```

## 14. How are Collections different from Stream?

Collections and Streams are two different concepts used for handling and manipulating data, but they serve different purposes and have distinct characteristics.

1. Collection is an in-memory data structure, but stream is a pipeline of data that can be generated from a collection or other data sources like arrays, files, or I/O channels, streams are not data structures.
2. Collection stores date, so we can access data in collection multiple times, but stream does not store data, the data in stream can not be reused.
3. Collections are generally mutable, you can add, remove, or modify elements after the collection is created (though some collections like Collections.unmodifiableList() are immutable). But streams are immutable. They do not modify the underlying source, and operations on streams result in a new stream without changing the original data.
4. Collections can contain null elements. But Streams generally discourage the use of null. The use of Optional is preferred in streams to avoid NullPointerException. Passing null values into stream methods may cause exceptions.

## 15. Implement Stream API's filter and map methods by yourself.

```Java
package com.hw4.java8newfeature;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MyStream<T> {
    private List<T> elements;

    public MyStream(List<T> elements) {
        this.elements = elements;
    }

    // Implement the filter method
    public MyStream<T> filter(Predicate<? super T> predicate) {
        List<T> filteredElements = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredElements.add(element);
            }
        }
        return new MyStream<>(filteredElements); // Return new stream with filtered elements
    }

    // Implement the map method
    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedElements = new ArrayList<>();
        for (T element : elements) {
            mappedElements.add(mapper.apply(element));
        }
        return new MyStream<>(mappedElements); // Return new stream with mapped elements
    }

    // Method to collect and print elements
    public void forEach(java.util.function.Consumer<? super T> action) {
        for (T element : elements) {
            action.accept(element);
        }
    }
}

public class CustomStreamExample {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Create a custom stream from the list of numbers
        MyStream<Integer> stream = new MyStream<>(numbers);

        // Filter and map using custom stream implementation
        stream.filter(n -> n % 2 == 0)               // Filter even numbers
                .map(n -> n * n)                       // Square the numbers
                .forEach(System.out::println);         // Print the squared numbers
    }
}
```




