package dataaccess;

import model.GameData;

import java.util.HashMap;
import java.util.Map;

public class MemoryGameDAO implements GameDAO {

    public Map<Integer, GameData> memoryGameMap = new HashMap<>();


    public void createGame(GameData game){
        memoryGameMap.put(game.gameID(), game);
    }

    public boolean readGame(GameData game){

        if(memoryGameMap.containsKey(game.gameID())){
            return true;
        }
        return false;
        //returns true if the auth toekn exists in the userbase
    }
    public Map<Integer, GameData> listGames(){
        return memoryGameMap;
    }

    public void updateGame(GameData game){
        memoryGameMap.put(game.gameID(), game);
    }

    public void deleteGame(GameData game){
        memoryGameMap.remove(game.gameID());
    }

    public GameData getGame(int gameID){
        return memoryGameMap.get(gameID);
    }

    public void clearAllGames(){
        memoryGameMap.clear();
    }
}

