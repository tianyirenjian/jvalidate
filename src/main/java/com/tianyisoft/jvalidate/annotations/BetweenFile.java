package com.tianyisoft.jvalidate.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JValidate
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BetweenFile {
    String message() default "%s 文件大小必须在 %ld 和 %ld 之间";
    long minSize();
    long maxSize();
}
