package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.Accepted;
import com.tianyisoft.jvalidate.utils.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class AcceptedValidator extends Validator {
    public Tuple2<Boolean, String> validate(Accepted accepted, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        List<String> acceptedValues = new ArrayList<String>() {{
            add("on");
            add("yes");
            add("1");
            add("true");
        }};
        if (o == null || (o instanceof String && (acceptedValues.contains(o)))) {
            return trueResult();
        }
        return falseResult(accepted.message(), fieldName);
    }
}
