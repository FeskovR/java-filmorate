package ru.yandex.practicum.filmorate.storage.friend;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendDao {
    void addToFriends(long user_id, long friend_id);

    void removeFromFriends(long user_id, long friend_id);

    List<User> findAllFriends(long user_id);
}