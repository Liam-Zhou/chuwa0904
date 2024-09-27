package org.example;

import org.example.coinfig.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Alien obj =  context.getBean(Alien.class);
        System.out.println(obj.getAge());
        obj.code();



//        Desktop dt = context.getBean("desktop", Desktop.class);
//        dt.compile();
//
//        Desktop dt1 = context.getBean("desktop", Desktop.class);
//        dt1.compile();








//xml config:
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Alien obj =  context.getBean(Alien.class);
//        obj.setAge(21);
//        System.out.println(obj.getAge());
//        obj.code();

//        Alien obj1 =  context.getBean(Alien.class);
//        System.out.println(obj1.age);
//        //obj1.code();
    }
}
