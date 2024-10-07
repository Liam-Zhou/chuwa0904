### List all of the annotations you learned from class and homework to annotaitons.md

###  Briefly reading: https://www.techgeeknext.com/spring-boot/spring-boot-aop-interview-questions

### What is Aspect Oriented Programming, explain it with detailed use cases?
Aspect Oriented Programming (AOP) is a way of programming that separates tasks like logging, security, or managing transactions from the main code, so you can handle these tasks separately using aspects.
### What are the advantages and disadvantages of Spring AOP?
- Advantages: It helps in separating concerns, reduces code duplication, and makes code easier to maintain.
- Disadvantages: It can increase complexity and make debugging harder due to the hidden nature of aspects.
### Explain the following concepts in your own words, you may include code snippets as part of your answer:

- Aspect: An aspect is a class that contains cross-cutting concerns, like logging or security, which can be applied to various methods. In my project, MethodLogAspect is the aspect that handles logging. It applies logging logic before and after methods annotated with @MethodLog.
- PointCut: 2 ways of defininh pointcut:
1. Using a separate @Pointcut method (traditional way)
   The method MethodLog() contains the Pointcut expression.
   The @Around advice refers to this Pointcut using "MethodLog()"
```angular2html
// Define pointcut for methods annotated with @MethodLog
    @Pointcut("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public void MethodLog() {}

    // Around advice to handle both before and after execution
    @Around("MethodLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //logic
    }
```
2. Directly in the @Around advice
   This is an inline Pointcut definition, where the Pointcut is specified directly inside the @Around annotation.

````angular2html
@Around("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    //  logging logic
}

````
- JoinPoint: The JoinPoint (or ProceedingJoinPoint in the case of @Around) is passed as a parameter inside the aspect logic. It gives you access to details about the method where the aspect (advice) is being applied.
```angular2html
@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    // Around advice with a pointcut targeting methods annotated with @MethodLog
    @Around("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Using JoinPoint to get method details
        String methodName = proceedingJoinPoint.getSignature().toShortString();  // Method signature
        Object[] methodArgs = proceedingJoinPoint.getArgs();  // Method arguments
        
        // Log before method execution
        log.info("Before - Method: {}, Params: {}",
                 methodName, 
                 JSON.toJSONString(methodArgs));

        // Proceed with method execution
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        // Log after method execution
        log.info("After - Method: {}, Result: {}, Execution Time: {} ms",
                 methodName,
                 JSONObject.toJSONString(result), 
                 executionTime);

        return result;
    }
}

```
- Advice: Advice is the action taken at the join point, such as logging before, after, or around a method.
### How do we declare a pointcut, can we declare it without annotating an empty method? Name some expressions to do it.
2 ways of defininh pointcut:
1. Using a separate @Pointcut method (traditional way)
   The method MethodLog() contains the Pointcut expression.
   The @Around advice refers to this Pointcut using "MethodLog()"
```angular2html
// Define pointcut for methods annotated with @MethodLog
    @Pointcut("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
    public void MethodLog() {}

    // Around advice to handle both before and after execution
    @Around("MethodLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //logic
    }
```
2. Directly in the @Around advice
   This is an inline Pointcut definition, where the Pointcut is specified directly inside the @Around annotation.

````angular2html
@Around("@annotation(org.fionagu.springsecuritydemo.log.MethodLog)")
public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    //  logging logic
}

````

### Compare different types of advices in Spring AOP.
key diff:
1. @Before and @AfterReturning: These advices are passive. They do not control or modify when or whether the target method runs
2. @Around: This advice is active. It can control when (or if) the target method runs because you have to call proceed() manually to execute the method.
3. ProceedingJoinPoint(@Around) VS JoinPoint(@Before and @AfterReturning)
   - in @Around, If you don't call proceed(), the target method won’t execute.
   - JoinPoint gives you access to method details (method name, arguments, etc.), but it cannot control method execution.
   - ProceedingJoinPoint is a subclass of JoinPoint, so it provides the same method details, but with the added ability to call proceed() to control method execution.

- @Before: runs before a method,
```angular2html
@Before("MethodLog()")
public void doBefore(JoinPoint joinPoint) {
    log.info("Request Method: {}, Request Param: {}", 
        joinPoint.getSignature(), 
        JSON.toJSONString(joinPoint.getArgs()));
}

```
- @AfterReturning: runs after a method returns successfully,
```angular2html
@AfterReturning(returning = "o", pointcut = "MethodLog()")
public void afterReturning(Object o) {
    log.info("Response Result: {}", JSONObject.toJSONString(o));
}

```
- @AfterThrowing: If you want to log or handle exceptions (errors) that occur in the method, you use @AfterThrowing
```angular2html
@AfterThrowing(pointcut = "MethodLog()", throwing = "ex")
public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    log.error("After Throwing - Exception in method: {}, Exception: {}", joinPoint.getSignature(), ex.getMessage());
}

```
- @Around: can run both before and after the method and even modify the method's behavior.
```angular2html
@Around("MethodLog()")
public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    // Before the method
    log.info("Before - Method: {}, Params: {}", proceedingJoinPoint.getSignature(), JSON.toJSONString(proceedingJoinPoint.getArgs()));

    // Proceed with method execution
    Object result = proceedingJoinPoint.proceed();

    // After the method
    log.info("After - Result: {}", JSON.toJSONString(result));

    return result;
}

```
### On top of your Spring application which you did in Assignment #11(Spring Security),
- Implement a customized logger using Spring AOP, your logger should be able to log your code and also external code.
- Your AOP logger should log method execution time, Rest API request details and response details.
- Your AOP logger should log with all possible joint points (before method execution, after method execution etc...)
- You should bind JointPoints with your AOP code directly, instead of binding it with an empty method.
- Be ready to demo your implementation and prove it works in class.
```angular2html
code in Coding/SpringAOPSecurity-demo project 
```