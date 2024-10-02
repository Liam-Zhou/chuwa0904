Homework 10

### 2. Tight Coupling vs. Loose Coupling and Spring IoC
 - Tight Coupling:
  - Tight coupling means that classes or modules are heavily dependent on each other. A change in one class will likely affect another, making the system more rigid and difficult to maintain or scale. It decreases flexibility and testability.

 - Loose Coupling:
  - Loose coupling refers to a design where classes or components are minimally dependent on each other, allowing them to be modified or replaced independently. This increases flexibility and testability, making the system easier to manage and evolve.

 - Spring IoC (Inversion of Control):
  - The Spring IoC (Inversion of Control) container manages the dependencies between objects in your application. It promotes loose coupling by using dependency injection, where the framework is responsible for creating and injecting the required dependencies rather than having the classes manage their own dependencies. This leads to better modularity and maintainability.

### 3. What is the MVC Pattern?
 - The Model-View-Controller (MVC) pattern is an architectural design pattern that separates an application into three main logical components:
  - Model: Represents the application's data or business logic. It directly manages the data, logic, and rules of the application.
  - View: The presentation layer, which displays data to the user and sends user inputs to the controller.
  - Controller: Handles the user input, interacts with the model, and updates the view accordingly. It acts as an intermediary between the Model and the View.

### 4. What is Front-Controller?
 - A Front Controller is a design pattern where a single controller handles all requests for a web application. Rather than having multiple controllers for different requests, the front controller centralizes control logic, allowing better control of navigation, authentication, and request processing. In a web application, the front controller typically delegates the requests to different handlers or dispatchers.

### 5. Explain DispatcherServlet and How It Works
 - The DispatcherServlet is the front controller in Spring MVC. It is responsible for intercepting incoming HTTP requests and dispatching them to the appropriate handler (Controller). Here's how it works:

  1. The client sends a request to the web server.
  2. The request is intercepted by the DispatcherServlet, which is configured in web.xml or by annotations.
  3. The DispatcherServlet consults the HandlerMapping to determine which controller is responsible for handling the request.
  4. The request is forwarded to the appropriate controller.
  5. The controller processes the request and returns a ModelAndView object containing the data and the view name.
  6. The ViewResolver resolves the view name to an actual view (like JSP), and the DispatcherServlet renders the view.

### 6. What is JSP and What is ModelAndView?
 - JSP (JavaServer Pages):
  - JSP is a server-side technology that allows developers to create dynamic web pages using Java. It is primarily used for displaying data to the user in a web application by embedding Java code directly in HTML. JSP can be compiled into servlets, allowing server-side processing before the response is sent to the client.

 - ModelAndView:
  - In Spring MVC, ModelAndView is an object that holds both the model data and the view name. It is used to return both the data (Model) and the view (JSP or another view technology) to be rendered. The controller returns a ModelAndView object after processing a request.

### 7. Explain Servlet and Servlet Container, Name Some Servlet Implementations and Servlet Containers Other Than Tomcat
 - Servlet:
  - A Servlet is a Java class used to handle HTTP requests and generate dynamic web content. It runs on a server, processes client requests (like form submissions), and generates responses (typically HTML). Servlets are the building blocks of Java-based web applications.

 - Servlet Container:
  - A Servlet Container (or web container) is a part of a web server or application server that manages and executes servlets. It provides the necessary environment for servlets to run, including handling HTTP requests, managing lifecycle methods (init(), service(), destroy()), and performing resource management.

 - Servlet Implementations:
    Apache Tomcat
    Jetty
    GlassFish
    JBoss/WildFly
    Resin
    Servlet Containers (other than Tomcat):

 - Some servlet containers, besides Apache Tomcat, are:
    Jetty: A lightweight, embeddable web server often used in conjunction with frameworks like Spring Boot.
    GlassFish: An application server for the Java EE platform.
    JBoss/WildFly: A powerful, scalable application server that supports servlets and other Java EE technologies.
    Resin: Another application server that can serve servlets and JSP.
    