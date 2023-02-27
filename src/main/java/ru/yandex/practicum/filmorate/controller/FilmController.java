package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    @Autowired
    InMemoryFilmStorage inMemoryFilmStorage;
    int id = 1;

    @GetMapping
    public List<Film> getAllFilms() {
        log.info("Getting film");
        return inMemoryFilmStorage.getAll();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        ValidationService.validate(film);
        film.setId(id++);
        inMemoryFilmStorage.add(film);
        log.info("Film added");
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        if (inMemoryFilmStorage.getById(film.getId()) == null) {
            log.info("Film to update not found");
            throw new ValidationException();
        }
        ValidationService.validate(film);
        inMemoryFilmStorage.add(film);
        log.info("Film updated");
        return film;
    }
}
