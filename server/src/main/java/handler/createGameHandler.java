package handler;

import com.google.gson.Gson;
import com.google.gson.*;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;
import service.GameService;
import spark.Spark;
import spark.Request;
import spark.Response;

public class createGameHandler {
    AuthDAO authDAO;
    GameDAO gameDAO;

    public createGameHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object createGame(Request req, Response res) throws DataAccessException {
        String authtoken = req.headers("authorization");

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();
        String gameName = jsonObject.get("gameName").getAsString();

        GameData gameData = new GameData(gameDAO.listGames().size(), null, null, gameName, null);

        //var gameData = new Gson().fromJson(req.body(), GameData.class);
        GameService createGame = new GameService(authDAO, gameDAO);
        return new Gson().toJson(createGame.createGame(authtoken, gameData).gameID());

        /*
        try {
            res.status(200);

        } catch (Exception e) {
            res.status(500);
        }
        return "";

         */
    }
}
