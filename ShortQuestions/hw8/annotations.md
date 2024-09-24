```java
/**
 * @Bean is a method-level annotation used to declare a spring bean. When the container executes the annotated method, 
 * it registers the return value as a bean within a BeanFactory.
 */
@Bean
EmployeeService employeeService()
{
    return new EmployeeServiceImpl();
}

/**
 * @Component, @Controller, @Repository, @Service
 * These annotations are called stereotype annotations. When component scanning is enabled, Spring will automatically 
 * import these beans into the container and inject them into dependencies.
 * The @Component annotation is a generic annotation and marks a Java class as a bean.
 * The @Controller annotation marks a class as a Spring MVC controller.
 * The @Repository annotation is a specialization of the @Component annotation. In addition to importing the DAOs into the DI container, it also makes the unchecked exceptions (thrown from DAO methods) eligible for translation into Spring DataAccessException.
 * The @Service annotation is also a specialization of @Component and used over service-layer classes because it specifies intent better. It doesn’t currently provide any additional behavior.
 */
@Controller
public class UserMgmtController {

    //API handler methods
}

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //... 
}

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    //...
}

/**
 * During autowiring, if more than one bean of the same type is available in the container then the container will throw 
 * runtime exception. To fix this problem, we have to specifically tell Spring which bean has to be injected using this annotation.

 In the given example, if there are two beans of type Repository then on runtime, the bean with the name ‘fsRepository‘ 
 will be injected.
 */

public class EmployeeService {

    @Autowired
    @Qualifier("fsRepository")
    private Repository repository;
}
```