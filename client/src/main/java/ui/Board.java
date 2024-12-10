package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static ui.EscapeSequences.*;

    public class Board {

        private final ChessBoard board;
        private static String playerColor = "White";

        public Board(ChessBoard board, String playerColor) {
            this.board = board;
            this.playerColor = playerColor;
        }

        // Board dimensions.
        private static final int BOARD_SIZE_IN_SQUARES = 8;
        private static final int SQUARE_SIZE_IN_PADDED_CHARS = 1;
        private static final int LINE_WIDTH_IN_PADDED_CHARS = 0;


        // Padded characters.
        private static final String EMPTY = "   ";
        private static final String X = " X ";
        private static final String O = " O ";

        private static Random rand = new Random();


        public static void main(String[] args) {
            var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

            out.print(ERASE_SCREEN);

            ChessBoard board = new ChessBoard();
            board.resetBoard();
            String[] letters = { " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h ", "   " };
            String[] letters2 = {" h ", " g ", " f ", " e ", " d ", " c ", " b ", " a ", "   "};
            String[] numbers = { " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 "};
            ChessBoard boardBuffer = new ChessBoard();

            if(playerColor == "Black"){
                List<String> tempList = Arrays.asList(numbers);
                Collections.reverse(tempList);
                numbers = tempList.toArray(new String[0]);

                boardBuffer.squares = boardReverser(board);
                letters = letters2;

            }else{
                boardBuffer = board;
            }



            drawHeaders(out, letters, SET_BG_COLOR_LIGHT_GREY);
            drawSquares(out, numbers,8, boardBuffer.squares[0]);
            drawSquares(out, numbers,7, boardBuffer.squares[1]);
            drawSquares(out, numbers,6, boardBuffer.squares[2]);
            drawSquares(out, numbers,5, boardBuffer.squares[3]);
            drawSquares(out, numbers,4, boardBuffer.squares[4]);
            drawSquares(out, numbers,3, boardBuffer.squares[5]);
            drawSquares(out, numbers,2, boardBuffer.squares[6]);
            drawSquares(out, numbers,1, boardBuffer.squares[7]);

            //drawTicTacToeBoard(out);




            //out.print(SET_TEXT_COLOR_WHITE);
        }

        private static void drawHeaders(PrintStream out, String[] headers, String color) {

            setBlack(out);

            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                drawHeader(out, headers[boardCol], color);

                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
                }
            }
            out.print(color);
            out.print(SET_TEXT_COLOR_BLACK);
            out.print("   ");
            setBlack(out);


            out.println();
        }

        private static void drawHeader(PrintStream out, String headerText, String Color) {
            int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
            int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

            out.print(EMPTY.repeat(prefixLength));
            printHeaderText(out, headerText, Color);
            out.print(EMPTY.repeat(suffixLength));
        }

        private static void printHeaderText(PrintStream out, String player, String bgcolor) {
            out.print(bgcolor);
            out.print(SET_TEXT_COLOR_BLACK);

            out.print(player);
            setBlack(out);
        }

        private static void drawSquares(PrintStream out, String[] headers, int row, ChessPiece[] pieces) {

            setBlack(out);

            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                drawSquare(out, pieces[boardCol], ((row+boardCol)%2));

                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
                }
            }
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(headers[row-1]);
            setBlack(out);

            out.println();
        }

        private static void drawSquare(PrintStream out, ChessPiece piece, int num) {
            int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
            int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

            out.print(EMPTY.repeat(prefixLength));
            if(num == 0) {
                printSquaresText(out, piece, SET_BG_COLOR_WHITE);
            }else{
                printSquaresText(out, piece, SET_BG_COLOR_BLACK);
            }
            out.print(EMPTY.repeat(suffixLength));
        }

        private static void printSquaresText(PrintStream out, ChessPiece piece, String bgcolor) {
            String Piece = "   ";
            String textColor = SET_TEXT_COLOR_BLACK;
            if(piece != null) {
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
                    Piece = " P ";
                }
                if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
                    Piece = " B ";
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
                    Piece = " N ";
                }
                if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
                    Piece = " R ";
                }
                if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
                    Piece = " Q ";
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KING) {
                    Piece = " K ";
                }
                if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
                    textColor = SET_TEXT_COLOR_RED;
                }
                if(piece.getTeamColor() == ChessGame.TeamColor.BLACK){
                    textColor = SET_TEXT_COLOR_BLUE;
                }
            }
            out.print(bgcolor);

            out.print(textColor);

            out.print(Piece);

            setBlack(out);
        }

        public static ChessPiece[][] boardReverser(ChessBoard board){
            int rows = 8;
            int cols  = 8;
            ChessPiece[][] reversedGrid = new ChessPiece[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    reversedGrid[rows - 1 - i][cols - 1 - j] = board.squares[i][j];
                }
            }

            return reversedGrid;
        }



        private static void setWhite(PrintStream out) {
            out.print(SET_BG_COLOR_WHITE);
            out.print(SET_TEXT_COLOR_WHITE);
        }

        private static void setLightGrey(PrintStream out) {
            out.print(SET_BG_COLOR_LIGHT_GREY);
            out.print(SET_TEXT_COLOR_LIGHT_GREY);
        }

        private static void setBlack(PrintStream out) {
            out.print(SET_BG_COLOR_BLACK);
            out.print(SET_TEXT_COLOR_BLACK);
        }

        private static void printPlayer(PrintStream out, String player, int val) {
            if (val == 1) {
                out.print(SET_BG_COLOR_BLACK);
                out.print(SET_TEXT_COLOR_WHITE);

                out.print(player);

                setBlack(out);
            } else {
                out.print(SET_BG_COLOR_WHITE);
                out.print(SET_TEXT_COLOR_BLACK);
            }
        }
    }
