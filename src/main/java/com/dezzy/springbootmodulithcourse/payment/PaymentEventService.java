package com.dezzy.springbootmodulithcourse.payment;

import com.dezzy.springbootmodulithcourse.order.dto.CompleteOrderDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderPaymentDto;
import com.dezzy.springbootmodulithcourse.payment.type.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    @ApplicationModuleListener
    void on(final OrderPaymentDto paymentDto) {
        log.info("Received payment event for order id {} for amount {}", paymentDto.orderId(), paymentDto.amount());

        Payment payment = new Payment();
        payment.setOrderId(paymentDto.orderId());
        payment.setAmount(paymentDto.amount());

        paymentRepository.save(payment);
    }

    @ApplicationModuleListener
    void on(final CompleteOrderDto completeOrderDto) {
        log.info("Received complete order event for order id {}", completeOrderDto.orderId());

        Optional<Payment> optPayment = paymentRepository.getPaymentByOrderId(completeOrderDto.orderId());

        if (optPayment.isPresent()) {
            Payment payment = optPayment.get();
            payment.setStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);
        }
    }
}
