package handler;

import chess.ChessGame;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import service.GameService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class createGameHandler {
    AuthDAO authDAO;
    GameDAO gameDAO;
    int gameID = 1;
    public createGameHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object createGame(Request req, Response res) throws DataAccessException, SQLException {
        String authtoken = req.headers("authorization");

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();
        String gameName = jsonObject.get("gameName").getAsString();

        GameData gameData = new GameData(gameID, null, null, gameName, new ChessGame());
        gameID++;

        GameService createGame = new GameService(authDAO, gameDAO);
        int gameID = createGame.createGame(authtoken, gameData).gameID();

        JsonObject json = new JsonObject();
        json.addProperty("gameID", gameID);
        return new Gson().toJson(json);

    }
}
