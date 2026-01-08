package com.dezzy.springbootmodulithcourse.Order.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OPEN("O"),
    IN_PROGRESS("IP"),
    COMPLETED("C"),
    CANCELLED("X");

    private final String code;
}
