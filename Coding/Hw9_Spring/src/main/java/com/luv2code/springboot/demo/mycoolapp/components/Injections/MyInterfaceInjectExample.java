package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.stereotype.Component;

@Component
public class MyInterfaceInjectExample {
    private MyRepository myRepository;

    public String serve() {
        String res = "InterfaceInject: " + myRepository.fetch();
        System.out.println(res);
        return res;
    }

    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
}
