# Hw7
### Question 1
* @Entity: Mark a class as JPA entity. And Spring Data JPA uses this annotation to map the class to the corresponding table in the database.
* @Table: Specifites the anem of the table int the database to which this entity will be mapped. It is used when the entity calss doesn't match the table name (e.g.@Table(name = "{table_name}"))
* @Id: Marks a field as the primary key of the entity. It is required in every JPA entity to identify the primary key field.
* @GeneratedValue: It defines the strategy for how the primary key should be generated.
* @Column: Customizes the column mapping for a specific field in an entity like specify column details like name, length, or whether the column is nullable.
* @ManyToOne, @OneToMany, @OneToOne, @ManyToMany: Specifies how entities relate to each other in the database
* @JoinColumn: Specifies the column that joins two tables in a foreign key relationship.Used in combination with annotations like @ManyToOne or @OneToOne to specify the foreign key.
* @Repository: Marks a class as a repository. It indicates that the class provides mechanisms for data access, often used in data access objects (DAO).
* @Query: Used to define custom JPQL or SQL queries for specific methods in a repository. Allows custom database queries when standard method names in JpaRepository aren't sufficient. 
* @Modifying: Marks a query method that modifies data in the database (like an UPDATE or DELETE query).Used in combination with @Query to specify that the query performs an update or delete operation.
* @Transactional: It ensures that a sequence of operations is handled as a single transaction. Used for manages transactions at the method or class level.
* @EntityListeners: Specifies a class that listens for entity lifecycle events (e.g., pre-persist, post-persist).
* @PrePersist, @PostPersist, @PreUpdate, @PostUpdate: They handel tasks like auditing, logging, or validation before or after a particular lifecycle event. They are used to trigger events during the lifecycle of an entity
* @Version: Marks a field as a version field
* @Lob: Marks a field for storing large objects, such as binary data (BLOB) or text (CLOB).

### Question 2
* DTO(Data Transfer Object): It is an object that used to encapsulate data and transfer between layers of an application
* VO (Value Object): A VO is an immutable object that represents a value or a set of related attributes. Unlike DTOs, VOs focus on equality based on the values of the properties, not the identity (like primary keys).
* Payload: In the context of APIs, Payload is the data sent or received in a request. It can be considered the actual data transmitted over the network, typically in JSON or XML format in RESTful services.
* PO (Persistent Object): A PO (Persistent Object) is an object that is typically stored in a database. It represents the object that are persisted to the database and are often managed by an ORM (Object Relational Mapping) framework like Hibernate.
* Model: It represent objects that are part of the domain or business logic layer of an application.
* DAO (Data Access Object): It is an object or an interface that provides access to an underlying database without exposing database details.

### Question 3 
@JsonProperty is for customizing the serialization and deserialization process of JSON objects. It allows you to map a Java object’s field or property to a specific JSON property, providing flexibility and control over the JSON representation.

### Question 4
It's purpose is to include Jackson Databind in your project. Jackson Databind provides functionality for converting Java objects to and from JSON

### Question 5
Spring Boot Starter is a pre-configured set of dependencies that simplifies building Spring applications by bundling common libraries and frameworks required for a particular functionality.
The starter blow includes:
* Spring MVC: For building web applications using the Model-View-Controller (MVC) design pattern.
* Jackson: For converting Java objects to and from JSON
* Tomcat: An embedded web server, allowing the application to run standalone.
* Spring Framework Core: Provides the foundational features of Spring.
* Hibernate Validator: For validation of request parameters, bodies, and model objects using annotations like @Valid and @NotNull.

Other Common Spring Boot Starters:
* spring-boot-starter-data-jpa: Used for working with relational databases using Spring Data JPA and Hibernate.
* spring-boot-starter-security: Adds security features to your application using Spring Security.
* spring-boot-starter-test: Provides libraries for testing Spring Boot applications.
* spring-boot-starter-logging: Provides logging capabilities using Logback and SLF4J.

### Question 6
The @RequestMapping annotation in Spring MVC is used to map web requests (HTTP requests) to specific handler methods in controller classes. 
(value = "/users", method = RequestMethod.POST) means: The method is mapped to handle HTTP POST requests at the URL /users.

