package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final Map<Long, Event> events = new HashMap<>();
    private final Map<String, Attendance> attendances = new HashMap<>();
    private Long nextEventId = 1L;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        event.setId(nextEventId);
        events.put(nextEventId, event);
        nextEventId++;
        return ResponseEntity.ok(event);
    }

    @PostMapping("/{eventId}/invite")
    public ResponseEntity<Attendance> inviteMember(@PathVariable Long eventId, @RequestParam Long memberId) {
        String key = eventId + "_" + memberId;
        Attendance attendance = new Attendance(null, eventId, memberId, "приглашён");
        attendances.put(key, attendance);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/{eventId}/attendance")
    public ResponseEntity<Attendance> markAttendance(@PathVariable Long eventId, @RequestParam Long memberId, @RequestParam String status) {
        String key = eventId + "_" + memberId;
        Attendance attendance = attendances.getOrDefault(key, new Attendance(null, eventId, memberId, status));
        attendance.setStatus(status);
        attendances.put(key, attendance);
        return ResponseEntity.ok(attendance);
    }
}