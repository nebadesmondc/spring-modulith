package com.dezzy.springbootmodulithcourse.order.dto;


import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.PAYMENT)
public record OrderPaymentDto(String orderId, long amount) implements DomainEvent {
}
