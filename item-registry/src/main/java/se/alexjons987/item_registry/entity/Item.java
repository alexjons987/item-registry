package se.alexjons987.item_registry.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import se.alexjons987.item_registry.enums.Quality;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer level;

    @NotNull
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Quality quality;

    @NotNull
    private String origin;

    @NotNull
    @Column(name = "item_value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserAccount owner;

    public Item() {
    }

    public Item(Integer level, String name, Quality quality, String origin, Long value) {
        this.level = level;
        this.name = name;
        this.quality = quality;
        this.origin = origin;
        this.value = value;
    }

    public Item(Long id, Integer level, String name, Quality quality, String origin, Long value) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public String toString() {
        return String.format(
                "[%d] %s %s - lvl: %d (%d)", // [2] Collector's Cozy Camper - lvl: 1 ()
                this.getId(),
                this.getQuality(),
                this.getName(),
                this.getLevel(),
                this.getValue()
        );
    }
}
