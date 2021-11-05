package com.tianyisoft.jvalidate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JValidate
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BetweenString {
    String message() default "%s 长度必须在 %ld 和 %ld 之间";
    long min();
    long max();
}
