package dataaccess;

import model.UserData;

import java.sql.SQLException;
import java.util.Map;
public interface UserDAO {

    public void createUser(UserData user) throws SQLException, DataAccessException;

    public boolean readUser(String username) throws SQLException, DataAccessException;

    // Uncomment this when if you need to: public void updateUser(UserData user);

    // Uncomment this when if you need to: public void deleteUser(UserData user);

    public Map<String, UserData> getMemoryUserMap() throws SQLException, DataAccessException;

    public void clearAllUsers() throws SQLException, DataAccessException;
}

