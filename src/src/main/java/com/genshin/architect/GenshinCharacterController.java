package com.genshin.architect;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import java.util.List;

@RestController
public class GenshinCharacterController {

    private final GenshinCharacterRepository repository;

    public GenshinCharacterController(GenshinCharacterRepository repository) {
        this.repository = repository;
    }

    // 1. CREATE (with @Valid validation)
    @PostMapping("/add-character")
    public String addCharacter(@Valid @RequestBody GenshinCharacter newCharacter) {
        repository.save(newCharacter);
        return "Character saved successfully!";
    }

    // 2. READ (Full List)
    @GetMapping("/all-characters")
    public List<GenshinCharacter> getAllCharacters() {
        return repository.findAll();
    }

    // 3. DELETE
    @DeleteMapping("/delete-character/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        repository.deleteById(id);
        return "Character deleted!";
    }

    // 4. UPDATE (with @Valid validation)
    @PutMapping("/update-character/{id}")
    public String updateCharacter(@PathVariable Long id, @Valid @RequestBody GenshinCharacter updatedData) {
        updatedData.setId(id); 
        repository.save(updatedData); 
        return "Character updated successfully!";
    }

    // 5. BACKEND FILTER (Custom SQL Query)
    @GetMapping("/filter-characters")
    public List<GenshinCharacter> filterCharacters(@RequestParam String element) {
        return repository.findByElement(element);
    }

    // 6. BACKEND SEARCH (Custom SQL Query)
    @GetMapping("/search-characters")
    public List<GenshinCharacter> searchCharacters(@RequestParam String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // 7. PAGINATED READ (Chunks of 5 characters)
    @GetMapping("/paged-characters")
    public Page<GenshinCharacter> getPagedCharacters(@RequestParam(defaultValue = "0") int page) {
        return repository.findAll(PageRequest.of(page, 5));
    }
}