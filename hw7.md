#### 1. List all of the Spring data related annotations your learned and explain its usage.
**1. @Entity**
Usage: Marks a class as a JPA entity, indicating that it maps to a table in the database. This is a part of JPA (Java Persistence API).
**2. @Id**
Usage: Specifies the primary key of an entity.
**3. @GeneratedValue**
Usage: Specifies that the value of the primary key is automatically generated (e.g., using auto-increment, sequences).
**4. @Column**
Usage: Used to define a column in the database and customize its attributes like name, length, nullable, etc.
**5. @Table**
Usage: Specifies the table name for an entity. It allows customization of the table name, schema, and other table-level configurations.
**6. @Repository**
Usage: Marks a class as a Data Access Object (DAO) in the Spring Data framework, making it eligible for exception translation and Spring’s persistence management.
**7. @Query**
Usage: Used to define custom JPQL (Java Persistence Query Language) or native SQL queries for repository methods.
**8. @Modifying**
Usage: Applied to repository methods that perform data-modifying queries, such as updates or deletes. Must be used with @Query.
**9. @Transactional**
Usage: Indicates that the method or class should be wrapped with a transaction, allowing rollback and commit for database operations. It ensures data consistency.
**10. @OneToOne, @OneToMany, @ManyToOne, @ManyToMany**
Usage: These annotations define relationships between entities in JPA, like:
@OneToOne: A one-to-one association between two entities.
@OneToMany: A one-to-many association where one entity is related to multiple entities.
@ManyToOne: A many-to-one association where many entities are related to one entity.
@ManyToMany: A many-to-many association between two entities.

#### 2. What is DTO, VO, Payload, PO, model, DAO?
DTO: Used to transfer data between layers.
VO: Immutable object that holds values without identity, often used in domain models.
Payload: The actual data being sent in a request or response, often in JSON or XML format.
PO: Represents data that is persisted in the database.
Model: Represents the core business logic and data of the application.
DAO: Encapsulates database access and provides a structured way to interact with the data layer.

#### 3. What is @JsonProperty("description_yyds")
@JsonProperty is an annotation from the Jackson library (a popular JSON processing library in Java) used to control the serialization and deserialization process of JSON data. It allows customization of how fields in a Java class map to JSON properties and vice versa.

#### 4. Explain the purpose of following dependency?
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.13.3</version>
<scope>compile</scope>
</dependency>
This dependency for jackson-databind is used to include Jackson's data-binding functionality in a Java project. Jackson is a popular JSON processing library, and jackson-databind is a key component that allows automatic conversion between Java objects and JSON (serialization and deserialization).

#### 5. What is spring-boot-stater?
A Spring Boot Starter is a convenient way to include and configure commonly used dependencies in a Spring Boot application. It’s essentially a specialized Maven or Gradle dependency that packages a set of related libraries and configuration into one bundle, allowing you to quickly set up specific functionality in your Spring Boot project without manually specifying and configuring each dependency.

#### 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
The annotation @RequestMapping(value = "/users", method = RequestMethod.POST) is used in Spring MVC to map HTTP requests to handler methods in your controller classes. Specifically, it indicates that this method will handle POST requests to the /users URL.
Create: @RequestMapping(value = "/users", method = RequestMethod.POST)
Get: @RequestMapping(value = "/users", method = RequestMethod.GET)
Put: @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
Delete: @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)

#### 7. What is ResponseEntity? why do we need it?
ResponseEntity is a class in Spring that represents the full HTTP response, including the status code, headers, and body. It is a flexible way to customize and control the response returned from a controller in a Spring MVC or Spring Boot application.

ResponseEntity gives you more control over the HTTP response, allowing you to set the following:
HTTP Status Code: You can specify the exact HTTP status code you want to return (e.g., 200 OK, 404 Not Found, 201 Created).
Response Headers: You can add custom headers to the response.
Response Body: You can return a body with data (typically serialized as JSON or XML).

#### 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
ResultSet is an interface that represents a table of data returned by executing a SQL query. It is essentially a cursor that points to one row of data at a time, allowing you to iterate through the rows and retrieve column values for each row.
**Establish a Connection:** A connection is made to the database using JDBC.
**Execute a Query:** A SQL query (SELECT * FROM users) is sent to the database.
**Retrieve Data Using ResultSet:** The ResultSet object contains the results of the query. You iterate through the rows and columns to extract the data.
**Close Resources:** Finally, close the ResultSet, Statement, and Connection.

#### 9. Compare Spring Data JPA vs Hibernate vs JDBC.

**Level of Abstraction:**
JDBC: Low-level API; manual SQL and connection management.
Hibernate: Medium-level ORM; automatic mapping of Java objects to database tables.
Spring Data JPA: High-level abstraction; repository-based, minimal SQL for CRUD operations.
**Boilerplate Code:**
JDBC: High; requires extensive manual coding.
Hibernate: Reduced; abstracts many repetitive tasks.
Spring Data JPA: Very low; auto-generates basic operations.
**Querying:**
JDBC: SQL-based, manual queries.
Hibernate: Supports HQL/JPQL and Criteria API.
Spring Data JPA: Supports derived query methods and JPQL, with optional native queries.
**Transaction Management:**
JDBC: Manual management required.
Hibernate: Handles transactions automatically with frameworks like Spring.
Spring Data JPA: Integrates seamlessly with Spring's transaction management.
**Learning Curve:**
JDBC: Moderate for basics, complex for advanced usage.
Hibernate: Moderate; requires understanding ORM principles.
Spring Data JPA: Low for CRUD operations, moderate for complex queries.
**Performance:**
JDBC: High performance for simple queries.
Hibernate: Some overhead due to ORM; can be optimized.
Spring Data JPA: Similar to Hibernate, but may introduce slight overhead.

#### 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        double average = Arrays.stream(numbers)
                               .average()
                               .orElse(0.0); 
        System.out.println("Average: " + average);
    }
```