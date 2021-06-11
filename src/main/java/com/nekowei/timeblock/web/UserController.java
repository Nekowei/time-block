package com.nekowei.timeblock.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> get() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

}
