package database;

import database.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    private Executor() {
    }

    public static void executeBatch(List<String> queries) {
        try (Connection connection = ConnectionCreator.createConnection();
             Statement statement = connection.createStatement()) {
            for (String query : queries)
                statement.addBatch(query);

            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> executeUsers(String query) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                users.add(new User(id, userName, password, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
