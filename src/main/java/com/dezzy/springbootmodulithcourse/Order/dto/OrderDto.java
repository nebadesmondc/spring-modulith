package com.dezzy.springbootmodulithcourse.Order.dto;

import java.util.List;

public record OrderDto(
        String customerName,
        String customerEmail,
        List<InventoryRequestDto> items
) {}
