package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    FilmStorage inMemoryFilmStorage;
    @Autowired
    UserStorage inMemoryUserStorage;
    long id = 1;

    public List<Film> findAll() {
        return inMemoryFilmStorage.findAll();
    }

    public Film findById(long id) {
        Film film = inMemoryFilmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        return film;
    }

    public Film add(Film film) {
        film.setId(id++);
        inMemoryFilmStorage.add(film);
        return film;
    }

    public Film update(Film film) {
        if (inMemoryFilmStorage.getById(film.getId()) == null) {
            throw new RuntimeException("Фильм для обновления не найден");
        }
        inMemoryFilmStorage.add(film);
        return film;
    }

    public void likeFilm(long id, long userId) {
        if (inMemoryUserStorage.getById(userId) == null) {
            throw new RuntimeException("User is not found");
        }
        Film film = inMemoryFilmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        film.addToUsersWhoLikes(userId);
    }

    public void removeLike(long id, long userId) {
        if (inMemoryUserStorage.getById(userId) == null) {
            throw new RuntimeException("User is not found");
        }
        Film film = inMemoryFilmStorage.getById(id);
        if (film == null) {
            throw new RuntimeException("Film is not found");
        }
        film.removeFromUsersWhoLikes(userId);
    }

    public List<Film> getTopFilms(int count) {
        List<Film> topFilms = new ArrayList<>();
        List<Film> allFilms = inMemoryFilmStorage.findAll();

        allFilms.sort((Film film1, Film film2) -> film2.getUsersWhoLikes().size() - film1.getUsersWhoLikes().size());

        if (allFilms.size() > count) {
            for (int i = 0; i < count; i++) {
                topFilms.add(allFilms.get(i));
            }
        } else {
            topFilms.addAll(allFilms);
        }
        return topFilms;
    }
}
