package com.example.demo;
import java.util.*;

public class UserService {
    private Map<Integer, User> users = new HashMap<>();
    private Map<String, Integer> usernameToId = new HashMap<>();
    private int nextId = 1;

    public int createUser(String username, String password, String repeatPassword, int age) {
        if (!password.equals(repeatPassword)) {
            return 400;
        }

        if (usernameToId.containsKey(username)) {
            return 409;
        }

        User user = new User(nextId, username, password, age);
        users.put(nextId, user);
        usernameToId.put(username, nextId);
        nextId++;
        return 201;
    }

    public Object getUserById(int id) {
        if (!users.containsKey(id)) {
            return 404;
        }
        return users.get(id).withoutPassword();
    }

    public int deleteUserById(int id) {
        if (!users.containsKey(id)) {
            return 404;
        }
        User user = users.get(id);
        usernameToId.remove(user.getUsername());
        users.remove(id);
        return 200;
    }

    public int updateUsername(int id, String newUsername, String repeatPassword) {
        if (!users.containsKey(id)) {
            return 404;
        }

        User user = users.get(id);
        if (!user.getPassword().equals(repeatPassword)) {
            return 400;
        }

        if (usernameToId.containsKey(newUsername) && usernameToId.get(newUsername) != id) {
            return 409;
        }

        usernameToId.remove(user.getUsername());
        user.setUsername(newUsername);
        usernameToId.put(newUsername, id);
        return 200;
    }

    public List<User> getUsersByAge(int targetAge) {
        List<User> result = new ArrayList<>();
        int minAge = targetAge - 5;
        int maxAge = targetAge + 5;

        for (User user : users.values()) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                result.add(user.withoutPassword());
            }
        }
        return result;
    }

    public int createUserWithoutRepeat(String username, String password, int age) {
        if (usernameToId.containsKey(username)) {
            return 409;
        }

        User user = new User(nextId, username, password, age);
        users.put(nextId, user);
        usernameToId.put(username, nextId);
        nextId++;
        return 201;
    }

    public int updateUsernameWithoutPassword(int id, String newUsername) {
        if (!users.containsKey(id)) {
            return 404;
        }

        if (usernameToId.containsKey(newUsername) && usernameToId.get(newUsername) != id) {
            return 409;
        }

        User user = users.get(id);
        usernameToId.remove(user.getUsername());
        user.setUsername(newUsername);
        usernameToId.put(newUsername, id);
        return 200;
    }
}
