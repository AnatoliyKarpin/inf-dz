package com.example.demo;


import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/posts")
 class PostController {
    private final Map<Long, Post> posts = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        Long id = idCounter.getAndIncrement();
        post.setId(id);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        posts.put(id, post);
        return post;
    }
    @GetMapping
    public List<Post> getAllPosts() {
        List<Post> allPosts = new ArrayList<>(posts.values());

        allPosts.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
        return allPosts;
    }
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        return post;
    }
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = posts.get(id);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        if (postDetails.getTitle() != null) {
            post.setTitle(postDetails.getTitle());
        }
        post.setPrice(postDetails.getPrice());
        post.setAuthor(postDetails.getAuthor());
        post.setMessage(postDetails.getMessage());
        post.setUpdatedAt(LocalDateTime.now());

        return post;
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        if (!posts.containsKey(id)) {
            throw new RuntimeException("Post not found");
        }
        posts.remove(id);
    }
    @DeleteMapping
    public void deleteAllPosts() {
        posts.clear();
    }
    @GetMapping("/count")
    public Map<String, Long> getPostCount() {
        Map<String, Long> response = new HashMap<>();
        response.put("count", (long) posts.size());
        return response;
    }
    @GetMapping("/author/{author}")
    public List<Post> getPostsByAuthor(@PathVariable String author) {
        List<Post> authorPosts = new ArrayList<>();
        for (Post post : posts.values()) {
            if (author.equals(post.getAuthor())) {
                authorPosts.add(post);
            }
        }
        authorPosts.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
        return authorPosts;
    }
}
