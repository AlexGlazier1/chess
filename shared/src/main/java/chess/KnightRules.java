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

    public static void down(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition dl = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()-1);
        ChessPosition dr = new ChessPosition((tempPosition.getRow()-2), tempPosition.getColumn()+1);


        if (edgeChecker(board, dl)){
            if(board.getPiece(dl) == null){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));

            }else if(board.getPiece(dl).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, dl, null));
            }
        }

        if (edgeChecker(board, dr)){
            if(board.getPiece(dr) == null){
                possibleMoves.add(new ChessMove(moveFrom, dr, null));

            }else if(board.getPiece(dr).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, dr, null));
            }
        }


    }

    public static void right(ChessBoard board,
                             ChessPosition moveFrom,
                             ChessPosition tempPosition,
                             ArrayList<ChessMove> possibleMoves,
                             ChessGame.TeamColor myColor){

        ChessPosition ru = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()+2);
        ChessPosition rd = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()+2);


        if (edgeChecker(board, ru)){
            if(board.getPiece(ru) == null){
                possibleMoves.add(new ChessMove(moveFrom, ru, null));

            }else if(board.getPiece(ru).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, ru, null));
            }
        }

        if (edgeChecker(board, rd)){
            if(board.getPiece(rd) == null){
                possibleMoves.add(new ChessMove(moveFrom, rd, null));

            }else if(board.getPiece(rd).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, rd, null));
            }
        }


    }

    public static void left(ChessBoard board,
                            ChessPosition moveFrom,
                            ChessPosition tempPosition,
                            ArrayList<ChessMove> possibleMoves,
                            ChessGame.TeamColor myColor){

        ChessPosition lu = new ChessPosition((tempPosition.getRow()+1), tempPosition.getColumn()-2);
        ChessPosition ld = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-2);


        if (edgeChecker(board, lu)){
            if(board.getPiece(lu) == null){
                possibleMoves.add(new ChessMove(moveFrom, lu, null));

            }else if(board.getPiece(lu).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, lu, null));
            }
        }

        if (edgeChecker(board, ld)){
            if(board.getPiece(ld) == null){
                possibleMoves.add(new ChessMove(moveFrom, ld, null));

            }else if(board.getPiece(ld).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, ld, null));
            }
        }


    }

    public static void up(ChessBoard board,
                          ChessPosition moveFrom,
                          ChessPosition tempPosition,
                          ArrayList<ChessMove> possibleMoves,
                          ChessGame.TeamColor myColor) {

        ChessPosition ul = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() - 1);
        ChessPosition ur = new ChessPosition((tempPosition.getRow() + 2), tempPosition.getColumn() + 1);


        if (edgeChecker(board, ul)) {
            if (board.getPiece(ul) == null) {
                possibleMoves.add(new ChessMove(moveFrom, ul, null));

            } else if (board.getPiece(ul).getTeamColor() != board.getPiece(moveFrom).getTeamColor()) {
                possibleMoves.add(new ChessMove(moveFrom, ul, null));
            }
        }

        if (edgeChecker(board, ur)) {
            if (board.getPiece(ur) == null) {
                possibleMoves.add(new ChessMove(moveFrom, ur, null));

            } else if (board.getPiece(ur).getTeamColor() != board.getPiece(moveFrom).getTeamColor()) {
                possibleMoves.add(new ChessMove(moveFrom, ur, null));
            }
        }

    }
}
