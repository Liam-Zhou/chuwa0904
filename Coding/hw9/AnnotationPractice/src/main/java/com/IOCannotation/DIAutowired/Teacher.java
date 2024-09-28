package com.IOCannotation.DIAutowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Teacher implements Person{
    @Override
    public void say() {
        System.out.println("I am a Teacher.");
    }
}
