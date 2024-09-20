
# Springboot RUD Exercises

1. **List all of the Spring data related annotations your learned and explain its usage.**

- @Entity: Marks a class as a JPA entity, which means it is mapped to a database table.
- @Table: Specifies the table in the database that this entity is mapped to. It can also specify unique constraints.
- @Id: Marks a field as the primary key of the entity.
- @GeneratedValue: Specifies how the primary key should be generated. Common strategies include AUTO, IDENTITY, SEQUENCE, and TABLE.
- @Column: Specifies the details of the column to which a field will be mapped. It can define constraints such as nullable, unique, and length.
- @ManyToOne, @OneToMany, @OneToOne, @ManyToMany: These annotations define the relationships between entities. They can be combined with @JoinColumn or @JoinTable to customize the database mapping.
- @JoinColumn: Specifies the foreign key column for an entity relationship.
- @Repository: Marks the class as a Spring Data repository, which is responsible for data access.
- @Query: Defines a custom query using JPQL (Java Persistence Query Language).
- @Transactional: Specifies that a method should be executed within a transaction.
- @CreationTimestamp & @UpdateTimestamp: Automatically sets the creation and update timestamps when an entity is persisted or updated.
- @Modifying: Used with @Query for defining queries that modify the data (e.g., UPDATE, DELETE).

2. **What is DTO, VO, Payload, PO, model, DAO?**
- DTO: Data Transfer Object. DTOs are objects used to transfer data between layers or systems. They typically contain only data and no business logic.
- VO: Value Object. A Value Object is an object that represents a simple entity with no identity. It's immutable and is defined by its attributes rather than an ID.
- Payload: A payload refers to the actual data that is transmitted over the network during a request or response in a web service or API. In the context of REST APIs, payload typically refers to the request or response body.
- PO: Persistent Object. A Persistent Object is an object that is saved in a database. It's typically used to represent entities that need to be stored or retrieved from a database.
- Model: Model is broadly used to represent the domain-specific objects that the application operates on. In an MVC (Model-View-Controller) pattern, the "Model" represents the business data and rules.
- DAO: Data Access Object. DAO is a design pattern used to abstract the data access logic from the business logic of an application. DAOs contain methods to interact with the database, such as CRUD (Create, Read, Update, Delete) operations.

3. **What is `@JsonProperty("description_yyds")`?**
@JsonProperty("description_yyds") is used to control how JSON property names are mapped to and from Java fields when using Jackson.

4. **Explain the purpose of the following dependency?**
    ```xml
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.3</version>
      <scope>compile</scope>
    </dependency>
    ```
    The jackson-databind dependency is essential for handling JSON data in Spring Boot or any Java application. It allows you to easily convert between JSON and Java objects, which is vital for building RESTful APIs, handling external data formats, and exchanging data with client applications.

5. **What is `spring-boot-starter`?**
   - What dependencies in the below starter? Do you know any starters?


6. **Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)`**
   - Could you list CRUD operations in this style?

7. **What is ResponseEntity? Why do we need it?**
The spring-boot-starter mechanism simplifies dependency management by bundling required libraries for specific functionality in a Spring Boot application. Instead of manually specifying all dependencies, you include the necessary starter, and Spring Boot brings in everything you need for that functionality.

8. **What is ResultSet in JDBC?**
ResultSet is an object that holds the data retrieved from a database after executing a query. It is essentially a table of data representing the results of a SQL query. The data in the ResultSet can be accessed row by row, and each row consists of the values of the columns that were retrieved by the query.
   - Describe the flow to get data using JDBC.  
        1. Load the JDBC Driver
        2. Establish a Database Connection
        3. Create a Statement
        4. Execute the Query
        5. Process the ResultSet
        6. Close the ResultSet, Statement, and Connection


9. **Compare Spring Data JPA vs Hibernate vs JDBC.**
- Spring Data JPA: Spring Data JPA is a part of the larger Spring Data project. It is an abstraction layer built on top of JPA (Java Persistence API) to simplify database access using repository patterns. It integrates with Spring Boot and reduces boilerplate code by offering ready-to-use interfaces for common database operations.
- Hibernate: Hibernate is an ORM (Object-Relational Mapping) framework that implements the JPA specification. It provides a way to map Java objects to database tables and handles CRUD operations on those objects without manually writing SQL queries.
- JDBC: JDBC is a low-level API for connecting Java applications directly to databases. It allows developers to execute SQL queries, retrieve results, and handle database transactions programmatically.  
  
  Spring Data JPA is great for projects using Spring that require rapid development and high-level abstractions for common database operations.
  Hibernate offers a full-featured ORM solution with more control over the mapping between Java objects and database tables, ideal for complex applications.
  JDBC provides the most control but requires more manual work. It's suited for simple applications or scenarios where performance and fine-grained control are essential.



10. **Learn how to use ObjectMapper with this example:**
    - [Example on GitHub](https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java)
    1. Add Dependencies
    ```
    <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    </dependency>
    ```
    2. Import ObjectMapper
    ```
    import com.fasterxml.jackson.databind.ObjectMapper;
    ```
    3. Do the Serialization
    4. Do the Deserialization


11. **What is serialization and deserialization?**
    - [Serialization Glossary](https://hazelcast.com/glossary/serialization)
- Serialization is the process of converting a Java object (or any object in general) into a format that can be easily stored or transmitted, such as a file, database, or over a network. The object is transformed into a byte stream or JSON, XML, or other human-readable formats.
- Deserialization is the reverse process of serialization. It takes the byte stream or other formats (e.g., JSON, XML) and reconstructs it back into the original object.


12. **Use Stream API to get the average of the array `[20, 3, 78, 9, 6, 53, 73, 99, 24, 32]`.**
    ```
    int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
    double average = Arrays.stream(numbers).average().orElse(0);
    System.out.println(average);
    ```

13. **Understand the code in the following repositories:**
    - [Post Pageable Code](https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable)
    - [Comment Code](https://github.com/TAIsRich/springboot-redbook/tree/04_comment)

    The Post Pageable Code repository demonstrates how pagination is implemented in a Spring Boot application, using Spring Data JPA's Pageable and PageRequest classes to retrieve a subset of posts. The controller method accepts parameters like page number and size to return paginated data, encapsulated in a Page<PostDto> object, which includes pagination metadata such as the current page and total pages. The Comment Code repository focuses on managing comments related to posts, where a Comment entity is associated with a Post through a ManyToOne relationship. The service and repository layers handle business logic and database operations for comments, allowing comments to be added to or retrieved by posts. Both examples showcase the use of Spring Data JPA to simplify data access and management.






