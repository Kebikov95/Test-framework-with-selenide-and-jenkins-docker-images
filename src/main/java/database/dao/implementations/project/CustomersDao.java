package database.dao.implementations.project;

import database.dao.implementations.AbstractDao;
import database.entities.project.Customer;
import database.exceptions.DaoException;

public abstract class CustomersDao extends AbstractDao<Customer> {

    public abstract Customer findCustomerByName(String patternName) throws DaoException;
}
