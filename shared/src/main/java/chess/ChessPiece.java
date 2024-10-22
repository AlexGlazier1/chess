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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
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
}
