package ui;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import chess.ChessBoard;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;


public class ServerFacade {
    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public AuthData register(UserData userData) throws ResponseException {
        var path = "/user";
        return this.makeRequest("POST", path, userData, AuthData.class, null);
    }

    public AuthData login(UserData userData) throws ResponseException {
        var path = "/session";
        return this.makeRequest("POST", path, userData, AuthData.class, null);
    }

    record CreateGameRequest(String gameName) {}

    public void createGame(String name, AuthData authData) throws ResponseException {
        var path = "/game";
        //System.out.println("Request Payload: " + new Gson().toJson(new CreateGameRequest(name)));
        this.makeRequest("POST", path, new CreateGameRequest(name), null, authData);
    }

    public GameData[] listGames(AuthData authData) throws ResponseException {
        var path = "/game";
        record ListGamesResponse(GameData[] games) {
        }
        var response = this.makeRequest("GET", path, null, ListGamesResponse.class, authData);
        return response.games();
    }

    public GameData joinGame(int gameID, String teamColor, AuthData authData) throws ResponseException {
        var path = "/game";
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("gameID", gameID);
        requestData.put("playerColor", teamColor.toUpperCase());

        var response = this.makeRequest("PUT", path, requestData, GameData.class, authData);
        return response;
    }

    public ChessBoard observeGame(int gameID, AuthData authData) throws ResponseException {
        var path = "/game";
        record ListGamesResponse(GameData[] games) {
        }
        var response = this.makeRequest("GET", path, null, ListGamesResponse.class, authData);
        GameData[] games = response.games();
        for (GameData g: games) {
            if (g.gameID() == gameID){
                return g.game().getBoard();
            }
        }
        return null;
    }


    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass, AuthData authData) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            if(authData != null){
                http.addRequestProperty("Authorization", authData.authToken());
            }
            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (ResponseException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            try (InputStream respErr = http.getErrorStream()) {
                if (respErr != null) {
                    throw ResponseException.fromJson(respErr, status);
                }
            }

            throw new ResponseException(status, "other failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}
