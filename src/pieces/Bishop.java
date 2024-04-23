package pieces;

import board.Square;
import game.PlayerEnum;

public class Bishop extends Piece {
    private final int value = 3;
    private final char letter = 'B';

    public Bishop(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
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