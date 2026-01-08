package com.dezzy.springbootmodulithcourse.Order.dto;

public record InventoryRequestDto(
        String inventoryName,
        int qty
) {
}
