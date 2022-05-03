package db;

import database.exceptions.DaoException;
import database.executors.Executor;
import database.logic.LogicDb;
import database.queries.Queries;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseLogicTests {

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

    // LOGIN LOGIC
    @ParameterizedTest
    @MethodSource("userListProviderForLogin")
    void checkLogin(String userName, String password, String expectedMessage) throws DaoException {
        LogicDb logicDB = new LogicDb();
        String actualMessage = logicDB.login(userName, password);
        assertEquals(expectedMessage, actualMessage);
    }

    static Stream<Arguments> userListProviderForLogin() {
        return Stream.of(
                Arguments.of("Simon2312", "qwerty", "User not found"),
                Arguments.of("Albert_32", "qwerty", "LogicDb successful"),
                Arguments.of("Albert_32", "123456", "LogicDb failed")
        );
    }

    @ParameterizedTest
    @MethodSource("userListProviderForDelete")
    void checkDelete(String userName, String password, boolean expectedFlag) throws DaoException {
        LogicDb logicDB = new LogicDb();
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
