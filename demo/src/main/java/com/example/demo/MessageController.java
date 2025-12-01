package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    //curl --location 'localhost:8080/messages'
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    //curl --location 'localhost:8080/messages' \
    //--header 'Content-Type: text/plain' \
    //--data 'nono'
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    //curl --location --request GET 'localhost:8080/messages/0' \
    //--header 'Content-Type: text/plain' \
    //--data 'nono'
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    //curl --location --request DELETE 'localhost:8080/messages/0' \
    //--header 'Content-Type: text/plain' \
    //--data 'nono'
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    //curl --location --request PUT 'localhost:8080/messages/0' \
    //--header 'Content-Type: text/plain' \
    //--data 'lolo'
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8080/messages/search/aaa'
    @GetMapping("/messages/search/{text}")
    public ResponseEntity<Integer> jjj(@PathVariable String text) {
        int foundedIndex = 0;
        for (String s : messages) {
            if (s.equals(text)) {
                return ResponseEntity.ok(foundedIndex);
            }
            foundedIndex++;
        }
        return ResponseEntity.ok(-1);
    }

    //curl --location 'localhost:8080/messages/count'
    @GetMapping("/messages/count")
    public ResponseEntity<Integer> ggg() {
        return ResponseEntity.ok(messages.size());
    }

    //curl --location 'localhost:8080/messages/1/create' \
    //--header 'Content-Type: application/json' \
    //--data 'aaa'
    @PostMapping("/messages/{index}/create")
    public ResponseEntity<Void> ccc(@PathVariable int index, @RequestBody String text) {
        messages.add(index, text);
        return ResponseEntity.noContent().build();
    }

    //curl --location --request DELETE 'localhost:8080/messages/search/aaa'
    @DeleteMapping("/messages/search/{text}")
    public ResponseEntity<Void> ddd(@PathVariable String text) {
        int i = 0;
        for (String s : messages) {
            if (s.contains(text)) {
                messages.remove(i);
            }
        }
        return ResponseEntity.noContent().build();
    }

}
