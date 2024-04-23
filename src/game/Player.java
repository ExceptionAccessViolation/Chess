package game;

import pieces.Piece;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Player {
    private final ArrayList<Piece> pieces;
    private final ArrayList<Piece> capturedPieces = new ArrayList<>();
    final PlayerEnum player;
    private int material;

    Player(PlayerEnum player, ArrayList<Piece> pieces) {
        this.pieces = pieces;
        this.player = player;

        this.updateMaterial();
    }

    public boolean hasPiece(Piece piece) {
        return pieces.contains(piece);
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void deletePiece(Piece piece) {
        if (!pieces.remove(piece))
            throw new NoSuchElementException();
        else
            capturedPieces.add(piece);

        this.updateMaterial();
        System.out.println(piece.getName());
    }


    public void updateMaterial() {
        material = 0;
        for (Piece p : pieces) {
            material += p.getValue();
        }
    }


    public int getMaterial() {
        return this.material;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ArrayList<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public PlayerEnum getPlayerEnum() {
        return player;
    }
}
