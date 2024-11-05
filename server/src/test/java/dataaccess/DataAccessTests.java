package dataaccess;

import chess.*;
import dataaccess.DataAccessException;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import server.Server;
import service.ClearService;
import service.GameService;
import service.UserService;
import dataaccess.SQLGameDAO;
import service.ServiceTests;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessTests {

    Server testserver;

    UserService userService;
    GameService gameService;
    ClearService clearService;

    @BeforeEach
    void setUp() {
        testserver = new Server();

        userService = new UserService(testserver.sqlUserDAO, testserver.sqlAuthDAO);
        gameService = new GameService(testserver.sqlAuthDAO, testserver.sqlGameDAO);
        clearService = new ClearService(testserver.sqlGameDAO, testserver.sqlAuthDAO, testserver.sqlUserDAO);
    }


    @Test
    void goodRegister() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");

        AuthData testAuth =  userService.registerService(testUser);

        assertTrue(testAuth instanceof AuthData);

    }

    @Test
    void badRegister() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("", "password", "email");

        assertThrows(DataAccessException.class, ()->{
            AuthData testAuth =  userService.registerService(testUser);
        });
    }

    @Test
    void goodLogin() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        AuthData secondTestAuth = userService.loginService(testUser.username(), testUser.password());

        assertTrue(secondTestAuth instanceof AuthData);


    }

    @Test
    void badLogin() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        assertThrows(DataAccessException.class, ()->{
            AuthData secondTestAuth = userService.loginService("ayyoooo", testUser.password());
        });


    }

    @Test
    void goodLogout() throws SQLException, DataAccessException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        try {
            AuthData testAuth = userService.registerService(testUser);

            userService.logoutService(testAuth.authToken());

            //assertTrue(testserver.memoryAuthDAO.memoryAuthMap.isEmpty());
            assertTrue(testserver.sqlAuthDAO.getAuth(testAuth.authToken()) == null);
        }catch(DataAccessException e){
            assertTrue(false);
        }

    }

    @Test
    void badLogout() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        userService.registerService(testUser);

        assertThrows(DataAccessException.class, ()->{
            userService.logoutService("1298339842");
        });

    }

    @Test
    void goodCreateGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth = userService.registerService(testUser);

        GameData gameData = new GameData(1234, null, null, "testGame", new ChessGame());

        assertEquals(gameService.createGame(testAuth.authToken(), gameData).gameID(), 1234);





    }

    @Test
    void badCreateGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth = userService.registerService(testUser);

        GameData gameData = new GameData(1234, null, null, null, new ChessGame());

        assertThrows(DataAccessException.class, ()->{
            gameService.createGame(testAuth.authToken(), gameData);
        });
    }

    @Test
    void goodJoinGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        UserData testUser2 = new UserData("user2", "pass2", "email2");
        AuthData testAuth1 = userService.registerService(testUser1);
        AuthData testAuth2 = userService.registerService(testUser2);

        GameData gameData = new GameData(1234, null, null, "testGame", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData);

        gameService.joinGame(testAuth1.authToken(),"WHITE", 1234);
        gameService.joinGame(testAuth2.authToken(),"BLACK", 1234);

        assertEquals(testserver.sqlGameDAO.listGames().size(), 1);
        assertEquals(testserver.sqlGameDAO.getGame(1234).whiteUsername(), "user1");
        assertEquals(testserver.sqlGameDAO.getGame(1234).blackUsername(), "user2");

    }

    @Test
    void badJoinGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        UserData testUser2 = new UserData("user2", "pass2", null);
        AuthData testAuth1 = userService.registerService(testUser1);

        GameData gameData = new GameData(1234, null, null, "testGame", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData);


        gameService.joinGame(testAuth1.authToken(),"WHITE", 1234);

        assertThrows(DataAccessException.class, ()->{
            AuthData testAuth2 = userService.registerService(testUser2);
            gameService.joinGame(testAuth2.authToken(),"BLACK", 1234);

        });

    }

    @Test
    void goodListGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        AuthData testAuth1 = userService.registerService(testUser1);

        GameData gameData1 = new GameData(1234, null, null, "testGame1", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData1);

        GameData gameData2 = new GameData(5678, null, null, "testGame2", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData2);

        GameData gameData3 = new GameData(0001, null, null, "testGame3", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData3);

        GameData gameData4 = new GameData(1000, null, null, "testGame4", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData4);


        assertEquals(testserver.sqlGameDAO.listGames().size(), 4);

    }

    @Test
    void badListGame() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        AuthData testAuth1 = userService.registerService(testUser1);

        GameData gameData1 = new GameData(5678, null, null, "testGame1", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData1);

        GameData gameData2 = new GameData(1234, null, null, "testGame2", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData2);

        GameData gameData3 = new GameData(1000, null, null, "testGame3", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData3);

        GameData gameData4 = new GameData(0001, null, null, "testGame4", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData4);


        assertEquals(testserver.sqlGameDAO.listGames().size(), 4);


    }

    @Test
    void goodUpdateGame() throws DataAccessException, SQLException, InvalidMoveException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        UserData testUser2 = new UserData("user2", "pass2", "email2");
        AuthData testAuth1 = userService.registerService(testUser1);
        AuthData testAuth2 = userService.registerService(testUser2);

        ChessGame chess = new ChessGame();
        chess.getBoard().resetBoard();
        chess.setTeamTurn(ChessGame.TeamColor.WHITE);

        GameData gameData1 = new GameData(1234, null, null, "testGame", chess);
        gameService.createGame(testAuth1.authToken(), gameData1);

        gameService.joinGame(testAuth1.authToken(),"WHITE", 1234);
        gameService.joinGame(testAuth2.authToken(),"BLACK", 1234);

        ChessMove move = new ChessMove(new ChessPosition(2,1), new ChessPosition(4, 1), null);
        chess.makeMove(move);
        GameData gameData2 = new GameData(1234, "user1", "user2", "testGame", chess);


        SQLGameDAO updating = new SQLGameDAO();
        updating.updateGame(gameData2);

        ChessGame updatedGame = updating.getGame(1234).game();
        assertTrue(updatedGame.getBoard().getPiece(new ChessPosition(4, 1)).getPieceType().equals(ChessPiece.PieceType.PAWN));

    }

    @Test
    void badUpdateGame() throws DataAccessException, SQLException, InvalidMoveException {
    clearService.clearService();

    UserData testUser1 = new UserData("user1", "pass1", "email1");
    UserData testUser2 = new UserData("user2", "pass2", "email2");
    AuthData testAuth1 = userService.registerService(testUser1);
    AuthData testAuth2 = userService.registerService(testUser2);

    ChessGame chess = new ChessGame();
        chess.getBoard().resetBoard();
        chess.setTeamTurn(ChessGame.TeamColor.WHITE);

    GameData gameData1 = new GameData(1234, null, null, "testGame", chess);
        gameService.createGame(testAuth1.authToken(), gameData1);

        gameService.joinGame(testAuth1.authToken(),"WHITE", 1234);
        gameService.joinGame(testAuth2.authToken(),"BLACK", 1234);

    ChessMove move = new ChessMove(new ChessPosition(2,1), new ChessPosition(4, 1), null);
    //chess.makeMove(move);
    GameData gameData2 = new GameData(1234, "user1", "user2", "testGame", chess);


    SQLGameDAO updating = new SQLGameDAO();
        updating.updateGame(gameData2);

    ChessGame updatedGame = updating.getGame(1234).game();

    assertThrows(NullPointerException.class, ()->{
        assertTrue(updatedGame.getBoard().getPiece(new ChessPosition(4, 1)).getPieceType().equals(null));
    });
    }

    @Test
    void goodClear() throws DataAccessException, SQLException {
        clearService.clearService();

        UserData testUser1 = new UserData("user1", "pass1", "email1");
        AuthData testAuth1 = userService.registerService(testUser1);

        GameData gameData1 = new GameData(1234, null, null, "testGame1", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData1);

        GameData gameData2 = new GameData(5678, null, null, "testGame2", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData2);

        assertFalse(testserver.sqlGameDAO.listGames().isEmpty());
        assertFalse(testserver.sqlUserDAO.getMemoryUserMap().isEmpty());
        assertEquals(testserver.sqlAuthDAO.getUsername(testAuth1.authToken()), testUser1.username());

        clearService.clearService();

        assertTrue(testserver.sqlGameDAO.listGames().isEmpty());
        assertTrue(testserver.sqlUserDAO.getMemoryUserMap().isEmpty());
        assertThrows(NullPointerException.class, ()->{
            testserver.sqlAuthDAO.getUsername(testAuth1.authToken()).equals(testUser1.username());
        });
    }

}
