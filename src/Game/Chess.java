package Game;

import Board.*;
import Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Chess {

    public static Square[][] boardArray = {
                    { new Square('A', 1), new Square('B', 1), new Square('C', 1), new Square('D', 1), new Square('E', 1), new Square('F', 1), new Square('G', 1), new Square('H', 1) },
                    { new Square('A', 2), new Square('B', 2), new Square('C', 2), new Square('D', 2), new Square('E', 2), new Square('F', 2), new Square('G', 2), new Square('H', 2) },
                    { new Square('A', 3), new Square('B', 3), new Square('C', 3), new Square('D', 3), new Square('E', 3), new Square('F', 3), new Square('G', 3), new Square('H', 3) },
                    { new Square('A', 4), new Square('B', 4), new Square('C', 4), new Square('D', 4), new Square('E', 4), new Square('F', 4), new Square('G', 4), new Square('H', 4) },
                    { new Square('A', 5), new Square('B', 5), new Square('C', 5), new Square('D', 5), new Square('E', 5), new Square('F', 5), new Square('G', 5), new Square('H', 5) },
                    { new Square('A', 6), new Square('B', 6), new Square('C', 6), new Square('D', 6), new Square('E', 6), new Square('F', 6), new Square('G', 6), new Square('H', 6) },
                    { new Square('A', 7), new Square('B', 7), new Square('C', 7), new Square('D', 7), new Square('E', 7), new Square('F', 7), new Square('G', 7), new Square('H', 7) },
                    { new Square('A', 8), new Square('B', 8), new Square('C', 8), new Square('D', 8), new Square('E', 8), new Square('F', 8), new Square('G', 8), new Square('H', 8) }
            };
    public static ArrayList<Piece> pieceList = new ArrayList<>();
    public static Board board;

    static Player white;
    static Player black;

    public static void main(String[] args) {

        board = new Board(boardArray);

        white = new Player(
                PlayerEnum.WHITE, new ArrayList<>(List.of(new Pawn(PlayerEnum.WHITE, board.getSquare('A', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('B', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('C', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('D', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('E', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('F', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('G', 2)), new Pawn(PlayerEnum.WHITE, board.getSquare('H', 2)),
                new Rook(PlayerEnum.WHITE, board.getSquare('A', 1)), new Knight(PlayerEnum.WHITE, board.getSquare('B', 1)), new Bishop(PlayerEnum.WHITE, board.getSquare('C', 1)), new Queen(PlayerEnum.WHITE, board.getSquare('D', 1)), new King(PlayerEnum.WHITE, board.getSquare('E', 1)), new Bishop(PlayerEnum.WHITE, board.getSquare('F', 1)), new Knight(PlayerEnum.WHITE, board.getSquare('G', 1)), new Rook(PlayerEnum.WHITE, board.getSquare('H', 1)))
        ));

        black = new Player(PlayerEnum.BLACK, new ArrayList<>(List.of(new Rook(PlayerEnum.BLACK, board.getSquare('A', 8)), new Knight(PlayerEnum.BLACK, board.getSquare('B', 8)), new Bishop(PlayerEnum.BLACK, board.getSquare('C', 8)), new Queen(PlayerEnum.BLACK, board.getSquare('D', 8)), new King(PlayerEnum.BLACK, board.getSquare('E', 8)), new Bishop(PlayerEnum.BLACK, board.getSquare('F', 8)), new Knight(PlayerEnum.BLACK, board.getSquare('G', 8)), new Rook(PlayerEnum.BLACK, board.getSquare('H', 8)),
                new Pawn(PlayerEnum.BLACK, board.getSquare('A', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('B', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('C', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('D', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('E', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('F', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('G', 7)), new Pawn(PlayerEnum.BLACK, board.getSquare('H', 7)))
        ));

//        white = new Player(PlayerEnum.WHITE, new ArrayList<>(List.of(new Rook(PlayerEnum.WHITE, board.getSquare('G', 6)), new Rook(PlayerEnum.WHITE, board.getSquare('E', 6)))));
//        black = new Player(PlayerEnum.BLACK, new ArrayList<>(List.of()))));

    }

    public static Player getPlayerObject(PlayerEnum playerEnum) {
        return playerEnum == PlayerEnum.WHITE ? white : black;
    }
}