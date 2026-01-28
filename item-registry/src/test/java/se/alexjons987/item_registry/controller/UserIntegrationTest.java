package se.alexjons987.item_registry.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.entity.UserRole;
import se.alexjons987.item_registry.repository.RoleRepository;
import se.alexjons987.item_registry.repository.UserRepository;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        UserRole userRole = roleRepository.save(new UserRole("USER"));

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("UIT");
        userAccount.setPassword(passwordEncoder.encode("UIT123"));
        userAccount.setValue(0L);
        userAccount.setRoles(Set.of(userRole));

        userRepository.save(userAccount);
    }

    @Test
    @DisplayName("register with unavailable username should return 409 Conflict")
    void registerWithUnavailableUsernameShouldReturnConflict() throws Exception {

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "UIT",
                                    "password": "UIT123"
                                }
                                """))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("register as new user and login should return 200 OK")
    void registerAndLoginShouldReturnToken() throws Exception {

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "UITNew",
                                    "password": "UITNew123"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "UITNew",
                                    "password": "UITNew123"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("correct login for pre-existing user should return 200 OK")
    void correctLoginShouldReturnToken() throws Exception {

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "UIT",
                                    "password": "UIT123"
                                }
                                """))
                .andExpect(status().isOk());
    }
}
