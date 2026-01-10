package com.dezzy.springbootmodulithcourse.order.typeconverter;

import com.dezzy.springbootmodulithcourse.exception.ModulithException;
import com.dezzy.springbootmodulithcourse.order.type.Status;
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
                .findFirst().orElseThrow(() -> new ModulithException("Status not found"));
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getCode().equalsIgnoreCase(s))
                .findFirst().orElseThrow(() -> new ModulithException("Unknown status code: " + s));
    }
}
