package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.Unique;
import com.tianyisoft.jvalidate.utils.Tuple2;
import org.springframework.jdbc.core.JdbcTemplate;

public class UniqueValidator extends Validator {
    public Tuple2<Boolean, String> validate(Unique unique, JdbcTemplate jdbcTemplate, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null) {
            return trueResult();
        }
        String sql = "select count(*) as aggregate from " + unique.table() + " where " + unique.field() + " = ? " + unique.where();
        Long count = jdbcTemplate.queryForObject(sql, Long.class, o);
        if (count != null && count == 0L) {
            return trueResult();
        }
        return falseResult(unique.message(), fieldName, unique.table());
    }
}
