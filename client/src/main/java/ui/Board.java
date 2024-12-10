package ui;

import chess.ChessBoard;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static ui.EscapeSequences.*;

    public class Board {

        // Board dimensions.
        private static final int BOARD_SIZE_IN_SQUARES = 8;
        private static final int SQUARE_SIZE_IN_PADDED_CHARS = 1;
        private static final int LINE_WIDTH_IN_PADDED_CHARS = 0;
        static ChessBoard board;

        // Padded characters.
        private static final String EMPTY = "   ";
        private static final String X = " X ";
        private static final String O = " O ";

        private static Random rand = new Random();


        public static void main(String[] args) {
            //board.resetBoard();
            var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

            out.print(ERASE_SCREEN);


            String[] headers = { " a ", " b ", " c ", " d ", " e ", " f ", " g ", " h " };
            drawHeaders(out, headers, SET_BG_COLOR_LIGHT_GREY);
            String[] Row1 = {EMPTY, EMPTY,EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,EMPTY };
            drawSquares(out, Row1, 1);
            drawSquares(out, Row1,2);
            drawSquares(out, Row1,3);
            drawSquares(out, Row1,4);
            drawSquares(out, Row1,5);
            drawSquares(out, Row1,6);
            drawSquares(out, Row1,7);
            drawSquares(out, Row1,8);

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

        private static void drawSquares(PrintStream out, String[] headers, int row) {

            setBlack(out);

            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                drawSquare(out, headers[boardCol], ((row+boardCol)%2));

                if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
                }
            }

            out.println();
        }

        private static void drawSquare(PrintStream out, String headerText, int num) {
            int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
            int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

            out.print(EMPTY.repeat(prefixLength));
            if(num == 0) {
                printSquaresText(out, headerText, SET_BG_COLOR_WHITE);
            }else{
                printSquaresText(out, headerText, SET_BG_COLOR_BLACK);
            }
            out.print(EMPTY.repeat(suffixLength));
        }

        private static void printSquaresText(PrintStream out, String player, String bgcolor) {
            out.print(bgcolor);
            out.print(SET_TEXT_COLOR_BLACK);

            out.print(player);

            setBlack(out);
        }


        /*
        private static void drawTicTacToeBoard(PrintStream out) {
            for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {
                drawRowOfSquares(out, boardRow); // Pass the current row index
            }
        }

        private static void drawRowOfSquares(PrintStream out, int boardRow) {
            for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_PADDED_CHARS; ++squareRow) {
                for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
                    // Checkerboard logic: alternate colors based on the sum of row and column indices
                    if ((boardRow + boardCol) % 2 == 0) {
                        setWhite(out); // White square
                    } else {
                        setBlack(out); // Black square
                    }

                    // Print the content of the square (X or O) in the middle row
                    if (squareRow == SQUARE_SIZE_IN_PADDED_CHARS / 2) {
                        int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
                        int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

                        out.print(EMPTY.repeat(prefixLength));
                        printPlayer(out, rand.nextBoolean() ? X : O, ((boardRow + boardCol) % 2)); // Random X or O
                        out.print(EMPTY.repeat(suffixLength));
                    } else {
                        out.print(EMPTY.repeat(SQUARE_SIZE_IN_PADDED_CHARS));
                    }
                }

                out.println(); // Move to the next line after each row of squares
            }
        }

        private static void drawHorizontalLine(PrintStream out) {

            int boardSizeInSpaces = BOARD_SIZE_IN_SQUARES * SQUARE_SIZE_IN_PADDED_CHARS +
                    (BOARD_SIZE_IN_SQUARES - 1) * LINE_WIDTH_IN_PADDED_CHARS;

            for (int lineRow = 0; lineRow < LINE_WIDTH_IN_PADDED_CHARS; ++lineRow) {
                setLightGrey(out);
                out.print(EMPTY.repeat(boardSizeInSpaces));

                setBlack(out);
                out.println();
            }
        }

        */
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
