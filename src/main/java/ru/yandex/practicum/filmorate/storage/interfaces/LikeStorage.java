package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface LikeStorage {
    void addLikeToFilm(long filmId, long userId);

    void removeLikeFromFilm(long filmId, long userId);

    List<Film> getTopFilms(int count);
}
