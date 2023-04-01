package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeDao;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    @Qualifier("FilmDbStorage")
    FilmStorage filmStorage;
    @Autowired
    @Qualifier("UserDbStorage")
    UserStorage userStorage;
    @Autowired
    LikeDao likeImpl;

    public List<Film> findAll() {
        return filmStorage.findAll();
    }

    public Film findById(long id) {
        Film film = filmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        return film;
    }

    public Film add(Film film) {
        ValidationService.validate(film);
        return filmStorage.add(film);
    }

    public Film update(Film film) {
        ValidationService.validate(film);
        if (filmStorage.getById(film.getId()) == null) {
            throw new RuntimeException("Фильм для обновления не найден");
        }
        return filmStorage.update(film);
    }

    public void likeFilm(long id, long userId) {
        if (userStorage.findById(userId) == null) {
            throw new RuntimeException("User is not found");
        }
        Film film = filmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        likeImpl.addLikeToFilm(id, userId);
    }

    public void removeLike(long id, long userId) {
        if (userStorage.findById(userId) == null) {
            throw new RuntimeException("User is not found");
        }
        Film film = filmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        likeImpl.removeLikeFromFilm(id, userId);
    }

    public List<Film> getTopFilms(int count) {
        return likeImpl.getTopFilms(count);
    }
}
