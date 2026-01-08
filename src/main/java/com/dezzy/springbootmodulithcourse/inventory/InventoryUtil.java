package com.dezzy.springbootmodulithcourse.inventory;

import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryUtil {

    public InventoryDto toDto(Inventory inventory) {
        return new InventoryDto(
            inventory.getId(),
            inventory.getName(),
            inventory.getDescription(),
            inventory.getPrice()
        );
    }
}
