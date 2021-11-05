package com.tianyisoft.jvalidation;

import com.tianyisoft.jvalidate.annotations.EnableJValidate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJValidate
public class JValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JValidationApplication.class, args);
    }

}
