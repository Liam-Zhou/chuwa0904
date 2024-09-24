package com.chris.hw9;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    public String getIdentityHashCode() {
        return "Prototype Bean's System Identity: " + System.identityHashCode(this);
    }
}