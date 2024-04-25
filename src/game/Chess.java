package game;

import board.Board;
import board.Square;
import gui.Window;
import pieces.Piece;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Chess {
    public static Square[][] boardArray = new Square[8][8];
    public static Board board;
    public static final String STARTING_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public static final String fen = "5Rq1/8/4K2k/8/8/8/5rQ1/8";

    static MoveLogger logger;

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
//        logger = new MoveLogger();

        {
            Piece[] pieces = Utils.decodeFen(fen).toArray(new Piece[0]);
            ArrayList<Piece> whitePieces = new ArrayList<>();
            ArrayList<Piece> blackPieces = new ArrayList<>();

            for (Piece p : pieces) {
                if (p.getPlayerEnum() == PlayerEnum.WHITE)
                    whitePieces.add(p);
                else
                    blackPieces.add(p);
            }

            white = new Player(PlayerEnum.WHITE, whitePieces);
            black = new Player(PlayerEnum.BLACK, blackPieces);
        }

        new Window();

        turn = white;
    }

    // Will be called when a move is made. For updating anything
    public static void afterMove(Piece moved, Square movedTo) {
        // Changing turn
        turn = turn == white ? black : white;
//        try {
//            logger.log(moved, movedTo);
//        } catch (IOException ignored) {}
    }
}