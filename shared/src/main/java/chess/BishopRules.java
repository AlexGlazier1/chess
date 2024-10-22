package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.edgeChecker;


public class BishopRules {


    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {


        //ChessPosition adjustedNotation = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());
        try{
            upRight(board, myPosition, myPosition, possibleMoves, myColor);
        }finally {
            try {
                downLeft(board, myPosition, myPosition, possibleMoves, myColor);
            } finally {
                try {
                    downRight(board, myPosition, myPosition, possibleMoves, myColor);
                } finally {
                    try {
                        upLeft(board, myPosition, myPosition, possibleMoves, myColor);
                    } finally {
                        return possibleMoves;

                    }
                }
            }
        }
    }

    public static void downLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        ChessPosition dl = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-1);

        if (edgeChecker(board, tempPosition)){

            if(board.getPiece(dl) == null){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));
                downLeft(board,moveFrom, dl, possibleMoves, myColor);

            }else if(board.getPiece(dl).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));
            }
        }
    }

    public static void downRight(ChessBoard board,
                                 ChessPosition moveFrom,
                                 ChessPosition tempPosition,
                                 ArrayList<ChessMove> possibleMoves,
                                 ChessGame.TeamColor myColor){

        ChessPosition dr = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+1);

        if (edgeChecker(board, dr)){

            if(board.getPiece(dr) == null){
                possibleMoves.add(new ChessMove(moveFrom, dr, null));
                downRight(board,moveFrom, dr, possibleMoves, myColor);

            }else if(board.getPiece(dr).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, dr, null));
            }
        }
    }

    public static void upLeft(ChessBoard board,
                              ChessPosition moveFrom,
                              ChessPosition tempPosition,
                              ArrayList<ChessMove> possibleMoves,
                              ChessGame.TeamColor myColor){

        ChessPosition ul = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-1);

        if (edgeChecker(board, ul)){

            if(board.getPiece(ul) == null){
                possibleMoves.add(new ChessMove(moveFrom, ul, null));
                upLeft(board,moveFrom, ul, possibleMoves, myColor);

            }else if(board.getPiece(ul).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, ul, null));
            }
        }
    }

    public static void upRight(ChessBoard board,
                               ChessPosition moveFrom,
                               ChessPosition tempPosition,
                               ArrayList<ChessMove> possibleMoves,
                               ChessGame.TeamColor myColor){

        ChessPosition ur = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+1);

        if (edgeChecker(board, ur)){

            if(board.getPiece(ur) == null){
                possibleMoves.add(new ChessMove(moveFrom, ur, null));
                upRight(board,moveFrom, ur, possibleMoves, myColor);

            }else if(board.getPiece(ur).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, ur, null));
            }
        }
    }

}
