package com.dezzy.springbootmodulithcourse.inventory.exposed;

import lombok.Builder;
import lombok.Data;


public record InventoryDto(
    Long id,
    String name,
    String description,
    long price
) {}