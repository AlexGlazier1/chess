package dataaccess;

import model.GameData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


public class SQLGameDAO implements GameDAO {
    public void createGame(GameData Game) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        //int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game;


        try (var preparedStatement = conn.prepareStatement("INSERT INTO GameData (gameID, game) VALUES(?, ?)")) {
            preparedStatement.setInt(1, Game.gameID());
            preparedStatement.setObject(2, Game.game());

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

    public void updateGame(GameData Game){
    }

    public void deleteGame(GameData Game){
    }

    public GameData getGame(int GameID){
        return null;
    }

    public void clearAllGames(){
    }
}


