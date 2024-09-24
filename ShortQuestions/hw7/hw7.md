### 1. List all of the Spring data related annotations your learned and explain its usage.

| Annotation                                             | Usage Description                                                             | Example                                                                             |
| ------------------------------------------------------ | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `@Entity`                                              | Marks a class as a persistent entity representing a database table.           | `@Entity public class User { @Id private Long id; private String name; }`           |
| `@Id`                                                  | Defines the primary key of an entity.                                         | `@Id private Long id;`                                                              |
| `@GeneratedValue`                                      | Specifies the primary key generation strategy.                                | `@GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;`              |
| `@Repository`                                          | Marks an interface as a Spring Data repository for database operations.       | `@Repository public interface UserRepository extends JpaRepository<User, Long> { }` |
| `@Table`                                               | Specifies the database table name for an entity.                              | `@Table(name = "users")`                                                            |
| `@Column`                                              | Defines the column details, such as name, nullable, length, etc.              | `@Column(name = "user_name", nullable = false, length = 50) private String name;`   |
| `@Query`                                               | Custom JPQL or SQL query for repository methods.                              | `@Query("SELECT u FROM User u WHERE u.name = :name")`                               |
| `@Transactional`                                       | Manages transactions for the method or class to ensure atomic operations.     | `@Transactional public void saveUser(User user)`                                    |
| `@Modifying`                                           | Used with `@Query` for update or delete queries.                              | `@Modifying @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")`            |
| `@OneToOne`, `@ManyToOne`, `@OneToMany`, `@ManyToMany` | Defines the relationships between entities like one-to-one, many-to-one, etc. | `@OneToOne private Profile profile; @ManyToOne private Department department;`      |
| `@JoinColumn`                                          | Specifies the foreign key column for an entity relationship.                  | `@JoinColumn(name = "department_id")`                                               |
| `@Cascade`                                             | Configures cascading operations (save, update, delete) for related entities.  | `@OneToMany(cascade = CascadeType.ALL) private Set<Address> addresses;`             |

### 2. What is DTO, VO, Payload, PO, model, DAO?

| Term        | Definition                                                                         | Purpose/Usage                                                                                |
| ----------- | ---------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| **DTO**     | A simple object used to transfer data between layers.                              | Reduces coupling between client and server by carrying only necessary data.                  |
| **VO**      | An immutable object representing a value in the domain, without identity.          | Used to encapsulate descriptive attributes such as Address or Date.                          |
| **Payload** | The data sent over a network, usually in an API request or response.               | Represents the actual data being transferred, often in JSON format.                          |
| **PO**      | An object representing a record that is stored and synchronized with the database. | Mapped to database tables for persistence, often used in ORM frameworks like JPA.            |
| **Model**   | Represents data or business logic in the application.                              | Used across layers to represent entities in an application, often carrying additional logic. |
| **DAO**     | An abstract interface for data access operations.                                  | Encapsulates CRUD operations, separating the data access logic from the business layer.      |

### 3. What is @JsonProperty("description_yyds")

The `@JsonProperty("description_yyds")` annotation is applied to a field or getter/setter method to specify a custom name for that property when converting between Java objects and JSON.

## 4. Explain the purpose of following dependency

The purpose is to include the **Jackson Databind library** in your project, which provides functionality for serializing Java objects to JSON and deserializing JSON to Java objects.

## 5. What is spring-boot-stater?

A **Spring Boot Starter** is a pre-configured set of dependencies that simplify the process of adding commonly used libraries to a Spring Boot project. It eliminates the need for developers to manually specify individual dependencies and configurations, thereby streamlining development.

Dependency:
The **spring-boot-starter-web** starter is specifically designed for building web applications, including RESTful web services. When you include this starter in your project, it brings in several key dependencies:

Other Spring Boot Starters:

- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-test`
- `spring-boot-starter-thymeleaf`
- `spring-boot-starter-actuator`

## 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)` ? could you list CRUD by this style?

The `@RequestMapping` annotation in Spring MVC is used to map HTTP requests to handler methods of MVC controllers. It maps **POST** requests sent to the `/users` URL to the corresponding method in the controller

| CRUD Operation | HTTP Method | URL           | Purpose             |
| -------------- | ----------- | ------------- | ------------------- |
| Create         | POST        | `/users`      | Create a new user   |
| Read All       | GET         | `/users`      | Get a list of users |
| Read One       | GET         | `/users/{id}` | Get a user by ID    |
| Update         | PUT         | `/users/{id}` | Update a user by ID |
| Delete         | DELETE      | `/users/{id}` | Delete a user by ID |

## 7. What is ResponseEntity? why do we need it?

`ResponseEntity` is a class in Spring that represents the **entire HTTP response**, including the **status code**, **headers**, and **body**. It is a generic type, and you can specify the type of object that will be included in the body of the response (for example, a DTO, string, list, etc.).

**Why Do We Need ResponseEntity?**

1. Control over HTTP Status Code
2. Custom Response Headers
3. Error Handling
4. More Flexibility

## 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC

In **Java Database Connectivity (JDBC)**, a `ResultSet` is an object that represents the data retrieved from a database after executing a SQL query. It acts as a cursor that points to the current row of data within the result set, allowing you to navigate through the data and retrieve individual column values.

**Flow of Retrieving Data Using JDBC**  
| Step | Description | Example Code Snippet |
| ---- | ----------------------------------------------------------- | --------------------------------------------------------------------- |
| 1 | **Load JDBC Driver** (Optional for JDBC 4.0+) | `Class.forName("com.mysql.cj.jdbc.Driver");` |
| 2 | **Establish Connection** | `Connection conn = DriverManager.getConnection(url, user, password);` |
| 3 | **Create Statement** | `Statement stmt = conn.createStatement();` |
| | or **PreparedStatement** | `PreparedStatement pstmt = conn.prepareStatement(sql);` |
| 4 | **Execute SQL Query** | `ResultSet rs = stmt.executeQuery("SELECT * FROM users");` |
| | | `ResultSet rs = pstmt.executeQuery();` |
| 5 | **Process the ResultSet** | `while (rs.next()) { /* Retrieve data */ }` |
| 6 | **Close Resources** (Automatically with try-with-resources) | Managed by try-with-resources |

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.

**JDBC (Java Database Connectivity)**

- Type: Low-level API
- Purpose: Facilitates direct interaction with databases using SQL.
- Use Case: When fine-grained control over database operations is required or when working in environments where higher-level abstractions are not feasible.

**Hibernate**

- Type: Object-Relational Mapping (ORM) Framework
- Purpose: Maps Java objects to database tables, automates CRUD operations, and manages relationships between entities.
- Use Case: When you want to abstract away SQL queries, leverage caching, and manage complex entity relationships with less boilerplate code.

**Spring Data JPA**

- Type: Spring Framework Module
- Purpose: Provides a higher abstraction over JPA (Java Persistence API) implementations like Hibernate, simplifying data access layers.
- Use Case: When building Spring-based applications and you want to reduce boilerplate code further, utilize repository patterns, and integrate seamlessly with Spring's ecosystem.

## 11. What is the serialization and desrialization?

| Aspect                  | Serialization                                                             | Deserialization                                                               |
| ----------------------- | ------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| **Definition**          | Converting an object into a format for storage or transfer                | Reconstructing an object from its serialized format                           |
| **Purpose**             | Persisting object state, transmitting objects over a network              | Recovering object state for use in the application                            |
| **Common Formats**      | Byte streams, JSON, XML, Protocol Buffers, Avro                           | Byte streams, JSON, XML, Protocol Buffers, Avro                               |
| **Java Implementation** | Implementing `Serializable`, using `ObjectOutputStream`, Jackson for JSON | Using `ObjectInputStream`, Jackson for JSON, JAXB for XML                     |
| **Use Cases**           | Saving objects to files, sending objects via APIs, caching                | Loading saved objects, receiving objects from APIs, retrieving cached objects |
| **Advantages**          | Enables object persistence and communication                              | Allows object reuse and manipulation after transfer                           |
| **Disadvantages**       | Can be slow, security risks, versioning issues                            | Can fail if the class structure has changed, security risks                   |

## 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32]

```
import java.util.Arrays;
import java.util.OptionalDouble;

public class AverageCalculator {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        OptionalDouble averageOptional = Arrays.stream(numbers).average();

        double average = averageOptional.orElse(0.0);

        System.out.println("The average is: " + average);
    }
}
```
