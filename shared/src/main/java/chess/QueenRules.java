package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.ChessPiece.edgeChecker;


public class QueenRules {
    public static Collection<ChessMove> moveCalc(ChessBoard board,
                                                 ChessPosition myPosition,
                                                 ArrayList<ChessMove> possibleMoves,
                                                 ChessGame.TeamColor myColor) {

        ChessPosition moveFrom = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        up(board, myPosition, myPosition, possibleMoves, myColor);
        down(board, myPosition, myPosition, possibleMoves, myColor);
        right(board, myPosition, myPosition, possibleMoves, myColor);
        left(board, myPosition, myPosition, possibleMoves, myColor);

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
