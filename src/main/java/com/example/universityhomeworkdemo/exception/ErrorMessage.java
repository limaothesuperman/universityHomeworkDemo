package com.example.universityhomeworkdemo.exception;

import java.util.Date;

public class ErrorMessage {
    private int statusCode;
    private Date date;
    private String message;
    private String description;

    public int getStatusCode() {
        return statusCode;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public ErrorMessage(int statusCode, Date date, String message, String description) {
        this.statusCode = statusCode;
        this.date = date;
        this.message = message;
        this.description = description;
    }
}
