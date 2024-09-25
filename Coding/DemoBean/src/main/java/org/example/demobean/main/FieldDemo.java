package org.example.demobean.main;
import org.example.demobean.components.JpaChuwa;
import org.springframework.beans.factory.annotation.Autowired;

public class FieldDemo {
    /**
     * type 1, field Injection
     */
//    @Autowired
    private JpaChuwa jpaChuwa;

    /**
     * type 2, Constructor Injection
     * @Autowired可以省略
     */
    //@Autowired
    public FieldDemo(JpaChuwa jpaChuwa) {
        this.jpaChuwa = jpaChuwa;
    }

    public void setJpaChuwa(JpaChuwa jpaChuwa) {
        this.jpaChuwa = jpaChuwa;
    }



}
