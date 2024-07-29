package dataaccess;

import server.Server;
import model.AuthData;


public class MemoryAuthDAO implements AuthDAO {

    public void createAuth(AuthData auth){
        Server.memoryAuthMap.put(auth.authToken(), auth.username());
    }

    public boolean readAuth(int authToken){

        if(Server.memoryAuthMap.containsKey(authToken)){
            return true;
        }
        return false;
        //returns true if the auth toekn exists in the userbase
    }

    public void updateAuth(AuthData auth){
        Server.memoryAuthMap.put(auth.authToken(), auth.username());
    }

    public void deleteAuth(AuthData auth){
        Server.memoryAuthMap.remove(auth.authToken());
    }

    public void clearAllAuth(){
        Server.memoryAuthMap.clear();
    }
}
