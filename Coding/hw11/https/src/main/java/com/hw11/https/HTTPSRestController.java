package com.hw11.https;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTTPSRestController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }
}
