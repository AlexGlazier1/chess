package dataaccess;

import model.AuthData;

import java.util.HashMap;
import java.util.Map;


public class MemoryAuthDAO implements AuthDAO {

    public Map<String, AuthData> memoryAuthMap = new HashMap<>();

    public void createAuth(AuthData auth){
        memoryAuthMap.put(auth.authToken(), auth);
    }

    public boolean readAuth(String authToken) {

        if(memoryAuthMap.containsKey(authToken)){
            return true;
        }
        return false;
        //returns true if the auth toekn exists in the userbase
    }

    // Uncomment this when if you need to: public void updateAuth(AuthData auth) throws DataAccessException{
    // Uncomment this when if you need to:         memoryAuthMap.put(auth.authToken(), auth);
    // Uncomment this when if you need to: }

    public String getUsername(String authToken) throws DataAccessException{
        return memoryAuthMap.get(authToken).username();
    }

    public AuthData getAuth(String authToken) throws DataAccessException{
        return memoryAuthMap.get(authToken);
    }

    public void deleteAuth(AuthData auth) throws DataAccessException {
        memoryAuthMap.remove(auth.authToken());
    }

    public void clearAllAuth(){
        memoryAuthMap.clear();
    }
}
