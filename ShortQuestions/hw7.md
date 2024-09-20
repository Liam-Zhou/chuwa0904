## 1. List all of the Spring data-related annotations you learned and explain their usage.
- **@Entity**: Marks a class as a GPA entity representing a table in the database
- **@Table**: Specifies the table name for an entity
- **@Id**: refers the primary key of the entity
- **@GeneratedValue**: indicates that the value of the primary key is automatically generated
- **@Column**: Specifies the mapped column for a field in an entity
- **@ManyToOne, @OneToMany , @ManyToMany, @OneToOne**: Define relationship between entities
- **@Repository**: Marks the class as a Data Access Object
- **@Autowired**: Automatically injects dependencies in a Spring managed bean
- **@Service**: Indicates that the class is service

## 2. What is DTO, VO, Payload, PO, model, DAO?
- **@DTO**: An object that carries data between processes to reduce the number of method calls
- **@VO**: An object contains values that are immutable
- **@Payload**: the data sent in the body of an HTTP request or response
- **@PO**: An object that represents the data entity and its state
- **@model**: Represents the data in the MVC architecture
- **@DAO**: An object that provides an abstract interfaces to the database

## 3.What is @JsonProperty("description_yyds")?
it is used to specify the JSON field name for Java object field during serialization and deserialization. "description_yyds" in JSOn will be mapped to the corresponding Java field 


## 4. xplain the purpose of the following dependency?
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```
The example shows dependency jackson-databind and spring-boot-starter-web

## 5.What is spring-boot-stater?
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
Spring Boot Starters are dependency descriptors that group libraries together. Also, it can include the necessary libraries to the project.

## 6.Explain @RequestMapping(value = "/users", method = RequestMethod.POST)?
- **@RequestMapping**: is used to map HTTP request to handler method in a controller. it maps POST requets to /users to specific method


## 7. What is ResponseEntity? Why do we need it?
```
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```
- **@ResponseEntity**: is used to represent the HTTP response, including the status code, headers, and body.customizing the response data and HTTP status codes, so we know what is going on.


## 8. What is ResultSet in JDBC? Describe the flow of getting data using JDBC.
- **ResultSet**: is an interface that represents the result of a SQL query
  - Load the JDBC driver
  - Establish connection to the database
  - Create a statement object
  - Execute a query and get the ResultSet
  - iterate the ResultSet
  - close the connection

## 9.Compare Spring Data JPA vs Hibernate vs JDBC.
- **Spring Data JPA**: Provides a higher abstraction over JPA, simplifying database operations.
- **Hibernate**: A JPA implementation, providing powerful features like lazy loading, caching, and more.
- **JDBC**: A lower-level API for direct database interaction, providing more control but requiring more boilerplate code

## 10. Learn how to use ObjectMapper by this example.
- **ObjectMapper** : use readValue to convert the JSON response from the API into Java objects


## 11. What is the serialization and desrialization?
- **Serialization**: Converts an object into a byte stream for storage or transmission.
- **Deserialization**: Converts a byte stream back into an object.


## 12. Use Stream API to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```
int[] array = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
double average = Arrays.stream(array).average().orElse(0);
```

## 13.抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码



## 14.抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码

