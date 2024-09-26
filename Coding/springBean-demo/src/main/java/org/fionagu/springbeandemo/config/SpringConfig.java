package org.fionagu.springbeandemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.fionagu.springbeandemo.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*配置类*/
@Configuration
@ComponentScan("org.fionagu.springbeandemo")
public class SpringConfig {
    //定义一个方法获取要管理的对象 or bean
    //方法的返回值是bean
    //The @Bean annotation tells Spring that the method returns a bean that should be managed by the Spring container.
    //spring handle its lifecycle
    @Bean
    public DataSource dataSource(BookService bookService){
        System.out.println("print bookService in datasource bean: "+bookService);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
