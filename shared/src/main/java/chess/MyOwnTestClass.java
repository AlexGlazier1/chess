package chess;

import java.util.ArrayList;

public class MyOwnTestClass {

    public MyOwnTestClass(){

    }

    public static void main(String[] args) {

        ChessBoard myBoard = new ChessBoard();
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


        //boolean checkChecker = myGame.isInCheck(ChessGame.TeamColor.WHITE);

        //ChessPosition KingSpot = myGame.getKingPosition(ChessGame.TeamColor.BLACK);
        //String Printer = KingSpot.getRow()+  " "+ KingSpot.getColumn();
        //System.out.println(Printer);
        ChessPosition kingSquare = myGame.getKingPosition(ChessGame.TeamColor.BLACK, myBoard);


        ChessBoard testForCheckMate = new ChessBoard(myBoard);

        testForCheckMate.addPiece(new ChessPosition(7,6), null);


        System.out.println(myGame.isInCheck(ChessGame.TeamColor.BLACK, myBoard));
        //myGame.isInCheck(ChessGame.TeamColor.BLACK);

        System.out.println(myGame.isInCheck(ChessGame.TeamColor.BLACK, testForCheckMate));

    }
}

