package com.dezzy.springbootmodulithcourse.inventory;

import com.dezzy.springbootmodulithcourse.exception.ModulithException;
import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryDto;
import com.dezzy.springbootmodulithcourse.inventory.exposed.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class InventoryServiceImpl  implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryDto fetchInventoryByName(String name) {
        return inventoryRepository.getInventoriesByName(name)
                .map(InventoryUtil::toDto)
                .orElseThrow(() -> new ModulithException("Inventory with name " + name + " not found"));
    }

    @Override
    public List<InventoryDto> fetchAllInNames(List<String> names) {
        return inventoryRepository.getInventoryByNameIn(names)
                .stream()
                .map(InventoryUtil::toDto)
                .toList();
    }
}
