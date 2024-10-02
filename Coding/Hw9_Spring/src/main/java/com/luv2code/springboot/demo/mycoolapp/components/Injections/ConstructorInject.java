package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInject {
    private final MyRepository myRepository;

    @Autowired
    public ConstructorInject(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public String serve() {
        String res = "ConstructorInject " + myRepository.fetch();
        System.out.println(res);
        return res;
    }
}
