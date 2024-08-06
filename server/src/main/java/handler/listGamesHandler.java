package handler;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Array;
import java.util.ArrayList;


public class listGamesHandler {

    AuthDAO authDAO;
    GameDAO gameDAO;
    Gson gson = new Gson();
    public listGamesHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object listGames(Request req, Response res) throws DataAccessException {
        //var authData = new Gson().fromJson(req.body(), AuthData.class);
        //var gameData = new Gson().fromJson(req.body(), GameData.class);
        String authtoken = req.headers("authorization");
        GameService listGames = new GameService(authDAO, gameDAO);
        ArrayList<GameData> temp = listGames.listGames(authtoken);

        String data = gson.toJson(temp);
        JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();

        JsonObject json = new JsonObject();
        json.add("games", jsonArray);
        return new Gson().toJson(json);
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
