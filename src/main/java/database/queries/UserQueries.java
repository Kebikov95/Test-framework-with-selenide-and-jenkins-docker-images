package database.queries;

public class UserQueries {

    private UserQueries() {
    }

    public static final String INSERT_USER = "INSERT Users(UserName, Password, Email) VALUES(?,?,?);";
    public static final String SELECT_USERS = "SELECT * FROM Users;";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE Id = ?;";
    public static final String SELECT_USER_BY_USER_NAME = "SELECT * FROM Users WHERE UserName = ?;";
    public static final String UPDATE_USER_BY_ID = "UPDATE Users SET UserName = ?, Password = ?, Email = ? WHERE Id = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM Users WHERE Id = ?;";
}
