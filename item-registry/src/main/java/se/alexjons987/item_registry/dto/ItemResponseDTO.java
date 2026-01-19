package se.alexjons987.item_registry.dto;

import se.alexjons987.item_registry.enums.Quality;

public class ItemResponseDTO {

    private Long id;
    private Integer level;
    private String name;
    private Quality quality;
    private String origin;
    private Long value;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Long id, Integer level, String name, Quality quality, String origin, Long value) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.quality = quality;
        this.origin = origin;
        this.value = value;
    }

    public Long getId() {
        return id;
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
