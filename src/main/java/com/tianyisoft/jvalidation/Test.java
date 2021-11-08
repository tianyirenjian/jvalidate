package com.tianyisoft.jvalidation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.*;

public class Test {
    public static void main(String []args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = dateFormat.parse("1989-12-19 12:13:56");
        LocalDate d2 = LocalDate.parse("2018-03-25");
        Instant d3 = Instant.parse("2018-11-30T18:35:24.00Z");
        System.out.println(d);
        System.out.println(d2);
        System.out.println(d3);
    }
}
