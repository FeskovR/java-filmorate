package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    @GetMapping("/film")
    public Map<Integer, Film> getAllFilms() {
        return films;
    }

    @PostMapping("/film")
    public Film addFilm(@RequestBody Film film) {
        if (validateFilm(film)) {
            films.put(film.getId(), film);
            return film;
        } else {
            throw new ValidationException();
        }
    }

    @PutMapping("/film")
    public Film updateFilm(@RequestBody Film film) {
        if (validateFilm(film)) {
            films.put(film.getId(), film);
            return film;
        } else {
            throw new ValidationException();
        }
    }

    private boolean validateFilm(Film film) {
        LocalDate limitDate = LocalDate.of(1895, 12, 28);
        if (film.getName() != null || !film.getName().isBlank() &&
                film.getDescription().length() <= 200 &&
                film.getReleaseDate().isAfter(limitDate) &&
                film.getDuration() > 0 ) {
            films.put(film.getId(), film);
            return true;
        } else {
            return false;
        }
    }
}
