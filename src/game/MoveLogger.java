package game;

import board.Square;
import pieces.Pawn;
import pieces.Piece;

import java.io.*;
import java.util.Calendar;

public abstract class MoveLogger {
    static File file = new File(System.getProperty("user.dir") + "\\game.txt");
    static BufferedReader reader;
    static FileWriter writer;

    static {
        try {
            file.createNewFile();
            reader = new BufferedReader(new FileReader(file));
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(Piece moved, Square movedTo) throws IOException {
        String move = genMove(moved, movedTo);
        System.out.println(move);
        if (reader.read() == -1) {
            writer.write("Game at: " + Calendar.getInstance().getTime() + "\n");
            writer.append("White\t\tBlack\n");
            writer.append("1. ").append(genMove(moved, movedTo));
        } else {
            String str = "";
            while (reader.readLine() != null)
                str = reader.readLine();

            int moveNum = str.charAt(0);
            if (str.lastIndexOf('\t') == -1)
                writer.append("\t\t").append(move);
            else
                writer.append("\n").append(move);
        }
    }

    private static String genMove(Piece moved, Square movedTo) {
        return moved instanceof Pawn ? String.valueOf(moved.getLetter()) :
                "" + Character.toLowerCase(movedTo.getCoordinates().charAt(0)) + movedTo.getCoordinates().charAt(1);
    }
}
