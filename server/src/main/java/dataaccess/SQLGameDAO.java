package dataaccess;

import model.GameData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.UserData;
import chess.ChessGame;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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
    public Map<Integer, GameData> listGames() throws SQLException, DataAccessException{
        Map<Integer, GameData> SQLGameMap = new HashMap<>();

        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT * FROM authData")) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    var myGameID = rs.getInt("gameID");
                    var myWhiteUsername = rs.getString("whiteUsername");
                    var myBlackUsername = rs.getString("blackUsername");
                    var myGameName = rs.getString("gameName");
                    var myGameJson = rs.getString("game");
                    var myGame = new Gson().fromJson(myGameJson, ChessGame.class);

                    GameData game = new GameData(myGameID,myWhiteUsername, myBlackUsername, myGameName, myGame);
                    SQLGameMap.put(game.gameID(), game);
                }
            }
        }
        return SQLGameMap;
    }

    public void updateGame(GameData game) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("UPDATE gameData SET whiteUsername=?, blackUsername=?, game=?,  WHERE gameID=?")) {
            preparedStatement.setString(1, game.whiteUsername());
            preparedStatement.setString(2, game.blackUsername());
            var gameJson = new Gson().toJson(game.game());
            preparedStatement.setString(3, gameJson);
            preparedStatement.setInt(4, game.gameID());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteGame(GameData Game){
    }

    public GameData getGame(int GameID){
        return null;
    }

    public void clearAllGames() throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();
        try (var preparedStatement = conn.prepareStatement("TRUNCATE gameData")) {
            preparedStatement.executeUpdate();
        }
    }
}


