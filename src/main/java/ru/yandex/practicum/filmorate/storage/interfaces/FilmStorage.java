package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(Film film);

    Film getById(long id);

    List<Film> findAll();

    Film update(Film film);

    void deleteFilm(long id);
}
