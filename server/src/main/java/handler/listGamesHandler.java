package handler;

import com.google.gson.*;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import spark.*;
import service.GameService;
import dataaccess.DataAccessException;
import model.GameData;
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
