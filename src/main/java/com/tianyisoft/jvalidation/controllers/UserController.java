package com.tianyisoft.jvalidation.controllers;

import com.tianyisoft.jvalidate.annotations.JValidated;
import com.tianyisoft.jvalidation.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @JValidated
    @PostMapping("/users")
    public ResponseEntity<?> store(@RequestBody @JValidated User user) {
        return ResponseEntity.ok(user);
    }
}
