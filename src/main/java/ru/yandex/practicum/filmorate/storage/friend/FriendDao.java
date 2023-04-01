package ru.yandex.practicum.filmorate.storage.friend;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendDao {
    void addToFriends(long userId, long friendId);

    void removeFromFriends(long userId, long friendId);

    List<User> findAllFriends(long userId);
}