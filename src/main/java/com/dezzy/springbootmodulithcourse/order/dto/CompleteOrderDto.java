package com.dezzy.springbootmodulithcourse.order.dto;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.CustomEventMarker;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrderDto(
        @NotNull(message = "Order ID is required")
        @Positive(message = "Order ID must be greater than zero")
        long orderId,
        boolean paymentComplete
) {
}
