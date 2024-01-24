package Board;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    public static final int FRAME_WIDTH = 540;
    public static final int FRAME_HEIGHT = 540;
    public static final Color altSquareColour = new Color(89, 147, 93);
    public static final Color selectedWhiteSquareColour = new Color(130, 166, 104);
    public static final Color selectedColouredSquareColour = new Color(54, 116, 61);

    BoardPanel boardPanel;
    static Square[][] boardArray;
    public Board(Square[][] boardArray) {
        Board.boardArray = boardArray;
        boardPanel = new BoardPanel();
        this.setTitle("Chess!");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(boardPanel);
        this.setVisible(true);
    }
    public void update() {
        for (Square[] squares : boardArray) {
            for (Square square : squares) {
                square.repaint();
            }
        }
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
            default -> boardArray[-1][-1];
        };
    }
}
