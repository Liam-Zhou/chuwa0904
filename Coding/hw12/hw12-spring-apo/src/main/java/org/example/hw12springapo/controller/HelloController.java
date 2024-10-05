package org.example.hw12springapo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("hello")
    public String Hello(){
        return "Hello World";
    }


    @GetMapping("hi")
    public String Hi(){return "Hi baby";}
}
