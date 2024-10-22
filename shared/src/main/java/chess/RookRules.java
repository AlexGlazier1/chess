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

        up(board, myPosition, myPosition, possibleMoves, myColor);
        down(board, myPosition, myPosition, possibleMoves, myColor);
        right(board, myPosition, myPosition, possibleMoves, myColor);
        left(board, myPosition, myPosition, possibleMoves, myColor);


        return possibleMoves;
    }

    public static void down(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition d = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn());

        if (edgeChecker(board, d)){


            if(board.getPiece(d) == null){
                possibleMoves.add(new ChessMove(moveFrom, d, null));
                down(board,moveFrom, d, possibleMoves, myColor);

            }else if(board.getPiece(d).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, d, null));
            }
        }
    }

    public static void right(ChessBoard board,
                             ChessPosition moveFrom,
                             ChessPosition tempPosition,
                             ArrayList<ChessMove> possibleMoves,
                             ChessGame.TeamColor myColor){

        ChessPosition r = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()+1);

        if (edgeChecker(board, r)){

            //possibleMoves.add(new ChessMove(moveFrom, DR, null));

            if(board.getPiece(r) == null){
                possibleMoves.add(new ChessMove(moveFrom, r, null));
                right(board,moveFrom, r, possibleMoves, myColor);

            }else if(board.getPiece(r).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, r, null));
            }
        }
    }

    public static void left(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition l = new ChessPosition((tempPosition.getRow()), tempPosition.getColumn()-1);

        if (edgeChecker(board, l)){


            if(board.getPiece(l) == null){
                possibleMoves.add(new ChessMove(moveFrom, l, null));
                left(board,moveFrom, l, possibleMoves, myColor);

            }else if(board.getPiece(l).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, l, null));
            }
        }
    }

    public static void up(ChessBoard board,
                          ChessPosition moveFrom,
                          ChessPosition tempPosition,
                          ArrayList<ChessMove> possibleMoves,
                          ChessGame.TeamColor myColor){

        ChessPosition u = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn());

        if (edgeChecker(board, u)){



            if(board.getPiece(u) == null){
                possibleMoves.add(new ChessMove(moveFrom, u, null));
                up(board,moveFrom, u, possibleMoves, myColor);

            }else if(board.getPiece(u).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, u, null));
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