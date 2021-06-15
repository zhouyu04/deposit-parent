package org.javaboy.vhr.common.interfaces;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface FieldMeta {

    String fileNote() default "";

}
