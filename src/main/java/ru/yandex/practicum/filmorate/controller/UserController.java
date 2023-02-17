package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.UserData;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    UserData userData = new UserData();
    ValidationService validationService = new ValidationService();
    int id = 1;

    @GetMapping
    public List<User> getAllUsers() {
        return userData.getAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        validationService.userValidate(user);
        user.setId(id++);
        if (user.getName() == null)
            user.setName(user.getLogin());
        userData.add(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        if (userData.getById(user.getId()) == null)
            throw new ValidationException();
        validationService.userValidate(user);
        userData.add(user);
        return user;
    }
}
