package ru.yandex.practicum.filmorate.storage.friend;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FriendImpl implements FriendDao {
    private final JdbcTemplate jdbcTemplate;

    public FriendImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addToFriends(long user_id, long friend_id) {
        String sqlQuery = "insert into friendships (user_id, friend_id, status) values (?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                user_id,
                friend_id,
                "unconfirmed");
    }

    @Override
    public void removeFromFriends(long user_id, long friend_id) {
        String sqlQuery = "delete from friendships where user_id = ? and friend_id = ?";
        jdbcTemplate.update(sqlQuery,
                user_id,
                friend_id);
    }

    @Override
    public List<User> findAllFriends(long user_id) {
        List<User> friends = new ArrayList<>();
        String sqlQuery = "select us.* " +
                "from friendships as fr " +
                "join users as us on fr.friend_id=us.user_id " +
                "where fr.user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, user_id);
        while (rowSet.next()) {
            LocalDate birthday = dateFormatter(rowSet.getString("birthday"));
            User friend = new User(rowSet.getLong("user_id"),
                    rowSet.getString("email"),
                    rowSet.getString("login"),
                    rowSet.getString("name"),
                    birthday);
            friends.add(friend);
        }
        return friends;
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
