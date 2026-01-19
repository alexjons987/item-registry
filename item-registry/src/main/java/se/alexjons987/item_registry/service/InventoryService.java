package se.alexjons987.item_registry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.alexjons987.item_registry.dto.ItemRequestDTO;
import se.alexjons987.item_registry.dto.ItemResponseDTO;
import se.alexjons987.item_registry.entity.Item;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.entity.UserAchievement;
import se.alexjons987.item_registry.enums.Quality;
import se.alexjons987.item_registry.exception.AchievementNotFoundException;
import se.alexjons987.item_registry.mapper.ItemMapper;
import se.alexjons987.item_registry.repository.AchievementRepository;
import se.alexjons987.item_registry.repository.InventoryRepository;
import se.alexjons987.item_registry.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    ItemMapper itemMapper;

    public List<ItemResponseDTO> getCurrentUserInventory(Authentication authentication) {
        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.getItems().stream()
                .map(itemMapper::toResponseDTO)
                .toList();
    }

    public ItemResponseDTO addNewItem(ItemRequestDTO itemRequestDTO, Authentication authentication) {

        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Item item = new Item();

        item.setLevel(itemRequestDTO.getLevel());
        item.setName(itemRequestDTO.getName());
        item.setQuality(itemRequestDTO.getQuality());
        item.setOrigin(itemRequestDTO.getOrigin());
        item.setValue(itemRequestDTO.getValue());
        item.setOwner(user);

        Item savedItem = inventoryRepository.save(item);

        Long newAccountValue = user.getValue() + itemRequestDTO.getValue();
        user.setValue(newAccountValue);

        updateUserAchievements(user, item);

        userRepository.save(user);

        return itemMapper.toResponseDTO(savedItem);
    }

    private void updateUserAchievements(UserAccount userAccount, Item item) {
        Set<UserAchievement> userAchievements = userAccount.getAchievements();

        addAchievementIfMissing(userAccount, "My Precious");

        if (item.getQuality() == Quality.LEGENDARY) {
            addAchievementIfMissing(userAccount, "Legen... Wait for it... -dary!");
        }

        if (item.getQuality() == Quality.MYTHIC) {
            addAchievementIfMissing(userAccount, "A conversation piece");
        }

        if (userAccount.getValue() > 9000L) {
            addAchievementIfMissing(userAccount, "It's Over 9000!");
        }
    }

    private void addAchievementIfMissing(UserAccount user, String name) {
        UserAchievement achievement = achievementRepository.findByName(name)
                .orElseThrow(() -> new AchievementNotFoundException("Achievement not found: " + name));

        if (!user.getAchievements().contains(achievement)) {
            user.getAchievements().add(achievement);
        }
    }
}
