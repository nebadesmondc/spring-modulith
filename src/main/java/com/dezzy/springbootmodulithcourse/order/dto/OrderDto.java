package com.dezzy.springbootmodulithcourse.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderDto(
        @NotBlank(message = "Customer name is required")
        String customerName,
        @NotBlank(message = "Customer email is required")
        String customerEmail,
        @Valid
        List<InventoryRequestDto> items
) {}
