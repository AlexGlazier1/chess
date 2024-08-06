package handler;

import chess.ChessGame;
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

    public Object joinGame(Request req, Response res) throws DataAccessException {
        String authtoken = req.headers("authorization");

        JsonObject jsonObject = JsonParser.parseString(req.body()).getAsJsonObject();

        if (req.headers("authorization") == null || !authDAO.readAuth(authtoken)) {
            throw new DataAccessException("Error: unauthorized");
        }
        if(jsonObject.get("playerColor") == null){
            throw new DataAccessException("Error: bad request");
        //}else if(jsonObject.get("playerColor").getAsString() == "WHITE" || jsonObject.get("playerColor").getAsString() == "BLACK" ) {
        //    throw new DataAccessException("Error: hmmm");
        }
        if(jsonObject.get("gameID") == null){
            throw new DataAccessException("Error: bad request");
        }

        String playerColor = jsonObject.get("playerColor").getAsString();
        int gameID = jsonObject.get("gameID").getAsInt();

        //String gameName = gameDAO.getGame(gameID).gameName();


        GameService joinGame = new GameService(authDAO, gameDAO);
        joinGame.joinGame(authtoken, playerColor, gameID);
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
