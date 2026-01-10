package com.dezzy.springbootmodulithcourse.order;

import com.dezzy.springbootmodulithcourse.order.dto.InventoryRequestDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderPaymentDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
class OrderIntegrationTest {

    @Autowired
    OrderService orderService;

    @Test
    void verifyModule() {

    }

    @Test
    void createOrder() {
        List<InventoryRequestDto> requests = List.of(
                new InventoryRequestDto("Laptop Pro 15", 2),
                new InventoryRequestDto("Wireless Mouse", 3)
        );

        OrderDto orderDto = new OrderDto(
                "Tester",
                "tester@gmail.com",
                requests

        );

        OrderResponseDto order = orderService.createOrder(orderDto);

        assertThat(order.message().equals("Order created successfully"));
    }

    @Test
    void publishOrderPaymentDto(Scenario scenario) {
        long randomOrderId = java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 100000);

        scenario.publish(new OrderPaymentDto(randomOrderId, 5000L))
                .andWaitForEventOfType(OrderPaymentDto.class)
                .matching(event -> event.amount() == 5000L)
                .toArriveAndVerify(ev -> System.out.println(ev.amount()));
    }
}