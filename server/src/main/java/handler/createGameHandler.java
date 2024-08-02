package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;
import service.GameService;
import spark.Request;
import spark.Response;

public class createGameHandler {
    AuthDAO authDAO;
    GameDAO gameDAO;

    public createGameHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object createGame(Request req, Response res) {
        var authData = new Gson().fromJson(req.body(), AuthData.class);
        var gameData = new Gson().fromJson(req.body(), GameData.class);
        GameService createGame = new GameService(authDAO, gameDAO);
        try {
            res.status(200);
            return new Gson().toJson(createGame.createGame(authData, gameData).gameID());
        } catch (Exception e) {
            res.status(500);
        }
        return "";
    }
}
