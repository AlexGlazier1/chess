package handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import service.GameService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class JoinGameHandler {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public JoinGameHandler(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    public Object joinGame(Request req, Response res) throws SQLException, DataAccessException {
        String authtoken = req.headers("authorization");

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();

        if (req.headers("authorization") == null || !authDAO.readAuth(authtoken)) {
            throw new DataAccessException("Error: unauthorized");
        }
        if(jsonObject.get("playerColor") == null){
            throw new DataAccessException("Error: bad request");

        }
        if(jsonObject.get("gameID") == null){
            throw new DataAccessException("Error: bad request");
        }

        String playerColor = jsonObject.get("playerColor").getAsString();
        int gameID = jsonObject.get("gameID").getAsInt();


        GameService joinGame = new GameService(authDAO, gameDAO);
        joinGame.joinGame(authtoken, playerColor, gameID);
        return "";

    }
}
