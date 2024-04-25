package game;

import board.Square;
import pieces.Pawn;
import pieces.Piece;

import java.io.*;
import java.util.Calendar;

public class MoveLogger {
    File file;
    BufferedReader reader;
    FileWriter writer;

    public MoveLogger() {
        file = new File(System.getProperty("user.dir") + "\\game.txt");
        try {
            if (!file.exists())
                System.out.println(file.createNewFile());

            reader = new BufferedReader(new FileReader(file));
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Piece moved, Square movedTo) throws IOException {
        String move = genMove(moved, movedTo);
        System.out.println(move);

        if (reader.readLine() == null) {
            System.out.println("File is empty.");
            writer.write("Game at: " + Calendar.getInstance().getTime() + "\n");
//            writer.write("Hello!");
            System.out.println("A");
            writer.append("White\t\tBlack\n");
            writer.append("1. ").append(genMove(moved, movedTo));
        } else {
            String str = "";
            System.out.println("jewogw");
            while (reader.readLine() != null)
                str = reader.readLine();

            int moveNum = str.charAt(0);
            if (str.lastIndexOf('\t') == -1)
                writer.append("\t\t").append(move);
            else
                writer.append("\n").append(move);
        }
    }

    private String genMove(Piece moved, Square movedTo) {
        return moved instanceof Pawn ? String.valueOf(moved.getLetter()) :
                "" + Character.toLowerCase(movedTo.getCoordinates().charAt(0)) + movedTo.getCoordinates().charAt(1);
    }
}
