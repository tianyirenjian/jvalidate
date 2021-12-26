package com.tianyisoft.jvalidation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.*;
import java.util.Objects;

public class Test {
    public static void main(String []args) throws ParseException {
        String[] sa = new String[] {"s", "v"};
        int[] ba = new int[] {3, 2, 1};
        System.out.println(((Object) sa).getClass());
        System.out.println(sa.getClass().isArray());
        System.out.println(ba.getClass().isArray());
    }
}
