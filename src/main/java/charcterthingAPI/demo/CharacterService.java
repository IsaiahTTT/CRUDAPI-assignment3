package charcterthingAPI.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {
    
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }  

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }

    public Character updateCharacter(Long id, Character updatedCharacter) {
        return characterRepository.findById(id)
                .map(character -> {
                    character.setName(updatedCharacter.getName());
                    character.setDescription(updatedCharacter.getDescription());
                    character.setRole(updatedCharacter.getRole());
                    character.setAge(updatedCharacter.getAge());
                    return characterRepository.save(character);
                })
                .orElse(null);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    public List<Character> getCharactersByName(String name) {
        return characterRepository.findByName(name);
    }

    public List<Character> getCharactersByRole(String role) {
        if (role.equalsIgnoreCase("Apprentice")) {
            return characterRepository.findApprenticeRole(role);
        } else if (role.equalsIgnoreCase("Master")) {
            return characterRepository.findMasterRole(role);
        } else {
            return List.of(); // Return an empty list if the role is not recognized
        }
    }

    public List<Character> getCharactersByAgeRange(int minAge, int maxAge) {
        return characterRepository.findAll().stream()
                .filter(character -> character.getAge() >= minAge && character.getAge() <= maxAge)
                .toList();
    }
}
