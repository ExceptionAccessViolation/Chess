package pieces;

import board.Direction;
import board.Square;
import game.PlayerEnum;

import java.util.ArrayList;

public class Knight extends Piece {
    private final int value = 3;
    private final char letter = 'N';

    public Knight(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    @Override
    public boolean move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo)) {
            return super.move(moveTo);
        }
        return false;
    }

    @Override
    public ArrayList<Square> getAvailableSquares() {
        final Square position = this.getSquare();
        ArrayList<Square> squares = new ArrayList<>();
        for (Direction d : Direction.getCardinals()) {
            Square nearbySquare = position.getNearbySquare(d, this.getPlayerEnum());
            if (nearbySquare == null)
                continue;
            Square cardinalSquare = nearbySquare.getNearbySquare(d, this.getPlayerEnum());
            if (cardinalSquare == null)
                continue;

            Square[] rightleft = { cardinalSquare.getNearbySquare(d == Direction.NORTH || d == Direction.SOUTH ?
                    Direction.EAST : Direction.NORTH, this.getPlayerEnum()),
                    cardinalSquare.getNearbySquare(d == Direction.EAST || d == Direction.WEST ?
                            Direction.SOUTH : Direction.WEST, this.getPlayerEnum()) };

            for (Square sq : rightleft) {
                if (sq != null) {
                    if (sq.hasPiece()) {
                        if (sq.getPiece().getPlayerEnum() == this.getPlayerEnum())
                            continue;
                    }
                    squares.add(sq);
                }
            }
        }
        return squares;
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
