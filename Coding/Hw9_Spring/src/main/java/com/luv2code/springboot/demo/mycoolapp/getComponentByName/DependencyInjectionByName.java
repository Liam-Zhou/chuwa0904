package com.luv2code.springboot.demo.mycoolapp.getComponentByName;

import com.luv2code.springboot.demo.mycoolapp.components.Injections.ConstructorInject;
import com.luv2code.springboot.demo.mycoolapp.components.Injections.FieldInject;
import com.luv2code.springboot.demo.mycoolapp.components.Injections.SetterInject;
import com.luv2code.springboot.demo.mycoolapp.components.JpaChuwa;
import com.luv2code.springboot.demo.mycoolapp.components.impl.BeanbyType;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependencyInjectionByName {
    @Autowired
     private BeanbyType beanbyType;

    @Autowired
    private JpaChuwa beanByNameA;

    @Autowired
    private JpaChuwa beanByNameB;

    @Autowired
    private ConstructorInject constructorInject;

    @Autowired
    private FieldInject fieldInject;

    @Autowired
    private SetterInject setterInject;

    public void printMessage() {
        beanbyType.printMessage();
        beanByNameA.printMessage();
        beanByNameB.printMessage();

        constructorInject.serve();
        fieldInject.serve();
        setterInject.serve();
    }
}
