package database.queries.project;

public class OrdersQueries {

    private OrdersQueries() {
    }

    // ORDERS QUERIES
    public static final String INSERT_ORDER = "INSERT Orders(ProductId, OrderId, CreatedAt, ProductCount, Price) VALUES(?,?,?,?,?);";
    public static final String SELECT_ORDERS = "SELECT * FROM Orders;";
    public static final String SELECT_ORDER_BY_ID = "SELECT * FROM Orders WHERE ID = ?;";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE Orders SET ProductId = ?, CustomerId = ?, CreatedAt = ?, ProductCount = ?, Price = ? WHERE Id = ?;";
    public static final String DELETE_ORDER_BY_ID = "DELETE FROM Orders WHERE Id = ?;";
}
