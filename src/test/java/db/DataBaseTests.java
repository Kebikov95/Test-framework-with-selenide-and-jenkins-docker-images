package db;

import database.Executor;
import database.dao.implementations.UserDaoImplementation;
import database.entities.User;
import database.exceptions.DaoException;
import database.queries.Queries;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseTests {

    private static final Logger log = getLogger(DataBaseTests.class);

    private UserDaoImplementation userDaoImpl = new UserDaoImplementation();

    @BeforeAll
    static void createDb() {
        List<String> queries = new ArrayList<>();
        queries.add(Queries.CREATE_USERS_TABLE);
        queries.add(Queries.INSERT_USERS);
        Executor.executeBatch(queries);
    }

    @AfterAll
    static void deleteDb() {
        List<String> queries = new ArrayList<>();
        queries.add(Queries.DELETE_USERS_TABLE);
        Executor.executeBatch(queries);
    }

    // CREATE
    @Test
    void addUser() throws DaoException {
        User user = User.builder()
                .id(22)
                .userName("Mercy2321")
                .password("23212321")
                .email("merc23@yahoo.com")
                .build();
        boolean response = userDaoImpl.create(user);
        log.info("The user has been added, result: {}", response);
        assertTrue(response, "The user hasn't been added.");
    }

    @Test
    void addUsers() throws DaoException {
        List<User> addingUsers = List.of(
                new User(10, "Alex97", "aLex", "alex@mail.com"),
                new User(11, "GK_2002", "qwerty", "gk@mail.com")
        );
        userDaoImpl.create(addingUsers);
        List<User> readingUsers = userDaoImpl.findAll();
        boolean isContains = readingUsers.containsAll(addingUsers);
        assertTrue(isContains);
    }

    //READ
    @Test
    void getSizeOfUsers() throws DaoException {
        List<User> users = userDaoImpl.findAll();
        log.info("The quantity of users is '{}'", users.size());
        int expectedQuantity = 5;
        int actualQuantity = users.size();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    void getUserById() throws DaoException {
        long firstUserId = 1;
        User user = userDaoImpl.findEntityById(firstUserId);
        log.info("The first user: {}", user);
        assertNotNull(user);
    }

    @Test
    void getUserByUserName() throws DaoException {
        String userName = "FrankUK";
        User user = userDaoImpl.findUserByUserName(userName);
        log.info("The first user: {}", user);
        assertNotNull(user);
    }

    // UPDATE
    @Test
    void updateFirstUser() throws DaoException {
        long firstUserId = 2;
        User userBeforeUpdate = userDaoImpl.findEntityById(firstUserId);
        userBeforeUpdate.setUserName("Android_101");
        userDaoImpl.update(userBeforeUpdate);
        User userAfterUpdate = userDaoImpl.findEntityById(firstUserId);
        assertEquals(userBeforeUpdate, userAfterUpdate);
    }

    // DELETE
    @Test
    void deleteUserById() throws DaoException {
        long firstUserId = 1;
        User user = userDaoImpl.findEntityById(firstUserId);
        log.info("The first user: {}", user);
        assertNotNull(user);

        boolean response = userDaoImpl.delete(user.getId());
        log.info("The user has been deleted, result: {}", response);
        assertTrue(response, "The user hasn't been deleted.");

        user = userDaoImpl.findEntityById(firstUserId);
        log.info("The first user: {}", user);
        assertNull(user);
    }
}
