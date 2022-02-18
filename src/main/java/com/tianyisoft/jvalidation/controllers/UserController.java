package com.tianyisoft.jvalidation.controllers;

import com.tianyisoft.jvalidate.JValidator;
import com.tianyisoft.jvalidate.annotations.JValidated;
import com.tianyisoft.jvalidation.pojos.Create;
import com.tianyisoft.jvalidation.pojos.Update;
import com.tianyisoft.jvalidation.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody @JValidated User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<?> store(@RequestBody @JValidated(groups = {Create.class, Update.class}) User user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public User withoutAnnotation(@RequestBody User user) {
        Class<?>[] groups = new Class<?>[]{Create.class, Update.class};
        Map<String, List<String>> errors = JValidator.validate(jdbcTemplate, user, groups);
        System.out.println(errors);
        return user;
    }
}
