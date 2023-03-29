package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;
import java.util.List;

@Component("FilmDbStorage")
@Slf4j
public class FilmDbStorage implements FilmStorage{
    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film add(Film film) {
        String sqlQuery = "insert into films (name, description, release_date, duration, mpa_id) " +
                "values (?, ?, ?, ?, ?)";
        int mpaId = film.getMpa().getId();

        jdbcTemplate.update(sqlQuery,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                mpaId);

        String sqlQueryForLastId = "select film_id from films order by film_id desc limit 1";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQueryForLastId);
        if (rowSet.next()) {
            film.setId(rowSet.getLong("film_id"));
            return film;
        } else {
            return null;
        }
    }

    @Override
    public Film getById(long id) {
        String sqlQuery = "select * from films as f " +
                "join mpa as m on f.mpa_id=m.mpa_id " +
                "where film_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, id);
        Film film = null;
        if (rowSet.next()) {
            Mpa mpa = new Mpa(rowSet.getInt("mpa_id"), rowSet.getString("mpa_name"));
            film = new Film(rowSet.getInt("film_id"),
                    rowSet.getString("name"),
                    rowSet.getString("description"),
                    dateFormatter(rowSet.getString("release_date")),
                    rowSet.getInt("duration"),
                    mpa);

            String ratingSqlQuery = "select * from film_genre where film_id = ?";
            SqlRowSet genresRowSet = jdbcTemplate.queryForRowSet(ratingSqlQuery, id);
            while (genresRowSet.next()) {
                Genre genre = new Genre(genresRowSet.getInt("genre_id"),
                        genresRowSet.getString("genre_name"));
                film.addGenre(genre);
            }
        }
        return film;
    }

    @Override
    public List<Film> findAll() {
        return null;
    }

    @Override
    public Film update(Film film) {
        String sqlQuery = "update films set " +
                "name = ?, description = ?, release_date = ?, duration = ?, mpa_id = ? " +
                "where film_id = ?";

        int mpaId = film.getMpa().getId();
        log.info("" + mpaId);

        jdbcTemplate.update(sqlQuery,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                mpaId,
                film.getId());

        return film;
    }

    private LocalDate dateFormatter (String date) {
        String[] parts = new String[3];
        parts = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));
        return localDate;
    }
}
