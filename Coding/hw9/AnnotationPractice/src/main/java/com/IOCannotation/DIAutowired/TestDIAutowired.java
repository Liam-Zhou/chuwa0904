package com.IOCannotation.DIAutowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDIAutowired {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean03.xml");
        Person1 p1 = context.getBean(Person1.class);
        p1.person.say();
    }
}
