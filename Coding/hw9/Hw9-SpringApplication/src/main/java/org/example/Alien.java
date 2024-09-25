package org.example;

import java.beans.ConstructorProperties;

public class Alien {

    private int age;
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
