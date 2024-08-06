package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;
import server.Server;
import service.ClearService;
import service.GameService;
import service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    @Test
    void addition() {
        assertEquals(2, add(1, 1));
    }


    @Test
    void goodRegister() throws DataAccessException {
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);

        UserData testUser = new UserData("username", "password", "email");

        AuthData testAuth =  userService.registerService(testUser);

        assertTrue(testAuth instanceof AuthData);

    }

    @Test
    void badRegister(){
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);

        UserData testUser = new UserData("", "password", "email");

        assertThrows(DataAccessException.class, ()->{
            AuthData testAuth =  userService.registerService(testUser);
        });
    }

    @Test
    void goodLogin() throws DataAccessException {
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        AuthData secondTestAuth = userService.loginService(testUser.username(), testUser.password());

        assertTrue(secondTestAuth instanceof AuthData);


    }

    @Test
    void badLogin() throws DataAccessException {
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());

        assertThrows(DataAccessException.class, ()->{
            AuthData secondTestAuth = userService.loginService("ayyoooo", testUser.password());
        });


    }

    @Test
    void goodLogout(){
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);
        UserData testUser = new UserData("username", "password", "email");
        AuthData testAuth =  userService.registerService(testUser);

        userService.logoutService(testAuth.authToken());


    }

    @Test
    void badLogout(){
        Server testserver = new Server();
        UserService userService = new UserService(testserver.memoryUserDAO, testserver.memoryAuthDAO);


    }

    @Test
    void goodCreateGame(){
        Server testserver = new Server();

    }

    @Test
    void badCreateGame(){
        Server testserver = new Server();

    }

    @Test
    void goodJoinGame(){
        Server testserver = new Server();

    }

    @Test
    void badJoinGame(){
        Server testserver = new Server();

    }

    @Test
    void goodListGame(){
        Server testserver = new Server();

    }

    @Test
    void badListGame(){
        Server testserver = new Server();

    }

    @Test
    void goodClear(){

    }



    public int add(int i, int j){
        return i + j;
    }
}


