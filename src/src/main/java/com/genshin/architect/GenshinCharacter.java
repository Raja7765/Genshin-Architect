package com.genshin.architect;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class GenshinCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Element is required")
    private String element;

    @Min(value = 1, message = "Rarity must be at least 1")
    @Max(value = 5, message = "Genshin only has up to 5-star characters!")
    private int rarity;

    private String imageUrl;

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getElement() { return element; }
    public void setElement(String element) { this.element = element; }

    public int getRarity() { return rarity; }
    public void setRarity(int rarity) { this.rarity = rarity; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}