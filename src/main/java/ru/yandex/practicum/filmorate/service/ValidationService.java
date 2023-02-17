package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class ValidationService {
    public void userValidate(User user) {
        if (user.getEmail() == null ||
                user.getEmail().isBlank() ||
                !user.getEmail().contains("@") ||
                user.getLogin().isBlank() ||
                user.getLogin().contains(" ") ||
                user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("User validation failed");
        }
    }

    public void filmValidate(Film film) {
        LocalDate limitDate = LocalDate.of(1895, 12, 28);
        if (film.getName() == null ||
                film.getName().isBlank() ||
                film.getDescription().length() > 200 ||
                film.getReleaseDate().isBefore(limitDate) ||
                film.getDuration() <= 0) {
            throw new ValidationException("Film validation failed");
        }
    }
}
