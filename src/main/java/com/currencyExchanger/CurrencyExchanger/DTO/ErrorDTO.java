package com.currencyExchanger.CurrencyExchanger.DTO;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"unused", "ArraysAsListWithZeroOrOneArgument"})
public class ErrorDTO {
    private final HttpStatus status;
    private final String message;
    private List<String> errors;

    public ErrorDTO(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorDTO(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ErrorDTO(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
