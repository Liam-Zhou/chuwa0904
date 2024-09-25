# HW9

## 1. Annotations learned

## 2. Compare Spring and SpringBoot. What are benefits of Springboot?

Spring is a comprehensive framework, provides dependency injection, aspect-oriented programming, data access, transaction management, and more. Springboot is a simplification of Spring with minimal configuration, uses annotation and auto configuration.

### Spring

- Spring requires XML configuration manually
- Spring requires complex setting up, configurations for project. 

### Springboot

- Quick and easy setup, provides opinionated defaults
- Minimal configuration, use annotations and auto configuration
- Support embedded server

## 3. What is IOC and what is DI

**IOC (Inverse of Control)**, is a concept to simplify the java software development. When using the Spring, IOC is core idea, giving responsibility of the object creation and management to the framework. Reduce Coupling, increase maintainability and testability

**DI**: is a implementation of IoC, through constructor, attribute or method injection to object. `@Component` `@Bean` `@Autowired`

## 4. What is @ComponentScan

@ComponentScan annotation along with the @Configuration annotation to specify the packages that we want to be scanned. *@ComponentScan* without arguments tells Spring to scan the current package and all of its sub-packages.

In one package, the annotation just need to be placed once

~~~java
@ComponentScan
// if do not specify scope, it scans this package and subpackage
@ComponentScan(basePackages = {"com.example.package1", "com.example.package2"})
~~~



## 5. What is @SpringbootApplication

It is a convenience annotation that combines several important annotations to simplify the configuration and setup of a spring boot application.

It serves as the main entry point of spring application and is typically based on the main class.

It is a composite annotation include: @Configuration, @EnableAutoConfiguration, @ComponentScan

It also handles most setup for the application

## 6. How many ways to define a bean

1. Use annotations
   - @Component: Generic strereotype of annotation
   - @Service: A specialization of @Component used for service-layer beans
   - @Repository: Specialization for DAO layer
   - @Controller: Specialization for web controller
   - @Bean: Define a bean explicitly in a `@Configuration` class using the @Bean
2. Java Configuration

~~~java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean(); // Create and return the bean
    }
}
~~~

3. XML

~~~xml
<beans>
    <bean id="myBean" class="com.example.MyBean" />
</beans>
~~~

4. @ConfigurationProperties

~~~java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class MyConfig {
    private String name;
    private int value;
    
    // Getters and setters
}
~~~



## 7. What is default bean name for @Component and @Bean, Compare them

@Bean == @Component == <bean></bean> in xml



##  8. Compare @Component and @Service, @repository, @controller

Technically, they are just alias, but each carries specific meanings and responsibilities



## 9. Explain @Autowired, @Qualifier, @Resource and @Primary

**Autowired** auto injects dependencies into a spring bean

**Qualifier** specify the exact bean to inject

**Resource** inject dependency by name `@Resource(name = "myRepository") // Specify the bean name`

**Primary**, bean under primary annotation will be preferred when multiple beans of the same type exist

## 10. How many annotations we can use to inject a bean?

1. @Autowired
2. @Qualifier
3. @Resource
4. @Inject
5. @Primary
6. @Value
7. @ConfigurationProperties Binds external configuration properties to a bean, making it easy to manage complex configuration settings
8. @Bean, used in @Configuration class to explicitly define a bean and its dependencies.



## 11. Explain and compare different types of dependency injection, pros and cons and use cases

Dependency Injection (DI) is a design pattern used to implement Inversion of Control (IoC), allowing for better separation of concerns and easier testing in software applications.

**Constructor Injection**, **Setter Injection**, and **Interface Injection**. 

More detail example at **Coding Folder**.

**Constructor Injection**

- Dependencies are provided through a class constructor.

- Dependencies are typically final field, ensuring they cannot be changed after the object is constructed.

- ~~~java
  public class MyService {
      private final MyRepository myRepository;
  
      @Autowired
      public MyService(MyRepository myRepository) {
          this.myRepository = myRepository;
      }
  }
  ~~~

**Setter Injection**

Dependencies are provided through setter methods after the object is constructed.

~~~java
public class MyService {
    private MyRepository myRepository;

    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
~~~



**Interface Injection**

~~~java
public interface MyService {
    void injectRepository(MyRepository myRepository);
}

public class MyServiceImpl implements MyService {
    private MyRepository myRepository;

    @Override
    public void injectRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
~~~





### Comparison 

| Type            | Pros                                              | Cons                                          | Use Cases                                     |
| --------------- | ------------------------------------------------- | --------------------------------------------- | --------------------------------------------- |
| **Constructor** | Valid state guarantee, immutability, easy testing | Cluttered constructors for many dependencies  | Mandatory dependencies, service classes       |
| **Setter**      | Flexibility, easy for optional dependencies       | Possible invalid state, complex configuration | Optional dependencies, property configuration |
| **Interface**   | Client control, flexibility                       | Less common, more boilerplate                 | Rarely used in Spring                         |

### Conclusion

## 12. If we have multiple beans for one type, how  to set one is primary? and how Spring IOC picks one bean to inject if no primary, demo with code

Just add @primary annotation.

If not primary, it will match by name.

~~~java
@Component("hibernateChuwa")
public class HibernateChuwa implements JpaChuwa {
    @Override
    public void printMessage() {}
}

@Component
public elipseChuwa implements JpaChuwa {
    @Override
    public void printMessage() {}
}

// by type
@Autowired 
private HibernateChuwa hibernate;
// by name if multiple name for one type
@Autowired
private JpaChuwa hibernateChuwa;
@Autowired
private JpaChuwa eclipseChuwa;

~~~



##  13. Compare BeanFactory and ApplicationContext in Spring framework

Both are interfaces used for managing beans in the spring IoC. ApplicationContext is more advanced and recommended.

**BeanFactory** is the simplest container that provides the basic support for dependency injection and instantiation of beans.

- Features:
  - Lazy initialization of beans: beans are created only when requested
  - Minimal overhead; lightweight and suitable for simple applications
  - Lack of advanced features like event propagation, internationalization, and AOP(Aspect-Oriented Programming)

**ApplicationContext** is more advance container that extends `BeanFactory`. It provides additional features.

- Eager initialization of beans by default(can be configured to lazy)
- Support for internationalization (message resource)
- Support for event propagation (application events)
- Ability to access application layers (like WebApplicationContext)
- Integration with AOP
- Automatic bean scanning using annotation



## 14. Explain bean scope in Spring IOC? List bean scopes with explanation and code examples.

Basic(通⽤)

- Singleton by default（在IOC Container中只存在⼀个实例）

  - ~~~java
    import org.springframework.stereotype.Component;
    
    @Component
    public class SingletonBean {
        public SingletonBean() {
            System.out.println("SingletonBean instance created");
        }
    }
    ~~~

    

- prototype （在每次获取bean时，都创建⼀个新的实例, 即使⽤context.getBean()⽅法 或 @Autowired时）

  - ~~~java
    import org.springframework.context.annotation.Scope;
    import org.springframework.stereotype.Component;
    
    @Component
    @Scope("prototype")
    public class PrototypeBean {
        public PrototypeBean() {
            System.out.println("PrototypeBean instance created");
        }
    }
    ~~~

Web (只有web application)

- Request (New instance for each HTTP request.)
  - @Scope("request")
- Session (New instance for each HTTP session.)
  - @Scope("session")
- Application (One shared instance for the entire application context.)
  - @Scope("application")
- Websocket (New instance for each WebSocket session.)
  - @Scope("websocket")



## 15. Write a Spring application that registers and Autowires  beans

This part is in the Coding Folder.

```java
//output
BeanType
BeanByName A
BeanByNameB
ConstructorInject Data from repository
Field Inject Data from repository
Setter Inject Data from repository
Created with Bean and Configuration
InterfaceInject: Data from repository
```



## 16. Explain Builder pattern with Code exmaple

Allow to construct complex object step by step. It separates the construction of a complex object from its representation.

~~~java
public class Pizza {
    private final String size; // Required
    private final boolean cheese; // Optional
    private final boolean pepperoni; // Optional
    private final boolean mushrooms; // Optional

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
    }

    public static class Builder {
        private final String size; // Required
        private boolean cheese; // Optional
        private boolean pepperoni; // Optional
        private boolean mushrooms; // Optional

        public Builder(String size) {
            this.size = size;
        }

        public Builder withCheese() {
            this.cheese = true;
            return this;
        }

        public Builder withPepperoni() {
            this.pepperoni = true;
            return this;
        }

        public Builder withMushrooms() {
            this.mushrooms = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", mushrooms=" + mushrooms +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza.Builder("Large")
                .withCheese()
                .withPepperoni()
                .build();

        Pizza pizza2 = new Pizza.Builder("Medium")
                .withMushrooms()
                .build();

        System.out.println(pizza1);
        System.out.println(pizza2);
    }
}
// output
//Pizza{size='Large', cheese=true, pepperoni=true, mushrooms=false}
//Pizza{size='Medium', cheese=false, pepperoni=false, mushrooms=true}

~~~

