package chess;

import java.util.ArrayList;

public class MyOwnTestClass {

    public MyOwnTestClass(){

    }

    public static void main(String[] args) {

        /*ChessBoard myBoard = new ChessBoard();
        //myBoard.resetBoard();
        ChessGame myGame = new ChessGame(myBoard, ChessGame.TeamColor.WHITE);


        myBoard.addPiece(new ChessPosition(1,1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        myBoard.addPiece(new ChessPosition(6,4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        myBoard.addPiece(new ChessPosition(5,5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        myBoard.addPiece(new ChessPosition(7,6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        myBoard.addPiece(new ChessPosition(2,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        myBoard.addPiece(new ChessPosition(8,6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        myBoard.addPiece(new ChessPosition(8,7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        myBoard.addPiece(new ChessPosition(7,8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));


        myBoard.addPiece(new ChessPosition(7,3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        myBoard.addPiece(new ChessPosition(1,6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        myBoard.addPiece(new ChessPosition(2,5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));

        //boolean checkChecker = myGame.isInCheck(ChessGame.TeamColor.WHITE);

        //ChessPosition KingSpot = myGame.getKingPosition(ChessGame.TeamColor.BLACK);
        //String Printer = KingSpot.getRow()+  " "+ KingSpot.getColumn();
        //System.out.println(Printer);

        ChessPosition Start = new ChessPosition(7,3);
        ChessPosition End = new ChessPosition(8, 3);

        try {
            myGame.makeMove(new ChessMove(Start, End, ChessPiece.PieceType.QUEEN));
            System.out.println("It Works!! but why?...");
        } catch (InvalidMoveException e) {
            throw new RuntimeException(e);
        }*/



        stringMaker(5);

    }

    static void stringMaker(int n){
        String characterList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        String sb = "";// = new StringBuilder(n);

        char s = characterList.charAt(2);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(characterList.length() * Math.random());

            // add Character one by one in end of sb
            sb = sb + (characterList.charAt(index));
        }

        System.out.println( sb.toString());
    }
}

