package dataaccess;

import model.GameData;

import java.sql.SQLException;
import java.util.Map;

public interface GameDAO {

    public void createGame(GameData game) throws SQLException, DataAccessException;

    public boolean readGame(GameData Game);

    public Map<Integer, GameData> listGames();

    public void updateGame(GameData Game);

    public void deleteGame(GameData game);

    public GameData getGame(int GameID);

    public void clearAllGames();
}
