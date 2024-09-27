# Spring MVC Notes

### List of Annotations

- `@ComponentScan(basePackages = { "com.chuwa.training" })`: Scans the specified base package for Spring components.
- `@PutMapping(value = "/{id}", produces = "application/xml", consumes = "application/json")`: Handles HTTP PUT requests, consumes JSON, and produces XML.
- `@RestController`: Combines `@Controller` and `@ResponseBody` to simplify RESTful web services creation.

**Headers:**
- `Content-Type: application/json`: Specifies that the request body format is JSON.
- `Accept: application/json`: Indicates that the response format must be JSON. `*/*` allows any format.


### Tight Coupling vs Loose Coupling and Spring IOC

- **Tight Coupling**: Classes depend heavily on each other, making the code harder to maintain.
  
- **Loose Coupling**: Classes are independent, making the code more flexible and maintainable.

- **Spring IoC**: Manages object creation and dependencies to achieve loose coupling using Dependency Injection (DI).


### MVC Pattern

- **Model**: Manages data and business logic.
- **View**: Displays data to the user. (Template like JSP)
- **Controller**: Handles user input, updates the Model, and refreshes the View.

MVC promotes separation of concerns for better maintainability.


### Front-Controller

- A **Front-Controller** centralizes request handling, directing requests to the right handlers. In Spring MVC, `DispatcherServlet` acts as the Front-Controller.


### DispatcherServlet

- **DispatcherServlet** is the front-controller in Spring MVC. It receives all HTTP requests, dispatches them to the appropriate controller based on the request, and returns the response. It integrates with the View, Controller, and Model to process the request and generate a response.


### JSP (JavaServer Pages)

- **JSP**: A server-side technology used to create dynamic web pages. It allows embedding Java code in HTML to generate content dynamically.

### ModelAndView

- **ModelAndView**: A Spring MVC object that holds both the model (data) and the view (the presentation layer) to be rendered as a response.


### Servlet and Servlet Container

- **Servlet**: A Java class that handles HTTP requests and responses in web applications.
- **Servlet Container**: Manages servlet lifecycle, routing requests, and providing services like security and session management.

**Servlet Implementations**: Apache HttpComponents, Jetty.
**Servlet Containers**: Jetty, GlassFish, JBoss.
