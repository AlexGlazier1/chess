package dataaccess;

import model.GameData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


public class SQLGameDAO implements GameDAO {
    public void createGame(GameData game) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        //int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game;


        try (var preparedStatement = conn.prepareStatement("INSERT INTO GameData(gameID,whiteUsername,blackUsername,gameName,game) VALUES(?,?,?,?,?)")){
            preparedStatement.setInt(1, game.gameID());
            preparedStatement.setString(2, game.whiteUsername());
            preparedStatement.setString(3, game.blackUsername());
            preparedStatement.setString(4, game.gameName());
            var gameJson = new Gson().toJson(game.game());
            preparedStatement.setString(5, gameJson);

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        }
    }

    public boolean readGame(GameData Game){
        return true;
    }
    public Map<Integer, GameData> listGames(){
        return null;
    }

    public void updateGame(GameData Game) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("UPDATE Gamedata SET gameID=? WHERE id=?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, petID);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteGame(GameData Game){
    }

    public GameData getGame(int GameID){
        return null;
    }

    public void clearAllGames(){
    }
}

