package server;

import java.util.Map;

import com.google.gson.Gson;
import dataaccess.*;
import handler.*;
import spark.*;

public class Server {
    public MemoryAuthDAO memoryAuthDAO = new MemoryAuthDAO();
    public MemoryGameDAO memoryGameDAO = new MemoryGameDAO();
    public MemoryUserDAO memoryUserDAO = new MemoryUserDAO();

    public SQLAuthDAO sqlAuthDAO = new SQLAuthDAO();
    public SQLGameDAO sqlGameDAO = new SQLGameDAO();
    public SQLUserDAO sqlUserDAO = new SQLUserDAO();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        try {
            DatabaseManager.createDatabase();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        // Register your endpoints and handle exceptions here.

        handlerMaker(sqlGameDAO, sqlAuthDAO, sqlUserDAO);

        Spark.exception(Exception.class, (exception, req, res) -> {
            res.body(new Gson().toJson(Map.of("message", "Error: " + exception.getMessage())));
            switch (exception.getMessage()) {
                case "Error: bad request":
                    res.status(400);
                    break;

                case "Error: unauthorized":
                    res.status(401);
                    break;

                case "Error: already taken":
                    res.status(403);
                    break;

                default:
                    res.status(500);
                    break;
            }


        });

        //This line initializes the server and can be removed once you have a functioning endpoint
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void handlerMaker(GameDAO myGame, AuthDAO myAuth, UserDAO myUser) {
        ClearHandler clearHandler = new ClearHandler(myGame, myAuth, myUser);
        Spark.delete("/db", clearHandler::clearDAOS);

        RegisterHandler registerHandler = new RegisterHandler(myUser, myAuth);
        Spark.post("/user", registerHandler::register);

        LoginHandler loginHandler = new LoginHandler(myUser, myAuth);
        Spark.post("/session", loginHandler::login);

        LogoutHandler logoutHandler = new LogoutHandler(myUser, myAuth);
        Spark.delete("/session", logoutHandler::logout);

        CreateGameHandler creategame = new CreateGameHandler(myGame, myAuth);
        Spark.post("/game", creategame::createGame);

        ListGamesHandler listgamesHandler = new ListGamesHandler(myGame, myAuth);
        Spark.get("/game", listgamesHandler::listGames);

        JoinGameHandler joinGameHandler = new JoinGameHandler(myAuth, myGame);
        Spark.put("/game", joinGameHandler::joinGame);
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    // memory auth dao -> map, key is authtoken,

    //Map of ID to record object

}