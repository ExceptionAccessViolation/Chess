package pieces;

import board.Square;
import game.PlayerEnum;

public class Queen extends Piece {
    private final int value = 9;
    private final char letter = 'Q';

    public Queen(PlayerEnum allegiance, Square square) {
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
