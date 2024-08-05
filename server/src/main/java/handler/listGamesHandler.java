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


    public Object listGames(Request req, Response res) throws DataAccessException {
        //var authData = new Gson().fromJson(req.body(), AuthData.class);
        //var gameData = new Gson().fromJson(req.body(), GameData.class);
        String authtoken = req.headers("authorization");
        GameService listGames = new GameService(authDAO, gameDAO);

        return new Gson().toJson(listGames.listGames(authtoken));
        /*
        try {
            //register.registerService(userData);
            res.status(200);

        } catch (Exception e) {
            res.status(500);
        }
        return "";
         */
    }
}
