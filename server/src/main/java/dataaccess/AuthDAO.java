package dataaccess;

import model.AuthData;

import java.sql.SQLException;

public interface AuthDAO {


    public void createAuth(AuthData auth) throws SQLException, DataAccessException;

    public boolean readAuth(String authToken) throws SQLException, DataAccessException;

    // Uncomment this when if you need to: public void updateAuth(AuthData auth) throws DataAccessException;

    public String getUsername(String authToken) throws DataAccessException, SQLException;

    public AuthData getAuth(String authToken) throws DataAccessException, SQLException;

    public void deleteAuth(AuthData auth) throws DataAccessException, SQLException;

    public void clearAllAuth() throws SQLException, DataAccessException;
}
