package Pieces;

import Board.Direction;
import Board.Square;

public class Bishop extends Piece {
    protected final int value = 3;
    public Bishop(PlayerEnum allegiance, Square square) {
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

//        System.out.println("Initial Square: " + String.valueOf(INITIAL_SQUARE.getFile()) + INITIAL_SQUARE.getRank());
//        System.out.println("Player: " + PLAYER.toString());

//        final int x_axis_change = moveTo.getFile() - INITIAL_SQUARE.getFile();
//        final int y_axis_change = moveTo.getRank() - INITIAL_SQUARE.getRank();

//        this.getAvailableSquares().forEach(square -> System.out.print(square.coordinates + " "));
//        System.out.println("X change: " + x_axis_change);
//        System.out.println("Y change: " + y_axis_change);

//        Direction movingDirection = null;

//        if (Math.abs(x_axis_change) != Math.abs(y_axis_change))
            // bishops can only move with an equal x axis and y axis change e.g. a4 to d1 4-1 a-d 3 -3
//            return;
        if (this.getAvailableSquares().contains(moveTo))
            super.move(moveTo);

//        if (x_axis_change > 0 && y_axis_change > 0) // pos pos
//            movingDirection = PLAYER == PlayerEnum.WHITE ? Direction.NORTHEAST : Direction.SOUTHWEST;
//        else if (x_axis_change > 0 && y_axis_change < 0) // pos neg
//            movingDirection = PLAYER == PlayerEnum.WHITE ? Direction.SOUTHEAST : Direction.NORTHWEST;
//        else if (x_axis_change < 0 && y_axis_change > 0) // neg pos
//            movingDirection = PLAYER == PlayerEnum.WHITE ? Direction.NORTHWEST : Direction.SOUTHEAST;
//        else // neg neg
//            movingDirection = PLAYER == PlayerEnum.WHITE ? Direction.SOUTHWEST : Direction.NORTHEAST;
//
////        System.out.println("Moving direction: " + movingDirection.toString());
//
//        Square moving = INITIAL_SQUARE;
//
////        System.out.println("Moving to square: " + String.valueOf(moveTo.getFile()) + moveTo.getRank());
//        do {
////            System.out.println("Moving square: " + String.valueOf(moving.getFile()) + moving.getRank());
//            if (moving == moveTo) {
//                super.move(moveTo);
//                return;
//            }
//            moving = moving.getNearbySquare(movingDirection, PLAYER);
//        } while (moving != null && moving.getPiece() == null);
    }
}