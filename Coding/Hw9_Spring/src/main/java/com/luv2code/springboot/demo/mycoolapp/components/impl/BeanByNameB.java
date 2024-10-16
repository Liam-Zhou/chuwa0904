package com.luv2code.springboot.demo.mycoolapp.components.impl;

import com.luv2code.springboot.demo.mycoolapp.components.JpaChuwa;
import org.springframework.stereotype.Component;

@Component
public class BeanByNameB implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("BeanByNameB");
    }
}
