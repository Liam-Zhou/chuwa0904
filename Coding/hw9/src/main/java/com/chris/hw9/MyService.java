package com.chris.hw9;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private final ServiceBean serviceBean;
    private SimpleComponent simpleComponent;
    // Constructor Injection in MyService
    @Autowired
    public MyService(@Qualifier("primaryServiceBean") ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }
    //Setter Injection
    @Autowired
    public void setSimpleComponent(SimpleComponent simpleComponent) {
        this.simpleComponent = simpleComponent;
    }

    public String getMessages() {
        return serviceBean.getName() + " and " + simpleComponent.getMessage();
    }
}