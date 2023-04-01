package ru.yandex.practicum.filmorate.storage.like;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface LikeDao {
    void addLikeToFilm(Long filmId, Long userId);
    void removeLikeFromFilm(Long filmId, Long userId);
    List<Film> getTopFilms(int count);
}
