package com.luv2code.springboot.demo.mycoolapp.components.Injections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// In interface injection, a class is required to provide an injector method that will inject the dependency.
// This is less common in Spring and is more of a theoretical concept.
@Component
public class InterfaceInject implements DependencyInjector{
    private final MyRepository myRepository;

    @Autowired
    public InterfaceInject(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    @Override
    public void inject(MyInterfaceInjectExample interfaceInjectExample) {
        interfaceInjectExample.setMyRepository(myRepository);
    }
}
