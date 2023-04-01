package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Film {
    long id;
    String name;
    String description;
    LocalDate releaseDate;
    int duration;
    Mpa mpa;
    List<Genre> genres = new ArrayList<>();

    public Film() {
    }

    public Film(long id, String name, String description, LocalDate releaseDate, int duration, Mpa mpa) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa = mpa;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

//    Set<Long> usersWhoLikes = new HashSet<>();
//
//    public Set<Long> getUsersWhoLikes() {
//        return usersWhoLikes;
//    }
//
//    public void addToUsersWhoLikes(long id) {
//        usersWhoLikes.add(id);
//    }
//
//    public void removeFromUsersWhoLikes(long id) {
//        usersWhoLikes.remove(id);
//    }
}
