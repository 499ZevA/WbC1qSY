// 代码生成时间: 2025-08-20 19:20:38
package com.example.inventory;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Controller("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    // Constructor injection of InventoryService
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves the list of all inventory items
     *
     * @return List of inventory items
     */
    @Get("/items")
    public List<InventoryItem> getAllItems() {
        return inventoryService.findAllItems();
    }

    /**
     * Retrieves an inventory item by its ID
     *
     * @param id The ID of the item
     * @return The inventory item if found, otherwise a not found response
     */
    @Get("/items/{id}")
    public HttpResponse<InventoryItem> getItemById(@PathVariable Long id) {
        return inventoryService.findItemById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    /**
     * Adds a new inventory item
     *
     * @param item The item to add
     * @return The created item
     */
    @Post("/items")
    public HttpResponse<InventoryItem> addItem(@Body @Valid InventoryItem item) {
        InventoryItem createdItem = inventoryService.addItem(item);
        return HttpResponse.created(createdItem);
    }
}

/**
 * Service class for Inventory operations
 */
class InventoryService {

    private List<InventoryItem> items = List.of(
        new InventoryItem(1L, "Item 1", 10),
        new InventoryItem(2L, "Item 2", 20)
    );

    public List<InventoryItem> findAllItems() {
        return items;
    }

    public Optional<InventoryItem> findItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public InventoryItem addItem(InventoryItem item) {
        // In a real application, you'd generate a unique ID here
        items.add(item);
        return item;
    }
}

/**
 * Represents an inventory item
 */
class InventoryItem {
    private Long id;
    private String name;
    private Integer quantity;

    public InventoryItem(Long id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters and other methods...
}
