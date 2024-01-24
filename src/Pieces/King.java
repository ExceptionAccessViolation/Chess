package Pieces;

import Board.Direction;
import Board.Square;
import Game.Chess;
import Game.Player;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class King extends Piece {
    protected final int value = 0;
    public King(PlayerEnum allegiance, Square square) {
        super(allegiance, square);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void move(Square moveTo) {
//        System.out.print(this.getPieceType() + " at " + this.getSquare().coordinates + "'s squares: ");
        for (Square sq : this.getAvailableSquares()) {
//            System.out.print(sq.coordinates + " ");
        }
//        System.out.println();
        if (this.getAvailableSquares().contains(moveTo)) {
                super.move(moveTo);
        }
    }
    @Override
    public ArrayList<Square> getAvailableSquares() {
        Square initialSquare = this.getSquare();
        ArrayList<Square> availableSquares = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Square nearbySquare = initialSquare.getNearbySquare(d, this.getPlayer());
            if (nearbySquare != null) {
                Player opponent = Chess.getPlayerObject(this.getPlayer() == PlayerEnum.WHITE ? PlayerEnum.BLACK : PlayerEnum.WHITE);
                if (nearbySquare.hasPiece()) {
                    if (nearbySquare.getPiece().getPlayer() != this.getPlayer()) {
                        availableSquares.add(nearbySquare);
                        continue;
                    }
                }
                boolean valid = true;
                for (Piece p : opponent.getPieces()) {
                    if (p instanceof Pawn) {
                        if (((Pawn) p).getCapturableSquares().contains(nearbySquare)) {
//                            System.out.println("Piece type: " + p.getPieceType() + "\nPiece coordinates: " + p.getSquare().coordinates);
                            valid = false;
                            break;
                        }
                    } else if (!(p instanceof King)) {
                        if (p.getAvailableSquares().contains(nearbySquare)) {
//                            System.out.println("Piece type: " + p.getPieceType() + "\nPiece coordinates: " + p.getSquare().coordinates);
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid)
                    availableSquares.add(nearbySquare);
            }
        }
        return availableSquares;
    }
}