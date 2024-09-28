package com.IOCannotation.ComponentAndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final Car componentCar;
    private final Car beanCar;

    // Injecting both the component-based and bean-based Car instances
    @Autowired
    public CarService(@Qualifier("car") Car componentCar, @Qualifier("carBean") Car beanCar) {
        this.componentCar = componentCar;
        this.beanCar = beanCar;
    }

    public void printCars() {
        System.out.println("Component Car: " + componentCar.getModel());
        System.out.println("Bean Car: " + beanCar.getModel());
    }
}
