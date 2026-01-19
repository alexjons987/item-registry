package se.alexjons987.item_registry.mapper;

import org.springframework.stereotype.Component;
import se.alexjons987.item_registry.dto.ItemResponseDTO;
import se.alexjons987.item_registry.entity.Item;

@Component
public class ItemMapper {

    public ItemResponseDTO toResponseDTO(Item item) {
        if (item == null) return null;

        return new ItemResponseDTO(
                item.getId(),
                item.getLevel(),
                item.getName(),
                item.getQuality(),
                item.getOrigin(),
                item.getValue()
        );
    }
}
