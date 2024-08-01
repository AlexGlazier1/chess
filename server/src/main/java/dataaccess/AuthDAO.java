package dataaccess;

import model.UserData;
import model.AuthData;
import model.GameData;

public interface AuthDAO {


    public void createAuth(AuthData auth);

    public boolean readAuth(String authToken);

    public void updateAuth(AuthData auth) throws DataAccessException;

    public void deleteAuth(AuthData auth) throws DataAccessException;

    public void clearAllAuth();
}
