package database.queries.project;

public class OrdersQueries {

    private OrdersQueries() {
    }

    // ORDERS QUERIES
    public static final String INSERT_CUSTOMER = "INSERT Orders(ProductId, CustomerId, CreatedAt, ProductCount, Price) VALUES(?,?,?,?,?);";
    public static final String SELECT_CUSTOMERS = "SELECT * FROM Orders;";
    public static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Orders WHERE ID = ?;";
    public static final String UPDATE_CUSTOMER_BY_ID = "UPDATE Orders SET ProductId = ?, CustomerId = ?, CreatedAt = ?, ProductCount = ?, Price = ? WHERE Id = ?;";
    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM Orders WHERE Id = ?;";
}
