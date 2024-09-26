package org.fionagu.springbeandemo.dao.impl;

import org.fionagu.springbeandemo.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/*数据层*/
//The @Repository annotation in BookDaoImpl.java tells Spring that this class is a bean,
//When you annotate BookDaoImpl with @Repository, Spring automatically detects it as a candidate for dependency injection.
//Summary: The @Repository annotation in BookDaoImpl.java allows Spring to recognize BookDaoImpl as a bean and manage it as a dependency. When BookServiceImpl requests a BookDao bean (via @Autowired), Spring knows that BookDaoImpl is a suitable candidate for injection because of the @Repository annotation. This way, BookServiceImpl can use the BookDao without needing to know about or create the specific implementation itself.

@Repository
@Scope("prototype")
public class BookDaoImpl implements BookDao {
    public void save(){
        System.out.println("Book dao save ..");
    }
}
