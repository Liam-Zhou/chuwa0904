package com.hw11.https;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTTPSRestController {
    @GetMapping("/")
    public String sayHello() {
        String s = "Hello World!";
//        String s_null = null;  // Exception
//        System.out.println(s_null.length());
        return s;
    }
}
