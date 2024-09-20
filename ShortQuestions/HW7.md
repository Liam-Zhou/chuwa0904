# HomeWork 7

Yuhang Li

# 1.List all of the Spring data related annotations you learned and explain its usage. #

### Core Spring Boot Annotations

1. **@SpringBootApplication**:
   - Marks the main class of a Spring Boot application.
   - Combines three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
   - This enables automatic configuration, scanning for components, and registering them as Spring beans.
2. **@ComponentScan**:
   - Used to specify the base packages to scan for Spring components like `@Controller`, `@Service`, and `@Repository` annotated classes.
   - Automatically included by `@SpringBootApplication`.
3. **@Configuration**:
   - Marks a class as a source of bean definitions.
   - Allows the class to define beans using methods annotated with `@Bean`.
4. **@Bean**:
   - Used inside a `@Configuration` class to define a Spring-managed bean.
   - The method annotated with `@Bean` returns an object that will be registered in the Spring application context.
5. **@EnableAutoConfiguration**:
   - Enables Spring Boot's auto-configuration feature, which automatically configures beans based on the classpath and other settings.
   - Typically included in `@SpringBootApplication`.

### Web-Related Annotations

1. **@RestController**:
   - A convenience annotation that combines `@Controller` and `@ResponseBody`.
   - Used to create RESTful web services by returning JSON or XML directly from controller methods.
2. **@RequestMapping**:
   - Maps HTTP requests to specific handler methods in a controller.
   - Can be used at the class and method levels to specify paths, HTTP methods (`GET`, `POST`, etc.), and other request details.
3. **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping**:
   - Specialized annotations for HTTP method-specific request mapping (e.g., `@GetMapping` for `GET` requests).
   - A more readable alternative to using `@RequestMapping` with a method attribute.
4. **@PathVariable**:
   - Used to bind URI template variables to method parameters in controller methods.
5. **@RequestParam**:
   - Used to extract query parameters or form data from an HTTP request and bind them to method parameters.
6. **@RequestBody**:
   - Used in a controller method to bind the body of an HTTP request to a method parameter (typically a POJO).
   - Primarily used in RESTful APIs to handle JSON or XML request bodies.
7. **@ResponseBody**:
   - Indicates that the return value of a method should be used as the response body in a web request.
   - Typically used in REST APIs.
8. **@CrossOrigin**:
   - Enables Cross-Origin Resource Sharing (CORS) for controller methods or globally across the application.

### Data and Transaction Management Annotations

1. **@Transactional**:
   - Marks a method or class to be wrapped in a transaction.
   - Ensures that methods execute within a database transaction, with automatic commit or rollback based on success or failure.
2. **@EnableJpaRepositories**:
   - Enables JPA repositories, allowing Spring Data JPA to create repository implementations automatically.
3. **@EntityScan**:
   - Specifies the base packages to scan for JPA entity classes.
   - Often used alongside `@EnableJpaRepositories`.
4. **@EnableTransactionManagement**:
   - Enables transaction management in the Spring Boot application.
   - Allows `@Transactional` to work in the application.

### Dependency Injection Annotations

1. **@Autowired**:
   - Used to inject Spring-managed beans into a class, either via constructor, field, or setter injection.
   - It allows Spring to resolve and inject collaborating beans automatically.
2. **@Qualifier**:
   - Used alongside `@Autowired` to resolve ambiguity when multiple beans of the same type are available.
   - Specifies which bean should be injected.
3. **@Value**:
   - Used to inject values from application properties or environment variables into fields or method parameters.
4. **@Component, @Service, @Repository, @Controller**:
   - Stereotype annotations that define a class as a Spring-managed component.
   - `@Component` is a generic Spring bean, while `@Service` (business logic), `@Repository` (data access), and `@Controller` (web layer) are more specific types.

### Security and Validation Annotations

1. **@PreAuthorize**:
   - Used to define security restrictions on methods, allowing access based on user roles, permissions, or conditions.
2. **@Valid**:
   - Triggers validation on a method parameter, often used with form data or `@RequestBody` objects.
   - Works with the Java Bean Validation API (e.g., Hibernate Validator).
3. **@EnableWebSecurity**:
   - Enables Spring Security's web security configuration.
   - Usually used with a `WebSecurityConfigurerAdapter` class to define custom security settings.



# 2.What is DTO, VO, Payload, PO, model, DAO?

### 1. **DTO (Data Transfer Object)**

- **Definition**: A DTO is an object that is used to transfer data between layers (usually between client and server or different services) in a simplified manner.

- **Purpose**: Its main purpose is to carry only necessary data, often reducing the amount of information passed between systems. DTOs usually don't contain any business logic.

- **Usage**: Used in RESTful APIs or any kind of service where data needs to be sent or received. For example, you can have a `UserDTO` with only user-specific fields when transmitting data from the server to the client.

- **Example:**

  ```java
  public class UserDTO {
      private String username;
      private String email;
  }
  ```

### 2. **VO (Value Object)**

- **Definition**: A VO is an object that represents a value and does not have an identity. It is immutable, meaning that once created, its values cannot change.

- **Purpose**: VOs are typically used for domain modeling where you need to capture some concept, like money or address, where the individual values don’t have distinct identities.

- **Usage**: Common in domain-driven design (DDD), where the value object represents values like `Money`, `DateRange`, `Address`, etc.

- **Example:**

  ```java
  public class AddressVO {
      private final String street;
      private final String city;
      private final String zipCode;
      
      // Constructor, Getters, but no Setters (immutability)
  }
  ```

### 3. **Payload**

- **Definition**: A payload refers to the actual data being transmitted over the network or through APIs. It typically represents the body of a message (such as in HTTP requests or responses) that contains useful information.

- **Purpose**: The term is often used to describe the data in a request/response body that is being sent to or received from an API.

- **Usage**: Payload often refers to JSON or XML data in the body of an HTTP request or response.

- **Example:**

  ```json
  {
      "username": "john_doe",
      "email": "john@example.com"
  }
  ```

  This JSON object could be considered the payload of an API request.

### 4. **PO (Persistent Object)**

- **Definition**: A PO is an object that represents a row in a database table and is usually mapped to it by an ORM (Object-Relational Mapping) framework like Hibernate. These objects are stored in and retrieved from the database.

- **Purpose**: POs are meant to persist the application’s state into a database, representing the actual data being stored.

- **Usage**: It is commonly associated with database entities or records.

- **Example:**

  ```java
  @Entity
  public class UserPO {
      @Id
      private Long id;
      private String username;
      private String email;
  }
  ```

### 5. **Model**

- **Definition**: A model represents a business domain object that holds the logic or data structure of the system. It could represent both persistent and non-persistent data depending on the use case.

- **Purpose**: Models are part of the business logic layer and define the structure of domain objects used within the application.

- **Usage**: A model can be a POJO (Plain Old Java Object) representing business entities, such as `User`, `Order`, `Product`, etc. It could also be mapped to database objects or be used for application logic.

- **Example:**

  ```java
  public class UserModel {
      private String username;
      private String passwordHash;
      private List<Order> orders;
      
      // Getters, Setters, and Business Logic Methods
  }
  ```

### 6. **DAO (Data Access Object)**

- **Definition**: A DAO is an object or an interface that provides an abstraction for data operations (such as `create`, `read`, `update`, `delete`) from a database or other persistence store.

- **Purpose**: It separates the business logic from the underlying data access logic, allowing for easier maintenance and testing of database-related code.

- **Usage**: The DAO pattern is widely used in applications that interact with databases, as it abstracts the actual database operations and isolates them from the rest of the code.

- **Example:**

  ```java
  public interface UserDAO {
      UserPO findById(Long id);
      void save(UserPO user);
      void delete(Long id);
  }
  ```



# 3.What is @JsonProperty("description_yyds")?

The `@JsonProperty("description_yyds")` annotation is part of the **Jackson** library in Java, which is used for JSON (de)serialization. This annotation maps a JSON property to a Java class field or method.

### Usage:

- When deserializing JSON (i.e., converting JSON into Java objects), the annotation tells Jackson to bind the `description_yyds` property in the JSON to a specific field in the Java class.
- When serializing Java objects (i.e., converting Java objects to JSON), it tells Jackson to use the name `description_yyds` for the field in the resulting JSON.

### Example:

```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    
    @JsonProperty("description_yyds")
    private String description;

    // Constructor, getters, setters
    public Product(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

### JSON Example:

#### JSON input:

```json
{
  "description_yyds": "This is the product description."
}
```

#### Java object deserialization:

This JSON would be deserialized into a `Product` object where the `description` field will contain the value `"This is the product description."`.

#### JSON output:

When the `Product` object is serialized back into JSON, the field will appear as `description_yyds`:

```json
{
  "description_yyds": "This is the product description."
}
```

### Why Use `@JsonProperty`?

1. **Custom JSON Field Name**: You might have a field name in Java that doesn't match the expected JSON property name. `@JsonProperty` allows you to map the two.
2. **Legacy Systems**: If you're dealing with an external system where the field names are fixed (e.g., `description_yyds`), you can map your more conventional Java fields to these properties without changing your Java class structure.
3. **Improved Readability**: It can help make code more readable by using intuitive or standard naming conventions in Java while adhering to a required JSON structure.

In this case, `"description_yyds"` in the JSON would be mapped to the `description` field in the Java class.



# 4.Explain the purpose of following dependency? 

The given dependency in your **Maven** project refers to the **Jackson Databind** library, which is part of the **Jackson** suite used for handling JSON serialization and deserialization in Java.

### Dependency Breakdown:

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.13.3</version>
  <scope>compile</scope>
</dependency>
```

### Purpose of the Dependency:

- **Group ID (`com.fasterxml.jackson.core`)**: This identifies the organization or group that provides the library. `com.fasterxml.jackson.core` is the base group for all Jackson core modules.
- **Artifact ID (`jackson-databind`)**: This refers to the **Jackson Databind** module, which provides functionality for converting Java objects to and from JSON. It combines the core `streaming` and `annotations` modules to map JSON data directly to Java objects (and vice versa).
- **Version (`2.13.3`)**: Specifies the version of the **Jackson Databind** library you are using. Here, version `2.13.3` ensures compatibility with other dependencies and fixes specific to this version.
- **Scope (`compile`)**: This indicates that the library is required at both compile-time and runtime. The `compile` scope means it will be included in your project’s build and bundled with the output artifacts.



# 5.What is `spring-boot-starter`?

**Spring Boot Starter** is a dependency that provides a ready-to-use setup for common frameworks and features in Spring Boot applications. Instead of manually specifying individual dependencies for various functionalities (e.g., web, data, security, etc.), Spring Boot **starters** offer pre-configured dependency bundles. This makes it easier to get started quickly by including only the necessary dependencies.

**Purpose**: Starters simplify dependency management by grouping related dependencies into one package, avoiding the need for developers to explicitly add each individual library required for a specific functionality.

### What dependencies are included in the `spring-boot-starter-web`?

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

The **`spring-boot-starter-web`** is a starter that helps you build **web applications**, including RESTful services using **Spring MVC**. It comes pre-configured with the following dependencies:

1. **Spring MVC**: Provides support for creating web applications using the Model-View-Controller (MVC) pattern.
   - **Spring Web** (`spring-web`, `spring-webmvc`) for building web applications and handling RESTful requests.
2. **Jackson**: Automatically handles JSON serialization and deserialization.
   - **Jackson Databind** (`jackson-databind`) for converting Java objects to/from JSON in REST API communication.
3. **Embedded Servlet Container**: Includes an embedded web server, usually **Tomcat** (though Jetty and Undertow can be configured).
   - **Tomcat** (`spring-boot-starter-tomcat`) as the default web server.
4. **Validation**: Provides validation features, such as `@Valid` and `@NotNull`, for input validation.
   - **Hibernate Validator** (`hibernate-validator`) for data validation.
5. **Logging**: Provides logging support with default configurations for different levels (e.g., `INFO`, `ERROR`).
   - **SLF4J** (Simple Logging Facade for Java)
   - **Logback** (`spring-boot-starter-logging`) as the default logging framework.

This starter allows you to quickly build RESTful web services or full-fledged MVC-based web applications.

### Other Common Spring Boot Starters:

1. **`spring-boot-starter-data-jpa`**:
   - Provides support for **JPA** (Java Persistence API) and **Spring Data JPA** to easily interact with relational databases.
   - Includes dependencies like Hibernate ORM for persistence and transaction management.
2. **`spring-boot-starter-security`**:
   - Adds support for **Spring Security**, enabling authentication and authorization for your application.
3. **`spring-boot-starter-thymeleaf`**:
   - Starter for using **Thymeleaf** as the template engine in web applications for generating dynamic content.
4. **`spring-boot-starter-test`**:
   - Provides testing support, including **JUnit**, **Spring Test**, and **Mockito**, to enable unit testing and integration testing.
5. **`spring-boot-starter-actuator`**:
   - Adds production-ready features like health checks, metrics, and monitoring.
6. **`spring-boot-starter-redis`**:
   - Provides integration with **Redis** for caching, messaging, or other Redis-based operations.
7. **`spring-boot-starter-mail`**:
   - Enables email sending functionality using JavaMail.
8. **`spring-boot-starter-quartz`**:
   - Provides integration with **Quartz** for scheduling tasks.
9. **`spring-boot-starter-amqp`**:
   - Provides support for working with **RabbitMQ** and other AMQP (Advanced Message Queuing Protocol) systems.

------

### Summary:

- **`spring-boot-starter-web`** is a dependency starter in Spring Boot that bundles various libraries to quickly build web applications (including RESTful services), along with embedded server support, JSON handling, validation, and logging.
- **Starters** like `spring-boot-starter-data-jpa`, `spring-boot-starter-security`, `spring-boot-starter-test`, and others provide pre-configured dependencies, making it easy to set up various functionalities without having to manage individual libraries.



# 6.Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? Could you list CRUD by this style?

The `@RequestMapping` annotation in **Spring MVC** is used to map web requests (HTTP requests) to handler methods in a controller class. It allows you to specify the URL pattern, HTTP method, headers, and more for a particular endpoint.

#### Breakdown of `@RequestMapping(value = "/users", method = RequestMethod.POST)`:

- **`value = "/users"`**: This specifies the URL path or endpoint (`/users`) that the method will handle. In this case, the endpoint `/users` will be mapped to the handler method.
- **`method = RequestMethod.POST`**: This specifies the HTTP method that the request must use. In this case, it handles **POST** requests, typically used for creating new resources.

### Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Logic to create a new user
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
```

Here, the method handles POST requests at `/users/`, which is commonly used for creating new users. The `@RequestBody` annotation binds the request's body to the `user` parameter, allowing you to receive the data needed to create the user.

------

### CRUD Operations Using `@RequestMapping`

You can implement the standard **CRUD** operations (Create, Read, Update, Delete) by combining different HTTP methods (POST, GET, PUT, DELETE) and paths with the `@RequestMapping` annotation:

#### 1. **Create** (HTTP POST)

- **Purpose**: To create a new resource (e.g., a new user).

- Example:

  ```java
  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public ResponseEntity<User> createUser(@RequestBody User user) {
      // Logic to create a new user
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
  ```

#### 2. **Read** (HTTP GET)

- **Purpose**: To read or retrieve resources.

- **Example for fetching all users**:

  ```java
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public List<User> getAllUsers() {
      // Logic to retrieve all users
      return userService.getAllUsers();
  }
  ```

- **Example for fetching a specific user by ID**:

  ```java
  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
      // Logic to retrieve a user by ID
      return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
  }
  ```

#### 3. **Update** (HTTP PUT)

- **Purpose**: To update an existing resource.

- Example:

  ```java
  @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
      // Logic to update user information
      return new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
  }
  ```

#### 4. **Delete** (HTTP DELETE)

- **Purpose**: To delete a resource.

- Example:

  ```java
  @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
      // Logic to delete a user by ID
      userService.deleteUser(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  ```

------

### 

# 7.What is ResponseEntity? Why do we need it?

`ResponseEntity` is a class in Spring that represents the entire HTTP response, including the **status code**, **headers**, and the **body**. It is a flexible and powerful way to fully control the HTTP response when handling requests in a Spring RESTful web service.

#### Key Features:

- **HTTP Status**: You can specify the HTTP status code (e.g., `200 OK`, `201 CREATED`, `404 NOT FOUND`) that should be returned to the client.
- **Response Body**: The body of the response, typically the data (e.g., JSON) that you want to return.
- **HTTP Headers**: You can include custom headers in the response.

By using `ResponseEntity`, you can control what gets returned to the client in terms of the response body, status code, and headers, rather than just returning the data object itself.



### Why Do We Need `ResponseEntity`?

Without `ResponseEntity`, the Spring controller will return just the response body (e.g., a JSON object) with a default status code (typically `200 OK` for successful responses). However, in many cases, you need more control over the response:

1. **Custom Status Codes**: You may want to return specific HTTP status codes like `201 Created`, `404 Not Found`, or `500 Internal Server Error` to indicate the result of an operation (e.g., resource created, resource not found, etc.).
2. **Custom Headers**: If you need to include additional metadata in the HTTP response, such as `Content-Type`, `Location`, or custom headers, `ResponseEntity` allows you to set them.
3. **More Control Over the Response**: It provides more fine-grained control over the entire HTTP response, including how errors or successful outcomes are communicated to the client.

### Examples

#### 1. **Creating a New Resource** (HTTP Status `201 CREATED`):

```java
@PostMapping("/posts")
public ResponseEntity<Post> createPost(@RequestBody Post postRequest) {
    Post postResponse = postService.save(postRequest);
    return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
}
```

- **Explanation**: Here, the `POST` request creates a new post. The response includes the created post and returns the status **`201 Created`**, which tells the client that a new resource has been successfully created.

#### 2. **Returning an Existing Resource** (HTTP Status `200 OK`):

```java
@GetMapping("/posts/{id}")
public ResponseEntity<Post> getPostById(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    return ResponseEntity.ok(post); // Same as new ResponseEntity<>(post, HttpStatus.OK)
}
```

- **Explanation**: When the resource (a post) is successfully retrieved, the `ResponseEntity.ok()` method is a shorthand to return the resource with HTTP status `200 OK`.

#### 3. **Handling Errors or Not Found Scenarios**:

```java
@GetMapping("/posts/{id}")
public ResponseEntity<Post> getPostById(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    if (post == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No body, just status 404
    }
    return ResponseEntity.ok(post); // Return the post with 200 OK
}
```

- **Explanation**: If the post is not found (i.e., `null`), we return a response with **HTTP status 404 (Not Found)**. This way, the client knows that the requested resource does not exist.

#### 4. **Adding Custom Headers**:

```java
@GetMapping("/posts/{id}")
public ResponseEntity<Post> getPostById(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Custom-Header", "CustomHeaderValue");
    
    return new ResponseEntity<>(post, headers, HttpStatus.OK); // Response with headers
}
```

- **Explanation**: Here, we add custom headers to the response. The client will receive the post along with the specified headers and the **`200 OK`** status.

------

### Different Ways to Use `ResponseEntity`

1. **With a Body and Status Code**:

   ```java
   new ResponseEntity<>(postResponse, HttpStatus.OK);
   ```

2. **With a Body and Status Code (Shortened)**:

   ```java
   ResponseEntity.ok(postService.getPostById(id)); // Equivalent to 200 OK
   ```

3. **Without a Body (Only Status Code)**:

   ```java
   new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 without a body
   ```

4. **With Custom Headers**:

   ```java
   HttpHeaders headers = new HttpHeaders();
   headers.add("Header-Name", "HeaderValue");
   new ResponseEntity<>(postResponse, headers, HttpStatus.OK);
   ```

------

### 

# 8.What is ResultSet in jdbc? And describe the flow how to get data using JDBC.

`ResultSet` in **JDBC (Java Database Connectivity)** is an object that represents the result of a query executed against a database. When you execute a SQL query (such as `SELECT`) using JDBC, the data returned from the query is stored in a `ResultSet` object. This object provides methods to iterate through the data row by row and retrieve values for each column.

#### Key Features of `ResultSet`:

- It acts as a pointer to a table of data retrieved from the database.
- It is **forward-only** by default, meaning you can iterate through the rows one at a time, in one direction.
- `ResultSet` provides methods like `next()`, `getString()`, `getInt()`, `getDate()`, etc., to access the data from each row.

#### Example:

```java
ResultSet rs = statement.executeQuery("SELECT id, name FROM users");
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    System.out.println("ID: " + id + ", Name: " + name);
}
```

- **`executeQuery()`**: Executes the SQL query and returns a `ResultSet` object.
- **`rs.next()`**: Moves the pointer to the next row in the result set.
- **`rs.getInt("id")`**: Retrieves the value of the `id` column for the current row.
- **`rs.getString("name")`**: Retrieves the value of the `name` column for the current row.

------

### Flow: How to Get Data Using JDBC

The process of interacting with a database using JDBC involves the following steps:

#### 1. **Load and Register JDBC Driver**

The first step is to load the JDBC driver specific to the database you're working with (such as MySQL, Oracle, or PostgreSQL). This step ensures the driver is available for the application to use.

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

- **`Class.forName()`**: Dynamically loads the database driver class.
- Note: In modern versions of JDBC, explicit loading of the driver is not always required if the driver JAR is available in the classpath.

#### 2. **Establish a Database Connection**

You need to establish a connection to the database using the **`DriverManager`** class by providing the database URL, username, and password.

```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydatabase", "username", "password");
```

- **`DriverManager.getConnection()`**: Establishes the connection to the database.
- The connection string (URL) includes details such as the protocol (`jdbc:mysql://`), server address (`localhost`), port number (`3306`), and database name (`mydatabase`).

#### 3. **Create a Statement or PreparedStatement**

Once connected, you can create a `Statement` or `PreparedStatement` to send SQL queries to the database.

- **`Statement`**: Used for simple, static queries.
- **`PreparedStatement`**: Used for parameterized queries and is more efficient for repeated queries.

```java
Statement statement = connection.createStatement();
```

Or, for a parameterized query:

```java
PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
preparedStatement.setInt(1, 1); // Set parameter value for the query
```

#### 4. **Execute the Query**

After preparing the query, execute it using either `executeQuery()` for `SELECT` queries or `executeUpdate()` for `INSERT`, `UPDATE`, or `DELETE` operations.

- **`executeQuery()`**: Returns a `ResultSet` for queries that return data (e.g., `SELECT`).
- **`executeUpdate()`**: Executes DML statements (e.g., `INSERT`, `UPDATE`, `DELETE`) and returns the number of affected rows.

```java
ResultSet rs = statement.executeQuery("SELECT id, name FROM users");
```

#### 5. **Process the `ResultSet`**

Once the query is executed, use the `ResultSet` to process the retrieved data. The `next()` method moves the cursor to the next row, and you can retrieve column values using methods like `getInt()`, `getString()`, etc.

```java
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    System.out.println("ID: " + id + ", Name: " + name);
}
```

#### 6. **Close Resources**

It's important to close the `ResultSet`, `Statement`, and `Connection` to release database resources.

```java
rs.close();
statement.close();
connection.close();
```

By following these steps, you can retrieve and manipulate data from a relational database using JDBC in a Java application.



# 9.Compare **Spring Data JPA** vs **Hibernate** vs **JDBC**.

### Comparison: **Spring Data JPA** vs **Hibernate** vs **JDBC**

Each of these technologies is used to interact with relational databases in Java applications, but they operate at different levels of abstraction and offer varying levels of functionality and flexibility. Here's a detailed comparison:

------

### 1. **JDBC (Java Database Connectivity)**

#### **Overview**:

- **JDBC** is the lowest-level API in Java for interacting with databases.
- It provides a direct and imperative way of executing SQL queries, updates, and managing result sets.
- It is database-agnostic but requires developers to handle SQL, connections, statements, and result sets manually.

#### **Pros**:

- **Fine-grained control**: You have full control over how SQL queries are written and executed.
- **Performance**: Since it is low-level, it can be highly optimized for specific use cases.
- **No dependencies**: JDBC is part of the standard Java API and does not require additional libraries.

#### **Cons**:

- **Verbose**: Requires a lot of boilerplate code (handling connections, closing resources, handling SQL exceptions).
- **Manual SQL**: You need to manually write SQL queries, which can be error-prone and harder to maintain.
- **No Object-Relational Mapping (ORM)**: It lacks any abstraction over database tables and does not support ORM features like object caching, automatic schema generation, etc.

#### **Use Cases**:

- Useful when you need high control over SQL queries, or in applications where performance is critical and minimal abstraction is desired.

------

### 2. **Hibernate**

#### **Overview**:

- **Hibernate** is an Object-Relational Mapping (ORM) framework that simplifies database interactions by allowing Java objects (entities) to be mapped to database tables.
- It provides a higher level of abstraction than JDBC by automatically generating SQL and managing object states.
- It also offers advanced features such as **caching**, **lazy loading**, and **automatic schema generation**.

#### **Pros**:

- **ORM support**: Simplifies database operations by mapping Java objects to database tables and automatically handling CRUD operations.
- **Less boilerplate**: Automatically generates SQL and manages connections, transactions, and statements.
- **Powerful features**: Includes features like lazy loading, caching, optimistic/pessimistic locking, and pagination.
- **Portability**: Hibernate is database-agnostic, so the same code can run across different databases without major changes.

#### **Cons**:

- **Complex learning curve**: Hibernate has a steep learning curve, especially for advanced features like caching, and lazy vs. eager loading.
- **Potential performance overhead**: The abstraction can add performance overhead, especially when not properly configured (e.g., handling N+1 query problems).
- **Less control over SQL**: Developers have less fine-tuned control over the SQL queries, which may be a limitation in some use cases.

#### **Use Cases**:

- Ideal for applications where object-oriented data modeling is preferred.
- Suitable for large, complex projects where entities are mapped to relational data and more advanced ORM features are needed.

------

### 3. **Spring Data JPA**

#### **Overview**:

- **Spring Data JPA** builds on top of Hibernate (or any other JPA provider) to further abstract and simplify database interactions.
- It provides a repository abstraction layer that automatically generates SQL based on method names (e.g., `findByName()`), reducing the need for custom queries.
- It integrates seamlessly with the Spring Framework and simplifies both the configuration and data access layers.

#### **Pros**:

- **Even less boilerplate**: Provides repositories and query generation based on method names, meaning you can get data access functionality without writing SQL or HQL.
- **Easy to use**: Integrated with Spring Boot, making setup and configuration simple.
- **Custom queries supported**: When necessary, custom SQL or JPQL queries can be written, giving flexibility.
- **Integration with Spring**: Works seamlessly with the wider Spring ecosystem (e.g., Spring Security, Spring Web, Spring Transactions).

#### **Cons**:

- **Even more abstraction**: With additional abstraction, it might be hard to control performance when the automatic query generation is not optimal.
- **Limited to JPA**: Spring Data JPA works with JPA providers (like Hibernate), so it inherits their limitations and performance overheads.
- **Learning curve**: Though easier than pure Hibernate, it still has a learning curve, especially when dealing with more complex queries or relationships.

#### **Use Cases**:

- Perfect for applications built with Spring, where rapid development, clean code, and easy database interactions are essential.
- Works well for small-to-medium projects and teams that want to avoid writing boilerplate or SQL.

------

### Comparison Summary:

| **Feature**                | **JDBC**                                    | **Hibernate**                                     | **Spring Data JPA**                                 |
| -------------------------- | ------------------------------------------- | ------------------------------------------------- | --------------------------------------------------- |
| **Level of Abstraction**   | Low (manual SQL queries)                    | High (ORM framework)                              | Very high (Repository-based ORM)                    |
| **Boilerplate Code**       | High                                        | Low (due to ORM features)                         | Very low (minimal custom SQL or query code)         |
| **SQL Control**            | Full control (manual queries)               | Limited (Hibernate generates SQL)                 | Limited (method-based query generation)             |
| **Complex Queries**        | Manually written in SQL                     | Custom HQL/JPQL queries                           | Method-based or custom queries when needed          |
| **Ease of Use**            | Difficult (more manual code)                | Moderate (requires understanding of ORM concepts) | Easy (out-of-the-box repository support)            |
| **Caching**                | Not supported                               | Supported (first-level and second-level caching)  | Inherits caching from JPA provider (like Hibernate) |
| **Transaction Management** | Manual                                      | Automatic (with JPA support)                      | Integrated with Spring’s transaction management     |
| **Configuration**          | Manual database configuration required      | Auto-configuration, but some Hibernate specifics  | Simplified with Spring Boot’s auto-configuration    |
| **ORM Support**            | No ORM support                              | Full ORM support                                  | Full ORM support (via JPA provider)                 |
| **Database Independence**  | Requires SQL tweaks for different databases | Database-agnostic (uses JPA)                      | Database-agnostic (built on JPA)                    |

------

### Comparison: **Spring Data JPA** vs **Hibernate** vs **JDBC**

Each of these technologies is used to interact with relational databases in Java applications, but they operate at different levels of abstraction and offer varying levels of functionality and flexibility. Here's a detailed comparison:

------

### 1. **JDBC (Java Database Connectivity)**

#### **Overview**:

- **JDBC** is the lowest-level API in Java for interacting with databases.
- It provides a direct and imperative way of executing SQL queries, updates, and managing result sets.
- It is database-agnostic but requires developers to handle SQL, connections, statements, and result sets manually.

#### **Pros**:

- **Fine-grained control**: You have full control over how SQL queries are written and executed.
- **Performance**: Since it is low-level, it can be highly optimized for specific use cases.
- **No dependencies**: JDBC is part of the standard Java API and does not require additional libraries.

#### **Cons**:

- **Verbose**: Requires a lot of boilerplate code (handling connections, closing resources, handling SQL exceptions).
- **Manual SQL**: You need to manually write SQL queries, which can be error-prone and harder to maintain.
- **No Object-Relational Mapping (ORM)**: It lacks any abstraction over database tables and does not support ORM features like object caching, automatic schema generation, etc.

#### **Use Cases**:

- Useful when you need high control over SQL queries, or in applications where performance is critical and minimal abstraction is desired.

------

### 2. **Hibernate**

#### **Overview**:

- **Hibernate** is an Object-Relational Mapping (ORM) framework that simplifies database interactions by allowing Java objects (entities) to be mapped to database tables.
- It provides a higher level of abstraction than JDBC by automatically generating SQL and managing object states.
- It also offers advanced features such as **caching**, **lazy loading**, and **automatic schema generation**.

#### **Pros**:

- **ORM support**: Simplifies database operations by mapping Java objects to database tables and automatically handling CRUD operations.
- **Less boilerplate**: Automatically generates SQL and manages connections, transactions, and statements.
- **Powerful features**: Includes features like lazy loading, caching, optimistic/pessimistic locking, and pagination.
- **Portability**: Hibernate is database-agnostic, so the same code can run across different databases without major changes.

#### **Cons**:

- **Complex learning curve**: Hibernate has a steep learning curve, especially for advanced features like caching, and lazy vs. eager loading.
- **Potential performance overhead**: The abstraction can add performance overhead, especially when not properly configured (e.g., handling N+1 query problems).
- **Less control over SQL**: Developers have less fine-tuned control over the SQL queries, which may be a limitation in some use cases.

#### **Use Cases**:

- Ideal for applications where object-oriented data modeling is preferred.
- Suitable for large, complex projects where entities are mapped to relational data and more advanced ORM features are needed.

------

### 3. **Spring Data JPA**

#### **Overview**:

- **Spring Data JPA** builds on top of Hibernate (or any other JPA provider) to further abstract and simplify database interactions.
- It provides a repository abstraction layer that automatically generates SQL based on method names (e.g., `findByName()`), reducing the need for custom queries.
- It integrates seamlessly with the Spring Framework and simplifies both the configuration and data access layers.

#### **Pros**:

- **Even less boilerplate**: Provides repositories and query generation based on method names, meaning you can get data access functionality without writing SQL or HQL.
- **Easy to use**: Integrated with Spring Boot, making setup and configuration simple.
- **Custom queries supported**: When necessary, custom SQL or JPQL queries can be written, giving flexibility.
- **Integration with Spring**: Works seamlessly with the wider Spring ecosystem (e.g., Spring Security, Spring Web, Spring Transactions).

#### **Cons**:

- **Even more abstraction**: With additional abstraction, it might be hard to control performance when the automatic query generation is not optimal.
- **Limited to JPA**: Spring Data JPA works with JPA providers (like Hibernate), so it inherits their limitations and performance overheads.
- **Learning curve**: Though easier than pure Hibernate, it still has a learning curve, especially when dealing with more complex queries or relationships.

#### **Use Cases**:

- Perfect for applications built with Spring, where rapid development, clean code, and easy database interactions are essential.
- Works well for small-to-medium projects and teams that want to avoid writing boilerplate or SQL.

------

### Comparison Summary:

| **Feature**                | **JDBC**                                    | **Hibernate**                                     | **Spring Data JPA**                                 |
| -------------------------- | ------------------------------------------- | ------------------------------------------------- | --------------------------------------------------- |
| **Level of Abstraction**   | Low (manual SQL queries)                    | High (ORM framework)                              | Very high (Repository-based ORM)                    |
| **Boilerplate Code**       | High                                        | Low (due to ORM features)                         | Very low (minimal custom SQL or query code)         |
| **SQL Control**            | Full control (manual queries)               | Limited (Hibernate generates SQL)                 | Limited (method-based query generation)             |
| **Complex Queries**        | Manually written in SQL                     | Custom HQL/JPQL queries                           | Method-based or custom queries when needed          |
| **Ease of Use**            | Difficult (more manual code)                | Moderate (requires understanding of ORM concepts) | Easy (out-of-the-box repository support)            |
| **Caching**                | Not supported                               | Supported (first-level and second-level caching)  | Inherits caching from JPA provider (like Hibernate) |
| **Transaction Management** | Manual                                      | Automatic (with JPA support)                      | Integrated with Spring’s transaction management     |
| **Configuration**          | Manual database configuration required      | Auto-configuration, but some Hibernate specifics  | Simplified with Spring Boot’s auto-configuration    |
| **ORM Support**            | No ORM support                              | Full ORM support                                  | Full ORM support (via JPA provider)                 |
| **Database Independence**  | Requires SQL tweaks for different databases | Database-agnostic (uses JPA)                      | Database-agnostic (built on JPA)                    |

------

### When to Use Each?

- **JDBC**:
  - Use it when you need full control over SQL queries, want to optimize performance by managing the database interactions directly, or in simple applications where ORM overhead is unnecessary.
- **Hibernate**:
  - Use it for projects that need a powerful ORM with features like caching, lazy loading, and complex relationships. Ideal for projects where writing manual SQL is cumbersome and object-relational mapping makes the development process smoother.
- **Spring Data JPA**:
  - Use it in **Spring Boot** applications where you want rapid development and want to avoid repetitive CRUD operations and manual query writing. It's best for applications that require seamless integration with Spring's ecosystem and a clean, repository-based data access layer.



# 10.Learn how to use ObjectMapper by this example.

1. https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java

```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```

LEARNED, DONE



# 11.What is the serialization and desrialization?

### 1. **Serialization**

**Definition**: Serialization is the process of converting an object (or data structure) into a format that can be easily saved to a file, transmitted over a network, or stored in a database.

**Common Formats**:

- **JSON**: JavaScript Object Notation, a lightweight data-interchange format.
- **XML**: Extensible Markup Language, a markup language that defines a set of rules for encoding documents.
- **Binary**: A format that encodes data in binary form, often used for efficiency.

**Use Cases**:

- Saving the state of an object to a file for later retrieval.
- Sending objects over a network in web services.
- Storing data in databases in a structured format.

**Example in Java (using JSON)**:

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
    private String name;
    private int age;

    // Constructors, getters, and setters
}

User user = new User("Alice", 30);
ObjectMapper objectMapper = new ObjectMapper();
String jsonString = objectMapper.writeValueAsString(user); // Serialization
System.out.println(jsonString); // Output: {"name":"Alice","age":30}
```

### 2. **Deserialization**

**Definition**: Deserialization is the reverse process of serialization. It converts a serialized format (such as JSON, XML, or binary) back into an object or data structure.

**Use Cases**:

- Retrieving an object from a saved state (e.g., loading user preferences).
- Receiving data from a network request and converting it into usable objects.
- Restoring application state after a crash or restart.

**Example in Java (using JSON)**:

```java
String jsonInput = "{\"name\":\"Alice\",\"age\":30}";
User userFromJson = objectMapper.readValue(jsonInput, User.class); // Deserialization
System.out.println(userFromJson.getName()); // Output: Alice
```



# 12.Use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

```java
import java.util.Arrays;

public class AverageExample {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        double average = Arrays.stream(numbers)
                               .average() // Calculate the average
                               .orElse(0.0); // Provide a default value if the stream is empty

        System.out.println("Average: " + average);
    }
}
```



# 13

Read, Done



# 14

Read, Done