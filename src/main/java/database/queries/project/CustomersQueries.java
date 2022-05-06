package database.queries.project;

public class CustomersQueries {

    private CustomersQueries() {
    }

    // CUSTOMERS QUERIES
    public static final String INSERT_CUSTOMER = "INSERT Customers(Name) VALUES(?);";
    public static final String SELECT_CUSTOMERS = "SELECT * FROM Customers;";
    public static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customers WHERE ID = ?;";
    public static final String SELECT_USER_BY_USER_NAME = "SELECT * FROM Customers WHERE Name = ?;";
    public static final String UPDATE_CUSTOMER_BY_ID = "UPDATE Customers SET Name = ? WHERE Id = ?;";
    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM Customers WHERE Id = ?;";
}
