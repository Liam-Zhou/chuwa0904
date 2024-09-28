package com.IOCannotation.DITypes;

import org.springframework.stereotype.Component;

@Component
public class DIObject01 implements DIObject{
    @Override
    public void say() {
        System.out.println("Inject successfully!!!");
    }
}
