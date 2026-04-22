package com.genshin.architect;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GenshinCharacterRepository extends JpaRepository<GenshinCharacter, Long> {

    // 🌟 NEW: Spring magically translates this into: 
    // SELECT * FROM genshin_character WHERE element = ?
    List<GenshinCharacter> findByElement(String element);

    // 🌟 NEW: Spring translates this into a search that ignores uppercase/lowercase:
    // SELECT * FROM genshin_character WHERE name LIKE %?%
    List<GenshinCharacter> findByNameContainingIgnoreCase(String name);
    
}