package database.queries;

public class Queries {

    private  Queries() {
    }

    public static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS Users\n" +
            "(\n" +
            "    Id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    UserName VARCHAR(20) NOT NULL UNIQUE,\n" +
            "    Password VARCHAR(20) NOT NULL,\n" +
            "    Email VARCHAR(30) NOT NULL UNIQUE\n" +
            ");";

    public static final String DELETE_USERS_TABLE = "DROP TABLE Users";

    public static final String INSERT_USERS = "INSERT Users (UserName, Password, Email) VALUES \n" +
            "('Albert_32', 'qwerty', 'email@mail.com'),\n" +
            "('Erick', 'pass1234', 'erick@gmail.com'),\n" +
            "('FrankUK', 'gb_qwerty', 'fransis1990@gmail.com'),\n" +
            "('AdelAdel', 'rolling_in_the_deep', 'adad2022@gmail.com'),\n" +
            "('Anastasia2312', '23122132', 'anastasia@mail.com');";
}
