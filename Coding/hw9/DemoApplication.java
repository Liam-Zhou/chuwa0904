// Spring Application to Demo Dependency Injection, Bean Scopes, and Bean Registration

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Demo Application Entry Point
@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication {

   public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
   }

   @Bean
   @Scope("prototype")
   public MyService prototypeService() {
      return new MyServiceImpl1();
   }

   @Bean
   @Scope("singleton")
   @Qualifier("singletonService")
   public MyService singletonService() {
      return new MyServiceImpl2();
   }
}

// Demo for Constructor Injection and Field Injection
@Component
class ClientComponent {

   private final MyService myService;

   // Constructor Injection
   @Autowired
   public ClientComponent(@Qualifier("singletonService") MyService myService) {
      this.myService = myService;
   }

   // Field Injection
   @Autowired
   @Qualifier("prototypeService")
   private MyService prototypeService;

   public void performService() {
      System.out.println("Singleton service: " + myService.performAction());
      System.out.println("Prototype service: " + prototypeService.performAction());
   }
}

// Defining Services with @Component and @Bean

interface MyService {
   String performAction();
}

@Component
class MyServiceImpl1 implements MyService {
   @Override
   public String performAction() {
      return "Service 1 - Prototype";
   }
}

@Component
class MyServiceImpl2 implements MyService {
   @Override
   public String performAction() {
      return "Service 2 - Singleton";
   }
}

// Another Component with Setter Injection
@Component
class AnotherClientComponent {

   private MyService myService;

   // Setter Injection
   @Autowired
   @Qualifier("prototypeService")
   public void setMyService(MyService myService) {
      this.myService = myService;
   }

   public void performAnotherService() {
      System.out.println("Performing action with prototype service: " + myService.performAction());
   }
}

// Configuration class that demonstrates @Bean registration and bean scopes
@Configuration
class AppConfig {

   @Bean
   @Qualifier("myServiceByBean")
   public MyService myServiceByBean() {
      return new MyServiceImpl1();
   }
}
