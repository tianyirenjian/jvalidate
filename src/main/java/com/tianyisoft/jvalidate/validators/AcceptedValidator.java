package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.Accepted;
import com.tianyisoft.jvalidate.utils.Tuple2;

public class AcceptedValidator extends Validator {
    public Tuple2<Boolean, String> validate(Accepted accepted, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        if (o instanceof String && (((String)o).equals("on") || ((String)o).equals("yes") || ((String)o).equals("1") || ((String)o).equals("true"))) {
            return new Tuple2<>(true, "");
        }
        return new Tuple2<>(false, String.format(accepted.message(), fieldName));
    }
}
