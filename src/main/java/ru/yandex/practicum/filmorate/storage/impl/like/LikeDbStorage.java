package ru.yandex.practicum.filmorate.storage.impl.like;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.interfaces.LikeStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LikeDbStorage implements LikeStorage {
    private final JdbcTemplate jdbcTemplate;

    public LikeDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLikeToFilm(long filmId, long userId) {
        String sqlQuery = "insert into likes (user_id, film_id) " +
                "values (?, ?)";
        jdbcTemplate.update(sqlQuery, userId, filmId);
    }

    @Override
    public void removeLikeFromFilm(long filmId, long userId) {
        String sqlQuery = "delete from likes where user_id = ? and film_id = ?";
        jdbcTemplate.update(sqlQuery, userId, filmId);
    }

    @Override
    public List<Film> getTopFilms(int count) {
        String sqlQuery = "SELECT f.*, count(l.user_id) AS likes, mpa.mpa_name " +
                "FROM FILMS AS f " +
                "LEFT JOIN likes AS l ON f.film_id=l.film_id " +
                "LEFT JOIN mpa AS mpa ON f.mpa_id=mpa.mpa_id " +
                "GROUP BY f.film_id " +
                "ORDER BY likes DESC  " +
                "LIMIT ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, count);
        List<Film> filmRating = new ArrayList<>();
        while (rowSet.next()) {
            Film film = new Film(rowSet.getInt("film_id"),
                    rowSet.getString("name"),
                    rowSet.getString("description"),
                    dateFormatter(rowSet.getString("release_date")),
                    rowSet.getInt("duration"),
                    new Mpa(rowSet.getInt("mpa_id"), rowSet.getString("mpa_name"))
            );
            filmRating.add(film);
        }
        return filmRating;
    }

    private LocalDate dateFormatter(String date) {
        String[] parts = new String[3];
        parts = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
        return localDate;
    }

}
