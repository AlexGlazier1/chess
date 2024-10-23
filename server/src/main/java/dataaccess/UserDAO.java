package dataaccess;

import model.UserData;

import java.util.Map;
public interface UserDAO {

    public void createUser(UserData user);

    public boolean readUser(String username);

    // Uncomment this when if you need to: public void updateUser(UserData user);

    // Uncomment this when if you need to: public void deleteUser(UserData user);

    public Map<String, UserData> getMemoryUserMap();

    public void clearAllUsers();
}

