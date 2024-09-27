package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Component
public class Alien {

    @Value("21")
    private int age;

    @Autowired
    @Qualifier("com2")
    private Computer com;

    public Alien(){
        System.out.println("Object created");
    }

//    @ConstructorProperties({"age","laptop"})
//    public Alien(int age, Laptop laptop) {
//        this.age = age;
//        this.laptop = laptop;
//    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }

    public void code() {
        System.out.println("coding");
        com.compile();
    }
}
