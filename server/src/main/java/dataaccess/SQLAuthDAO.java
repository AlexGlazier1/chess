package dataaccess;

import model.AuthData;

public class SQLAuthDAO implements AuthDAO {
    public void createAuth(AuthData auth){
    }

    public boolean readAuth(String authToken) {

    }

    public void updateAuth(AuthData auth) throws DataAccessException{
    }

    public String getUsername(String authToken) throws DataAccessException{
    }

    public AuthData getAuth(String authToken) throws DataAccessException{
    }

    public void deleteAuth(AuthData auth) throws DataAccessException {
    }

    public void clearAllAuth(){
    }
}
