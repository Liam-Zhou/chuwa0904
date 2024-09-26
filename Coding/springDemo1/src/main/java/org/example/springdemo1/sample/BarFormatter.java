package org.example.springdemo1.sample;

import org.springframework.stereotype.Component;
@FormatterType("Bar")
@Component
//@Component("barFormatter")
public class BarFormatter implements Formatter {
    public String format() {
        return "bar";
    }
}