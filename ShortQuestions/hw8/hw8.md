### 1. List all of the annotations you learned from class and homework to annotaitons.md

### 2. Type out the code for the Comment feature of the class project.
### 3. In postman, call all of the APIs in PostController and CommentController.


### 4. What is JPA? and what is Hibernate?

JPA(Java Persistence API) is a standard of Object Relational Mapping. It is an interface that defines a set of annotations 
for creating the object relational mapping. The most popular ORM framework is Hibernate

### 5. What is Hiraki? what is the benefits of connection pool?

HikariCP is a lightweight, high-performance JDBC connection pool.  It is known for its ability to provide fast, reliable 
connections to databases while minimizing the overhead on system resources.  

### 6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.

Attributes in JPA annotations like @OneToMany , @ManyToOne , @ManyToMany , and @OneToOne are used to configure the 
relationship between entities, controlling aspects such as fetching strategy, cascade behavior, and join columns.  

### 7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType and their features? In which situation we choose which one?

**cascade = CascadeType.ALL** This means that any operation performed on the parent entity will be cascaded to the child entity.  
**orphanRemoval = true** This means that if a child entity is removed from the relationship (e.g., from a List or Set of 
children in the parent), it will be deleted from the database.  
**CascadeType.PERSIST**  Feature: When the parent entity is persisted (saved), the related child entities are also persisted.
Use Case: Use when you want to automatically save new child entities when saving the parent.
**CascadeType.MERGE**  Feature: When the parent entity is merged (updated), the related child entities are also merged.
Use Case: Use when updating the parent should also update the state of the child entities.
**CascadeType.REMOVE**  Feature: When the parent entity is removed (deleted), the related child entities are also removed.
Use Case: Use when deleting a parent should also delete its associated children.
**CascadeType.REFRESH**  Feature: When the parent entity is refreshed (reloaded from the database), the related child entities are also refreshed.
Use Case: Use when you need to ensure that both parent and children are reloaded to reflect the current database state.
**CascadeType.DETACH**  Feature: When the parent entity is detached from the persistence context, the related child entities are also detached.
Use Case: Use when you want to detach both parent and children from the persistence context, typically in long-running conversations.
**CascadeType.NONE**  No cascading operations. This is the default behavior if no cascade type is explicitly set.
Use case: When you want the parent and child entities to be managed separately.
```java
// When a Parent is persisted, all Child entities in the children list are also persisted.
// If a Child is removed from the children list, it is automatically deleted from the database.
@Entity
public class Parent {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();
    
    // getters and setters
}
```
Parent-Child Relationships  

Example: Order and OrderItem.  
Cascade: Typically, CascadeType.ALL or at least PERSIST and REMOVE.  
Reason: When you create or delete an Order, you usually want to create or delete its associated OrderItems.  
Shared or Independent Entities  

Example: User and Role.  
Cascade: Avoid cascading REMOVE. Use MERGE and PERSIST if roles are managed independently.  
Reason: Roles might be shared across multiple users; deleting a user shouldn't delete a role.  
One-to-One Relationships  
 
Example: User and Profile.  
Cascade: CascadeType.ALL can be appropriate if the Profile lifecycle is strictly tied to the User.  
Reason: Deleting a User should also delete the associated Profile.  
Bidirectional Relationships  

Example: Parent and Child where both reference each other.  
Cascade: Be cautious to prevent unintended operations. Use specific cascade types as needed.  
Reason: Prevent operations from cascading in both directions, which can lead to performance issues or unintended deletions.
Orphan Removal  

Use Case: When a child entity should not exist without its parent.  
Example: A ShoppingCart and its CartItems.  
Reason: If a CartItem is removed from the ShoppingCart, it should be deleted from the database to maintain data integrity.  

```java
@Entity
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    
    // other fields, getters, setters
}

@Entity
public class OrderItem {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    // other fields, getters, setters
}
```

### 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which situation you choose which one?

**FetchType.LAZY** (Lazy Loading)  
With lazy loading, associated entities are not loaded immediately when the parent entity is fetched. Instead, the child 
entities are fetched on demand, only when they are explicitly accessed in the code.  
```java
@Entity
public class Order {
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;
}
```
In this example, the Order entity is loaded without its OrderItems when the Order is fetched from the database. The 
OrderItem list is loaded only when accessed in code:
```java
Order order = entityManager.find(Order.class, orderId);
// Items are not loaded yet
List<OrderItem> items = order.getItems(); // Items are now fetched from the database
```
**FetchType.EAGER** (Eager Loading)
With eager loading, associated entities are loaded immediately along with the parent entity. When the parent entity is 
fetched, all child entities are retrieved in the same query (if possible).

### 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

**Entity Naming** By default, a JPA entity class maps to a database table with the same name as the class. 
Class Name to Table Name: If the class name is Order, the table will be Order

```java
@Entity
@Table(name = "customer_orders")
public class Order {
    // mapped to table 'customer_orders'
}
```

**Attribute Naming** By default, an entity's fields map to columns with the same name as the field name, converted to 
snake_case in most databases. Field Name to Column Name: If the field name is orderDate, the corresponding column name will be order_date.

```java
@Entity
public class Order {
    @Column(name = "ordered_on")
    private LocalDate orderDate; // maps to column 'ordered_on'
}
```

**Primary Key Naming** By convention, JPA assumes the primary key field or property is called id, but you can name it anything.
 Field Name: By default, a field id maps to the id column

```java
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // primary key 'orderId' can be customized
}
```

**Foreign Key Naming** By default, JPA uses the entity name of the referenced entity followed by _id as the foreign key column name.
For example, if an Order entity has a Customer relationship, the foreign key column is customer_id.
```java
@ManyToOne
@JoinColumn(name = "customer_id") // default or explicitly specified
private Customer customer;
```

**Join Table Naming** In a many-to-many relationship, JPA uses the names of both entities to create the default name for the join table.
For example, a relationship between User and Role would create a table user_role

```java
@ManyToMany
@JoinTable(name = "user_role")
private Set<Role> roles;
```


12. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming
    convention to use the method provided by JPA.
11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcT
    emplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.
12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with h
    ttps://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.
### 13. What is JPQL?

JPQL is Java Persistence Query Language defined in JPA specification. It is used to create queries against entities to 
store in a relational database.

### 14. What is @NamedQuery and @NamedQueries?
    A named query is a statically defined query with a predefined unchangeable query string.  
```java
@Entity
@NamedQueries({
 @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
 @NamedQuery(name = "Book.findByPublishingDate", query = "SELECT b FROM Book b WHERE b.publishingDate = :publishingDate")})
public class Book implements Serializable {
// ...
}
```

### 15. What is @Query? In which Interface we write the sql or JPQL?

```java
// In this example, the query is written using JPQL, which refers to the entity and its fields (Order and customer.name), 
// rather than the actual database table or column names.
@Query("SELECT o FROM Order o WHERE o.customer.name = :customerName")
List<Order> findOrdersByCustomerName(@Param("customerName") String customerName);

// In this case, the query is written in native SQL, directly targeting the database table (customer_orders) and column 
// names (customer_id). Notice the nativeQuery = true flag to indicate that this is a native SQL query.
@Query(value = "SELECT * FROM customer_orders WHERE customer_id = :customerId", nativeQuery = true)
List<Order> findOrdersByCustomerId(@Param("customerId") Long customerId);
```

16. What is HQL and Criteria Queries?
17. What is EnityManager?
18. What is SessionFactory and Session?
### 19. What is Transaction? how to manage your transaction?

A transaction simply represents a unit of work.

```java
@Test
void testSessionFactory() {
    // 1. 根据配置⽂件，创建⼀个sessionFactory
    SessionFactory sessionFactory = new
    Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    // 2. sessionFactory 创建出⼀个session
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    try {
        // 3. session 开始⼀个transaction
        transaction = session.beginTransaction();
        // 4. 执⾏txn
        post.setTitle(post.getTitle() + LocalDateTime.now());
        post.setCreateDateTime(LocalDateTime.now());
        post.setUpdateDateTime(LocalDateTime.now());
        Post savedPost = (Post) session.merge(post);
        System.out.println(savedPost);
        // 5. commit txn, 该txn要么成功，要么失败,是个原⼦操作。
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
        // txn 失败则回滚
        transaction.rollback();
         }
        e.printStackTrace();
    } finally {
        // 6. close session
        session.close();
    }
}
```
20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
21. What is the difference between first-level cache and second-level cache?
22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)