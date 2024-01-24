package Game;

import Board.Square;
import Pieces.Piece;
import Pieces.PieceEnum;
import Pieces.PlayerEnum;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Player {
    private ArrayList<Piece> pieces;
    PlayerEnum player;
    private int material;
    Player(PlayerEnum player, ArrayList<Piece> pieces) {
        this.pieces = pieces;
        this.player = player;

        this.updateMaterial();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    public boolean hasPiece(Piece piece) {
        return pieces.contains(piece);
    }
    public void deletePiece(Piece piece) {
        if (!pieces.remove(piece))
            throw new NoSuchElementException();
        this.updateMaterial();
    }
    public int getMaterial() {
        return this.material;
    }
    public void updateMaterial() {
        material = 0;
        for (Piece p : pieces) {
            material += p.getValue();
        }
    }
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }
}
