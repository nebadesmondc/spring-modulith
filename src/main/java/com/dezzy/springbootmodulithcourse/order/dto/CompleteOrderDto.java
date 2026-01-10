package com.dezzy.springbootmodulithcourse.order.dto;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.CustomEventMarker;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrderDto(
        long orderId,
        boolean paymentComplete
) {
}
