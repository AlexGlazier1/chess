package dataaccess;

import model.AuthData;

import java.sql.Connection;
import java.sql.SQLException;
/*
public class SQLAuthDAO implements AuthDAO {

    public void createAuth(AuthData auth) throws SQLException, DataAccessException {
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("INSERT INTO AuthData (authToken, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, auth.authToken());
            preparedStatement.setString(2, auth.username());

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        }
        //DatabaseManager.getConnection();
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

 */
