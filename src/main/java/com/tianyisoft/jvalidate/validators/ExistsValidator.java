package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.Exists;
import com.tianyisoft.jvalidate.utils.Tuple2;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExistsValidator extends Validator {
    public Tuple2<Boolean, String> validate(Exists exists, Class<?>[] groups, JdbcTemplate jdbcTemplate, Class<?> klass, Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        if (notNeedValidate(groups, exists.groups(), exists.condition(), klass, object, exists.params())) {
            return trueResult();
        }
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null) {
            return trueResult();
        }
        List<Object> parameters = new ArrayList<>();
        parameters.add(o);

        String sql;
        if (Objects.equals(exists.sql(), "")) {
            Tuple2<String, List<Object>> where = explainWhere(klass, object, exists.where());
            parameters.addAll(where.getV1());
            sql = "select count(*) as aggregate from " + exists.table() + " where `" + exists.field() + "` = ? " + where.getV0();
        } else {
            Tuple2<String, List<Object>> query = explainWhere(klass, object, exists.sql());
            parameters.addAll(query.getV1());
            sql = query.getV0();
        }

        Long count = jdbcTemplate.queryForObject(sql, Long.class, parameters.toArray());
        if (count != null && count > 0) {
            return trueResult();
        }
        return falseResult(exists.message(), fieldName, exists.table());
    }
}
