package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        log.info("Getting all users");
        return userService.findAll();
    }

    @PostMapping("/users")
    public User add(@RequestBody User user) {
        log.info("Adding new user");
        return userService.add(user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        log.info("User id: " + user.getId() + " updating");
        return userService.update(user);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        log.info("Finding user by ID: " + id);
        return userService.findById(id);
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addToFriends(@PathVariable long id, @PathVariable long friendId) {
        log.info("Add user id: " + friendId + " to friends user id: " + id);
        userService.addToFriends(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeUserFromFriends(@PathVariable long id, @PathVariable long friendId) {
        log.info("Remove user id: " + friendId + " from friends user id: " + id);
        userService.deleteFromFriends(id, friendId);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getUserFriends(@PathVariable long id) {
        log.info("Getting all friends by user id: " + id);
        return userService.findAllFriends(id);
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable long id, @PathVariable long otherId) {
        log.info("Getting all common friends by users id " + id + " and " + otherId);
        return userService.commonFriends(id, otherId);
    }
}
