package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnRules {
    //private boolean firstTurn = true;

    //public PawnRules(boolean firstTurn) {
    //this.firstTurn = firstTurn;
    //}


    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        if(myColor == ChessGame.TeamColor.WHITE){
            Forward(board, myPosition, possibleMoves, myColor, 1);
        }

        if(myColor == ChessGame.TeamColor.BLACK){
            Forward(board, myPosition, possibleMoves, myColor, -1);
        }

        return possibleMoves;
    }

    public static void Forward(ChessBoard board,
                               ChessPosition moveFrom,
                               ArrayList<ChessMove> possibleMoves,
                               ChessGame.TeamColor myColor,
                               int direction) {

        ChessPosition F = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn());
        ChessPosition FR = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn()+1);
        ChessPosition FL = new ChessPosition((moveFrom.getRow() + direction), moveFrom.getColumn()-1);

        //Forward once and twice
        if (edgeChecker(board, F)) {
            if (board.getPiece(F) == null) {
                if (F.getRow() == 8 && myColor == ChessGame.TeamColor.WHITE) {
                    //possibleMoves.add(new ChessMove(moveFrom, F, null));
                    possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.KNIGHT));
                    possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.ROOK));
                    possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.BISHOP));

                } else {
                    if (F.getRow() == 1 && myColor == ChessGame.TeamColor.BLACK) {
                        //possibleMoves.add(new ChessMove(moveFrom, F, null));
                        possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.KNIGHT));
                        possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(moveFrom, F, ChessPiece.PieceType.BISHOP));

                    } else {
                        possibleMoves.add(new ChessMove(moveFrom, F, null));
                    }
                }

                ChessPosition FPlus = new ChessPosition(moveFrom.getRow() + (direction * 2), moveFrom.getColumn());
                if (moveFrom.getRow() == 2 && myColor == ChessGame.TeamColor.WHITE && board.getPiece(FPlus) == null) {
                    possibleMoves.add(new ChessMove(moveFrom, FPlus, null));
                }
                if (moveFrom.getRow() == 7 && myColor == ChessGame.TeamColor.BLACK && board.getPiece(FPlus) == null) {
                    possibleMoves.add(new ChessMove(moveFrom, FPlus, null));
                }
            }
        }
        //Capture Right
        if(edgeChecker(board, FR)){
            if(board.getPiece(FR) != null && board.getPiece(FR).getTeamColor() != myColor){
                if(FR.getRow() == 8 && myColor == ChessGame.TeamColor.WHITE){
                    //possibleMoves.add(new ChessMove(moveFrom, F, null));
                    possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.QUEEN));
                    possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.KNIGHT));
                    possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.ROOK));
                    possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.BISHOP));

                }else{
                    if(FR.getRow() == 1 && myColor == ChessGame.TeamColor.BLACK){
                        //possibleMoves.add(new ChessMove(moveFrom, F, null));
                        possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.KNIGHT));
                        possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(moveFrom, FR, ChessPiece.PieceType.BISHOP));

                    }else {
                        possibleMoves.add(new ChessMove(moveFrom, FR, null));
                    }
                }
            }
        }
        //Capture Left
        if(edgeChecker(board, FL)){
            if(board.getPiece(FL) != null && board.getPiece(FL).getTeamColor() != myColor){
                if(board.getPiece(FL) != null && board.getPiece(FL).getTeamColor() != myColor){
                    if(FL.getRow() == 8 && myColor == ChessGame.TeamColor.WHITE){
                        //possibleMoves.add(new ChessMove(moveFrom, F, null));
                        possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.QUEEN));
                        possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.KNIGHT));
                        possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.ROOK));
                        possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.BISHOP));

                    }else{
                        if(FL.getRow() == 1 && myColor == ChessGame.TeamColor.BLACK){
                            //possibleMoves.add(new ChessMove(moveFrom, F, null));
                            possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.QUEEN));
                            possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.KNIGHT));
                            possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.ROOK));
                            possibleMoves.add(new ChessMove(moveFrom, FL, ChessPiece.PieceType.BISHOP));

                        }else {
                            possibleMoves.add(new ChessMove(moveFrom, FL, null));
                        }
                    }
                }
            }
        }

    }

    public static boolean edgeChecker(ChessBoard board, ChessPosition myPosition){
        if ((myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8 ) && (myPosition.getRow() >= 1 && myPosition.getRow() <= 8)){
            return true;
        }else{
            return false;
        }
    }
}
