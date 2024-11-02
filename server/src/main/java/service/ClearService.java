package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

import java.sql.SQLException;

public class ClearService {

    GameDAO gameData;
    AuthDAO authData;
    UserDAO userData;

    public ClearService(GameDAO gameData, AuthDAO authData, UserDAO userData) {
        this.gameData = gameData;
        this.authData = authData;
        this.userData = userData;
    }

    public String clearService() throws SQLException, DataAccessException {

        gameData.clearAllGames();
        authData.clearAllAuth();
        userData.clearAllUsers();
        return "";
    }



}
