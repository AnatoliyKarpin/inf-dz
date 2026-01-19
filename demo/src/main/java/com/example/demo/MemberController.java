package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final Map<Long, Member> members = new HashMap<>();
    private Long nextId = 1L;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        member.setId(nextId);
        members.put(nextId, member);
        nextId++;
        return ResponseEntity.ok(member);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(new ArrayList<>(members.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = members.get(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        if (!members.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        member.setId(id);
        members.put(id, member);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        members.remove(id);
        return ResponseEntity.noContent().build();
    }
}