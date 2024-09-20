# hw3 Java OOP

## 1. Write up Example code to demonstrate the three foundmental concepts of OOP.

### Encapsulation

Create a `Person` class, and make attributes private, and then create constructor, setter methods and getter methods.
```Java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

Outside the `Person` class, using setter and getter methods to access attributes of Person class.
```Java
public static void main(String[] args) {
    Person p1 = new Person("Kevin", 10);
    // Using setter and getter to access attributes of Person class
    System.out.println(p1.getName() + " " + p1.getAge());
    System.out.println("After one year");
    p1.setAge(p1.getAge() + 1);
    System.out.println(p1.getName() + " " + p1.getAge());
}
```

### Polymorphism

The polymorphism for methods: overload and override.

The polymorphism for objects: 
Parent class: `Person`; Child class: `Teacher` and `Engineer`.
```Java
public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}
```

```Java
public class Teacher extends Person{
    int age;
    public Teacher(String name, int age) {
        super(name);
        this.age = age;
    }
    public void showInfo() {
        System.out.println("I am " + this.name + ", " + this.age + " years old, and I am an teacher.");
    }
}

```

```Java
public class Engineer extends Person{
    int age;
    public Engineer(String name, int age) {
        super(name);
        this.age = age;
    }
    public void showInfo() {
        System.out.println("I am " + this.name + ", " + this.age + " years old, and I am an engineer.");
    }
}
```

The reference of parent class can not access members of child class: `Person p1 = new Teacher("Kevin", 30);`, the compile type of p1 is `Person`, and the runtime type of p1 is `Teacher`, the accessibility of p1 is determined by compile type. `Teacher t = (Teacher) p1;` is a kind of forcing transform, called downcast, while `Person p1 = new Teacher("Kevin", 30);` is also a forcing transform, called upcast.

```Java
public static void main(String[] args) {
    Person p1 = new Teacher("Kevin", 30);
    Person p2 = new Engineer("Kate", 40);
    // The reference of parent class can not access members of child class
    //System.out.println(p1.age);
    //p1.showInfo();
    Teacher t = (Teacher) p1;
    Engineer e = (Engineer) p2;
    t.showInfo();
    e.showInfo();
}
```

### Inheritance

Java does not allow multiple inheritance.

Child class has all members in parent class, vice versa。

The example code of inheritance is also showed in Polymorphism part.


## 2. What is wrapper class in Java and Why we need wrapper class?

1. Wrapper classes are used to represent the primitive data types as objects. Boxing: primitive -> object, Unboxing: object -> primitive.
2. Since Java is an object-oriented programming language, wrapper class provides the situation where you need an object instead of a primitive data type. 

## 3. What is the difference between HashMap and Hashtable?

1. HashMap is not thread safety (not synchronized), but Hashtable is thread safety (synchronized);
2. HashMap allows null key and value, but Hashtable does not, it will throw NullPointerException.

## 4. What is String pool in Java and why we need String pool? Explain String immunity.

1. The String Pool in Java is a special memory area in the Java heap where String literals are stored. `String s = "xxx";`
2. When you create a String literal, Java checks the String Pool to see if the same String already exists. If it does, a reference to the existing String is returned; if not, the new String is added to the pool. This mechanism helps save memory by preventing the creation of duplicate String objects.
3. String immunity: a String in Java is immutable, meaning once a String object is created, its value cannot be changed.

## 5. Explain garbage collection?

Garbage Collection (GC) in Java is the process of automatically identifying and freeing up memory that is no longer being used by the program. It is a form of automatic memory management, where the Java Virtual Machine (JVM) handles the task of reclaiming memory occupied by objects that are no longer reachable or needed, preventing memory leaks and optimizing memory usage.

## 6. What are access modifiers and their scopes in Java?

1. Access modifier controls the accessible scope in Java. They are private, default, protected, public.
2. Private members can be only accessed in the same class; Default members can be accessed in the same class and same package; Protected members can be accessed in the same class, same package, and sub class; Public members can be accessed in the same class, same package, sub class, and different packages.

## 7. Explain final keyword? (Field, Method, Class)

1. final field can’t be reassigned and re-referenced;
2. final method can’t be overridden;
3. final class can’t be inherited.

## 8. Explain static keyword? (Field, Method, Class). When do we usually use it?

1. Static members will be created during the compile time, and be created only one time, so static variable and static method can be directly called without instantiating a class.
   - static variable: a static variable will be created when the first instance of the class is created, all the instances of this class share the static variable.
   - static method: static method can only call static variable and static method.
   - static class: static class will be created during the compile time.
2. When we want to save memory or ensure that the value is consistent across all objects of the class, we can use static variable. Static method can be useful for designing utility methods, mathematical operations, or factory methods, we can directly call these static method, they are easy to use.

## 9. What is the differences between overriding and overloading?

1. Override occurs in inheritance, but overload occurs in the same class.
2. For override, both method name and method parameter are the same. However, for overload, names of methods are the same but with different method parameters;
3. Override method call is determined at run time, but overload method call is determined at compile time.

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

1. In Java, a method signature refers to the unique identification of a method within a class. **A method signature is composed of the method’s name and its parameter list (the number, type, and order of parameters).** The return type is not considered part of the method signature in Java.
2. Overloading is determined by differences in parameter lists. Overriding must have the same method signature (method name and parameter list) as the method in the superclass.

## 11. What is the differences between super and this?

1. `super` refers to the superclass (parent class) of the current class. It is used to access members (variables, methods, or constructors) of the parent class. In constructor, `super()` is used to call the constructor of the superclass. `super()` is hidden in the begin of constructor, the default constructor of the superclass is automatically called.
2. `this` is used to refer to instance variables or methods of the current object, especially when there's a conflict between local variables and instance variables (e.g., when the parameter and instance variable names are the same). `this()` in constructor is used to call other overloading constructors.

## 12. Explain how equals and hashCode work.

1. The equals() method is used to determine whether two objects are logically equivalent, meaning they have the same data or attributes.
2. The hashCode() method returns an integer representation of the object, called a hash code, which is used in hash-based collections like HashMap, HashSet, etc., for efficient lookup and retrieval.
3. There is a strict contract between equals() and hashCode(): 1. If two objects are considered equal according to the equals() method, then they must have the same hash code. 2. If two objects have the same hash code, they may or may not be equal according to equals(). However, unequal objects should aim to produce different hash codes to minimize hash collisions (where two different objects end up in the same bucket in a hash table).

## 13. What is the Java load sequence?

In general, `static block` -> `block` -> `constructor`

In detail, `static block` -> `static variable` -> `block` -> `constructor` -> `variable`

```Java
public class LoadSequence {
    static String staticVar = "static variable";
    String var = "normal variable";

    static {
        System.out.println("static block");
    }

    {
        System.out.println("normal block");
    }

    public LoadSequence() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println(LoadSequence.staticVar);
        LoadSequence ls = new LoadSequence();
        System.out.println(ls.var);
    }
}
```

Output:

```
static block
static variable
normal block
constructor
normal variable
```

## 14. What is Polymorphism? And how Java implements it?

More detail are written in Question 1

Method Polymorphism includes method overloading and overriding; Object Polymorphism allows objects to be treated as instances of their parent class. 

Java uses upcasting and downcasting to implement it.

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?

More detail are written in Question 1

1. Bundling attributes and methods into a class, and making attributes private, these private attributes are not accessible to other classes, they are only accessible via the public setter and getter methods. 

2. Encapsulation ensure the Integrity Protection and the data security by restricting its access. And it improves code maintenance.

## 16. Compare interface and abstract class.

1. Interface: Before Java 8, has only abstract methods.
After Java 8, interface has started supporting implemented methods, but these methods should be modified with default or static.
Absract class: Normally has a mixture of abstract and non-abstract methods.
2. A class can implement multiple interfaces. Abstract class doesn’t support multiple inheritance.
3. If a class implements an interface, this class must implement all abstract method in the interface. But a child class does not have to implement the abstract method of abstract class, if this child class extends the abstract class, and the child class should be abstract.

## 17. Write a factory pattern in code.

Firstly, create a `Person` interface:

```Java
public interface Person {
    void saySomething();
}
```

Secondly, create `Teacher`, `Engineer`, `Doctor` classes to implement `Person` interface: 

```Java
public class Teacher implements Person{
    @Override
    public void saySomething() {
        System.out.println("I am a teacher.");
    }
}
```

```Java
public class Engineer implements Person{
    @Override
    public void saySomething() {
        System.out.println("I am an engineer.");
    }
}
```

```Java
public class Doctor implements Person{
    @Override
    public void saySomething() {
        System.out.println("I am a doctor.");
    }
}
```

Finally, create a `PersonFactory` class to provide a factory class to instantiate target class (target class can be `Teacher`, `Engineer`, or `Doctor` class)

```Java
public class PersonFactory {
    public Person getPerson(String personType) {
        if (personType == null)  return null;
        if (personType.equalsIgnoreCase("TEACHER")) {
            return new Teacher();
        }
        else if (personType.equalsIgnoreCase("ENGINEER")) {
            return new Engineer();
        }
        else if (personType.equalsIgnoreCase("DOCTOR")) {
            return new Doctor();
        }
        return null;
    }
}
```

Then, test `PersonFactory` class: 

```Java
public static void main(String[] args) {
    PersonFactory pf = new PersonFactory();
    Person p1 = pf.getPerson("Teacher");
    Person p2 = pf.getPerson("Engineer");
    Person p3 = pf.getPerson("Doctor");
    p1.saySomething();
    p2.saySomething();
    p3.saySomething();
}
```

Output: 

```
I am a teacher.
I am an engineer.
I am a doctor.
```

## 18. Write a adapter pattern in code.

Adapter pattern aims to solve the compatibility problem between different interfaces.

Ref: https://www.runoob.com/design-pattern/adapter-pattern.html

`MediaPlayer` interface supports mp3, `AdvancedMediaPlayer` interface supports mp4 and vlc. `MediaAdapter` class is an adapter class to `MediaPlayer` interface, enable `MediaPlayer` interface to have features in `AdvancedMediaPlayer` interface.

Firstly, create `MediaPlayer` interface and `AdvancedMediaPlayer` interface: 

```Java
public interface MediaPlayer {
    public void play(String audioType, String fileName);
}
```

```Java
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
```

Secondly, create `VlcPlayer` and `Mp4Player` classes to implement `AdvancedMediaPlayer`: 

```Java
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
    }
}
```

```Java
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String fileName) {
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
```

Thirdly, create adapter class `MediaAdapter` to `MediaPlayer`: 

```Java
public class MediaAdapter implements MediaPlayer{
    AdvancedMediaPlayer advancedMusicPlayer;
    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
```

Finally, `AudioPlayer` implements `MediaPlayer`, and enable to support all audiotype in `MediaPlayer` and `AdvancedMediaPlayer` by using adapter `MediaAdapter`: 

```Java
public class AudioPlayer implements MediaPlayer{
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing mp3 file. Name: "+ fileName);
        }
        else if(audioType.equalsIgnoreCase("vlc")
                || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else{
            System.out.println("Invalid media. "+ audioType + " format not supported");
        }
    }
}
```

Test:
```Java
public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "a.mp3");
        audioPlayer.play("mp4", "b.mp4");
        audioPlayer.play("vlc", "c.vlc");
        audioPlayer.play("avi", "d.avi");
    }
```

Output:
```
Playing mp3 file. Name: a.mp3
Playing mp4 file. Name: b.mp4
Playing vlc file. Name: c.vlc
Invalid media. avi format not supported
```

## 19. Write singleton pattern in code, make sure your code is thread-safe.

Using volatile and synchronized keyword to guarantee thread-safe.

```Java
public class Singleton {
    private String name;
    // volatile instance
    private static volatile Singleton instance;

    private Singleton(String name) {
        this.name = name;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton("Kevin");
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

Output:

```
Singleton{name='Kevin'}
```

## 20. Design a parking lot (put the code to `Coding/hw3src/com/parkinglot`)

I don't know the requirement, so I just give the simplest parking lot.

`Vehicle` class: 
```
Attributes: vehicleType, plate
```

Car class, `CompactCar, RegularCar, LargeCar`:
```
There are three car types: compact, regular and large.
So CompactCar, RegularCar, LargeCar extend Vehicle class.
```

`ParkingSpot` class:
```
Attributes: spotNumber, isOccupied, vehicle;
methods: park, unpark
```

`ParkingLot` class:
```
Attribute: parkingLot
(can be improved, such as parking fee)
```

Output:

```
Spot 0: spotType='Compact', isOccupied=true, vehicleType=Compact, vehiclePlate=AAA000
Spot 1: spotType='Compact', isOccupied=true, vehicleType=Compact, vehiclePlate=AAA001
Spot 2: spotType='Compact', isOccupied=true, vehicleType=Compact, vehiclePlate=AAA002
Spot 3: spotType='Compact', isOccupied=true, vehicleType=Compact, vehiclePlate=AAA003
Spot 4: spotType='Compact', isOccupied=true, vehicleType=Compact, vehiclePlate=AAA004
Spot 5: spotType='Regular', isOccupied=true, vehicleType=Regular, vehiclePlate=BBB000
Spot 6: spotType='Regular', isOccupied=true, vehicleType=Regular, vehiclePlate=BBB001
Spot 7: spotType='Regular', isOccupied=true, vehicleType=Regular, vehiclePlate=BBB002
Spot 8: spotType='Regular', isOccupied=true, vehicleType=Regular, vehiclePlate=BBB003
Spot 9: spotType='Regular', isOccupied=true, vehicleType=Regular, vehiclePlate=BBB004
Spot 10: spotType='Large', isOccupied=true, vehicleType=Large, vehiclePlate=CCC000
Spot 11: spotType='Large', isOccupied=true, vehicleType=Large, vehiclePlate=CCC001
```

## 21. What are Queue interface implementations and what are the differences and when to use what?

Implementations: AbstractQueue, ArrayBlockingQueue, ArrayDeque, ConcurrentLinkedDeque, ConcurrentLinkedQueue, DelayQueue, LinkedBlockingDeque, LinkedBlockingQueue, LinkedList, LinkedTransferQueue, PriorityBlockingQueue, PriorityQueue, SynchronousQueue

LinkedList (FIFO): 
- Nulls: Allows null elements.
- Thread Safety: Not thread-safe.
- Operations: offer(), poll(), peek(), etc.

PriorityQueue (Priority-based):
- Nulls: Does not allow null elements.
- Thread Safety: Not thread-safe.
- Operations: offer(), poll(), peek(), etc. (removal retrieves the highest priority element).

ArrayDeque (Deque implementation):
- Nulls: Does not allow null elements.
- Thread Safety: Not thread-safe.
- Operations: addFirst(), addLast(), removeFirst(), removeLast(), peekFirst(), peekLast().

