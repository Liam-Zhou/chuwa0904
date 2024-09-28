package com.IOCannotation.ComponentAndBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean04.xml");

        CarService carService = context.getBean(CarService.class);
        carService.printCars();
    }
}
