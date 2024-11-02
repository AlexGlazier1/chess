package dataaccess;

import com.google.gson.Gson;
import model.AuthData;
import model.UserData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SQLUserDAO implements UserDAO {

    public void createUser(UserData user)throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("INSERT INTO userData (username, password, email) VALUES(?, ?)")) {
            preparedStatement.setString(1, user.username());
            preparedStatement.setString(2, user.password());
            preparedStatement.setString(3, user.email());

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var ID = 0;
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        }

    }

    public boolean readUser(String username)throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT 1 FROM userData WHERE username = ? LIMIT 1;")) {
            preparedStatement.setString(1, username);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return true;
                }
                return false;
            }


        }
    }

    public void updateUser(UserData user){
    }

    public void deleteUser(UserData user){
    }

    public Map<String, UserData> getMemoryUserMap()throws SQLException, DataAccessException{
        Map<String, UserData> memoryUserMap = new HashMap<>();

        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("SELECT * FROM authData")) {
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    var myUsername = rs.getString("username");
                    var myPassword = rs.getString("password");
                    var myEmail = rs.getString("email");


                    UserData user = new UserData(myUsername, myPassword, myEmail);
                    memoryUserMap.put(user.username(), user);
                }
            }
        }
        return memoryUserMap;
    }

    public void clearAllUsers()throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();
        try (var preparedStatement = conn.prepareStatement("TRUNCATE userData")) {
            preparedStatement.executeUpdate();
        }
    }
}

