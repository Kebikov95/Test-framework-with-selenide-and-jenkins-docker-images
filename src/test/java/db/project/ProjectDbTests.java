package db.project;

import database.connection.ProjectDbConnectionCreator;
import database.dao.implementations.project.CustomersDaoImplementations;
import database.dao.implementations.project.OrdersDaoImplementations;
import database.dao.implementations.project.ProductsDaoImplementations;
import database.entities.Entities;
import database.entities.Entity;
import database.entities.project.Customer;
import database.entities.project.Product;
import database.entities.project.Order;
import database.exceptions.DaoException;
import database.executors.Executor;
import database.queries.project.ProjectDbQueries;
import db.BaseTest;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectDbTests extends BaseTest {

    private static final CustomersDaoImplementations CUSTOMERS_DAO_IMPL = new CustomersDaoImplementations();
    private static final ProductsDaoImplementations PRODUCTS_DAO_IMPL = new ProductsDaoImplementations();
    private static final OrdersDaoImplementations ORDERS_DAO_IMPL = new OrdersDaoImplementations();

    @BeforeAll
    static void createDb() throws SQLException {
        List<String> queries = new ArrayList<>();
//        queries.add(ProjectDbQueries.CREATE_PROJECT_DB);
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

    @Test
    @org.junit.jupiter.api.Order(1)
    void createCustomer() throws DaoException {
        Customer customer = Customer.builder()
                .firstName("Mikel Owen")
                .build();
        boolean response = CUSTOMERS_DAO_IMPL.create(customer);
        assertTrue(response, "The customer hasn't been added.");
        Customer dbCustomer = CUSTOMERS_DAO_IMPL.findCustomerByName(customer.getFirstName());
        assertEquals(customer.getFirstName(), dbCustomer.getFirstName());
        log.info("The customer has been added: [{}]", customer);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void readCustomer() throws DaoException {
        Customer customer = CUSTOMERS_DAO_IMPL.findCustomerByName("Mikel Owen");
        assertNotNull(customer);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void updateCustomer() throws DaoException {
        Customer updatedCustomer = CUSTOMERS_DAO_IMPL.findCustomerByName("Mikel Owen");
        updatedCustomer.setFirstName("Nick Philips");
        CUSTOMERS_DAO_IMPL.update(updatedCustomer);

        Customer dbCustomer = CUSTOMERS_DAO_IMPL.findCustomerByName("Nick Philips");
        assertEquals(updatedCustomer, dbCustomer);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void deleteCustomer() throws DaoException, SQLException {
        Customer deletedCustomer = CUSTOMERS_DAO_IMPL.findCustomerByName("Nick Philips");
        CUSTOMERS_DAO_IMPL.delete(deletedCustomer.getId());
        Customer dbCustomer = CUSTOMERS_DAO_IMPL.findCustomerByName("Nick Philips");
        assertNull(dbCustomer);
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
        assertTrue(Entities.isListContainsEntity(dbOrders.stream()
                .map(e -> (Entity) e).collect(Collectors.toList()), order));
        log.info("The Order has been added: [{}]", order);
    }
}
