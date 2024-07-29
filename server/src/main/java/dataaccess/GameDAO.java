package dataaccess;
import model.UserData;
import model.AuthData;
import model.GameData;

import java.util.Map;

public interface GameDAO {

    public void createGame(GameData game);

    public boolean readGame(GameData Game);

    public Map<Integer, GameData> listGames();

    public void updateGame(GameData Game);

    public void deleteGame(GameData game);

    public void clearAllGames();
}
