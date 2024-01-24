package Pieces;

import Board.Square;

public class Queen extends Piece {
    protected final int value = 9;
    public Queen(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    @Override
    public int getValue() {
        return value;
    }
    @Override
    public void move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo))
            super.move(moveTo);
    }
}
