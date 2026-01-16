package se.alexjons987.item_registry.dto;

import java.util.List;

public class UserAccountResponseDTO {

    private String username;
    private Long value;
    private List<String> roles;

    public UserAccountResponseDTO() {
    }

    public UserAccountResponseDTO(String username, Long value, List<String> roles) {
        this.username = username;
        this.value = value;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public Long getValue() {
        return value;
    }

    public List<String> getRoles() {
        return roles;
    }
}
