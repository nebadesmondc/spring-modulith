package com.dezzy.springbootmodulithcourse.email;

import com.dezzy.springbootmodulithcourse.order.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @ApplicationModuleListener
    void sendEmail(EmailDto emailDto) {
        log.info("Email properties received");

        if(!emailDto.orderCompleted()) {
            log.info("Initial email to {} regarding order {} for amount {}",
                    emailDto.customerName(),
                    emailDto.orderIdentifier(),
                    emailDto.totalAmount()
            );
        } else {
            log.info("Order completion email to {} regarding order {} for amount {}",
                    emailDto.customerName(),
                    emailDto.orderIdentifier(),
                    emailDto.totalAmount()
            );
        }
    }
}
