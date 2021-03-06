package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.BeforeOrEqual;
import com.tianyisoft.jvalidate.utils.Tuple2;

public class BeforeOrEqualValidator extends BaseDateValidator {
    public Tuple2<Boolean, String> validate(BeforeOrEqual beforeOrEqual, Class<?>[] groups, Class<?> klass, Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        if (notNeedValidate(groups, beforeOrEqual.groups(), beforeOrEqual.condition(), klass, object, beforeOrEqual.params())) {
            return trueResult();
        }
        return validateDate(klass, object, fieldName, beforeOrEqual.withTime(), beforeOrEqual.date(), beforeOrEqual.message(), "lte");
    }
}
