package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;

public class loginHandler {

    UserDAO userDAO;
    AuthDAO authDAO;


    public loginHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public String Login(Request req, Response res) throws DataAccessException {
        var userData = new Gson().fromJson(req.body(), UserData.class);
        UserService login = new UserService(userDAO, authDAO);
        login.loginService(userData);
        return "";
        /*
        try {
            login.loginService(userData);
            res.status(200);
            return "";
        }catch (Exception e){
            res.status(500);
        }
        return "";
         */
    }
}
