package database.dao.implementations.project;

import database.dao.implementations.AbstractDao;
import database.entities.project.Product;
import database.exceptions.DaoException;

public abstract class ProductsDao extends AbstractDao<Product> {

    public abstract Product findEntityByProductName(String patternName) throws DaoException;
}
