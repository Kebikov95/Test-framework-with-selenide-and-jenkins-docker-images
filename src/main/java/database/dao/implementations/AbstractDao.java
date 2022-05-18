package database.dao.implementations;

import database.entities.Entity;
import database.exceptions.DaoException;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public abstract class AbstractDao <T extends Entity> {

    protected Logger log = getLogger(AbstractDao.class);

    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(long id) throws DaoException;
    public abstract boolean delete(long id) throws DaoException, SQLException;
    public abstract boolean delete(T entity) throws DaoException, SQLException;
    public abstract boolean create(T entity) throws DaoException;
    public abstract void create(List<T> entity) throws DaoException;
    public abstract T update(T entities) throws DaoException;

    public void close(Statement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    public void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }
}
