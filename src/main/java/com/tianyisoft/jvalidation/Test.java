package com.tianyisoft.jvalidation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.*;

public class Test {
    public static void main(String []args) throws ParseException {
        String a = "ss";
        Object o = a;
        System.out.println(o.getClass());
        System.out.println(o.equals("ss"));
    }
}
