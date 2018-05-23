package hu.bme.aut.webshop.service.exception;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
    public final String ex;
    public final HttpStatus status;

    public ErrorInfo(Exception ex, HttpStatus status) {
        this.ex = ex.getMessage();
        this.status = status;
    }
}
