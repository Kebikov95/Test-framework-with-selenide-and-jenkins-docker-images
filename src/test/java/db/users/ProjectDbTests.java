package db.users;

import database.connection.ProjectDbConnectionCreator;
import database.dao.implementations.project.CustomersDaoImplementations;
import database.dao.implementations.project.OrdersDaoImplementations;
import database.dao.implementations.project.ProductsDaoImplementations;
import database.entities.project.Customer;
import database.exceptions.DaoException;
import database.executors.Executor;
import database.queries.project.ProjectDbQueries;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectDbTests extends BaseTest {

    private static final CustomersDaoImplementations CUSTOMERS_DAO_IMPL = new CustomersDaoImplementations();
    private static final ProductsDaoImplementations PRODUCTS_DAO_IMPL = new ProductsDaoImplementations();
    private static final OrdersDaoImplementations ORDERS_DAO_IMPL = new OrdersDaoImplementations();

    @BeforeAll
    static void createDb() throws SQLException {
        List<String> queries = new ArrayList<>();
        queries.add(ProjectDbQueries.CREATE_CUSTOMERS_TABLE);
//        queries.add(ProjectDbQueries.CREATE_PRODUCTS_TABLE);
//        queries.add(ProjectDbQueries.CREATE_ORDERS_TABLE);
        queries.add(ProjectDbQueries.INSERT_CUSTOMERS_DATA);
//        queries.add(ProjectDbQueries.INSERT_PRODUCTS_DATA);
//        queries.add(ProjectDbQueries.INSERT_ORDERS_DATA);
        Executor.executeBatch(ProjectDbConnectionCreator.createConnection(), queries);
    }

    @AfterAll
    static void deleteDb() throws SQLException {
        List<String> queries = new ArrayList<>();
//        queries.add(ProjectDbQueries.DELETE_ORDERS_TABLE);
        queries.add(ProjectDbQueries.DELETE_CUSTOMERS_TABLE);
//        queries.add(ProjectDbQueries.DELETE_PRODUCTS_TABLE);
        Executor.executeBatch(ProjectDbConnectionCreator.createConnection(), queries);
    }

    @Test
    void createCustomer() throws DaoException {
        Customer customer = Customer.builder()
                .firstName("Mikel Owen")
                .build();
        boolean response = CUSTOMERS_DAO_IMPL.create(customer);
        assertTrue(response, "The customer hasn't been added.");
        Customer dbCustomer = CUSTOMERS_DAO_IMPL.findCustomerByFirstName(customer.getFirstName());
        assertEquals(customer.getFirstName(), dbCustomer.getFirstName());
        log.info("The user has been added: [{}]", customer);
    }
}
