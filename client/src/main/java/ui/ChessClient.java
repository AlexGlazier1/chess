package ui;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.GameData;
import model.UserData;
import ui.ResponseException;
import ui.ServerFacade;

public class ChessClient {

    private String visitorName = null;
    private final ServerFacade server;
    private final String serverUrl;
    Map<Integer, Integer> gameMap = new HashMap<>();
    //private final NotificationHandler notificationHandler;
    //private WebSocketFacade ws;
    private State state = State.SIGNEDOUT;

    public ChessClient(String serverUrl) {//NotificationHandler notificationHandler) {
        server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
        //this.notificationHandler = notificationHandler;
    }

    public String eval(String input) {
        if (state == State.SIGNEDOUT) {
            try {
                var tokens = input.toLowerCase().split(" ");
                var cmd = (tokens.length > 0) ? tokens[0] : "help";
                var params = Arrays.copyOfRange(tokens, 1, tokens.length);
                return switch (cmd) {

                    case "login" -> logIn(params);
                    case "register" -> register(params);
                    case "quit" -> "quit";
                    default -> help();
                };
            } catch (ResponseException ex) {
                return ex.getMessage();
            }
        } else {
            try {
                var tokens = input.toLowerCase().split(" ");
                var cmd = (tokens.length > 0) ? tokens[0] : "help";
                var params = Arrays.copyOfRange(tokens, 1, tokens.length);
                return switch (cmd) {

                    case "login" -> logIn(params);
                    case "create" -> createGame(params);
                    case "list" -> listGame(params);
                    //case "join" -> joinGame(params);
                    //case "observe" -> observeGame(params);
                    //case "logout" -> logout(params);
                    case "quit" -> "quit";
                    default -> help();
                };
            } catch (ResponseException ex) {
                return ex.getMessage();
            }
        }
    }

    public String logIn(String... params) throws ResponseException {
        if (params.length >= 2) {
            visitorName = String.join("-", params);
            var userData = new UserData(params[0],params[1],"null");
            try{
                server.login(userData);
                state = State.SIGNEDIN;
            }catch(Exception e) {
                throw new ResponseException(400, "This user is not registered");
            }


            //ws = new WebSocketFacade(serverUrl, notificationHandler);
            //ws.enterPetShop(visitorName);
            return String.format("You signed in as %s.", visitorName);
        }
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD>");
    }

    public String register(String... params) throws ResponseException {
        if (params.length >= 3) {
            visitorName = String.join("-", params);
            var userData = new UserData(params[0],params[1],params[2]);
            try{
                server.register(userData);
                state = State.SIGNEDIN;
            }catch(Exception e) {
            throw new ResponseException(400, "This user is already registered>");
            }
            //ws = new WebSocketFacade(serverUrl, notificationHandler);
            //ws.enterPetShop(visitorName);
            return String.format("You signed in as %s.", visitorName);
        }else{
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD> <EMAIL>");
        }
    }

    public String listGame(String...params) throws ResponseException {
        if (params.length == 0) {
            //try {
                GameData[] games = server.listGames();
                //return String.format("You signed in as %s.", visitorName);
                System.out.println("Game Number: Game Name, GameID, White, Black");
                int loopInt = 1;
                for (GameData g: games) {
                    System.out.print(loopInt + ":" + g.gameName() + ", "+ g.gameID() + ", "+ g.whiteUsername() + ", "+ g.blackUsername());
                    loopInt++;
                    gameMap.put(loopInt,g.gameID());
                }
                return "end of list";

            //} catch (Exception e) {
            //    throw new ResponseException(400, "This user is already registered>");
            //}
        } else {
            throw new ResponseException(400, "Expected: No other parameters");
        }
    }

    public String createGame(String...params) throws ResponseException {
        if (params.length >= 0){
            //GameData gameData = new GameData()
            server.createGame(params[0]);
            return String.format("Chess game ", params[0]," created.");
            //try{
            //    server.createGame(params[0]);
            //    return String.format("Chess game ", params[0]," created.");
            //}catch(Exception e) {
            //    throw new ResponseException(400, "This user is already registered>");
            //}
        //}else{
        //    System.out.println(params.length);
        //    throw new ResponseException(400, "Expected: No other parameters");

        }else{
            return "uggh";
        }
    }
    /*

    public String rescuePet(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length >= 2) {
            var name = params[0];
            var type = PetType.valueOf(params[1].toUpperCase());
            var pet = new Pet(0, name, type);
            pet = server.addPet(pet);
            return String.format("You rescued %s. Assigned ID: %d", pet.name(), pet.id());
        }
        throw new ResponseException(400, "Expected: <name> <CAT|DOG|FROG>");
    }

    public String listPets() throws ResponseException {
        assertSignedIn();
        var pets = server.listPets();
        var result = new StringBuilder();
        var gson = new Gson();
        for (var pet : pets) {
            result.append(gson.toJson(pet)).append('\n');
        }
        return result.toString();
    }

    public String adoptPet(String... params) throws ResponseException {
        assertSignedIn();
        if (params.length == 1) {
            try {
                var id = Integer.parseInt(params[0]);
                var pet = getPet(id);
                if (pet != null) {
                    server.deletePet(id);
                    return String.format("%s says %s", pet.name(), pet.sound());
                }
            } catch (NumberFormatException ignored) {
            }
        }
        throw new ResponseException(400, "Expected: <pet id>");
    }

    public String adoptAllPets() throws ResponseException {
        assertSignedIn();
        var buffer = new StringBuilder();
        for (var pet : server.listPets()) {
            buffer.append(String.format("%s says %s%n", pet.name(), pet.sound()));
        }

        server.deleteAllPets();
        return buffer.toString();
    }

    public String signOut() throws ResponseException {
        assertSignedIn();
        ws.leavePetShop(visitorName);
        ws = null;
        state = State.SIGNEDOUT;
        return String.format("%s left the shop", visitorName);
    }

    private Pet getPet(int id) throws ResponseException {
        for (var pet : server.listPets()) {
            if (pet.id() == id) {
                return pet;
            }
        }
        return null;
    }
    */
        public String help() {
            if (state == State.SIGNEDOUT) {
                return """
                        - register <USERNAME> <PASSWORD> <EMAIL> - to create an account
                        - login <USERNAME> <PASSWORD> - to play chess
                        - quit - playing chess
                        - help - with possible commands
                        """;
            }
            return """
                    - create <NAME> - a game
                    - list - games
                    - join <ID> [WHITE/BLACK] - a game
                    - observe <ID> - a game
                    - logout - when you are done
                    - quit - playing chess
                    - help - with possible commands
                    """;
        }

    private void assertSignedIn() throws ResponseException {
        if (state == State.SIGNEDOUT) {
            throw new ResponseException(400, "You must sign in");
        }
    }



}
