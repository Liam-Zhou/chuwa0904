package com.IOCannotation.DIAutowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person1 {
    @Autowired
    @Qualifier("engineer")
//    @Qualifier("teacher")
    Person person;
}
