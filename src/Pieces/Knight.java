package Pieces;

import Board.Direction;
import Board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    protected final int value = 3;
    public Knight(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    @Override
    public int getValue() {
        return value;
    }
    @Override
    public void move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo)) {
            super.move(moveTo);
        }
    }
    @Override
    public ArrayList<Square> getAvailableSquares() {
        final Square position = this.getSquare();
        ArrayList<Square> squares = new ArrayList<>();
        for (Direction d : Direction.getCardinals()) {
            Square nearbySquare = position.getNearbySquare(d, this.getPlayer());
            if (nearbySquare == null)
                continue;
            Square cardinalSquare = nearbySquare.getNearbySquare(d, this.getPlayer());
            if (cardinalSquare == null)
                continue;
            
            Square[] rightleft = {cardinalSquare.getNearbySquare(d == Direction.NORTH || d == Direction.SOUTH ?
                    Direction.EAST : Direction.NORTH, this.getPlayer()), 
                    cardinalSquare.getNearbySquare(d == Direction.EAST || d == Direction.WEST ?
                    Direction.SOUTH : Direction.WEST, this.getPlayer())};

            for (Square sq : rightleft) {
                if (sq != null) {
                    if (sq.hasPiece()) {
                        if (sq.getPiece().getPlayer() == this.getPlayer())
                            continue;
                    }
                    squares.add(sq);
                }
            }
        }
        return squares;
    }
}
