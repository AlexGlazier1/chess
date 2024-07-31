package service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;
import model.AuthData;
import model.GameData;

public class UserService {

    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public AuthData registerService(UserData user) throws DataAccessException {
        if(userDAO.readUser(user.username())){
            throw new DataAccessException("Username is already in use");
        }else{
            userDAO.createUser(user);

            return new AuthData(stringMaker(8), user.username());
        }
    }
    public AuthData loginService(UserData user) {
        return null;
    }
    public void logoutService(UserData user) {}

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