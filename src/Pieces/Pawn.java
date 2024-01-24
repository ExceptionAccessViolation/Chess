package Pieces;

import Board.Direction;
import Board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    protected final int value = 1;

    public Pawn(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }
    public boolean hasMoved() {
        return this.getPlayer() == PlayerEnum.WHITE ? this.getSquare().getRank() != 2 : this.getSquare().getRank() != 7;
    }
    @Override
    public int getValue() {
        return value;
    }
    @Override
    public void move(Square moveTo) {
        if (this.getAvailableSquaresWithoutKing().contains(moveTo))
            super.move(moveTo);
    }
    
    @Override
    public ArrayList<Square> getAvailableSquares() {
        ArrayList<Square> availableSquares = new ArrayList<>();
        Square initialSquare = this.getSquare();
        PlayerEnum player = this.getPlayer();

        final int legalMoves = player == PlayerEnum.WHITE ? 
                (this.getSquare().getRank() == 2 ? 2 : 1) : (this.getSquare().getRank() == 7 ? 2 : 1);

        Square aheadSquare = initialSquare.getNearbySquare(Direction.NORTH, player);
        Square twoAheadSquare = aheadSquare.getNearbySquare(Direction.NORTH, player);

        if (!aheadSquare.hasPiece()) {
            if (legalMoves == 2) {
                if (!twoAheadSquare.hasPiece())
                    availableSquares.add(twoAheadSquare);
            }
            availableSquares.add(aheadSquare);
        }

        Square[] northwestNortheast = {initialSquare.getNearbySquare(Direction.NORTHEAST, player),
                initialSquare.getNearbySquare(Direction.NORTHWEST, player)};
        for (Square sq : northwestNortheast) {
            if (sq != null) {
                if (sq.hasPiece() && sq.getPiece().getPlayer() != this.getPlayer())
                    availableSquares.add(sq);
            }
        }

        return availableSquares;
    }

    public ArrayList<Square> getAvailableSquaresWithoutKing() {
        ArrayList<Square> availableSquares = getAvailableSquares();
        availableSquares.removeIf(sq -> sq.getPiece() instanceof King);
        return availableSquares;
    }

    public ArrayList<Square> getCapturableSquares() {
        Square initialSquare = this.getSquare();
        PlayerEnum player = this.getPlayer();
        ArrayList<Square> capturableSquares = new ArrayList<>();
        Square[] northwestNortheast = { initialSquare.getNearbySquare(Direction.NORTHEAST, player),
                initialSquare.getNearbySquare(Direction.NORTHWEST, player)};
        for (Square sq : northwestNortheast) {
            if (sq != null) {
//                System.out.println("not null: " + sq.coordinates);
                if (sq.hasPiece() && sq.getPiece().getPlayer() != this.getPlayer()) {
//                    System.out.print(sq.coordinates);
                    capturableSquares.add(sq);
                }
            }
        }
        return capturableSquares;
    }
}
