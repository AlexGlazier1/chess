package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.*;


public class BishopRules {


    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {


        //ChessPosition adjustedNotation = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());
        return diagonalMoveAdder(board, myPosition, possibleMoves, myColor);
    }




}
