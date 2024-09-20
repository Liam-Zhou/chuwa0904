# HW7 SpringBoot RUD

## 1. List all of the Spring data related annotations your learned and explain its usage

1. @Entity: Mark a Java class as an entity that will be mapped to a table in the database.
2. @Table: Specifies the name of the database table that an @Entity class is mapped to. If not specified, the default is the class name.
3. @Id: Marks a field in an @Entity class as the primary key.
4. @Query: Used to define custom SQL or JPQL queries in repository interfaces.
5. @Repository: Marks a class as a Data Access Object (DAO) that encapsulates database operations.
6. @GeneratedValue: Specifies how the primary key should be generated (e.g., auto-incremented).

## 2. DTO, VO, Payload, PO, Model, DAO

1. DTO: A DTO is a simple object that is used to transfer data between different layers of an application (e.g., from the service layer to the presentation layer or across network boundaries). It is often used to bundle and pass multiple values together in one object, without any business logic.
2. VO: A VO(Value Object) is an object that represents a conceptual whole. It is immutable and typically used to represent a value (e.g., money, date range, coordinates). Unlike DTOs, VOs can have business logic but are meant to be immutable. Value Objects encapsulate data that represents a domain concept and are used to improve readability and ensure type safety in domain modeling.
3. Payload: In the context of APIs or messaging systems, Payload refers to the data that is being carried or transmitted between two parties (e.g., from client to server or vice versa). The payload usually contains the relevant information to process a request or response.
4. PO: A PO(Persistent Object) or POJO (Plain Old Java Object) is an object that is used to represent a database entity or row in a table. These objects are typically mapped to database tables using ORM frameworks like JPA or Hibernate. POs are used to store data that is persisted to and retrieved from the database.
5. Model: Model is a broader term that generally refers to the objects that represent data in an application. It is often used to represent the structure of domain objects, especially in the MVC (Model-View-Controller) architecture. Models represent the business domain and contain data and methods that encapsulate business logic.
6. DAO: A DAO is a design pattern that abstracts the underlying data access logic. It is used to separate the low-level database interaction code from the business logic, providing a cleaner structure and encapsulation. A DAO handles operations such as querying, inserting, updating, and deleting data from a database. It abstracts the persistence logic and allows you to change the data source without affecting the rest of the application.

## 3. What is @JsonProperty("description_yyds")

The @JsonProperty annotation is used in Jackson (a popular Java library for processing JSON) to control how JSON fields are serialized and deserialized between Java objects and JSON. It allows you to specify the name of the property in the JSON representation, even if the Java field has a different name.

In the case of @JsonProperty("description_yyds"), it means that when this object is serialized to JSON, or when JSON is deserialized into the object, the corresponding field will be mapped to the description_yyds property in JSON.

## 4. Explain the purpose of following dependency?

The purpose of the following dependency in a Maven pom.xml file is to include the Jackson Databind library in your project, which is used for converting between Java objects and JSON

## 5. What is spring-boot-stater?

A Spring Boot Starter is a pre-defined Maven dependency that aggregates a set of libraries, configurations, and dependencies commonly used for a particular functionality. Starters are designed to simplify the process of setting up new Spring Boot projects by including all the necessary libraries and their transitive dependencies so that you don’t have to explicitly define them individually in your pom.xml file.

```
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
```

This starter brings together all the necessary components for building web applications, including RESTful services, using Spring MVC. It includes everything you need to set up a web server, handle HTTP requests, and return responses, including JSON handling.

### Other Spring Boot starters:

| **Starter**                     | **Description**                                                                                            |
| ------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| `spring-boot-starter`           | The core starter for Spring Boot applications. Includes basic dependencies and auto-configuration support. |
| `spring-boot-starter-data-jpa`  | Starter for JPA (Java Persistence API) support, integrating Hibernate for ORM (Object-Relational Mapping). |
| `spring-boot-starter-security`  | Starter for Spring Security, providing authentication and authorization support.                           |
| `spring-boot-starter-thymeleaf` | Starter for using Thymeleaf as the templating engine for web applications.                                 |
| `spring-boot-starter-test`      | Starter for testing with JUnit, Mockito, Spring Test, and other testing libraries.                         |
| `spring-boot-starter-actuator`  | Adds production-ready features to help monitor and manage your application (e.g., health checks, metrics). |
| `spring-boot-starter-mail`      | Provides support for sending email via the JavaMail API.                                                   |
| `spring-boot-starter-websocket` | Starter for WebSocket support, used for real-time communication between client and server.                 |
| `spring-boot-starter-logging`   | The default logging starter, included by default. It sets up SLF4J with Logback.                           |
| `spring-boot-starter-redis`     | Provides integration with Redis, a key-value store used for caching and data persistence.                  |

## 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?

The @RequestMapping annotation in Spring is used to map HTTP requests to handler methods of MVC (Model-View-Controller) and REST controllers. The parameters value and method within @RequestMapping specify the URL endpoint and the HTTP method (such as GET, POST, PUT, DELETE) that the handler method will respond to.

CRUD by this style
| **Operation** | **HTTP Method** | **URL Path** | **@RequestMapping Example** |
|-----------------|-----------------|-----------------------|----------------------------------------------------------------------------|
| **Create** | POST | `/users` | `@RequestMapping(value = "/users", method = RequestMethod.POST)` |
| **Read (all)** | GET | `/users` | `@RequestMapping(value = "/users", method = RequestMethod.GET)` |
| **Read (one)** | GET | `/users/{id}` | `@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)` |
| **Update** | PUT | `/users/{id}` | `@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)` |
| **Delete** | DELETE | `/users/{id}` | `@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)` |

## 7. What is ResponseEntity? why do we need it?

ResponseEntity is a class in Spring MVC that represents the whole HTTP response, including the status code, headers, and body. It provides greater control over the HTTP response that a REST API returns compared to just returning the response body.

When building RESTful APIs, you often need to return not only the response data but also information such as HTTP status codes (e.g., 200 OK, 404 Not Found), custom headers, and sometimes an empty body. ResponseEntity allows you to control all these aspects in a clean, flexible way.

## 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC

In JDBC (Java Database Connectivity), a ResultSet is an object that holds the data retrieved from a database after executing a SQL query. It acts as a table of data representing a set of results returned by a SQL SELECT query. The ResultSet object maintains a cursor that points to the current row of the data, and by moving the cursor, you can retrieve the data from the result set row by row.

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcExample {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Establish the connection
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Step 2: Create a statement or prepared statement
            String sql = "SELECT id, name FROM users WHERE age > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 18);  // Set the parameter

            // Step 3: Execute the query
            resultSet = preparedStatement.executeQuery();

            // Step 4: Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 5: Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.

| **Feature**                | **Spring Data JPA**                                   | **Hibernate**                                           | **JDBC**                                                    |
| -------------------------- | ----------------------------------------------------- | ------------------------------------------------------- | ----------------------------------------------------------- |
| **Abstraction Level**      | High-level abstraction over JPA and ORM frameworks    | Mid-level abstraction using ORM                         | Low-level, direct interaction with the database             |
| **Ease of Use**            | Simplified, minimal boilerplate                       | Easier than JDBC, but requires ORM knowledge            | Most difficult, requires manual SQL and connection handling |
| **Control Over SQL**       | Limited, but supports custom queries                  | More control, but still ORM-based                       | Full control over SQL queries                               |
| **Performance**            | Moderate due to abstraction overhead                  | Moderate, depends on caching and optimizations          | Fastest, no ORM overhead                                    |
| **Learning Curve**         | Low, easy to start with                               | Medium, requires knowledge of ORM and JPA               | High, requires SQL, database, and JDBC knowledge            |
| **Query Language**         | JPQL, HQL, Criteria API, Native SQL                   | JPQL, HQL, Criteria API, Native SQL                     | Raw SQL                                                     |
| **Transaction Management** | Managed by Spring with annotations (`@Transactional`) | Programmatic or declarative (can integrate with Spring) | Manual transaction management using connection methods      |
| **Caching**                | First and second-level cache via JPA providers        | Built-in support for caching, configurable              | No caching support                                          |
| **Use Case**               | CRUD operations with minimal code                     | More flexible ORM operations                            | High-performance, fine-tuned database operations            |

## 11. What is the serialization and desrialization?

`Serialization` is the process of converting a Java object (or any object in general) into a format that can be easily stored or transmitted. This format could be a binary format, JSON, XML, or another form suitable for transport or storage.

`Deserialization` is the reverse process of serialization. It converts the serialized data (such as a JSON string, XML, or binary data) back into a Java object (or the corresponding object in another language).

## 12. Use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

```
import java.util.Arrays;

public class StreamAverage {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        // Calculate average using Stream API
        double average = Arrays.stream(numbers) // Convert array to stream
                               .average()       // Calculate average
                               .orElse(0);      // Default if no elements

        System.out.println("Average: " + average);
    }
}
```
