package com.globallogic.controller;

import com.globallogic.entities.ErrorDetails;
import com.globallogic.exceptions.EmailException;
import com.globallogic.exceptions.PasswordException;
import com.globallogic.exceptions.UserCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler({EmailException.class})
    public ResponseEntity<ErrorDetails> handleEmail(EmailException exception) {
        var errorDetails = new ErrorDetails();
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        errorDetails.setTimestamp(timestamp);
        errorDetails.setDetail("Email invalido");
        errorDetails.setCodigo(getRandom());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({PasswordException.class})
    public ResponseEntity<ErrorDetails> handlePassword(PasswordException exception) {
        var errorDetails = new ErrorDetails();
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        errorDetails.setTimestamp(timestamp);
        errorDetails.setDetail("Password invalido");
        errorDetails.setCodigo(getRandom());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserCreatedException.class})
    public ResponseEntity<ErrorDetails> handleUser(UserCreatedException exception) {
        var errorDetails = new ErrorDetails();
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        errorDetails.setTimestamp(timestamp);
        errorDetails.setDetail("Usuario creado");
        errorDetails.setCodigo(getRandom());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    private int getRandom() {
        int min = 50;
        int max = 100;
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

}
