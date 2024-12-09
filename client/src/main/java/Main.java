import chess.*;
import ui.Repl;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        //heSystem.out.println("♕ 240 Chess Client: " + piece);

        var serverUrl = "http://localhost:8080";
        new Repl(serverUrl).run();
    }
}