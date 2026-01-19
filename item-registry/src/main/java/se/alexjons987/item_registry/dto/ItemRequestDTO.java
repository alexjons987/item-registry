package se.alexjons987.item_registry.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import se.alexjons987.item_registry.enums.Quality;

public class ItemRequestDTO {

    @Positive(message = "level cannot be less than zero")
    private Integer level;

    @Size(min = 2, message = "name cannot contain less than two characters")
    private String name;

    @NotNull(message = "quality must be one of: NORMAL, RARE, LEGENDARY, MYTHIC")
    private Quality quality;

    @NotEmpty(message = "origin has to be specified")
    private String origin;

    @Positive(message = "value cannot be less than zero")
    private Long value;

    public ItemRequestDTO() {
    }

    public ItemRequestDTO(Integer level, String name, Quality quality, String origin, Long value) {
        this.level = level;
        this.name = name;
        this.quality = quality;
        this.origin = origin;
        this.value = value;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public Quality getQuality() {
        return quality;
    }

    public String getOrigin() {
        return origin;
    }

    public Long getValue() {
        return value;
    }
}
