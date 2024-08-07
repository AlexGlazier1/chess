package dataaccess;

import model.UserData;
import model.AuthData;
import model.GameData;
import java.util.HashMap;
import java.util.Map;
public interface UserDAO {

    public void createUser(UserData user);

    public boolean readUser(String username);

    public void updateUser(UserData user);

    public void deleteUser(UserData user);

    public Map<String, UserData> getMemoryUserMap();

    public void clearAllUsers();
}

