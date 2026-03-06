package charcterthingAPI.demo;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/character")
public class CharacterApiController {
    
    private final CharacterService characterService;
    
    public CharacterApiController(CharacterService characterService) {
        this.characterService = characterService;
    }

    // Get all characters endpoint
    @GetMapping
    public ResponseEntity<Collection<Character>> getAllCharacters(){
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    //get character by id  endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //create new character endpoint
    @PostMapping("/character")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        Character createdCharacter = characterService.createCharacter(character);
        return ResponseEntity.ok(createdCharacter);
    }

    //get character by age range endpoint
    @GetMapping("/age")
    public ResponseEntity<Collection<Character>> getCharactersByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return ResponseEntity.ok(characterService.getCharactersByAgeRange(minAge, maxAge));
    }

    //get character by name endpoint
    @GetMapping("/name")
    public ResponseEntity<Collection<Character>> getCharactersByName(@RequestParam(required = false) String name) {
     List<Character> characters;
        if (name != null) {
            characters = characterService.getCharactersByName(name);
        } else {
            characters = characterService.getAllCharacters();
        }
        return ResponseEntity.ok(characters);
    }

    //get character by role endpoint
    @GetMapping("/role")
    public ResponseEntity<Collection<Character>> getCharactersByRole(@RequestParam(required = false) String role) {
        List<Character> characters;
        if (role != null) {
            characters = characterService.getCharactersByRole(role);
        } else {
            characters = characterService.getAllCharacters();
        }
        return ResponseEntity.ok(characters);
    }

    //update character by id endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character updatedCharacter) {
        Character character = characterService.updateCharacter(id, updatedCharacter);
        if (character != null) {
            return ResponseEntity.ok(character);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //delete character by id endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
    
}
