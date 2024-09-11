## 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
### 1. Encapsulation
```
public class Encap{
    private int id;
    private String email;
    private String name;

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setEmail(String newEmail){
        email = newEmail;
    }

    public void setName(String newName){
        name = newName;
    }
}
public class RunEncap{
    public static void main(String args[]){
        Encap en = new Encap();
        en.setId(001);
        en.setName("Alex");
        en.setEmail("alexakame@gmail.com");

        System.out.println("user_id: "+en.getId());
        System.out.println("user_name: "+en.getName());
        System.out.println("user_email: "+en.getEmail());
    }
}
```
### 2. Polymorphism
```
public abstract class Animal {
    public abstract void makeSound();
}
public class Dog extends Animal{
    @Override 
    public void makeSound(){
        System.out.println("bark");
}

}
public class RunPoly {
    public static void main(String args[]){
    Animal dog = new Dog();
    dog.makeSound();
}
}

```
### 3. Inheritance
```
public class BankAccount {
    public String holderName;
    public int accountBalance;
    public int accountNumber;

    public int getBalance(){
        return accountBalance;
    }

    public String getDetails(){
        return holderName;
    }

    public void withdraw(int amount){
        accountBalance -= amount;
    }

    public void deposit(int amount){
        accountBalance += amount;
    }
    
}
public class SavingsAccount extends BankAccount {
    public int interestAmount;
    public void addInterest(int amount){
        interestAmount += amount;
    }
    
}
public class RunInherit {
    public static void main(String args[]){
        BankAccount account = new SavingsAccount();
        account.accountBalance = 1000;
        account.deposit(300);
        System.out.println("Amount after deposit: "+account.accountBalance);
        account.withdraw(20);
        System.out.println("Amount after withdraw: "+account.accountBalance);
    }
}

```
## 2. What is wrapper class in Java and Why we need wrapper class?
Wrapper can convert primitive data types to objects, and data structures like ArrayList only stores objects not primitive types, so we need wrapper class to convert primitive types to objects to help those data structures to store them.

## 3. What is the difference between HashMap and HashTable?
HashMap is asyn while HashTable is sync. HashMap allows null while HashTable doesn't allow null. HashMap is not thread safe because multiple threads can operate at the same time, while HashTable is thread safe because only one thread can operate at one time.

## 4. What is String pool in Java and why we need String pool? Explain String immunity.
String pool is the heap memory to store String constants in Java.
Java String is immutable because String with the same content are stored in the same address in the String pool, so if a String is modified, it means a new String is created.

## 5. Explain garbage collection?
Java program is complied to bytecode which can run on JVM, and this process creates objects on the heap, and some objects may no longer needed in the end, so garbage collector will find those objects and delete them to free up memory.

## 6. What are access modifiers and their scopes in Java?
Examples of access modifiers are private, protected, public and default. Private declaratioins are only visible in the specified class. Protected declarations are only visible within the package or all subclasses. Public declarations are visible everywhere. Default declarations are only visible within the package.

## 7. Explain final key word? (Filed, Method, Class)
Final keyword can be used to define constant variables, and the variables are immutable and cannot be changed.
Final keyword can be used in the method declaration to prevent override.
Final keyword can be used in the class declaration to prevent inheritance and make class immutable.

## 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?
Static variables only have one instance. 
Static methods can be called directly by its name without creating any objects of its class.
Static keyword can be used in nested classes, it can be instantiated without an instance of the outer class.
We often use static keyword to share data across the class, get access to nested class, and using helper methods.

## 9. What is the differences between overriding and overloading?
Overlaoding happens at compile time and have better performance, while overriding happens at runtime and have less performance.
Private and final methods can be overloaded, while they cannot be overriding.
Method return type doesn't matter for overloading, while method return type must be the same for overriding.
Arguments must be different for method overloading, and arguments are the same for method overriding.
Overloading is done in the same class, overriding is done in child classes.

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
Method signature is the combination of the method's name and its list of parameters. There can't be two methods with the same signiture in one class. 
Overloading needs different method signitures, and overriding must have the same method signiture as the base class.

## 11. What is the differences between super and this?
This refers to the current instance of the class, super refers to the base instance of the current object.

## 12. Explain how equals and hashCode work.
Equals compares the reference of two objects, and hashCode compares the value of two objects.
Two objects can be true in hashCode but false in equals.
Two objects which are true in equals must be true in hashCode.

## 13. What is the Java load sequence?
Class loading, static initialization, instance initialization, constructor initialization.

## 14. What is Polymorphism ? And how Java implements it ?
Polymorphism means the same obejct with different forms and behaviors. Java has polymorphism types of method overloading (compile time) and method overriding (run time).

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
Encapsulation is to bind the data and the methods to manipulate the data in a single class. We can define variables as private in a class, and outer class can get and modify those private data by using get and set functions. We need encapsulation to hide some sensitive information from users.

## 16. Compare interface and abstract class.
Interface class is the blueprint that one class must follow, the abstract methods must be implemented by other class, allow multiple inheritance.
Abstract class can be extended by other class, it has abstract methods with or without implementation, doesn't allow multiple inheritance.

## 17. Write a factory pattern in code.
```
interface Animal {
    void makeSound();
}
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("bark");
    }
}

class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("miao");
    }
}

class Cow implements Animal {
    @Override
    public void makeSound() {
        System.out.println("meow");
    }
}
class AnimalFactory {
    
    public Animal getAnimal(String animalType) {
        if (animalType == null) {
            return null;
        }
        
        if (animalType.equalsIgnoreCase("Dog")) {
            return new Dog();
        } else if (animalType.equalsIgnoreCase("Cat")) {
            return new Cat();
        } else if (animalType.equalsIgnoreCase("Cow")) {
            return new Cow();
        }
        
        return null;
    }
}
public class FactoryPatternDemo {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();

        Animal animal1 = animalFactory.getanimal("Dog");
        animal1.makeSound();

        Animal animal2 = animalFactory.getanimal("Cat");
        animal2.makeSound();

        Animal animal3 = animalFactory.getanimal("Cow");
        animal3.makeSound();
}
}
```

## 18. Write a adapter pattern in code.
```
interface TypeC {
    void charge();
}
class  Lightning {
    void show(){
        System.out.println("Charging via Lightning);
    }
}
class TypeCAdapter implements TypeC{
    private Lightning light;
    public TypeCAdapter(Lightning light){
        this.light = light;
    }
    public void display(){
        light.show();
    }
}
```
## 19. Write singleton pattern in code, make sure your code is thread-safe.
```
public class Singleton {

private static Singleton instance = new Singleton();

private Singleton() {
}

public static Singleton getInstance() {
return instance;
}
}
Singleton is NOT null
Singleton.getInstance(); 
```

## 21. What are Queue interface implementations and what are the differences and when to use what?
Queue is a first in first out data structure, and we can push element into the end of queue, and pop the first element from the queue. 
We can use queue to implement priority queue, linked list, and array deque. 
