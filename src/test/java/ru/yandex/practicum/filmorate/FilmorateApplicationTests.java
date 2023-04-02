package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.impl.user.UserDbStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmorateApplicationTests {
	private final UserDbStorage userStorage;
	User user = new User(0,
			"mail@mail.ru",
			"Login",
			"Username",
			LocalDate.of(1990, 10, 15)
	);
	User updatedUser = new User(1,
			"newmail@mail.ru",
			"UpdatedLogin",
			"newName",
			LocalDate.of(1995, 12, 27)
	);

	@Test
	public void addUserToDb() {
		User returnedUser = userStorage.add(user);
		user.setId(1);
		assertEquals(user, returnedUser);
		userStorage.deleteUser(1);
	}

	@Test
	public void updateUser() {
		userStorage.add(user);
		User returnedUser = userStorage.update(updatedUser);
		assertEquals(updatedUser, returnedUser);
		userStorage.deleteUser(1);
	}

	@Test
	public void findByIdInDb() {
		userStorage.add(user);
		User returnedUser = userStorage.findById(1);
		assertEquals(user, returnedUser);
		userStorage.deleteUser(1);
	}

	@Test
	public void findByIdInDbWithWrongId() {
		User returnedUser = userStorage.findById(99);
		assertNull(returnedUser);
	}

	@Test
	public void findAll(){
		userStorage.add(user);
		userStorage.add(updatedUser);
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(updatedUser);
		List<User> returnedList = userStorage.findAll();
		assertArrayEquals(userList.toArray(), returnedList.toArray());
	}

	@Test
	void contextLoads() {
	}

}
