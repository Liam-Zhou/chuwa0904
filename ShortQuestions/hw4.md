# HW4 

Haifeng Yang

## 1. Read Code: Generic {#section-1}

### Static

- The static variables are loaded first, then it runs the static {} block. This happened when an class instance is first created, meaning that when create a class instance with static {} second time, the static part will not be called again. 

- Java lazy load the static method, static variable. If not referenced, it will be optimized by just being ignored.

  

### Class inherit from Object

We can override the object class method like toString()

‘

### Generic NonGeneric

**Generic type T** implements the **Comparable interface**, handles any data type without the need to write separate methods for each type. It has **code reusablility** and **type safety**(Prevent mix use of type accidentally, catch type-related errors at **compile-time**)

**Non-generic** requires multiple overload methods for each type. We benefit from simplicity and **better performance** with less type conversion. **Less Flexibility, Risk of inconsistence**.



### Constructor

Constructor can call constructor



<h2 id="java8-feature">2. Read Code: Java8 Feature</h2>

1. **Interface: default, abstract, static** 

~~~java
public interface DIML {
    static final String BLOG = "is Chuwa a";

    int add(int a, int b);

    default int substract(int a,  int b) {
        return a - b;
    }

    static String blogName() {
        return BLOG;
    }
}
~~~

2. **Lambda** 

   Functional Interface has only one abstract method. When we override it, we can simply use the lambda to implement it.

   When pass in the parameter, the parameter has to be effective final, meaning the parameter can not be changed or will have compile error.

~~~java
@FunctionalInterface
public interface Foo {
    String aMethod(String string);

    default String defaultBar() {
        String s = "default Bar method";
        System.out.println(s);
        return s;
    }

    default String defaultCommon() {
        String s = "defaultCommon from Bar";
        System.out.println(s);
        return s;
    }
}

@Test
public void overrideFoo() {
    // Before Java 8, we can use Anonymous Class to override this method
    // Interface var = new Class
    // List<Integer> var = new ArrayList<>()
    // Foo 这个interface有一个abstract method, 所以在Anonymous Class里需要override来提供method body
    Foo fooByIC = new Foo() {
        @Override
        public String aMethod(String string) {
            return string + " from Foo";
        }
    };

    String hello = fooByIC.aMethod("hello");
    System.out.println(hello);
}

@Test
public void lambdaFoo() {
    // Foo.aMethod() 是abstract method,缺少method body. lambda 提供method body.
    // 比Anonymous class 简洁很多。
    Foo foo = parameter -> parameter + " from Foo";
  // Foo foo = parameter -> parameter.toUpperCase() + " from Foo";
    //Foo foo = parameter -> {
            //return parameter + " " + localVariable;
    //};
    String hello = foo.aMethod("hello");
    System.out.println(hello);
}

~~~



2. **Method Reference**

​	The use of Method Reference

implicit conversion 隐式转换, 编译器在一定的请开给你下帮忙完成了一部分操作 不需要显式转换逻辑.

lambda表达式或者方法引用返回int 自动转换成了对应的comparator对象

~~~java
public static void main(String[] args) {
    // 1. 静态方法引用
    List<Person> people = new ArrayList<>(Arrays.asList(
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35)));

 	people.sort(new Comparator<Person>() {
    	@Override
        public int compare(Person p1, Person p2) {
            return Person.compareByName(p1, p2);
        }
	});
    // 使用Lambda表达式
    // 简单来说这里需要静态的原因是 编译器通过签名来识别 我们传入两个Person来判断 被编译器识别成了我们直接传入一个comparator 所以需要是静态
    // sort() 方法 期望的是一个 Comparator<Person>，即一个具有签名 int compare(T o1, T o2) 的方法，
    // 它接受两个对象并返回一个 int。
    // 因为 Person::compareByName 符合这个签名（它需要两个 Person 参数，并返回 int），
    // 所以编译器可以将 Person::compareByName 看作是 Comparator<Person> 的实现。
    // 那下面不需要静态的部分 没有传入Person对象 或者只传入一个Person对象都是可以被识别成对象级别的方法

    people.sort((p1, p2) -> Person.compareByName(p1, p2));
    // 使用静态方法引用 这里要求方法是静态
    // Person::CompareByName 返回一个int 这里也是int被直接用来重写comparator了
    people.sort(Person::compareByName);

	// 2. 实例方法引用（特定对象的实例方法）
    // 为什么 getAge() 可以是实例方法?
    //  1. 实例方法引用的签名匹配 Person::getAge 符合 ToIntFunction<Person> 接口的签名，
    //  它接受一个 Person 对象并返回一个 int，这正是 comparingInt 需要的函数。
    //  2. 编译器隐式处理：当你调用 Comparator.comparingInt(Person::getAge) 时，
    //  编译器知道你传递的是一个方法引用，它会将每一个 Person 实例传递给 getAge() 方法来获取 int 值
    //  3. comparingInt 是一个工具方法：Comparator.comparingInt 是专门为这种情况设计的。
    //  它简化了你手动编写比较器的步骤，而你只需要提供一个提取 int 值的函数即可。
    //  这个函数可以是实例方法引用，例如 Person::getAge。
    // 等效于
    // Comparator<Person> byAgeComparator = new Comparator<Person>() {
    //     @Override
    //     public int compare(Person p1, Person p2) {
    //         return Integer.compare(p1.getAge(), p2.getAge());
    //     }
    // };
    Comparator<Person> byAgeComparator = Comparator.comparingInt(Person::getAge);
    people.sort(byAgeComparator);
    
    // 这个是comparator同理
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    names.sort(String::compareToIgnoreCase);
    
    // 3. 类的实例方法引用
    // Function, 一般配合stream 将会被stream使用。
    // Function<T, R> 一样是一个函数式接口,接受一个参数返回一个参数，我们提供这个接口的重写方法就行了
    Function<Person, String> getNameFunction = Person::getName;
    // 等价于
    Function<Person, String> getNameFunction = (person) -> person.getName();
    List<String> names = people.stream()
                           .map(Person::getName)  // 使用方法引用或 Function
                           .collect(Collectors.toList());
    System.out.println(stream_names);
    // 只需要知道 Person::getName 能提取名字并传递给 map() 方法来处理流中的每个元素
    // 每一步都是传入自定义操作
    people.stream().map(Person::getName).forEach(System.out::print);

    // 4. 构造方法引用  
    // Bifunction 和 Function 同理
    BiFunction<String, Integer, Person> personCreator = Person::new;
    Person newPerson = personCreator.apply("David", 40);
    people.add(newPerson);
    System.out.println(people);
}
~~~

3. **Optional**  处理NullPointerException

## 3. Practice Stream

## 4. Practice Optional

## 5. Best Practice nullptr exception prevention

### Avoid return null

~~~java
// return empty collection or an optional instead of null
public List<Order> getOrders() {
    // Return an empty list instead of null
    return Collections.emptyList(); 
}

public Optional<Order> findOrderById(Long id) {
    // Return an Optional to represent "order may or may not exist"
    return Optional.ofNullable(orderRepo.findById(id));
}
~~~



### Optional for Nullable return type

Avoid Nullptr and Make the code easy to read.

~~~java
public Optional<String> getEmail(User user) {
    return Optional.ofNullable(user.getEmail());
}
// // Using Optional on the receiving end
Optional<String> email = getEmail(user);
email.ifPresent(System.out::println);
~~~



### Defensive Coding

Just assume all the return can be null

~~~java
// Check input at the beginning of a method
public void processOrder(Order order) {
    Objects.requireNonNull(order, "Order must not be null");
    // Continue processing safely
}
// or thorws IllegalArgumentException
public void process(Order order) {
    if (order == null) {
        throw new IllegalArgumentException("Order cannot be null");
    }
    // Continue processing order
}

~~~



### Use @NonNull and @Nullable Annotaion

~~~java
public @NonNull String getName(@Nullable User user) {
	return user != null ? user.getName() : "Anonymous";
}
~~~



### Use Object.equals() check to avoid the null check using ==

~~~java
if(a.equals(b)) {}

if(objects.equals(str1, str2)) {}
~~~



### Always Initialize

~~~java
Private List<String> names = new ArrayList<>();
~~~



### Use String.ValueOf() instead of toString()

**Why**: Calling `.toString()` on a `null` object will throw an NPE.

The ValueOf return **"null"** instead of **null**.

### Avoid deep method chains, Or use Optional



## 6. Java 8 new features

### Lambda Expression

~~~java
List<Integer> nums = new Array.asList(1, 2, 3);
names.sort((a, b) -> a - b);

Runnalble r = ()->System.out.println("r");
~~~



### Functional Interface

~~~java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

Greeting greeting = name -> System.out.print("Hello " + name);
greeting.sayHello();
~~~



### Stream API

~~~java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Filter names starting with "A" and print them
names.stream()
     .filter(name -> name.startsWith("A"))
     .forEach(System.out::println);
~~~



### Optional

~~~java
Optional<String> optional  = Optional.ofNullable(null);
String result = optional.orElse("Default Value");
System.out.println(result);  // Output: Default Value

Optional<String> optionalA = Optional.of("Alice");
String res = OptionalA.get()
String temp = OptionalA.orElseGet()
// res == temp
~~~



### Method Reference 

参考第二部分 <a href="#java8-feature">跳转到Java8特性部分</a> 的分析



### Default and static methods in Interfaces

Static will **not be inherit**, it can be called like static methods as helper methods and utility method.

~~~java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
    
    default String sleep() {
        return "Sleep"
    }
  
    static void Spend() {
        System.out.print(".");
    }
}

Greeting greeting = name -> System.out.print("Hello " + name);
greeting.sayHello();
~~~



### Date and Time API (java.time)

~~~java
// Current date and time
LocalDate date = LocalDate.now();
LocalTime time = LocalTime.now();
LocalDateTime dateTime = LocalDateTime.now();

System.out.println(date);    // Output: 2024-09-12
System.out.println(time);    // Output: 15:34:45.123
System.out.println(dateTime); // Output: 2024-09-12T15:34:45.123
~~~



### Stream with Collectors

~~~java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Collect names into a List
List<String> filteredNames = names.stream()
                                  .filter(name -> name.startsWith("A"))
                                  .collect(Collectors.toList());

System.out.println(filteredNames);  // Output: [Alice]

~~~



### Parallel of Streams

Allow parallel processing for leveraging speed

~~~java
// Parallel processing of a stream
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.parallelStream()
       .forEach(n -> System.out.println(n + " " + Thread.currentThread().getName()));

~~~



### Nashorn JavaScript Engine

Like Python, Java can run JavaScript code.

~~~java
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        engine.eval("print('Hello from JavaScript');");
    }
}
~~~



## 7. Advantages of Optional Classes

1. Avoid NullPointerException(NPE), avoid null check, handles null consistently, Simplifies Default Value Handling
2. Marks clearly that methods or variable may not return a value. We use optional means that we want to handle it
3. Encourage Functional Programming (map, flatMap, filter, and ifPresent)
4. Improve Readability, Maintainability
5. Encapsule null-handling logic

## 8. Functional Interface and Lambda

<a href="#java8-feature"> Also refer to the above analysis. </a>

Functional Interface has only one abstract method. When we override it, we can simply use the lambda to implement it.

When pass in the parameter, the parameter has to be effective final, meaning the parameter can not be changed or will have compile error.

~~~java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);  // Only one abstract method
    
    default String sleep() {
        return "Sleep"
    }
  
    static void Spend() {
        System.out.print(".");
    }
}

public static void main(String[] args) {
    // Lambda expression implementing Greeting interface
    Greeting greeting = name -> System.out.println("Hello, " + name);

    // Call the sayHello method
    greeting.sayHello("Alice");
}
~~~



## 9. Method reference

<a href="#java8-feature"> Also refer to the above analysis. </a>

implicit conversion 隐式转换, 编译器在一定的请开给你下帮忙完成了一部分操作 不需要显式转换逻辑.

lambda表达式或者方法引用返回int 自动转换成了对应的comparator对象

编译器还有IDE会根据输入和return数据识别是否是静态static (两个及以上的输入)  还是类方法(小于等于一个输入 有输入必须是相同类的引用) 

## 10. Lambda can use unchanged variable outside of lambda

Yes. It is Effective final. When variable not modified in the block, it will be identified as final. 

~~~java
@FunctionalInterface
public interface Foo {
    String aMethod(String string);

    default String defaultBar() {
        String s = "default Bar method";
        System.out.println(s);
        return s;
    }

    default String defaultCommon() {
        String s = "defaultCommon from Bar";
        System.out.println(s);
        return s;
    }
}

@Test
public void overrideFoo() {
	// Foo booByIC = ()->string + " from Foo";
    String random = "good for me";
	Foo fooByIC = (in) -> (in + random + " from Foo");
    String hello = fooByIC.aMethod("hello");
    System.out.println(hello);
}
~~~

If we need to modify an object, we can use a wrapper object.

~~~java
AtomicInteger count = new AtomicInteger(0);  

Runnable r = () -> {
    count.incrementAndGet();  // Modifying the AtomicInteger's value
    System.out.println(count.get());
};

int[] count = {0};  // Array reference is effectively final

Runnable r = () -> {
    count[0]++;  // Modifying the array element, not the array reference
    System.out.println(count[0]);
};

r.run();  // Output: 1
~~~



## 11. Create a functional interface extends and inherits another interface

~~~java
@FunctionalInterface
interface BaseInterface {
    void baseMethod(); // One abstract method
}

@FunctionalInterface
interface ExtenededInterface extends BaseInterFace{
    // So no more abstract method here
    default void defaultMethod(){
        System.out.println("This is a default method");
    }
    static void staticMethod() {
        System.out.println("This is a static method");
    }
}
public class Main {
    public static void main(String[] args) {
        ExtendedFunctionalInterface example = () -> System.out.println("Overriding baseMethod");
        
        example.baseMethod();  // Output: Overriding baseMethod 
        example.defaultMethod();  // Output: This is a default method
        ExtendedFunctionalInterface.staticMethod();  // Output: This is a static method
    }
}
~~~



## 12. Intermediate and Terminal Operations

Intermediate operations **return a stream** for further evaluation, do **not produce result**. Intermediate operations transform a stream into another stream. For optimization, it uses lazy evaluation, meaning it won't be applied until it knows it needs to, typically when the terminal operations are called.

**filter(), map(), flatMap(), distinct(), sorted(), limit(), skip()**



Terminal Operation are used to **produce a final result** from a steam. When it is called, it triggers execution of all intermediate operations in stream's pipeline.

**foreach(), collect(), reduce(), count(), findfirst(), findAny(), allMatch(), anyMatch(), nonMatch(), min(), max().**



## 13. Most Commonly used intermediate opearations in Stream API

~~~java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Terminal operation: collect()
List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("A"))  // Intermediate operation
    .map(String::toUpperCase)  // Intermediate operation
    .collect(Collectors.toList());  // Terminal operation
System.out.println(filteredNames);  // Output: [ALICE]
~~~

-

~~~java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

// A typical pipeline of intermediate and terminal operations
long count = numbers.stream()
                    .filter(n -> n % 2 == 0)  // Intermediate operation: filter
                    .map(n -> n * n)  // Intermediate operation: map
                    .count();  // Terminal operation: count
System.out.println("Count of even squares: " + count);

~~~



## 14. Collections difference with Stream

- Collections (List, Set, Map) data structures that used to **store elements in memory and frequently make changes to the data**.

- Streams are a sequence of elements that **used to process data, compute on demand(lazily)**. Until the terminal operation is used, stream will operate. 

- Collections are usually **mutable**, meaning you can add, remove or modify elements after the collection has been created (though there are immutable variants like Collections.unmodifiableList())

- Stream are **immutable** meaning that it can not be modified.

- Stream is more **procedural programming**, it will be consumed after the operation. While the Collections can be reused and changed.

- Stream uses internal iterations handled by Stream API, not the user. Enables **streams** to **parallelize processing** behind scenes if needed.



## 15. Implement Stream API's filter and map

### One use list to store data

~~~java
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
    private List<T> source;
    public MyStream(List<T> source) {
        this.source = source;
    }

    public static <T> MyStream<T> of(List<T> soruce) {
        return new MyStream<T>(soruce);
    }

    public MyStream<T> filter(Predicate<? super T> predicate) {
        List<T> list = source;
        for (T t : list) {
            if (predicate.test(t)) {
                list.add(t);
            }
        }
        return new MyStream<>(list);
    }

    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> result = new ArrayList<>();
        for(T t : source)
            result.add(mapper.apply(t));
        return new MyStream<>(result);
    }

    public List<T> collect() {
        return source;
    }

    public void forEach(Consumer<? super T> action) {
        for(T t : source)
            action.accept(t);
    }
}

~~~

### Iterator Implementation, with lazy load

~~~java
package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
    private final Iterator<T> source;

    private MyStream(Iterator<T> source) {
        this.source = source;
    }
    private final List<Function<Iterator<?>, Iterator<?>>> pipeline = new ArrayList<>();

    public MyStream<T> filter(Predicate<? super T> predicate) {
        pipeline.add(iterator -> new Iterator<T>() {
            private T nextItem;
            private boolean hasNextItem;
            @Override
            public boolean hasNext() {
                while(!hasNextItem && iterator.hasNext()) {
                    T item = ((Iterator<T>)iterator).next();
                    if (predicate.test(item)) {
                        hasNextItem = true;
                        nextItem = item;
                    }
                }
                return hasNextItem;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                hasNextItem = false;
                return nextItem;
            }
        });
        return this;
    }

    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        pipeline.add(iterator -> new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public R next() {
                return mapper.apply(((Iterator<T>)iterator).next());
            }
        });
        return (MyStream<R>) this;
    }

    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = applyPipeline(source);
        while(iterator.hasNext()) 
            action.accept(iterator.next());
    }

    private Iterator<T> applyPipeline(Iterator<T> iterator) {
        for(Function<Iterator<?>, Iterator<?>> stage : pipeline) {
            iterator = (Iterator<T>) stage.apply(iterator);
        }
        return iterator;
    }
}

~~~

