package com.tianyisoft.jvalidate.validators;

import com.tianyisoft.jvalidate.annotations.After;
import com.tianyisoft.jvalidate.utils.Tuple2;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AfterValidator extends Validator {
    public Tuple2<Boolean, String> validate(After after, Class<?> klass, Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object o = getFieldValue(klass, object, fieldName);
        if (o == null) {
            return trueResult();
        }
        String date = after.date();
        if (!after.withTime()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try { // after.date as date
                if (o instanceof Date) {
                    Date d = dateFormat.parse(date);
                    if (((Date) o).after(d)) {
                        return trueResult();
                    }
                }
                if (o instanceof LocalDate) {
                    LocalDate d = LocalDate.parse(date);
                    if (((LocalDate) o).isAfter(d)) {
                        return trueResult();
                    }
                }
                if (o instanceof Instant) {
                    Instant d = Instant.parse(date);
                    if (((Instant) o).isAfter(d)) {
                        return trueResult();
                    }
                }
            } catch (ParseException e) { // after.date as field
                List<Field> fields = Arrays.stream(klass.getDeclaredFields()).filter(field -> field.getName().equals(date)).collect(Collectors.toList());
                if (fields.size() == 1) {
                    Field anotherField = fields.get(0);
                    Object p = getFieldValue(klass, object, anotherField.getName());
                    if (p.getClass().equals(o.getClass())) { // 要求类型相等
                        if (o instanceof Date && ((Date) o).after((Date) p)) {
                            return trueResult();
                        }
                        if (o instanceof LocalDate && ((LocalDate)o).isAfter((LocalDate)p)) {
                            return trueResult();
                        }
                        if (o instanceof Instant && ((Instant)o).isAfter((Instant) p)) {
                            return trueResult();
                        }
                    }
                }
            }
        } else {// with time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                if (o instanceof Date) {
                    Date d = dateFormat.parse(date);
                    if (((Date) o).after(d)) {
                        return trueResult();
                    }
                }
                if (o instanceof Instant) {
                    Instant d = Instant.parse(date);
                    if (((Instant) o).isAfter(d)) {
                        return trueResult();
                    }
                }
            } catch (ParseException e) {
                List<Field> fields = Arrays.stream(klass.getDeclaredFields()).filter(field -> field.getName().equals(date)).collect(Collectors.toList());
                if (fields.size() == 1) {
                    Field anotherField = fields.get(0);
                    Object p = getFieldValue(klass, object, anotherField.getName());
                    if (p.getClass().equals(o.getClass())) { // 要求类型相等
                        if (o instanceof Date && ((Date) o).after((Date) p)) {
                            return trueResult();
                        }
                        if (o instanceof Instant && ((Instant)o).isAfter((Instant) p)) {
                            return trueResult();
                        }
                    }
                }
            }
        }
        return falseResult(after.message(), fieldName, after.date());
    }
}
