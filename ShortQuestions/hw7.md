# HW7

haifeng yang

## 1. Spring Annotations

### @Repository

It indicates that class provides the mechanism for CRUD operations or database interaction. Spring will automatically handle exceptions related to database operations with this annotation added.

~~~java
@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
~~~

### @Entity

Marks a class as a JPA entity. The class will be mapped to a database table, and each instance will represent a row in that table.

~~~java
@Entity
public class User {
    @Id
    private Long id;
}
~~~

### @Table

Specify the table in the database to which entity is mapped. It is **optional** and only needed if the table name differs the class name.

~~~java
@Entity
@Table(Name = "users")
public class User{}
~~~

### @Id

Makrs the field as the primary key of the entity. Mandatory in any JPA entity

~~~java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
~~~

### @GeneratedValue

Specify how the primary key should be automatically generated (identify, sequence, auto)

~~~java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
~~~

### @Column

Maps a field to a column in the database. Specify the column name, length, uniqueness etc.

~~~java
@Column(name = "user_name", nullable = false)
private String username;
~~~

### @Query

Defines a custom JPQL or native SQL query for repository methods. It allows you to write custom queries in repositories.

~~~java
@Query("SELECT u FROM User u WHERE u.username = :username")
User findByUsername(@Param("username") String username);
~~~

### @Modifying

Used with @query for modifying queries(e.g. update, delete operations). It indicates that the method should perform a data-modifying operation. 

~~~java
@Modifying
@Query("UPDATE USER u SET u.active = fakse WHERE u.id = id")
void deactivateUser(@Param("id") Long id);
~~~

### @Transactional

It ensures **A unit of work, aka. A sequence of operations** (like database updates or inserts) is completed successfully. If anything goes wrong, the entire sequence is rolled back, leaving the system in its previous consistent state.

~~~java
@Transactional
public void saveUser(User user) {}
~~~

### @PersistenceContext

Inject the EntityManager into a repository or service for managing persistence operations in JPA (e.g. merging, finding entities)

~~~java
@PersistenceContext
private EntityManager entityManager;
~~~

### @JoinColumn

Specify the foreign key column in a relationship between two entities

~~~java
@ManyToOne
@JoinColumn(name = "department_id")
private Department department
~~~

### @OneToMany, @ManyToOne, @OneToOne, @ManyToMany

Define relationships between entities. These annotaions manage how entities relate to each other.

~~~java
@OneToMany(mapped = "user")
private List<Order> orders;
~~~

### @Audited (Hibernate Envers)

Enables auditing of an entity's changes, help track history. Hibernate Envers automatically creates a corresponding audit table for that entity. Snapshot and timestamp.

~~~java
@Entity
@Audited
public class User{
    @Id
    private Long id;
    
    private String itemName;
    // We can exlucde the audit field
    @NotAudited
    private String tempStatus;  // This field won't be audited
}
~~~

~~~java
// we can also only audit part of the data
@Entity
public class Product {
    @Id
    private Long id;
    
    @Audited
    private String name;
    
    private double price;  // Not audited
}
~~~



## 2. What is DTO, VO, Payload, PO, model, DAO?

### DTO (Data Transfer Object)

A Single model class that encapsulate other different objects into one. When transfering data between layers, particularly between client and server in distributed system (e.g. REST APIs).

~~~java
public class UserDTO {
    private String name;
    private String email;
    // Getters and Setters
}
~~~



### VO (Value Object)

An object that holds a value, and its identity does not matter. VO are immutable and are often used to represent domain concepts like money, dates, and quantities.

**Usage**: Value Objects do not have unique identifiers and are interchangeable if they have the same values. In Domain-Driven Design (DDD), VOs help encapsulate concepts in a clean and reusable way.

**Example**: A `Money` value object might represent an amount and currency in a financial application.

~~~java
public class Money {
    private final BigDecimal amount;
    private final String currency;
    // Constructor, equals(), and hashCode() methods
}
~~~



### Payload

**Definition**: Payload refers to the body of data being transferred over the network in the context of request or response. Can be raw data like JSON, XML, or binary data. That is transported via an API.

~~~json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
~~~

### PO (Persistent Object or Plain Old Object)

**Definition**: An object that represents data stored in a persistent storage, such as a database.

~~~java
@Entity
public class Employee {
    @Id
    private Long id;
    private String name;
    private String department;
    // Getters and Setters
}
~~~

### Model

**Definition**: Object that represents the business **logic** or **domain** of the application. Encapsulate the application's core logic and interacts with the database or data stores.

**Usage**: Models are central to the MVC (Model-View-Controller) design pattern.

**Example**: In an e-commerce system, a `Product` model might represent an item for sale and contain logic for pricing and availability.

~~~java
public class Product {
    private String name;
    private BigDecimal price;
    private int stockQuantity;
    // Business logic methods
}
~~~

### DAO (Data Access Object)

It is a design pattern used to abstract and encapsulate all access to the data source. DAO manages the connection to the data source to perform CRUD operations. It separate the persistence logic from business logic.

**Usage:** DAOs makes it easier to change or swap the underlying data source (e.g. switch from MySQL to MongoDB) without affecting the business logic of the application

~~~java
public interface UserDAO {
    void createUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
}
~~~

### Summary:

- **DTO**: Transfers data between systems/layers (usually across networks).
- **VO**: Represents a value without concern for identity; typically immutable.
- **Payload**: The body of data being transferred in a request or response, often in JSON/XML.
- **PO**: Refers to persistent data mapped to a database or a simple object (POJO).
- **Model**: Represents business domain and logic, often used in MVC patterns.
- **DAO**: Provides an abstraction layer for database access, encapsulating CRUD operations.

## 3. What is @JsonProperty("")

It is annotation used to **map JSON property names to JAVA object** fields. If a JSON field has a different name than the corresponding Java field, this annotation ensure the two can still be linked.

~~~java
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("full_name")
    private String name;

    @JsonProperty("user_email")
    private String email;

    // Constructors, getters, and setters
}

~~~

## 4. Purpose of Dependency

~~~xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
~~~

It adds the jackson-databind dependency, which is used to easily do serilization/deserialization. This is extremely useful for communication occurs through JSON.

**Example**

~~~java
ObjectMapper objectMapper = new ObjectMapper();
User user = new User(1, "John Doe", "john.doe@example.com");
String jsonString = objectMapper.writeValueAsString(user);
System.out.println(jsonString);
~~~

Will output the following JSON string:

~~~json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
~~~

And it can be deserialzed:

~~~java
User user = objectMapper.readValue(json, User.class);
~~~

## 5. Spring-boot-starter

It is a dependency of spring boot. A starter provides pre-configured setups for common development scenarios. It bundles together various libraries needed for a specific functionality.

### what dependecies in the below starter? do you know any starters?

**spring-boot-starter-web** includes libraries like Spring MVC, Tomcat, and Jackson

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
~~~

**Common Spring Boot Starters**:

- `spring-boot-starter-web`: For building web, RESTful, and MVC applications.
- `spring-boot-starter-data-jpa`: For JPA-based data access and Hibernate ORM.
- `spring-boot-starter-security`: For security features like authentication and authorization.
- `spring-boot-starter-test`: For testing, includes libraries like JUnit, Mockito, and Spring Test.

## 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?

@RequestMapping is used to map HTTP request to specific handler method in controller classes. 

`value` attribute defines the URL path, `method` specifies the type of HTTP method.

**Create**(POST)

~~~java
@RequestMapping(value = "/users", method = RequestMethod.POST)
public ResponseEntity<User> createUser(@RequestBody User user) {
    // Create a new user
    return new ResponseEntity<>(user, HttpStatus.CREATED);
}

~~~

**Read**(GET)

getAll

~~~java
@RequestMapping(value = "/users", method = RequestMethod.GET)
public ReponseEntity<List<User>> getAllUsers() {
	return new ReponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
}
~~~

get a single user by id

~~~java
@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    // Retrieve user by ID
    return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
}
~~~

**Update** (PUT) 

~~~java
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    // Update user information
    return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
}
~~~

**Delete**

~~~java
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
	userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
~~~



## 7. What is ResponseEntity

In spring framework, it represents the entire http request. It also provides full control over HTTP **status code, header and response body**.

~~~java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
~~~

The above are used to return a response entity and its http status. The .ok() is a shortcut for returning a HttpStatus.OK.

## 8. What is ResultSet in jdbc? describe the flow how to get data using jdbc

A ResultSet represents the result set of data entry. It is a cursor or a pointer to the rows of data retrieved from a database when you execute an SQL query (Such as SELECT)

**Iterate data** It can use next() method to get the next data. 

**Fetch data**: It has methods like getString(), getInt(), getDouble() to retrieve the values of columns in the current row

Read-Only by Default: it is usually read-only.

Scrollable: By default, the ResultSet can only move forward. However, a scrollable ResultSet can be created to move he curser forward and backward and even jump.

### Basic flow to get data using JDBC

**1. Load the JDBC Driver**

~~~java
Class.forName("com.mysql.cj.jdbc.Driver")
~~~

**2. Establish a Connection**

~~~java
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
~~~

**3. Create a statement or PreparedStatement**

A Statement or PreparedStatement object is used to execute SQL queries to the database

~~~java
String sql = "SELECT id, name, age FROM users WHERE age > ?";
PreparedStatement preparedStatement = connection.prepareStatement(sql);
preparedStatement.setInt(1, 18); // Setting parameter for age
~~~

**4. Execute the Query**

~~~java
ResultSet resultSet = preparedStatement.executeQuery();
~~~

**5. Process the ResulSet**

Iterate through the ResultSet to retrieve data from the result rows

~~~java
while(resultSet.next()) {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    int age = resultSet.getInt("age");
    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
}
~~~

**6. Close the resources**

~~~java
resultSet.close();
preparedStatement.close();
connection.close();
~~~



## 9. Compare Spring Data JPA vs Hibernate vs JDBC

### **Comparison Table**:

| Feature                  | Spring Data JPA                                | Hibernate                                    | JDBC                                    |
| ------------------------ | ---------------------------------------------- | -------------------------------------------- | --------------------------------------- |
| **Level of Abstraction** | High                                           | Medium                                       | Low                                     |
| **Ease of Use**          | Very easy, minimal boilerplate                 | Moderate, some boilerplate required          | Hard, requires manual work for CRUD ops |
| **Configuration**        | Minimal (Spring Boot auto-configures)          | More configuration than Spring Data JPA      | Manual setup for everything             |
| **Query Language**       | JPQL, HQL, and derived queries                 | HQL, native SQL                              | SQL only                                |
| **Performance**          | Moderate (may add overhead for large datasets) | Good, but depends on caching, lazy loading   | High, no abstraction overhead           |
| **Caching**              | Not built-in (depends on JPA provider)         | Built-in caching mechanisms                  | No built-in caching                     |
| **Transactions**         | Managed by Spring (easy to configure)          | Requires manual management or Spring support | Fully manual                            |
| **Learning Curve**       | Easy for Spring developers                     | Moderate (requires learning ORM concepts)    | Steep                                   |

### **When to Use Each**:

- Spring Data JPA: Ideal for quick development with minimal boilerplate. Perfect for CRUD operations. Take advantage of spring-boot's auto configuration, or when need rapid prototyping.
- Hibernate: Suitable for complex domain models, large-scale applications and projects that require fine-grained control over database interactions while still benefiting from ORM features like caching, lazy loading, etc.
- JDBC: when need max performance and control over SQL queries, or if working with a simple use case where the overhead of an ORM is not justified. JDBC is also useful for interacting with non-relational data structures.

## 10. Learn how to use ObjectMapper

[chuwa-eij-tutorial/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java at main · CTYue/chuwa-eij-tutorial (github.com)](https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java)

~~~java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
~~~

ObjectMapper did the serialization and deserialization for data. It deserialize data into a java object. The variable are labelled with @JsonProperty in Object class, so ObjectMapper knows how to match each field.

## 11. What is serialization and deserialization

Serialization is converting of an object into a data format (e.g. JSON, XML). So it can be easily stored or transmitted.

Deserialization is the reverse. It converts the byte stream or data format (like JSON, XML) back into an Object.

## 12. Stream API to get average from array

~~~java
List<Integer> scores = Arrays.asList(85, 90, 95, 88, 92);
double average = scores.stream().mapToInt(Integer::intValue)
.average().orElse(0);
System.out.println(average);
~~~

## 13. Practice

## 14. Practice





