package dataaccess;

import model.GameData;

import java.util.HashMap;
import java.util.Map;

public class MemoryGameDAO implements GameDAO {

    public Map<Integer, GameData> memoryGameMap = new HashMap<>();


    public void createGame(GameData Game){
        memoryGameMap.put(Game.gameID(), Game);
    }

    public boolean readGame(GameData Game){

        if(memoryGameMap.containsKey(Game.gameID())){
            return true;
        }
        return false;
        //returns true if the auth toekn exists in the userbase
    }
    public Map<Integer, GameData> listGames(){
        return memoryGameMap;
    }

    public void updateGame(GameData Game){
        memoryGameMap.put(Game.gameID(), Game);
    }

    public void deleteGame(GameData Game){
        memoryGameMap.remove(Game.gameID());
    }

    public GameData getGame(int GameID){
        return memoryGameMap.get(GameID);
    }

    public void clearAllGames(){
        memoryGameMap.clear();
    }
}

