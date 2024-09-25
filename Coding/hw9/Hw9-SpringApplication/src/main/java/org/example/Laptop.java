package org.example;

public class Laptop implements Computer {
    public Laptop() {
        System.out.println("Laptop created");
    }

    @Override
    public void compile(){
        System.out.println("Compiling using Laptop");
    }
}
