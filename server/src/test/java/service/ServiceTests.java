package service;

import chess.ChessGame;
import dataaccess.DataAccessException;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import server.Server;
import service.ClearService;
import service.GameService;
import service.UserService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    Server testserver;

    UserService userService;
    GameService gameService;
    ClearService clearService;

    @BeforeEach
    void setUp() {
        testserver = new Server();

        userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);
        gameService = new GameService(testserver.memoryAuthDAO, testserver.memoryGameDAO);
        clearService = new ClearService(testserver.memoryGameDAO, testserver.memoryAuthDAO, testserver.memoryUserDAO);
    }


    @Test
    void goodRegister() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");

        AuthData testAuth =  userService.registerService(testUser);

        assertTrue(testAuth instanceof AuthData);

    }

    @Test
    void badRegister() throws DataAccessException {
        UserData testUser = new UserData("", "password", "email");

        assertThrows(DataAccessException.class, ()->{
            AuthData testAuth =  userService.registerService(testUser);
        });
    }

    @Test
    void goodLogin() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        AuthData secondTestAuth = userService.loginService(testUser.username(), testUser.password());

        assertTrue(secondTestAuth instanceof AuthData);


    }

    @Test
    void badLogin() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        assertThrows(DataAccessException.class, ()->{
            AuthData secondTestAuth = userService.loginService("ayyoooo", testUser.password());
        });


    }

    @Test
    void goodLogout() throws SQLException {
        UserData testUser = new UserData("username", "password", "email");
        try {
            AuthData testAuth = userService.registerService(testUser);

            userService.logoutService(testAuth.authToken());

            assertTrue(testserver.memoryAuthDAO.memoryAuthMap.isEmpty());
        }catch(DataAccessException e){
            assertTrue(false);
        }

    }

    @Test
    void badLogout() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");
        userService.registerService(testUser);

        assertThrows(DataAccessException.class, ()->{
            userService.logoutService("1298339842");
        });

    }

    @Test
    void goodCreateGame() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth = userService.registerService(testUser);

        GameData gameData = new GameData(1234, null, null, "testGame", new ChessGame());

        assertEquals(gameService.createGame(testAuth.authToken(), gameData).gameID(), 1234);





    }

    @Test
    void badCreateGame() throws DataAccessException, SQLException {
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth = userService.registerService(testUser);

        GameData gameData = new GameData(1234, null, null, null, new ChessGame());

        assertThrows(DataAccessException.class, ()->{
            gameService.createGame(testAuth.authToken(), gameData);
        });
    }

    @Test
    void goodJoinGame() throws DataAccessException, SQLException {
        UserData testUser1 = new UserData("user1", "pass1", "email1");
        UserData testUser2 = new UserData("user2", "pass2", "email2");
        AuthData testAuth1 = userService.registerService(testUser1);
        AuthData testAuth2 = userService.registerService(testUser2);

        GameData gameData = new GameData(1234, null, null, "testGame", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData);

        gameService.joinGame(testAuth1.authToken(),"WHITE", 1234);
        gameService.joinGame(testAuth2.authToken(),"BLACK", 1234);

        assertEquals(testserver.memoryGameDAO.memoryGameMap.size(), 1);
        assertEquals(testserver.memoryGameDAO.memoryGameMap.get(1234).whiteUsername(), "user1");
        assertEquals(testserver.memoryGameDAO.memoryGameMap.get(1234).blackUsername(), "user2");



    }

    @Test
    void badJoinGame() throws DataAccessException, SQLException {
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


        assertEquals(testserver.memoryGameDAO.memoryGameMap.size(), 4);

    }

    @Test
    void badListGame() throws DataAccessException, SQLException {
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


        assertEquals(testserver.memoryGameDAO.memoryGameMap.size(), 4);


    }

    @Test
    void goodClear() throws DataAccessException, SQLException {
        UserData testUser1 = new UserData("user1", "pass1", "email1");
        AuthData testAuth1 = userService.registerService(testUser1);

        GameData gameData1 = new GameData(1234, null, null, "testGame1", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData1);

        GameData gameData2 = new GameData(5678, null, null, "testGame2", new ChessGame());
        gameService.createGame(testAuth1.authToken(), gameData2);

        assertTrue(!testserver.memoryGameDAO.memoryGameMap.isEmpty());
        assertTrue(!testserver.memoryAuthDAO.memoryAuthMap.isEmpty());
        assertTrue(!testserver.memoryUserDAO.memoryUserMap.isEmpty());

        clearService.clearService();

        assertTrue(testserver.memoryGameDAO.memoryGameMap.isEmpty());
        assertTrue(testserver.memoryAuthDAO.memoryAuthMap.isEmpty());
        assertTrue(testserver.memoryUserDAO.memoryUserMap.isEmpty());
    }
}


