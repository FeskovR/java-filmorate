package ru.yandex.practicum.filmorate.data;

import ru.yandex.practicum.filmorate.model.User;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserData {
    Map<Integer, User> users = new LinkedHashMap<>();

    public void add(User user) {
        users.put(user.getId(), user);
    }

    public User getById(int id) {
        return users.get(id);
    }

    public List<User> getAll() {
        List<User> usersList = new ArrayList<>(users.values());
        return usersList;
    }
}
