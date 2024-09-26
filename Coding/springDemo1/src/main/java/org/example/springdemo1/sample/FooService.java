package org.example.springdemo1.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FooService {
    // @Autowired on Properties
    // As a result, Spring injects fooFormatter when FooService is created.
     @Autowired(required = false)
//     @Qualifier("fooFormatter")
     @FormatterType("Foo")
     private Formatter formatter;

    // @Autowired on Setters
    // the setter method is called with the instance of FooFormatter when FooService is created:
//    @Autowired
//    public void setFormatter(FooFormatter fooFormatter) {
//        this.formatter = fooFormatter;
//    }
    // @Autowired on Constructors
    // an instance of FooFormatter is injected by Spring as an argument to the FooService constructor:
//    @Autowired
//    public FooService(FooFormatter fooFormatter) {
//        this.formatter = fooFormatter;
//    }
}