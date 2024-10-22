package handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import service.UserService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class LoginHandler {

    UserDAO userDAO;
    AuthDAO authDAO;


    public LoginHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public String login(Request req, Response res) throws DataAccessException, SQLException {

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();
        if(jsonObject.get("username").getAsString() == null){
            throw new DataAccessException("Error: unauthorized");
        }
        if(jsonObject.get("password").getAsString() == null){
            throw new DataAccessException("Error: unauthorized");
        }
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();


        //var userData = new Gson().fromJson(req.body(), UserData.class);
        // might need to individually extract data here
        UserService login = new UserService(userDAO, authDAO);

        return new Gson().toJson(login.loginService(username, password));

    }
}
