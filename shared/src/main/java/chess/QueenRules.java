package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.*;



public class QueenRules {
    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        up(board, myPosition, myPosition, possibleMoves, myColor);
        down(board, myPosition, myPosition, possibleMoves, myColor);
        right(board, myPosition, myPosition, possibleMoves, myColor);
        left(board, myPosition, myPosition, possibleMoves, myColor);

        return diagonalMoveAdder(board, myPosition, possibleMoves, myColor);


    }

}
