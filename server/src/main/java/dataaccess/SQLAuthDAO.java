package dataaccess;

import model.AuthData;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLAuthDAO implements AuthDAO {

    public void createAuth(AuthData auth) throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("INSERT INTO authData (authToken, username) VALUES(?, ?)",
                                                                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, auth.authToken());
            preparedStatement.setString(2, auth.username());

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        //DatabaseManager.getConnection();
    }

    public boolean readAuth(String authToken) throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT 1 FROM authData WHERE authToken = ? LIMIT 1;")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return true;
                }
                return false;
            }


        }
    }

    //Don't need this, might delete out of all the DAOs: public void updateAuth(AuthData auth) throws DataAccessException{
    //}

    public String getUsername(String authToken) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT username FROM authData WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    var username = rs.getString("username");

                    return username;
                }
            }
        }
        return null;
    }

    public AuthData getAuth(String authToken) throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT authToken, username FROM authData WHERE authToken=?")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    var myAuthToken = rs.getString("authToken");
                    var myUsername = rs.getString("username");


                    return new AuthData(myAuthToken, myUsername);
                }
            }
        }
        return null;
    }

    public void deleteAuth(AuthData auth) throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();
        try (var preparedStatement = conn.prepareStatement("DELETE FROM authData WHERE authToken=?")) {
            preparedStatement.setString(1, auth.authToken());
            preparedStatement.executeUpdate();
        }
    }

    public void clearAllAuth() throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();
        try (var preparedStatement = conn.prepareStatement("TRUNCATE authData")) {
            preparedStatement.executeUpdate();
        }
    }
}


