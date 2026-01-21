package se.alexjons987.item_registry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.alexjons987.item_registry.dto.UserAccountResponseDTO;
import se.alexjons987.item_registry.service.UserService;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserAccountResponseDTO> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getCurrentUser(authentication));
    }

    @GetMapping("/all")
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.status(501).build(); // TODO: Implement, list all users and their account value
    }

    @GetMapping("/top")
    public ResponseEntity<?> listTopUsers() {
        return ResponseEntity.status(501).build(); // TODO: Implement, top 10 based on account value
    }
}
