package com.dezzy.springbootmodulithcourse.order.dto;

public record InventoryRequestDto(
        String inventoryName,
        int qty
) {
}
