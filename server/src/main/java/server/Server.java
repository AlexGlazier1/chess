package server;

import java.util.Map;
import java.util.HashMap;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import handler.*;
import model.GameData;
import model.UserData;
import model.AuthData;
import spark.*;

public class Server {
    public MemoryAuthDAO memoryAuthDAO = new MemoryAuthDAO();
    public MemoryGameDAO memoryGameDAO = new MemoryGameDAO();
    public MemoryUserDAO memoryUserDAO = new MemoryUserDAO();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //memoryAuthDAO.createAuth(new AuthData("Token", "Username"));
        //memoryGameDAO.createGame(new GameData(1, null, null, "temp", null));
        //memoryUserDAO.createUser(new UserData("User", "temp","temp"));


        clearHandler clearHandler = new clearHandler(memoryGameDAO, memoryAuthDAO, memoryUserDAO);
        Spark.delete("/db", clearHandler::clearDAOS);

        RegisterHandler registerHandler = new RegisterHandler(memoryUserDAO, memoryAuthDAO);
        Spark.post("/user", registerHandler::Register);

        loginHandler loginHandler = new loginHandler(memoryUserDAO, memoryAuthDAO);
        Spark.delete("/session", loginHandler::Login);

        logoutHandler logoutHandler = new logoutHandler(memoryUserDAO, memoryAuthDAO);
        Spark.delete("/session", logoutHandler::Logout);

        createGameHandler creategame = new createGameHandler(memoryGameDAO, memoryAuthDAO);
        Spark.post("/game", creategame::createGame);

        listGamesHandler listgamesHandler = new listGamesHandler(memoryGameDAO, memoryAuthDAO);
        Spark.get("/game", listgamesHandler::listGames);

        joinGameHandler joinGameHandler = new joinGameHandler(memoryAuthDAO, memoryGameDAO);
        Spark.put("/game", joinGameHandler::joinGame);

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    // memory auth dao -> map, key is authtoken,

    //Map of ID to record object

}
