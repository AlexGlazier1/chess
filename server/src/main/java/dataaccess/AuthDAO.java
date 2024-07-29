package dataaccess;

import model.UserData;
import model.AuthData;
import model.GameData;

public interface AuthDAO {


    public void createAuth(AuthData auth);

    public boolean readAuth(int authToken);

    public void updateAuth(AuthData auth);

    public void deleteAuth(AuthData auth);

    public void clearAllAuth();
}
