package database.logic;

import database.dao.implementations.UserDaoImplementation;
import database.entities.User;
import database.exceptions.DaoException;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

import static org.apache.logging.log4j.LogManager.getLogger;

public class LogicDb {

    private static final Logger log = getLogger(LogicDb.class);
    private static final UserDaoImplementation USER_DAO_IMPL = new UserDaoImplementation();

    public String login() throws DaoException {
        Scanner sc = new Scanner(System.in);
        log.info("Please enter user name: ");
        String userName = sc.nextLine();
        log.info("Please enter password: ");
        String password = sc.nextLine();
        return login(userName, password);
    }

    public String login(String userName, String password) throws DaoException {
        // login DB logic
        return getMessageByUserNameAndPassword(userName, password);
    }

    public boolean delete(String userName, String password) throws DaoException {
        if (this.checkDbContainsUser(userName, password)) {
            User user = USER_DAO_IMPL.findUserByUserName(userName);
            USER_DAO_IMPL.delete(user.getId());
            log.info("The user has been deleted [UserName={}].", userName);
            return true;
        } else {
            log.info("The user hasn't been deleted, UserName: {}, Password: {}", userName, password);
            return false;
        }
    }

    private boolean checkDbContainsUser(String userName, String password) throws DaoException {
        String message = getMessageByUserNameAndPassword(userName, password);
        switch (message) {
            case "LogicDb successful":
                return true;
            case "LogicDb failed":
            case "User not found":
                return false;
            default: throw new IllegalArgumentException("The message has been unexpected.");
        }
    }

    private String getMessageByUserNameAndPassword(String userName, String password) throws DaoException {
        String message;
        User user = USER_DAO_IMPL.findUserByUserName(userName);
        if (Objects.nonNull(user)) {
            if (password.equals(user.getPassword())) {
                message = "LogicDb successful";
            } else {
                message = "LogicDb failed";
            }
        } else {
            message = "User not found";
        }
        log.info("Message: {}, for User: {}", message, user);
        return message;
    }
}
