package handler;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import spark.*;
import dataaccess.UserDAO;
import service.ClearService;

public class clearHandler {

    GameDAO gameDAO;
    AuthDAO authDAO;
    UserDAO userDAO;

    public clearHandler(GameDAO gameData, AuthDAO authData, UserDAO userData) {
        this.gameDAO = gameData;
        this.authDAO = authData;
        this.userDAO = userData;
    }


    public String clearDAOS(Request req, Response res){
        ClearService Clear = new ClearService(gameDAO, authDAO, userDAO);
        try {
            Clear.clearService();
            res.status(200);
            return "";
        }catch (Exception e){
            res.status(500);
        }
        return "";

    }
}
