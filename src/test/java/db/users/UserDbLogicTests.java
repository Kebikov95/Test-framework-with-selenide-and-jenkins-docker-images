package db.users;

import database.connection.UsersDbConnectionCreator;
import database.exceptions.DaoException;
import database.executors.Executor;
import database.logic.UsersDbLogic;
import database.queries.users.UsersQueries;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDbLogicTests extends BaseTest {

    @BeforeAll
    static void createDb() throws SQLException {
        List<String> queries = new ArrayList<>();
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

    // LOGIN LOGIC
    @ParameterizedTest
    @MethodSource("userListProviderForLogin")
    void checkLogin(String userName, String password, String expectedMessage) throws DaoException {
        UsersDbLogic logicDB = new UsersDbLogic();
        String actualMessage = logicDB.login(userName, password);
        assertEquals(expectedMessage, actualMessage);
    }

    static Stream<Arguments> userListProviderForLogin() {
        return Stream.of(
                Arguments.of("Simon2312", "qwerty", "User not found"),
                Arguments.of("Albert_32", "qwerty", "UsersDbLogic successful"),
                Arguments.of("Albert_32", "123456", "UsersDbLogic failed")
        );
    }

    @ParameterizedTest
    @MethodSource("userListProviderForDelete")
    void checkDelete(String userName, String password, boolean expectedFlag) throws DaoException {
        UsersDbLogic logicDB = new UsersDbLogic();
        boolean isDeleted = logicDB.delete(userName, password);
        assertEquals(expectedFlag, isDeleted);
    }

    static Stream<Arguments> userListProviderForDelete() {
        return Stream.of(
                Arguments.of("Simon2312", "23122132", false),
                Arguments.of("Anastasia2312", "qwerty", false),
                Arguments.of("Anastasia2312", "23122132", true)
        );
    }
}
