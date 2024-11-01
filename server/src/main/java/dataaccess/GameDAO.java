package dataaccess;

import model.GameData;

import java.sql.SQLException;
import java.util.Map;

public interface GameDAO {

    public void createGame(GameData game) throws SQLException, DataAccessException;

    public boolean readGame(GameData game);

    public Map<Integer, GameData> listGames();

    public void updateGame(GameData game) throws SQLException, DataAccessException;

    // Uncomment this when if you need to: public void deleteGame(GameData game);

    public GameData getGame(int gameID);

    public void clearAllGames();
}
