package com.dezzy.springbootmodulithcourse.Order;

import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryService inventoryService;
}
