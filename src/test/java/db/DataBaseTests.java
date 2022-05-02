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
    private static final UserDaoImplementation USER_DAO_IMPL = new UserDaoImplementation();

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
        boolean response = USER_DAO_IMPL.create(user);
        assertTrue(response, "The user hasn't been added.");
        User dbUser = USER_DAO_IMPL.findUserByUserName(user.getUserName());
        assertEquals(user.getUserName(), dbUser.getUserName());
        log.info("The user has been added: [{}]", user);
    }

    @Test
    void addUsers() throws DaoException {
        List<User> users = List.of(
                new User(10, "Alex97", "aLex", "alex@mail.com"),
                new User(11, "GK_2002", "qwerty", "gk@mail.com")
        );
        USER_DAO_IMPL.create(users);
        List<User> dbUsers = USER_DAO_IMPL.findAll();
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
    void updateFirstUser() throws DaoException {
        long firstUserId = 2;
        User userBeforeUpdate = USER_DAO_IMPL.findEntityById(firstUserId);
        log.info("The user before updating: [{}]", userBeforeUpdate);
        userBeforeUpdate.setUserName("Android_101");
        USER_DAO_IMPL.update(userBeforeUpdate);
        User userAfterUpdate = USER_DAO_IMPL.findEntityById(firstUserId);
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
