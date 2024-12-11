package ui;

import chess.ChessBoard;
import com.google.gson.Gson;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.AuthData;
import model.GameData;
import model.UserData;


public class ChessClient {

    private String visitorName = null;
    private final ServerFacade server;
    private final String serverUrl;
    Map<Integer, GameData> gameMap = new HashMap<>();
    //private final NotificationHandler notificationHandler;
    private State state = State.SIGNEDOUT;
    private AuthData authData;

    public ChessClient(String serverUrl) {
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
                    case "observe" -> observeGame(params);
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
            visitorName = params[0];
            var userData = new UserData(params[0],params[1],"null");
            try{
                authData = server.login(userData);
                state = State.SIGNEDIN;
            }catch(Exception e) {
                throw new ResponseException(400, "This user is not registered");
            }

            return String.format("You signed in as %s.", visitorName);
        }
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD>");
    }

    public String register(String... params) throws ResponseException {
        if (params.length >= 3) {
            visitorName = params[0];
            var userData = new UserData(params[0],params[1],params[2]);
            try{
                authData = server.register(userData);
                state = State.SIGNEDIN;
            }catch(Exception e) {
            throw new ResponseException(400, "This user is already registered>");
            }

            return String.format("You signed in as %s.", visitorName);
        }else{
        throw new ResponseException(400, "Expected: <USERNAME> <PASSWORD> <EMAIL>");
        }
    }

    public String listGame(String...params) throws ResponseException {
        if (params.length == 0) {
            //try {
                GameData[] games = server.listGames(authData);
                //return String.format("You signed in as %s.", visitorName);
                System.out.println("Game Number: Game Name, White, Black");
                int loopInt = 1;
                for (GameData g: games) {
                    System.out.println(loopInt + ":" + g.gameName() + ", "+ g.whiteUsername() + ", "+ g.blackUsername());
                    gameMap.put(loopInt,g);
                    loopInt++;

                }
                return "end of list";
        } else {
            throw new ResponseException(400, "Expected: No other parameters");
        }
    }

    public String createGame(String...params) throws ResponseException {
        if (params.length == 0){
            try{
                server.createGame(params[0], authData);
                return String.format("Chess game ", params[0]," created.");
            }catch(Exception e) {
                throw new ResponseException(400, "This game already exists>");
            }
        } else {
            throw new ResponseException(400, "Expected: No other parameters");

        }
    }

    public String joinGame(String...params) throws ResponseException {
        if (params.length >= 1) {
            String gameName = "";
            try{
                GameData gameToJoin = server.joinGame(gameMap.get(params[1]),params[2], authData);
                gameName = gameToJoin.gameName();
            }catch(Exception e) {
                throw new ResponseException(400, "This user is already registered>");
            }

            return String.format("You joined %s.", gameName);
        }else{
            throw new ResponseException(400, "Expected: <ID> [WHITE/BLACK]");
        }
    }


    public String observeGame(String...params) throws ResponseException {
        if (params.length <= 1) {
            int num = Integer.parseInt(params[0]);
            var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
            ChessBoard board = gameMap.get(num).game().getBoard();
            new Board(board,"White").drawBoard(out);
            return String.format("You viewed %s.", gameMap.get(num).gameName());

        } else {
            throw new ResponseException(400, "Provide game number");
        }
    }




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
