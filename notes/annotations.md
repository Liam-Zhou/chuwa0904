# Commonly Used Java and Spring Annotations

## Java Annotations

### @Override
- **Description**: Indicates that a method declaration is intended to override a method declaration in a superclass.

### @SuppressWarnings
- **Description**: Indicates that the named compiler warnings should be suppressed in the annotated element.

### @Deprecated
- **Description**: Marks the annotated element as deprecated, indicating that it should no longer be used.

### @FunctionalInterface
- **Description**: Indicates that the type declaration is intended to be a functional interface.

## Spring Framework Annotations

### @SpringBootApplication
- **Description**: Used to mark a configuration class that declares one or more `@Bean` methods and also triggers auto-configuration and component scanning.

### @Autowired
- **Description**: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.

### @Component
- **Description**: Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

### @Service
- **Description**: Indicates that an annotated class is a "service", originally defined by Domain-Driven Design (DDD) as a stateless operation.

### @Repository
- **Description**: Indicates that an annotated class is a "repository", a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.

### @Controller
- **Description**: Indicates that an annotated class is a "controller" (e.g., a web controller).

### @RestController
- **Description**: A convenience annotation that is itself annotated with `@Controller` and `@ResponseBody`. Indicates that the class is a controller where every method returns a domain object instead of a view.

### @RequestMapping
- **Description**: Annotation for mapping web requests onto methods in request-handling classes with flexible method signatures.

### @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
- **Descriptions**: Specializations of `@RequestMapping` that act as shortcuts for specifying HTTP methods in request mappings.

### @ResponseBody
- **Description**: Indicates that a method return value should be bound to the web response body.

### @PathVariable
- **Description**: Indicates that a method parameter should be bound to a URI template variable.

### @RequestParam
- **Description**: Indicates that a method parameter should be bound to a web request parameter.

### @Bean
- **Description**: Indicates that a method produces a bean to be managed by the Spring container.

## JPA/Hibernate Annotations

### @Entity
- **Description**: Specifies that the class is an entity. This annotation is applied to the entity class.

### @Table
- **Description**: Specifies the primary table for the annotated entity. Additional tables can be specified using `@SecondaryTable` or `@SecondaryTables` annotation.

### @Id
- **Description**: Specifies the primary key of an entity.

### @GeneratedValue
- **Description**: Provides for the specification of generation strategies for the values of primary keys.

### @Column
- **Description**: Specifies the mapped column for a persistent property or field.

### @ManyToOne, @OneToMany, @OneToOne, @ManyToMany
- **Descriptions**: These annotations mark the relationships between entities.

