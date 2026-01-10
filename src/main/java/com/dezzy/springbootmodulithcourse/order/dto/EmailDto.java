package com.dezzy.springbootmodulithcourse.order.dto;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.EMAIL)
public record EmailDto(
        String email,
        String customerName,
        String orderIdentifier,
        long totalAmount,
        boolean orderCompleted
) implements DomainEvent {
}
