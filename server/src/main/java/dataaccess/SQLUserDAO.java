package dataaccess;

import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SQLUserDAO implements UserDAO {

    public void createUser(UserData user)throws SQLException, DataAccessException{
        Connection conn = DatabaseManager.getConnection();

        try (var preparedStatement = conn.prepareStatement("INSERT INTO userData (username, password, email) VALUES(?, ?, ?)",
                                                                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.username());
            String hashedPassword = BCrypt.hashpw(user.password(), BCrypt.gensalt());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, user.email());

            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            var id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }

    }

    public boolean readUser(String username)throws SQLException, DataAccessException{
        try(Connection conn = DatabaseManager.getConnection()) {

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
    }

    //Turns out I don't need these either: public void updateUser(UserData user){}

    //This guy isn't used as well: public void deleteUser(UserData user){}

    public Map<String, UserData> getMemoryUserMap()throws SQLException, DataAccessException{
        Map<String, UserData> sqlUserMap = new HashMap<>();

        try(Connection conn = DatabaseManager.getConnection()) {

            try (var preparedStatement = conn.prepareStatement("SELECT * FROM userData")) {
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        var myUsername = rs.getString("username");
                        var myPassword = rs.getString("password");
                        var myEmail = rs.getString("email");


                        UserData user = new UserData(myUsername, myPassword, myEmail);
                        sqlUserMap.put(user.username(), user);
                    }
                }
            }
        }
        return sqlUserMap;
    }

    public void clearAllUsers()throws SQLException, DataAccessException {
        try (Connection conn = DatabaseManager.getConnection()) {
            try (var preparedStatement = conn.prepareStatement("TRUNCATE userData")) {
                preparedStatement.executeUpdate();
            }
        }
    }
}

