package database.queries.project;

public class ProjectDbQueries {

    private ProjectDbQueries() {
    }

    // PROJECT DB
    public static final String CREATE_PROJECT_DB = "CREATE project_db";
    public static final String USE_PROJECT_DB = "USE project_db";

    // PRODUCTS TABLE
    public static final String CREATE_PRODUCTS_TABLE = "CREATE TABLE Products\n" +
            "(\n" +
            "    Id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    ProductName VARCHAR(30) NOT NULL,\n" +
            "    Manufacturer VARCHAR(20) NOT NULL,\n" +
            "    ProductCount INT DEFAULT 0,\n" +
            "    Price DECIMAL NOT NULL\n" +
            ");";
    public static final String DELETE_PRODUCTS_TABLE = "DROP TABLE Products";
    public static final String INSERT_PRODUCTS_DATA = "INSERT INTO Products (ProductName, Manufacturer, ProductCount, Price)\n" +
            "VALUES ('iPhone XR', 'Apple', 2, 76000),\n" +
            "('iPhone 13 Pro', 'Apple', 2, 51000),\n" +
            "('iPhone 12 mini', 'Apple', 5, 42000),\n" +
            "('Galaxy S9', 'Samsung', 2, 56000),\n" +
            "('Galaxy S8', 'Samsung', 1, 46000),\n" +
            "('Honor 10', 'Huawei', 2, 26000),\n" +
            "('P40', 'Huawei', 6, 38000);";

    // CUSTOMERS TABLE
    public static final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE Customers\n" +
            "(\n" +
            "    Id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    Name VARCHAR(30) NOT NULL\n" +
            ");";
    public static final String DELETE_CUSTOMERS_TABLE = "DROP TABLE Customers";
    public static final String INSERT_CUSTOMERS_DATA = "INSERT INTO Customers(FirstName) " +
            "VALUES ('Kevin Miller'), ('Martin Sven'),('Sam Smith');";

    // ORDERS TABLE
    public static  final String CREATE_ORDERS_TABLE = "CREATE TABLE Orders\n" +
            "(\n" +
            "    Id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    ProductId INT NOT NULL,\n" +
            "    CustomerId INT NOT NULL,\n" +
            "    CreatedAt DATE NOT NULL,\n" +
            "    ProductCount INT DEFAULT 1,\n" +
            "    Price DECIMAL NOT NULL,\n" +
            "    FOREIGN KEY (ProductId) REFERENCES Products(Id) ON DELETE CASCADE,\n" +
            "    FOREIGN KEY (CustomerId) REFERENCES Customers(Id) ON DELETE CASCADE\n" +
            ");";
    public static final String DELETE_ORDERS_TABLE = "DROP TABLE Customers";
    public static final String INSERT_ORDERS_DATA = "INSERT INTO Orders(ProductId, CustomerId, CreatedAt, ProductCount, Price)\n" +
            "VALUES ('5', '1', '2022-04-21', '2', '46000'),\n" +
            "('1', '1', '2022-04-22', '1', '56000'),\n" +
            "('1', '2', '2022-04-11', '1', '76000');";
}
