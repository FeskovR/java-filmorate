package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
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
        if (validateUser(user)) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new ValidationException();
        }
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        if (validateUser(user)) {
            users.put(user.getId(), user);
            return user;
        } else {
            throw new ValidationException();
        }
    }

    private boolean validateUser(User user) {
        if (user.getEmail() != null || !user.getEmail().isBlank() &&
            user.getEmail().contains("@") &&
            !user.getLogin().isBlank() &&
            !user.getLogin().contains(" ") &&
            user.getBirthday().isBefore(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
    }
}
