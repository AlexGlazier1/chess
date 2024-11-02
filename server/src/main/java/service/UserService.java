package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

import java.sql.SQLException;

public class UserService {

    UserDAO userDAO;
    AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }


    public AuthData registerService(UserData user) throws DataAccessException, SQLException {
        if(userDAO.readUser(user.username())){
            throw new DataAccessException("Error: already taken");
        }else if(user.username() == null || user.username().isEmpty()){
            throw new DataAccessException("Error: bad request");
        }else if(user.password() == null || user.password().isEmpty()){
            throw new DataAccessException("Error: bad request");
        } else if(user.email() == null || user.email().isEmpty()){
            throw new DataAccessException("Error: bad request");
        }
        else{
            userDAO.createUser(user);
            AuthData userAuth = new AuthData(makeString(8), user.username());
            authDAO.createAuth(userAuth);

            return userAuth;
        }
    }
    public AuthData loginService(String username, String password) throws DataAccessException, SQLException {


        if(userDAO.getMemoryUserMap().containsKey(username) && userDAO.getMemoryUserMap().get(username).password().equals(password)   ){
            AuthData userAuth = new AuthData(makeString(8), username);
            authDAO.createAuth(userAuth);
            return userAuth;
        }else{
            throw new DataAccessException("Error: unauthorized");
        }
    }

    public void logoutService(String authtoken) throws SQLException, DataAccessException {

        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");
        }else{
            authDAO.deleteAuth(authDAO.getAuth(authtoken));
        }

    }

    static String makeString(int n)
    {
        String characterList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder();// = new StringBuilder(n);


        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(characterList.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(characterList.charAt(index));
        }

        return sb.toString();
    }
}