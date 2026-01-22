package se.alexjons987.item_registry.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.alexjons987.item_registry.dto.ItemEditRequestDTO;
import se.alexjons987.item_registry.dto.ItemRequestDTO;
import se.alexjons987.item_registry.dto.ItemResponseDTO;
import se.alexjons987.item_registry.entity.Item;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.entity.UserAchievement;
import se.alexjons987.item_registry.enums.Quality;
import se.alexjons987.item_registry.exception.AchievementNotFoundException;
import se.alexjons987.item_registry.exception.ForbiddenOperationException;
import se.alexjons987.item_registry.exception.ItemNotFoundException;
import se.alexjons987.item_registry.mapper.ItemMapper;
import se.alexjons987.item_registry.repository.AchievementRepository;
import se.alexjons987.item_registry.repository.InventoryRepository;
import se.alexjons987.item_registry.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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

    @Transactional
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

    @Transactional
    public void deleteItem(Long id, Authentication authentication) {
        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Item itemToDelete = inventoryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with ID " + id + " does not exist"));

        if (!isItemOwnedByUser(itemToDelete, user)) {
            throw new ForbiddenOperationException("You do not have permission to delete this item");
        }

        user.setValue(user.getValue() - itemToDelete.getValue());

        userRepository.save(user);

        inventoryRepository.delete(itemToDelete);
    }

    public ItemResponseDTO editItem(ItemEditRequestDTO itemEditRequestDTO, Long itemId, Authentication authentication) {
        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Item item = inventoryRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException("Item with ID " + itemId + " does not exist")
        );

        if (!isItemOwnedByUser(item, user)) {
            throw new ForbiddenOperationException("You do not have permission to edit this item");
        }

        Long oldItemValue = item.getValue(); // current DB value

        if (itemEditRequestDTO.getValue() != null) {
            Long newItemValue = itemEditRequestDTO.getValue();
            user.setValue(user.getValue() - oldItemValue + newItemValue);
            item.setValue(newItemValue);
        }

        if (itemEditRequestDTO.getLevel() != null) {
            item.setLevel(itemEditRequestDTO.getLevel());
        }

        if (itemEditRequestDTO.getName() != null) {
            item.setName(itemEditRequestDTO.getName());
        }

        if (itemEditRequestDTO.getQuality() != null) {
            item.setQuality(itemEditRequestDTO.getQuality());
        }

        if (itemEditRequestDTO.getOrigin() != null) {
            item.setOrigin(itemEditRequestDTO.getOrigin());
        }

        updateUserAchievements(user, item);

        userRepository.save(user);
        Item savedItem = inventoryRepository.save(item);

        return itemMapper.toResponseDTO(savedItem);
    }

    // Util
    private boolean isItemOwnedByUser(Item item, UserAccount userAccount) {
        return item.getOwner().getId().equals(userAccount.getId());
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
