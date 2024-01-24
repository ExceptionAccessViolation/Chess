package Board;

import Game.Chess;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    BoardPanel () {
//        Border border = BorderFactory.createStrokeBorder(new BasicStroke(2));

        this.setPreferredSize(new Dimension(Board.FRAME_WIDTH, Board.FRAME_HEIGHT));
        this.setLayout(new GridLayout(8, 8));
//        this.setBorder(border);

        for (int i = Chess.boardArray.length - 1; i >= 0; i--) {
            for (int j = 0; j < Chess.boardArray.length; j++) {
                this.add(Chess.boardArray[i][j]);
            }
        }

        this.setVisible(true);
    }
}
