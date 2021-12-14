package com.tianyisoft.jvalidation.controllers;

import com.tianyisoft.jvalidate.annotations.JValidated;
import com.tianyisoft.jvalidate.utils.BindingErrors;
import com.tianyisoft.jvalidation.pojos.Create;
import com.tianyisoft.jvalidation.pojos.Update;
import com.tianyisoft.jvalidation.pojos.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @JValidated
    @PostMapping("/users")
    public ResponseEntity<?> store(@RequestBody @JValidated(groups = {Create.class, Update.class}) User user, BindingErrors bindingErrors) {
        if (bindingErrors.hasErrors()) {
            return ResponseEntity.status(400).body(bindingErrors.getErrors());
        }
        return ResponseEntity.ok(user);
    }
}
