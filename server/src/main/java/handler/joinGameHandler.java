package handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.UserData;
import model.AuthData;
import model.GameData;
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

    public String joinGame(Request req, Response res) throws DataAccessException {
        String authtoken = req.headers("authorization");

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();
        String playerColor = jsonObject.get("playerColor").getAsString();
        int gameID = jsonObject.get("gameID").getAsInt();

        String gameName = gameDAO.getGame(gameID).gameName();

        GameData gameData;
        if(playerColor.equals("WHITE")) {
            gameData = new GameData(gameID, authDAO.getUsername(authtoken), gameDAO.getGame(gameID).blackUsername(), gameName, null);
        }else {
            gameData = new GameData(gameID, gameDAO.getGame(gameID).whiteUsername(), authDAO.getUsername(authtoken), gameName, null);
        }


        GameService joinGame = new GameService(authDAO, gameDAO);

        joinGame.joinGame(authtoken, gameData);
        return "";
        /*
        try{
            joinGame.joinGame(authData);
            res.status(200);
            return "";
        }catch (Exception e){
            res.status(500);
        }

        return "";
         */
    }
}
