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
        this.board = new ChessBoard();
        board.resetBoard();
        teamColor = TeamColor.WHITE;
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
        //Creates two lists, one list containing all moves a piece and make
        //Loops through this list and adds moves to the list as valid moves are found
        //Valid moves are tested on temporary boards to see of they leave King in check

        Collection<ChessMove> allMoves = board.getPiece(startPosition).pieceMoves(board, startPosition);
        Collection<ChessMove> validMoves = new ArrayList<>();

        for (ChessMove move : allMoves) {
            ChessBoard testForValid = new ChessBoard(board);
            ChessPiece temp =  testForValid.getPiece(move.getStartPosition());
            testForValid.addPiece(move.getStartPosition(), null);
            testForValid.addPiece(move.getEndPosition(), temp);
            if(!isInCheck(board.getPiece(startPosition).getTeamColor(), testForValid)) {
                validMoves.add(move);
            }
        }


        return validMoves;

        //throw new RuntimeException("Not implemented");
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        //Checks if the given move is moving a piece
        //Then checks if it is the turn of the piece being moved
        //Then checks if the move is legal
        //If so, the move is executed and the turn is switched
        //otherwise an exception is thrown
        ChessPiece pieceToBeMoved = board.getPiece(move.getStartPosition());

        if(pieceToBeMoved != null) {
            if (pieceToBeMoved.getTeamColor() == this.getTeamTurn()) {
                if (validMoves(move.getStartPosition()).contains(move)) {
                    ChessPiece promo = new ChessPiece(pieceToBeMoved.getTeamColor(), move.getPromotionPiece());

                    board.addPiece(move.getStartPosition(), null);
                    if(move.getPromotionPiece() != null){
                        board.addPiece(move.getEndPosition(), promo);
                        setTeamTurn(getOppositeTeamColor(pieceToBeMoved.getTeamColor()));
                    }else {
                        board.addPiece(move.getEndPosition(), pieceToBeMoved);
                        setTeamTurn(getOppositeTeamColor(pieceToBeMoved.getTeamColor()));
                    }
                } else {
                    throw new InvalidMoveException();
                }
            } else {
                throw new InvalidMoveException();
            }
        }else {
            throw new InvalidMoveException();
        }



        //throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //Acquires square team's king is on in current board
        //Gets list of all the opponent's available moves
        //If any end on current team's square they are in check and true is returned
        return checkFinder(teamColor, board);


        //throw new RuntimeException("Not implemented");
    }

    public boolean isInCheck(TeamColor teamColor, ChessBoard board) {
        //Acquires square team's king is on in a specified board
        //Gets list of all the opponent's available moves
        //If any end on current team's square they are in check and true is returned
        return checkFinder(teamColor, board);


        //throw new RuntimeException("Not implemented");
    }

    private boolean checkFinder(TeamColor teamColor, ChessBoard board) {
        ChessPosition kingSquare = getKingPosition(teamColor, board);
        Collection<ChessMove> oppositeTeamMoves = getAllMoves(getOppositeTeamColor(teamColor), board);

        for (ChessMove move : oppositeTeamMoves) {
            if(move.getEndPosition().equals(kingSquare)){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        //Checks if team is in check and if so for all moves available to them checks
        //if any of them will get them out of check
        //if not, they are checkmated


        if(isInCheck(teamColor)){
            Collection<ChessMove> myTeamMoves = getAllMoves(teamColor, board);

            for(ChessMove move : myTeamMoves) {
                ChessBoard testForCheckMate = new ChessBoard(board);
                ChessPiece temp =  testForCheckMate.getPiece(move.getStartPosition());
                testForCheckMate.addPiece(move.getStartPosition(), null);
                testForCheckMate.addPiece(move.getEndPosition(), temp);
                if(!isInCheck(teamColor, testForCheckMate)) {
                    return false;
                }
            }

            return true;
        }else {
            return false;
        }


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
        Collection<ChessMove> teamMoves = new ArrayList<>();

        int row = 1;
        for(ChessPiece place[]: board.squares){
            int column = 1;
            for(ChessPiece piece: place){
                if(piece != null && piece.getTeamColor() == teamColor) {
                    teamMoves.addAll(validMoves(new ChessPosition(row, column)));
                }
                column++;
            }
            row++;
        }
        if(!isInCheck(teamColor, board) && teamMoves.isEmpty()){
            return true;
        }
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

    //returns the position of the team's king on a specified board
    public ChessPosition getKingPosition(TeamColor teamColor, ChessBoard board){

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

    //takes team color and a board and returns all moves a team can make with available pieces
    // even if they leave them in check/checkmate
    public Collection<ChessMove> getAllMoves(TeamColor teamColor, ChessBoard board) {
        Collection<ChessMove> teamMoves = new ArrayList<>();

        int row = 1;
        for(ChessPiece place[]: board.squares){
            int column = 1;
            for(ChessPiece piece: place){
                if((piece != null) && (piece.getTeamColor().equals(teamColor))){
                    //teamPieces.add(piece);
                    teamMoves.addAll(piece.pieceMoves(board, new ChessPosition(row, column)));
                }
                column++;
            }
            row++;
        }
        return teamMoves;
    }

    //Returns team opposite of given team
    public TeamColor getOppositeTeamColor(TeamColor teamColor) {
        if (teamColor == TeamColor.WHITE) {
            return TeamColor.BLACK;
        }else {
            return TeamColor.WHITE;
        }
    }
}

