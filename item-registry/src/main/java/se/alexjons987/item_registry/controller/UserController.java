package se.alexjons987.item_registry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.status(501).build(); // TODO: Implement, list all users and their "value"
    }

    @GetMapping("/top")
    public ResponseEntity<?> listTopUsers() {
        return ResponseEntity.status(501).build(); // TODO: Implement, top 10 based on "value"
    }
}
