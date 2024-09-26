package org.example.springdemo1.sample;

import org.springframework.stereotype.Component;
// @Component is an annotation that allows Spring to detect our custom beans automatically.
//
// In other words, without having to write any explicit code, Spring will:
// Scan our application for classes annotated with @Component
// Instantiate them and inject any specified dependencies into them
// Inject them wherever needed

// By default, Spring resolves @Autowired entries by type. If more than one bean of the same type is available in the
// container, the framework will throw a fatal exception.
@FormatterType("Foo")
@Component
//@Component("fooFormatter")
public class FooFormatter implements Formatter {
    public String format() {
        return "foo";
    }
}