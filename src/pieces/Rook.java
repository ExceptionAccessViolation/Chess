package pieces;

import board.Square;
import game.PlayerEnum;

public class Rook extends Piece {
    private final int value = 5;
    private final char letter = 'R';

    Square initialSquare;

    public Rook(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
        initialSquare = square;
    }

    public boolean hasMoved() {
        return !this.getSquare().getCoordinates().equals(initialSquare.getCoordinates());
    }

    @Override
    public boolean move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo))
            return super.move(moveTo);
        return false;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public char getLetter() {
        return letter;
    }
}