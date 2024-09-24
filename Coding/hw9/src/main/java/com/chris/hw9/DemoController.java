package com.chris.hw9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private MyService myService;

    @Autowired
    private PrototypeBean prototypeBean;

    @GetMapping("/test")
    public String testService() {
        return myService.getMessages();
    }

    @GetMapping("/prototype")
    public String getPrototype() {
        return prototypeBean.getIdentityHashCode();
    }
}
