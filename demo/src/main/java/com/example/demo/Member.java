package com.example.demo;

public class Member {
    private Long id;
    private String name;
    private String grade;
    private String email;

    public Member() {}

    public Member(Long id, String name, String grade, String email) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}