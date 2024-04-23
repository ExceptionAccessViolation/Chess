package game;

import board.Square;
import pieces.*;

import java.util.ArrayList;

public abstract class Utils {
    public static Player getPlayerObject(PlayerEnum playerEnum) {
        return playerEnum == PlayerEnum.WHITE ? Chess.white : Chess.black;
    }

    public static Player getOpponent(Player player) {
        return player == Chess.white ? Chess.black : Chess.white;
    }

    public static PlayerEnum getOpponent(PlayerEnum player) {
        return player == PlayerEnum.WHITE ? PlayerEnum.BLACK : PlayerEnum.WHITE;
    }

    public static String generateFEN(Square[][] boardArray) {
        assert boardArray.length == 8 && boardArray[0].length == 8 : new IllegalArgumentException("Length of boardArray is not equal to 8!");

        StringBuilder fen = new StringBuilder();

        for (int i = boardArray.length - 1; i >= 0; i--) {
            int emptySquares = 0;

            for (int j = 0; j < boardArray.length; j++) {
                Piece piece = boardArray[i][j].getPiece();

                if (piece == null)
                    emptySquares++;
                else {
                    if (emptySquares != 0)
                        fen.append(emptySquares);

                    emptySquares = 0;

                    char toAdd = switch (piece) {
                        case Pawn ignored -> 'p';
                        case Knight ignored -> 'n';
                        case Bishop ignored -> 'b';
                        case Rook ignored -> 'r';
                        case Queen ignored -> 'q';
                        case King ignored -> 'k';
                        default -> throw new IllegalArgumentException();
                    };

                    if (piece.getPlayerEnum() == PlayerEnum.WHITE)
                        toAdd = Character.toUpperCase(toAdd);

                    fen.append(toAdd);

                }
            }
            if (emptySquares != 0)
                fen.append(emptySquares);
            if (i != 0)
                fen.append('/');
        }

        return fen.toString();
    }

    public static ArrayList<Piece> decodeFen(String fen) {
        ArrayList<Piece> pieces = new ArrayList<>();
        int emptySquares = 0;
        int rank = 8;
        char file = 'A';

        for (int i = 0; i < fen.length(); i++) {
            char c = fen.charAt(i);

            if (Character.isLetter(c)) {
                PlayerEnum player = Character.isUpperCase(c) ? PlayerEnum.WHITE : PlayerEnum.BLACK;

                Piece piece = switch (Character.toLowerCase(c)) {
                    case 'b' -> new Bishop(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    case 'k' -> new King(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    case 'n' -> new Knight(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    case 'p' -> new Pawn(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    case 'q' -> new Queen(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    case 'r' -> new Rook(player, Chess.board.getSquare((char) (file + emptySquares), rank));
                    default -> throw new IllegalArgumentException("Character: " + c);
                };

                pieces.add(piece);
                file++;
            } else if (Character.isDigit(c)) {
                emptySquares += (c - '0'); // Subtracting the ASCII value of 0 from c, thus converting it to the raw digit
            } else if (c == '/') {
                emptySquares = 0;
                file = 'A';
                rank--;
            }
        }

        return pieces;
    }
}
