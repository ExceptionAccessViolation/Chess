package pieces;

import board.Direction;
import board.Square;
import game.Player;
import game.PlayerEnum;
import game.Utils;

import java.util.ArrayList;

public class King extends Piece {
    private final int value = 0;
    private final char letter = 'K';

    public King(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    public boolean hasMoved() {
        return !(this.getPlayerEnum() == PlayerEnum.WHITE ? this.getSquare().getCoordinates().equals("E1")
                : this.getSquare().getCoordinates().equals("E8"));
    }

    @Override
    public boolean move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo))
            return super.move(moveTo);
        return false;
    }

    @Override
    public ArrayList<Square> getAvailableSquares() {
        Square initialSquare = this.getSquare();
        ArrayList<Square> availableSquares = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Square nearbySquare = initialSquare.getNearbySquare(d, this.getPlayerEnum());
            if (nearbySquare != null) {
                Player opponent = Utils.getPlayerObject(this.getPlayerEnum() == PlayerEnum.WHITE ? PlayerEnum.BLACK : PlayerEnum.WHITE);
                if (nearbySquare.hasPiece()) {
                    if (nearbySquare.getPiece().getPlayerEnum() != this.getPlayerEnum()) {
                        availableSquares.add(nearbySquare);
                        continue;
                    }
                }
                boolean valid = true;
                for (Piece p : opponent.getPieces()) {
                    if (p instanceof Pawn) {
                        if (((Pawn) p).getCapturableSquares().contains(nearbySquare)) {
//                            System.out.println("Piece type: " + p.getPieceType() + "\nPiece coordinates: " + p.getSquare().coordinates);
                            valid = false;
                            break;
                        }
                    } else if (!(p instanceof King)) {
                        if (p.getAvailableSquares().contains(nearbySquare) || p.getProtectedSquares().contains(nearbySquare)) {
//                            System.out.println("Piece type: " + p.getPieceType() + "\nPiece coordinates: " + p.getSquare().coordinates);
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid)
                    availableSquares.add(nearbySquare);
            }
        }
        return availableSquares;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public ArrayList<Square> getProtectedSquares() { // TODO
        return new ArrayList<>();
    }
}   