1. Write up Example code to demonstrate the three foundmental concepts of OOP.
    - Encapsulation;
    - Polymorphism;
    - Inheritance;
2. What is wrapper class in Java and Why we need wrapper class?
A wrapper class in Java is an object representation of a primitive data type. Java provides wrapper classes to treat primitive data types (like int, char, etc.) as objects.
Working with Collections: Java’s collections framework (like ArrayList, HashMap, etc.) cannot store primitive data types directly; they only work with objects. Wrapper classes enable the use of primitive types as objects in these collections.

3. What is the difference between HashMap and HashTable?
HashMap
- Not thread-safe: HashMap is not synchronized and can be used in a single-threaded environment or synchronized externally if required.
- Faster: Since HashMap is not synchronized, it generally performs better in a single-threaded environment compared to Hashtable.
- Allows null keys and values: A HashMap can have one null key and multiple null values.
HashTable
- Thread-safe: Hashtable is synchronized, meaning it can be safely used in a multi-threaded environment.
- Slower: Synchronization adds overhead, which makes Hashtable slower in comparison to HashMap in a single-threaded context.
- Does not allow null keys or values: Hashtable throws NullPointerException if any key or value is null.

4. What is String pool in Java and why we need String pool? Explain String immunity.
The String Pool (also known as the String Intern Pool) in Java is a special area in the Heap Memory where String literals are stored. The main idea behind the String Pool is to optimize memory usage by storing only one copy of each distinct String literal.

When you create a String using a literal, Java checks the String Pool to see if an identical String already exists. If it does, it returns the reference to the existing String in the pool, rather than creating a new one. If it doesn't, it adds the new String to the pool.

Why Do We Need the String Pool?
- Memory Optimization: The String Pool avoids creating duplicate String objects in memory, reducing memory consumption. Since Strings are immutable and frequently used in Java programs (e.g., in loops or method calls), the String Pool optimizes memory by storing only one instance of each literal.

- Performance Improvement: Since identical String literals reference the same object in memory, it improves performance when comparing Strings using the == operator, as it checks for reference equality (which is faster) rather than content equality.

- Thread Safety: Since Strings are immutable (cannot be changed after they are created), sharing String objects from the pool between multiple threads is safe. There is no risk of one thread modifying the String object for another thread.

String immutability means that once a String object is created, its value cannot be changed. If you try to modify a String, a new String object is created instead.

5. Explain garbage collection?
Garbage Collection (GC) in Java is the process of automatically reclaiming memory by identifying and disposing of objects that are no longer in use or have no references pointing to them.Garbage collection is handled by the Java Virtual Machine (JVM).

6. What are access modifiers and their scopes in Java?
In Java, access modifiers are keywords used to define the visibility and accessibility of classes, methods, constructors, and variables. They control where certain elements can be accessed from, providing a way to encapsulate and protect the data. Java has four main access modifiers:
- public
- private
- protected
- default (also called package-private)

7. Explain final key word? (Filed, Method, Class)
8. Explan static keyword? (Filed, Method, Class). When do we usually use it?
9. What is the differences between overriding and overloading?
10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
11. What is the differences between super and this?
12. Explain how equals and hashCode work.
13. What is the Java load sequence?
14. What is Polymorphism ? And how Java implements it ?
15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
16. Compare interface and abstract class.
17. Write a factory pattern in code.
18. Write a adapter pattern in code.
19. Write singleton pattern in code, make sure your code is thread-safe.
20. design a parking lot parking lot (put the code to codingQuestions/coding1 folder, )
21. What are Queue interface implementations and what are the differences and when to use what? 