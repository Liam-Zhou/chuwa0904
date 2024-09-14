## 1. Learn Java generics by reading and practicing following code:
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic /
```
public class GenericClass<T> {
    // T -> Integer, User, Product
    T obj;
    
    public GenericClass(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }
}

public class GenericClassTest {
    public static void main(String[] args) {
        // Test for Integer type
        GenericClass<Integer> obj1 = new GenericClass<>(5);
        System.out.println(obj1.getObj());

        // Test for double type
        GenericClass<Double> obj2 = new GenericClass<>(15.745);
        System.out.println(obj2.getObj());

        // Test for String type
        GenericClass<String> obj3 = new GenericClass<>("slfjg");
        System.out.println(obj3.getObj());
    }
}

public class GenericMethod {
    public static void main(String[] args){
        System.out.printf("Max of %d, %d and %d is %d\n\n", 3, 4, 5, MaximumGenericTest.maximum(3, 4, 5));
    }
}

class MaximumGenericTest {
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if(y.compareTo(max) > 0) {
            max = y;
        }

        if(z.compareTo(max) > 0) {
            max = z;
        }
        return max;
    }
}

public class GenericMultiArguments {

    public static <T1, T2, T3> void temp(T1 x, T2 y, T3 z) {
        System.out.println("This is x = " + x);
        System.out.println("This is y = " + y);
        System.out.println("This is z = " + z);
    }
    public static void main(String[] args) {
        temp(1, "2", 3L);
    }
}
```

## 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
1. Check for null before head
```
public class OptionalDemo {
    public static void main(String[] args) {
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(1, new User("Alice", new Address("Main Street")));
        userMap.put(2, new User("Bob", null));

        String streetName = "Unknown";
        User user = userMap.get(2);
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                streetName = address.getStreet();
                }
            }
        System.out.println("Street name without Optional: " + streetName);

    }
}
```

2. Use Optional to avoid nullptr exception:
```
public class OptionalDemo {
    public static void main(String[] args) {
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(1, new User("Alice", new Address("Main Street")));
        userMap.put(2, new User("Bob", null));

        streetName = Optional.ofNullable(userMap.get(2))
            .map(User::getAddress)
            .map(Address::getStreet)
            .orElse("Unknown");
        System.out.println("Street name with Optional: " + streetName);
    }
}
```

3. optional get() method, if it's null then throws NoSuchElementException error
```
Optional<String> optionalName = Optional.of("Alice");
String name = optionalName.get(); // 返回 "Alice"
```

4. orElse(), if it's empty, then return the default value
```
Optional<String> optionalName = Optional.empty();
String name = optionalName.orElse("Unknown"); // 返回 "Unknown"
```

5. orElseThrow(), NoSuchElementException if it's empty
```
Optional<String> optionalName = Optional.of("Alice");
String name = optionalName.orElseThrow(); // 返回 "Alice"
String name2 = null;
Optional<String> optionalName2 = Optional.ofNullable(name);
String name = optionalName.orElseThrow(() -> "UNKNOWN"); // 返回
```

## 7. What are the advantages of the Optional class?
1. Avoid null pointer exception.
2. Avoid overuse of null checks.
3. It can display a null variable with default value.
## 8. Explain Functional Interface and Lambda with code samples.
A Functional Interface is an interface that contains exactly one abstract method, and it can have multiple default or static methods./
Lambda is the implementation of the abstract method./
```
@FunctionalInterface
interface MyFunctionalInterface {
    void printMessage(String message);
}
public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Lambda expression to implement the printMessage method
        MyFunctionalInterface printer = (message) -> System.out.println(message);

        // Using the functional interface
        printer.printMessage("Hello, Functional Interface!");
    }
}
```

## 9. Explain Method Reference with code samples?
1. ContainingClass::staticMethodName
```
public class MethodReferenceExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35));

        people.sort(Person::compareByName);

    }
}
```

2. containingObject::instanceMethodName
```
public class MethodReferenceExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35));

        Comparator<Person> byAgeComparator = Comparator.comparingInt(Person::getAge);
        people.sort(byAgeComparator);

    }
}
```

3. ContainingType::methodName
```
public class MethodReferenceExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35));

        Function<Person, String> getNameFunction = Person::getName;
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);
    }
}
```

4. ClassName::new
```
public class MethodReferenceExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35));

        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person newPerson = personCreator.apply("David", 40);
    }
}
```

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
As long as the address of variable is unchanged, then lambda can use it.
```
public void testFinal21() {
    String localVariable = "Local";
    localVariable = "LOCAL"; // String is changed, so it has a new address
    Foo foo = parameter -> {
        return parameter + " " + localVariable; // 会报错
    };  
    System.out.println(foo.aMethod("hello"));
}
```
## 11. Can a functional interface extend/inherit another interface?
One functional interface can extend/inherit another interface.\

Single Abstract Method Requirement: A functional interface is defined as having exactly one abstract method. If a functional interface extends another interface (which can also be a functional interface), it must still adhere to this rule. This means that if the parent interface has an abstract method, the child functional interface should not introduce another abstract method, or else it will no longer be a functional interface.\

Inheriting a Functional Interface: If the parent interface is a functional interface (with one abstract method), the child interface can still be considered a functional interface as long as no additional abstract methods are introduced.

## 12. What are Intermediate and Terminal operations?
Intermediate operations: /
Intermediate operations return another stream and are used to build a pipeline of transformations. They do not process or trigger the stream elements until a terminal operation is invoked. Intermediate operations are typically lazy, meaning they are not executed until a terminal operation is called./
Examples: /
1. filter(Predicate p) 
2. limit(n) 
3. skip(n) 
4. distinct() 
5. map(function f) element -> black box(f) -> return new element
6. flatMap(function f)
Terminate operations: 
Terminal operations mark the end of the stream pipeline. They trigger the processing of the stream and either produce a result or perform an action. Once a terminal operation is invoked, the stream is considered consumed, and it cannot be reused. /
Examples: /
1. allMatch(Predicate p) 
2. anyMatch(Predicate p)  
3. noneMatch(Predicate p) 
4. findFirst 
5. findAny  
6. count  
7. max(Comparator c) 
8. min(Comparator c) 
9. forEach(Consumer c)  
10. reduce(T identity, BinaryOperator)
11. reduce(BinaryOperator)
12. collect(Collectors c)

## 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
Filter:
```
public class StreamFilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Filter only even numbers
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());

        System.out.println(evenNumbers);  // Output: [2, 4, 6]
    }
}
```

Map:
```
public class StreamMapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack");

        // Convert each name to uppercase
        List<String> upperCaseNames = names.stream()
                                           .map(String::toUpperCase)
                                           .collect(Collectors.toList());

        System.out.println(upperCaseNames);  // Output: [JOHN, JANE, JACK]
    }
}
```

sorted:
```
public class StreamSortedExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Alice");

        // Sort the names in alphabetical order
        List<String> sortedNames = names.stream()
                                        .sorted()
                                        .collect(Collectors.toList());

        System.out.println(sortedNames);  // Output: [Alice, Jack, Jane, John]
    }
}
```

## 14. How are Collections different from Stream?
Collections:
1. Data structure holds all the data elements
2. External Iteration
3. Can be processed any number of times
4. Elements are easy to access
5. Is a data store
Stream:
1. No data is stored. Have the capacity to process an infinite number of elements on demand
2. Internal Iteration
3. Traversed only once
4. No direct way of accessing specific elements
5. Is an API to process the data