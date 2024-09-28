package com.IOCannotation.BeanScopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class PrototypeBean {
}
