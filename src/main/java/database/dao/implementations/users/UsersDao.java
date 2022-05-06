package database.dao.implementations.users;

import database.dao.implementations.AbstractDao;
import database.entities.users.User;
import database.exceptions.DaoException;

public abstract class UsersDao extends AbstractDao<User> {

    public abstract User findUserByUserName(String patternName) throws DaoException;
}
