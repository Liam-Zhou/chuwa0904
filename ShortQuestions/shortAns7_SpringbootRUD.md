### List all of the Spring data related annotations your learned and explain its usage.
***Spring JPA Annotations***
- @Entity: mark a class as a "database entity"
- @Table: used alongside @Entity to get more control of the database table
- @Id: used to specify the field which acts as a unique identifier (primary key) for the class.
- @GeneratedValue: instructs JPA to manage primary key generation
- @Column: provides control over the database column definition,allows control over column settings such as name and length
- @Transient:  indicates that the field is not to be persisted in the database and should be ignored by JPA

***Spring MVC annotation***
- @RestController: Combines @Controller and @ResponseBody to simplify the creation of RESTful web services, It handles HTTP requests and automatically serializes the response into JSON or XML.
- @RequestMapping: defines the base URL for endpoints in a controller.
````angular2html
@RequestMapping("/api/v1")
````
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping: Specialized versions of @RequestMapping that handle specific HTTP methods (GET, POST, PUT, DELETE)
```angular2html
 @PostMapping("/posts")
```
- @RequestBody:  Binds the body of an HTTP request to a method parameter, allowing Spring to automatically convert JSON or XML data from the request into a Java object.
```angular2html
public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){}
```

***Spring Core annotation***
- @Autowired: Automatically injects dependencies into Spring-managed beans


### What is DTO, VO, Payload, PO, model, DAO?
1. DTO: Transfers data between layers without exposing internal structures.
2. VO: Represents immutable data(object) with no identity, often used for calculations or formatting.
3. Payload: The main data content sent or received in an API request or response.
4. PO (Persistent Object): Represents data(rows in a database table) stored in a database, managed by an ORM.
5. Model: The main representation of data and business logic in an application.
6. DAO: Provides a structured way to perform CRUD operations on the database


### What is @JsonProperty("description_yyds") 
@JsonProperty: 
- used during both serialization (when converting Java objects to JSON) 
- deserialization (when converting JSON back into Java objects).
- the Java field description will be mapped to the JSON property description_yyds.
```angular2html
public class Product &#123;
      @JsonProperty("description_yyds")
      private String description;
    }
```

### Explain the purpose of following dependency?
Purpose of jackson-databind
1. Serialization(Converts Java objects into JSON format) and Deserialization(Converts JSON data back into Java objects)
2. provide annotation support like JsonProperty, @JsonIgnore, and @JsonInclude
3. Integrate with Springboot w/o additional config
4. making it easy to work with JSON in Java applications
```angular2html
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>

```

### What is spring-boot-stater?

- what: Spring Boot Starters are a set of convenient dependency descriptors. 
- how: Starters bundle together commonly used dependencies, configurations, and components

#### what dependecies in the below starter? do you know any starters?

- what: spring-boot-starter-web starter is used for building web applications
- what dependencies included: 
  -  Spring MVC: For building web applications and RESTful APIs.
  - Embedded Tomcat: By default, it includes Tomcat as the embedded web server, so you don’t need to set up a separate server.
  - Jackson: For JSON serialization and deserialization
  - Validation API: For data validation within your application.
- Common SB starters:
  - spring-boot-starter-data-jpa: For using Spring Data JPA with Hibernate to handle database operations.
  - spring-boot-starter-security: For adding security features like authentication and authorization.
  - spring-boot-starter-test: For including libraries commonly used in testing, such as JUnit, Mockito, and Spring Test.
  - spring-boot-starter-thymeleaf: For using Thymeleaf as the templating engine for your web application.

### Explain the code below, could you list CRUD by this style?

```angular2html
@RequestMapping(value = "/users", method = RequestMethod.POST)
```
RequestMapping maps **http post** request that sent to /users URL to corresponding method in controller
- @RequestMapping: map the web request to handler method in controller
- value = "/users": Specifies the URL path that the method will handle.
- method = RequestMethod.POST: specifies the HTTP method the handler will respond to post request

### What is ResponseEntity? why do we need it?
```angular2html
import org.springframework.http.ResponseEntity;

return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
```
- what: ResponseEntity is a powerful (class)tool in Spring MVC including the **status code**, **headers**, and **body**.
```angular2html
postResponse: This is the body of the response
HttpStatus.CREATED: This status code (201) indicates that the request has been successfully fulfilled
```
- why: provides full control over the HTTP response returned by your RESTful API, allowing you to customize the response in detail.

### What is ResultSet in jdbc? and describe the flow how to get data using JDBC
- what: ResultSet is an object in jdbc represent the result set of query executed on database 
- how: it provides navigation method like .next() .previous(), and method to retrieve data like getInt
**Flow**
1.  Load the database driver.
2.  Establish a connection to the database.
3.  Define and execute the SQL query.
4.  Process the results using ResultSet.
5.  Close the resources to prevent resource leaks.

### Compare Spring Data JPA vs Hibernate vs JDBC.
- Spring Data JPA: **An abstraction over JPA** (Java Persistence API) that simplifies database interactions in Spring applications. minimal code, best for quick development.
- HibernateA popular **JPA implementation and ORM (Object-Relational Mapping) tool**, best for complex entity relationships.
- JDBC: **A low-level API** for connecting to databases and executing SQL directly, best for simple or high-control needs.


### What is the serialization and desrialization

#### my understanding
- Serialization(Converts Java objects into JSON format) 
  - UserDto is automatically serialized into JSON by Spring (using Jackson) before being sent in the response body to the client.
```angular2html
@RestController
@RequestMapping("/api/v1")
public class UserController &#123;

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser() {
        UserDto user = new UserDto(1, "John Doe");
        return new ResponseEntity<>(user, HttpStatus.OK); // Spring automatically serializes UserDto to JSON
    }
}



```
- Deserialization(Converts JSON data back into Java objects)
  -   the @RequestBody annotation tells Spring to deserialize the incoming JSON request body into a UserDto object that the createUser method can then use
```angular2html
@RestController
@RequestMapping("/api/v1")
public class UserController &#123;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        // `userDto` is automatically deserialized from the JSON request body to a Java object
        System.out.println("Received user: " + userDto.getName());
        return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
    }
}

```
#### Hazelcast’s
```angular2html
https://hazelcast.com/glossary/serialization/
```
- Hazelcast is a distributed in-memory data grid, and its definition of serialization is more focused on how objects are transformed into a byte stream that can be sent across the network or stored in memory in a cluster.
- focusing more on converting objects to a byte stream suitable for network transmission between clustered nodes.

### use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
- List interface (works with objects, not primitives)creates a list of Integer objects (not primitive int)
- mapToInt(...): A method from the Stream API that converts a stream of objects into an IntStream.
- Integer::intValue is a method reference that serves as the implementation of the functional interface

```angular2html
List<Integer> list = Arrays.asList(20, 3, 78, 9, 6, 53, 73, 99, 24, 32);
System.out.println("avg by using Stream : " + list.stream().mapToInt(Integer::intValue).average());

```

### objectMapper.readTree() // learn how to use it?
```angular2html
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);

String s = objectMapper.writeValueAsString(foodOutlet);

objectMapper.readTree() 

```
- readValue(): Converts JSON to a Java object (FoodOutlet).
- writeValueAsString() writes it as a JSON-formatted string
  -  Converts the Java object back into a JSON string
- readTree(): Converts the JSON string into a JsonNode for dynamic JSON manipulation.
