# Hw 3

## Question 2
Wrapper class is for the primitive data type of Java to make it have object orianted features. Sometimes we need to treat primitive data type in object, we will need wrapper class in that case

## Question 3
* HashMap is not not synchronized which is not thread safe, and it allows null keys and null values.
* HashTable is synchronized which means it is thread safe, and it doesn't allow null keys and null values

## Question 4
String pool is a special storage place in the heap memory space in Java. When a new String is created, JVM will check the String pool if that String is already in the string pool. If it is already created, it will reuse the reference instead of creating a new one. \n
Once a String object is created, it cannot be modified. Because of String pool, if a String object is able to change, it will also effect other String object that used to have the same value, since they might have the same reference.

## Question 5

Garbage Collection is the process of Java JVM automatically manages memory. Garbage Collection will find the allocated heap memory that is no longer used and free up those memory.

## Question 6

* private: means it can be only accessible within the class.
* public: means it is accessible everywhere like from other class, package, or module.
* protected: different than private, it is accessible for subclasses and other class within the same package.
* default: for no keyword is used, it is accessible for other class within the same package.

## Question 7
* Final Field(Variable): for varibales, it means once it has been assigned, it cannot be modified.
* Final method: for methods, it means it cannot be override by subclasses.
* Final class: for class, it means it cannot be inherited.
  
## Question 8
* Static Field(Variable): A static field is shared by all instances of the class. All instances of the class shares the same static field. Best use for defining a constant in a class.
* Static Method: A static method belongs to a class. It doesn't need a instance to call the method. Like a lot of method from Math. When we just need to use this method but don't want to create an instance, we will create a static method.
* Static Class: Only can be declared in a nested class and it doesn't need to create a instance of the outer class to be created. It is often used for organizing code logically like creating multiple similar helper class.

## Question 9
* Overriding occurs when a subclass wants to implement a method that is already defined in its superclass. It enables the subclass have the same name of the method but different implementations.It is static polymorphism.
* Overloading occurs when there are multiple method that have a same name but different signatures. It is dynamic polymorphism

## Qustion 10
In Java, a method signature only consist a function name and a parameter list. The compiler uses the method signature to distinguish between overloaded methods.For a method to override another, it must have the same method signature(name and parameters) as the method in the superclass.

## Quesiton 11
* Super: super is used for access superclass objects.
* This: It is used for the current instance of the class 

## Question 12
Equals compares two object's reference by default, it can be override. When two object have the same hashcode, they usually treated as the same object. If they are not actually the same. There are hash collision occur.

## Question 13
* Class loading: The JVM loads the class into memory via the ClassLoader the first time the class is referenced.
* Class Linking: The class is verified, prepared (memory allocated for static fields), and symbolic references are resolved.
* Class Initialization: Static variables are initialized and static blocks are executed in the order they appear.
* Object Creation: Memory is allocated for the object, instance variables are initialized, instance blocks are executed, and the constructor is called. Constructor chaining occurs starting from the superclass.

## Question 14
Polymorphism in programing defines the ability of a single interface, method, or class to represent different forms or behaviors. In Java, there are Static Compile-time Polymorphism (Method Overloading) and Dynamic Runtime Polymorphism (Method Overriding)

## Question 15
Encapsulation is defined as wrapping data into a single unit. In Java, it implement encapsulation via private access modifiers and provide public getter and setter methods. We need encapsulation for hide the internal state of an object from external modification and makes code more maintainable

## Question 16
* Abstract classes and Interface for provides blueprint for subclasses
* Abstract classes can have both abstract methods and concrete methods. Interface can have both abstract methods and concrete methods only after Java 8.
* A class can inherite multiple interfaces but not Abstract classes
* Abstract classes can have instance variable and constants but Interface can only have constants
* Abstract classes can have constructor but Interface can't
* Abstract class methods can have any access modifier but Interface methods are public by default