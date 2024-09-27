package com.IOCannotation.DITypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIByConstructor {

    private DIObject diObject;

    @Autowired
    public DIByConstructor(DIObject diObject) {
        this.diObject = diObject;
        System.out.print("DI By Constructor. ");
        diObject.say();
    }
}
