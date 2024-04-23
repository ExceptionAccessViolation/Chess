package board;

import game.Chess;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public static final int BOARD_WIDTH = 540;
    public static final int BOARD_HEIGHT = 540;
    public static final Color altSquareColour = new Color(89, 147, 93);
    public static final Color selectedWhiteSquareColour = new Color(130, 166, 104);
    public static final Color selectedColouredSquareColour = new Color(54, 116, 61);

    Square[][] boardArray;

    public Board(Square[][] boardArray) {
        this.boardArray = boardArray;

        this.setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        this.setLayout(new GridLayout(8, 8));

        for (int i = Chess.boardArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < Chess.boardArray.length; j++) {
                this.add(Chess.boardArray[i][j]);
            }
        }

        this.setVisible(true);
    }

    public Square getSquare(char file, int rank) {
        return switch (file) {
            case 'A' -> boardArray[rank - 1][0];
            case 'B' -> boardArray[rank - 1][1];
            case 'C' -> boardArray[rank - 1][2];
            case 'D' -> boardArray[rank - 1][3];
            case 'E' -> boardArray[rank - 1][4];
            case 'F' -> boardArray[rank - 1][5];
            case 'G' -> boardArray[rank - 1][6];
            case 'H' -> boardArray[rank - 1][7];
            default -> {
//                boardArray[-1][-1];
                throw new ArrayIndexOutOfBoundsException("Invalid FEN! Details -> File: " + file + ", Rank: " + rank);
            }
        };
    }
}
