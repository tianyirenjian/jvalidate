package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.Distinct;
import com.tianyisoft.jvalidate.utils.Tuple2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DistinctValidator extends Validator {
    public Tuple2<Boolean, String> validate(Distinct distinct, Class<?>[] groups, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        if (!needValidateByGroups(groups, distinct.groups())) {
            return trueResult();
        }
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null || (!(o.getClass().isArray()) && !(o instanceof List))) {
            return trueResult();
        }
        if (o.getClass().isArray()) {
            Object[] oArray = (Object[]) o;
            Set<Object> set = new HashSet<>(oArray.length);
            set.addAll(Arrays.asList(oArray));
            if (set.size() == oArray.length) {
                return trueResult();
            }
        } else {
            Set<?> set = new HashSet<>((List<?>) o);
            if (set.size() == ((List<?>) o).size()) {
                return trueResult();
            }
        }
        return falseResult(distinct.message(), fieldName);
    }
}
