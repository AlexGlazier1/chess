package dataaccess;

import com.google.gson.Gson;
import model.UserData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;


public class SQLUserDAO implements UserDAO {

    public void createUser(UserData user)throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("INSERT INTO UserData (username, password, email) VALUES(?, ?)")) {
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

    public boolean readUser(String username){
        return true;
    }

    public void updateUser(UserData user){
    }

    public void deleteUser(UserData user){
    }

    public Map<String, UserData> getMemoryUserMap(){
        return null;
    }

    public void clearAllUsers()throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();
        try (var preparedStatement = conn.prepareStatement("TRUNCATE userdata")) {
            preparedStatement.executeUpdate();
        }
    }
}

