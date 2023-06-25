package com.pensumeditor.datastructures.exceptions;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public KeyNotFoundException() {
        super("");
    }
}
