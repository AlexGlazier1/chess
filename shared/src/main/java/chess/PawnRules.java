package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.edgeChecker;


public class PawnRules {


    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        if(myColor == ChessGame.TeamColor.WHITE){
            forward(board, myPosition, possibleMoves, myColor, 1);
        }

        if(myColor == ChessGame.TeamColor.BLACK){
            forward(board, myPosition, possibleMoves, myColor, -1);
        }

        return possibleMoves;
    }

    public static void forward(ChessBoard board,
                               ChessPosition moveFrom,
                               ArrayList<ChessMove> possibleMoves,
                               ChessGame.TeamColor myColor,
                               int direction) {

        ChessPosition f = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn());
        ChessPosition fr = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn()+1);
        ChessPosition fl = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn()-1);

        //Forward once and twice
        if (edgeChecker(board, f)) {
            if (board.getPiece(f) == null) {
                pawnHelper(moveFrom, possibleMoves, myColor, f);

                ChessPosition fPlus = new ChessPosition(moveFrom.getRow() + (direction * 2), moveFrom.getColumn());
                if (moveFrom.getRow() == 2 && myColor == ChessGame.TeamColor.WHITE && board.getPiece(fPlus) == null) {
                    possibleMoves.add(new ChessMove(moveFrom, fPlus, null));
                }
                if (moveFrom.getRow() == 7 && myColor == ChessGame.TeamColor.BLACK && board.getPiece(fPlus) == null) {
                    possibleMoves.add(new ChessMove(moveFrom, fPlus, null));
                }
            }
        }
        //Capture Right
        if(edgeChecker(board, fr)){
            if(board.getPiece(fr) != null && board.getPiece(fr).getTeamColor() != myColor){
                pawnHelper(moveFrom, possibleMoves, myColor, fr);
            }
        }
        //Capture Left
        if(edgeChecker(board, fl)){
            if(board.getPiece(fl) != null && board.getPiece(fl).getTeamColor() != myColor){
                if(board.getPiece(fl) != null && board.getPiece(fl).getTeamColor() != myColor){
                    pawnHelper(moveFrom, possibleMoves, myColor, fl);
                }
            }
        }

    }

    private static void pawnHelper(ChessPosition moveFrom, ArrayList<ChessMove> possibleMoves, ChessGame.TeamColor myColor, ChessPosition f) {
        if (f.getRow() == 8 && myColor == ChessGame.TeamColor.WHITE) {
            //possibleMoves.add(new ChessMove(moveFrom, F, null));
            pawnMoveAdder(moveFrom, possibleMoves, f);

        } else {
            if (f.getRow() == 1 && myColor == ChessGame.TeamColor.BLACK) {
                //possibleMoves.add(new ChessMove(moveFrom, F, null));
                pawnMoveAdder(moveFrom, possibleMoves, f);

            } else {
                possibleMoves.add(new ChessMove(moveFrom, f, null));
            }
        }
    }

    private static void pawnMoveAdder(ChessPosition moveFrom, ArrayList<ChessMove> possibleMoves, ChessPosition f) {
        possibleMoves.add(new ChessMove(moveFrom, f, ChessPiece.PieceType.QUEEN));
        possibleMoves.add(new ChessMove(moveFrom, f, ChessPiece.PieceType.KNIGHT));
        possibleMoves.add(new ChessMove(moveFrom, f, ChessPiece.PieceType.ROOK));
        possibleMoves.add(new ChessMove(moveFrom, f, ChessPiece.PieceType.BISHOP));
    }
}
