package ru.yandex.practicum.filmorate.storage.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.ArrayList;
import java.util.List;

@Component
public class LikeImpl implements LikeDao{
    JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("FilmDbStorage")
    FilmStorage filmStorage;

    public LikeImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLikeToFilm(Long filmId, Long userId) {
        String sqlQuery = "insert into likes (user_id, film_id) " +
                "values (?, ?)";
        jdbcTemplate.update(sqlQuery, userId, filmId);
    }

    @Override
    public void removeLikeFromFilm(Long filmId, Long userId) {
        String sqlQuery = "delete from likes where user_id = ? and film_id = ?";
        jdbcTemplate.update(sqlQuery, userId, filmId);
    }

    @Override
    public List<Film> getTopFilms(int count) {
        String sqlQuery = "SELECT f.film_id, count(l.user_id) AS likes " +
                "FROM FILMS AS f " +
                "LEFT JOIN likes AS l ON f.film_id=l.film_id " +
                "GROUP BY f.film_id " +
                "ORDER BY likes DESC  " +
                "LIMIT ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, count);
        List<Film> filmRating = new ArrayList<>();
        while (rowSet.next()) {
            Film film = filmStorage.getById(rowSet.getInt("film_id"));
            filmRating.add(film);
        }
        return filmRating;
    }
}
