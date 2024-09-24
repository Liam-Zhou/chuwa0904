### List all of the annotations you learned from class and homework to annotaitons.md
```Code in annotaitons.md ```
### Type out the code for the Comment feature of the class project.
```angular2html
code in Coding/springboot-redbook-comment
```
### In postman, call all of the APIs in PostController and CommentController.

### What is JPA? and what is Hibernate?
**JPA**
- what:JPA(Java Persistence API) is serving as bridge between java class (eneties) and database (table), transfering and manipulating data in between.
- How: 
  - Mapping java class(entities) to db using annotaition @Entity, @Table, @Column
  - CRUD: perform both on Java class(entities) and db(Jpa translate action into sql run on db)
- example of jpa: findById(id), findAll(),save(entity),delete(entity) , etc.


**Hibernate**
- What: ORM (Object-Relational Mapping) framework that implements the JPA specification.
It provides functionalities/ implementation of method defined by JPA interface

### What is Hiraki? what is the benefits of connection pool?

- what:Hikari is a JDBC connection pool used in spring app
- why: it manages db connection efficiently by 
    - providing connetion pool of active connections that are ready to use
    - leak detection by identifing connections are not properly closed
    - automatic pool sizing to match the application's workload
### What is the @OneToMany, @ManyToOne, @ManyToMany

- @OneToMany: Establishes a one-to-many relationship between two entities. This is used when one entity has a collection of another entity.
-  @ManyToOne: Establishes a many-to-one relationship, where multiple entities relate to a single entity. Often used as a foreign key.
```angular2html
@Entity
public class Employee &#123;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many Employees belong to one Department
    @ManyToOne
    @JoinColumn(name = "department_id") // Specifies the foreign key column in Employee table
    private Department department;

    // Getters and Setters
}

```
- @ManyToMany: Defines a many-to-many relationship between two entities and usually requires a join table.
  create a separate table(join table) to hold the foreign keys
  - owner side: Student, Inverse side: Course
  - Owning Side : Defines the @JoinTable and manages the relationship
  - Inverse Side : Uses mappedBy = "courses" to indicate that the Course entity does not control the relationship
```angular2html
@Entity
public class Student {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

// Owning side of the many-to-many relationship
@ManyToMany
@JoinTable(
name = "student_course", // Name of the join table
joinColumns = @JoinColumn(name = "student_id"), // Foreign key to Student
inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key to Course
)
private Set<Course> courses = new HashSet<>(); //courses ref to Course object

// Getters and Setters
}


@Entity
public class Course {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

// Inverse side of the many-to-many relationship, managed by Student
@ManyToMany(mappedBy = "courses")
private Set<Student> students = new HashSet<>();

// Getters and Setters
}



```

### What is the
cascade = CascadeType.ALL, orphanRemoval = true
? and what are the other CascadeType
and their features? In which situation we choose which one?
### What is the
fetch = FetchType.LAZY, fetch = FetchType.EAGER
? what is the difference? In which
situation you choose which one?
### What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
1. Start with keywords like findBy, getBy, or readBy followed by field names
2. Use CamelCase: Combine field names in camelCase
```angular2html
// Find user by first name and last name
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
```

### Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.

### What is JPQL?
JPQL (Java Persistence Query Language) provides a way to query and interact with entities in a more object-oriented manner
-  @NamedQuery, @Query and TypedQuery with EntityManager are used in JPA for defining JPQL
- @NamedQuery: Defined on the entity class and used in the repository, service layer or anywhere an EntityManager is available
- @Query: Directly defined and used in the repository.
1. Example of @NamedQuery
```angular2html
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;

    // Getters and Setters
}
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

@PersistenceContext
private EntityManager entityManager;

public List<Employee> findEmployeesByLastName(String lastName) {
// Using the NamedQuery defined in the Employee entity
TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByLastName", Employee.class);
  query.setParameter("lastName", lastName);
  return query.getResultList();
  }
  }

```
2. Example of @Query 
```angular2html
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Using @Query to define a JPQL query directly on the repository method
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName")
    List<Employee> findByFirstName(@Param("firstName") String firstName);
}
```

```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

@Autowired
private EmployeeRepository employeeRepository;

public List<Employee> findEmployeesByFirstName(String firstName) {
return employeeRepository.findByFirstName(firstName); // Calls the @Query method
}
        }
```
3. Example of TypedQuery Using EntityManager
```angular2html
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> findEmployeesByFirstNameAndLastName(String firstName, String lastName) {
        // Creating a TypedQuery with JPQL directly using EntityManager
        String jpql = "SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}

```
### What is @NamedQuery and @NamedQueries?
- @NamedQuery is an annotation used to define a single, static query within an entity class.
- @NamedQueries is an annotation used to group multiple @NamedQuery
```angular2html
@Entity
@NamedQueries(&#123;
    @NamedQuery(name = "EntityName.findByName", query = "SELECT e FROM EntityName e WHERE e.name = :name"),
    @NamedQuery(name = "EntityName.findAll", query = "SELECT e FROM EntityName e")
})
```

### What is @Query? In which Interface we write the sql or JPQL?
@Query is an annotation used to define custom SQL or JPQL queries directly within a repository interface
### What is HQL and Criteria Queries?

### What is EnityManager?
EntityManager is an interface of JPA provides ways to manage entities and execute queries(TypeQuery)
### What is SessionFactory and Session?
- SessionFactory is a factory object in Hibernate that creates and manages Session objects.
- Session is hibernate core api(interface) for performing persistence operation by supporting JPA entityManager

### What is Transaction? how to manage your transaction?
Transaction: A transaction is a sequence of operations
it either fully completes (commit) or is rolled back if any part fails
using @Transactional annotation in method to manage 

### What is hibernate Caching? Explain Hibernate caching mechanism in detail.
First-level caching is automatically enabled and stores entities within a session, avoiding repeated database access for the same session. Second-level caching operates at the session factory level, relies on third-party caching providers, and must be explicitly configured to be used across multiple sessions.

### What is the difference between first-level cache and second-level cache?
First-Level Cache: Session-level, always enabled, visible only within the session.
Second-Level Cache: SessionFactory-level, optional, relies on third-party caching providers, shared across sessions.
### How do you understand @Transactional? (
https://github.com/TAIsRich/tutorial-transaction
)
- The @Transactional annotation simplifies transaction management in Spring, automatically handling commit and rollback.
- (w/o @Transactional)Manual transaction management using EntityManager provides more control but requires explicit transaction handling.


