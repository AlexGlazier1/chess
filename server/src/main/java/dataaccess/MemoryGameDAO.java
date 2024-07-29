package dataaccess;

import model.GameData;
import server.Server;

import java.util.HashMap;
import java.util.Map;

public class MemoryGameDAO implements GameDAO {
    
    public void createGame(GameData Game){
        Server.memoryGameMap.put(Game.gameID(), Game);
    }

    public boolean readGame(GameData Game){

        if(Server.memoryGameMap.containsKey(Game.gameID())){
            return true;
        }
        return false;
        //returns true if the auth toekn exists in the userbase
    }
    public Map<Integer, GameData> listGames(){
        return Server.memoryGameMap;
    }

    public void updateGame(GameData Game){
        Server.memoryGameMap.put(Game.gameID(), Game);
    }

    public void deleteGame(GameData Game){
        Server.memoryGameMap.remove(Game.gameID());
    }

    public void clearAllGames(){
        Server.memoryGameMap.clear();
    }
}

