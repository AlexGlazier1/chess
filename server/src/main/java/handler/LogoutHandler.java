package handler;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import service.UserService;
import spark.Request;
import spark.Response;

public class LogoutHandler {

    UserDAO userDAO;
    AuthDAO authDAO;


    public LogoutHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public String Logout(Request req, Response res) throws DataAccessException {
        String authtoken = req.headers("authorization");

        UserService logout = new UserService(userDAO, authDAO);
        logout.logoutService(authtoken);
        return "";

    }
}
