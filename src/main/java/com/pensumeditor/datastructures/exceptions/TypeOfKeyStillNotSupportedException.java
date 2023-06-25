package com.pensumeditor.datastructures.exceptions;

public class TypeOfKeyStillNotSupportedException extends RuntimeException {

    public TypeOfKeyStillNotSupportedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public TypeOfKeyStillNotSupportedException() {
        super("");
    }

}
