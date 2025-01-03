package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessPiece.edgeChecker;

public class KnightRules {

    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {


        //ChessPosition adjustedNotation = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        up(board, myPosition, myPosition, possibleMoves, myColor);
        down(board, myPosition, myPosition, possibleMoves, myColor);
        right(board, myPosition, myPosition, possibleMoves, myColor);
        left(board, myPosition, myPosition, possibleMoves, myColor);


        return possibleMoves;
    }



    private static void knightEdgeCheck(ChessBoard board,
                                        ChessPosition moveFrom,
                                        ArrayList<ChessMove> possibleMoves,
                                        ChessPosition dl,
                                        ChessPosition dr) {
        KingRules.kingEdgeChecker(board, moveFrom, possibleMoves, dl);

        KingRules.kingEdgeChecker(board, moveFrom, possibleMoves, dr);
    }

    public static void down(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition dl = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()-1);
        ChessPosition dr = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()+1);


        knightEdgeCheck(board, moveFrom, possibleMoves, dl, dr);


    }

    public static void right(ChessBoard board,
                             ChessPosition moveFrom,
                             ChessPosition tempPosition,
                             ArrayList<ChessMove> possibleMoves,
                             ChessGame.TeamColor myColor){

        ChessPosition ru = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+2);
        ChessPosition rd = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+2);


        knightEdgeCheck(board, moveFrom, possibleMoves, ru, rd);


    }

    public static void left(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition lu = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-2);
        ChessPosition ld = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-2);


        knightEdgeCheck(board, moveFrom, possibleMoves, lu, ld);


    }

    public static void up(ChessBoard board,
                          ChessPosition moveFrom,
                          ChessPosition tempPosition,
                          ArrayList<ChessMove> possibleMoves,
                          ChessGame.TeamColor myColor) {

        ChessPosition ul = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() - 1);
        ChessPosition ur = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() + 1);


        knightEdgeCheck(board, moveFrom, possibleMoves, ul, ur);

    }
}
