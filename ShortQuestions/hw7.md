### 1. List all of the Spring Data related annotations you learned and explain their usage.

Here are some important **Spring Data** annotations and their usage:

- **@Entity**: Marks a class as an entity that maps to a database table.
  
- **@Table**: Used to specify the details of the table (name, schema, etc.) associated with the entity.

- **@Id**: Defines the primary key of the entity.

- **@GeneratedValue**: Used to specify how the primary key should be generated (e.g., `AUTO`, `IDENTITY`, `SEQUENCE`).

- **@Column**: Specifies the mapped column for a persistent property.

- **@OneToOne, @OneToMany, @ManyToOne, @ManyToMany**: These annotations define the relationships between entities (one-to-one, one-to-many, etc.).

- **@JoinColumn**: Specifies the foreign key column used in the relationship between entities.

- **@Transactional**: Marks a method or class as transactional, meaning the code will execute within a database transaction.

- **@Query**: Used to define custom queries directly in the repository interface.

- **@Modifying**: Indicates that a query method modifies the database (e.g., for `UPDATE` or `DELETE` operations).

- **@Repository**: Used to indicate that the class is a Spring Data repository, which abstracts data access logic.

### 2. What is DTO, VO, Payload, PO, Model, DAO?

- **DTO (Data Transfer Object)**: A simple object that is used to transfer data between different layers, such as from the controller to the service layer. It is primarily used to encapsulate the data.

- **VO (Value Object)**: Similar to DTO but represents data as part of a business domain model. It's immutable and often used to describe a piece of data that does not have identity or unique properties.

- **Payload**: Typically refers to the data sent in a request (often in JSON format). In REST APIs, the payload represents the body of the request.

- **PO (Persistent Object)**: Refers to objects that represent data persisted in a database, typically annotated with `@Entity`.

- **Model**: In Spring, the model is an object that holds the data that will be rendered in the view layer (in MVC). It can also refer to domain models or business entities in general.

- **DAO (Data Access Object)**: An object responsible for abstracting and encapsulating all access to the data source. It provides CRUD operations for the underlying database.

### 3. What is `@JsonProperty("description_yyds")`?

- **@JsonProperty("description_yyds")**: This annotation is part of the Jackson library and is used to map a JSON property to a Java field when serializing or deserializing. In this case, the JSON property `"description_yyds"` will be mapped to a Java field, ensuring that the field in Java matches this custom JSON key.

   Example:
   ```java
   public class Product {
       @JsonProperty("description_yyds")
       private String description;
   }
   ```

### 4. Explain the purpose of the following dependency?
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.13.3</version>
  <scope>compile</scope>
</dependency>
```

- **Jackson Databind** is a popular library used for converting between Java objects and JSON (both for serialization and deserialization). This dependency is part of the **Jackson** library ecosystem, and it provides functionality to parse JSON into Java objects and vice versa. It is essential for working with REST APIs where JSON is the data format.

### 5. What is spring-boot-starter? 
- A **Spring Boot Starter** is a set of pre-configured dependencies that allow you to quickly set up a Spring project. Each starter brings in a bundle of dependencies and configuration to help with specific tasks like web development, data access, testing, etc.

### 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)`? Could you list CRUD by this style?

- **@RequestMapping(value = "/users", method = RequestMethod.POST)**: This annotation maps an HTTP request to the specified URL (`/users`) and HTTP method (`POST`). It defines the handler method that will be executed when a POST request is made to `/users`.
  
  To define other CRUD operations:

  - `@RequestMapping(value = "/users", method = RequestMethod.GET)` – Fetch all users (Read)
  - `@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)` – Fetch a specific user by ID (Read)
  - `@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)` – Update a specific user (Update)
  - `@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)` – Delete a specific user (Delete)

### 7. What is `ResponseEntity`? Why do we need it?

- **ResponseEntity**: It represents the entire HTTP response, including status code, headers, and body. It is used when you want to customize the HTTP response returned from a controller. For example, it allows you to control the status code or add custom headers.

  Example:
  ```java
  return new ResponseEntity<>(user, HttpStatus.OK);
  ```

### 8. What is `ResultSet` in JDBC? Describe the flow to get data using JDBC.

- **ResultSet**: In JDBC, `ResultSet` is an object that holds the data retrieved from a database query. It represents the rows and columns of data that result from executing a `SELECT` query.
  
   **Flow to get data using JDBC**:
   1. Establish a connection using `DriverManager.getConnection()`.
   2. Create a `Statement` or `PreparedStatement`.
   3. Execute a SQL query using `executeQuery()` to obtain a `ResultSet`.
   4. Iterate over the `ResultSet` to retrieve the data.
   5. Close the `ResultSet`, `Statement`, and `Connection`.

### 9. Compare Spring Data JPA vs Hibernate vs JDBC.

- **Spring Data JPA**: 
   - Provides an abstraction over JPA, allowing for easy integration with databases without writing boilerplate code.
   - Uses repository interfaces and automatic query generation.
   - Simplifies data access by using annotations.

- **Hibernate**: 
   - A robust ORM framework that implements the JPA specification.
   - Provides more powerful, low-level control over database interaction compared to Spring Data JPA.
   - Offers features like lazy loading, caching, and transaction management.

- **JDBC**: 
   - The lowest-level API for interacting with relational databases in Java.
   - Requires writing SQL queries and handling result sets manually.
   - More complex but offers full control over the SQL and connection management.
   

Spring Data JPA and Hibernate are higher-level abstractions built on top of JDBC to simplify database operations, while JDBC gives you direct control but with more complexity.

### 10. Learn how to use `ObjectMapper` by this example:
To understand how to use `ObjectMapper`, let’s break it down from the example provided in the link. `ObjectMapper` is a class from the Jackson library that is used for converting Java objects to JSON and vice versa.

- **Serialization**: Convert a Java object to JSON.
- **Deserialization**: Convert JSON to a Java object.

From the link you provided, here’s a high-level breakdown:
1. Import `ObjectMapper` from Jackson.
2. Create an instance of `ObjectMapper`.
3. To **serialize** an object to JSON, use `writeValueAsString()` or `writeValue()`.
   ```java
   ObjectMapper mapper = new ObjectMapper();
   String jsonString = mapper.writeValueAsString(yourObject);
   ```
4. To **deserialize** JSON to an object, use `readValue()`.
   ```java
   YourClass object = mapper.readValue(jsonString, YourClass.class);
   ```

The full example from your link will demonstrate how to use these methods effectively.

### 11. What is serialization and deserialization?
Serialization is the process of converting an object into a format that can be stored or transmitted (such as JSON, XML, or binary). Deserialization is the reverse process, where the stored data is converted back into an object.

#### Source: [Hazelcast Glossary on Serialization](https://hazelcast.com/glossary/serialization/)
Serialization is crucial for transferring data between different systems or persisting it in storage. Java provides built-in serialization, and frameworks like Jackson handle JSON-based serialization/deserialization.

### 12. Use Stream API to get the average of the array `[20, 3, 78, 9, 6, 53, 73, 99, 24, 32]`.
Using Java's Stream API, you can easily calculate the average value of an array of integers:

```java
import java.util.Arrays;

public class StreamExample {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        
        double average = Arrays.stream(numbers)
                               .average()
                               .orElse(0);
        
        System.out.println("Average: " + average);
    }
}
```

This code snippet calculates the average of the array by converting it into a stream and applying the `average()` method.

