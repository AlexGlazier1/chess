package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightRules {

    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {


        //ChessPosition adjustedNotation = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()-1);

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        Up(board, myPosition, myPosition, possibleMoves, myColor);
        Down(board, myPosition, myPosition, possibleMoves, myColor);
        Right(board, myPosition, myPosition, possibleMoves, myColor);
        Left(board, myPosition, myPosition, possibleMoves, myColor);


        return possibleMoves;
    }

    public static void Down(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition DL = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()-1);
        ChessPosition DR = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()+1);


        if (edgeChecker(board, DL)){
            if(board.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));

            }else if(board.getPiece(DL).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));
            }
        }

        if (edgeChecker(board, DR)){
            if(board.getPiece(DR) == null){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));

            }else if(board.getPiece(DR).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, DR, null));
            }
        }


    }

    public static void Right(ChessBoard board,
                             ChessPosition moveFrom,
                             ChessPosition tempPosition,
                             ArrayList<ChessMove> possibleMoves,
                             ChessGame.TeamColor myColor){

        ChessPosition RU = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+2);
        ChessPosition RD = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+2);


        if (edgeChecker(board, RU)){
            if(board.getPiece(RU) == null){
                possibleMoves.add(new ChessMove(moveFrom, RU, null));

            }else if(board.getPiece(RU).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, RU, null));
            }
        }

        if (edgeChecker(board, RD)){
            if(board.getPiece(RD) == null){
                possibleMoves.add(new ChessMove(moveFrom, RD, null));

            }else if(board.getPiece(RD).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, RD, null));
            }
        }


    }

    public static void Left(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition LU = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-2);
        ChessPosition LD = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-2);


        if (edgeChecker(board, LU)){
            if(board.getPiece(LU) == null){
                possibleMoves.add(new ChessMove(moveFrom, LU, null));

            }else if(board.getPiece(LU).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, LU, null));
            }
        }

        if (edgeChecker(board, LD)){
            if(board.getPiece(LD) == null){
                possibleMoves.add(new ChessMove(moveFrom, LD, null));

            }else if(board.getPiece(LD).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, LD, null));
            }
        }


    }

    public static void Up(ChessBoard board,
                          ChessPosition moveFrom,
                          ChessPosition tempPosition,
                          ArrayList<ChessMove> possibleMoves,
                          ChessGame.TeamColor myColor) {

        ChessPosition UL = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() - 1);
        ChessPosition UR = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() + 1);


        if (edgeChecker(board, UL)) {
            if (board.getPiece(UL) == null) {
                possibleMoves.add(new ChessMove(moveFrom, UL, null));

            } else if (board.getPiece(UL).getTeamColor() != board.getPiece(moveFrom).getTeamColor()) {
                possibleMoves.add(new ChessMove(moveFrom, UL, null));
            }
        }

        if (edgeChecker(board, UR)) {
            if (board.getPiece(UR) == null) {
                possibleMoves.add(new ChessMove(moveFrom, UR, null));

            } else if (board.getPiece(UR).getTeamColor() != board.getPiece(moveFrom).getTeamColor()) {
                possibleMoves.add(new ChessMove(moveFrom, UR, null));
            }
        }

    }
    //Need to rewrite the edge checker. It does not account for if a piece is on the edge but wants to move inward or parallel

    private static boolean edgeChecker(ChessBoard board, ChessPosition myPosition){
        if ((myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8 ) && (myPosition.getRow() >= 1 && myPosition.getRow() <= 8)){
            return true;
        }else{
            return false;
        }
    }
}
