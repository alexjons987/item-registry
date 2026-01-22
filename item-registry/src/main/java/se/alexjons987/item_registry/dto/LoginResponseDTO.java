package se.alexjons987.item_registry.dto;

public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
