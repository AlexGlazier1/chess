package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard board;
    private TeamColor teamColor;

    public ChessGame() {

    }

    public ChessGame(ChessBoard board, TeamColor teamColor) {
        this.board = board;
        this.teamColor = teamColor;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamColor;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamColor = team;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        return null;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {


        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //X get board
        //X get square with team color king on it
        //X Get list of moves available from each type of piece
        // For each move list see if that type of piece is in range from the king's position
        // if any piece from opp side is in range for a matching move, king is in check
        ChessPosition kingSquare = getKingPosition(teamColor);

        ArrayList<ChessMove> queenMoves = new ArrayList<>();
        ArrayList<ChessMove> kingMoves = new ArrayList<>();
        ArrayList<ChessMove> rookMoves = new ArrayList<>();
        ArrayList<ChessMove> bishopMoves = new ArrayList<>();
        ArrayList<ChessMove> knightMoves = new ArrayList<>();
        ArrayList<ChessMove> pawnMoves = new ArrayList<>();

        ChessPosition UR = new ChessPosition(0,0);
        ChessPosition UL = new ChessPosition(0,0);
        if(teamColor == TeamColor.WHITE) {
            UR = new ChessPosition(kingSquare.getRow()+1,kingSquare.getColumn()+1);
            UL = new ChessPosition(kingSquare.getRow()+1,kingSquare.getColumn()-1);

        }
        if(teamColor == TeamColor.BLACK) {
            UR = new ChessPosition(kingSquare.getRow()-1,kingSquare.getColumn()+1);
            UL = new ChessPosition(kingSquare.getRow()-1,kingSquare.getColumn()-1);

        }
        if(PawnRules.edgeChecker(board, UR)) {
            ChessMove UpRight = new ChessMove(kingSquare, UR, ChessPiece.PieceType.PAWN);
            pawnMoves.add(UpRight);
        }
        if(PawnRules.edgeChecker(board, UL)) {
            ChessMove UpLeft = new ChessMove(kingSquare, UL, ChessPiece.PieceType.PAWN);
            pawnMoves.add(UpLeft);
        }

        QueenRules.moveCalc(board, kingSquare, queenMoves, teamColor);
        KingRules.moveCalc(board, kingSquare, kingMoves, teamColor);
        RookRules.moveCalc(board, kingSquare, rookMoves, teamColor);
        BishopRules.moveCalc(board, kingSquare, bishopMoves, teamColor);
        KnightRules.moveCalc(board, kingSquare, knightMoves, teamColor);

        if(isPieceTypeChecking(queenMoves, ChessPiece.PieceType.QUEEN, teamColor))
            return true;
        if(isPieceTypeChecking(kingMoves, ChessPiece.PieceType.KING, teamColor))
            return true;
        if(isPieceTypeChecking(rookMoves, ChessPiece.PieceType.ROOK, teamColor))
            return true;
        if(isPieceTypeChecking(bishopMoves, ChessPiece.PieceType.BISHOP, teamColor))
            return true;
        if(isPieceTypeChecking(knightMoves, ChessPiece.PieceType.KNIGHT, teamColor))
            return true;
        if(isPieceTypeChecking(pawnMoves, ChessPiece.PieceType.PAWN, teamColor))
            return true;

        return false;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        return false;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        return false;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;

        //throw new RuntimeException("Not implemented");
    }

    public ChessPosition getKingPosition(TeamColor teamColor){
        ChessPosition KingSquare;
        int row = 1;
        for(ChessPiece place[]: board.squares){
            int column = 1;
            for(ChessPiece piece: place){
                if(piece != null && piece.getTeamColor().equals(teamColor) && piece.getPieceType() == ChessPiece.PieceType.KING){
                    return new ChessPosition(row,column);
                }else{
                    column++;
                }
            }
            row++;
        }
        return null;
    }

    public boolean isPieceTypeChecking(ArrayList<ChessMove> moves, ChessPiece.PieceType pieceType, TeamColor teamColor){
        for(ChessMove move: moves){
            if(board.getPiece(move.getEndPosition()).getPieceType() == pieceType && board.getPiece(move.getEndPosition()).getTeamColor() != teamColor){
                return true;
            }
        }
        return false;
    }
}
