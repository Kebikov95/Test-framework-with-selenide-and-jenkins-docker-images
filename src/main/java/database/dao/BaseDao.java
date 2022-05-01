package database.dao;

import database.entities.Entity;
import database.exceptions.DaoException;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public interface BaseDao <K, T extends Entity> {

    Logger log = getLogger(BaseDao.class);

    boolean create(T t) throws DaoException;
    void create(List<T> t) throws DaoException;
    List<T> findAll() throws DaoException;
    T findEntityById(K id) throws DaoException;
    T update(T t) throws DaoException;
    boolean delete(T t) throws DaoException, SQLException;
    boolean delete(K id) throws DaoException, SQLException;

    default void close(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }
}
