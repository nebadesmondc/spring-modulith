package com.dezzy.springbootmodulithcourse.Order.typeconverter;

import com.dezzy.springbootmodulithcourse.Order.type.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        return Arrays.stream(Status.values())
                .filter(s -> s == status)
                .map(Status::getCode)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown status: " + status));
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getCode().equalsIgnoreCase(s))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown status code: " + s));
    }
}
