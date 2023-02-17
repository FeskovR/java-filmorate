package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.data.FilmData;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    FilmData filmData = new FilmData();
    ValidationService validationService = new ValidationService();
    int id = 1;

    @GetMapping
    public List<Film> getAllFilms() {
        log.info("Getting film");
        return filmData.getAll();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        validationService.filmValidate(film);
        film.setId(id++);
        filmData.add(film);
        log.info("Film added");
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        if (filmData.getById(film.getId()) == null) {
            log.info("Film to update not found");
            throw new ValidationException();
        }
        validationService.filmValidate(film);
        filmData.add(film);
        log.info("Film updated");
        return film;
    }
}
