package database.dao.implementations.users;

import database.connection.ConnectionCreator;
import database.entities.User;
import database.exceptions.DaoException;
import database.queries.UserQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.enums.UsersTableFields.*;

public class UsersDaoImplementation implements UsersDao {

    @Override
    public boolean create(User user) throws DaoException {
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.INSERT_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, String.valueOf(user.getPassword().hashCode()));
            statement.setString(3, user.getEmail());
            return !statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(List<User> users) throws DaoException {
        for (User user : users) {
            this.create(user);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.SELECT_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .userName(resultSet.getString(USER_NAME.getFieldName()))
                        .password(resultSet.getString(PASSWORD.getFieldName()))
                        .email(resultSet.getString(EMAIL.getFieldName()))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return users;
    }

    @Override
    public User findEntityById(Long id) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.SELECT_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .userName(resultSet.getString(USER_NAME.getFieldName()))
                        .password(resultSet.getString(PASSWORD.getFieldName()))
                        .email(resultSet.getString(EMAIL.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public User findUserByUserName(String patternName) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.SELECT_USER_BY_USER_NAME)) {
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .userName(resultSet.getString(USER_NAME.getFieldName()))
                        .password(resultSet.getString(PASSWORD.getFieldName()))
                        .email(resultSet.getString(EMAIL.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return this.delete(user.getId());
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.DELETE_USER_BY_ID)) {
            statement.setString(1, id.toString());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public User update(User user) throws DaoException {
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(UserQueries.UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }
}
