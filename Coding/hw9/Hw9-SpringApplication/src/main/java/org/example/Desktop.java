package org.example;

public class Desktop implements Computer{

    public Desktop(){
        System.out.println("Desktop Created");
    }

    @Override
    public void compile(){
        System.out.println("Compiling using Desktop");
    }
}
