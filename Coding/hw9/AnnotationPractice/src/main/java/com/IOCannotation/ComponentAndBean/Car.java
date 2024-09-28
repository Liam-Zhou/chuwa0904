package com.IOCannotation.ComponentAndBean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String model = "Toyota";

    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

