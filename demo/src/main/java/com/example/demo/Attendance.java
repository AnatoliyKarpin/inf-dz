package com.example.demo;

public class Attendance {
    private Long id;
    private Long eventId;
    private Long memberId;
    private String status;

    public Attendance() {}

    public Attendance(Long id, Long eventId, Long memberId, String status) {
        this.id = id;
        this.eventId = eventId;
        this.memberId = memberId;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}