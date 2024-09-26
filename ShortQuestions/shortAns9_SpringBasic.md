
### List all of the annotations you learned from class and homework to annotations.md (your own cheatsheet).
```angular2html
Code in annotation.md file
```
### Compare Spring and Spring Boot? What are the benefits of Spring Boot?
Spingboot simplifies the development process of spring application by providing embeded server, simplified configuration thru annotation, reduce the need for mannual setup.
Spring provides dependencies injection(DI)and inversion of control(IoC) which allows developers write loosely coupled code


### What is IOC and What is DI?
dependencies: Dependencies are objects or components that a class or module needs to function correctly. Think of it as a main class needing an external object to perform its tasks—this external object is the dependency.


IoC: inversion of control, it  provides dependency injection, which promotes loose coupling between components.
(It creates objects, configures and assembles their dependencies.
IOC use DI(dependency injection) to manage the components.)

DI: is a process where object are given the things they need from outside, instead of manually creating themselves.
DI reduces the direction connection between parts of the code, allowing them work independently.
(chatgpt: Dependency Injection (DI) is a design pattern where an object receives its dependencies from an external source rather than creating them internally and manually. )
###  What is @ComponentScan?
provide pathway for spring to scan the bean definations and generate the beans
@ComponentScan is an annotation in Spring that tells the framework to scan specified packages for components, configurations, and beans to automatically detect and register them in the application context.
```angular2html

/*配置类*/
@Configuration
@ComponentScan("org.fionagu.springbeandemo")
public class SpringConfig {}
```
### What is @SpringBootApplication?
@SpringBootApplication is an annotation in Spring Boot that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan to set up a Spring Boot application, enabling automatic configuration, component scanning, and bean registration.
```angular2html

@SpringBootApplication
public class SpringBeanDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBeanDemoApplication.class, args);
    }

}
```

### How many ways to define a bean? Provide code examples.
1. Using @Component(@Repository,@Service,@Controller,@ComponentScan,@ControllerAdvice) Annotation(class level)
   The @Component annotation is used directly on a class to mark it as a Spring-managed bean. Spring automatically detects it through component scanning (@ComponentScan).
```angular2html
// BookDaoImpl.java
package org.fionagu.springbeandemo.dao.impl;

import org.fionagu.springbeandemo.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository // This is a specialized version of @Component
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("Book dao save ..");
    }
}

```
2. Using @Bean Annotation (method level)
@Bean use with @Configuration together// return an external objects
```angular2html
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean // Defines a DataSource bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds; //return an external objects
    }
}

```
3. XML Configuration
```angular2html
<!-- In applicationContext.xml -->
<bean id="bookDao" class="org.fionagu.springbeandemo.dao.impl.BookDaoImpl"/>

```

### What is the default bean name for @Component and @Bean? Also compare @Component and @Bean.
The default bean name for @Component and @Bean is the method or class name with the first letter lowercased.
1. @Component
- Default Bean Name: The default bean name is bookDaoImpl (the class name with the first letter lowercased).
- Usage: Automatically registers the class as a Spring-managed bean during component scanning.
```
@Repository // A specialized form of @Component
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("Book dao save ..");
    }
}

```
2. @Bean
```angular2html
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}

```
- Default Bean Name: The default bean name is dataSource (the method name with the first letter lowercased).

**why matter** 
1. Explicit Injection with @Qualifier:
```angular2html
@Autowired
@Qualifier("dataSource") // Bean name specified here
private DataSource dataSource;

```
2. Retrieving Beans from Application Context:
```angular2html
ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
DataSource dataSource = (DataSource) ctx.getBean("dataSource"); // Bean name used here

```


### Compare @Component and @Service, @Repository, @Controller.
@Component is generic, while @Service marks a service layer class, @Repository marks a data access object, and @Controller marks a web controller
### Explain @Autowired, @Qualifier, @Resource, and @Primary.
@Autowired automatically injects dependencies, @Qualifier specifies which bean to inject when multiple candidates exist, @Resource performs similar injection with name-based resolution, and @Primary designates a default bean to use when multiple options are available, resolving injection conflicts.
### How many annotations can we use to inject a bean?
@Autowired: Automatically injects beans by type.
@Qualifier: Works with @Autowired to specify which bean to inject when multiple candidates exist
@Resource injects by name, using the bean's identifier.
Name > Type 
```angular2html
// Service class using @Resource for injection by name
@Service
public class BookServiceImpl {
    @Resource(name = "bookDaoImpl2") // Injects the bean named "bookDaoImpl2"
    private BookDao bookDao;

    public void save() {
        bookDao.save();
    }
}
```
@Inject injects by type, similar to @Autowired, but it's part of Java CDI.

```angular2html
// Service class using @Inject for injection by type
@Service
public class AnotherBookService {
    @Inject // Injects the bean by type, similar to @Autowired
    private BookDao bookDao;

    public void save() {
        bookDao.save();
    }
}
```
@Primary designates a preferred bean when multiple candidates are present, automatically resolving which to inject when using @Autowired.
```angular2html
// First implementation of BookDao
@Repository
@Primary // Marks this bean as the primary choice for injection
public class PrimaryBookDaoImpl implements BookDao {
    public void save() {
        System.out.println("PrimaryBookDaoImpl save ..");
    }
}
```

### Explain and compare different types of dependency injection, their pros and cons, and use cases.
1. Field Injection
- Pros:

   - Simple and Concise: Easy to use with minimal boilerplate code.
   - Direct Access: Dependencies are injected directly into the fields.
- Cons:

  - Less Testable: Difficult to inject mocks for unit testing without reflection.
  - Hidden Dependencies: Dependencies are not explicitly shown in constructors, making the class less readable and harder to understand.
  - No Immutability: Dependencies can be altered, leading to potential state inconsistency.

```angular2html
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao; // Field Injection,where the bean is actually injected into the field of another class.

    public void save() {
        bookDao.save(); // Use the injected dependency
    }
}

```
2. Constructor Injection(recommend)
- Pros:

  - Explicit Dependencies: All dependencies are clear and required upon object creation, making the code more readable.
  - **Immutability**: Fields can be made final, ensuring they are set once and never changed.
  - **Better Testability**: Easily inject mock dependencies for testing.
- Cons:

  - More Boilerplate: Slightly more code due to the need for a constructor.
  - Circular Dependency Risk: Can lead to circular dependencies if not managed carefully.
```angular2html
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    // Constructor Injection
    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save() {
        bookDao.save(); // Use the injected dependency
    }
}

```
3. Setter Injection
- Pros:

  - Flexible: Allows changing dependencies after object creation, making it good for optional dependencies.
  - Readable and Manageable: Dependencies can be added or removed easily.
- Cons:

  - Inconsistent State: The object can exist in an incomplete state if the setter is never called.
  - Less Control Over Dependencies: Not as clear or strict as constructor injection, especially for required dependencies
```angular2html
@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    // Setter Injection
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save() {
        bookDao.save(); // Use the injected dependency
    }
}

```
### If we have multiple beans for one type, how to set one as primary? How does Spring IOC pick one bean to inject if no primary is set? Demonstrate with code examples.

Priority: @Qulifier > @Primary > By name 
```angular2html
@Repository("bookDao2")
@Primary
public class BookDaoImpl2 implements BookDao {
    //This annotation injects the literal string "BookDao impl 2" into the name field
    //without using a constructor or setter method.
    @Value("BookDao impl 2")
    private String name;
    public void save(){
        System.out.println("Book dao save .. 2 "+ name);
    }
}
```
```angular2html
@Service
public class BookServiceImpl implements BookService {
@Autowired
private BookDao bookDao;
public void  save(){
System.out.println("Book service save ..");
//
bookDao.save();// call BookDaoImpl2 save() method becuz of @Primary
    }
}
```
```angular2html
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        //how you access and utilize a bean that is already managed by the Spring container.
        BookService bookService = ctx.getBean(BookService.class);
        bookService.save();
    }
}
```



### Compare BeanFactory and ApplicationContext in the Spring framework.
**both** BeanFactory and ApplicationContext are IoC (Inversion of Control) **containers**
**AnnotationConfigApplicationContext is a type of ApplicationContext, but with a specific focus on configuration through annotations rather than XML.**


- BeanFactory is the basic Spring container that manages beans and handles dependency injection(Beans instantiation/wiring)
```angular2html
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryExample {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        BookDao bookDao = (BookDao) factory.getBean("bookDao");
        bookDao.save();
    }
}

```
(Beans instantiation/wiring,auto beanPostProcessor registration, application event publication)
- ApplicationContext builds on BeanFactory, adding more features like event handling, easier integration with web applications, and support for advanced configurations
```angular2html
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookDao bookDao = (BookDao) context.getBean("bookDao");
        bookDao.save();
    }
}

```
### Explain bean scope in Spring IOC. List bean scopes with explanations and code examples if possible.
- basic
@Scope("Singleton") default
@Scope("Prototype") Creates a New Instance of bean Each Time
- web
Request
Session
Application
websocket
### Write a Spring application that registers and autowires beans, demonstrating different types of dependency injection, bean scopes, and ambiguity in bean definitions. Demonstrate bean registration with both @Component and @Bean.
```angular2html
code in Coding/springBean-demo
```
### Explain the builder pattern with code examples.
 Builder Pattern is a creational design pattern that separates the construction of an object from its representation, enabling more flexible and readable object creation.
```angular2html
// Product class with private constructor
public class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    // Private constructor to prevent direct instantiation
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    // Static nested Builder class
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        // Builds and returns the final Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Computer myComputer = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .build();

        System.out.println("Computer built with: " + myComputer);
    }
}

```