package database.executors;

import database.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Executor {

    private Executor() {
    }

    public static void executeBatch(List<String> queries) {
        try (Connection connection = ConnectionCreator.createConnection();
             Statement statement = connection.createStatement()) {
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
