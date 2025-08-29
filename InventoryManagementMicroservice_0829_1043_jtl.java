// 代码生成时间: 2025-08-29 10:43:53
package com.example.inventory;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import java.util.List;

// InventoryItem entity representing a product in the inventory
@Introspected
@MappedEntity(dialect = Dialect.POSTGRES)
public class InventoryItem {
    @Id
    private Long id;
    private String name;
    private int quantity;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Repository interface for InventoryItem
@JdbcRepository
public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
}

// Inventory management controller
@Controller("/inventory")
public class InventoryController {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryController(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    // Get all inventory items
    @Get("/items")
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    // Get a single inventory item by ID
    @Get("/items/{id}")
    public InventoryItem getInventoryItem(Long id) {
        return inventoryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory item not found"));
    }

    // Add a new inventory item
    @Post("/items")
    public InventoryItem addInventoryItem(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }

    // Update an existing inventory item
    @Put("/items/{id}")
    public InventoryItem updateInventoryItem(Long id, InventoryItem inventoryItem) {
        InventoryItem item = inventoryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory item not found"));
        item.setName(inventoryItem.getName());
        item.setQuantity(inventoryItem.getQuantity());
        return inventoryItemRepository.update(item);
    }

    // Delete an inventory item
    @Delete("/items/{id}")
    public void deleteInventoryItem(Long id) {
        inventoryItemRepository.deleteById(id);
    }
}
