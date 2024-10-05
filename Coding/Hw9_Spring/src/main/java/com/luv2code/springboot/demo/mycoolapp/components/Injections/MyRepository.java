package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.stereotype.Component;

@Component
public class MyRepository {
    public String fetch() {
        return "Data from repository";
    }
}
