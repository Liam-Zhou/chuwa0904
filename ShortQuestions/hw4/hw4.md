# Java 8
### 1. Learn Java generics by reading and practicing following code:
   https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic

    ... done
### 2. Read the follwoing code repo and type it one by one by yourself.
   https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features
### 3. Practice following stream API exercises at least 3 times
   https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java
### 4. Practice Optional methods at least 2 times
   https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java
### 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.

Use Optional. 

```java
    public static void main(String[] args) {
        Map<Integer,User> users = new HashMap<Integer, User>();
        users.put(1,new User("John",new Address("Moscow")));
        users.put(2,new User("Jane",null));
        // without optional
        String street="Unknown";
        User user=users.get(2);
        if(user!=null){
            Address address=user.getAddress();
            if(address!=null){
                street=address.getStreet();
            }
        }
        System.out.println("Street name without Optional: " + street);
        // with optional
        street= Optional.ofNullable(users.get(2))
                .map(User::getAddress)
                .map(Address::getStreet)
                .orElse("Unknown");
        System.out.println("Street name with Optional: " + street);

    }
```

### 6. Discuss Java 8 new features with code snippet.  

1. Lambda

```java
 interface Foo{
    String method(String s);
    default String defaultBar(){
        String s="default Bar method";
        System.out.println(s);
        return s;
    }
    default String defaultCommon(){
        String s="default Common method";
        System.out.println(s);
        return s;
    }
}
class Main{
    public void overrideFoo(){
//        Foo foo = new Foo(){
//            public String method(String s){
//                return s+" from Foo";
//            }
//        };
        Foo foo = s -> {
            return s+" from foo";
        };
        String hello=foo.method("hello");
        foo.defaultBar();
        foo.defaultCommon();
        System.out.println(hello);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.overrideFoo();
    }
}
```

2. Method Reference

```java
    public static void main(String[] args) {
        /**
         * Array - a very basic data structure. It has a fixed, predetermined size that must be known at the time of creating (instantiating) the array. Think of it as a sorting box with several slots. Here, you have .length - without parentheses as it is a property, not a method
         *
         * List - an interface (not a class) that defines certain behavior. It cannot be instantiated on its own.
         *
         * ArrayList - a concrete class that implements the List interface.
         */
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);
        for (String name : names) {
            System.out.println(name);
        }
    }
```

3. Optional

Example in Q5.

4. Stream API
```java
    public static void main(String[] args) {
        /**
         * Array - a very basic data structure. It has a fixed, predetermined size that must be known at the time of creating (instantiating) the array. Think of it as a sorting box with several slots. Here, you have .length - without parentheses as it is a property, not a method
         *
         * List - an interface (not a class) that defines certain behavior. It cannot be instantiated on its own.
         *
         * ArrayList - a concrete class that implements the List interface.
         */
        List<String> list= Arrays.asList("Alice","Bob","Charlie");
        Stream<String> stream = list.stream();
        Stream<String> stream2 = Stream.of("Alice","Bob","Charlie");
        String[] array = {"Alice","Bob","Charlie"};
        Stream<String> stream3 = Arrays.stream(array);
        Stream<Integer> stream4=Stream.iterate(0,n->n+2).limit(3);
        Stream<Double> stream5=Stream.generate(Math::random).limit(3);
        stream4.forEach(System.out::println);
        stream5.forEach(System.out::println);
    }
```
### 7. What are the advantages of the Optional class?  

Avoid null checks and run time NullPointerExceptions. 

### 8. Explain Functional Interface and Lambda with code samples.

Foo is an interface, we can use lambda to override its functions. 

```java
//        Foo foo = new Foo(){
//            public String method(String s){
//                return s+" from Foo";
//            }
//        };
        Foo foo = s -> {
            return s+" from foo";
        };
```

### 9. Explain Method Reference with code samples?

sort takes a comparator, which can be method reference String::compareToIgnoreCase

```java
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);
        for (String name : names) {
            System.out.println(name);
        }
    }
```

### 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

```java
    public void overrideFoo(){
        String a=" variable inside ";
        Foo foo = s -> {
            return s+a+" from foo";
        };
        String hello=foo.method("hello");
        foo.defaultBar();
        foo.defaultCommon();
        System.out.println(hello);
    }
```

### 11. Can a functional interface extend/inherit another interface?

```java
 interface Foo{
    String method(String s);

     default String defaultBar(){
        String s="default Bar method";
        System.out.println(s);
        return s;
    }
    default String defaultCommon(){
        String s="default Common method";
        System.out.println(s);
        return s;
    }
}

interface Bar extends Foo{

}

class Main {
    public void overrideBar(){
        String a=" variable inside ";
        Bar bar = s -> {
            return s+a+" from bar";
        };
        String hello=bar.method("hello");
        bar.defaultBar();
        bar.defaultCommon();
        System.out.println(hello);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.overrideBar();
    }
}
```

### 12. What are Intermediate and Terminal operations?

```java
    public static void main(String[] args) {
        List<String> names=Arrays.asList("John","Mary","Jane","Alice","Bob","Charlie");
        List<String> filteredNames=names.stream() 
                .filter(name->name.length()>=4)
                .map(String::toUpperCase)
                .sorted((name1,name2)->name2.length()-name1.length()) // above are all intermediate
                .collect(Collectors.toList()); //terminate
        System.out.println(filteredNames);
    }
```

### 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

```java
    public static void main(String[] args) {
        List<String> names=Arrays.asList("John","Mary","Jane","Alice","Bob","Charlie");
        List<String> filteredNames=names.stream()
                .filter(name->name.length()>=4)
                .distinct()
                .limit(5)
                .map(String::toUpperCase)
                .sorted((name1,name2)->name2.length()-name1.length())
                .collect(Collectors.toList()); //terminate
        System.out.println(filteredNames);
    }
```

### 14. How are Collections different from Stream?

1. Stream is a sequence of elements supporting sequential and parallel aggregate operations.
2. A collection represents a group of objects, known as its elements. Some collections allow duplicate elements and
others do not. Some are ordered and others unordered.

```java
public interface Stream<T> extends BaseStream<T, Stream<T>> {}
public interface BaseStream<T, S extends BaseStream<T, S>>
        extends AutoCloseable {}
public interface Collection<E> extends Iterable<E> {}
```
### 15. Implement Stream API's filter and map methods by your self.

```java
class MyStream<T>{
    class MyStream<T>{
        MyStream(Collection<T> c){
            collection = c;
            result = new ArrayList<>();
        }
        Collection<T> collection;
        Collection<T> result;
        MyStream<T> filter(Predicate<T> predicate){
            result.clear();
            for(T t:collection){
                if(predicate.test(t)){
                    result.add(t);
                }
            }
            return new MyStream<T>(result);
        }
        MyStream<T> map(Function<T, T> f){
            result.clear();
            for(T t:collection){
                result.add(f.apply(t));
            }
            return new MyStream<T>(result);
        }
        public String toString(){
            return collection.toString();
        }
    }

    public static void main(String[] args) {
        List<String> names=Arrays.asList("John","Mary","Jane","Alice","Bob","Charlie");
//        List<String> filteredNames=names.stream()
//                .filter(name->name.length()>=4)
//                .distinct()
//                .limit(5)
//                .map(String::toUpperCase)
//                .sorted((name1,name2)->name2.length()-name1.length())
//                .collect(Collectors.toList()); //terminate
//        System.out.println(filteredNames);
        MyStream<String> myStream = new MyStream<>(names);
        System.out.println(myStream.filter(name->name.length()>=4).map(String::toUpperCase).toString());
    }
}
```
https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html