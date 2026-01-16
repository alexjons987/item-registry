package se.alexjons987.item_registry.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.alexjons987.item_registry.config.JwtUtil;
import se.alexjons987.item_registry.dto.UserAccountRequestDTO;
import se.alexjons987.item_registry.dto.UserAccountResponseDTO;
import se.alexjons987.item_registry.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserAccountResponseDTO> register(
            @Valid @RequestBody UserAccountRequestDTO userAccountRequestDTO) {
        return ResponseEntity.ok(authService.registerNewUser(userAccountRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserAccountRequestDTO userAccountRequestDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAccountRequestDTO.getUsername(),
                        userAccountRequestDTO.getPassword()
                )
        );

        String role = authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        return ResponseEntity.ok(jwtUtil.generateToken(userAccountRequestDTO.getUsername(), role));
    }
}
