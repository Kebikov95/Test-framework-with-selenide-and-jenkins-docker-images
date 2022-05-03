package database.dao.implementations.users;

import database.dao.BaseDao;
import database.entities.User;
import database.exceptions.DaoException;

public interface UsersDao extends BaseDao<Long, User> {

    User findUserByUserName(String patternName) throws DaoException;
}
