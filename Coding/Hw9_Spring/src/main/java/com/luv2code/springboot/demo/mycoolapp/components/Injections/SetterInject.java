package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SetterInject {
    private MyRepository myRepository;

    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public String serve() {
        String res = "Setter Inject " + myRepository.fetch();
        System.out.println(res);
        return res;
    }
}
