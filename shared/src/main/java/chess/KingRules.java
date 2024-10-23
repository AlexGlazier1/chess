package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.edgeChecker;


public class KingRules {

    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        kingUp(board, myPosition, myPosition, possibleMoves, myColor);
        kingDown(board, myPosition, myPosition, possibleMoves, myColor);
        kingRight(board, myPosition, myPosition, possibleMoves, myColor);
        kingLeft(board, myPosition, myPosition, possibleMoves, myColor);

        try{
            kingUpRight(board, myPosition, myPosition, possibleMoves, myColor);
        }finally {
            try {
                kingDownLeft(board, myPosition, myPosition, possibleMoves, myColor);
            } finally {
                try {
                    kingDownRight(board, myPosition, myPosition, possibleMoves, myColor);
                } finally {
                    try {
                        kingUpLeft(board, myPosition, myPosition, possibleMoves, myColor);
                    } finally {
                        return possibleMoves;

                    }
                }
            }
        }


    }

    public static void kingDown(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        ChessPosition d = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn());

        kingEdgeChecker(board, moveFrom, possibleMoves, d);
    }


    public static void kingRight(ChessBoard board,
                                 ChessPosition moveFrom,
                                 ChessPosition tempPosition,
                                 ArrayList<ChessMove> possibleMoves,
                                 ChessGame.TeamColor myColor){

        ChessPosition r = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()+1);

        kingEdgeChecker(board, moveFrom, possibleMoves, r);
    }

    public static void kingLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        ChessPosition l = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()-1);

        kingEdgeChecker(board, moveFrom, possibleMoves, l);
    }

    public static void kingUp(ChessBoard board,
                              ChessPosition moveFrom,
                              ChessPosition tempPosition,
                              ArrayList<ChessMove> possibleMoves,
                              ChessGame.TeamColor myColor){

        ChessPosition u = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn());

        kingEdgeChecker(board, moveFrom, possibleMoves, u);
    }
    //Need to rewrite the edge checker. It does not account for if a piece is on the edge but wants to move inward or parallel

    public static void kingDownLeft(ChessBoard board,
                                    ChessPosition moveFrom,
                                    ChessPosition tempPosition,
                                    ArrayList<ChessMove> possibleMoves,
                                    ChessGame.TeamColor myColor){

        ChessPosition dl = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-1);

        edgeCheckHelper(board, moveFrom, tempPosition, possibleMoves, dl);
    }


    public static void kingDownRight(ChessBoard board,
                                     ChessPosition moveFrom,
                                     ChessPosition tempPosition,
                                     ArrayList<ChessMove> possibleMoves,
                                     ChessGame.TeamColor myColor){

        ChessPosition dr = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+1);

        kingEdgeChecker(board, moveFrom, possibleMoves, dr);
    }

    public static void kingUpLeft(ChessBoard board,
                                  ChessPosition moveFrom,
                                  ChessPosition tempPosition,
                                  ArrayList<ChessMove> possibleMoves,
                                  ChessGame.TeamColor myColor){

        ChessPosition ul = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-1);

        kingEdgeChecker(board, moveFrom, possibleMoves, ul);
    }

    public static void kingUpRight(ChessBoard board,
                                   ChessPosition moveFrom,
                                   ChessPosition tempPosition,
                                   ArrayList<ChessMove> possibleMoves,
                                   ChessGame.TeamColor myColor){

        ChessPosition ur = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+1);

        kingEdgeChecker(board, moveFrom, possibleMoves, ur);
    }

    static void kingEdgeChecker(ChessBoard board,
                                ChessPosition moveFrom,
                                ArrayList<ChessMove> possibleMoves,
                                ChessPosition d) {
        edgeCheckHelper(board, moveFrom, d, possibleMoves, d);
    }

    private static void edgeCheckHelper(ChessBoard board,
                                        ChessPosition moveFrom,
                                        ChessPosition tempPosition,
                                        ArrayList<ChessMove> possibleMoves,
                                        ChessPosition dl) {
        if (edgeChecker(board, tempPosition)){

            if(board.getPiece(dl) == null){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));

            }else if(board.getPiece(dl).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));
            }
        }
    }


}
