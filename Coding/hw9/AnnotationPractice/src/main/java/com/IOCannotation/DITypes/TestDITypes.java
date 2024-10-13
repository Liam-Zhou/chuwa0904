package com.IOCannotation.DITypes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDITypes {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean01.xml");
        // DI by field
        DIByField diByField = context.getBean(DIByField.class);
        System.out.print("DI By Field. ");
        diByField.diObject.say();

        // DI by constructor
        DIByConstructor diByConstructor = context.getBean(DIByConstructor.class);

        // DI by setter method
        DIBySetter diBySetter = context.getBean(DIBySetter.class);
    }
}
