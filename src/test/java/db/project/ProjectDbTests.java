package db.project;

import database.connection.ProjectDbConnectionCreator;
import database.dao.implementations.project.CustomersDaoImplementations;
import database.dao.implementations.project.OrdersDaoImplementations;
import database.dao.implementations.project.ProductsDaoImplementations;
import database.entities.Entity;
import database.entities.project.Customer;
import database.entities.project.Product;
import database.entities.project.Order;
import database.exceptions.DaoException;
import database.executors.Executor;
import database.queries.project.ProjectDbQueries;
import db.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectDbTests extends BaseTest {

    private static final CustomersDaoImplementations CUSTOMERS_DAO_IMPL = new CustomersDaoImplementations();
    private static final ProductsDaoImplementations PRODUCTS_DAO_IMPL = new ProductsDaoImplementations();
    private static final OrdersDaoImplementations ORDERS_DAO_IMPL = new OrdersDaoImplementations();

    @BeforeAll
    static void createDb() throws SQLException {
        List<String> queries = new ArrayList<>();
        queries.add(ProjectDbQueries.CREATE_PROJECT_DB);
        queries.add(ProjectDbQueries.USE_PROJECT_DB);
        queries.add(ProjectDbQueries.CREATE_CUSTOMERS_TABLE);
        queries.add(ProjectDbQueries.CREATE_PRODUCTS_TABLE);
        queries.add(ProjectDbQueries.CREATE_ORDERS_TABLE);
        queries.add(ProjectDbQueries.INSERT_CUSTOMERS_DATA);
        queries.add(ProjectDbQueries.INSERT_PRODUCTS_DATA);
        queries.add(ProjectDbQueries.INSERT_ORDERS_DATA);
        Executor.executeBatch(ProjectDbConnectionCreator.createConnection(), queries);
    }

    @AfterAll
    static void deleteDb() throws SQLException {
        List<String> queries = new ArrayList<>();
        queries.add(ProjectDbQueries.DELETE_ORDERS_TABLE);
        queries.add(ProjectDbQueries.DELETE_CUSTOMERS_TABLE);
        queries.add(ProjectDbQueries.DELETE_PRODUCTS_TABLE);
        Executor.executeBatch(ProjectDbConnectionCreator.createConnection(), queries);
        log.info("Drop all project tables.");
    }

    @BeforeEach
    void useDB() throws SQLException {
        List<String> queries = new ArrayList<>();
        queries.add(ProjectDbQueries.CREATE_PROJECT_DB);
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
        log.info("The customer has been added: [{}]", customer);
    }

    @Test
    void createProduct() throws DaoException {
        Product product = Product.builder()
                .productName("iPhone 11")
                .manufacturer("Apple")
                .productCount(5)
                .price(64330)
                .build();
        boolean response = PRODUCTS_DAO_IMPL.create(product);
        assertTrue(response, "The product hasn't been added.");
        Product dbProduct = PRODUCTS_DAO_IMPL.findEntityByProductName(product.getProductName());
        assertEquals(product.getProductName(), dbProduct.getProductName());
        log.info("The product has been added: [{}]", product);
    }

    @Test
     void createOrder() throws DaoException {
        Order order = Order.builder()
                .productId(2)
                .customerId(2)
                .createdAt("2022-04-28")
                .productCount(2)
                .price(84000)
                .build();
        boolean response = ORDERS_DAO_IMPL.create(order);
        assertTrue(response, "The order hasn't been added.");
        List<Order> dbOrders = ORDERS_DAO_IMPL.findAll();
        assertTrue(isListContainsEntity(dbOrders.stream()
                .map(e -> (Entity) e).collect(Collectors.toList()), order));
        log.info("The Order has been added: [{}]", order);
    }

    private boolean isListContainsEntity(List<Entity> entities, Entity findEntity) {
        for (Entity entity : entities) {
            if (entity.equals(findEntity)) {
                return true;
            }
        }
        return false;
    }
}
