package database.dao.implementations.project;

import database.connection.ProjectDbConnectionCreator;
import database.entities.project.Order;
import database.exceptions.DaoException;
import database.queries.project.OrdersQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.enums.project.OrdersTableFields.*;

public class OrdersDaoImplementations implements OrdersDao {

    @Override
    public boolean create(Order order) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(OrdersQueries.INSERT_ORDER)) {
            statement.setLong(1, order.getProductId());
            statement.setLong(2, order.getCustomerId());
            statement.setString(3, order.getCreatedAt());
            statement.setInt(4, order.getProductCount());
            statement.setInt(5, order.getPrice());
            return !statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(List<Order> orders) throws DaoException {
        for (Order order : orders) {
            this.create(order);
        }
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(OrdersQueries.SELECT_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = Order.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .productId(resultSet.getLong(PRODUCT_ID.getFieldName()))
                        .customerId(resultSet.getLong(CUSTOMER_ID.getFieldName()))
                        .createdAt(resultSet.getString(CREATED_AT.getFieldName()))
                        .productCount(resultSet.getInt(PRODUCT_COUNT.getFieldName()))
                        .price(resultSet.getInt(PRICE.getFieldName()))
                        .build();
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findEntityById(Long id) throws DaoException {
        Order order = null;
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(OrdersQueries.SELECT_ORDER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = Order.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .productId(resultSet.getLong(PRODUCT_ID.getFieldName()))
                        .customerId(resultSet.getLong(CUSTOMER_ID.getFieldName()))
                        .createdAt(resultSet.getString(CREATED_AT.getFieldName()))
                        .productCount(resultSet.getInt(PRODUCT_COUNT.getFieldName()))
                        .price(resultSet.getInt(PRICE.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return order;
    }

    @Override
    public Order update(Order order) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(OrdersQueries.UPDATE_ORDER_BY_ID)) {
            statement.setLong(1, order.getProductId());
            statement.setLong(2, order.getCustomerId());
            statement.setString(3, order.getCreatedAt());
            statement.setInt(4, order.getProductCount());
            statement.setInt(5, order.getPrice());
            statement.executeUpdate();
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Order order) throws DaoException, SQLException {
        return this.delete(order.getId());
    }

    @Override
    public boolean delete(Long id) throws DaoException, SQLException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(OrdersQueries.DELETE_ORDER_BY_ID)) {
            statement.setString(1, id.toString());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }
}
