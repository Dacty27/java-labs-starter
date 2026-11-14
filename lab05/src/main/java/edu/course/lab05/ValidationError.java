package edu.course.lab05;

import java.util.Objects;

public final class ValidationError {
    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        this.lineNumber = lineNumber;
        this.field = Objects.requireNonNull(field);
        this.message = Objects.requireNonNull(message);
    }

    public int lineNumber() { return lineNumber; }
    public String field() { return field; }
    public String message() { return message; }

    @Override
    public String toString() {
        return "line " + lineNumber + " [" + field + "]: " + message;
    }
}