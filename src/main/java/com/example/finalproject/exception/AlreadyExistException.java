package com.example.finalproject.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
