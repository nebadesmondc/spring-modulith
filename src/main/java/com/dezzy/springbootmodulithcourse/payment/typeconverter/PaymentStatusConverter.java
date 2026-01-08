package com.dezzy.springbootmodulithcourse.payment.typeconverter;

import com.dezzy.springbootmodulithcourse.payment.type.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        if(paymentStatus == null) throw new IllegalArgumentException("paymentStatus cannot be null");
        return paymentStatus.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String s) {
        if(s == null) throw new IllegalArgumentException("paymentStatus cannot be null");
        return Arrays.stream(PaymentStatus.values())
                .filter(status -> status.getCode().equalsIgnoreCase(s))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
