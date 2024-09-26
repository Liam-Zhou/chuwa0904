package org.fionagu.springbeandemo.dao;

import org.fionagu.springbeandemo.config.SpringConfig;
import org.fionagu.springbeandemo.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        BookDao boookDao1 = ctx.getBean(BookDao.class);
//        BookDao boookDao2 = ctx.getBean(BookDao.class);
//        System.out.println("Displaying scope annotation prototype:  ");
//        System.out.println("This is BookDao bean 1  "+boookDao1);
//        System.out.println("This is BookDao bean 2  "+boookDao2);
/*
        //how you access and utilize a bean that is already managed by the Spring container.
        BookService bookService = ctx.getBean(BookService.class);
        bookService.save();*/

        //Retrieving the DataSource Bean
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println("This is a datasource "+ dataSource);

    }
}
