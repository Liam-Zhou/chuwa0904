### discuss best pratice on nullptr exception prevention, provide code example for each pratice

- null check

```
public class test {
    public void checkNull(String data){
        if(data != null){
            System.out.println(data.toUpperCase());
        }else{
            System.out.println("data is null");
        }
    }
}
```

- optional class
  - Optional.of(value): Creates an Optional with the given non-null value
  - Optional.empty(): Creates an empty Optional for when there's no value.
  - Optional.ofNullable(value): Creates an Optional that may contain a value or be empty, depending on whether value is null.

```
public Optional<String> getData(boolean data2){
        if(data2){
            return Optional.of("valid data"); // non-null
        }else{
            return Optional.empty(); // empty optional
        }
    }

```

```
Optional<String> optional2 = Optional.ofNullable(null);  
```

- Use Objects.requireNonNull()

The Objects.requireNonNull() method checks if an object is null and throws a customized NullPointerException

```
public void processData(String data){
        Objects.requireNonNull(data,"Data cannot be null");
    }

    public static void main(String[] args) {
        new Test().processData(null);
    }
```

### Discuss java 8 new feature with code example

- Lambda Expressions

concise way to represent functions.

```
Runnable runnable1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello, World!");
    }
};

// Using lambda expression
//() parameter
//-> method body
Runnable runnable2 = () -> System.out.println("Hello, World!");


```


- Functional Interfaces

A functional interface is an interface that contains exactly one abstract method. **You can use lambda expressions to provide implementations of these interfaces.**

1. Consumer<T>
Consumer represents an operation that accepts a single input of type T and returns no result. It’s typically used for performing actions (like printing, logging, etc.) on data.

Abstract Method: void accept(T t)
```
public static void main(String[] args) {

        Consumer<String> con = s -> System.out.println("Displaying "+s);
        con.accept("mesg 1");
    }
```
2. Supplier<T>
Supplier does not take any input but supplies (produces) a result of type T. It’s often used for lazy initialization or deferred execution.

Abstract Method: T get()

```
 public static void main(String[] args) {

        Supplier<String> sup = () -> "msg from supplier";
        System.out.println(sup.get());
    }
```
3. Predicate<T>
Predicate is used to test a condition on an argument of type T and returns a boolean (true or false). It is commonly used for filtering data.

Abstract Method: boolean test(T t)

```
public static void main(String[] args) {

        // Predicate to check if a number is greater than 10
        Predicate<Integer> isGreaterThanTen = number -> number > 10;

        // Applying the predicate
        System.out.println(isGreaterThanTen.test(15));  // true
        System.out.println(isGreaterThanTen.test(5));   // false
    }
```


4. Function<T, R> 
but it takes two arguments of types T and U and returns a result of type R.

Abstract Method: R apply(T t, U u)
```
BiFunction<Integer,Integer,Integer> bi = (a,b) -> a+b;
        
        System.out.println("The sum of 1 and 2 is "+ bi.apply(1, 2));
```


- Streams API

The Streams API allows you to work with sequences of data (like collections) in a functional way. You can filter, map, and reduce collections with fewer lines of code.
**does not store data like collection**

When you use .filter(num -> num > 5), the filter() method requires a Predicate<Integer>, and ___the lambda expression is treated as an implementation of that functional interface.___

1. filter() uses the Predicate<T> functional interface
2. map() uses the Function<T, R> functional interface
3. reduce() uses the BinaryOperator<T> or BiFunction<T, U, R> functional interface 

```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filtering numbers greater than 5 and printing them
        numbers.stream()
               .filter(num -> num > 5)
               .forEach(System.out::println);
    }
```




- Method References

Method references provide a way to refer to methods directly without calling them. They are often used ***in place of lambda expressions for cleaner code.***

shorthand for calling methods in lambdas

Syntax of Method References
```
ClassName::methodName
instance::instanceMethod
ClassName::instanceMethod
ClassName::new // refer to a constructor


```

```
public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack");

        // Using a method reference to print each name
        names.forEach(System.out::println);
    }

```


- Default and Static Methods in Interfaces

- Optional Class

avoids nullptr exception

### What are the advantage of the optional class

- Avoid NullPointerExceptions
```
Optional<String> name = Optional.ofNullable(null);
name.ifPresent(System.out::println); 
```
- Explicitly Represents Missing Values
  - Instead of using null, Optional clearly shows that a value might be missing. This makes your code more expressive and clear.
- Cleaner Code: You no longer need multiple null checks.

### Explain functional interface and Lambda expression with code

- A functional interface in Java is an interface that contains exactly one abstract method. It can have default or static methods, but it must have only one abstract method. 

```
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}
```
- Lambda expressions are mainly used to implement functional interfaces.

```
(parameters) -> expression or { statements }
```

```
// Lambda expression that implements the Greeting interface
Greeting greeting = (name) -> System.out.println("Hello, " + name);

// Using the lambda
greeting.sayHello("Fiona");  // Output: Hello, Fiona
```

### explain method reference with code
- Method references in Java 8 are a shorthand way of using lambda expressions to call a method directly. 

1. ClassName::staticMethod
```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using method reference for a static method
        numbers.stream()
               .map(String::valueOf)  // Refers to static method String.valueOf(int)
               .forEach(System.out::println);  // Prints each number as a string
    }
```

2. instance::instanceMethod
```
public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Creating a specific instance of Printer
        Printer printer = new Printer();

        // Using method reference to an instance method of a specific object
        names.forEach(printer::printName);
    }
```

3.ClassName::instanceMethod

```
public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using method reference to an instance method of an arbitrary object
        names.stream()
             .map(String::toUpperCase)  // Refers to String.toUpperCase()
             .forEach(System.out::println);  // Prints names in uppercase
    }
```

4.ClassName::new

```
public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using method reference to constructor (Person::new)
        List<Person> people = names.stream()
                                   .map(Person::new)  // Refers to the Person constructor
                                   .toList();

        people.forEach(person -> System.out.println(person.getName()));
    }
```

### explain Lambda can use unchanged variable outside of lambda with code

1. unchanged meaning final 
2. lambda can use variable from outside meaning capturing the variable.

  -  why capture:
        - When you use a variable in a lambda, Java captures its value. This is because lambdas can be executed later or in a different thread, so Java needs to ensure the value of the variable remains stable. 
3. Read only within lambda 
    - why restriction:
        - The reason for this restriction is thread safety and consistency.

```
public static void main(String[] args) {
        final int number = 5;  // Explicitly marked as final

        // Lambda expression using the final variable
        Runnable runnable = () -> System.out.println("Number: " + number);

        runnable.run();  // Output: Number: 5
    }
```


### can a functional interface extend/innherit another interface

-  Yes, a functional interface in Java can extend (inherit) another interface, including another functional interface,but it must still have only one abstract method to qualify as a functional interface.


### what are intermidiate and terminal operation
- key features: allowd method chaining, (lazy) process til terminal operation called,return stream

- common intermediate operations: 
    - filter(): Filters elements based on a predicate.
    - map(): Transforms elements of a stream.
    - sorted(): Sorts elements of the stream.
    - distinct(): Removes duplicates.
    -limit(): Limits the number of elements in the stream.
    - skip(): Skips the first N elements.

- key feature: do not return stream, trigger process

 - common terminal operations:
    - forEach(): Iterates over elements and performs an action.
    - collect(): Gathers the stream elements into a collection (like List, Set, etc.).
    - reduce(): Reduces the elements of the stream to a single value.
    - count(): Returns the count of elements in the stream.
    - anyMatch(), allMatch(), noneMatch(): Return a boolean based on a condition.


### determine the most common used intermidate operation in stream api with code
- filter()
```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter numbers greater than 5
        numbers.stream()
               .filter(n -> n > 5)
               .forEach(System.out::println);  // Output: 6, 7, 8, 9, 10
    }
```

- map()
```
public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Convert all names to uppercase
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);  // Output: ALICE, BOB, CHARLIE
    }
```

- sorted()

```
public static void main(String[] args) {
        List<Double> salary = Arrays.asList(10,100,1000);

        // Sort names alphabetically
        names.stream()
             .sorted()
             .forEach(System.out::println);  // Output: 10,100,1000
    }
```

- distinct()
```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

        // Remove duplicate numbers
        numbers.stream()
               .distinct()
               .forEach(System.out::println);  // Output: 1, 2, 3, 4, 5
    }
```

- limit()
```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Limit to the first 5 elements
        numbers.stream()
               .limit(5)
               .forEach(System.out::println);  // Output: 1, 2, 3, 4, 5
    }
```

- skip()
```
public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Skip the first 5 elements
        numbers.stream()
               .skip(5)
               .forEach(System.out::println);  // Output: 6, 7, 8, 9, 10
    }
```







### How are collection diff from Stream
- collection(List, Set, Map): storage of data
- stream: compution of data, do not store or return data, used for process data

### Implement stream api 's filter and map method
code in folderQuestion20

```
package Coding.Question20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MyStream<T>{
    private List<T> lists;
    //constructor
    public MyStream(List<T> lists){
        this.lists = lists;
    }

    //filter method for mystream class
    public MyStream<T> filter(Predicate<T> pre){
        List<T> filterList = new ArrayList<>();
        for(T item:lists){
            if(pre.test(item)){
                filterList.add(item);
            }
        }
        return new MyStream<>(filterList);
    }

    //map method for mystream class
    //R: generoc type declaration 
    //MyStream<R>: return type for method
    public <R> MyStream<R> map(Function<T,R> mp){
        List<R> mapList = new ArrayList<>();
        for(T item : lists){
            mapList.add(mp.apply(item));
        }
        return new MyStream<>(mapList);
    }

    //terminal method
    public List<T> collList(){
        return lists;
    }



}

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> numbList = List.of(1,2,3,4,5);
        //stream api process
        List<Integer> result = new MyStream<>(numbList)//stream object
                            .filter(n->n>2)
                            .map(n->n*n)
                            .collList();

        //print out
        System.out.println(result);
    }
}
```
















