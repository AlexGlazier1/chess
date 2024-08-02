package service;
import dataaccess.DataAccessException;

import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.UserData;
import model.AuthData;
import model.GameData;
import dataaccess.AuthDAO;

import java.util.ArrayList;

public class GameService {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public GameService(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }


    public void joinGame(AuthData auth) throws dataaccess.DataAccessException {
        if(!authDAO.readAuth(auth.authToken())){
            throw new DataAccessException("Error: unauthorized");
        }else{try {
            gameDAO.getGame();
            GameData update = new GameData();
            gameDAO.updateGame(update);
            }catch(Exception e){
                throw new DataAccessException("Error: Game not found");
            }
        }
    }
    public GameData createGame(AuthData auth, GameData game) throws dataaccess.DataAccessException{
        if(!authDAO.readAuth(auth.authToken())){
            throw new DataAccessException("Error: unauthorized");
        }else{try{
            gameDAO.createGame(game);
            return game;
        }catch(Exception e){
            throw new DataAccessException("Error: unauthorized");
        }
        }
    }
    public ArrayList<GameData> listGames(AuthData auth) throws dataaccess.DataAccessException {
        if(!authDAO.readAuth(auth.authToken())){
            throw new DataAccessException("Error: unauthorized");
        }else{try{
            ArrayList<GameData> Games  = new ArrayList<>();
            for(GameData game: gameDAO.listGames().values() ){
                Games.add(game);
            }
            return Games;
        }catch(Exception e){
            throw new DataAccessException("Error: unauthorized");
            }
        }

    }

}
