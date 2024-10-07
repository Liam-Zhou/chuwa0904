package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldInject {
    @Autowired
    private MyRepository myRepository;

    public String serve() {
        String res = "Field Inject " + myRepository.fetch();
        System.out.println(res);
        return res;
    }
}
