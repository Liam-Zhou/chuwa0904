# hw7 Spring boot

## 1. List all of the Spring data related annotations you learned and explain its usage.

1. @Entity: Marks a class as a persistent entity, meaning it's mapped to a database table. Used in JPA to specify that the class represents a table in the database.
    ```Java
    @Entity
    public class User {
        @Id
        private Long id;
        private String name;
    }
    ```

2. @Table: Specifies the table name in the database that an entity is mapped to. If not specified, the entity’s class name will be used.
    ```Java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        private Long id;
    }
    ```

3. @Id: Indicates the primary key of an entity.
    ```Java
    @Entity
    public class User {
        @Id
        private Long id;
    }
    ```

4. @Query: Used to define custom queries using JPQL or native SQL. It can be used inside repository interfaces to create complex queries.
    ```Java
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    List<User> findByName(String name);
    ```

## 2. What is DTO, VO, Payload, PO, model, DAO?

1. DTO (Data Transfer Object)

    Definition: A DTO is an object used to transfer data between different parts of an application, especially between layers like the service layer and the presentation layer (e.g., between a controller and the client).

2. VO (Value Object)

    Definition: A VO is an immutable object that represents a value or a small set of attributes. It does not have an identity like an entity and is often used to encapsulate business data with no need to be stored in the database.

3. Payload

    Definition: Payload refers to the actual data being transferred over a network or sent in a request or response. In a REST API, a payload typically refers to the body of an HTTP request or response, such as JSON or XML data.

4. PO (Persistent Object)

    Definition: A Persistent Object (PO) is an object that represents a record stored in a database. This is often referred to as an Entity in the context of JPA/Hibernate. A PO is mapped to a table in the database and can be persisted and retrieved using ORM frameworks.

5. Model

    Definition: A Model represents the business domain in an application, and in many cases, it could be the same as a persistent object. However, it can also refer to any object in the domain layer of an application that holds data.

6. DAO (Data Access Object)

    Definition: A DAO is an object responsible for accessing the database or other persistence storage. It typically contains methods to perform CRUD operations (Create, Read, Update, Delete) on the persistent objects.

## 3. What is @JsonProperty("description_yyds")

`@JsonProperty` is an annotation from the Jackson library in Java, used to map JSON property names to Java object fields when serializing or deserializing JSON data.

Deserialization (JSON to Java):
```
{
  "description_yyds": "Best product ever!"
}
```

Serialization (Java to JSON):

```Java
Product product = new Product();
product.setDescription("Best product ever!");
```

## 4. Explain the purpose of following dependency?

```XML
<dependency>
    <!-- specifies the group or organization responsible for the library. It’s com.fasterxml.jackson.core, which is the group that develops the core Jackson libraries for JSON parsing and processing. -->
    <groupId>com.fasterxml.jackson.core</groupId>
    <!-- The artifact ID refers to the specific module or library that you are adding to your project. The jackson-databind module provides the core functionality for converting between Java objects (POJOs) and JSON (both serialization and deserialization). -->
    <artifactId>jackson-databind</artifactId>
    <!-- Version of jackson-databind is 2.13.2 -->
    <version>2.13.3</version>
    <!-- Scope defines when this dependency is needed. The compile scope means that this dependency is required at compile time and at runtime. -->
    <scope>compile</scope>
</dependency>
```

## 5. What is spring-boot-stater?

what dependecies in the below starter? do you know any starters?

```XML
<dependency>
    
    <!-- 'com.fasterxml.jackson.core' is the group responsible for the Jackson core libraries, which are widely used for JSON parsing and processing. -->
    <groupId>com.fasterxml.jackson.core</groupId>
    
    <!-- 'jackson-databind' is the module that provides data-binding functionality, allowing for the conversion between Java objects (POJOs) and JSON, both serialization (Java to JSON) and deserialization (JSON to Java). -->
    <artifactId>jackson-databind</artifactId>
    
    <!-- Version 2.13.3 -->
    <version>2.13.3</version>
    
    <!-- 'compile' scope means that this library is required during both the compile-time and runtime of your application. -->
    <scope>compile</scope>
    
</dependency>
```

## 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST)? could you list CRUD by this style?

@RequestMapping is a Spring MVC annotation used to map HTTP requests to handler methods in controller classes. It allows you to specify the URL path, HTTP method, and other configurations for mapping incoming web requests to specific Java methods.

1. Create (POST - /users)
    ```Java
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // logic to create user
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    ```

2. Read (Retrieve) (GET - /users or /users/{id})
    ```Java
    // Get all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        // logic to retrieve all users
        return userService.getAllUsers();
    }

    // Get a single user by ID
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // logic to retrieve user by ID
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    ```

3. Update (PUT or PATCH - /users/{id})
    ```Java
    // Full update using PUT
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // logic to update user by ID
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Partial update using PATCH
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<User> partiallyUpdateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // logic to partially update user
        User updatedUser = userService.partiallyUpdateUser(id, updates);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    ```

4. Delete (DELETE - /users/{id})
    ```Java
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // logic to delete user by ID
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    ```

## 7. What is Response Entity? why do we need it?
1. `ResponseEntity<T>` is a class in Spring Framework used to represent the HTTP response (status, headers, and body) of a request. It is a generic class where T is the type of the response body.
2. `ResponseEntity` is useful when you need control over the entire HTTP response, not just the response body. (Control over HTTP status code, Setting custom headers, Returning a response body with status code, Handling Errors)

## 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC.

1. `ResultSet` is an object that holds the result of a SQL query executed against a database. It acts as a cursor, allowing you to iterate over the rows of data returned by the query. The data is retrieved one row at a time, and each column value within the row can be accessed.
2. Flow of Retrieving Data Using JDBC: 
    Load the JDBC Driver (optional in newer JDBC versions).
    Establish a Connection to the database.
    Create a Statement or PreparedStatement object.
    Execute the SQL Query to retrieve the data.
    Process the ResultSet to access the data.
    Close the Resources (i.e., ResultSet, Statement, Connection).

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.
| **Aspect**                  | **Spring Data JPA**                         | **Hibernate**                             | **JDBC**                                  |
|-----------------------------|---------------------------------------------|-------------------------------------------|-------------------------------------------|
| **Level of Abstraction**     | High-level abstraction over JPA (ORM)       | ORM (Object-Relational Mapping) framework | Low-level (direct database access)        |
| **Purpose**                  | Simplifies JPA-based data access with Spring features | Maps Java objects to database tables using JPA or Hibernate API | Direct interaction with relational databases using SQL |
| **Ease of Use**              | Very easy to use with minimal boilerplate; requires no SQL writing for basic operations | Easier than JDBC but requires more configuration than Spring Data JPA | Most verbose; requires explicit SQL queries and manual result set handling |
| **Query Writing**            | Query generation based on method names; supports JPQL, Criteria API, and native SQL | Supports JPQL, HQL (Hibernate Query Language), Criteria API, and native SQL | Requires writing raw SQL queries manually |
| **Transaction Management**   | Integrated with Spring’s transaction management (Declarative and programmatic) | Provides its own transaction management, integrated with Spring | Transactions must be managed manually or integrated with Spring transaction management |
| **Boilerplate Code**         | Minimal; CRUD repositories automatically generated | Reduced boilerplate compared to JDBC, but more than Spring Data JPA | High; requires manual query execution, result set handling, and transaction management |
| **Performance**              | Good for most use cases; has optimizations like caching, lazy loading, and fetch strategies | Excellent for ORM performance; supports caching, lazy loading, and advanced optimization techniques | Can be highly efficient for specific cases (no ORM overhead), but more error-prone and harder to optimize manually |
| **Configuration Complexity** | Low; automatic setup and configuration using Spring Boot or XML | Moderate; requires more configuration, such as entity mappings and session management | Low for setup, but requires more code to handle query execution, result handling, and object mapping |
| **Caching**                  | Supports 1st and 2nd level caching (via Hibernate) | Supports 1st level (session) and 2nd level (shared) caching | No built-in caching support; caching must be implemented manually |
| **Flexibility**              | Flexible; integrates well with other Spring modules, and supports multiple database vendors | Very flexible; supports JPA and Hibernate-specific features, highly configurable | Very flexible but requires manual work for database logic, mapping, and handling |
| **Learning Curve**           | Very low for Spring developers; the simplest to use among the three | Moderate; must understand JPA and Hibernate-specific concepts | High; requires a deep understanding of SQL and database handling |
| **Lazy Loading**             | Supported via Hibernate | Supported out-of-the-box | Not supported; must manually load related entities |
| **Error Handling**           | Simple and consistent exception handling via Spring's `DataAccessException` | Hibernate exceptions can be converted to Spring's `DataAccessException` | Must manually handle SQLExceptions and errors |
| **Integration with Spring**  | Seamless integration; part of the Spring ecosystem | Easily integrates with Spring, but adds some additional configuration | Requires more integration effort with Spring, but supported |
| **Flexibility with Custom Queries** | Very flexible; can use derived queries, JPQL, and native SQL | Flexible; supports HQL, JPQL, Criteria, and native SQL | Flexible, but everything must be written manually in SQL |
| **Batch Processing**         | Supports batch processing through JPA and custom queries | Supports batch processing via Hibernate’s features | Supported but must be managed manually with SQL queries |
| **Complex Querying**         | Supports method name conventions, custom JPQL, Criteria API, and native SQL | Supports complex queries through HQL, JPQL, and native SQL | Supports complex querying, but must write all SQL queries manually |

## 10. Learn how to use ObjectMapper by this example.
The `ObjectMapper` class is the main class of Jackson, which can help us quickly convert various types to Json types.
```Java
ObjectMapper mapper = new ObjectMapper();
String jsonString = "{\"name\":\"xxx\", \"age\":20}";
// json to class
Person person = mapper.readValue(jsonString, Person.class);
// class to json
jsonString = mapper.writeValueAsString(person);
```


## 11. What is the serialization and desrialization?
1. Serialization is the process of converting a data object—a combination of code and data represented within a region of data storage—into a series of bytes that saves the state of the object in an easily transmittable form.
2. Data serialization is the process of converting an object into a stream of bytes to more easily save or transmit it.

## 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```Java
int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
// Calculate average using Stream API
double average = Arrays.stream(numbers)
                        .average()
                        .orElse(0); // Returns 0 if the array is empty

System.out.println("Average: " + average);
```



