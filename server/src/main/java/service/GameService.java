package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameService {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public GameService(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }


    public void joinGame(String authtoken, String playerColor, int gameID) throws DataAccessException {

        GameData oldGame = gameDAO.getGame(gameID);
        String username = authDAO.getUsername(authtoken);
        String blackName = gameDAO.getGame(gameID).blackUsername();
        String whiteName = gameDAO.getGame(gameID).whiteUsername();
        String gameName = gameDAO.getGame(gameID).gameName();
        ChessGame chessGame = gameDAO.getGame(gameID).game();

        GameData newGame = new GameData(gameID, null, null, gameName, chessGame);

        if(playerColor.equals("BLACK")){
            newGame = new GameData(gameID, whiteName, username, gameName, chessGame);
        }
        if(playerColor.equals("WHITE")){
            newGame = new GameData(gameID, username, blackName, gameName, chessGame);
        }


        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");

        }else if(oldGame.blackUsername() != null && oldGame.whiteUsername() != null){
            throw new DataAccessException("Error: already taken");

        }else if(playerColor.equals("BLACK") && oldGame.blackUsername() != null){
            throw new DataAccessException("Error: already taken");

        }else if(playerColor.equals("WHITE") && oldGame.whiteUsername() != null){
            throw new DataAccessException("Error: already taken");

        }else if(!gameDAO.readGame(oldGame) ){
            throw new DataAccessException("Error: bad request");
        }else{
            gameDAO.updateGame(newGame);
        }
    }
    public GameData createGame(String authtoken, GameData game) throws DataAccessException, SQLException {
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

    }
    /////////MAKE SURE IT IS RECIEVING THE ACTUAL AUTHTOKEN STRING AND LOSE THE TRY CATCH BLOCK
    public ArrayList<GameData> listGames(String authtoken) throws DataAccessException {
        if(!authDAO.readAuth(authtoken)){
            throw new DataAccessException("Error: unauthorized");
        }else if(authtoken == null) {
            throw new DataAccessException("Error: unknown");
        }else {
            ArrayList<GameData> games = new ArrayList<>();
            for (GameData game : gameDAO.listGames().values()) {
                games.add(game);
            }
            return games;
        }

    }

}
