package com.dezzy.springbootmodulithcourse.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String messages, HttpStatus status) {
}
