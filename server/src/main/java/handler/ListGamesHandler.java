package handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import service.GameService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;


public class ListGamesHandler {

    AuthDAO authDAO;
    GameDAO gameDAO;
    Gson gson = new Gson();
    public ListGamesHandler(GameDAO gameDAO, AuthDAO authDAO) {
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }


    public Object listGames(Request req, Response res) throws DataAccessException {

        String authtoken = req.headers("authorization");
        GameService listGames = new GameService(authDAO, gameDAO);
        ArrayList<GameData> temp = listGames.listGames(authtoken);

        String data = gson.toJson(temp);
        JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();

        JsonObject json = new JsonObject();
        json.add("games", jsonArray);
        return new Gson().toJson(json);

    }
}
