package com.IOCannotation.DIAutowired;

import org.springframework.stereotype.Component;

@Component
public class Engineer implements Person{
    @Override
    public void say() {
        System.out.println("I am an Engineer.");
    }
}
