1. List all of the Spring data related annotations your learned and explain its usage.
    1. @Entity
        Usage: Marks a class as a JPA entity, which means it's a domain model that is mapped to a database table.
        ```java
        @Entity
        public class User {
            @Id
            private Long id;
            private String name;
        }
        ```
    2. @Table
        Usage: Specifies the table name in the database that the entity is mapped to. It’s optional if the class name matches the table name.
        ```java
        @Entity
        @Table(name = "users")
        public class User { ... }
        ```
    3. @Id
        Usage: Marks a field in an entity class as the primary key.
        ```java
        @Id
        private Long id;
        ```
    4. @GeneratedValue
        Usage: Specifies how the primary key should be automatically generated. Can be used with strategies like GenerationType.IDENTITY, GenerationType.SEQUENCE, etc.
        ```java
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
        ```
2. What is DTO, VO, Payload, PO, model, DAO?
    1. DTO (Data Transfer Object)
        Definition: 
        DTO is an object used to transfer data between different layers or subsystems of an application (such as between a controller and a service layer or between client and server).
        Characteristics:
        No behavior or logic, only contains fields and getters/setters.
        Often used to reduce network overhead by carrying only necessary data.
        May contain data from multiple entities.
        ```java
        public class UserDTO {
            private String name;
            private String email;
            
            // Getters and setters
        }
        ```
    2. VO (Value Object)
        Definition: 
        VO is an object that represents a value and is immutable. It is often used to model complex concepts or entities by grouping multiple attributes together.
        Characteristics:
        Immutable: Once created, its state cannot change.
        Used to represent values or a set of related attributes.
        Unlike DTO, it usually belongs to the domain model and can contain logic that validates or manipulates its data.
        ```java
        public class AddressVO {
            private final String street;
            private final String city;

            public AddressVO(String street, String city) {
                this.street = street;
                this.city = city;
            }

            // Getters and methods for business logic (e.g., formatting)
        }
        ```
    3. Payload
        Definition: 
        In the context of APIs, payload refers to the actual data that is sent in the request or response body. It is typically the content you send over the wire, such as JSON or XML data.
        Characteristics:
            Contains the data that needs to be processed by an API or service.
            Can be considered similar to DTO, but specifically refers to the data being transmitted over a network.
        ```json
        {
        "name": "John Doe",
        "email": "john.doe@example.com"
        }
        ```
    4. PO (Persistent Object)
        Definition: 
        PO represents an object that is directly mapped to a database table. In other words, it's an object that persists (or is saved) into the database. It's typically an entity object in JPA or Hibernate.
        Characteristics:
            Has a one-to-one mapping with the database schema.
            May include ORM annotations like @Entity, @Table, and @Column.
            Also called Entity in JPA.
        ```java
        @Entity
        @Table(name = "users")
        public class UserPO {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            private String email;

            // Getters and setters
        }
        ```
    5. Model
        Definition: A model represents the core business objects or data in the application. It can refer to the domain model (objects representing business concepts) or the data model (objects that map to database tables). The term "model" is often used generically.
        Characteristics:
        Represents the data or domain logic of an application.
        Used across different layers (persistence, services, etc.).
        Not restricted to persistence; it may also represent objects that aren't stored in a database but are central to business logic.
        ```java
        public class UserModel {
            private String name;
            private String email;
            
            // Business logic or validation can be included
            public boolean isValidEmail() {
                return email.contains("@");
            }
        }
        ```
    6. DAO (Data Access Object)
        Definition: 
        DAO is a design pattern that provides an abstraction for data access. It isolates the persistence logic from the rest of the application. DAO is responsible for CRUD operations (Create, Read, Update, Delete) on the database.
        Characteristics:
            Provides methods to access and manipulate data stored in a database.
            Uses objects like POs (Persistent Objects) or Entities to represent database records.
            Typically interacts with a JPA repository or JDBC in Java applications.
        ```Java
        public interface UserDAO {
        void save(UserPO user);
        UserPO findById(Long id);
        List<UserPO> findAll();
        }
        ```
    **Summary**
    | **Term**      | **Purpose**                                                           | **Characteristics**                                                                                        |
|---------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| **DTO**       | Transfer data between layers or systems                               | Contains only data, no logic, used for data transfer                                                        |
| **VO**        | Represents an immutable value in the domain                           | Immutable, represents a value concept, part of the domain model, can have logic                             |
| **Payload**   | Data sent in an API request or response                               | The actual data being transmitted over a network, often in JSON, XML, or other formats                      |
| **PO**        | Represents a persistent object stored in the database                 | Mapped to database schema (table), can contain ORM annotations, often referred to as **Entity**             |
| **Model**     | Represents core business or data objects                              | Represents domain or business objects, can contain logic, not necessarily tied to persistence               |
| **DAO**       | Provides abstraction for database access and CRUD operations          | Handles persistence logic, abstracts interaction with the database, usually handles **PO** objects          |

3. What is @JsonProperty("description_yyds") 
    
    The @JsonProperty annotation is part of the Jackson library in Java, which is used to control JSON serialization and deserialization. Specifically, it allows you to customize the name of a property when an object is serialized to JSON or deserialized from JSON.

4. Explain the purpose of following dependency?
    ```
        <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.3</version>
        <scope>compile</scope>
        </dependency>
    ```
    The given dependency is for Jackson Databind, which is part of the Jackson library used for JSON processing in Java applications.
5. What is spring-boot-stater? 
    ```
        <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    ```
    A Spring Boot Starter is a convenient dependency mechanism in Spring Boot that simplifies the process of setting up and configuring your project. These starters are a set of pre-defined templates that bring in a bundle of dependencies commonly used for particular functionalities.

    For example, instead of manually configuring various dependencies needed for a web project (like Spring MVC, embedded Tomcat, etc.), you can simply include the spring-boot-starter-web in your pom.xml (Maven) or build.gradle (Gradle) file. This will automatically bring in all the required libraries and settings for a web application.

    Some commonly used Spring Boot starters include:
        1. spring-boot-starter-web: For building web applications, RESTful services, and MVC architectures. It includes Spring MVC and an embedded Tomcat server.
        2. spring-boot-starter-data-jpa: For JPA-based data access with Hibernate.
        3. spring-boot-starter-security: For adding security features like authentication and authorization using Spring Security.
        4. spring-boot-starter-test: For unit and integration testing with libraries like JUnit and Mockito.

6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
    The @RequestMapping annotation in Spring MVC is used to map web requests to specific handler methods in a controller. The annotation can specify the URL, the HTTP method (e.g., GET, POST, PUT, DELETE)
    1. ```java
        @RequestMapping(value = "/users", method = RequestMethod.POST)
        public ResponseEntity<User> createUser(@RequestBody User user) {
            // Logic to create a new user
        }
        ```
    2. ```java
        @RequestMapping(value = "/users", method = RequestMethod.GET)
        public ResponseEntity<List<User>> getAllUsers() {
            // Logic to return all users
        }
        ```
    3. ```java
        @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
        public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
            // Logic to return user by ID
        }
        ```
    4. ```java
        @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
        public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
            // Logic to update user
        }
        ```
7. What is ResponseEntity? why do we need it?
    ```java
    new ResponseEntity<>(postResponse, HttpStatus.OK);
    new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    ResponseEntity.ok(postService.getPostById(id));
    ```

    ResponseEntity is a class in Spring Framework used to represent the entire HTTP response. It allows you to control the status code, headers, and body of the response in a REST API. By using ResponseEntity, you can customize and return responses based on the outcome of the operation or different business logic conditions.

8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
    In JDBC (Java Database Connectivity), ResultSet is an interface that represents the result set of a database query. It provides methods to access and manipulate the data returned by executing a SQL query, such as SELECT. Essentially, it acts as a table of data where each row in the table corresponds to a row in the database result.
    1. Create a Statement
        To execute a SQL query, you need a Statement or PreparedStatement. A Statement is generally used for static queries, while PreparedStatement is preferred for dynamic queries with parameters.
        ```java
        Statement statement = connection.createStatement();
        ```
    2.  Execute the Query
        ```java
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        ```
    3. Process the ResultSet
    ```java
        while (resultSet.next()) {
        int id = resultSet.getInt("id");         // Retrieve data from "id" column
        String name = resultSet.getString("name");  // Retrieve data from "name" column
        System.out.println("ID: " + id + ", Name: " + name);
        }
    ```
    4. Close Resources

9. Compare Spring Data JPA vs Hibernate vs JDBC.
    1. Spring Data JPA
        Overview:
        Spring Data JPA is part of the larger Spring Data ecosystem, and it provides a layer of abstraction on top of JPA (Java Persistence API). It simplifies database access by reducing the boilerplate code needed for repository interactions. It uses Hibernate as the default JPA provider underneath.

        Abstraction: Spring Data JPA abstracts most of the repetitive code, allowing you to focus on the business logic rather than writing explicit SQL queries or managing sessions.

        Ease of Use:

        Uses repository patterns with minimal custom code, like CrudRepository, JpaRepository.
        Provides methods like save(), findAll(), findById(), etc., without needing to write any SQL or HQL (Hibernate Query Language).
        Key Features:

        Automatic Query Generation: Automatically generates SQL queries based on method names, such as findByUsername().
        Pagination and Sorting: Built-in support for paginating and sorting records using methods like findAll(Pageable pageable).
        Integration with Spring Boot: Works seamlessly with Spring Boot and other Spring projects.
        Configuration:

        Annotations: Uses JPA annotations like @Entity, @Table, @Id, and JPA repository interfaces like JpaRepository.
        Requires minimal configuration when combined with Spring Boot, as many defaults are auto-configured.
        Performance:

        Performance is usually on par with Hibernate (since it uses Hibernate by default) but can suffer if you rely heavily on autogenerated queries without proper optimization or indexing.
        When to Use:

        When working with Spring applications and you want to abstract data access with minimal code.
        For rapid development and when you need to use a repository-based design.
    2. Hibernate
        Overview:
        Hibernate is an ORM (Object-Relational Mapping) framework that simplifies the interaction between Java objects and relational databases. It implements JPA specifications but offers additional features beyond the standard JPA.

        Abstraction: Hibernate provides an abstraction layer over raw SQL by allowing developers to manipulate data through Java objects, which it maps to database tables.

        Ease of Use:

        Hibernate makes it easier to perform CRUD operations by eliminating the need to write raw SQL and by providing methods for object manipulation.
        It uses HQL (Hibernate Query Language) for more advanced queries.
        Key Features:

        Caching: Hibernate provides first-level and second-level caching, which can significantly boost performance by reducing database calls.
        Lazy Loading: It supports lazy loading for large datasets, meaning that related data isn't fetched until it's explicitly requested.
        Inheritance Mapping: Hibernate supports complex mappings, including inheritance between entities, which is not easily handled with JDBC.
        Criteria API: Hibernate offers a criteria API for dynamically building queries without the need for HQL or SQL.
        Configuration:

        Annotations: Uses JPA annotations such as @Entity, @Table, @Id, but also provides Hibernate-specific annotations like @Lazy, @Cache, @BatchSize.
        Configuration files (hibernate.cfg.xml) or properties can be used to set up the Hibernate session and database connection.
        Performance:

        Hibernate's first-level and second-level caching mechanisms can enhance performance.
        Lazy loading and batch fetching help reduce memory usage and load times when handling large datasets.
        When to Use:

        When you need full control over ORM functionality and advanced caching, lazy loading, and batch operations.
        For applications with complex data relationships and requirements like object inheritance, caching, and large-scale data operations.
    3. JDBC (Java Database Connectivity)
        Overview:
        JDBC is the foundational API in Java for directly interacting with databases using SQL. It provides a low-level API for executing SQL statements and processing result sets.

        Abstraction: None. JDBC is a low-level API and does not provide any abstraction over the database operations. The developer must write all SQL queries and manage database connections, statements, and result sets manually.

        Ease of Use:

        JDBC is more verbose and complex compared to Spring Data JPA and Hibernate.
        You need to handle boilerplate code like opening connections, managing transactions, handling exceptions, and closing resources.
        Key Features:

        Direct SQL Execution: You write your own SQL queries and directly execute them using Statement or PreparedStatement.
        Fine-Grained Control: JDBC gives you the most control over the exact SQL being executed and database interactions.
        Configuration:

        Requires database-specific drivers to establish connections (DriverManager or connection pooling).
        No annotations are used; everything is done programmatically.
        Performance:

        JDBC can be very efficient, but performance optimization depends entirely on the developer’s SQL writing, query optimization, and resource management.
        Lacks built-in caching or lazy loading features.
        When to Use:

        When you need complete control over the SQL execution and you are comfortable writing complex queries yourself.
        For small applications or when you don't want to introduce additional layers of abstraction, such as ORM frameworks.
        Suitable for applications that do not have complex object relationships or need fine-grained control over database transactions.

12. Use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

    ```java
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
    
        double average = Arrays.stream(numbers)
                                .average()
                                .orElse(0); 
    ```