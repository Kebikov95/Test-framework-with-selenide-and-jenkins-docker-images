package database.queries.users;

public class UsersQueries {

    private UsersQueries() {
    }

    // USERS DB QUERIES
    public static final String CREATE_USERS_DB = "CREATE DATABASE [IF NOT EXISTS] users_db";
    public static final String USE_USERS_DB = "USE users_db";
    public static final String DROP_USERS_DB = "DROP DATABASE users_db";

    // USERS TABLE QUERIES
    public static final String INSERT_USER = "INSERT Users(UserName, Password, Email) VALUES(?,?,?);";
    public static final String SELECT_USERS = "SELECT * FROM Users;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE Id = ?;";
    public static final String SELECT_USER_BY_USER_NAME = "SELECT * FROM Users WHERE UserName = ?;";
    public static final String UPDATE_USER_BY_ID = "UPDATE Users SET UserName = ?, Password = ?, Email = ? WHERE Id = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM Users WHERE Id = ?;";


    // USERS INSERT DATA QUERIES
    public static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS Users\n" +
            "(\n" +
            "    Id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    UserName VARCHAR(20) NOT NULL UNIQUE,\n" +
            "    Password VARCHAR(20) NOT NULL,\n" +
            "    Email VARCHAR(30) NOT NULL UNIQUE\n" +
            ");";
    public static final String DELETE_USERS_TABLE = "DROP TABLE Users";
    public static final String INSERT_USERS_DATA = "INSERT Users (UserName, Password, Email) VALUES \n" +
            "('Albert_32', 'qwerty', 'email@mail.com'),\n" +
            "('Erick', 'pass1234', 'erick@gmail.com'),\n" +
            "('FrankUK', 'gb_qwerty', 'fransis1990@gmail.com'),\n" +
            "('AdelAdel', 'rolling_in_the_deep', 'adad2022@gmail.com'),\n" +
            "('Anastasia2312', 'anas2312', 'anastasia@mail.com');";
}
