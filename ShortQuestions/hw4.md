### 1. **Best Practices for NullPointerException Prevention**
   - **Use `Optional` class**: Prevents `NullPointerException` by providing methods to deal with potentially `null` values.
     ```java
     Optional<String> optional = Optional.ofNullable(getValue());
     optional.ifPresent(System.out::println);
     ```
   - **Use `Objects.requireNonNull`**: Ensures the object is non-null, throwing an exception otherwise.
     ```java
     String value = Objects.requireNonNull(inputValue, "Value cannot be null");
     ```
   - **Initialize variables properly**: Always initialize variables before use.
     ```java
     String name = "";  // Rather than leaving it uninitialized
     ```
   - **Use `null` checks**: Implement explicit `null` checks when necessary.
     
     ```java
     if (obj != null) {
         obj.doSomething();
     }
     ```

**Without Optional (custom null check):**

```
public String getStudentName() {
  // can be null or non-null value
  Student student = fetchStudent(); 
  if(student != null){
    return student.getName();
  }else {
    return null;
  }
}
```

**With Optional:**

```
public Optional<String> getStudentName() {
  // can be null or non-null value
  Optional<Student> student = Optional.ofNullable(fetchStudent()); 
  if(student.isPresent()){
    return Optional.of(student.getName());
  }
  return Optional.empty();
}
```

### 2. **Java 8 New Features with Code Snippets**

   - **Lambda Expressions**:
     ```java
     List<String> names = Arrays.asList("John", "Jane", "Doe");
     names.forEach(name -> System.out.println(name));
     ```
   - **Stream API**:
     ```java
     List<String> names = Arrays.asList("John", "Jane", "Doe");
     List<String> filtered = names.stream()
         .filter(name -> name.startsWith("J"))
         .collect(Collectors.toList());
     ```
   - **Optional Class**:
     ```java
     Optional<String> optional = Optional.ofNullable("Hello");
     optional.ifPresent(System.out::println);
     ```
   - **Default Methods in Interfaces**:
     ```java
     interface MyInterface {
         default void print() {
             System.out.println("Default method");
         }
     }
     ```

### 3. **Advantages of the `Optional` Class**
   - **Prevents `NullPointerException`** by providing methods to check and act on the presence of a value.
   - **Encourages more readable code** by avoiding explicit `null` checks.
   - **Chainable methods** like `map`, `flatMap`, and `filter` allow transformation of values if present.
   - **Clear intent**: Shows that a method might not return a value.

### 4. **Functional Interface and Lambda with Code Samples**
   - **Functional Interface**: An interface with only one abstract method.
     ```java
     @FunctionalInterface
     interface MyFunctionalInterface {
         void perform();
     }
     ```
   - **Lambda Expression**: Provides implementation for a functional interface.
     ```java
     MyFunctionalInterface func = () -> System.out.println("Performing action");
     func.perform();
     ```

### 5. **Method Reference with Code Samples**
   - **Method reference** is a shorthand for lambda expressions where the method is already defined.
     ```java
     class Printer {
         static void print(String message) {
             System.out.println(message);
         }
     }
     
     List<String> messages = Arrays.asList("Hello", "World");
     messages.forEach(Printer::print);  // Method reference
     ```

### 6. **"Lambda can use unchanged variable outside of lambda" with Code Snippet**
   - Lambda expressions can use variables from the enclosing scope, provided they are effectively final.
     ```java
     String greeting = "Hello";
     MyFunctionalInterface func = () -> System.out.println(greeting);  // `greeting` is effectively final
     func.perform();
     ```

### 7. **Can a Functional Interface Extend/Inherit Another Interface?**
   - Yes, a functional interface can extend another interface as long as it still has only one abstract method.
     ```java
     @FunctionalInterface
     interface A {
         void methodA();
     }
     
     @FunctionalInterface
     interface B extends A {
         // No new abstract method
     }
     ```

### 8. **Intermediate and Terminal Operations**
   - **Intermediate Operations**: Return a new stream and are lazy, such as `filter`, `map`, `flatMap`.
   - **Terminal Operations**: Produce a result or side effect, such as `collect`, `forEach`, `reduce`.

### 9. **Most Commonly Used Intermediate Operations in Stream API**
   - **Filter**: Filters elements based on a condition.
     ```java
     List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
     numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
     ```
   - **Map**: Transforms elements.
     ```java
     List<String> names = Arrays.asList("John", "Jane", "Doe");
     names.stream().map(String::toUpperCase).forEach(System.out::println);
     ```
   - **Distinct**: Removes duplicates.
     ```java
     List<Integer> numbers = Arrays.asList(1, 2, 2, 3);
     numbers.stream().distinct().forEach(System.out::println);
     ```

### 10. **How Collections Are Different from Stream**
   - **Collections** are in-memory data structures, while **Stream** represents a sequence of elements that can be processed lazily.
   - Streams support **parallel processing**, whereas collections do not by default.
   - Streams can only be traversed **once**, while collections allow multiple iterations.

### 11. **Implement `filter` and `map` Methods of Stream API by Yourself**
   - Here's a basic custom implementation of `filter` and `map`:
     ```java
     interface MyStream<T> {
         MyStream<T> filter(Predicate<T> predicate);
         <R> MyStream<R> map(Function<T, R> mapper);
         List<T> collect();
     }
     
     class MyStreamImpl<T> implements MyStream<T> {
         private List<T> source;
     
         MyStreamImpl(List<T> source) {
             this.source = source;
         }
     
         public MyStream<T> filter(Predicate<T> predicate) {
             List<T> result = new ArrayList<>();
             for (T element : source) {
                 if (predicate.test(element)) {
                     result.add(element);
                 }
             }
             return new MyStreamImpl<>(result);
         }
     
         public <R> MyStream<R> map(Function<T, R> mapper) {
             List<R> result = new ArrayList<>();
             for (T element : source) {
                 result.add(mapper.apply(element));
             }
             return new MyStreamImpl<>(result);
         }
     
         public List<T> collect() {
             return source;
         }
     }
     
     // Usage:
     List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
     MyStreamImpl<Integer> stream = new MyStreamImpl<>(numbers);
     List<Integer> filteredAndMapped = stream
         .filter(n -> n % 2 == 0)
         .map(n -> n * 2)
         .collect();
     
     System.out.println(filteredAndMapped);  // [4, 8]
     ```
