### 1. List all of the Spring data related annotations your learned and explain its usage.  

```
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan

```

### 2. What is DTO, VO, Payload, PO, model, DAO?  

DTO (Data Transfer Object):  
Are containers which are used to transport data between layers and tiers. The solution is to create a Data Transfer 
Object that can hold all the data for the call. It needs to be serializable to go across the connection. Usually an 
assembler is used on the server side to transfer data between the DTO and any domain objects. It’s often little more 
than a bunch of fields and the getters and setters for them.  

VO — A Value Object:  
Represents itself a fixed set of data and is similar to a Java enum. A Value Object’s identity is based on their state 
rather than on their object identity and is immutable.  

Domain Model:  
Contains all Entities and Value Objects. And some other types of classes depending on the classification you use.  

Model:   
Defines a holder for model attributes and is primarily designed for adding attributes to the model.  

DAO (Data Access Object) or Repository:
A Data Access Object abstracts and encapsulates all access to the data source. The DAO manages the connection with the 
data source to obtain and store data. The DAO implements the access mechanism required to work with the data source. 
The data source could be a persistent store like an RDBMS, or a business service accessed via REST or SOAP.

### 3. What is @JsonProperty("description_yyds")

We can add the @JsonProperty annotation to indicate the property name in JSON.


### 4. Explain the purpose of following dependency?

This dependency adds the Jackson Databind library, allowing to work with JSON data by converting Java objects to 
JSON (serialization) and vice versa (deserialization).

```xml 
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.13.3</version>
<scope>compile</scope>
</dependency>
```

### 5. What is spring-boot-starter?
   1. what dependecies in the below starter? do you know any starters?  

A Spring Boot Starter is a collection of dependency descriptors that can be added to an application to simplify the 
process of developing Spring-based projects. For example, spring-boot-starter-web includes dependencies to create web 
applications using Spring MVC.

```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
### 7. What is ResponseEntity? why do we need it?

```
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```

### 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
### 9. Compare Spring Data JPA vs Hibernate vs JDBC

### 10. Learn how to use ObjectMapper by this example.
1. https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
```
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```

### 11. What is the serialization and desrialization?
1. https://hazelcast.com/glossary/serialization/

### 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
### 13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
### 14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码