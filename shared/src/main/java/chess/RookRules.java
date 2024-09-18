package chess;

import java.util.ArrayList;
import java.util.Collection;


public class RookRules {

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

        ChessPosition D = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn());

        if (edgeChecker(board, D)){


            if(board.getPiece(D) == null){
                possibleMoves.add(new ChessMove(moveFrom, D, null));
                Down(board,moveFrom, D, possibleMoves, myColor);

            }else if(board.getPiece(D).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, D, null));
            }
        }
    }

    public static void Right(ChessBoard board,
                             ChessPosition moveFrom,
                             ChessPosition tempPosition,
                             ArrayList<ChessMove> possibleMoves,
                             ChessGame.TeamColor myColor){

        ChessPosition R = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()+1);

        if (edgeChecker(board, R)){

            //possibleMoves.add(new ChessMove(moveFrom, DR, null));

            if(board.getPiece(R) == null){
                possibleMoves.add(new ChessMove(moveFrom, R, null));
                Right(board,moveFrom, R, possibleMoves, myColor);

            }else if(board.getPiece(R).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, R, null));
            }
        }
    }

    public static void Left(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition L = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()-1);

        if (edgeChecker(board, L)){


            if(board.getPiece(L) == null){
                possibleMoves.add(new ChessMove(moveFrom, L, null));
                Left(board,moveFrom, L, possibleMoves, myColor);

            }else if(board.getPiece(L).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, L, null));
            }
        }
    }

    public static void Up(ChessBoard board,
                          ChessPosition moveFrom,
                          ChessPosition tempPosition,
                          ArrayList<ChessMove> possibleMoves,
                          ChessGame.TeamColor myColor){

        ChessPosition U = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn());

        if (edgeChecker(board, U)){



            if(board.getPiece(U) == null){
                possibleMoves.add(new ChessMove(moveFrom, U, null));
                Up(board,moveFrom, U, possibleMoves, myColor);

            }else if(board.getPiece(U).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, U, null));
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