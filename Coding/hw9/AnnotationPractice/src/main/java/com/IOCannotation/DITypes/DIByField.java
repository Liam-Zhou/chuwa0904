package com.IOCannotation.DITypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIByField {
    @Autowired
    DIObject diObject;
}
