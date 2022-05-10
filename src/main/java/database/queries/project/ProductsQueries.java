package database.queries.project;

public class ProductsQueries {

    private ProductsQueries() {
    }

    // PRODUCTS QUERIES
    public static final String INSERT_PRODUCT = "INSERT Products(ProductName, Manufacturer, ProductCount, Price) VALUES(?,?,?,?);";
    public static final String SELECT_PRODUCTS = "SELECT * FROM Products;";
    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Products WHERE ID = ?;";
    public static final String SELECT_PRODUCT_BY_PRODUCT_NAME = "SELECT * FROM Products WHERE ProductName = ?;";
    public static final String UPDATE_PRODUCT_BY_ID = "UPDATE Products SET ProductName = ?, Manufacturer = ?, ProductCount = ?, Price = ? WHERE Id = ?;";
    public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM Products WHERE Id = ?;";
}
