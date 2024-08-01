package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import model.UserData;
import service.UserService;
import spark.*;
import service.GameService;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.GameData;
import model.AuthData;


public class listGamesHandler {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public listGamesHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object listGames(Request req, Response res) {
        var authData = new Gson().fromJson(req.body(), AuthData.class);
        var gameData = new Gson().fromJson(req.body(), GameData.class);
        GameService listGames = new GameService(authDAO, gameDAO);
        try {
            //register.registerService(userData);
            res.status(200);
            return new Gson().toJson(listGames.listGames(authData));
        } catch (Exception e) {
            res.status(500);
        }
        return "";
    }
}
