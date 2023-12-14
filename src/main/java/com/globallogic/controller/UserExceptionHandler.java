package com.globallogic.controller;

import com.globallogic.exceptions.EmailException;
import com.globallogic.exceptions.PasswordException;
import com.globallogic.exceptions.UserCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({EmailException.class})
    public ResponseEntity<String> handleEmail(EmailException exception) {
        return new ResponseEntity<>("Email invalido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PasswordException.class})
    public ResponseEntity<String> handlePassword(PasswordException exception) {
        return new ResponseEntity<>("Password invalido", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserCreatedException.class})
    public ResponseEntity<String> handleUserCreatedUser(UserCreatedException exception) {
        return new ResponseEntity<>("El usuario existe", HttpStatus.BAD_REQUEST);
    }

}
