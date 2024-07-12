package chess;

public class MyOwnTestClass {

    public MyOwnTestClass(){

    }

    public static void main(String[] args) {

        ChessBoard myBoard = new ChessBoard();
        myBoard.resetBoard();
        ChessGame myGame = new ChessGame(myBoard, ChessGame.TeamColor.WHITE);
        //boolean checkChecker = myGame.isInCheck(ChessGame.TeamColor.WHITE);

        //ChessPosition KingSpot = myGame.getKingPosition(ChessGame.TeamColor.BLACK);
        //String Printer = KingSpot.getRow()+  " "+ KingSpot.getColumn();
        //System.out.println(Printer);

        myGame.isInCheck(ChessGame.TeamColor.WHITE);
        System.out.println(myGame.isInCheck(ChessGame.TeamColor.WHITE));
    }
}

