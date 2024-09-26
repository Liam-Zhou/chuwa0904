https://www.baeldung.com/spring-autowire

Key Notations:  
@Component    - create bean
    @Component("Foo") - bean with name Foo
@Autowired    - wire a bean
@Qualifier    - naming a bean
    @Qualifier("Foo") - wire the bean with name Foo
    @Target       - qualifier customization
    @Retention    - Indicates how long annotations with the annotated interface are to be retained.

```java
    // FormatterType is a specialized Qualifier
    @Qualifier
    // For example, an annotation whose interface is meta-annotated with @Target(ElementType. FIELD) may only be written as
    // a modifier for a field declaration.
    @Target({
    ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    // @ symbol denotes an annotation type definition.
    public @interface FormatterType {
        String value();
    }
```


In our example, there are two concrete implementations of Formatter available for the Spring container. As a result, 
Spring will throw a NoUniqueBeanDefinitionException exception when constructing the FooService. 

When there are multiple beans of the same type, it’s a good idea to use @Qualifier to avoid ambiguity.