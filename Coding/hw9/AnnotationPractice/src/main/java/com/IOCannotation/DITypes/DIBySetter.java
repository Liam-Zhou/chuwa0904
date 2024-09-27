package com.IOCannotation.DITypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIBySetter {
    private DIObject diObject;

    @Autowired
    public void setDiObject(DIObject diObject) {
        this.diObject = diObject;
        System.out.print("DI By Setter. ");
        diObject.say();
    }
}
