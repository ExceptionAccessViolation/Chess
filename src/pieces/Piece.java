package pieces;

import board.Direction;
import board.Square;
import game.Chess;
import game.PlayerEnum;
import game.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public static final int IMAGE_LENGTH = 60;
    public static final int IMAGE_WIDTH = 60;

    public final Image white = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("assets/White_" +
            this.getClass().getSimpleName() + ".png"))).getImage();
    public final Image black = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("assets/Black_" +
            this.getClass().getSimpleName() + ".png"))).getImage();

    private final int value = 0;
    private final char letter = '\u0000';
    private final String name = this.getClass().getSimpleName();

    private final PlayerEnum player;
    private Square square;

    public Piece(PlayerEnum player, Square square) {
        Chess.board.getSquare(square.getFile(), square.getRank()).setPiece(this);
        this.player = player;
        this.square = square;
    }

    public boolean move(Square movedTo) {
        if (movedTo.hasPiece()) {
            if (movedTo.getPiece().getPlayerEnum() != this.getPlayerEnum() && !(movedTo.getPiece() instanceof King)) {
                Utils.getPlayerObject(movedTo.getPiece().getPlayerEnum()).deletePiece(movedTo.getPiece());
            } else
                return false;
        }
        movedTo.setPiece(this);
        this.square.setPiece(null);
        this.square = movedTo;
        return true;
    }

    public ArrayList<Square> getAvailableSquares() {
        ArrayList<Direction> availableDirections = new ArrayList<>(switch (this) {
            case Pawn ignored -> null;
            case Knight ignored -> null;
            case Bishop ignored -> Direction.getDiagonals();
            case Rook ignored -> Direction.getCardinals();
            case Queen ignored -> List.of(Direction.values());
            case King ignored -> null;
            default -> null;
        });

        ArrayList<Square> availableSquares = new ArrayList<>();
        for (Direction d : availableDirections) {
            Square moving = this.getSquare();
            while (true) {
                moving = moving.getNearbySquare(d, this.getPlayerEnum());
                if (moving != null) {
                    if (moving.hasPiece()) {
                        if (moving.getPiece().getPlayerEnum() != this.getPlayerEnum()) {
                        } else
                            break;
                    }
                    availableSquares.add(moving);
                } else
                    break;
            }
        }
        return availableSquares;
    }

    ArrayList<Square> getProtectedSquares() {
        ArrayList<Direction> availableDirections = new ArrayList<>(switch (this) {
            case Pawn ignored -> null;
            case Knight ignored -> null;
            case Bishop ignored -> Direction.getDiagonals();
            case Rook ignored -> Direction.getCardinals();
            case Queen ignored -> List.of(Direction.values());
            case King ignored -> null;
            default -> null;
        });

        ArrayList<Square> protectedSquares = new ArrayList<>();
        for (Direction d : availableDirections) {
            Square moving = this.getSquare();
            while (true) {
                moving = moving.getNearbySquare(d, this.getPlayerEnum());
                if (moving != null) {
                    if (moving.hasPiece()) {
                        if (moving.getPiece().getPlayerEnum() == this.getPlayerEnum()) {
                            protectedSquares.add(moving);
                        }
                    }
                } else
                    break;
            }
        }
        return protectedSquares;
    }

    public PlayerEnum getPlayerEnum() {
        return player;
    }

    public Square getSquare() {
        return square;
    }

    public int getValue() {
        return value;
    }

    public char getLetter() {
        return letter;
    }

    public String getName() {
        return name;
    }
}
