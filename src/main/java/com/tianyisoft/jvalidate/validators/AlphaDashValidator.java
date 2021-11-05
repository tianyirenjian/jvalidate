package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.AlphaDash;
import com.tianyisoft.jvalidate.utils.Tuple2;

public class AlphaDashValidator extends Validator {
    public Tuple2<Boolean, String> validate(AlphaDash alphaDash, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null || (o instanceof String && ((String) o).matches(getRegexp()))) {
            return new Tuple2<>(true, "");
        }
        return new Tuple2<>(false, String.format(alphaDash.message(), fieldName));
    }

    public String getRegexp() {
        return "^[a-zA-Z0-9_-]*$";
    }
}
