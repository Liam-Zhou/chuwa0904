### 1. List all of the annotations you learned from class and homework to annotaitons.md (your own
   cheatsheet).
### 2. Compare Spring and Springboot? What are the benfits of Srpingboot?  

Spring Boot is basically an extension of the Spring framework, which eliminates the boilerplate configurations required
for setting up a Spring application.

It takes an opinionated view of the Spring platform, which paves the way for a faster and more efficient development ecosystem.

### 3. What is IOC and What is DI?

Inversion of control, dependency injection.  
Inversion of Control is a principle in software engineering which transfers the control of objects or portions of a 
program to a container or framework.  
Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object’s dependencies.

### 4. What is @CompnonentScan ?

With Spring, we use the @ComponentScan annotation along with the @Configuration annotation to specify the packages that 
we want to be scanned. @ComponentScan without arguments tells Spring to scan the current package and all of its sub-packages.  

### 5. What is @SpringbootApplication ?

The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan 
with their default attributes  

### 6. How many ways to define a bean? Provide code examples.

@ComponentScan, @Component, @Repository, @Service, @Controller, @Bean

### 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean .

@Component: The default bean name is the class name of the annotated component with the first letter lowercased.  
@Bean: The default bean name is the method name where the @Bean annotation is applied.  

### 8. Compare @component and @service , @repository , @controller ?



### 9. Explain @Autowired , @Qualifier , @Resource and @Primary ?  

Starting with Spring 2.5, the framework introduced annotations-driven Dependency Injection. The main annotation of 
this feature is @Autowired. It allows Spring to resolve and inject collaborating beans into our bean.  

### 10. How many annotaitons we can use to inject a bean?

A bean can only have one injectable constructor. A bean can have multiple initializer methods.  

11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to
    inject if no primay, demo with code examples.
13. Compare BeanFactory and ApplicationContext in Spring framework?
14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
15. Write a Spring application that registers and autowires beans,
    Demo different types of dependency injection
    Demo bean scopes.
    Demo dependency injection by type and by name, when there's ambiguity in bean definition.
    Demo bean registration by both @Component and @Bean
16. Explain builder pattern with code examples