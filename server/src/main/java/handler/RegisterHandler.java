package handler;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;
import model.UserData;
import service.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class RegisterHandler {


    UserDAO userDAO;
    AuthDAO authDAO;

    public RegisterHandler(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public Object Register(Request req, Response res) throws DataAccessException {
        var userData = new Gson().fromJson(req.body(), UserData.class);
        UserService register = new UserService(userDAO, authDAO);
        return new Gson().toJson(register.registerService(userData));
        /*
        try {
            //register.registerService(userData);
            res.status(200);
            return new Gson().toJson(register.registerService(userData));
        }catch (Exception e){
            res.status(500);
        }
        return "";

         */

    }

    //var pet = new Gson().fromJson(req.body(), Pet.class);
    //pet = service.addPet(pet);
    //return new Gson().toJson(pet);
}
