package com.luv2code.springboot.demo.mycoolapp.components.impl;

import org.springframework.context.annotation.Configuration;

public class NewClassForBeanCreation {
    public NewClassForBeanCreation () {}

    public void printMessage() {
        System.out.println("Created with Bean and Configuration");
    }
}
