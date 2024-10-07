package org.fionagu.springsecuritydemo.log;

import java.lang.annotation.*;

/*Custom Annotation */
/*methods with this annotation, you want extra behavior (logging) to happen.*/
//ensures the annotation is available at runtime so Spring can detect it
@Retention(RetentionPolicy.RUNTIME)
//It specifies that this annotation can only be applied to methods.
@Target({ElementType.METHOD})
@Documented
public @interface MethodLog {
}
