package ru.yandex.practicum.filmorate.data;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserData {
    Map<Integer, User> users = new LinkedHashMap<>();

    public void add(User user) {
        users.put(user.getId(), user);
    }

    public User getById(int id) {
        return users.get(id);
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
