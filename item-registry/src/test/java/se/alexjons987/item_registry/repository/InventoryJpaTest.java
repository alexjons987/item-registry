package se.alexjons987.item_registry.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import se.alexjons987.item_registry.entity.Item;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.enums.Quality;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class InventoryJpaTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("saved items are owned by currently logged in user")
    void savedItemsAreOwnedByCurrentlyLoggedInUser() {
        final String USERNAME = "jpatest";
        final String PASSWORD = "testjpa";

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(USERNAME);
        userAccount.setPassword(PASSWORD);

        Item item = new Item();
        item.setLevel(10);
        item.setName("The JPA Test");
        item.setQuality(Quality.NORMAL);
        item.setOrigin("Testing");
        item.setValue(100L);
        item.setOwner(userAccount);
        item = inventoryRepository.save(item);

        userAccount.setItems(Set.of(item));
        userRepository.save(userAccount);

        UserAccount foundUserAccount = userRepository.findByUsername(USERNAME).orElseThrow();
        assertEquals(1, foundUserAccount.getItems().size());

        Item foundItem = foundUserAccount.getItems().iterator().next();
        assertEquals(item.getId(), foundItem.getId());
        assertEquals(10, foundItem.getLevel());
        assertEquals("The JPA Test", foundItem.getName());
        assertEquals(Quality.NORMAL, foundItem.getQuality());
        assertEquals("Testing", foundItem.getOrigin());
        assertEquals(100L, foundItem.getValue());
    }
}
