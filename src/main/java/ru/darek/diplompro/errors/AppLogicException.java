package ru.darek.diplompro.errors;

public class AppLogicException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public AppLogicException(String code, String message) {
        super(message);
        this.code = code;
    }
}
