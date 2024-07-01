package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopRules {


    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {


        //ChessPosition adjustedNotation = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        UpRight(board, myPosition, myPosition, possibleMoves, myColor);
        DownLeft(board, myPosition, myPosition, possibleMoves, myColor);
        DownRight(board, myPosition, myPosition, possibleMoves, myColor);
        UpLeft(board, myPosition, myPosition, possibleMoves, myColor);


        return possibleMoves;
    }

    public static void DownLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        if (edgeChecker(board, tempPosition)){

            ChessPosition DL = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-1);

            if(ChessBoard.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
                DownLeft(board,moveFrom, DL, possibleMoves, myColor);

            }else if(ChessBoard.getPiece(DL).getTeamColor() != ChessBoard.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
        }
        }
    }

    public static void DownRight(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        if (edgeChecker(board, tempPosition)){

            ChessPosition DR = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+1);
            //possibleMoves.add(new ChessMove(moveFrom, DR, null));

            if(ChessBoard.getPiece(DR) == null){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));
                DownRight(board,moveFrom, DR, possibleMoves, myColor);

            }else if(ChessBoard.getPiece(DR).getTeamColor() != ChessBoard.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));
            }
        }
    }

    public static void UpLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        if (edgeChecker(board, tempPosition)){

            ChessPosition DL = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-1);

            if(ChessBoard.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
                UpLeft(board,moveFrom, DL, possibleMoves, myColor);

            }else if(ChessBoard.getPiece(DL).getTeamColor() != ChessBoard.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
            }
        }
    }

    public static void UpRight(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        if (edgeChecker(board, tempPosition)){

            ChessPosition DL = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+1);

            if(ChessBoard.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
                UpRight(board,moveFrom, DL, possibleMoves, myColor);

            }else if(ChessBoard.getPiece(DL).getTeamColor() != ChessBoard.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
            }
        }
    }

    private static boolean edgeChecker(ChessBoard board, ChessPosition myPosition){
        if ((myPosition.getColumn() > 1 && myPosition.getColumn() < 8 ) && (myPosition.getRow() > 1 && myPosition.getRow() < 8)){
            return true;
        }else{
            return false;
        }
    }


}
