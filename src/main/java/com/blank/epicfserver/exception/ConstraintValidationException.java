package com.blank.epicfserver.exception;

public class ConstraintValidationException extends RuntimeException {
    private String field;

    public ConstraintValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
