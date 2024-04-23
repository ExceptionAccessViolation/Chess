package board;

import java.util.List;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTHEAST,
    SOUTHEAST,
    NORTHWEST,
    SOUTHWEST;

    public static List<Direction> getCardinals() {
        return List.of(NORTH, SOUTH, EAST, WEST);
    }

    public static List<Direction> getDiagonals() {
        return List.of(NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }
}
