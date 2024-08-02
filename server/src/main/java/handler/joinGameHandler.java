package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.UserData;
import model.AuthData;
import service.UserService;
import service.GameService;
import spark.Request;
import spark.Response;

public class joinGameHandler {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public joinGameHandler(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    public String joinGame(Request req, Response res) {
        var authData = new Gson().fromJson(req.body(), AuthData.class);
        GameService joinGame = new GameService(authDAO, gameDAO);
        try{
            joinGame.joinGame(authData);
            res.status(200);
            return "";
        }catch (Exception e){
            res.status(500);
        }

        return "";
    }
}
