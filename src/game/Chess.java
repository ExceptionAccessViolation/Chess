package game;

import board.Board;
import board.Square;
import gui.Window;
import pieces.*;

import java.util.ArrayList;

public class Chess {
    public static Square[][] boardArray = new Square[8][8];
    public static ArrayList<Piece> pieceList = new ArrayList<>();
    public static Board board;

    public static Player white;
    public static Player black;
    public static Player turn;

    public static void main(String[] args) {
        {
            char rank = 65;
            for (int i = 0; i < boardArray.length; i++) {
                for (int j = boardArray.length - 1; j >= 0; j--) {
                    boardArray[j][i] = new Square(rank, j + 1);
                }
                rank++;
            }
        }

        board = new Board(boardArray);

        Piece[] pieces = Utils.decodeFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR").toArray(new Piece[0]);
        ArrayList<Piece> whitePieces = new ArrayList<>();
        ArrayList<Piece> blackPieces = new ArrayList<>();

        for(Piece p : pieces) {
            if (p.getPlayerEnum() == PlayerEnum.WHITE) {
                whitePieces.add(p);
            } else {
                blackPieces.add(p);
            }
        }

        white = new Player(PlayerEnum.WHITE, whitePieces);
        black = new Player(PlayerEnum.BLACK, blackPieces);

        new Window();

        turn = white;
    }
}