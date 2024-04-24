package pieces;

import board.Direction;
import board.Square;
import game.PlayerEnum;

import java.util.ArrayList;

public class Pawn extends Piece {
    private final int value = 1;
    private final char letter = '.'; // for debug

    public Pawn(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    public boolean hasMoved() {
        return this.getPlayerEnum() == PlayerEnum.WHITE ? this.getSquare().getRank() != 2 : this.getSquare().getRank() != 7;
    }

    @Override
    public boolean move(Square moveTo) {
        if (this.getAvailableSquares().contains(moveTo))
            return super.move(moveTo);

        return false;
    }

    @Override
    public ArrayList<Square> getAvailableSquares() {
        ArrayList<Square> availableSquares = new ArrayList<>();
        Square initialSquare = this.getSquare();
        PlayerEnum player = this.getPlayerEnum();

        // If the pawn is not on the starting rank, it can only move one square
        final int legalMoves = this.hasMoved() ? 1 : 2;

        Square aheadSquare = initialSquare.getNearbySquare(Direction.NORTH, player);
        Square twoAheadSquare = aheadSquare.getNearbySquare(Direction.NORTH, player);

        if (!aheadSquare.hasPiece()) {
            if (legalMoves == 2 && !twoAheadSquare.hasPiece())
                    availableSquares.add(twoAheadSquare);

            availableSquares.add(aheadSquare);
        }

        Square[] northwestNortheast = { initialSquare.getNearbySquare(Direction.NORTHEAST, player),
                initialSquare.getNearbySquare(Direction.NORTHWEST, player) };
        for (Square sq : northwestNortheast) {
            if (sq != null) {
                // If the piece on the square is not of the same player's, the square can be moved to
                if (sq.hasPiece() && sq.getPiece().getPlayerEnum() != this.getPlayerEnum())
                    availableSquares.add(sq);
            }
        }

        return availableSquares;
    }

    @Override
    public ArrayList<Square> getProtectedSquares() { // TODO
        return new ArrayList<>();
    }

    public ArrayList<Square> getCapturableSquares() {
        Square initialSquare = this.getSquare();
        PlayerEnum player = this.getPlayerEnum();
        ArrayList<Square> capturableSquares = new ArrayList<>();
        Square[] northwestNortheast = { initialSquare.getNearbySquare(Direction.NORTHEAST, player),
                initialSquare.getNearbySquare(Direction.NORTHWEST, player) };
        for (Square sq : northwestNortheast) {
            if (sq != null) {
                if (sq.hasPiece() && sq.getPiece().getPlayerEnum() != this.getPlayerEnum())
                    capturableSquares.add(sq);
            }
        }
        return capturableSquares;
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
