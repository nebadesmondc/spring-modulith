package com.dezzy.springbootmodulithcourse.eventaction.action;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {
    @Override
    public String convertToDatabaseColumn(Action action) {
        if (action == null) throw new IllegalArgumentException("action cannot be null");
        return action.getCode();
    }

    @Override
    public Action convertToEntityAttribute(String code) {
        if (code == null) throw new IllegalArgumentException("code cannot be null");
        return Arrays.stream(Action.values())
                .filter(action -> action.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
    }
}