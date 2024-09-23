## Homework 7

### 1. Spring Data related annotations:
 - @Entity: Marks a class as an entity to be managed by JPA.
 - @Id: Specifies the primary key of an entity.
 - @GeneratedValue: Specifies how the primary key value is generated.
 - @Repository: Indicates that the class is a Data Access Object (DAO) and facilitates exception translation.
 - @Query: Defines custom database queries using JPQL or SQL.

### 2. DTO, VO, Payload, PO, Model, DAO:

 - DTO (Data Transfer Object): Used to transfer data between different layers, usually between the controller and the service layer.
 - VO (Value Object): Represents a simple, immutable object, typically used to hold and transfer read-only values.
 - Payload: Refers to the actual data or body transferred in a request or response in an API.
 - PO (Persistent Object): Refers to an entity object stored in the database.
 - Model: Represents the data that is handled in the application, often mapped to a database table.
 - DAO (Data Access Object): A design pattern for managing data access logic separately from the application logic.

### 3. What is @JsonProperty("description_yyds"):

 - This annotation is used to map a JSON property to a Java field.

### 4. Explain the purpose of following dependency?

 - This dependency adds the Jackson Databind library, allowing to work with JSON data by converting Java objects to JSON (serialization) and vice versa (deserialization).

### 5. Spring Boot Starter:

A "starter" is a collection of dependencies that simplify project setup. For example, spring-boot-starter-web includes dependencies to create web applications using Spring MVC.

### 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?

- This maps a POST request to the /users endpoint. Other CRUD operations:

 - Create: @RequestMapping(value = "/create", method = RequestMethod.POST)
 - Read: @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
 - Update: @RequestMapping(value = "/update", method = RequestMethod.PUT)
 - Delete: @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)

### 7. ResponseEntity:

 - It represents the HTTP response, including the status code, headers, and body. It is used to control the full HTTP response, especially in REST APIs.

### 8. JDBC ResultSet:

 - A ResultSet is an interface that represents the result set of a query executed on a database. The process:
 - Establish a connection.
 - Create a Statement.
 - Execute a query to get a ResultSet.
 - Iterate through the ResultSet to extract data.

### 9. Spring Data JPA vs Hibernate vs JDBC:

 - Spring Data JPA: A layer built on top of Hibernate that simplifies repository implementation using JPA.
 - Hibernate: An ORM framework that automates object-to-table mapping and simplifies database interaction.
 - JDBC: A low-level API that requires manual handling of SQL queries and result sets.

### 10. ObjectMapper Example:

### 11. Serialization and Deserialization:

Serialization: The process of converting an object into a format that can be stored or transmitted.
Deserialization: The reverse process of reconstructing an object from its serialized format.

### 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

```java
int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
double average = Arrays.stream(numbers).average().orElse(0);
```