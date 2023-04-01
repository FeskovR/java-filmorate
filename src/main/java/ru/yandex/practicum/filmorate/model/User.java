package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;

    public User() {
    }

    public User(long id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    //    private final Set<Long> friends = new HashSet<>();
//
//    public Set<Long> getFriends() {
//        return friends;
//    }
//
//    public void addFriend(Long id) {
//        friends.add(id);
//    }
//
//    public void removeFriend(Long id) {
//        friends.remove(id);
//    }
}
