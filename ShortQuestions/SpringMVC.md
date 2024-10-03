# Spring MVC
## 1. List all of the annotations you learned from this class session.
Check annotations.md

## 2. Explain tight coupling vs loose coupling and what does Spring IOC do?
### Tight Coupling
- Components are highly dependent on each other.
- Changes in one component can break others, making it hard to modify or extend the system.

### Loose Coupling
- Components are independent or minimally dependent on each other.
- Easier to modify, test, and maintain, as components can be changed without affecting others.

### Spring IOC (Inversion of Control)
- Decouples component creation from usage.
- Spring IOC container manages dependencies, injecting them where needed (via constructor, setter, or field injection).
- Promotes loose coupling by handling object creation and dependency management externally.


## 3. What is MVC pattern?
MVC pattern separates concerns for easier maintenance and testing.
- Model: Manages data and business logic.
- View: Displays data (UI).
- Controller: Handles user input, updates Model and View.
### Flow:
1. User interacts with View.
2. Controller updates Model.
3. Model updates View.

## 4. What is Front-Controller?
### Description:
- A design pattern where a single controller handles all requests for a web application.
- Centralizes request handling, improving control and reducing duplication.

### Purpose:
- Delegates requests to appropriate handlers (other controllers or services).
- Simplifies security, logging, and view management.

### Example:
- In Spring MVC, `DispatcherServlet` acts as the Front-Controller, routing requests to appropriate controllers.


## 5. Explain DispatcherServlet and how it works.
DispatcherServlet is the front-controller in Spring MVC that abstracts away tedious tasks:

### How It Works:
1. Intercepts incoming HTTP requests.
2. Determines the appropriate controller using handler mapping.
3. Invokes the controller method to process the request.
4. Returns a ModelAndView object with model data and the view name.
5. Resolves the view using a view resolver.
6. Renders the view and sends the response back to the client.


## 6. What is JSP and What is ModelAndView?
JSP is used for creating dynamic web content, while ModelAndView is a Spring MVC construct that combines model data and view information for rendering responses.

### JSP (JavaServer Pages)
- JSP is a technology used for creating dynamic web pages based on HTML, XML, or other document types.
- It allows embedding Java code directly into HTML using special tags.
- JSP files are compiled into servlets by the server, which can handle requests and generate responses.

### ModelAndView
- ModelAndView is a Spring MVC class used to represent both the model data and the view name in a single object.
- It contains:
    - Model: A map of data to be rendered in the view.
    - View Name: The name of the view to be rendered (e.g., a JSP page).
- Facilitates the separation of concerns by decoupling the data from the view logic.



## 7. Explain servlet and servlet container , name some servlet implementations and servlet containers other than tomcat
A servlet is a Java-based server-side component for handling requests, 
while a servlet container manages servlets' lifecycle and execution.
### Servlet
- A servlet is a Java program that runs on a server and handles client requests, typically via HTTP.
- It extends the capabilities of a web server by processing requests, generating dynamic content, and managing session data.

### Servlet Container
- A servlet container (or servlet engine) is a web server component that manages the lifecycle of servlets, including loading, instantiation, and handling requests.
- It provides an environment for servlets to run and interacts with the web server to handle client requests.

### Servlet Implementations
- Java EE Servlet API: The standard specification for creating servlets.

### Servlet Containers (other than Tomcat)
- Jetty: A lightweight and flexible servlet container.
- GlassFish: An open-source application server for Java EE.
- WildFly (formerly JBoss AS): A Java EE application server that includes servlet capabilities.
- Resin: A commercial Java EE application server with servlet support.


## 8. Clone this repo, and run it on your local:
[repo](https://github.com/CTYue/springmvc5-demo)
- Notice that you need to configure the Tomcat by yourself.
- Find out the APIs in controller and call some APIs, In slides, I also list some API.
- Remember to set up mysql database for this project. 
- Test APIs (controllers) in postman.