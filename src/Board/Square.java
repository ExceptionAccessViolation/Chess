package Board;

import Pieces.Piece;
import Pieces.PlayerEnum;
import Game.Chess;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Game.Chess.board;

public class Square extends JPanel implements MouseListener {
    private final int rank;
    private final char file;
    public final boolean isOnRankBorder;
    public final boolean isOnFileBorder;
    public final boolean isOnBorder;
    public final String coordinates;
    public final int AVAILABLE_SQUARE_OVAL_LENGTH;
    public final int AVAILABLE_SQUARE_OVAL_WIDTH;
    private Piece piece = null;
    private Graphics g;

    public Square(char file, int rank) {
        this.rank = rank;
        this.file = file;
        this.coordinates = String.valueOf(this.getFile()) + this.getRank();

        AVAILABLE_SQUARE_OVAL_LENGTH = this.getWidth() / 3;
        AVAILABLE_SQUARE_OVAL_WIDTH = this.getHeight() / 3;

        Border border = BorderFactory.createStrokeBorder(new BasicStroke(1));
        this.setLayout(new BorderLayout());

        isOnRankBorder = this.rank == 1 || this.rank == 8;
        isOnFileBorder = this.file == 'A' || this.file == 'H';
        isOnBorder =  isOnRankBorder || isOnFileBorder;

        if (isOnFileBorder || isOnRankBorder) {
            JLabel rankLabel = new JLabel(String.valueOf(this.rank));
            JLabel fileLabel = new JLabel(String.valueOf(this.file));
            rankLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
            fileLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

            if (this.getOriginalColour() == Board.altSquareColour) {
                rankLabel.setForeground(Color.WHITE);
                fileLabel.setForeground(Color.WHITE);
            } else {
                rankLabel.setForeground(Board.altSquareColour);
                fileLabel.setForeground(Board.altSquareColour);
            }

            if (this.file == 'H')
                this.add(rankLabel, BorderLayout.EAST);
            if (this.rank == 1)
                this.add(fileLabel, BorderLayout.SOUTH);
        }

        this.addMouseListener(this);

        this.setBackground(this.getOriginalColour());
        this.setBorder(border);
        this.setVisible(true);
    }

    void update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        if (this.piece != null)
            g2D.drawImage(this.piece.getPlayer() == PlayerEnum.WHITE ? this.piece.white : this.piece.black,
                    (this.getWidth() / 2) - (Piece.IMAGE_WIDTH / 2),
                    (this.getHeight() / 2) - (Piece.IMAGE_LENGTH / 2), null);

        if (ClickHandling.getClicked() != null && ClickHandling.getClicked().hasPiece()) {
            if (ClickHandling.getClicked().getPiece().getAvailableSquares().contains(this)) {
                g2D.setColor(new Color(79, 69, 69, 221));
                g2D.fillRect(
                        (this.getWidth() / 2) - (this.AVAILABLE_SQUARE_OVAL_WIDTH / 2),
                        (this.getHeight() / 2) - (this.AVAILABLE_SQUARE_OVAL_LENGTH / 2),
                        this.AVAILABLE_SQUARE_OVAL_WIDTH, this.AVAILABLE_SQUARE_OVAL_LENGTH);
            }
        }
    }
    Color getOriginalColour() {
        return switch (this.file) {
            case 'A', 'C', 'E', 'G' -> this.rank % 2 == 0 ? Color.WHITE : Board.altSquareColour;
            case 'B', 'D', 'F', 'H' -> this.rank % 2 == 0 ? Board.altSquareColour : Color.WHITE;
            default -> Color.GRAY;
        };
    }
    public Square getNearbySquare(Direction direction, PlayerEnum player) {
        return switch (player) {
            case WHITE:
                switch (direction) {
                    case NORTH: yield this.rank == 8 ? null : board.getSquare(this.file, this.rank + 1);
                    case SOUTH: yield this.rank == 1 ? null : board.getSquare(this.file, this.rank - 1);
                    case EAST: yield this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank);
                    case WEST: yield this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank);
                    case NORTHEAST: yield this.rank == 8 || this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank + 1);
                    case SOUTHEAST: yield this.rank == 1 || this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank - 1);
                    case NORTHWEST: yield this.rank == 8 || this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank + 1);
                    case SOUTHWEST: yield this.rank == 1 || this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank - 1);
                    default: throw new NullPointerException();
                }
            case BLACK:
                switch (direction) {
                    case NORTH: yield this.rank == 1 ? null : board.getSquare(this.file, this.rank - 1);
                    case SOUTH: yield this.rank == 8 ? null : board.getSquare(this.file, this.rank + 1);
                    case EAST: yield this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank);
                    case WEST: yield this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank);
                    case NORTHEAST: yield this.rank == 1 || this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank - 1);
                    case SOUTHEAST: yield this.rank == 8 || this.file == 'A' ? null : board.getSquare((char) (this.file - 1), this.rank + 1);
                    case NORTHWEST: yield this.rank == 1 || this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank - 1);
                    case SOUTHWEST: yield this.rank == 8 || this.file == 'H' ? null : board.getSquare((char) (this.file + 1), this.rank + 1);
                    default: throw new NullPointerException();
                }
            default: throw new NullPointerException();
        };
    }

    public int getRank() {
        return this.rank;
    }
    public char getFile() {
        return this.file;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.repaint();
    }
    public boolean hasPiece() {
        return this.getPiece() != null;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (ClickHandling.getClicked() == null) {
            ClickHandling.setClicked(this);
            this.setBackground((this.getOriginalColour() == Board.altSquareColour) ? Board.selectedColouredSquareColour : Board.selectedWhiteSquareColour);
        } else if (ClickHandling.getClicked() == this) {
            this.setBackground(this.getOriginalColour());
            ClickHandling.setClicked(null);
        } else {
            if (ClickHandling.getClicked().getPiece() != null) {
                ClickHandling.getClicked().getPiece().move(this);
            }
            ClickHandling.getClicked().setBackground(ClickHandling.getClicked().getOriginalColour());
            ClickHandling.setClicked(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
