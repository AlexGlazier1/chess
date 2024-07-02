package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingRules {

    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        Up(board, myPosition, myPosition, possibleMoves, myColor);
        Down(board, myPosition, myPosition, possibleMoves, myColor);
        Right(board, myPosition, myPosition, possibleMoves, myColor);
        Left(board, myPosition, myPosition, possibleMoves, myColor);

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

            }else if(board.getPiece(U).getTeamColor() != board.getPiece(moveFrom).getTeamColor()){
                possibleMoves.add(new ChessMove(moveFrom, U, null));
            }
        }
    }
    //Need to rewrite the edge checker. It does not account for if a piece is on the edge but wants to move inward or parallel

    public static void DownLeft(ChessBoard board,
                                ChessPosition moveFrom,
                                ChessPosition tempPosition,
                                ArrayList<ChessMove> possibleMoves,
                                ChessGame.TeamColor myColor){

        ChessPosition DL = new ChessPosition((tempPosition.getRow()-1), tempPosition.getColumn()-1);

        if (edgeChecker(board, tempPosition)){

            if(board.getPiece(DL) == null){
                possibleMoves.add(new ChessMove(moveFrom, DL, null));

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
