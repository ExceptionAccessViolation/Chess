package gui;

import board.Board;
import game.PlayerEnum;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoPanel extends JPanel {
    public static final int X = Window.H_PADDING * 2 + Board.BOARD_WIDTH;
    public static final int Y = Window.HEIGHT / 10;
    public static final int WIDTH = Window.WIDTH - X - Window.H_PADDING;
    public static final int LOWER_EDGE = Board.BOARD_HEIGHT;

    InfoPanel() {
        this.setBounds(X, Y, WIDTH, Board.BOARD_HEIGHT);
        this.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 2, true);

        final int PANEL_HEIGHT = 200;

        PlayerInfoPanel whiteInfoPanel = new PlayerInfoPanel(PlayerEnum.WHITE);
        PlayerInfoPanel blackInfoPanel = new PlayerInfoPanel(PlayerEnum.BLACK);

        whiteInfoPanel.setBounds(0, LOWER_EDGE - PANEL_HEIGHT, WIDTH, PANEL_HEIGHT);
        blackInfoPanel.setBounds(0, 0, WIDTH, PANEL_HEIGHT);

        this.add(whiteInfoPanel);
        this.add(blackInfoPanel);
        this.setBorder(border);
        this.setVisible(true);
    }
}
