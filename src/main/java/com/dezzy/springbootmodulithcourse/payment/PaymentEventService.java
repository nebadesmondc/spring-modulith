package com.dezzy.springbootmodulithcourse.payment;

import com.dezzy.springbootmodulithcourse.order.dto.OrderPaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    @ApplicationModuleListener
    void on(final OrderPaymentDto paymentDto) {
        log.info("Received payment event for order id {} for amount {}", paymentDto.orderId(), paymentDto.amount());
    }
}
