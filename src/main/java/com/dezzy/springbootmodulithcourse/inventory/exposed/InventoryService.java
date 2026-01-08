package com.dezzy.springbootmodulithcourse.inventory.exposed;

import java.util.List;

public interface InventoryService {

    InventoryDto fetchInventoryByName(String name);

    List<InventoryDto> fetchAllInNames(List<String> names);
}
