package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    InMemoryUserStorage inMemoryUserStorage;
    int id = 1;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Getting user");
        return inMemoryUserStorage.getAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        ValidationService.validate(user);
        user.setId(id++);
        if (user.getName() == null) {
            log.info("User name set by login");
            user.setName(user.getLogin());
        }
        inMemoryUserStorage.add(user);
        log.info("User added");
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        if (inMemoryUserStorage.getById(user.getId()) == null) {
            log.info("User to update not found");
            throw new ValidationException();
        }
        ValidationService.validate(user);
        inMemoryUserStorage.add(user);
        log.info("User updated");
        return user;
    }
}
