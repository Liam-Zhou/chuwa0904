package org.example.springdemo1.sample;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
// For example, an annotation whose interface is meta-annotated with @Target(ElementType. FIELD) may only be written as
// a modifier for a field declaration.
@Target({
        ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
// @ symbol denotes an annotation type definition.
public @interface FormatterType {
    String value();
}