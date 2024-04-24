package gui;

import game.Chess;
import game.Player;
import game.PlayerEnum;
import game.Utils;
import pieces.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class PlayerInfoPanel extends JPanel {
    private final Image test = Chess.white.getPieces().getFirst().white;

    private final ArrayList<Piece> capturedPieces;
    private final PlayerEnum playerEnum;

    PlayerInfoPanel(PlayerEnum playerEnum) {
        this.playerEnum = playerEnum;

        this.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.CYAN, 2, true);

        Player player = Utils.getPlayerObject(playerEnum);
        Player opponent = Utils.getOpponent(player);

        int material = player.getMaterial();
        int opponentMaterial = opponent.getMaterial();

        capturedPieces = opponent.getCapturedPieces();

        this.setBorder(border);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(test, 10, 10, 60, 60, null);
        g2D.drawImage(test, 20, 10, 60, 60, null);
        g2D.drawImage(test, 30, 10, 60, 60, null);
    }

    private int[] countPieces(ArrayList<Piece> pieces) {
        int[] pieceCount = new int[5]; // pawn -> 0, knight -> 1, bishop -> 2, rook -> 3, queen -> 4

        pieces.forEach(p -> {
            switch (p) {
                case Pawn ignored -> pieceCount[0]++;
                case Knight ignored -> pieceCount[1]++;
                case Bishop ignored -> pieceCount[2]++;
                case Rook ignored -> pieceCount[3]++;
                case Queen ignored -> pieceCount[4]++;
                default -> {}
            }
        });

        return pieceCount;
    }
}
