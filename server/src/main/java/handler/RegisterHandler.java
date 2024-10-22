package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class RegisterHandler {


    UserDAO userDAO;
    AuthDAO authDAO;

    public RegisterHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public Object register(Request req, Response res) throws DataAccessException, SQLException {
        var userData = new Gson().fromJson(req.body(), UserData.class);
        UserService register = new UserService(userDAO, authDAO);
        return new Gson().toJson(register.registerService(userData));

    }

}
