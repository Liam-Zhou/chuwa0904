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

### Question 7
ResponseEntity is a class in Spring Framework that represents an HTTP response,including the response body, HTTP status code, and headers. It allows us to have full control over the HTTP response. Specifically, it lets you customize the HTTP status code, Add HTTP headers, return response body, flexible handling of different scenarios

### Question 8 
In JDBC (Java Database Connectivity), a ResultSet is an object that represents the data retrieved from a database after executing a query, typically a SELECT statement. It is essentially a table of data that holds the results of a database query and allows you to iterate over the rows and retrieve column values.
How to Get Data Using JDBC:
1. Load the JDBC Driver: After Java 6, you ususally don't need to load the JDBC driver manually.
2. Establish a connection using DriverManager.getConnection()
3. Create a statement or preparedStatement, PreparedStatement allows you to execute parameterized queries. Use connection.createStatement() or connection.prepareStatement()
4. Execute the Query using the executeQuery() method. This method returns a ResultSet containing the results of the query.

### Question 9
#### Spring Data JPA
* Spring Data JPA provides an abstraction over JPA and Hibernate, allowing you to perform most CRUD operations without writing any SQL or HQL. 
* *It uses repositories (e.g. JpaRepository) to handle CRUD operations automatically, with minimal code required.
* Spring Data JPA minimizes boilerplate code to almost zero for basic CRUD operations. The repository interfaces handle most of the work, and there is no need to write SQL or session management code.
#### JDBC
* JDBC provides direct access to the database. You manually manage database connections, SQL queries, and result sets.
* But we need to write raw SQL queries for all database operations.
* JDBC requires a lot of boilerplate code for managing connections, preparing statements, handling exceptions, and processing result sets. Every query involves significant manual work.
#### Hibernate
* Hibernate abstracts the database layer using object-relational mapping (ORM), allowing developers to interact with databases using Java objects instead of raw SQL.
* Queries are written in HQL, which operates on entity objects rather than directly on database tables
* Hibernate reduces boilerplate code by automatically managing entity persistence, session handling, and SQL generation. However, it still requires some code for managing sessions and transactions.

### Question 10
Create instance first
```
private static ObjectMapper objectMapper = new ObjectMapper();
```
Deserializing JSON with readValue: The method readValue is used to convert JSON strings into Java objects.
```
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
```
Serializing Java objects to JSON with writeValueAsString:
```
String s = objectMapper.writeValueAsString(foodOutlet); // FoodOutlet object is serialized back into a JSON string 
System.out.println(s);
```
After deserialization, the data within the FoodOutlet object can be accessed using getData() and process through Stream API.
```
List<Data> datas = foodOutlet.getData();
List<String> collect = datas.stream().filter(data -> data.getEstimated_cost() <= maxCost)
                                     .map(data -> data.getName())
                                     .collect(Collectors.toList());
```
The API may return data in paginated format, so the code is designed to handle multiple pages.
```
int total_pages = foodOutlet.getTotal_pages();
for (int i = 2; i <= total_pages; i++) {
    URL_Addr = BASE_URL + "&page=" + i;
    resBody = callURL(URL_Addr);
    strings = processData(resBody, maxCost);
    res.addAll(strings);
}
```
The total_pages field is extracted from the first response. The code then loops through subsequent pages, retrieving and processing additional data until all pages have been handled.

### Question 11
Serialization is the process of converting a data object—a combination of code and data represented within a region of data storage—into a series of bytes that saves the state of the object in an easily transmittable form. In this serialized form, the data can be delivered to another data store.
Deserialization is the reverse process of serialization. It converts a serialized format (JSON, XML, binary, etc.) back into a usable object within a programming environment.

### Question 12
```
        int[] nums = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        int ans = Arrays.stream(nums).sum();
        System.out.println(ans);
```

### Question 13 & 14
I have read, retyped, and understand the code provided
