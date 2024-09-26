package org.fionagu.springbeandemo.service.impl;

import org.fionagu.springbeandemo.dao.BookDao;
import org.fionagu.springbeandemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/*业务层*/
@Service
public class BookServiceImpl implements BookService {
    //@Autowired annotation to inject dependencies
    // Instead,Spring handles the creation and injection of the BookDao implementation, allowing us to call the methods of BookDao without direct instantiation.
    //When BookServiceImpl requests a BookDao bean (via @Autowired), Spring knows that BookDaoImpl is a suitable candidate for injection because of the @Repository annotation.
    @Autowired
    @Qualifier("bookDao2") // When you have multiple beans of the same type (BookDao), Spring needs to know which one to inject.
    private BookDao bookDao;
/*
    @Autowired
    private BookDao bookDao; // This is field dependency injection*/

/*    // Constructor Injection: Spring will inject the BookDao dependency through this constructor
    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }*/

/*    // Setter method for dependency injection
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao; // Spring injects the BookDao dependency here
    }*/

    public BookDao getBookDao() {
        return bookDao;
    }

    //暴力反射 不需要setter
    //Spring framework automatically injects the required dependency directly into the field or constructor of your class.
    //When you annotate a field, constructor, or setter method with @Autowired, Spring looks for a matching bean and injects it automatically:
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void  save(){
        System.out.println("Book service save ..");
        bookDao.save();// w/o @autowired annotation, it doesn't work, dependency injection
    }
}
