import chess.*;
import org.mindrot.jbcrypt.BCrypt;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);

        Server test = new Server();
        test.run(8080);

        //String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());


        //System.out.println(hashedPassword);
        //System.out.println(BCrypt.checkpw("password", hashedPassword));
    }
}