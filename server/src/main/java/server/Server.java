package server;

import java.util.Map;
import java.util.HashMap;

import model.GameData;
import model.UserData;
import model.AuthData;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    public static Map<String, String> memoryAuthMap = new HashMap<>();
    // memory auth dao -> map, key is authtoken,

    //Map of ID to record object
    public static Map<Integer, GameData> memoryGameMap = new HashMap<>();

    public static Map<String, UserData> memoryUserMap = new HashMap<>();
}
