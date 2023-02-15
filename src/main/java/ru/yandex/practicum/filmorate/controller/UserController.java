package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    Map<Integer, User> users = new HashMap<>();

    @GetMapping("/users")
    public Map<Integer, User> getAllUsers() {
        return users;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return user;
    }
}
