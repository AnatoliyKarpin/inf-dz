package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final Map<Long, Club> clubs = new HashMap<>();
    private Long nextId = 1L;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        club.setId(nextId);
        clubs.put(nextId, club);
        nextId++;
        return ResponseEntity.ok(club);
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(new ArrayList<>(clubs.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubs.get(id);
        if (club == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(club);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        if (!clubs.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        club.setId(id);
        clubs.put(id, club);
        return ResponseEntity.ok(club);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubs.remove(id);
        return ResponseEntity.noContent().build();
    }
}