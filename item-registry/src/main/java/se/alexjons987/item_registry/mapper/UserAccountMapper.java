package se.alexjons987.item_registry.mapper;

import org.springframework.stereotype.Component;
import se.alexjons987.item_registry.dto.UserAccountResponseDTO;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.entity.UserAchievement;
import se.alexjons987.item_registry.entity.UserRole;

@Component
public class UserAccountMapper {

    public UserAccountResponseDTO toResponseDTO(UserAccount userAccount) {
        if (userAccount == null) return null;

        return new UserAccountResponseDTO(
                userAccount.getUsername(),
                userAccount.getValue(),
                userAccount.getAchievements().stream()
                        .map(UserAchievement::getName)
                        .toList(),
                userAccount.getRoles().stream()
                        .map(UserRole::getName)
                        .toList()
        );
    }
}
