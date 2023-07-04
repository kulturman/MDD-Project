package com.kulturman.mdd.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private List<Error> errors;
}

@AllArgsConstructor
class Error {
    public String field;
    public String error;
}

