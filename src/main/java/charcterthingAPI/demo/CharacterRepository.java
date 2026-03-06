package charcterthingAPI.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    
    List<Character> findByName(String name);

    @Query(value = "SELECT c.* from character c WHERE c.role = 'Apprentice'", nativeQuery = true)
    List<Character> findApprenticeRole(String role);

    @Query(value = "SELECT c.* from character c WHERE c.role = 'Master'", nativeQuery = true)
    List<Character> findMasterRole(String role);
}
