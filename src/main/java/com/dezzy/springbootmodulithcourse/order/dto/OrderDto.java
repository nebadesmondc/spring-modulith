package com.dezzy.springbootmodulithcourse.order.dto;

import java.util.List;

public record OrderDto(
        String customerName,
        String customerEmail,
        List<InventoryRequestDto> items
) {}
