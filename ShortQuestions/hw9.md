# hw9 Spring-Basic

## 2. Compare Spring and Springboot? What are the benfits of Srpingboot?

| **Spring** | **Spring Boot** |
|------------|-----------------|
| A comprehensive framework for Java applications, offering tools for dependency injection, data access, security, transaction management, and more. | An extension of Spring framework. Built on top of the Spring framework but focuses on simplifying development. |
| Requires significant configuration (XML or annotations). | Offers auto-configuration and convention-over-configuration. |
| Developers need to manually manage embedded servers or external containers. | Comes with an embedded server (Tomcat, Jetty, etc.), so no need to manually deploy to external servers. |

In summary, Spring Boot reduces boilerplate and configuration effort with default setups.

## 3. What is IOC and What is DI?

1. **IOC (Inversion of Control)**: Spring uses **IOC container** to control the instantiation and the initialization of Java object.

2. **DI (Dependency Injection)**: **DI** is a specific implementation of the **IOC** principle. Spring uses **DI** to create an object through configuration, rather than using `new` to instantiate an object.

## 4. What is `@CompnonentScan`?

Define where the spring need to scan the bean definations and generate the beans.
```Java
@Configuration  // Configuration class
@ComponentScan("com.spring6review")  // Start Component Scan at specific package
public class SpringConfig {
}
```

## 5. What is `@SpringbootApplication`?

`@SpringbootApplication` = `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`

`@Configuration`, which declares a class as the source for bean definitions.

`@EnableAutoConfiguration`, which allows the application to add beans using classpath definitions.

`@ComponentScan`, which directs Spring to search for components in the path specified.

## 6. How many ways to define a bean? Provide code examples.

1. Constructor Injection
    ```Java
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    ```

2. Setter Injection
    ```Java
    private UserService userService;

    @Autowired
    public void settUserService(UserService userService) {
        this.userService = userService;
    }
    ```

3. Field Injection
    ```Java
    @Autowired
    private UserService userService;
    ```

**Constructor Injection** is better:
1. Immutability: Constructor injection allows you to create immutable objects, where the object's state does not change after it is created.
2. Testability: Constructor injection makes unit testing easier. You can pass mock dependencies directly via the constructor without relying on the framework or reflection, making tests more explicit and easier to control.
3. Avoids NullPointerExceptions: Since dependencies are provided at construction time, it guarantees that the object has all the required dependencies right from the start, avoiding runtime issues like NullPointerException that can occur with setter or field injection.

## 7. What is default bean name for `@Component` and `@Bean`? Also compare `@Component` and `@Bean`.

The default bean name for a class annotated with `@Component` is the class name with the first letter in lowercase.
```Java
@Component
public class MyService { }
```
Bean name of `MyService` class is `myService`.

The default bean name for a method annotated with @Bean is the method name itself.
```Java
@Bean
public MyService myService() {
    return new MyService();
}
```
Bean name of `myService` method is `myService`.

| Feature                | `@Component`                                     | `@Bean`                                           |
|------------------------|--------------------------------------------------|---------------------------------------------------|
| **Purpose**             | Used to indicate that a class is a Spring-managed component or bean. | Used to explicitly declare a bean inside a `@Configuration` class. |
| **Usage**               | Applied at the class level.                      | Applied at the method level within a configuration class. |
| **Automatic Scanning**  | Can be automatically discovered during component scanning via `@ComponentScan`. | Not discovered via component scanning; explicitly defined in Java configuration. |
| **Configuration Flexibility** | Less flexible; typically used for simple bean definitions where the class itself is managed by Spring. | More flexible; allows full control over how beans are created (e.g., conditional logic, arguments). |
| **When to Use**         | When the bean is self-contained and doesn’t need complex configuration. | When you need more control over the bean creation process or need to instantiate third-party libraries or legacy code. |
| **Bean Scope**          | Can use annotations like `@Scope` to set the scope of the bean. | Can also define the scope directly using method-level configuration. |
| **Dependency Injection**| Dependencies are injected via annotations like `@Autowired` within the class. | Dependencies can be passed as method parameters, giving more flexibility in wiring. |

## 8. Compare @component and @service , @repository , @controller?

`@Component`, `@Repository`, `@Service`, and `@Controller` are all used to create bean object. But they are used in different class levels. `@Component` is a general annotation; `@Repository` is used in DAO layer; `@Service` is used in Service layer; `@Controller` is used in Controller layer.

## 9. Explain `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`?

`@Autowired`, `@Qualifier`, and `@Primary` are by class type.

`@Autowired` is used for automatic dependency injection in Spring.

If there are multiple implementation (same type), using `@Qualifier` can search by name.

`@Primary` is used to give a default bean preference when multiple beans of the same type are present, so Spring will choose the primary one if no @Qualifier is specified.

Priority: @Qualifier > @Primary

`@Resource` is by name.

## 10. How many annotaitons we can use to inject a bean?

`@Autowired`, `@Qualifier`, `@Primary`, `@Resource`, `@Inject`

## 11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.

1. **Constructor Injection**

    Pros:

    Immutability: Since dependencies are provided when the object is created, the class can be made immutable (final fields), ensuring that dependencies cannot change during the object's lifetime.

    Mandatory Dependencies: All required dependencies must be provided at object creation, preventing incomplete or invalid objects.

    Testability: Easy to test since you can inject mock dependencies through the constructor.

    Enforces good design: Constructor injection encourages smaller, more focused classes, as large constructors indicate that the class has too many responsibilities.

    Cons:

    Boilerplate Code: If there are many dependencies, the constructor can get long, leading to more boilerplate.

    Less flexibility: Adding or changing dependencies might require modifying the constructor, which could impact other parts of the application.

2. **Setter Injection**

    Pros:

    Optional Dependencies: Useful when dependencies are optional or have reasonable default values. The object can function even if some dependencies are not set.

    Flexibility: You can change or update dependencies after the object has been created by calling the setter method.
    Simpler Constructors: Keeps the constructor clean, especially when the class has many optional dependencies.

    Cons:

    Mutability: The object is mutable, meaning dependencies can change after the object is created, which can introduce unexpected behavior or bugs.

    Potential Incomplete Objects: There is no guarantee that all required dependencies will be provided, as the setters could be forgotten or skipped.

    Not suitable for mandatory dependencies: Can lead to errors if required dependencies are not injected before use.

3. **Field Injection**

    Pros:

    Minimal boilerplate: Simple and concise, requiring less code since no setter or constructor is needed.

    Easier to implement: Quick and easy, especially for small applications or demos.

    Cons:

    Harder to test: It is difficult to inject mock objects for unit testing since the field is private, making field injection less testable.
        
    Lack of transparency: Field injection hides the dependencies of a class, making it less clear what dependencies the class relies on.

    Immutability issues: Field injection inherently creates mutable objects, leading to potential issues in multi-threaded environments.

    Tightly coupled to the Spring framework: Unlike constructor or setter injection, field injection makes it harder to use the class outside of the Spring context (for example, in plain Java).

## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.

```Java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Configuration
public class AppConfig {

    @Bean
    public Car car1() {
        return new Car("Toyota");
    }

    @Bean
    @Primary
    public Car car2() {
        return new Car("Honda");
    }
}

@Service
class CarService {
    private Car car;

    // Injecting the primary bean
    @Autowired
    public CarService(Car car) {
        this.car = car;
    }

    public void printCar() {
        System.out.println("Injected Car: " + car.getName());
    }
}

class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

```

In this example, car2() will be injected by default because it is marked as @Primary. If you remove the @Primary annotation, you will need to explicitly specify which bean to inject using @Qualifier.

## 13. Compare BeanFactory and ApplicationContext in Spring framework?

**BeanFactory**: The fundamental interface in Spring for accessing the Spring container. It provides the basic functionality to manage and configure beans.

**ApplicationContext**: A more advanced and feature-rich interface, extending BeanFactory. It offers additional enterprise-level services.

| **BeanFactory** | **ApplicationContext** |
|-----------------|------------------------|
| The fundamental interface in Spring for accessing the Spring container. It provides the basic functionality to manage and configure beans. | A more advanced and feature-rich interface, extending BeanFactory. It offers additional enterprise-level services. |
| Beans are created lazily by default. | Beans are created eagerly by default. |

## 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.

In Spring IoC (Inversion of Control), bean scope defines the lifecycle and visibility of a bean. It determines how the bean is created, how many instances are created, and how it is shared.

1. Singleton: Single bean instance for the entire Spring context (default).
2. Prototype: A new bean instance for every request.
3. Request: One instance per HTTP request (Web only).
4. Session: One instance per HTTP session (Web only).

## 15. Checking at `/Coding/hw9`

Demo different types of dependency injection: `Coding/hw9/AnnotationPractice/src/main/java/com/IOCannotation/DITypes`

Demo bean scopes: `Coding/hw9/AnnotationPractice/src/main/java/com/IOCannotation/BeanScopes`

Demo dependency injection by type and by name, when there's ambiguity in bean definition: `Coding/hw9/AnnotationPractice/src/main/java/com/IOCannotation/DIAutowired`
    prove: For Priority, `@Qualifier` > `@Primary`

Demo bean registration by both @Component and @Bean: `Coding/hw9/AnnotationPractice/src/main/java/com/IOCannotation/ComponentAndBean`

| Feature | `@Bean` | `@Component` |
|---------|---------|--------------|
|Annotation Location | Method level (usually in `@Configuration` class) | Class level |
| Bean Registration | Explicit declaration, registered via method return value | Implicit registration via classpath scanning (`@ComponentScan`) |
| Use Case | When you need manual control over bean creation, initialization, or complex configuration | For simple, auto-scanned beans |
| Dependency Injection | Can manually build dependencies in the method (through method parameters or other ways) | Dependencies are injected via constructor, field, or setter injection
| Commonly Used With | Used with `@Configuration` | Used with `@ComponentScan` |

## 16. Explain builder pattern with code examples.

The Builder Pattern is a creational design pattern used to construct complex objects step by step. It separates the construction of an object from its representation, allowing you to create different types and representations of an object using the same construction process.

Item.java
```Java
public interface Item {
   public String name();
   public Packing packing();
   public float price();    
}
```
Packing.java
```Java
public interface Packing {
   public String pack();
}
```
Wrapper and Bottle implement Packing interface
```Java
public class Wrapper implements Packing {
   @Override
   public String pack() {
      return "Wrapper";
   }
}
```
```Java
public class Bottle implements Packing {
 
   @Override
   public String pack() {
      return "Bottle";
   }
}
```
Burger.java
```Java
public abstract class Burger implements Item {
 
   @Override
   public Packing packing() {
      return new Wrapper();
   }
 
   @Override
   public abstract float price();
}
```
ColdDrink.java
```Java
public abstract class ColdDrink implements Item {
 
    @Override
    public Packing packing() {
       return new Bottle();
    }
 
    @Override
    public abstract float price();
}
```
VegBurger.java
```Java
public class VegBurger extends Burger {
 
   @Override
   public float price() {
      return 25.0f;
   }
 
   @Override
   public String name() {
      return "Veg Burger";
   }
}
```
ChickenBurger.java
```Java
public class ChickenBurger extends Burger {
 
   @Override
   public float price() {
      return 50.5f;
   }
 
   @Override
   public String name() {
      return "Chicken Burger";
   }
}
```
Coke.java
```Java
public class Coke extends ColdDrink {
 
   @Override
   public float price() {
      return 30.0f;
   }
 
   @Override
   public String name() {
      return "Coke";
   }
}
```
Pepsi.java
```Java
public class Pepsi extends ColdDrink {
 
   @Override
   public float price() {
      return 35.0f;
   }
 
   @Override
   public String name() {
      return "Pepsi";
   }
}
```
Meal.java
```Java
import java.util.ArrayList;
import java.util.List;
 
public class Meal {
   private List<Item> items = new ArrayList<Item>();    
 
   public void addItem(Item item){
      items.add(item);
   }
 
   public float getCost(){
      float cost = 0.0f;
      for (Item item : items) {
         cost += item.price();
      }        
      return cost;
   }
 
   public void showItems(){
      for (Item item : items) {
         System.out.print("Item : "+item.name());
         System.out.print(", Packing : "+item.packing().pack());
         System.out.println(", Price : "+item.price());
      }        
   }    
}
```
MealBuilder.java
```Java
public class MealBuilder {
 
   public Meal prepareVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new VegBurger());
      meal.addItem(new Coke());
      return meal;
   }   
 
   public Meal prepareNonVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new ChickenBurger());
      meal.addItem(new Pepsi());
      return meal;
   }
}
```
main, BuilderPatternDemo.java
```Java
public class BuilderPatternDemo {
   public static void main(String[] args) {
      MealBuilder mealBuilder = new MealBuilder();
 
      Meal vegMeal = mealBuilder.prepareVegMeal();
      System.out.println("Veg Meal");
      vegMeal.showItems();
      System.out.println("Total Cost: " +vegMeal.getCost());
 
      Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
      System.out.println("\n\nNon-Veg Meal");
      nonVegMeal.showItems();
      System.out.println("Total Cost: " +nonVegMeal.getCost());
   }
}
```
```
Veg Meal
Item : Veg Burger, Packing : Wrapper, Price : 25.0
Item : Coke, Packing : Bottle, Price : 30.0
Total Cost: 55.0


Non-Veg Meal
Item : Chicken Burger, Packing : Wrapper, Price : 50.5
Item : Pepsi, Packing : Bottle, Price : 35.0
Total Cost: 85.5
```



