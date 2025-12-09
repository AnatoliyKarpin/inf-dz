package com.example.demo;
import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private int price;
    private String author;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Конструктор для создания
    public Post(String title, int price, String author, String message) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Конструктор пустой
    public Post() {}

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
