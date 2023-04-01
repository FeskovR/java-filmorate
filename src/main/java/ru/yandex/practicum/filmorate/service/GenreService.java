package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreDao;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreDao genreImpl;

    public Genre getGenreById(int id) {
        Genre genre = genreImpl.getGenreById(id);
        if (genre == null) {
            throw new RuntimeException("Жанр не найден");
        }
        return genre;
    }

    public List<Genre> getAllGenres() {
        return genreImpl.getAllGenres();
    }
}
