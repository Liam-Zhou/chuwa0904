package org.example.coinfig;


import org.example.Alien;
import org.example.Computer;
import org.example.Desktop;
import org.example.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.example")
public class AppConfig
{



//    @Bean
//    public Alien alien(@Qualifier("desktop") @Autowired Computer com){
//        Alien obj = new Alien();
//        obj.setAge(25);
//        obj.setCom(com);
//        return obj;
//    }
//
//
//
//    //@Bean(name = {"com2","desktop1","Beast"})
//    @Bean
//    //@Scope("prototype")
//    public Desktop desktop(){
//        return new Desktop();
//    }
//
//    @Bean
//    public Laptop laptop(){
//        return new Laptop();
//    }
}
