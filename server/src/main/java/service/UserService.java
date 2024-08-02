package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import model.UserData;
import model.AuthData;
import model.GameData;

public class UserService {

    UserDAO userDAO;
    AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }


    public AuthData registerService(UserData user) throws DataAccessException {
        if(userDAO.readUser(user.username())){
            throw new DataAccessException("Username is already in use");
        }else{
            userDAO.createUser(user);

            return new AuthData(stringMaker(8), user.username());
        }
    }
    public AuthData loginService(UserData user) throws DataAccessException{

        if(userDAO.getMemoryUserMap().containsKey(user.username()) && userDAO.getMemoryUserMap().get(user.username()).equals(user.password())     ){
            return new AuthData(stringMaker(8), user.username());
        }else{
            throw new DataAccessException("Username or password is incorrect");
        }
    }

    public void logoutService(AuthData authData) throws DataAccessException {
        if(!authDAO.readAuth(authData.authToken())){
            throw new DataAccessException("Error: unauthorized");
        }else{
            authDAO.deleteAuth(authData);
        }
    }

    static String stringMaker(int n)
    {
        String characterList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        String sb = "";// = new StringBuilder(n);

        char s = characterList.charAt(2);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(characterList.length() * Math.random());

            // add Character one by one in end of sb
            sb = sb + (characterList.charAt(index));
        }

        return sb.toString();
    }
}