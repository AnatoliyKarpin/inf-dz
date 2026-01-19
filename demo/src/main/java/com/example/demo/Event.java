package com.example.demo;
import java.time.LocalDateTime;

public class Event {
    private Long id;
    private LocalDateTime dateTime;
    private String title;
    private String description;
    private Long clubId;

    public Event() {}

    public Event(Long id, LocalDateTime dateTime, String title, String description, Long clubId) {
        this.id = id;
        this.dateTime = dateTime;
        this.title = title;
        this.description = description;
        this.clubId = clubId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getClubId() { return clubId; }
    public void setClubId(Long clubId) { this.clubId = clubId; }
}