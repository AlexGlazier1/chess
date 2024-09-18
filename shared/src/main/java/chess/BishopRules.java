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
        try{
            UpRight(board, myPosition, myPosition, possibleMoves, myColor);
        }finally {
            try {
                DownLeft(board, myPosition, myPosition, possibleMoves, myColor);
            } finally {
                try {
                    DownRight(board, myPosition, myPosition, possibleMoves, myColor);
                } finally {
                    try {
                        UpLeft(board, myPosition, myPosition, possibleMoves, myColor);
                    } finally {
                        return possibleMoves;

                    }
                }
            }
        }

        //DownLeft(board, myPosition, myPosition, possibleMoves, myColor);
        //DownRight(board, myPosition, myPosition, possibleMoves, myColor);
        //UpLeft(board, myPosition, myPosition, possibleMoves, myColor);

        //return possibleMoves;
    }

    public static void DownLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        ChessPosition DL = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-1);

        if (edgeChecker(board, tempPosition)){

            if(board.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
                DownLeft(board,moveFrom, DL, possibleMoves, myColor);

            }else if(board.getPiece(DL).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
            }
        }
    }

    public static void DownRight(ChessBoard board,
                                 ChessPosition moveFrom,
                                 ChessPosition tempPosition,
                                 ArrayList<ChessMove> possibleMoves,
                                 ChessGame.TeamColor myColor){

        ChessPosition DR = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+1);

        if (edgeChecker(board, DR)){

            if(board.getPiece(DR) == null){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));
                DownRight(board,moveFrom, DR, possibleMoves, myColor);

            }else if(board.getPiece(DR).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));
            }
        }
    }

    public static void UpLeft(ChessBoard board,
                              ChessPosition moveFrom,
                              ChessPosition tempPosition,
                              ArrayList<ChessMove> possibleMoves,
                              ChessGame.TeamColor myColor){

        ChessPosition UL = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-1);

        if (edgeChecker(board, UL)){

            if(board.getPiece(UL) == null){
                possibleMoves.add(new ChessMove(moveFrom, UL, null));
                UpLeft(board,moveFrom, UL, possibleMoves, myColor);

            }else if(board.getPiece(UL).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, UL, null));
            }
        }
    }

    public static void UpRight(ChessBoard board,
                               ChessPosition moveFrom,
                               ChessPosition tempPosition,
                               ArrayList<ChessMove> possibleMoves,
                               ChessGame.TeamColor myColor){

        ChessPosition UR = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+1);

        if (edgeChecker(board, UR)){

            if(board.getPiece(UR) == null){
                possibleMoves.add(new ChessMove(moveFrom, UR, null));
                UpRight(board,moveFrom, UR, possibleMoves, myColor);

            }else if(board.getPiece(UR).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, UR, null));
            }
        }
    }

    private static boolean edgeChecker(ChessBoard board, ChessPosition myPosition){
        if ((myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8 ) && (myPosition.getRow() >= 1 && myPosition.getRow() <= 8)){
            return true;
        }else{
            return false;
        }
    }


}
