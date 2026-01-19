package se.alexjons987.item_registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons987.item_registry.entity.Item;

@Repository
public interface InventoryRepository extends JpaRepository<Item, Long> {

}
