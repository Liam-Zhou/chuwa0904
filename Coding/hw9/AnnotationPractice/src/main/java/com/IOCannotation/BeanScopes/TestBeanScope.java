package com.IOCannotation.BeanScopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanScope {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean02.xml");
        SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
        System.out.println(singletonBean1);
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
        System.out.println(singletonBean2);
        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1);
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        System.out.println(prototypeBean2);
    }
}
