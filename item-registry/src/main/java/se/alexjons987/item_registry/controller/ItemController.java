package se.alexjons987.item_registry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/items")
public class ItemController {

    // Add item POST
    @PostMapping("/add")
    public ResponseEntity<?> addItem() {
        return ResponseEntity.status(501).build(); // TODO: Implement, add new item to logged in user (JWT)
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
