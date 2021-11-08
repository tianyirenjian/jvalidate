package com.tianyisoft.jvalidation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.*;

public class Test {
    public static void main(String []args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("1980-01-01");
        Date d2 = dateFormat.parse("1980-01-01");
        System.out.println(d1.after(d2));
        System.out.println(d1.before(d2));
        System.out.println(d1.equals(d2));
    }
}
