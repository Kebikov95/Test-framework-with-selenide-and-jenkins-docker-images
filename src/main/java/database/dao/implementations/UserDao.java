package database.dao.implementations;

import database.dao.BaseDao;
import database.entities.User;
import database.exceptions.DaoException;

public interface UserDao extends BaseDao<Long, User> {

    User findUserByUserName(String patternName) throws DaoException;
}
