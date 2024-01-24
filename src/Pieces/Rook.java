package Pieces;

import Board.Direction;
import Board.Square;

import java.util.ArrayList;

public class Rook extends Piece {
    protected final int value = 5;
    public Rook(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    @Override
    public int getValue() {
        return value;
    }
    @Override
    public void move(Square moveTo) {
//        final Square INITIAL_SQUARE = this.getSquare();
//        final PlayerEnum PLAYER = this.getPlayer();
//        Direction movingDirection = null;

//        ArrayList<Square> availableSquares = this.getAvailableSquares();

//        this.getAvailableSquares().forEach(square -> System.out.print(square.coordinates + " "));

        if (this.getAvailableSquares().contains(moveTo)) {
                super.move(moveTo);
        }
//        else if (moveTo.getRank() == INITIAL_SQUARE.getRank()) {
//            movingDirection = switch (PLAYER) {
//                case WHITE: yield moveTo.getFile() > INITIAL_SQUARE.getFile() ? Direction.EAST : Direction.WEST; // H G
//                case BLACK: yield moveTo.getFile() < INITIAL_SQUARE.getFile() ? Direction.EAST : Direction.WEST;
//            };
//        } else if (moveTo.getFile() == INITIAL_SQUARE.getFile()) {
//            movingDirection = switch (PLAYER) {
//                case WHITE: yield moveTo.getRank() > INITIAL_SQUARE.getRank() ? Direction.NORTH : Direction.SOUTH;
//                case BLACK: yield moveTo.getRank() < INITIAL_SQUARE.getRank() ? Direction.NORTH : Direction.SOUTH;
//            };
//        }

//        Square moving = INITIAL_SQUARE;
//        do {
//            if (moving == moveTo) {
//                super.move(moveTo);
//                return;
//            }
//            moving = moving.getNearbySquare(movingDirection, PLAYER);
//        } while (moving != null && moving.getPiece() == null);
    }
}