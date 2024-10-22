package dataaccess;

import model.GameData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/*
public class SQLGameDAO implements GameDAO {
    public void createGame(GameData Game) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game


        try (var preparedStatement = conn.prepareStatement("INSERT INTO AuthData (authToken, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, auth.authToken());
            preparedStatement.setString(2, auth.username());

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        }
    }

    public boolean readGame(GameData Game){
    }
    public Map<Integer, GameData> listGames(){
    }

    public void updateGame(GameData Game){
    }

    public void deleteGame(GameData Game){
    }

    public GameData getGame(int GameID){
    }

    public void clearAllGames(){
    }
}

 */
