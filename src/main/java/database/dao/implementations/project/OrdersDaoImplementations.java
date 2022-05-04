package database.dao.implementations.project;

import database.dao.BaseDao;
import database.entities.Entity;
import database.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public class OrdersDaoImplementations implements BaseDao {

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
        return false;
    }
}
