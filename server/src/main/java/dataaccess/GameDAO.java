package dataaccess;

import model.GameData;

import java.sql.SQLException;
import java.util.Map;

public interface GameDAO {

    public void createGame(GameData game) throws SQLException, DataAccessException;

    public boolean readGame(GameData game) throws SQLException, DataAccessException;

    public Map<Integer, GameData> listGames() throws SQLException, DataAccessException;

    public void updateGame(GameData game) throws SQLException, DataAccessException;

    // Uncomment this when if you need to: public void deleteGame(GameData game);

    public GameData getGame(int gameID) throws SQLException, DataAccessException;

    public void clearAllGames() throws SQLException, DataAccessException;
}
