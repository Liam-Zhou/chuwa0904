## 1. List all of the Spring data related annotations your learned and explain its usage.
@RestController is a Spring annotation used to define a controller in a Spring web application. It marks a class as a controller where every method returns a domain object instead of a view. It combines the functionality of @Controller and @ResponseBody. \
```
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Fetch and return a list of users
        return userService.findAll();
    }
}
```
Here, @RestController makes the UserController class a REST API controller, and the getAllUsers method responds with a JSON list of users.\
\
@RequestMapping is a Spring annotation used to map web requests to specific handler methods or classes in a controller. It can be applied at both the class level and the method level. When applied at the class level, it specifies a base path for all methods in the controller, and when applied at the method level, it defines the specific request handling logic.\
```
@RestController
@RequestMapping("/api")
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```
In this example, /api/users is mapped to getUsers, and /api/user/{id} is mapped to getUserById using @RequestMapping.\
\
@PostMapping is a Spring annotation used to map HTTP POST requests onto specific handler methods. It is a specialized form of @RequestMapping that simplifies the syntax for handling POST requests.\
```
@RestController
@RequestMapping("/api")
public class UserController {

    // Handles POST requests to /api/users
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Save the user and return the created object
        return userService.save(user);
    }
}
```
\
@RequestBody is a Spring annotation used to bind the HTTP request body to a method parameter in a controller. It allows you to map incoming data (typically JSON or XML) directly to a Java object.\
```
@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // The JSON data in the request body is deserialized into a User object
        return userService.save(user);
    }
}
```
\
@PathVariable is a Spring annotation used to extract values from the URI path and bind them to method parameters in a controller. It allows you to capture specific parts of the request URL and map them to variables in your code, typically for RESTful endpoints.\
```
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        // The {id} in the URL is captured and passed to the method as a parameter
        return userService.findById(id);
    }
}
```
\
@ResponseStatus is a Spring annotation used to specify the HTTP status code that a method or exception handler should return. It can be applied to a controller method or a custom exception class to indicate the desired status code when the method completes or the exception is thrown.\
```
@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED) // Returns 201 Created
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

```
\
## 2. What is DTO, VO, Payload, PO, model, DAO?
DTO is data transfer object, it is the only java class (created within the payload package ). DTO is an object that carries data between processes or layers (e.g., between the presentation layer and the service layer). Its primary role is to transfer data in a form that is independent of the internal domain model of the application.
```
public class UserDTO {
    private String name;
    private String email;
    // Getters and setters
}
```
\
VO is value object, it represents a value in the domain and is often used to encapsulate simple or complex data but with a focus on immutability. Two VOs with the same data are considered equal.
```
public class AddressVO {
    private final String street;
    private final String city;
    private final String zipCode;
    // Constructor and no setters (immutable)
}
```
\
A payload refers to the data that is sent or received. The payload is typically the body of the HTTP request or response.
```
{
    "name": "John Doe",
    "email": "john.doe@example.com"
}
```
\
A PO (Persistent Object) is an object that represents data stored in a database. These objects are typically mapped to a database table and include fields representing the columns of the table.
```
@Entity
public class UserPO {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    // Getters and setters
}
```
\
A "model" is a broad term that refers to an object representing the core data structure in an application, often corresponding to a domain object in the business logic layer.\
A DAO is an object that provides an abstract interface to the database or another persistence mechanism. It encapsulates the logic needed to retrieve, store, and modify data.
```
public interface UserDAO {
    UserPO findById(Long id);
    void save(UserPO user);
    void delete(Long id);
}
```

## 3. What is @JsonProperty("description_yyds")
@JsonProperty("description_yyds") is a Jackson annotation in Java that is used to customize the serialization and deserialization of JSON properties. Specifically, this annotation allows you to map a JSON field to a Java object field (or method) with a different name. It is part of the com.fasterxml.jackson.annotation package, commonly used in Spring applications for handling JSON data.
```
public class Product {

    @JsonProperty("description_yyds")
    private String description;

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


Product product = new Product();
product.setDescription("A great product");

// JSON output
{
    "description_yyds": "A great product"
}
```
## 4. Explain the purpose of following dependency?
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```
In Spring Boot, dependencies play a crucial role in determining the features and functionality of your application. Each starter dependency includes a set of commonly used libraries tailored for specific functionalities, making it easier to set up and manage the application's requirements without needing to handle individual library versions manually.\
1. Data Binding:\
The jackson-databind module is responsible for converting between Java objects and JSON data. It enables serialization (converting Java objects to JSON) and deserialization (converting JSON back into Java objects).\
2. Object Mapping:\
It provides mechanisms to map Java objects to JSON structures and vice versa, making it easier to work with JSON data formats in Java applications.\
3. Annotations Support:\
The module supports various annotations (such as @JsonProperty, @JsonIgnore, etc.) that allow developers to customize the mapping behavior, specify JSON field names, ignore certain fields, and control how data is serialized and deserialized.\
4. Configuration and Customization:\
jackson-databind offers extensive configuration options to tailor the serialization and deserialization process, including support for complex data types, collections, and custom serializers/deserializers.\
5. Integration with Spring:\
In Spring Boot applications, jackson-databind is used to handle JSON data in RESTful APIs, allowing automatic conversion between HTTP request/response bodies and Java objects without requiring manual parsing.\

## 5. What is spring-boot-stater?
The spring-boot-starter provides a set of common dependencies and auto-configuration features that simplify the setup and development of Spring Boot applications.\
Starter POM:\
It is essentially a "starter POM" (Project Object Model) that groups commonly used dependencies into a single unit, allowing developers to easily include the necessary libraries for building applications without having to manage each dependency individually.\
Auto-Configuration:\
It enables Spring Boot's auto-configuration feature, which automatically configures beans based on the classes present on the classpath and the properties defined in the application configuration. This means you can get started quickly without needing extensive configuration.\

### what dependecies in the below starter? do you know any starters?
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
The spring-boot-starter-web dependency is a specific starter in Spring Boot that provides all the necessary components to build web applications, particularly those that follow the RESTful architecture.\
Spring MVC:, Jackson, Spring Web are in this starter.\
spring-boot-starter-security, spring-boot-starter-test, spring-boot-starter-data-mongodb\

## 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
value: This attribute specifies the URI path to which the method will respond. In this case, it’s mapped to /users.\
method: This attribute specifies the HTTP method (like GET, POST, PUT, DELETE) that the handler method should respond to. Here, RequestMethod.POST means that this method will handle HTTP POST requests sent to /users.\
This specific mapping is typically used for creating new user resources in a RESTful web service. When a POST request is made to /users, the annotated method will be invoked to handle the request.\
```
@RequestMapping(value = "/users", method = RequestMethod.POST)
@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
```
## 7. What is ResponseEntity? why do we need it?
```
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```
ResponseEntity is a class in Spring that represents an HTTP response, including the status code, headers, and body. It is used to provide a more flexible way to configure HTTP responses in your controllers.\
Fine-Grained Control: It provides more control over the HTTP response compared to simply returning an object. This is particularly important in REST APIs, where the status codes are significant for clients to understand the result of their requests.\
Standardization: Using ResponseEntity helps standardize the responses from your API, making it easier for clients to handle responses consistently.\
Clarity: It makes the intent clear in your code, showing that you are explicitly managing the response and its details.\

## 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
In JDBC (Java Database Connectivity), a ResultSet is an interface that represents the result set of a query executed against a database. It provides methods to access and manipulate the data returned by a SQL query, allowing you to retrieve rows of data one at a time or as a whole.\
### Key Features of ResultSet:
Navigational Access: You can navigate through the rows of the result set using methods like next(), previous(), first(), last(), etc.\
Data Retrieval: You can retrieve data from the current row of the ResultSet using methods like getString(), getInt(), getDate(), etc., based on the column type.\
Scrollability and Updatability: Depending on how you create the ResultSet, it can be scrollable (able to move backward and forward) and updatable (allowing you to modify data).\
```
try {
    // 1, load Driver
    Class.forName(DRIVER);
    // 2， connect to Database;
    conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    // 3， define sql statement
    String sql = "SELECT * FROM emp WHERE ID = " + id;
    // 4, create a statement object
    stmt = conn.createStatement();
    // 5, use stmt object to execute sql statement;
    rs = stmt.executeQuery(sql); // the result is return to ResultSet
    while(rs.next()) {
    // 6, get ResultSet's data to java object(employee)
        post.setId(rs.getInt("id"));
        post.setName(rs.getString("title"));
    }
    rs = stmt.executeQuery(sqlComment);
    Comment comment = new Comment();
    while (rs.next()) {
        comment.setId(rs.getInt("id"));
        comment.setEmail(rs.getString("email"));
    }
    // 7, close conections and other resource.
        rs.close();
        stmt.close();
        conn.close();
        return employee;
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) {
            rs.close();
            rs = null;
            }
        if (stmt != null) {
            stmt.close();
            stmt = null;
            }
        if (conn != null) {
            conn.close();
            conn = null;
            }
        return null;
}

```

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.
| Feature                     | JDBC                                      | Hibernate                                    | Spring Data JPA                             |
|-----------------------------|-------------------------------------------|----------------------------------------------|---------------------------------------------|
| **Abstraction Level**       | Low-level API, direct SQL interaction     | ORM, maps Java objects to database tables    | Layer on top of JPA, repository abstraction |
| **Ease of Use**             | Verbose, error-prone, lots of boilerplate | More user-friendly, reduces boilerplate      | Highly convenient, defines interfaces for data access |
| **Performance**             | Direct access, can be more performant    | Some overhead, optimized through features    | Depends on JPA provider, may introduce overhead |
| **Transaction Management**  | Manual management using connection object  | Built-in support, integrates with Spring's management | Declarative transaction management using annotations |
| **Caching**                 | No built-in caching                       | Supports first-level and second-level caching| Can leverage Hibernate caching               |
| **Learning Curve**          | Steeper initial learning curve            | Moderate, requires understanding of ORM      | Easiest for Spring developers, simplifies data access |

## 11. What is the serialization and desrialization?
Serialization is the process of converting an object (or data structure) into a format that can be easily stored or transmitted. This often involves converting the object's state into a byte stream or a specific format like JSON or XML.\
In Java, you can serialize an object to JSON using libraries like Jackson or Gson:\
```
ObjectMapper objectMapper = new ObjectMapper();
User user = new User("John Doe", 30);
String jsonString = objectMapper.writeValueAsString(user); // Serialize to JSON
```
Deserialization is the reverse process of serialization. It involves converting a serialized format (like a JSON string or byte stream) back into an object or data structure.\
Continuing from the previous example, you can deserialize a JSON string back into a Java object:\
```
String jsonString = "{\"name\":\"John Doe\",\"age\":30}";
User user = objectMapper.readValue(jsonString, User.class); // Deserialize from JSON
```
## 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```
import java.util.Arrays;

public class AverageExample {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        double average = Arrays.stream(numbers)    // Create a stream from the array
                               .average()        // Calculate the average
                               .orElse(0.0);    // Provide a default value if the stream is empty

        System.out.println("Average: " + average);
    }
}
```