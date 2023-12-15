package com.globallogic.controller;


import com.globallogic.entities.User;
import com.globallogic.entities.UserLoginData;
import com.globallogic.exceptions.EmailException;
import com.globallogic.exceptions.PasswordException;
import com.globallogic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z0-9]{8,12}$";

    @RequestMapping(value = "signup",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST
    )
    public ResponseEntity<?> signup(@RequestBody User user) {
        if(!isValidEmail(user.getEmail())) throw new EmailException();
        if(!isValidPassword(user.getPassword())) throw new PasswordException();
        var response = userService.save(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "login",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    public ResponseEntity<UserLoginData> login(@RequestHeader("Authorization") String token) {
        var userLoginData = userService.getUserLoginData(token);
        return new ResponseEntity<>(userLoginData, HttpStatus.CREATED);
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
