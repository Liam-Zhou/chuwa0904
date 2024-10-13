# Spring AOP
## 1. Annotations
Check annotations.md

## 2. Reading
[Spring AOP interview questions](https://www.techgeeknext.com/spring-boot/spring-aop-interview-questions)

## 3. What is the Aspect Oriented Programming, explain it with detailed use cases?
Aspect Oriented Programming (AOP) is a programming approach that separates cross-cutting concerns (like logging, security, and transactions) from business logic. This enhances modularity and keeps the codebase clean.

### Use Cases of AOP
1. **Logging**: Automatically capture method calls, parameters, and execution time for auditing and debugging.

2. **Tracing**: Track the flow of requests through the application for better understanding and analysis.

3. **Exception Handling**: Centralize error management to provide consistent responses and logging for exceptions.

4. **Security Management**: Implement security checks before executing sensitive methods to enforce access control.

5. **Application Monitoring**: Collect performance metrics and health checks to monitor application behavior and identify issues.



## 4. What are the advantages and disadvantages of Spring AOP?
### Advantages
- **Separation of Concerns**: Promotes clean code by separating business logic from cross-cutting concerns (e.g., logging, security).
- **Reusability**: Allows the same aspect to be applied to multiple components, reducing code duplication.
- **Centralized Management**: Provides a single place to manage cross-cutting concerns, making maintenance easier.
- **Declarative Programming**: Uses annotations and configuration for ease of use, enhancing readability.

### Disadvantages
- **Complexity**: Introduces additional complexity in understanding and managing aspects, especially for beginners.
- **Performance Overhead**: Can introduce slight performance overhead due to additional method calls and processing.
- **Debugging Difficulty**: Debugging can be more challenging due to the indirect nature of aspect execution.


## 5. Explain the following concepts in your own words, you may include code snippets as part of your answer.
Aspects encapsulate cross-cutting concerns, pointcuts define where advice is applied, join points specify specific execution points in the application, and advice contains the actual logic that runs at those join points.
### 1. Aspect
An **Aspect** is a module that encapsulates a cross-cutting concern in an application. It is a class annotated with `@Aspect` that defines how certain operations should be executed at various join points in the application.

**Example:**
```java
@Aspect
public class LoggingAspect {
    // Aspect logic goes here
}
```

### 2. PointCut
A **PointCut** defines a set of join points where an advice should be applied. It uses expressions to specify the conditions for matching join points, typically method executions.

**Example:**
```java
@Pointcut("execution(* com.example.service.*.*(..))")
public void serviceMethods() {
    // Pointcut definition
}
```

### 3. JoinPoint
A **JoinPoint** represents a specific point in the execution of the program, such as a method call or field access. It provides context about the current method being executed, including method name, arguments, and target object.

**Example:**
```java
@Around("serviceMethods()")
public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("Before method: " + joinPoint.getSignature().getName());
    Object result = joinPoint.proceed(); // Proceed with the method execution
    System.out.println("After method: " + joinPoint.getSignature().getName());
    return result;
}
```

### 4. Advice
**Advice** is the action taken by an aspect at a specific join point. There are different types of advice, such as `@Before`, `@After`, `@AfterReturning`, and `@AfterThrowing`, each defining when to execute the advice relative to the join point.

**Example:**
```java
@Before("serviceMethods()")
public void beforeAdvice(JoinPoint joinPoint) {
    System.out.println("Executing before method: " + joinPoint.getSignature().getName());
}
``` 

## 6. How do we declare a pointcut, can we declare it without annotating an empty method? Name some expressions to do it.
### Declaring a Pointcut
A **pointcut** in Spring AOP can be declared using the `@Pointcut` annotation. This annotation can be associated with a method, which serves as the pointcut definition. The method itself does not need to contain any code, as it only defines the pointcut expression.

### Declaring without an Empty Method
While the conventional way is to annotate a method (which may be empty), you can also use pointcut expressions directly in advice annotations without needing a separate method. 
However, defining them with a method improves readability and reusability.
```java
@Pointcut("execution(* com.example.service.*.*(..))")
public void serviceMethods() {
    // This method can be empty
}
```

### Expressions to Declare Pointcuts
1. **Execution of all methods in a specific package:**
   ```java
   @Pointcut("execution(* com.example.service.*.*(..))")
   ```

2. **Execution of all methods with a specific return type:**
   ```java
   @Pointcut("execution(String com.example.service.*.*(..))")
   ```

3. **Execution of methods with a specific parameter type:**
   ```java
   @Pointcut("execution(* com.example.service.*.add*(com.example.model.*))")
   ```

4. **Execution of any public method in a class:**
   ```java
   @Pointcut("execution(public * com.example.service.*.*(..))")
   ```

5. **Execution of methods annotated with a specific annotation:**
   ```java
   @Pointcut("@annotation(com.example.annotation.Loggable)")
   ```


## 7. Different Types of Advices in Spring AOP.
1. **@Before**  
   Runs before the join point. Used for actions like logging or validation before method execution.

2. **@After**  
   Executes after the join point, regardless of its outcome. Suitable for cleanup tasks or logging.

3. **@AfterReturning**  
   Runs after the join point completes successfully. Used to modify the returned value or perform logging.

4. **@AfterThrowing**  
   Executes if the join point throws an exception. Useful for handling errors and logging exceptions.

5. **@Around**  
   Wraps around the join point, allowing control over when to proceed with execution. Ideal for performance monitoring or modifying input/output.

