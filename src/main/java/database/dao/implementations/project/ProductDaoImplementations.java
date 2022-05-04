package database.dao.implementations.project;

import database.connection.ProjectDbConnectionCreator;
import database.dao.BaseDao;
import database.entities.Entity;
import database.exceptions.DaoException;
import database.queries.project.CustomersQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImplementations implements BaseDao {

    @Override
    public boolean create(Entity entity) throws DaoException {
        return false;
    }

    @Override
    public void create(List t) throws DaoException {

    }

    @Override
    public List findAll() throws DaoException {
        return null;
    }

    @Override
    public Entity findEntityById(Object id) throws DaoException {
        return null;
    }

    @Override
    public Entity update(Entity entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Entity entity) throws DaoException, SQLException {
        return false;
    }

    @Override
    public boolean delete(Object id) throws DaoException, SQLException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.DELETE_CUSTOMER_BY_ID)) {
            statement.setString(1, id.toString());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }
}
