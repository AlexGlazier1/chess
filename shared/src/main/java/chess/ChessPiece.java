package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private PieceType type;


    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;

    }



    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
        //throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
        return "pieceColor=" + pieceColor +
                ", type=" + type;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
        //throw new RuntimeException("Not implemented");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) obj;
        return (Objects.equals(type, that.type) && Objects.equals(pieceColor, that.pieceColor));
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        ArrayList<ChessMove> moves = new ArrayList<>();

        if (board.getPiece(myPosition).type == PieceType.PAWN) {
            PawnRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        if (board.getPiece(myPosition).type == PieceType.BISHOP) {
            BishopRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        if (board.getPiece(myPosition).type == PieceType.ROOK) {
            RookRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        if (board.getPiece(myPosition).type == PieceType.KNIGHT) {
            KnightRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        if (board.getPiece(myPosition).type == PieceType.QUEEN) {
            QueenRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        if (board.getPiece(myPosition).type == PieceType.KING) {
            KingRules.moveCalc(board, myPosition, moves, pieceColor);
            return moves;
        }

        return new ArrayList<>();
    }

    public static boolean edgeChecker(ChessBoard board, ChessPosition myPosition){
        if ((myPosition.getColumn() >= 1 && myPosition.getColumn() <= 8 ) && (myPosition.getRow() >= 1 && myPosition.getRow() <= 8)){
            return true;
        }else{
            return false;
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

    static Collection<ChessMove> diagonalMoveAdder(ChessBoard board,
                                                   ChessPosition myPosition,
                                                   ArrayList<ChessMove> possibleMoves,
                                                   ChessGame.TeamColor myColor) {
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
}
