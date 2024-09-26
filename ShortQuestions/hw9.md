## 1. List all of the annotations you learned from class and homework to annotations.md (your own cheatsheet):
/annotataionshw9.md


## 2.Compare Spring and Spring Boot? What are the benefits of Spring Boot?
- **Spring**: A powerful, comprehensive framework for building Java applications with advanced configuration options.
- **Spring Boot**:create stand-alone, production-grade Spring based applications with minimal Spring configuration


## 3.What is IOC and What is DI?
- IOC
  - Inversion of Control
  - A principle in software engineering where the control of objects or portions of a program is transferred to a container or framework.
- DI
  - dependencies injections
  - dependencies are injected into the class by a container rather than by the class itself


## 4. What is @ComponentScan?
- @ComponentScan: to tell Spring where to search for annotated component


## 5.What is @SpringBootApplication?
- @SpringBootApplication: Where the Spring starts


## 6.How many ways to define a bean? Provide code examples.
```aidl
@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}

@Component
public class MyBean {
}

```

## 7. What is the default bean name for @Component and @Bean? Also, compare @Component and @Bean
- default bean name: class name
- @Component: stereotype for any Spring-managed component
- @Bean: Declare a single bean.


## 8.Compare @Component, @Service, @Repository, @Controller.
- @Component: Generic and can be used across application component 
- @Service: Annotates classes at the service layer.
- @Repository: Annotates classes at the persistence layer, which will act as a database repository.
- @Controller: Annotates classes at the presentation layer, handling HTTP requests.

## 9.Explain @Autowired, @Qualifier, @Resource, and @Primary.
- @Autowired: Automatically wires bean by type.
- @Qualifier: Specifies which bean to wire when there are multiple of the same type.
- @Resource: Similar to @Autowired but wires by name.
- @Primary: Indicates that a bean should be given preference when multiple beans are qualified for autowiring.

## 10.How many annotations can we use to inject a bean?
- @Autowired:is the most common annotation used for autowiring beans by type.
- @Resource: This Java EE annotation allows you to inject beans by name.
- @Inject:is a standard Java annotation used like @Autowired but isn’t Spring-specific.
- @Qualifier: Used in conjunction with @Autowired to further qualify the injected bean when multiple candidates exist.

## 11.Explain and compare different types of dependency injection, their pros and cons, and use cases.
- Constructor Injection: Dependencies are provided through the constructor. 
    - Ensures immutability, easier to test, clear visibility of dependencies
    - Can lead to large constructors if many dependencies are needed
    - mandatory dependencies
- Setter Injection: Dependencies are provided through setter methods after the object is constructed.
  - Suitable for optional dependencies, more flexibility.
  - Allows the object to exist in an incomplete state if setters aren’t called.
  - Optional dependencies
- Field Injection: Dependencies are injected directly into fields
  - Easy to use, fewer lines of code.
  - Not ideal for testing
  - Suitable for dependencies in integration tests

## 12.If we have multiple beans for one type, how to set one as primary? And how does Spring IOC pick one bean to inject if no primary? Demo with code examples.
```aidl
@Component
@Primary
public class PrimaryBean implements CommonInterface {
}

@Component
public class SecondaryBean implements CommonInterface {
}

```
- We can use @Qualifier, if not, it will throw an exception


## 13.Compare BeanFactory and ApplicationContext in Spring framework?
- BeanFactory: 
  - accessing Spring beans, providing basic functionality for managing beans
  - Lazy
- ApplicationContext:
  - Extends BeanFactory with more enterprise-specific functionality
  - Eager

## 14. Explain bean scope in Spring IOC? List bean scopes with explanations and code examples if possible.
- Singleton:
```aidl
@Component
@Scope("singleton")
public class SingletonBean {
}

```
- Prototype
```aidl
@Component
@Scope("prototype")
public class PrototypeBean {
}

```

- Request
```aidl
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean {
}

```

- Session
```aidl
@Component
@Scope("session")
public class SessionScopedBean {
}
```

- Global Session
```aidl
@Component
@Scope("globalSession")
public class GlobalSessionScopedBean {

}
```


- Application
```aidl
@Component
@Scope("application")
public class ApplicationScopedBean {

}
```

- WebSocket
```aidl
@Component
@Scope("websocket")
public class WebSocketScopedBean {

}
```

## 15.Write a Spring application that registers and autowires beans, demonstrating different types of dependency injection, bean scopes, and dependency injection by type and by name when there's ambiguity in bean definition.



## 16. Explain the builder pattern with code examples.
```aidl
public class Product {
  private final String partA;
  private final String partB;
  private final String partC;

  private Product(Builder builder) {
      this.partA = builder.partA;
      this.partB = builder.partB;
      this.partC = builder.partC;
  }

  public static class Builder {
      private String partA;
      private String partB;
      private String partC;

      public Builder partA(String partA) {
          this.partA = partA;
          return this;
      }

      public Builder partB(String partB) {
          this.partB = partB;
          return this;
      }

      public Builder partC(String partC) {
          this.partC = partC;
          return this;
      }

      public Product build() {
          return new Product(this);
      }
  }
}

```





