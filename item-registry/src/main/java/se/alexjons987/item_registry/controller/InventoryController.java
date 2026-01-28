package se.alexjons987.item_registry.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.alexjons987.item_registry.dto.ItemEditRequestDTO;
import se.alexjons987.item_registry.dto.ItemRequestDTO;
import se.alexjons987.item_registry.dto.ItemResponseDTO;
import se.alexjons987.item_registry.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    // Get inventory GET
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getCurrentUserInventory(Authentication authentication) {
        return ResponseEntity.ok(inventoryService.getCurrentUserInventory(authentication));
    }

    // Add item POST
    @PostMapping("/add")
    public ResponseEntity<ItemResponseDTO> addItem(@Valid @RequestBody ItemRequestDTO itemReqDTO,
                                                   Authentication authentication) {
        return ResponseEntity.ok(inventoryService.addNewItem(itemReqDTO, authentication));
    }

    // Remove item DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id, Authentication authentication) {
        inventoryService.deleteItem(id, authentication);
        return ResponseEntity.noContent().build();
    }

    // Edit item PATCH
    @PatchMapping("/edit/{id}")
    public ResponseEntity<ItemResponseDTO> editItem(@Valid @RequestBody ItemEditRequestDTO itemEditReqDTO,
                                                    @PathVariable Long id,
                                                    Authentication authentication) {
        return ResponseEntity.ok(inventoryService.editItem(itemEditReqDTO, id, authentication));
    }
}
