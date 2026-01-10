package com.dezzy.springbootmodulithcourse.order;

import com.dezzy.springbootmodulithcourse.order.dto.CompleteOrderDto;
import com.dezzy.springbootmodulithcourse.order.dto.EmailDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void completeOrder(final OrderPaymentDto orderPaymentDto, EmailDto emailDto) {

        log.info("Completing order with id {} for amount {}", orderPaymentDto.orderId(), orderPaymentDto.amount());
        applicationEventPublisher.publishEvent(orderPaymentDto);

        log.info("Publishing email event for order id {} to email {}", emailDto.orderIdentifier(), emailDto.email());
        applicationEventPublisher.publishEvent(emailDto);
    }

    @Transactional
    public void completePayment(CompleteOrderDto completeOrder, EmailDto emailDto) {
        log.info("Completing order with id {}", completeOrder.orderId());
        applicationEventPublisher.publishEvent(completeOrder);

        log.info("completed payment email for order id {} to email {}", emailDto.orderIdentifier(), emailDto.email());
        applicationEventPublisher.publishEvent(emailDto);
    }
}
