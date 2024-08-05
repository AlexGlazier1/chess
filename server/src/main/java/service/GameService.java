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


    public void joinGame(String authtoken, GameData Game) throws dataaccess.DataAccessException {
        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");
        }else if(Game.blackUsername() != null && Game.whiteUsername() != null){
            throw new dataaccess.DataAccessException("Error: already taken");
        }else if(!gameDAO.readGame(Game) ){
            throw new dataaccess.DataAccessException("Error: bad request");
        }else if(gameDAO.getGame(Game.gameID()).blackUsername() != null && Game.blackUsername() != null){
            throw new dataaccess.DataAccessException("Error: unknown");
        }else if(gameDAO.getGame(Game.gameID()).whiteUsername() != null && Game.whiteUsername() != null){
            throw new dataaccess.DataAccessException("Error: unknown");
        }else{
            //GameData tempGame = gameDAO.getGame(Game.gameID());
            //GameData update = new GameData();
            gameDAO.updateGame(Game);
        }

        /*
        {try {
            gameDAO.getGame();
            GameData update = new GameData();
            gameDAO.updateGame(update);
            }catch(Exception e){
                throw new DataAccessException("Error: Game not found");
            }
        }
         */
    }
    public GameData createGame(String authtoken, GameData game) throws dataaccess.DataAccessException{
        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");
        }else if(game.gameName() == null || game.gameName().isEmpty()){
            throw new DataAccessException("Error: bad request");
        }else if(game.gameName().length() < 2){
            throw new DataAccessException("Error: unknown");
        }else{
            gameDAO.createGame(game);
            return game;
        }

        /*
        {try{
            gameDAO.createGame(game);
            return game;
        }catch(Exception e){
            throw new DataAccessException("Error: unauthorized");
        }
        }
         */
    }
    /////////MAKE SURE IT IS RECIEVING THE ACTUAL AUTHTOKEN STRING AND LOSE THE TRY CATCH BLOCK
    public ArrayList<GameData> listGames(String authtoken) throws dataaccess.DataAccessException {
        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");
        }else if(authtoken == null) {
            throw new DataAccessException("Error: unknown");
        }else {
            ArrayList<GameData> Games = new ArrayList<>();
            for (GameData game : gameDAO.listGames().values()) {
                Games.add(game);
            }
            return Games;
        }

            /*
            try{

        }catch(Exception e){
            throw new DataAccessException("Error: unauthorized");
            }
        }*/

    }

}
