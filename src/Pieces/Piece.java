package Pieces;

import Board.Direction;
import Board.Square;
import Game.Chess;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final int value = 0;
    public static final int IMAGE_LENGTH = 60;
    public static final int IMAGE_WIDTH = 60;
    public final Image white = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("assets/White_" +
            this.getPieceType().toString() + ".png"))).getImage();
    public final Image black = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("assets/Black_" +
            this.getPieceType().toString() + ".png"))).getImage();
    private final PlayerEnum player;
    private Square square;
    public Piece (PlayerEnum player, Square square) {
        Chess.board.getSquare(square.getFile(), square.getRank()).setPiece(this); // remember to do this when moving piece
        this.player = player;
        this.square = square;
    }
    public void move(Square movedTo) {
        if (movedTo.hasPiece()) {
            if (movedTo.getPiece().getPlayer() != this.getPlayer() && !(movedTo.getPiece() instanceof King)) {
                Chess.getPlayerObject(movedTo.getPiece().getPlayer()).deletePiece(movedTo.getPiece());
            } else
                return;
        }
        movedTo.setPiece(this);
        this.square.setPiece(null);
        this.square = movedTo;
    }

    public ArrayList<Square> getAvailableSquares() {
        PieceEnum pieceType = this.getPieceType();
        ArrayList<Direction> availableDirections = new ArrayList<>(switch (pieceType) {
            case Pawn -> null;
            case Knight -> null;
            case Bishop -> Direction.getDiagonals();
            case Rook -> Direction.getCardinals();
            case Queen -> List.of(Direction.values());
            case King -> null;
        });

        ArrayList<Square> availableSquares = new ArrayList<>();
        for (Direction d : availableDirections) {
            Square moving = this.getSquare();
            while (true) {
                moving = moving.getNearbySquare(d, this.getPlayer());
                if (moving != null) {
                    if (moving.hasPiece()) {
                        if (moving.getPiece().getPlayer() != this.getPlayer()) {}
                        else
                            break;
                    }
                    availableSquares.add(moving);
                } else
                    break;
            }
        }
        return availableSquares;
    }
    public PlayerEnum getPlayer() {
        return this.player;
    }

    public Square getSquare() {
        return this.square;
    }
    public int getValue() {
        return value;
    }

    public PieceEnum getPieceType() {
        if (this instanceof Pawn)
            return PieceEnum.Pawn;
        else if (this instanceof Knight)
            return PieceEnum.Knight;
        else if (this instanceof Bishop)
            return PieceEnum.Bishop;
        else if (this instanceof Rook)
            return PieceEnum.Rook;
        else if (this instanceof Queen)
            return PieceEnum.Queen;
        else
            return PieceEnum.King;
    }
}
