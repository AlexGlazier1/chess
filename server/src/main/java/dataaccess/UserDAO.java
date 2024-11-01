package dataaccess;

import model.UserData;

import java.sql.SQLException;
import java.util.Map;
public interface UserDAO {

    public void createUser(UserData user) throws SQLException, DataAccessException;

    public boolean readUser(String username);

    // Uncomment this when if you need to: public void updateUser(UserData user);

    // Uncomment this when if you need to: public void deleteUser(UserData user);

    public Map<String, UserData> getMemoryUserMap();

    public void clearAllUsers() throws SQLException, DataAccessException;
}

