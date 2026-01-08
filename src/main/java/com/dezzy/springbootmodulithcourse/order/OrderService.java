package com.dezzy.springbootmodulithcourse.order;

import com.dezzy.springbootmodulithcourse.order.dto.InventoryRequestDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderPaymentDto;
import com.dezzy.springbootmodulithcourse.order.dto.OrderResponseDto;
import com.dezzy.springbootmodulithcourse.order.type.Status;
import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryDto;
import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final OrderInventoryRepository orderInventoryRepository;
    private final OrderEventService orderEventService;

    public OrderResponseDto createOrder(OrderDto orderDto) {

        List<String> inventoryNames = orderDto.items().stream()
                .map(InventoryRequestDto::inventoryName)
                .toList();

        List<InventoryDto> inventories = inventoryService.fetchAllInNames(inventoryNames);
        final AtomicLong amount = new AtomicLong();

        Order order = buildAndPersistOrder(orderDto);
        log.info("Order with id {} has been created successfully", order.getOrderIdentifier());

        buildAndPersistOrderInventories(orderDto, inventories, order.getId(), amount);

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto(order.getOrderIdentifier(), amount.get());
        orderEventService.completeOrder(orderPaymentDto);


        return new OrderResponseDto("Order created successfully");
    }

    private void buildAndPersistOrderInventories(OrderDto orderDto, List<InventoryDto> inventories, long orderId, AtomicLong amount) {
        List<OrderInventory> orderInventories = new ArrayList<>(inventories.size());

        inventories.forEach(inventoryDto -> {
            OrderInventory orderInventory = new OrderInventory();

            InventoryRequestDto inventoryRequestDto = getInventoryRequestDtoByName(inventoryDto.name(), orderDto.items());
            orderInventory.setOrderId(orderId);
            orderInventory.setInventoryId(inventoryDto.id());
            orderInventory.setQty(inventoryRequestDto.qty());

            long totalPrice = inventoryDto.price() * inventoryRequestDto.qty();
            orderInventory.setTotalQtyPrice(totalPrice);

            orderInventories.add(orderInventory);
            amount.addAndGet(totalPrice);
        });

        log.info("Order with id {} has been persisted successfully", orderId);
        orderInventoryRepository.saveAll(orderInventories);
    }

    private static InventoryRequestDto getInventoryRequestDtoByName(String InventoryName, List<InventoryRequestDto> inventoryRequestDtoList) {
        return inventoryRequestDtoList.stream()
                .filter(dto -> dto.inventoryName().equals(InventoryName))
                .findFirst()
                .orElse(null);
    }

    private Order buildAndPersistOrder(OrderDto orderDto) {
        final String orderIdentifier = "ORD-" + System.currentTimeMillis();
        Order order = new Order();

        order.setOrderIdentifier(orderIdentifier);
        order.setCustomerName(orderDto.customerName());
        order.setCustomerEmail(orderDto.customerEmail());
        order.setStatus(Status.COMPLETED);

        return orderRepository.save(order);
    }
}
