# Annotations
## Core Spring Framework Annotations
- @SpringBootApplication:
- @Autowired: Automatically injects the beans by type.
- @Bean: Used to declare a bean manually.
- @Service: Indicates a service layer class that holds business logic
- @Repository: indicates a data access object in the persistence layer
- @Autowired: Marks a constructor, field, setter method, or config method to be autowired by Spring's dependency injection facilities.
- @Component: Generic stereotype annotation indicating a component scanned by Spring
- @ControllerAdvice: Used for handling exceptions across the whole application, not just to an individual controller.
- @ComponentScan: Configures component scanning directives for Spring.
- @Configuration: Marks a class as a source of bean definitions.
- @ExceptionHandler: Used for handling specific exceptions within @Controller classes.
- @Valid: Marks a parameter to be validated, usually in controller methods


## HTTP Request Mapping Annotations
- @RestController: Annotation combines @Controller and @ResponseBody
- @RequestMapping: General purpose annotation for mapping web requests onto methods in request-handling classes
- @GetMapping
- @PostMapping
- @PutMapping
- @DeleteMapping
- @PathVariable: indicates that a method parameter should be bound to a URI template variable
- @RequestParam: indicates that a method parameter should be bound to a web request parameter


## JSON and Serialization Annotations
- @JsonPropertyOrder: allows the specification of the order in which properties are serialized
- @JsonProperty: Allows a property to be serialized with a different name or with additional characteristics


## Testing Annotations
- @Test

## Java Annotations
- @Override
- @FunctionalInterface


## JPA & Persistence Annotations
- @Entity: Marks a class as a JPA entity.
- @Table: Specifies the table that maps to the entity.
- @Id
- @GeneratedValue
- @OneToMany
- @ManyToOne
- @ManyToMany
- @JoinColumn

## Transactional & Session Management
- @Transactional

## Validation Related Annotations
- @NotEmpty: Constraint annotation to check that a field is not null or empty.
- @Email: Constraint annotation to check that a field is a valid email address.
- @Size: Constraint annotation to check that a field's size is within the specified bounds.