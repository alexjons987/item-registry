package se.alexjons987.item_registry.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.alexjons987.item_registry.dto.ItemRequestDTO;
import se.alexjons987.item_registry.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    // Add item POST
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemRequestDTO itemReqDTO, Authentication authentication) {
        return ResponseEntity.ok(itemService.addNewItem(itemReqDTO, authentication));
    }

    // Remove item DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItem() {
        return ResponseEntity.status(501).build(); // TODO: Implement, delete item based on ID for logged in user (JWT)
    }

    // Edit item PATCH
    @PatchMapping("/edit")
    public ResponseEntity<?> editItem() {
        return ResponseEntity.status(501).build(); // TODO: Implement, edit item based on ID for logged in user
    }
}
