package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.BetweenDouble;
import com.tianyisoft.jvalidate.utils.Tuple2;

public class BetweenDoubleValidator extends Validator {
    public Tuple2<Boolean, String> validate(BetweenDouble betweenDouble, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null) {
            return trueResult();
        }
        if (o instanceof Double) {
            double doubleO = (Double)o;
            if (doubleO >= betweenDouble.min() && doubleO <= betweenDouble.max()) {
                return trueResult();
            }
        }
        return falseResult(betweenDouble.message(), fieldName, betweenDouble.min(), betweenDouble.max());
    }
}
