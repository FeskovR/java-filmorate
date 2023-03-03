package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.ValidationService;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping("/films")
    public List<Film> findAll() {
        log.info("Getting film");
        return filmService.findAll();
    }

    @GetMapping("/films/{id}")
    public Film findById(@PathVariable long id){
        log.info("Getting film by id");
        return filmService.findById(id);
    }

    @PostMapping("/films")
    public Film add(@RequestBody Film film) {
        ValidationService.validate(film);
        log.info("Adding film");
        return filmService.add(film);
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        log.info("Updating film");
        return filmService.update(film);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void likeFilm(@PathVariable long id, @PathVariable long userId) {
        log.info("User id: " + userId + " like film id: " + id);
        filmService.likeFilm(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable long id, @PathVariable long userId) {
        log.info("User id: " + userId + " dislike film id: " + id);
        filmService.removeLike(id, userId);
    }

    @GetMapping("/films/popular")
    public List<Film> getTopFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            count = 10;
        }
        log.info("Getting top " + count + " films");
        return filmService.getTopFilms(count);
    }
}
