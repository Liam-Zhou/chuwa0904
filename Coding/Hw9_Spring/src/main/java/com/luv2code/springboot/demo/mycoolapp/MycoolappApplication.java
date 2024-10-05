package com.luv2code.springboot.demo.mycoolapp;

import com.luv2code.springboot.demo.mycoolapp.components.Injections.InterfaceInject;
import com.luv2code.springboot.demo.mycoolapp.components.Injections.MyInterfaceInjectExample;
import com.luv2code.springboot.demo.mycoolapp.components.impl.BeanConfig;
import com.luv2code.springboot.demo.mycoolapp.getComponentByName.DependencyInjectionByName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MycoolappApplication {

//	- Different types of dependency injection
//	- Bean Scope
//	- Dependency injection by type and by name, when there's ambiguity in bean definition
//	- Bean registration by both Component and @Bean
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MycoolappApplication.class, args);
		// Created byType and byName
		context.getBean("dependencyInjectionByName", DependencyInjectionByName.class).printMessage();
		// Created with @Bean and @Configuration
		context.getBean(BeanConfig.class).getNewClassForBean().printMessage();
		// Interface Injection
		MyInterfaceInjectExample t = context.getBean(MyInterfaceInjectExample.class);
		InterfaceInject in = context.getBean(InterfaceInject.class);
		in.inject(t);
		t.serve();
	}
}
