package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;
import model.UserData;
import model.AuthData;
import service.UserService;
import spark.Request;
import spark.Response;

public class logoutHandler {

    UserDAO userDAO;
    AuthDAO authDAO;


    public logoutHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public String Logout(Request req, Response res) throws DataAccessException {
        String authtoken = req.headers("authorization");

        //var authData = new Gson().fromJson(req.body(), AuthData.class);
        UserService logout = new UserService(userDAO, authDAO);
        logout.logoutService(authtoken);
        return "";
        /*
        try {
            logout.logoutService(authData);
            res.status(200);
            return "";
        }catch (Exception e){
            res.status(500);
        }
        return ""; */
    }
}
