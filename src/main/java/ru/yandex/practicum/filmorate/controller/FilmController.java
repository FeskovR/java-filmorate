package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.FilmData;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {
    FilmData filmData = new FilmData();
    ValidationService validationService = new ValidationService();
    int id = 1;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmData.getAll();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        validationService.filmValidate(film);
        film.setId(id++);
        filmData.add(film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        if (filmData.getById(film.getId()) == null)
            throw new ValidationException();
        validationService.filmValidate(film);
        filmData.add(film);
        return film;
    }
}
