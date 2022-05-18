package database.dao.implementations.project;

import database.connection.ProjectDbConnectionCreator;
import database.entities.project.Product;
import database.exceptions.DaoException;
import database.queries.project.ProductsQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.enums.project.OrdersTableFields.PRICE;
import static database.enums.project.OrdersTableFields.PRODUCT_COUNT;
import static database.enums.project.ProductsTableFields.*;
import static database.enums.project.ProductsTableFields.ID;

public class ProductsDaoImplementations extends ProductsDao {

    @Override
    public boolean create(Product product) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.INSERT_PRODUCT)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getManufacturer());
            statement.setInt(3, product.getProductCount());
            statement.setInt(4, product.getPrice());
            return !statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(List<Product> products) throws DaoException {
        for (Product product : products) {
            this.create(product);
        }
    }

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.SELECT_PRODUCTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = Product.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .productName(resultSet.getString(PRODUCT_NAME.getFieldName()))
                        .manufacturer(resultSet.getString(MANUFACTURER.getFieldName()))
                        .productCount(resultSet.getInt(PRODUCT_COUNT.getFieldName()))
                        .price(resultSet.getInt(PRICE.getFieldName()))
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return products;
    }

    @Override
    public Product findEntityById(long id) throws DaoException {
        Product product = null;
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.SELECT_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .productName(resultSet.getString(PRODUCT_NAME.getFieldName()))
                        .manufacturer(resultSet.getString(MANUFACTURER.getFieldName()))
                        .productCount(resultSet.getInt(PRODUCT_COUNT.getFieldName()))
                        .price(resultSet.getInt(PRICE.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return product;
    }

    @Override
    public Product findEntityByProductName(String patternName) throws DaoException {
        Product product = null;
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.SELECT_PRODUCT_BY_PRODUCT_NAME)) {
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .productName(resultSet.getString(PRODUCT_NAME.getFieldName()))
                        .manufacturer(resultSet.getString(MANUFACTURER.getFieldName()))
                        .productCount(resultSet.getInt(PRODUCT_COUNT.getFieldName()))
                        .price(resultSet.getInt(PRICE.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return product;
    }

    @Override
    public Product update(Product product) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.UPDATE_PRODUCT_BY_ID)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getManufacturer());
            statement.setInt(3, product.getProductCount());
            statement.setInt(4, product.getPrice());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Product product) throws DaoException, SQLException {
        return this.delete(product.getId());
    }

    @Override
    public boolean delete(long id) throws DaoException, SQLException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(ProductsQueries.DELETE_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
