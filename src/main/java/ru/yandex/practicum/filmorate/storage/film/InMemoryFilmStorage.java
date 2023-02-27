package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    Map<Integer, Film> films = new LinkedHashMap<>();

    public void add(Film film) {
        films.put(film.getId(), film);
    }

    public Film getById(int id) {
        return films.get(id);
    }

    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }
}
