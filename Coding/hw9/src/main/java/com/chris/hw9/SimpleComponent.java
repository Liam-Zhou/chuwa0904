package com.chris.hw9;


import org.springframework.stereotype.Component;

@Component
public class SimpleComponent {
    public String getMessage() {
        return "Hello from SimpleComponent!";
    }
}
