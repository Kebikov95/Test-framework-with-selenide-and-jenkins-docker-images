package db.users;

import database.connection.UsersDbConnectionCreator;
import database.executors.Executor;
import database.dao.implementations.users.UsersDaoImplementation;
import database.entities.users.User;
import database.exceptions.DaoException;
import database.queries.users.UsersQueries;
import db.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserDbTests extends BaseTest {

    private static final UsersDaoImplementation USER_DAO_IMPL = new UsersDaoImplementation();

    @BeforeAll
    static void createDb() throws SQLException {
        List<String> queries = new ArrayList<>();
//        queries.add(UsersQueries.CREATE_USERS_DB);
        queries.add(UsersQueries.USE_USERS_DB);
        queries.add(UsersQueries.CREATE_USERS_TABLE);
        queries.add(UsersQueries.INSERT_USERS_DATA);
        Executor.executeBatch(UsersDbConnectionCreator.createConnection(), queries);
    }

    @AfterAll
    static void deleteDb() throws SQLException {
        List<String> queries = new ArrayList<>();
        queries.add(UsersQueries.DELETE_USERS_TABLE);
        Executor.executeBatch(UsersDbConnectionCreator.createConnection(), queries);
    }

    // CREATE
    @Test
    void addUser() throws DaoException {
        User user = User.builder()
                .userName("Mercy2321")
                .password("merc2321")
                .email("merc23@yahoo.com")
                .build();
        boolean response = USER_DAO_IMPL.create(user);
        assertTrue(response, "The user hasn't been added.");
        User dbUser = USER_DAO_IMPL.findUserByUserName(user.getUserName());
        assertEquals(user.getUserName(), dbUser.getUserName());
        log.info("The user has been added: [{}]", user);
    }

    @Test
    void addUsers() throws DaoException {
        List<User> users = List.of(
                new User("Alex97", "aLex", "alex@mail.com"),
                new User("GK_2002", "qwerty", "gk@mail.com")
        );
        USER_DAO_IMPL.create(users);
        List<User> dbUsers = USER_DAO_IMPL.findAll();

        users = users.stream()
                .peek(u -> u.setPassword(String.valueOf(u.getPassword().hashCode())))
                .collect(Collectors.toList());
        boolean isContains = dbUsers.containsAll(users);
        assertTrue(isContains);
        log.info("The users has been added: [{}]", users);
    }

    //READ
    @Test
    void getSizeOfUsers() throws DaoException {
        List<User> users = USER_DAO_IMPL.findAll();
        int expectedQuantity = 5;
        int actualQuantity = users.size();
        assertEquals(expectedQuantity, actualQuantity);
        log.info("The quantity of users is '{}'", users.size());
    }

    @Test
    void getUserById() throws DaoException {
        long firstUserId = 1;
        User user = USER_DAO_IMPL.findEntityById(firstUserId);
        assertNotNull(user);
        log.info("The user by id '{}': [{}]", firstUserId, user);
    }

    @Test
    void getUserByUserName() throws DaoException {
        String userName = "FrankUK";
        User user = USER_DAO_IMPL.findUserByUserName(userName);
        assertNotNull(user);
        log.info("Get user by name '{}': [{}]", userName, user);
    }

    // UPDATE
    @Test
    void updateSecondUser() throws DaoException {
        long secondUserId = 2;
        User userBeforeUpdate = USER_DAO_IMPL.findEntityById(secondUserId);
        log.info("The user before updating: [{}]", userBeforeUpdate);
        userBeforeUpdate.setUserName("Android_101");
        USER_DAO_IMPL.update(userBeforeUpdate);
        User userAfterUpdate = USER_DAO_IMPL.findEntityById(secondUserId);
        assertEquals(userBeforeUpdate, userAfterUpdate);
        log.info("The user after updating: [{}]", userAfterUpdate);
    }

    // DELETE
    @Test
    void deleteUserById() throws DaoException {
        long firstUserId = 1;
        User user = USER_DAO_IMPL.findEntityById(firstUserId);
        assertNotNull(user);

        boolean response = USER_DAO_IMPL.delete(user.getId());
        assertTrue(response, "The user hasn't been deleted.");

        user = USER_DAO_IMPL.findEntityById(firstUserId);
        assertNull(user);
        log.info("The user has been deleted by id '{}', user = [{}]", firstUserId, user);
    }
}
