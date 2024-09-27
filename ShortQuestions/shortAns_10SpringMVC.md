### List all of the annotations you learned from this class session.
```angular2html
code in annotation.md file
```
### Explain tight coupling vs loose coupling and what does Spring IOC do?
**Tight coupling** often occurs when you manually instantiate objects within a class, making that class directly dependent on the specific implementations of those objects. This approach reduces flexibility and reusability,

**Loose coupling** involves using interfaces or abstractions to initialize objects, rather than creating them directly within a class.

- how does IoC help for lose coupling 
IoC allows objects to receive their dependencies from an external source rather than creating them internally, reducing tight coupling and making the code more modular, flexible, and easier to test.
1. External Dependency Management: In traditional tightly coupled systems, objects create and manage their dependencies directly, making the code rigid and difficult to modify. IoC flips this control, allowing an external IoC container to manage object creation and dependency injection, classes not need to directly handle their dependencies.
2. Dependency Injection:By injecting dependencies from an external source rather than having classes instantiate them, IoC reduces the direct dependency between classes and specific implementations. 
3. Encourages Use of Abstractions:IoC promotes coding to interfaces rather than concrete classes. Since classes depend on abstractions (interfaces) rather than specific implementations, they become more flexible and interchangeable. The actual implementations can be easily replaced without modifying the class that uses them.
4. Improved Testability: IoC allows for easier testing because dependencies can be mocked or stubbed during tests. This isolation of components means that each part can be tested independently without needing the actual dependency implementations, further promoting loose coupling.



### What is MVC pattern?
The MVC (Model-View-Controller) pattern is a design architecture that separates an application into three interconnected components: Model (data and business logic), View (user interface), and Controller (handles input and updates the Model and View)


### What is Front-Controller?
The Front Controller pattern serves as a centralized entry point that handles all incoming requests, it is DispatcherServlet.



### Explain DispatcherServlet and how it works.
- what: DispatcherServlet is the central component of the Spring MVC framework that acts as the Front Controller
- how: 
  -  mapping incoming http request to handler/controller
  - coordinating responses between models and views

### What is JSP and What is Model And View?
- JSP (JavaServer Pages) is a technology that helps create dynamic web pages using Java code inside HTML
- Model and View in MVC mean the Model handles the data and logic, and the View shows the data to the user

### Explain servlet and servlet container, name some servlet implementations and servlet containers other than tomcat.
- Servlet is a Java program that handles requests and responses on a web server
- Servlet Container is the server environment that runs servlets, managing their lifecycle and communication.
- servlet implementations: HttpServlet
- servlet containers:  Jetty, GlassFish, and WildFly.

### Run springMVC demo project and call on postman 
![Local Image](Image/imghw10_1.png)
![Local Image](Image/imghw10_2.png)