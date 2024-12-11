package client;

import chess.ChessBoard;
import dataaccess.DataAccessException;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.*;
import server.Server;
import service.ClearService;
import ui.ResponseException;
import ui.ServerFacade;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ServerFacadeTests {

    private static Server server;
    static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        var serverUrl = "http://localhost:" + port;
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade(serverUrl);
    }

    @BeforeEach
    public void clear() throws SQLException, DataAccessException {
        ClearService clearService = new ClearService(server.sqlGameDAO, server.sqlAuthDAO, server.sqlUserDAO);
        clearService.clearService();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

    @Test
    void goodRegister() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        var authData = facade.register(user);
        Assertions.assertTrue(authData.authToken() != null && authData.username() != null);
    }

    @Test
    void badRegister() throws Exception {
        UserData user = new UserData(null, null, null);
        assertThrows(ResponseException.class, ()->{
            var authData = facade.register(user);
        });
    }

    @Test
    void goodLogin() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        Assertions.assertTrue(authData.authToken() != null && authData.username() != null);
    }

    @Test
    void badLogin() throws Exception {
        UserData registerUser = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(registerUser);
        UserData loginUser = new UserData(null, null, null);
        assertThrows(ResponseException.class, ()->{
            var authData = facade.register(loginUser);
        });
    }

    @Test
    void goodCreateGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        assertDoesNotThrow(() -> facade.createGame("test", authData));
    }

    @Test
    void badCreateGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        assertThrows(ResponseException.class, ()->{
            facade.createGame(null, authData);
        });
    }

    @Test
    void goodListGames() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        GameData[] list = facade.listGames(authData);
        Assertions.assertTrue(list.length != 0);
    }

    @Test
    void badListGames() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        assertThrows(ResponseException.class, ()->{
            GameData[] list = facade.listGames(null);
        });
    }



    @Test
    void goodJoinGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        GameData[] list = facade.listGames(authData);
        int id = list[0].gameID();
        facade.joinGame(id, "WHITE", authData);
        list = facade.listGames(authData);
        Assertions.assertTrue(list[0].whiteUsername().equals("Alex"));
    }

    @Test
    void badJoinGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        GameData[] list = facade.listGames(authData);
        int id = list[0].gameID();
        assertThrows(ResponseException.class, ()->{
            facade.joinGame(id, "WHITE", null);
        });

    }

    @Test
    void goodObserveGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        GameData[] list = facade.listGames(authData);
        int id = list[0].gameID();
        ChessBoard board = facade.observeGame(id, authData);
        Assertions.assertTrue(facade.observeGame(id, authData) != null);


    }
    @Test
    void badObserveGame() throws Exception {
        UserData user = new UserData("Alex", "1234", "alex@gmail.com");
        facade.register(user);
        var authData = facade.login(user);
        facade.createGame("test", authData);
        GameData[] list = facade.listGames(authData);
        int id = list[0].gameID();
        assertThrows(ResponseException.class, ()->{
            facade.observeGame(id, null);
        });

    }

}
