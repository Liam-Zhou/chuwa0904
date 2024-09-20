## 1. Learn Java generics by reading and practicing following code
Coding/hw4/generic
## 2. Read the follwoing code repo and type it one by one by yourself.

## 3. Practice following stream API exercises at least 3 times

## 4. Practice Optional methods at least 2 times

## 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
```
String str = null;
int length = str.length();
Optional<String> optionalString = Optional.ofNullable(getString());
optionalString.ifPresent(s -> System.out.println(s.length()));

```

## 6.Discuss Java 8 new features with code snippet.
- **Lambda Expressions**:
```
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};
Runnable r2 = () -> System.out.println("Hello World");
```
- **Functional Interfaces**:
```
interface MyFunctionalInterface {
    void execute();
}

MyFunctionalInterface func = () -> System.out.println("Hello World");
func.execute();
```
- **Streams API**:
```
List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");
List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("J"))
    .collect(Collectors.toList());

System.out.println(filteredNames);
```
- **Optional Class**:
```
Optional<String> name = Optional.ofNullable("John");
name.ifPresent(n -> System.out.println(n));

Optional<String> emptyName = Optional.ofNullable(null);
System.out.println(emptyName.orElse("Default Name"));
```
- **Method References**:
```
List<String> names = Arrays.asList("John", "Jane", "Adam");
names.forEach(System.out::println);
```

## 7. What are the advantages of the Optional class?
- **Avoiding NullPointerException**:
- **Encourages Declarative Code**:
- **Avoiding Explicit Null Checks**:
- **Providing Default Values**:


## 8.Explain Functional Interface and Lambda with code samples
```
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();
}
(parameters) -> expression
```

## 9.Explain Method Reference with code samples?
```
Consumer<String> consumer = System.out::println;

Printer printer = new Printer();
Consumer<String> consumer = printer::print;

names.forEach(System.out::println);

Supplier<Person> supplier = Person::new;
```

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
```
public void testFinal() {
    String localVariable = "Local";
    Foo foo = parameter -> {
        return parameter + " " + localVariable;
    };
    System.out.println(foo.aMethod("hello"));
}
```
## 11. Can a functional interface extend/inherit another interface
```
@FunctionalInterface
interface ParentInterface {
    void parentMethod();
    
    default void defaultMethod() {
        System.out.println("Default method in parent interface");
    }
}

@FunctionalInterface
interface ChildInterface extends ParentInterface {
}
public class Test {
    public static void main(String[] args) {
        ChildInterface child = () -> System.out.println("Lambda for parentMethod");
        child.parentMethod(); 
        child.defaultMethod();
    }
}

```


## 12. What are Intermediate and Terminal operations?
**Examples of Intermediate Operations:**
- `filter()`
- `map()`
- `sorted()`
- `distinct()`
- `limit()`

**Examples of Terminal Operations:**

- `collect()`
- `forEach()`
- `reduce()`
- `count()`
- `findFirst()`


## 13.Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
```

List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");

List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("J"))
    .collect(Collectors.toList());

System.out.println(filteredNames);

List<String> names = Arrays.asList("John", "Jane", "Adam", "Eve");
```
```
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

System.out.println(nameLengths);
```
```
List<Integer> numbers = Arrays.asList(5, 2, 8, 1);

List<Integer> sortedNumbers = numbers.stream()
    .sorted()
    .collect(Collectors.toList());

System.out.println(sortedNumbers);
```
```
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);

List<Integer> distinctNumbers = numbers.stream()
    .distinct()
    .collect(Collectors.toList());

System.out.println(distinctNumbers);
```
```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

List<Integer> limitedNumbers = numbers.stream()
    .limit(3)
    .collect(Collectors.toList());

System.out.println(limitedNumbers);
```
```
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

List<Integer> skippedNumbers = numbers.stream()
    .skip(2)
    .collect(Collectors.toList());

System.out.println(skippedNumbers);

```

## 14.How are Collections different from Stream?
### Summary of Differences:

| Aspect                  | Collections                        | Streams                          |
|-------------------------|-------------------------------------|----------------------------------|
| **Storage**              | Stores data                        | Computes data                   |
| **Eager/Lazy**           | Eager                              | Lazy                            |
| **Finite/Infinite**      | Finite                             | Can be infinite                  |
| **Modifiable**           | Yes                                | No                              |
| **Iteration**            | External      | Internal   |
| **Operations**           | Adding, removing, updating elements | Transforming data  |


## 15. Implement Stream API's filter and map methods by your self.
```
public class MyStream<T> {
    private List<T> list;

    public MyStream(List<T> list) {
        this.list = list;
    }
    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return new MyStream<>(filteredList);
    }

    public List<T> collect() {
        return list;
    }

    public static void main(String[] args) {
        List<String> names = List.of("John", "Jane", "Adam", "Eve");

        MyStream<String> myStream = new MyStream<>(names);
        List<String> result = myStream
            .filter(name -> name.startsWith("J"))
            .collect();

        System.out.println(result);
    }
}
```
```
public class MyStream<T> {
    private List<T> list;

    public MyStream(List<T> list) {
        this.list = list;
    }
    public <R> MyStream<R> map(Function<T, R> function) {
        List<R> mappedList = new ArrayList<>();
        for (T element : list) {
            mappedList.add(function.apply(element));
        }
        return new MyStream<>(mappedList);
    }

    public List<T> collect() {
        return list;
    }
    public static void main(String[] args) {
        List<String> names = List.of("John", "Jane", "Adam", "Eve");
        MyStream<String> myStream = new MyStream<>(names);
        List<Integer> result = myStream
            .map(String::length)
            .collect();
        System.out.println(result);
    }
```
