# HW7 -- Springboot - RUD

## 1. List all of the Spring data related annotations your learned and explain its usage.

1. `@Entity`
   - **Usage**: Marks a class as a JPA entity, meaning it is mapped to a database table.
   - **Example**:
   ```java
   @Entity
   public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   }    
   ```
   - This class will be mapped to a database table, and each instance represents a row in the table.

2. `@Table`
    - **Usage**: MSpecifies the details of the table that the entity is mapped to (e.g., table name, schema).
    - **Example**:
    ```java
    @Entity
    @Table(name = "users")
    public class User {
    // ...
    }
    ```
    - This is used when you want to customize the table name or other table properties.

3. `@Id`
    - **Usage**: Marks a field as the primary key of the entity.
    - **Example**:
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```

4. `@GeneratedValue`
    - **Usage**:  Specifies the strategy for generating primary key values.
    - **Example**:
    ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```
    - Common strategies include `AUTO`, `IDENTITY`, `SEQUENCE`, and `TABLE`.

5. `@Column`
    - **Usage**:  Customizes the mapping between an entity field and a column in the database.
    - **Example**:
    ```java
    @Column(name = "user_name", nullable = false)
    private String name;
    ```
    - Useful for specifying the column name, constraints (like `nullable`, `length`, etc.), and other column properties.

6. `@ManyToOne`
    - **Usage**:  Defines a many-to-one relationship between two entities.
    - **Example**:
    ```java
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    ```
    - A join table is created (e.g., `user_roles`) to manage the relationship between the two entities.ther column properties.

7. `@OneToMany`
    - **Usage**:  Defines a one-to-many relationship between entities.
    - **Example**:
    ```java
    @OneToMany(mappedBy = "department")
    private List<User> users;
    ```
    - The `mappedBy` attribute indicates that this is the inverse side of the relationship and is mapped by the `department` field in the `User` entity.

8. `@ManyToMany`
    - **Usage**:  Defines a many-to-many relationship between two entities.
    - **Example**:
    ```java
    @ManyToMany
    @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    ```
    - A join table is created (e.g., `user_roles`) to manage the relationship between the two entities.


9. `@OneToOne`
    - **Usage**:   Defines a one-to-one relationship between two entities.
    - **Example**:
    ```java
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    ```

10. `@JoinColumn`
    - **Usage**:   Specifies the column that should be used for joining an entity association (e.g., foreign key).
    - **Example**:
    ```java
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    ```

11. `@Repository`
    - **Usage**: Indicates that the class is a repository (a Data Access Object, DAO) that interacts with the database.
    - **Example**:
    ```java
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
    }
    ```
    
12. `@Query`
    - **Usage**: Defines a custom query to be executed by the repository method.
    - **Example**:
    ```java
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    List<User> findByName(String name);
    ```
   - This allows you to write custom JPQL (Java Persistence Query Language) or native SQL queries.

13. `@Modifying`
    - **Usage**: Marks a method in a repository as one that modifies data (e.g., `UPDATE`, `DELETE`).
    - **Example**:
    ```java
    @Modifying
    @Query("UPDATE User u SET u.name = ?1 WHERE u.id = ?2")
    void updateUserName(String name, Long id);
    ```
    
14. `@Transactional`
    - **Usage**: Declares that a method should be executed within a transaction.
    - **Example**:
    ```java
    @Transactional
    public void saveUser(User user) {
    userRepository.save(user);
    }
    ```
    
15. `@EntityListeners`
    - **Usage**: Allows you to specify callback methods that will be invoked during the entity's lifecycle events (e.g., `@PrePersist`, `@PostPersist`).
    - **Example**:
    ```java
    @EntityListeners(AuditingEntityListener.class)
    public class User {
    // ...
    }
    ```
    
16. `@PrePersist`, `@PostPersist`, `@PreUpdate`, `@PostUpdate`, `@PreRemove`, `@PostRemove`
    - **Usage**: Used for entity lifecycle callbacks. These methods are invoked at certain points in an entity's lifecycle.
    - **Example**:
    ```java
    @PrePersist
    public void prePersist() {
    this.createdAt = new Date();
    }
    ```
    
17. `@MappedSuperclass`
    - **Usage**: Indicates that a class is a superclass whose properties can be inherited by entity classes but is not itself an entity.
    - **Example**:
    ```java
    @MappedSuperclass
    public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    }
    ```
    
18. `@Inheritance`
    - **Usage**: Specifies the inheritance strategy for an entity (e.g., `SINGLE_TABLE`, `JOINED`, `TABLE_PER_CLASS`).
    - **Example**:
    ```java
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    public class Vehicle {
    @Id
    private Long id;
    }
    ```
    
19. `@TableGenerator`
    - **Usage**: Defines a table-based primary key generator.
    - **Example**:
    ```java
    @TableGenerator(name = "user_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_value", pkColumnValue = "user_id", allocationSize = 1)
    ```
    
20. `@Version`
    - **Usage`: Used for optimistic locking by adding a version field to an entity. This ensures consistency during concurrent updates.
    - **Example:
    ```java
    @Version
    private Integer version;
    ```
    
## 2. What is DTO, VO, Payload, PO, model, DAO?

1. **DTO (Data Transfer Object)**: An object that carries data between processes or layers, typically used to transfer data in API calls without any business logic.
2. **VO (Value Object)**: An immutable object that represents a simple value, often used to encapsulate values that are equal based on their data, not identity.
3. **Payload**: The actual data being transmitted in a request or response, often refers to the body content in APIs.
4. **PO (Persistent Object)**: Represents an object that is stored and retrieved from a database, usually mapped to a table.
5. **Model**: A general term for an object that represents data in a business or application domain. In MVC architecture, it represents the data layer.
6. **DAO (Data Access Object)**: An object that provides an abstract interface for accessing and manipulating data from a database, isolating the data layer logic.

## 3. What is `@JsonProperty("description_yyds")`

The `@JsonProperty("description_yyds")` annotation in Java is part of the Jackson library, which is used to map between JSON and Java objects. It tells Jackson to map a JSON field named `"description_yyds"` to a Java class field (or method) during serialization and deserialization.

## 4. Explain the purpose of following dependency?

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```

This dependency is for **Jackson Databind**, a library used to convert between Java objects and JSON (serialization and deserialization).

- **groupId**: `com.fasterxml.jackson.core` - The organization providing Jackson.
- **artifactId**: `jackson-databind` - The module for data binding between Java objects and JSON.
- **version**: `2.13.3` - The specific version of the library.
- **scope**: `compile` - Makes the library available during compile time and part of the final build.

## 5. What is spring-boot-stater?
what dependecies in the below starter? do you know any starters?
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

1. A Spring Boot starter is a set of pre-configured dependencies to quickly set up a Spring application for specific use cases, like web development, testing, or databases. It simplifies dependency management by bundling related libraries together.

2. Dependencies in the below starter:
- The `spring-boot-starter-web` dependency includes libraries for building web applications. Some key dependencies are:
   - **Spring MVC**: For building RESTful web services and web applications.
   - **Embedded Tomcat**: As a default web server.
   - **Jackson**: For JSON handling.
   - **Spring Core and Spring Beans**: Core Spring framework components.

3. Other Starters:
- `spring-boot-starter-data-jpa`: For working with JPA and databases.
- `spring-boot-starter-security`: For adding security (e.g., authentication).
- `spring-boot-starter-test`: For testing (JUnit, Mockito).
- `spring-boot-starter-thymeleaf`: For using Thymeleaf as a templating engine.

## 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)` ? could you list CRUD by this style?

`@RequestMapping(value = "/users", method = RequestMethod.POST)` is an annotation in `Spring MVC` that maps HTTP requests to handler methods in a controller.

- `value = "/users"`: Specifies the URL path to map to this method (/users).
- `method = RequestMethod.POST`: Specifies the HTTP method (POST) for this endpoint.

**CRUD Operations with @RequestMapping**:

1. Creat(Post):

```java
@RequestMapping(value = "/users", method = RequestMethod.POST)
public ResponseEntity<User> createUser(@RequestBody User user) {
    // Logic to create user
}
```

2. Read(GET):

```java
@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
public ResponseEntity<User> getUser(@PathVariable Long id) {
    // Logic to get user by id
}
```

3. Update(PUT):

```java
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    // Logic to update user by id
}
```

4. Delete(DELETE):

```java
@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    // Logic to delete user by id
}
```

## 7. What is `ResponseEntity`? why do we need it?
```java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```

1. `ResponseEntity` is a class in `Spring MVC` used to represent the entire HTTP response. It provides more control over the HTTP status code, headers, and body of the response compared to just returning the body (like a plain object).

2. Why do we need `ResponseEntity`?
- **Control over HTTP Status**: You can explicitly set the HTTP status code (e.g., `OK`, `CREATED`, `NOT_FOUND`).
- **Headers management**: It allows you to add custom HTTP headers to the response.
- **Flexible response body**: You can include the response body (like `postResponse`) along with headers and status.

**Examples**:
1. `new ResponseEntity<>(postResponse, HttpStatus.OK)`:
   - Returns a response with the body (`postResponse`) and an HTTP status of **200 OK**.
2. `new ResponseEntity<>(postResponse, HttpStatus.CREATED)`:
   - Used for **201 CREATED** status when a new resource (like a post) has been successfully created.
3. `ResponseEntity.ok(postService.getPostById(id))`:
   -A shortcut for returning a **200 OK** status with the response body (`getPostById(id)`).

## 8. What is `ResultSet` in jdbc? and describe the flow how to get data using JDBC

### What is `ResultSet` in JDBC?

`ResultSet` is an object in JDBC (Java Database Connectivity) that holds the data retrieved from a database query. It acts as a cursor that points to a row of data and allows you to iterate through the result set of an SQL query.

### Flow to Get Data Using JDBC:

1. Load the JDBC Driver:
   - Load the appropriate JDBC driver for your database.

```java
Class.forName("com.mysql.jdbc.Driver");
```

2. Establish Connection:
   - Establish a connection to the database using `DriverManager`.

```java
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
```

3. Create a Statement:
   - Create a `Statement` or `PreparedStatement` to execute SQL queries.
   
```java
Statement statement = connection.createStatement();
```

4. Execute a Query:
   - Use the Statement to execute a query, which returns a `ResultSet`.
   
```java
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
```

5. Process the ResultSet:
   - Iterate over the `ResultSet` to retrieve the data.
   
```java
while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    // Process the data
}
```

6. Close Resources:
   - After processing the data, close the `ResultSet`, `Statement`, and `Connection` to release resources.
   
```java
resultSet.close();
statement.close();
connection.close();
```

## 9. Compare Spring Data JPA vs Hibernate vs JD

1. **Spring Data JPA**
   - **Abstraction**: A high-level abstraction on top of JPA, which simplifies database operations.
   - **Ease of Use**: Provides repository interfaces, automates the generation of queries, and reduces boilerplate code.
   - **Query Language**: Supports JPQL and derived queries (auto-generated from method names).
   - **Features**: Pagination, sorting, custom queries, and integration with Spring Boot.
   - **Use Case**: Ideal for developers who want to quickly implement CRUD operations and manage complex relationships without much SQL.
   Example:
```java
public interface UserRepository extends JpaRepository<User, Long> {}
```

2. **Hibernate**
   - **ORM Framework**: Hibernate is a lower-level ORM framework that implements JPA. It maps Java objects to database tables and handles complex relationships.
   - **Ease of Use**: Requires more configuration than Spring Data JPA but provides more flexibility.
   - **Query Language**: Supports HQL (Hibernate Query Language) and native SQL.
   - **Features**: Caching, lazy loading, and session management. Allows fine-tuning of the ORM behavior.
   - **Use Case**: Suitable for more complex database interactions or when full control over ORM behavior is needed.
   Example:
```java
Session session = sessionFactory.openSession();
Query query = session.createQuery("FROM User");
```

3. **JDBC (Java Database Connectivity)**
    - **Low-Level API**: JDBC is the lowest-level API for directly interacting with the database using SQL.
   - **Ease of Use**: Requires manual handling of SQL queries, result sets, transactions, and connection management.
   - **Query Language**: Pure SQL queries.
   - **Features**: Full control over SQL and database operations but involves more boilerplate code.
   - **Use Case**: Best when fine-grained control over SQL or performance optimizations are required, or for interacting with databases that don't support ORM frameworks.
   Example:
```java
Connection conn = DriverManager.getConnection(url, user, password);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

**Summary**：
| Feature               | Spring Data JPA         | Hibernate               | JDBC                     |
|-----------------------|-------------------------|-------------------------|--------------------------|
| **Level**             | High-level abstraction   | ORM framework            | Low-level API             |
| **Ease of Use**       | Easiest, less boilerplate| Moderate                 | Most boilerplate          |
| **Control**           | Moderate                 | High                     | Full control              |
| **Query Language**    | JPQL, derived queries    | HQL, native SQL          | Pure SQL                  |
| **Features**          | Pagination, Sorting      | Caching, Lazy loading    | Full SQL control          |
| **Best For**          | Simple to moderately complex CRUD apps | Complex ORM requirements | Direct SQL operations      |

- **Spring Data JPA** is the most developer-friendly but abstracted.
- **Hibernate** provides more control with advanced ORM capabilities.
- **JDBC** offers full control over database interactions but requires more effort.

## 10. Learn how to use `ObjectMapper` by this example.
<https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java>

```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```

1. `objectMapper.readValue()`
   - This method is used to deserialize JSON into a Java object.
   - In the example:
   ```java
   FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
   ```
   
     - `resBody` is a JSON string.
     - `FoodOutlet.class` specifies the type to deserialize into.
     - The result is a `FoodOutlet` Java object created from the JSON.

2. `objectMapper.writeValueAsString()`
   - This method **serializes** a Java object into a JSON string.
   - In the example:
   ```java
   String s = objectMapper.writeValueAsString(foodOutlet);
   ```
     - The `foodOutlet` Java object is converted into a JSON string.
     - The result, `s`, is a JSON representation of the `foodOutlet` object.

3. `objectMapper.readTree()`
   - This method parses JSON and returns a JsonNode, which allows you to traverse the JSON tree.
   - Useful for cases where you need to work with JSON structure but don't want to bind to a specific Java class.
   - Example:
   ```java
   JsonNode rootNode = objectMapper.readTree(jsonString);
   JsonNode nameNode = rootNode.get("name"); // Access specific field
   String name = nameNode.asText(); // Get the value as a string
   ```
     - `readTree()` parses the JSON string into a tree structure (JsonNode).
     - You can then access individual fields or traverse the JSON structure.

## 11. What is the serialization and desrialization? <https://hazelcast.com/glossary/serialization/>

- **Serialization**: The process of converting a data object (like a Java object) into a stream of bytes for storage or transmission to another system.
- **Deserialization**: The reverse process, reconstructing the object from the byte stream, making the data readable and usable in its original form.

## 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

```java
import java.util.Arrays;

public class AverageExample {
   public static void main(String[] args) {
      int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

      // Using Stream API to calculate the average
      double average = Arrays.stream(numbers)  // Converts the array to an IntStream (stream of integers)
                             .average()       // Stream API method to calculate the average of elements
                             .orElse(0);      // Returns 0 if the array is empty

      System.out.println("Average: " + average);
   }
}
```

## 13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码

## 14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码





