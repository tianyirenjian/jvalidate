package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.utils.Tuple2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class Validator {
    public Object getFieldValue(Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = klass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public Tuple2<Boolean, String> trueResult() {
        return new Tuple2<>(true, "");
    }

    public Tuple2<Boolean, String> falseResult(String message, Object ...params) {
        return new Tuple2<>(false, String.format(message, params));
    }
}
