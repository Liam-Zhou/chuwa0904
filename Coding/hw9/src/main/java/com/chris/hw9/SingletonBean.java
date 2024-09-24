package com.chris.hw9;

import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public String getMessage() {
        return "singleton bean!";
    }
}