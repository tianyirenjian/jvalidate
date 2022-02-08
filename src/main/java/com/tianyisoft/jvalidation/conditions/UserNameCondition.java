package com.tianyisoft.jvalidation.conditions;

import com.tianyisoft.jvalidate.Condition;

import java.util.Arrays;

public class UserNameCondition implements Condition {
    @Override
    public Boolean needValidate(Object[] args) {
        System.out.println(Arrays.toString(args));
        return false;
    }
}
