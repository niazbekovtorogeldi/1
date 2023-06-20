package com.example.finalproject.exception;

public class BadCredentialException  extends RuntimeException{
    public BadCredentialException() {
    }

    public BadCredentialException(String message) {
        super(message);
    }
}
