package database.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProjectDbConnectionCreator {

    private static final Properties PROPERTIES = new Properties();
    private static final String DATABASE_URL;
    private static final String PROPERTIES_DB_PATH = "src/main/resources/projectDb.properties";

    static {
        try {
            PROPERTIES.load(new FileReader(PROPERTIES_DB_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DATABASE_URL = (String) PROPERTIES.get("db.url");
    }

    private ProjectDbConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, PROPERTIES);
    }
}
