# Spring Boot Notes

### 1. List all of the Spring data related annotations you learned and explain its usage.
* `@Entity`: Specifies that the class is an entity and is mapped to a database table.
* `@Table`: Specifies the table name in the database to which the entity is mapped.
* `@Id`: Denotes the primary key of the entity.
* `@GeneratedValue`: Specifies how the primary key should be generated (e.g., auto-increment).
* `@Column`: Specifies the column mapping for a field in the entity.
* `@Transient`: Specifies that a field should not be persisted to the database.
* `@OneToMany`: Defines a one-to-many relationship between two entities.
* `@ManyToOne`: Defines a many-to-one relationship between two entities.
* `@OneToOne`: Defines a one-to-one relationship between two entities.
* `@ManyToMany`: Defines a many-to-many relationship between two entities.
* `@JoinColumn`: Specifies the foreign key column for a relationship.
* `@Query`: Defines a custom query for the repository method.
* `@Modifying`: Used in combination with `@Query` for update/delete queries.
* `@Transactional`: Ensures that the method executes within a transaction.
* `@Repository`: Indicates that the class is a repository, responsible for data access.
* `@RepositoryRestResource`: Exposes a repository as a RESTful resource.
* `@PersistenceContext`: Injects an EntityManager instance for working with entities.


### 2. What is DTO, VO, Payload, PO, Model, DAO?
* **DTO (Data Transfer Object)**: Transfers data between layers without business logic.
* **VO (Value Object)**: Represents immutable data without identity.
* **Payload**: Data sent in network requests/responses.
* **PO (Persistent Object)**: An object that is stored in the database.
* **Model**: Represents the business logic and data in an application.
* **DAO (Data Access Object)**: Handles database operations, abstracting data access logic.


### 3. What is @JsonProperty("description_yyds")?
* **@JsonProperty**: 
  This annotation is used to map a JSON property to a Java field. In this case, it maps the JSON key `"description_yyds"` to a Java field, allowing the field name in the Java class to differ from the key name in the JSON data.


### 4. Explain the purpose of the following dependency?
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```
* **Answer**: 
The `jackson-databind` dependency is used to handle JSON serialization and deserialization in Java. It allows you to convert Java objects into JSON and parse JSON data into Java objects.


### 5. What is spring-boot-starter?
1. What dependencies are in the below starter? Do you know any other starters?
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
* **Answer**: 
The `spring-boot-starter-web` includes all the necessary dependencies to build web applications, including:
- Spring MVC for building RESTful services.
- Embedded Tomcat as the default web server.
- Jackson for JSON serialization and deserialization.

#### Other Common Starters:
- `spring-boot-starter-data-jpa`: For JPA and Hibernate support.
- `spring-boot-starter-security`: For Spring Security.
- `spring-boot-starter-test`: For testing, including JUnit and Mockito.

### 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST)? Could you list CRUD by this style?
* _Answer_:
`@RequestMapping` maps HTTP requests to controller methods. The `value` specifies the URL, and `method` specifies the HTTP method.   
@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)   
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)   
@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)

### 7. What is ResponseEntity? Why do we need it?
```java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```
* _Answer_:
`ResponseEntity` is a class in Spring that represents the entire HTTP response, including the status code, headers, and body. It is useful when you need full control over the HTTP response in your REST API.

#### Why do we need it?
- To set custom HTTP status codes (e.g., `201 Created`).
- To return different status codes based on different conditions.
- To include custom headers or manipulate the response body.

### 8. What is ResultSet in JDBC? Describe the flow of how to get data using JDBC.
* _Answer_:
`ResultSet` is an interface in JDBC that represents the result set obtained from executing a SQL query. It provides methods to retrieve and navigate through the data.

#### Flow to get data using JDBC:
1. **Load the JDBC Driver**:
   ```java
   Class.forName("com.mysql.jdbc.Driver");
   ```
2. **Establish a Connection**:
   ```java
   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", "username", "password");
   ```
3. **Create a Statement**:
   ```java
   Statement stmt = conn.createStatement();
   ```
4. **Execute a Query**:
   ```java
   ResultSet rs = stmt.executeQuery("SELECT * FROM users");
   ```
5. **Process the ResultSet**:
   ```java
   while (rs.next()) {
       String name = rs.getString("name");
       int age = rs.getInt("age");
   }
   ```
6. **Close Resources**:
   ```java
   rs.close();
   stmt.close();
   conn.close();
   ```

`ResultSet` allows reading the query results row by row and accessing each column by name or index.



### 9. Compare Spring Data JPA vs Hibernate vs JDBC.

| Feature                   | Spring Data JPA                         | Hibernate                           | JDBC                                 |
|---------------------------|-----------------------------------------|-------------------------------------|--------------------------------------|
| **Abstraction Level**      | High-level abstraction over JPA         | ORM framework                       | Low-level SQL query execution        |
| **Ease of Use**            | Easiest to use with repositories        | Requires more configuration         | Requires manual query writing        |
| **Query Language**         | JPQL or derived queries                 | HQL (Hibernate Query Language)       | SQL                                  |
| **Performance Tuning**     | Good with JPA features (lazy loading)   | Good, supports caching              | Requires manual optimization         |
| **Boilerplate Code**       | Minimal, uses repository pattern        | Moderate, but still less than JDBC  | Requires significant boilerplate     |
| **Integration**            | Easily integrates with Spring ecosystem | Integrates well with Spring         | Basic integration, needs more work   |
| **Transaction Management** | Supported via Spring annotations        | Native support, integrates with JPA | Needs manual transaction management  |

#### Summary:
- **Spring Data JPA**: Easiest and most abstracted, minimal boilerplate, good for rapid development.
- **Hibernate**: Powerful ORM framework, gives more control over SQL generation and caching, but with more complexity.
- **JDBC**: Offers the most control but requires writing SQL manually, leading to more boilerplate and less convenience.


### 10. Learn how to use ObjectMapper by this example:
* [GitHub Example Link](https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java)
```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree();
```
* _Answer_:
The `ObjectMapper` class from Jackson is used for converting Java objects to JSON and vice versa.


### 11. What is serialization and deserialization?
* [Hazelcast Glossary](https://hazelcast.com/glossary/serialization/)
* _Answer_:
- **Serialization**: The process of converting a Java object into a format (like a byte stream) that can be stored in a file, database, or transmitted over a network.
- **Deserialization**: The reverse process of converting the stored or transmitted data back into a Java object.

#### Example:
```java
// Serialization
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
out.writeObject(object);

// Deserialization
ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
Object obj = in.readObject();
```

Serialization is commonly used for sending data over networks, storing in files, or caching objects.

### 12. Use Stream API to get the average of the array: [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
* _Answer_:
```java
int[] array = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
double average = Arrays.stream(array).average().OrElse(0);
```

### 13. 抄写并理解 [GitHub Code - 03_post_pageable](https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable)


### 14. 抄写并理解 [GitHub Code - 04_comment](https://github.com/TAIsRich/springboot-redbook/tree/04_comment)

1. **Why does IntelliJ give a warning about constructor injection?**
   - Constructor-based injection is preferred for better testability and immutability. Field injection can be less reliable.

2. **How many ways can we do Dependency Injection?**
   - **Constructor Injection**: Best practice.
   - **Setter Injection**: Through setters.
   - **Field Injection**: Using `@Autowired` on fields.

3. **Which way is the best?**
   - **Constructor-based injection**: Ensures immutability and easier testing.

4. **Which API retrieves the content of a post?**
   - The **GET** API: 
   ```java
   @GetMapping("/posts/{postId}/comments")
   ```

5. **Which API is called when submitting a comment?**
   - The **POST** API:
   ```java
   @PostMapping("/posts/{postId}/comments")
   ```

6. **Why is `postId` a `@PathVariable` and not a request parameter?**
   - **`@PathVariable`** fits REST principles, making the post ID part of the URL and resource path, making it more intuitive.
