package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

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
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping("/film")
    public Film updateFilm(@RequestBody Film film) {
        films.put(film.getId(), film);
        return film;
    }
}
