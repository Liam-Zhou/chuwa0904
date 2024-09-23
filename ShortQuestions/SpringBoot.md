# Spring Boot

## 1. List all of the Spring data related annotations you learned and explain its usage
### 1. Spring Data Related Annotations

- @Entity:
Marks a class as a JPA entity, representing a table in the database.

- @Table:
Specifies the table name for the entity in the database.

- @Id:
Marks a field as the primary key of the entity.

- @GeneratedValue: 
Indicates that the primary key value is generated automatically.

- @Column:
Specifies details of a column in the database table, such as name and constraints.

- @Repository:
Indicates that a class is a repository, allowing Spring to handle data access exceptions.

- @Query:
Defines custom queries using SQL directly in repository interfaces.

- Modifying:
Used with @Query to indicate that the query modifies data (insert, update, delete).

- Transactional:
Indicates that a method or class should run within a transactional context.

- @PagingAndSortingRepository:
A repository interface for pagination and sorting of entities.


## 2. What is DTO, VO, Payload, PO, model, DAO?
- DTO (Data Transfer Object): Used to transfer data between layers, typically without business logic.
- VO (Value Object): Represents a descriptive object that holds data without identity. It's often used for read-only purposes.
- Payload: Refers to the data sent in a request or response, often in JSON format.
- PO (Persistent Object): An object that maps directly to a database table, usually representing a row in that table.
- Model: A representation of the data and business logic in the application, often encompassing DTOs, VOs, and POs.
- DAO (Data Access Object): An interface or class responsible for encapsulating data access logic, performing CRUD operations on the database.

## 3. What is @JsonProperty("description_yyds")
@JsonProperty annotation is used to match properties in a JSON object with fields in a Java class. In simple terms, it links a specific field name in the JSON to a property in a Java object.


- Suppose there is a JSON data:
  ```json
  {
      "description_yyds": "This is a description."
  }
  ```

- **Java Class**:
  ```java
  public class Example {
      @JsonProperty("description_yyds")
      private String description;

      // Getter and Setter
  }
  ```

In this example, the JSON field "description_yyds" is mapped to the Java field "description".

## 4. Purpose of the dependency
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.13.3</version>
  <scope>compile</scope>
</dependency>
```
- JSON Processing: Provides functionalities for serializing Java objects to JSON and deserializing JSON back into Java objects.
- Data Binding: Facilitates the mapping between JSON data and Java classes, allowing easy integration with RESTful APIs and data transfer objects (DTOs).

## 5. What is spring-boot-stater?

- Spring Boot Starter: A starter is a convenient way to include a set of dependencies for a specific functionality in a Spring Boot application. It simplifies the configuration and setup process.

### Dependencies in the below starter
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
- Group ID: Indicates the Spring Boot project.
- Artifact ID:
Indicates this starter includes dependencies for building web applications, including:
        - Spring MVC
        - Jackson (for JSON processing)
        - Tomcat (as the default embedded server)

### Do you know any starters?

- `spring-boot-starter-data-jpa`: For Spring Data JPA and Hibernate.
- `spring-boot-starter-security`: For securing applications with Spring Security.
- `spring-boot-starter-test`: For testing with JUnit and other testing frameworks.


## 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
### `@RequestMapping(value = "/users", method = RequestMethod.POST)`
It maps POST requests to the /users URL path to a specific handler method in a controller, typically for creating new resources.

### CRUD Operations Using This Style
   ```java
   // Create
   @RequestMapping(value = "/users", method = RequestMethod.POST)
   // Read
   @RequestMapping(value = "/users", method = RequestMethod.GET)
   // Update
   @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
   // Delete
   @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
   ``` 

## 7. What is ResponseEntity?

- ResponseEntity: 
A Spring class that encapsulates the entire HTTP response, including the status code, headers, and body.

### Why Do We Need It?
- Full Control: 
Allows us to customize the entire HTTP response, including status codes and headers.

- Flexible Responses: 
Lets us return different types of responses based on what the application needs to do.

- Consistent Error Handling: 
Makes it easier to handle and format error responses uniformly across the application.


## 8. What is ResultSet in JDBC? Describe the flow to get data using JDBC

- ResultSet: 
Is an object in JDBC that holds the data retrieved from a database query, allowing you to iterate over the results.

### Flow to Get Data Using JDBC
```java
public class JDBC {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3361/EMP";
    private static final String USERNAME = "chuwa_geek";
    private static final String PASSWORD = "chuwa_yyds";

    public Employee getEmployeeById(int id) throws Exception {
        Employee employee = new Employee();
        Connection conn = null;
        Statement stmt = null;  
        ResultSet rs = null;

        try {
            // 1. Load Driver
            Class.forName(DRIVER);
            // 2. Connect to Database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 3. Define SQL Statement
            String sql = "SELECT * FROM emp WHERE ID = " + id;
            // 4. Create Statement Object
            stmt = conn.createStatement();
            // 5. Execute SQL Statement
            rs = stmt.executeQuery(sql); // ResultSet holds the data

            // 6. Process Results
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. Close Connections and Resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return employee;
    }
}
```

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.
- Spring Data JPA:
    - High-level abstraction for easy data access with minimal configuration, ideal for rapid development.
    - Use it for simplicity.
- Hibernate:
    - ORM framework for mapping Java objects to database tables, offering flexibility and features like caching but requiring more setup.
    - Use it for complex data mapping.
- JDBC:
    - Low-level access to databases with direct SQL queries, providing maximum performance but involving more manual coding and connection management.
    - Use it for performance and direct SQL control. 

## 10. Learn how to use ObjectMapper by this example.
- Use `readValue()` for JSON to Java object conversion:
Use `objectMapper.readValue(resBody, FoodOutlet.class)` to convert a JSON string into a `FoodOutlet` object.
- Use `writeValueAsString()` for Java object to JSON conversion:
Use `objectMapper.writeValueAsString(foodOutlet)` to convert a `FoodOutlet` object back into a JSON string.
- Use `readTree()` for flexible JSON handling:
This method parses JSON into a tree structure (`JsonNode`), allowing us to access dynamic properties without predefined classes.


## 11. What is Serialization and Deserialization?
- Serialization is the process of converting an object into a series of bytes, enabling easy storage or transmission over networks. This simplifies the transfer of complex data structures.
- Deserialization is the reverse process, where bytes are converted back into an object, allowing the data to be used in its original format.
- Both processes enable data to be shared between applications and stored efficiently. They are crucial in distributed systems for operations like data retrieval and communication across nodes.

## 12. Use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```java
import java.util.Arrays;

public class AverageVal {
    public static void main(String[] args) {
        int[] arr = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        double res = Arrays.stream(arr).average().orElse(0.0);
        System.out.println("Average: " + res); // Output: Average: 39.7
    }
}
```

## 13.
[03_post_pageable](https://github.com/CTYue/springboot-redbook/tree/03_post_pageable)
## 14. 
[04_comment](https://github.com/CTYue/springboot-redbook/tree/04_comment)