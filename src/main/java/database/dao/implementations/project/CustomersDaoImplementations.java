package database.dao.implementations.project;

import database.connection.ProjectDbConnectionCreator;
import database.dao.implementations.AbstractDao;
import database.entities.project.Customer;
import database.enums.users.UsersTableFields;
import database.exceptions.DaoException;
import database.queries.project.CustomersQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.enums.project.CustomersTableFields.FIRST_NAME;
import static database.enums.project.CustomersTableFields.ID;

public class CustomersDaoImplementations extends AbstractDao<Customer> {

    @Override
    public boolean create(Customer customer) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.INSERT_CUSTOMER)) {
            statement.setString(1, customer.getFirstName());
            return !statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void create(List<Customer> customers) throws DaoException {
        for (Customer customer : customers) {
            this.create(customer);
        }
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.SELECT_CUSTOMERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = Customer.builder()
                        .id(resultSet.getInt(ID.getFieldName()))
                        .firstName(resultSet.getString(FIRST_NAME.getFieldName()))
                        .build();
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer findEntityById(long id) throws DaoException {
        Customer customer = null;
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.SELECT_CUSTOMER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = Customer.builder()
                        .id(resultSet.getInt(UsersTableFields.ID.getFieldName()))
                        .firstName(resultSet.getString(FIRST_NAME.getFieldName()))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer) throws DaoException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.UPDATE_CUSTOMER_BY_ID)) {
            statement.setString(1, customer.getFirstName());
            statement.executeUpdate();
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Customer customer) throws DaoException, SQLException {
        return this.delete(customer.getId());
    }

    @Override
    public boolean delete(long id) throws DaoException, SQLException {
        try (Connection connection = ProjectDbConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(CustomersQueries.DELETE_CUSTOMER_BY_ID)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        }
    }
}
