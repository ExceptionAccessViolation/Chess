package gui;

import board.Board;
import game.Chess;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static final int HEIGHT = 750;
    public static final int WIDTH = 1000;
    public static final int H_PADDING = WIDTH / 16;

    public Window() {
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(600, 600));
        this.setTitle("Chess!");

        InfoPanel infoPanel = new InfoPanel();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {} // There are 20 exceptions so leave as Exception

        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Chess.board.setBounds(H_PADDING, HEIGHT / 10, Board.BOARD_WIDTH, Board.BOARD_HEIGHT);

        this.add(Chess.board);
        this.add(infoPanel);
        this.setVisible(true);
    }
}
