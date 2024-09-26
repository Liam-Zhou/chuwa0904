package org.fionagu.springbeandemo.dao.impl;

import org.fionagu.springbeandemo.dao.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("bookDao2")
@Primary
public class BookDaoImpl2 implements BookDao {
    //This annotation injects the literal string "BookDao impl 2" into the name field
    //without using a constructor or setter method.
    @Value("BookDao impl 2")
    private String name;
    public void save(){
        System.out.println("Book dao save .. 2 "+ name);
    }
}
