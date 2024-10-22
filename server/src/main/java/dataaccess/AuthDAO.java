package dataaccess;

import model.AuthData;

import java.sql.SQLException;

public interface AuthDAO {


    public void createAuth(AuthData auth) throws SQLException, DataAccessException;

    public boolean readAuth(String authToken);

    public void updateAuth(AuthData auth) throws DataAccessException;

    public String getUsername(String authToken) throws DataAccessException;

    public AuthData getAuth(String authToken) throws DataAccessException;

    public void deleteAuth(AuthData auth) throws DataAccessException;

    public void clearAllAuth();
}
