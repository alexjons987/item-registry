package se.alexjons987.item_registry.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.alexjons987.item_registry.dto.ItemRequestDTO;
import se.alexjons987.item_registry.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> getCurrentUserInventory(Authentication authentication) {
        return ResponseEntity.ok(inventoryService.getCurrentUserInventory(authentication));
    }

    // Add item POST
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemRequestDTO itemReqDTO, Authentication authentication) {
        return ResponseEntity.ok(inventoryService.addNewItem(itemReqDTO, authentication));
    }

    // Remove item DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id, Authentication authentication) {
        inventoryService.deleteItem(id, authentication);
        return ResponseEntity.noContent().build();
    }

    // Edit item PATCH
    @PatchMapping("/edit")
    public ResponseEntity<?> editItem() {
        return ResponseEntity.status(501).build(); // TODO: Implement, edit item based on ID for logged in user
    }
}
